package com.muqi.bookstore2be.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerAddStockLevelRequest {
    private String user_id;
    private String store_id;
    private String book_id;
    private int add_stock_level;
}
