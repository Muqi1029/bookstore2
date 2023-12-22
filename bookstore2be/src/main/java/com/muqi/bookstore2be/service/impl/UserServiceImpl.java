package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muqi.bookstore2be.domain.Store;
import com.muqi.bookstore2be.domain.User;
import com.muqi.bookstore2be.domain.request.Book;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import com.muqi.bookstore2be.mapper.UserMapper;
import com.muqi.bookstore2be.service.StoreService;
import com.muqi.bookstore2be.service.UserService;
import com.muqi.bookstore2be.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
            // TODO: 自定义异常类
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
    public boolean payment(String userId, String password, String orderId) {
        return false;
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
            return BASE_ERROR;
        }
    }

    @Override
    public StatusCodeEnum newOrder(String userId, String storeId, List<Book> bookList) {
       for (Book book: bookList) {

       }
       return null;
    }

    @Override
    public StatusCodeEnum addBook(String userId, String storeId, String bookInfo, int stockLevel) {
        User user = this.getById(userId);
        if (user == null) {
            return USER_NOT_EXIST;
        }
        Store store = storeService.getById(storeId);
        if (store == null) {
            return STORE_NOT_EXIST;
        }
        store = storeService.getOne(new QueryWrapper<Store>().eq("store_id", storeId).eq("book_id", book_id));
        if (store != null) {
            return BOOK_EXIST;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Book

        Store update_store = new Store();
        update_store.setStoreId(storeId);
        update_store.setUserId(userId);
        update_store.setBookId(book_id);
        update_store.setBookInfo(bookInfo);
        update_store.setStockLevel(stockLevel);
        if (storeService.save(update_store)) {
            return OK;
        }
        return BASE_ERROR;
    }

}
