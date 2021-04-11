package com.example.springbootannotation.service;

import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/9/27 10:05
 * @since JDK1.8
 */

@Service
public class DaoImpl1  implements BaseDao {

    @Override
    public void out(){
        System.out.println("it's DaoImpl1");
    }
}
