package com.muqi.bookstore2be.domain.request;

import lombok.Data;

@Data
public class BuyerSearchRequest {
    private String keyword;
    private String store_id;

    private int page;

    private int size;
}
