package com.muqi.bookstore2be.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private static final String SECRET_KEY = "Muqi"; // 请替换为自己的密钥
    private static final long EXPIRATION_TIME = 86400000; // 有效期为1天

    public static String generateToken1(String user_id, String terminal){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject("muqi")
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("user_id", user_id)
                .claim("terminal", terminal)
                .claim("timestamp", now.toString())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String generateToken(String userId, String terminal) {
        //准备一个空荷载claims，用于存储生成的key和value键值对（下面是存储生成token的时间和用户名）
        Map<String, Object> claims = new HashMap<>();
        claims.put("user id", userId);
        claims.put("terminal", terminal);
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setClaims(claims)//把荷载存储到里面
                .setExpiration(expiryDate)//设置失效时间
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) //签名
                .compact();
    }

}
