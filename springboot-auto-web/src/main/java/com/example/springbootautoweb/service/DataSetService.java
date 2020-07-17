package com.example.springbootautoweb.service;


import com.example.springbootautoweb.entity.DataSet;
import com.example.springbootautoweb.entity.DataSourceSet;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/5 14:13
 * @since JDK1.8
 */
public interface DataSetService {

    int batchInsertByDataSourceSet(DataSourceSet dataSourceSet);

    List<DataSet> selectAll();

    int selectCountBySourceId(String source_id);

    DataSet selectByPrimaryKey(String dataId);

    int insert(DataSet record);

}
