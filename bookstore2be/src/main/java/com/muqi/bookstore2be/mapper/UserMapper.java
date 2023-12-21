package com.muqi.bookstore2be.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muqi.bookstore2be.domain.User;
import org.springframework.stereotype.Repository;

/**
* @author mq
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-12-21 21:27:39
* @Entity com.muqi.bookstore2be.domain.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {


}
