package com.example.springbootautoweb.service.impl;


import com.example.springbootautoweb.dao.IframeSetMapper;
import com.example.springbootautoweb.entity.IframeSet;
import com.example.springbootautoweb.service.IframeSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/9 13:33
 * @since JDK1.8
 */
@Service
public class IframeSetServiceImpl implements IframeSetService {

    private final IframeSetMapper iframeSetMapper;

    @Autowired
    public IframeSetServiceImpl(IframeSetMapper iframeSetMapper){
        this.iframeSetMapper = iframeSetMapper;
    }

    @Override
    public IframeSet selectByPrimaryKey(String iframeId){
       return iframeSetMapper.selectByPrimaryKey(iframeId);
    }
}
