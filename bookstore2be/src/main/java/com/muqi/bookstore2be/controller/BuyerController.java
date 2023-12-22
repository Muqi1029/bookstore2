package com.muqi.bookstore2be.controller;

import com.muqi.bookstore2be.domain.request.BuyerAddFundsRequest;
import com.muqi.bookstore2be.domain.request.BuyerNewOrderRequest;
import com.muqi.bookstore2be.domain.request.BuyerPaymentRequest;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import com.muqi.bookstore2be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.muqi.bookstore2be.errorEnum.StatusCodeEnum.*;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    UserService userService;

    @PostMapping("/add_funds")
    public ResponseEntity<String> add_funds(@RequestBody BuyerAddFundsRequest buyerAddFunds) {
        boolean result = userService.addFunds(buyerAddFunds.getUser_id(), buyerAddFunds.getPassword(), buyerAddFunds.getAdd_value());
        if (result) {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.status(USER_AUTHORIZATION_ERROR.getCode()).body(USER_AUTHORIZATION_ERROR.getDesc());
    }

//    @PostMapping("/payment")
//    public ResponseEntity<String> payment(@RequestBody BuyerPaymentRequest buyerPaymentRequest) {
//        userService.payment(buyerPaymentRequest.getUser_id(), buyerPaymentRequest.getPassword(), buyerPaymentRequest.getOrder_id());
//    }
//
    @PostMapping("/new_order")
    public ResponseEntity<String> new_order(@RequestBody BuyerNewOrderRequest buyerNewOrderRequest) {
        StatusCodeEnum result = userService.newOrder(buyerNewOrderRequest.getUser_id(), buyerNewOrderRequest.getOrder_id(), buyerNewOrderRequest.getBooks());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }
}
