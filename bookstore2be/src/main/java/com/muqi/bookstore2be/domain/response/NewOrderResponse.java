package com.muqi.bookstore2be.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderResponse {
    private int statusCode;

    private String order_id;

}
