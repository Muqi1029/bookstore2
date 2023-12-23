package com.muqi.bookstore2be.domain.request;

import lombok.Data;

@Data
public class CancelOrderRequest {

    private String seller_id;
    private String buyer_id;

    private String order_id;

}
