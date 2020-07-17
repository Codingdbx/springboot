package com.example.springbootautoweb.dao;

import com.example.springbootautoweb.entity.DataSet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface DataSetMapper extends Mapper<DataSet> {

    @Select("select count(1) from DataSet where source_id = #{source_id}")
    int selectCountBySourceId(@Param("source_id") String source_id);
}