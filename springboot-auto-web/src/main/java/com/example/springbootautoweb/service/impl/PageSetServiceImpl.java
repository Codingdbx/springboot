package com.example.springbootautoweb.service.impl;


import com.example.springbootautoweb.dao.PageSetMapper;
import com.example.springbootautoweb.entity.PageSet;
import com.example.springbootautoweb.service.PageSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageSetServiceImpl implements PageSetService {

    private final PageSetMapper pageSetMapper;

    @Autowired
    public PageSetServiceImpl(PageSetMapper pageSetMapper){
        this.pageSetMapper = pageSetMapper;
    }



    @Override
    public PageSet selectByPrimaryKey(Object key){

        return pageSetMapper.selectByPrimaryKey(key);
    }

    @Override
    public  List<PageSet> selectAll(){
        return pageSetMapper.selectAll();
    }
}
