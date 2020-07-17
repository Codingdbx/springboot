package com.example.springbootautoweb.dao;

import com.example.springbootautoweb.entity.DataSourceSet;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

public interface DataSourceSetMapper extends Mapper<DataSourceSet> {

    @Insert("insert into DataSourceSet(SOURCE_ID,SOURCE_URL,SOURCE_TYPE) values(#{record.sourceId}, #{record.sourceUrl}, #{record.sourceType})")
    @Options(useGeneratedKeys = true, keyProperty = "record.sourceId")
    int insertAndGetPrimaryKey(@Param("record") DataSourceSet record);

    @Select("select count(*) from DataSourceSet where source_url = #{sourceUrl}")
    int selectBySourceUrl(@Param("sourceUrl") String sourceUrl);
}