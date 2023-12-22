package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.muqi.bookstore2be.domain.Book;
import com.muqi.bookstore2be.service.BookService;
import com.muqi.bookstore2be.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author mq
* @description 针对表【book】的数据库操作Service实现
* @createDate 2023-12-22 15:52:28
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
implements BookService{

}
