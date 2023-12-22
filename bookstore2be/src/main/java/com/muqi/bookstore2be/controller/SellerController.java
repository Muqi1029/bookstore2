package com.muqi.bookstore2be.controller;

import com.muqi.bookstore2be.domain.request.SellerAddBookRequest;
import com.muqi.bookstore2be.domain.request.SellerCreateStoreRequest;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import com.muqi.bookstore2be.service.StoreService;
import com.muqi.bookstore2be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    UserService userService;

    @Autowired
    StoreService storeService;

    @PostMapping("/create_store")
    public ResponseEntity<String> create_store(@RequestBody SellerCreateStoreRequest sellerCreateStoreRequest) {
        StatusCodeEnum result = userService.createStore(sellerCreateStoreRequest.getUser_id(), sellerCreateStoreRequest.getStore_id());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }

    @PostMapping("/add_book")
    public ResponseEntity<String> add_book(@RequestBody SellerAddBookRequest sellerAddBookRequest) {
        StatusCodeEnum result = userService.addBook(sellerAddBookRequest.getUser_id(), sellerAddBookRequest.getStore_id(), sellerAddBookRequest.getBook_info(), sellerAddBookRequest.getStock_level());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }
}
