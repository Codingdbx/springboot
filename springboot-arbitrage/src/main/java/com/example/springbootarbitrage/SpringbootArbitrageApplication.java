package com.example.springbootarbitrage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.springbootarbitrage.dao")
public class SpringbootArbitrageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootArbitrageApplication.class, args);
    }

}
