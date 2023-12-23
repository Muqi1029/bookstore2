package com.muqi.bookstore2be.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.muqi.bookstore2be.domain.request.*;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import com.muqi.bookstore2be.service.StoreService;
import com.muqi.bookstore2be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> add_book(@RequestBody SellerAddBookRequest sellerAddBookRequest) throws JsonProcessingException {
        StatusCodeEnum result = userService.addBook(sellerAddBookRequest.getUser_id(), sellerAddBookRequest.getStore_id(), sellerAddBookRequest.getBook_info(), sellerAddBookRequest.getStock_level());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }

    @PostMapping("/add_stock_level")
    public ResponseEntity<String> add_stock_level(@RequestBody SellerAddStockLevelRequest sellerAddStockLevelRequest) {
        StatusCodeEnum result = userService.addStockLevel(sellerAddStockLevelRequest.getUser_id(), sellerAddStockLevelRequest.getStore_id(), sellerAddStockLevelRequest.getBook_id(), sellerAddStockLevelRequest.getAdd_stock_level());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }

    @PostMapping("/shipment")
    public ResponseEntity<String> shipment(@RequestBody SellerShipmentRequest sellerShipmentRequest) {
        StatusCodeEnum result = userService.shipment(sellerShipmentRequest.getSeller_id(), sellerShipmentRequest.getBuyer_id(), sellerShipmentRequest.getOrder_id());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }

    @PostMapping("/cancel_order")
    public ResponseEntity<String> cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest) {
        StatusCodeEnum result = userService.cancelOrder(cancelOrderRequest.getBuyer_id(), cancelOrderRequest.getSeller_id(), cancelOrderRequest.getOrder_id());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }
}
