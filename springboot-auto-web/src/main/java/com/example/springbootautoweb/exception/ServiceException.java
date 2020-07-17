package com.example.springbootautoweb.exception;

/**
 * <p>Description: 插入数据异常类</p>
 *
 * @author dbx
 * @date 2020/3/5 17:24
 * @since JDK1.8
 */
public class ServiceException extends RuntimeException{


    public ServiceException(String message){
        super(message);
    }
}
