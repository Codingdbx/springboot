package com.example.springbootautoweb.service;


import com.example.springbootautoweb.entity.DataSourceSet;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/5 17:01
 * @since JDK1.8
 */
public interface DataSourceSetService {
    List<DataSourceSet> selectAll();

    int insertAndGetPrimaryKey(DataSourceSet record);

    int insert(DataSourceSet record);

    int selectBySourceUrl(String sourceUrl);
}
