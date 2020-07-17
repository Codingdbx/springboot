package com.example.springbootautoweb.service;


import com.example.springbootautoweb.entity.PageSet;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/4 14:13
 * @since JDK1.8
 */
public interface PageSetService {
    PageSet selectByPrimaryKey(Object key);

    List<PageSet> selectAll();

}
