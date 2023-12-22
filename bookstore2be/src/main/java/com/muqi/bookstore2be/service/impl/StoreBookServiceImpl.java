package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.bookstore2be.domain.StoreBook;
import com.muqi.bookstore2be.service.StoreBookService;
import com.muqi.bookstore2be.mapper.StoreBookMapper;
import org.springframework.stereotype.Service;

/**
* @author mq
* @description 针对表【store_book】的数据库操作Service实现
* @createDate 2023-12-22 17:38:20
*/
@Service
public class StoreBookServiceImpl extends ServiceImpl<StoreBookMapper, StoreBook>
implements StoreBookService{

}
