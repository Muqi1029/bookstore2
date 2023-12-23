package com.muqi.bookstore2be.domain.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muqi.bookstore2be.domain.StoreBook;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import lombok.Data;

@Data
public class SearchResponse {
    StatusCodeEnum statusCodeEnum;
    Page<StoreBook> bookPage;
}
