package com.muqi.bookstore2be.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.bookstore2be.domain.User;
import com.muqi.bookstore2be.mapper.UserMapper;
import com.muqi.bookstore2be.service.UserService;
import com.muqi.bookstore2be.utils.TokenUtil;
import org.apache.el.parser.Token;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author mq
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-12-21 21:27:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public boolean register(String userId, String password) {
        String terminal = "terminal_" + Instant.now().toEpochMilli();
        String token = TokenUtil.generateToken1(userId, terminal);
        User user = new User(userId, password, 0, token, terminal);
        try {
            return this.save(user);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean unregister(String userId, String password) {
        User user = this.getById(userId);
        if (user == null || !user.getPassword().equals(password)) {
            // TODO: 自定义异常类
            return false;
        }
        return this.removeById(userId);
    }

    @Override
    public String login(String userId, String password, String terminal) {
        User user = this.getById(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        String token = TokenUtil.generateToken1(userId, terminal);
        user.setTerminal(terminal);
        user.setToken(token);
        if (this.saveOrUpdate(user)) {
            return token;
        }
        return null;
    }

    @Override
    public boolean logout(String userId, String token) {
        User user = this.getById(userId);
        if (user == null || !user.getToken().equals(token)) {
            return false;
        }
        String terminal = "terminal_" + Instant.now().toEpochMilli();
        String new_token = TokenUtil.generateToken1(userId, terminal);
        user.setToken(new_token);
        user.setTerminal(terminal);
        return this.saveOrUpdate(user);
    }
}
