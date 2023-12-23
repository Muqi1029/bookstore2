package com.muqi.bookstore2be.domain.response;

import com.muqi.bookstore2be.domain.AllOrder;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOrderResponse {
    StatusCodeEnum statusCodeEnum;
    List<AllOrder> cancel_order;
    List<AllOrder> not_paid_order;
    List<AllOrder> paid_order;
    List<AllOrder> sent_order;
    List<AllOrder> received_order;

}
