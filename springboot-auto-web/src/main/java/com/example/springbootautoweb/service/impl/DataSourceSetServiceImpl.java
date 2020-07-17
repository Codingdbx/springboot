package com.example.springbootautoweb.service.impl;


import com.example.springbootautoweb.dao.DataSourceSetMapper;
import com.example.springbootautoweb.entity.DataSourceSet;
import com.example.springbootautoweb.service.DataSourceSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/5 17:02
 * @since JDK1.8
 */
@Service
public class DataSourceSetServiceImpl implements DataSourceSetService {

    private final DataSourceSetMapper sourceSetMapper;

    @Autowired
    public DataSourceSetServiceImpl(DataSourceSetMapper sourceSetMapper){
        this.sourceSetMapper = sourceSetMapper;
    }

    @Override
    public List<DataSourceSet> selectAll(){
        return sourceSetMapper.selectAll();
    }

    @Override
    public int insertAndGetPrimaryKey(DataSourceSet record){
        return sourceSetMapper.insertAndGetPrimaryKey(record);
    }

    @Override
    public int insert(DataSourceSet record){
        return sourceSetMapper.insert(record);
    }

    @Override
    public int selectBySourceUrl(String sourceUrl){
        return sourceSetMapper.selectBySourceUrl(sourceUrl);
    }
}
