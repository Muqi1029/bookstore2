package com.muqi.bookstore2be.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muqi.bookstore2be.domain.AllOrder;

import java.util.List;

/**
* @author mq
* @description 针对表【all_order】的数据库操作Service
* @createDate 2023-12-22 19:42:18
*/
public interface AllOrderService extends IService<AllOrder> {
    List<AllOrder> getUnpaidOrders();

    void cancelOrder(String order_id);

//    StatusCodeEnum cancelOrder(String buyerId, String sellerId, String orderId);
}
