package com.muqi.bookstore2be.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.muqi.bookstore2be.domain.Book;
import com.muqi.bookstore2be.domain.User;
import com.muqi.bookstore2be.domain.request.BookIdRequest;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;

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

    StatusCodeEnum payment(String userId, String password, String orderId);

    StatusCodeEnum createStore(String userId, String storeId);

    /**
     * 用户向指定商店购买书籍
     * @param userId
     * @param storeId
     * @param bookList
     * @return
     */
    StatusCodeEnum newOrder(String userId, String storeId, String orderId, List<BookIdRequest> bookList);

    /**
     * 卖家增加书籍
     * @param userId
     * @param storeId
     * @param bookInfo
     * @param stockLevel
     * @return
     */
    StatusCodeEnum addBook(String userId, String storeId, Book bookInfo, int stockLevel) throws JsonProcessingException;

    StatusCodeEnum addStockLevel(String userId, String storeId, String bookId, int addStockLevel);

    StatusCodeEnum changePwd(String userId, String oldPassword, String newPassword);
}
