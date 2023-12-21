package com.muqi.bookstore2be.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muqi.bookstore2be.domain.User;
import org.springframework.stereotype.Service;

/**
* @author mq
* @description 针对表【user】的数据库操作Service
* @createDate 2023-12-21 21:27:39
*/
public interface UserService extends IService<User> {

    boolean register(String userId, String password);

    boolean unregister(String userId, String password);

    String login(String userId, String password, String terminal);

    boolean logout(String userId, String token);
}
