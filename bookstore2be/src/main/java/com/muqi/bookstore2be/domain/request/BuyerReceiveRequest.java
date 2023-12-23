package com.muqi.bookstore2be.domain.request;

import lombok.Data;

@Data
public class BuyerReceiveRequest {
    private String buyer_id;

    private String order_id;
}
