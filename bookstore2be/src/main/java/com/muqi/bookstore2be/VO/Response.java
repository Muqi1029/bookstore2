package com.muqi.bookstore2be.VO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {
    private Boolean flag;

    private int code;

    private String message;

    private T data;

}
