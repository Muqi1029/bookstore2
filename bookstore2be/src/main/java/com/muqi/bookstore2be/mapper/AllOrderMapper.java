package com.muqi.bookstore2be.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muqi.bookstore2be.domain.AllOrder;
import org.springframework.stereotype.Repository;

/**
* @author mq
* @description 针对表【all_order】的数据库操作Mapper
* @createDate 2023-12-23 22:25:14
* @Entity com.muqi.bookstore2be.domain.AllOrder
*/
@Repository
public interface AllOrderMapper extends BaseMapper<AllOrder> {


}
