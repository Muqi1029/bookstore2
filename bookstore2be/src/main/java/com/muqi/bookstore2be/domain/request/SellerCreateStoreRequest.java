package com.muqi.bookstore2be.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerCreateStoreRequest {
    private String user_id;

    private String store_id;
}
