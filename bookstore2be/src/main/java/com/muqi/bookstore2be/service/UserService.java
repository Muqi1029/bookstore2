package com.muqi.bookstore2be.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muqi.bookstore2be.domain.User;
import com.muqi.bookstore2be.domain.request.Book;
import com.muqi.bookstore2be.domain.request.BuyerPaymentRequest;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mq
* @description 针对表【user】的数据库操作Service
* @createDate 2023-12-21 21:27:39
*/
public interface UserService extends IService<User> {

    boolean register(String userId, String password);

    boolean unregister(String userId, String password);

    String login(String userId, String password, String terminal);

    boolean logout(String userId, String token);

    boolean addFunds(String userId, String password, int addValue);

    boolean payment(String userId, String password, String orderId);

    StatusCodeEnum createStore(String userId, String storeId);

    StatusCodeEnum newOrder(String userId, String storeId, List<Book> bookList);

    /**
     * 卖家增加书籍
     * @param userId
     * @param storeId
     * @param bookInfo
     * @param stockLevel
     * @return
     */
    StatusCodeEnum addBook(String userId, String storeId, String bookInfo, int stockLevel);
}
