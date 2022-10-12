package com.example.sdemo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.sdemo1.dao")
public class Sdemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Sdemo1Application.class, args);
    }

}
