package com.muqi.bookstore2be.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.muqi.bookstore2be.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerAddBookRequest {
    private String user_id;

    private String store_id;

    @JsonProperty("book_info")
    private Book book_info;

    private int stock_level;

}
