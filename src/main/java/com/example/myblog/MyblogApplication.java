package com.example.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@MapperScan("com.example.myblog.mapper")
@SpringBootApplication
public class MyblogApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MyblogApplication.class, args);
    }

}
