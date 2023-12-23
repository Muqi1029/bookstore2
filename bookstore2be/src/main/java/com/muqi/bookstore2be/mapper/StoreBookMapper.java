package com.muqi.bookstore2be.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muqi.bookstore2be.domain.StoreBook;
import org.springframework.stereotype.Repository;

/**
* @author mq
* @description 针对表【store_book】的数据库操作Mapper
* @createDate 2023-12-23 16:27:06
* @Entity com.muqi.bookstore2be.domain.StoreBook
*/
@Repository
public interface StoreBookMapper extends BaseMapper<StoreBook> {


}
