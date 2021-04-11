package com.example.springbootannotation.controller;

import com.example.springbootannotation.service.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/9/27 10:07
 * @since JDK1.8
 */
@RestController
public class TestController {

    // @Resource 指定名称
    @Resource(name ="DaoImpl0")
    private BaseDao daoImpl0;

    // @Qualifier指定bean 的id 或名称
    @Autowired
    @Qualifier("daoImpl1")
    private BaseDao daoImpl1;

    @RequestMapping("/test")
    public void test(){
        daoImpl0.out();
        daoImpl1.out();

    }
}
