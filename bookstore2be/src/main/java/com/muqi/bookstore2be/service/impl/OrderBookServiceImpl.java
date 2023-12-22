package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.bookstore2be.domain.OrderBook;
import com.muqi.bookstore2be.service.OrderBookService;
import com.muqi.bookstore2be.mapper.OrderBookMapper;
import org.springframework.stereotype.Service;

/**
* @author mq
* @description 针对表【order_book】的数据库操作Service实现
* @createDate 2023-12-22 19:54:47
*/
@Service
public class OrderBookServiceImpl extends ServiceImpl<OrderBookMapper, OrderBook>
implements OrderBookService{

}
