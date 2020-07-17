package com.example.springbootautoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.springbootautoweb.dao")
public class SpringbootAutoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAutoWebApplication.class, args);
	}

}
