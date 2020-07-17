package com.example.springbootautoweb.service;


import com.example.springbootautoweb.entity.IframeSet;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/9 13:33
 * @since JDK1.8
 */
public interface IframeSetService {
    IframeSet selectByPrimaryKey(String iframeId);
}
