package com.example.springbootmybatisplus.entity;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/15 18:45
 * @since JDK1.8
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

