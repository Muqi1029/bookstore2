package com.muqi.bookstore2be;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.muqi.bookstore2be.mapper")
public class Bookstore2beApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bookstore2beApplication.class, args);
    }

}
