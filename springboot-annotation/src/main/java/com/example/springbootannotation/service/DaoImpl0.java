package com.example.springbootannotation.service;

import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/9/27 10:05
 * @since JDK1.8
 */
@Service("DaoImpl0")
public class DaoImpl0 implements BaseDao {

    @Override
    public void out(){
        System.out.println("it's DaoImpl0");
    }
}
