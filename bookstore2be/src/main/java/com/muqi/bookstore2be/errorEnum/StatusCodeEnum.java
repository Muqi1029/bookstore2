package com.muqi.bookstore2be.errorEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    USER_AUTHORIZATION_ERROR(401, "authorization fail"),
    USER_EXIST(512, "exist user id"),
    USER_LOGIN_ERROR(513, "user id:");


    private final Integer code;
    private final String desc;

    }
