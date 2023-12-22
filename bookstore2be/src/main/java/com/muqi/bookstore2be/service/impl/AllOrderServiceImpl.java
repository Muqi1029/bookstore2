package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.bookstore2be.domain.AllOrder;
import com.muqi.bookstore2be.service.AllOrderService;
import com.muqi.bookstore2be.mapper.AllOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author mq
* @description 针对表【all_order】的数据库操作Service实现
* @createDate 2023-12-22 19:42:18
*/
@Service
public class AllOrderServiceImpl extends ServiceImpl<AllOrderMapper, AllOrder>
implements AllOrderService{

}
