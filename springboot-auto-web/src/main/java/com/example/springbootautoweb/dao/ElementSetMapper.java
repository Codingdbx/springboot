package com.example.springbootautoweb.dao;

import com.example.springbootautoweb.entity.ElementSet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ElementSetMapper extends Mapper<ElementSet> {

    @Select("select * from ElementSet where page_id = #{page_id}")
//    @Results(id = "elementSetResultMap", value = {
//            @Result(column = "ELEMENT_ID", property = "elementId", jdbcType = JdbcType.VARCHAR, id = true),
//            @Result(column = "ELEMENT_NAME", property = "elementName", jdbcType = JdbcType.VARCHAR),
//            @Result(column = "ELEMENT_TYPE ", property = "elementType", jdbcType = JdbcType.VARCHAR)
//    })
    @ResultMap(value="BaseResultMap")
    List<ElementSet> selectListByPageId(@Param("page_id") String pageId);
}