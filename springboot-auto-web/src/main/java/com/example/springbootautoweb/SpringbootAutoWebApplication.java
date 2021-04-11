package com.example.springbootautoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.springbootautoweb.dao")
@EnableTransactionManagement  //PlatformTransactionManager，DataSourceTransactionManager
public class SpringbootAutoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAutoWebApplication.class, args);
	}

}
