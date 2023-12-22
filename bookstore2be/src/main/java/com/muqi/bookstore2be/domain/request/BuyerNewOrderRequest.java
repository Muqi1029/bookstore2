package com.muqi.bookstore2be.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerNewOrderRequest {
    private String user_id;
    private String order_id;

    private List<Book> books;
}
