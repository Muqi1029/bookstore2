package com.muqi.bookstore2be.controller;

import com.muqi.bookstore2be.domain.request.*;
import com.muqi.bookstore2be.domain.response.CheckOrderResponse;
import com.muqi.bookstore2be.domain.response.NewOrderResponse;
import com.muqi.bookstore2be.domain.response.SearchResponse;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import com.muqi.bookstore2be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    @PostMapping("/payment")
    public ResponseEntity<String> payment(@RequestBody BuyerPaymentRequest buyerPaymentRequest) {
        StatusCodeEnum result = userService.payment(buyerPaymentRequest.getUser_id(), buyerPaymentRequest.getPassword(), buyerPaymentRequest.getOrder_id());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());

    }

    @PostMapping("/new_order")
    public ResponseEntity<NewOrderResponse> new_order(@RequestBody BuyerNewOrderRequest buyerNewOrderRequest) {
        String user_id = buyerNewOrderRequest.getUser_id();
        String store_id = buyerNewOrderRequest.getStore_id();

        String order_id = buyerNewOrderRequest.getUser_id() + "_" + store_id + "_" + UUID.randomUUID();
        StatusCodeEnum result = userService.newOrder(user_id, store_id, order_id, buyerNewOrderRequest.getBooks());
        if (result.getCode() == HttpStatus.OK.value()) {
            NewOrderResponse newOrderResponse = new NewOrderResponse(result.getCode(), order_id);
            return ResponseEntity.ok(newOrderResponse);
        }
        return ResponseEntity.status(result.getCode()).body(new NewOrderResponse());
    }

    @PostMapping("/receive")
    public ResponseEntity<String> receive(@RequestBody BuyerReceiveRequest buyerReceiveRequest) {
        String buyer_id = buyerReceiveRequest.getBuyer_id();
        String order_id = buyerReceiveRequest.getOrder_id();
        StatusCodeEnum result = userService.receive(buyer_id, order_id);
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }

    @PostMapping("/cancel_order")
    public ResponseEntity<String> cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest) {
        StatusCodeEnum result = userService.cancelOrder(cancelOrderRequest.getBuyer_id(), cancelOrderRequest.getSeller_id(), cancelOrderRequest.getOrder_id());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }

    @PostMapping("/check_order")
    public ResponseEntity<CheckOrderResponse> checkOrder(@RequestBody CheckOrderRequest checkOrderRequest) {
        String buyerId = checkOrderRequest.getBuyer_id();
        CheckOrderResponse result = userService.checkOrder(buyerId);
        return ResponseEntity.status(result.getStatusCodeEnum().getCode()).body(result);
    }

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> search(@RequestBody BuyerSearchRequest buyerSearchRequest) {
        String keyword = buyerSearchRequest.getKeyword();
        String storeId = buyerSearchRequest.getStore_id();
        int page = buyerSearchRequest.getPage();
        int size = buyerSearchRequest.getSize();
        SearchResponse result = userService.search(keyword, storeId, page, size);
        return ResponseEntity.status(result.getStatusCodeEnum().getCode()).body(result);
    }

}
