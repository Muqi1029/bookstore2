package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.bookstore2be.domain.AllOrder;
import com.muqi.bookstore2be.domain.Store;
import com.muqi.bookstore2be.domain.User;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import com.muqi.bookstore2be.mapper.AllOrderMapper;
import com.muqi.bookstore2be.service.AllOrderService;
import com.muqi.bookstore2be.service.StoreService;
import com.muqi.bookstore2be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.muqi.bookstore2be.errorEnum.StatusCodeEnum.*;

/**
 * @author mq
 * @description 针对表【all_order】的数据库操作Service实现
 * @createDate 2023-12-22 19:42:18
 */
@Service
public class AllOrderServiceImpl extends ServiceImpl<AllOrderMapper, AllOrder>
        implements AllOrderService {
    @Override
    public List<AllOrder> getUnpaidOrders() {
       return this.list(new QueryWrapper<AllOrder>().eq("status", 0));
    }

    @Override
    public void cancelOrder(String order_id) {
        try {
            AllOrder order = this.getById(order_id);
            order.setStatus(-1);
            this.updateById(order);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    @Autowired
//    UserService userService;
//
//    @Autowired
//    StoreService storeService;
//
//    @Override
//    public StatusCodeEnum cancelOrder(String buyerId, String sellerId, String orderId) {
//        try {
//            if (buyerId == null) {
//                // seller cancels order
//                User seller = userService.getById(sellerId);
//                if (seller == null) {
//                    return USER_NOT_EXIST;
//                }
//                AllOrder order = this.getById(orderId);
//                if (order == null) {
//                    return ORDER_NOT_EXIST;
//                }
//                int status = order.getStatus();
//                int price = order.getPrice();
//                String buyer_id = order.getUserId();
//                User buyer = userService.getById(buyer_id);
//                if (buyer == null) {
//                    return USER_NOT_EXIST;
//                }
//                if (status == 1) {
//                    // users has paid
//                    int sellerBalance = seller.getBalance();
//                    if (sellerBalance - price < 0) {
//                        return NOT_SUFFICIENT_FUNDS;
//                    }
//                    seller.setBalance(sellerBalance - price);
//                    buyer.setBalance(buyer.getBalance() + price);
//                    if (!userService.updateById(seller) || !userService.updateById(buyer)) {
//                        return BASE_ERROR;
//                    }
//                }
//                order.setStatus(-1);
//                if (this.updateById(order)) {
//                    return OK;
//                }
//                return BASE_ERROR;
//            } else if (sellerId == null) {
//                // buyer cancels order
//                User buyer = userService.getById(buyerId);
//                if (buyer == null) {
//                    return USER_NOT_EXIST;
//                }
//                AllOrder order = this.getById(orderId);
//                if (order == null) {
//                    return ORDER_NOT_EXIST;
//                }
//                int status = order.getStatus();
//                int price = order.getPrice();
//                // find the seller
//                Store store = storeService.getById(order.getStoreId());
//                User seller = userService.getById(store.getUserId());
//                if (seller == null) {
//                    return USER_NOT_EXIST;
//                }
//
//                if (status == 1) {
//                    int balance = seller.getBalance();
//                    if (balance - price < 0) {
//                        return NOT_SUFFICIENT_FUNDS;
//                    }
//                    seller.setBalance(balance - price);
//                    buyer.setBalance(buyer.getBalance() + price);
//                    if (!userService.updateById(seller) || !userService.updateById(buyer)) {
//                        return BASE_ERROR;
//                    }
//                }
//                order.setStatus(-1);
//                if (this.updateById(order)) {
//                    return OK;
//                }
//                return BASE_ERROR;
//            }
//            return USER_NOT_EXIST;
//        } catch (Exception e) {
//            System.out.println(e);
//            return BASE_ERROR;
//        }
//    }
}
