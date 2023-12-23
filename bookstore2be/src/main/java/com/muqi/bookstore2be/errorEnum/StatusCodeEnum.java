package com.muqi.bookstore2be.errorEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    OK(200, "ok"),

    USER_AUTHORIZATION_ERROR(401, "authorization fail"),
    USER_NOT_EXIST(511, "not user id"),
    USER_EXIST(512, "exist user id"),
    USER_LOGIN_ERROR(513, "user id:"),
    STORE_EXIST(514, "exist store id"),
    STORE_NOT_EXIST(515, "exist store id"),
    BOOK_EXIST(516, "exist book id"),
    BOOK_NOT_EXIST(517, "not exist book id"),
    STOCK_LEVEL_LOW(518, "stock level low"),
    ORDER_NOT_EXIST(519, "not exist order"),
    NOT_SUFFICIENT_FUNDS(520, "not sufficient fund"),
    BASE_ERROR(530, "base error"), ORDER_HAS_PAID(521, "order has paid"), ORDER_HAS_RECEIVED(522, "order has been received"), ORDER_HAS_SENT(523, "order has sent" ), ORDER_NOT_PAID(524, "order not paid" ), DUPLICATE_CANCEL_ORDER(525, "duplicate cancel order"), ORDER_HAS_CANCELED(526, "order has cancelled");
    private final Integer code;
    private final String desc;

}
