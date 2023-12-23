package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.bookstore2be.domain.*;
import com.muqi.bookstore2be.domain.request.BookIdRequest;
import com.muqi.bookstore2be.domain.response.CheckOrderResponse;
import com.muqi.bookstore2be.domain.response.SearchResponse;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import com.muqi.bookstore2be.mapper.AllOrderMapper;
import com.muqi.bookstore2be.mapper.StoreBookMapper;
import com.muqi.bookstore2be.mapper.UserMapper;
import com.muqi.bookstore2be.service.*;
import com.muqi.bookstore2be.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.muqi.bookstore2be.errorEnum.StatusCodeEnum.*;

/**
 * @author mq
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-12-21 21:27:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    StoreService storeService;

    @Autowired
    StoreBookService storeBookService;

    @Autowired
    StoreBookMapper storeBookMapper;

    @Autowired
    AllOrderService allOrderService;

    @Autowired
    AllOrderMapper allOrderMapper;
    @Autowired
    OrderBookService orderBookService;

    @Override
    public boolean register(String userId, String password) {
        String terminal = "terminal_" + Instant.now().toEpochMilli();
        String token = TokenUtil.generateToken1(userId, terminal);
        User user = new User(userId, password, 0, token, terminal);
        try {
            return this.save(user);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean unregister(String userId, String password) {
        User user = this.getById(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }
        return this.removeById(userId);
    }

    @Override
    public String login(String userId, String password, String terminal) {
        User user = this.getById(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        String token = TokenUtil.generateToken1(userId, terminal);
        user.setTerminal(terminal);
        user.setToken(token);
        if (this.saveOrUpdate(user)) {
            return token;
        }
        return null;
    }

    @Override
    public boolean logout(String userId, String token) {
        User user = this.getById(userId);
        if (user == null || !user.getToken().equals(token)) {
            return false;
        }
        String terminal = "terminal_" + Instant.now().toEpochMilli();
        String new_token = TokenUtil.generateToken1(userId, terminal);
        user.setToken(new_token);
        user.setTerminal(terminal);
        return this.saveOrUpdate(user);
    }

    @Override
    public boolean addFunds(String userId, String password, int addValue) {
        User user = this.getById(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }
        user.setBalance(user.getBalance() + addValue);
        return this.updateById(user);
    }

    @Override
    public StatusCodeEnum payment(String userId, String password, String orderId) {
        try {
            AllOrder order = allOrderService.getById(orderId);
            // authorize order
            if (order == null) {
                return ORDER_NOT_EXIST;
            }
            if (order.getStatus() == 1) {
                return ORDER_HAS_PAID;
            }
            if (!userId.equals(order.getUserId())) {
                return USER_AUTHORIZATION_ERROR;
            }
            // authorize buyer
            User buyer = this.getById(userId);
            if (buyer == null) {
                return USER_NOT_EXIST;
            }
            if (!buyer.getPassword().equals(password)) {
                return USER_AUTHORIZATION_ERROR;
            }
            // authorize store
            Store store = storeService.getById(storeService.getById(order.getStoreId()));
            if (store == null) {
                return STORE_NOT_EXIST;
            }
            // authorize seller
            String sellerId = store.getUserId();
            User seller = this.getById(sellerId);
            if (seller == null) {
                return USER_NOT_EXIST;
            }
            if (buyer.getBalance() < order.getPrice()) {
                return NOT_SUFFICIENT_FUNDS;
            }
            buyer.setBalance(buyer.getBalance() - order.getPrice());
            seller.setBalance(seller.getBalance() + order.getPrice());
            boolean b1 = this.updateById(buyer);
            boolean b2 = this.updateById(seller);

            order.setStatus(1);
            boolean b3 = allOrderService.updateById(order);
            if (b1 && b2 && b3) {
                return OK;
            }
            return BASE_ERROR;
        } catch (Exception e) {
            return BASE_ERROR;
        }
    }

    @Override
    public StatusCodeEnum createStore(String userId, String storeId) {
        try {
            User user = this.getById(userId);
            if (user == null) {
                return USER_NOT_EXIST;
            }
            Store store = storeService.getById(storeId);
            if (store != null) {
                return STORE_EXIST;
            }
            Store new_store = new Store();
            new_store.setUserId(userId);
            new_store.setStoreId(storeId);
            if (storeService.save(new_store)) {
                return OK;
            }
            return BASE_ERROR;
        } catch (Exception e) {
            System.out.println(e);
            return BASE_ERROR;
        }
    }

    @Override
    public StatusCodeEnum newOrder(String userId, String storeId, String orderId, List<BookIdRequest> bookList) {
        try {
            User user = this.getById(userId);
            if (user == null) {
                return USER_NOT_EXIST;
            }
            Store store = storeService.getById(storeId);
            if (store == null) {
                return STORE_NOT_EXIST;
            }

            int order_price = 0;

            for (BookIdRequest bookIdRequest : bookList) {
                String bookId = bookIdRequest.getId();
                int count = bookIdRequest.getCount();
                StoreBook storeBook = storeBookService.getOne(new QueryWrapper<StoreBook>().eq("book_id", bookId).eq("store_id", storeId));
                if (storeBook == null) {
                    return BOOK_NOT_EXIST;
                }
                int stock_level = storeBook.getStockLevel();
                if (stock_level < count) {
                    return STOCK_LEVEL_LOW;
                }
                storeBook.setStockLevel(stock_level - count);
                storeBookService.updateById(storeBook);
                order_price += count * storeBook.getPrice();

                // insert an order
                OrderBook orderBook = new OrderBook();
                orderBook.setOrderId(orderId);
                orderBook.setBookId(bookId);
                orderBook.setPrice(storeBook.getPrice()); // store 单价
                orderBook.setCount(count);
                orderBookService.save(orderBook);
            }

            // insert this order to all_order table
            AllOrder allOrder = new AllOrder();
            allOrder.setOrderId(orderId);
            allOrder.setUserId(userId);
            allOrder.setStoreId(storeId);
            allOrder.setPrice(order_price); // store 总价
            if (allOrderService.save(allOrder)) {
                return OK;
            }
            return BASE_ERROR;
        } catch (Exception e) {
            System.out.println(e);
            return BASE_ERROR;
        }
    }

    @Override
    public StatusCodeEnum addBook(String userId, String storeId, Book bookInfo, int stockLevel) {
        try {
            User user = this.getById(userId);
            if (user == null) {
                return USER_NOT_EXIST;
            }
            Store store = storeService.getById(storeId);
            if (store == null) {
                return STORE_NOT_EXIST;
            }

            String book_id = bookInfo.getId();

            StoreBook storeBook = storeBookService.getOne(new QueryWrapper<StoreBook>().eq("store_id", storeId).eq("book_id", book_id));
            if (storeBook != null) {
                return BOOK_EXIST;
            }

            StoreBook update_store = new StoreBook();
            update_store.setStoreId(storeId);
            update_store.setBookId(book_id);
            update_store.setStockLevel(stockLevel);
            update_store.setPrice(bookInfo.getPrice());
            update_store.setAuthor(bookInfo.getAuthor());
            update_store.setAuthorIntro(bookInfo.getAuthorIntro());
            update_store.setBookIntro(bookInfo.getBookIntro());
            update_store.setContent(bookInfo.getContent());
            update_store.setPages(bookInfo.getPages());
            update_store.setPublisher(bookInfo.getPublisher());
            update_store.setTags(bookInfo.getTags());
            update_store.setTitle(bookInfo.getTitle());

            if (storeBookService.save(update_store)) {
                return OK;
            }
            return BASE_ERROR;
        } catch (Exception e) {
            System.out.println(e);
            return BASE_ERROR;
        }
    }

    @Override
    public StatusCodeEnum addStockLevel(String userId, String storeId, String bookId, int addStockLevel) {
        try {
            User user = this.getById(userId);
            if (user == null) {
                return USER_NOT_EXIST;
            }
            Store store = storeService.getById(storeId);
            if (store == null) {
                return STORE_NOT_EXIST;
            }
            StoreBook storeBook = storeBookService.getOne(new QueryWrapper<StoreBook>().eq("book_id", bookId).eq("store_id", storeId));
            if (storeBook == null) {
                return BOOK_NOT_EXIST;
            }
            storeBook.setStockLevel(addStockLevel + storeBook.getStockLevel());
            if (storeBookService.updateById(storeBook)) {
                return OK;
            }
            return BASE_ERROR;

        } catch (Exception e) {
            System.out.println(e);
            return BASE_ERROR;
        }

    }

    @Override
    public StatusCodeEnum changePwd(String userId, String oldPassword, String newPassword) {
        try {
            User user = this.getById(userId);
            if (user == null) {
                return USER_NOT_EXIST;
            }
            if (!user.getPassword().equals(oldPassword)) {
                return USER_AUTHORIZATION_ERROR;
            }
            String terminal = "terminal_" + new Date().toString();
            String newToken = TokenUtil.generateToken1(userId, terminal);
            user.setPassword(newPassword);
            user.setToken(newToken);
            if (this.updateById(user)) {
                return OK;
            }
            return BASE_ERROR;
        } catch (Exception e) {
            System.out.println(e);
            return BASE_ERROR;
        }
    }

    @Override
    public StatusCodeEnum shipment(String sellerId, String buyerId, String orderId) {
        try {
            User seller = this.getById(sellerId);
            if (seller == null) {
                return USER_NOT_EXIST;
            }
            User buyer = this.getById(buyerId);
            if (buyer == null) {
                return USER_NOT_EXIST;
            }
            AllOrder order = allOrderService.getOne(new QueryWrapper<AllOrder>().eq("user_id", buyerId).eq("order_id", orderId));
            if (order == null) {
                return ORDER_NOT_EXIST;
            }
            if (order.getStatus() == 2) {
                return ORDER_HAS_SENT;
            }
            if (order.getStatus() == 3) {
                return ORDER_HAS_RECEIVED;
            }
            order.setStatus(2);
            if (allOrderService.updateById(order)) {
                return OK;
            }
            return BASE_ERROR;
        } catch (Exception e) {
            System.out.println(e);
            return BASE_ERROR;
        }
    }

    @Override
    public StatusCodeEnum receive(String buyerId, String orderId) {
        try {
            User buyer = this.getById(buyerId);
            if (buyer == null) {
                return USER_NOT_EXIST;
            }
            AllOrder order = allOrderService.getOne(new QueryWrapper<AllOrder>().eq("order_id", orderId).eq("user_id", buyerId));
            if (order == null) {
                return ORDER_NOT_EXIST;
            }
            int status = order.getStatus();
            if (status == -1) {
                return ORDER_HAS_CANCELED;
            }
            if (status == 0) {
                return ORDER_NOT_PAID;
            }
            if (status == 1) {
                return ORDER_HAS_PAID;
            }
            if (status == 3) {
                return ORDER_HAS_RECEIVED;
            }
            order.setStatus(3);
            if (allOrderService.updateById(order)) {
                return OK;
            }
            return BASE_ERROR;
        } catch (Exception e) {
            System.out.println(e);
            return BASE_ERROR;
        }
    }


    @Override
    public StatusCodeEnum cancelOrder(String buyerId, String sellerId, String orderId) {
        try {
            if (buyerId != null && sellerId != null) {
                return BASE_ERROR;
            }
            AllOrder order = allOrderService.getById(orderId);
            if (order == null) {
                return ORDER_NOT_EXIST;
            }
            int status = order.getStatus();
            int price = order.getPrice();

            if (status == -1) {
                return DUPLICATE_CANCEL_ORDER;
            }
            User seller = null;
            User buyer = null;

            if (buyerId == null) {
                // seller cancels order
                seller = this.getById(sellerId);
                if (seller == null) {
                    return USER_NOT_EXIST;
                }
                String buyer_id = order.getUserId();
                buyer = this.getById(buyer_id);
                if (buyer == null) {
                    return USER_NOT_EXIST;
                }
            } else {
                // buyer cancels order
                buyer = this.getById(buyerId);
                if (buyer == null) {
                    return USER_NOT_EXIST;
                }
                // find the seller
                Store store = storeService.getById(order.getStoreId());
                seller = this.getById(store.getUserId());
                if (seller == null) {
                    return USER_NOT_EXIST;
                }
            }

            if (status == 1) {
                int balance = seller.getBalance();
                if (balance - price < 0) {
                    return NOT_SUFFICIENT_FUNDS;
                }
                seller.setBalance(balance - price);
                buyer.setBalance(buyer.getBalance() + price);
                if (!this.updateById(seller) || !this.updateById(buyer)) {
                    return BASE_ERROR;
                }
            }
            order.setStatus(-1);
            if (allOrderService.updateById(order)) {
                return OK;
            }
            return BASE_ERROR;
        } catch (Exception e) {
            System.out.println(e);
            return BASE_ERROR;
        }
    }

    @Override
    public CheckOrderResponse checkOrder(String buyerId) {
        try {
            User buyer = this.getById(buyerId);
            CheckOrderResponse result = new CheckOrderResponse();
            if (buyer == null) {
                result.setStatusCodeEnum(USER_NOT_EXIST);
                return result;
            }
            // start checking
            List<AllOrder> cancel = allOrderService.list(new QueryWrapper<AllOrder>().eq("user_id", buyerId).eq("status", -1));
            List<AllOrder> notPaid = allOrderService.list(new QueryWrapper<AllOrder>().eq("user_id", buyerId).eq("status", 0));
            List<AllOrder> hasPaid = allOrderService.list(new QueryWrapper<AllOrder>().eq("user_id", buyerId).eq("status", 1));
            List<AllOrder> hasSent= allOrderService.list(new QueryWrapper<AllOrder>().eq("user_id", buyerId).eq("status", 2));
            List<AllOrder> hasReceived = allOrderService.list(new QueryWrapper<AllOrder>().eq("user_id", buyerId).eq("status", 3));
            result.setCancel_order(cancel);
            result.setNot_paid_order(notPaid);
            result.setPaid_order(hasPaid);
            result.setSent_order(hasSent);
            result.setReceived_order(hasReceived);

            result.setStatusCodeEnum(OK);
            return result;

        } catch (Exception e) {
            System.out.println(e);
            CheckOrderResponse result = new CheckOrderResponse();
            result.setStatusCodeEnum(BASE_ERROR);
            return result;
        }

    }

    @Override
    public SearchResponse search(String keyword, String storeId, int page, int size) {
        try {
            SearchResponse result = new SearchResponse();
            Page<StoreBook> pageable = new Page<>(page, size);
            QueryWrapper<StoreBook> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("title", keyword).or().like("author", keyword).like("content", keyword).like("tags", keyword);

            if (storeId != null) {
                queryWrapper.eq("store_id", storeId);
            }
//            queryWrapper.groupBy("book_id");
            Page<StoreBook> bookPage = storeBookMapper.selectPage(pageable, queryWrapper);
            result.setBookPage(bookPage);
            result.setStatusCodeEnum(OK);
            return result;
        }catch (Exception e) {
            System.out.println(e);
            SearchResponse result = new SearchResponse();
            result.setStatusCodeEnum(BASE_ERROR);
            return result;
        }
    }
}

