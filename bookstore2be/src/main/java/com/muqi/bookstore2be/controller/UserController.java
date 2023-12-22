package com.muqi.bookstore2be.controller;

import com.muqi.bookstore2be.domain.request.UserChangePwdRequest;
import com.muqi.bookstore2be.domain.request.UserLoginRequest;
import com.muqi.bookstore2be.domain.request.UserLogoutRequest;
import com.muqi.bookstore2be.domain.request.UserRegisterRequest;
import com.muqi.bookstore2be.domain.response.LoginResponse;
import com.muqi.bookstore2be.errorEnum.StatusCodeEnum;
import com.muqi.bookstore2be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muqi.bookstore2be.errorEnum.StatusCodeEnum.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        boolean result = userService.register(userRegisterRequest.getUser_id(), userRegisterRequest.getPassword());

        if (result) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(USER_EXIST.getCode()).body(USER_EXIST.getDesc() + userRegisterRequest.getUser_id());
        }
    }

    @PostMapping("/unregister")
    public ResponseEntity<String> userUnRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        boolean result = userService.unregister(userRegisterRequest.getUser_id(), userRegisterRequest.getPassword());
        if (result) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(USER_EXIST.getCode()).body(USER_EXIST.getDesc() + userRegisterRequest.getUser_id());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            String token = userService.login(userLoginRequest.getUser_id(), userLoginRequest.getPassword(), userLoginRequest.getTerminal());

            if (token != null) {
                LoginResponse loginResponse = new LoginResponse(200, token);
                return ResponseEntity.ok(loginResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(HttpStatus.UNAUTHORIZED.value(), "User login error"));
            }
        } catch (Exception e) {
            // Handle exceptions if necessary
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> userLogOut(@RequestHeader("token") String token, @RequestBody UserLogoutRequest userLogoutRequest) {
        boolean result = userService.logout(userLogoutRequest.getUser_id(), token);
        if (result) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(USER_AUTHORIZATION_ERROR.getCode()).body(USER_AUTHORIZATION_ERROR.getDesc());
        }
    }

    @PostMapping("/password")
    public ResponseEntity<String> changePwd(@RequestBody UserChangePwdRequest userChangePwdRequest) {
        StatusCodeEnum result = userService.changePwd(userChangePwdRequest.getUser_id(), userChangePwdRequest.getOldPassword(), userChangePwdRequest.getNewPassword());
        return ResponseEntity.status(result.getCode()).body(result.getDesc());
    }
}
