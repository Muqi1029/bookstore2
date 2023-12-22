package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.bookstore2be.domain.Store;
import com.muqi.bookstore2be.service.StoreService;
import com.muqi.bookstore2be.mapper.StoreMapper;
import org.springframework.stereotype.Service;

/**
* @author mq
* @description 针对表【store】的数据库操作Service实现
* @createDate 2023-12-22 14:35:05
*/
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store>
implements StoreService{

}
