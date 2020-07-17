package com.example.springbootarbitrage.service.impl;


import com.example.springbootarbitrage.dao.FundMapper;
import com.example.springbootarbitrage.entity.Fund;
import com.example.springbootarbitrage.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/3 9:35
 * @since JDK1.8
 */
@Service
public class FundServiceImpl implements FundService {

    private FundMapper fundMapper;

    @Autowired
    private FundServiceImpl(FundMapper fundMapper){
        this.fundMapper = fundMapper;
    }

    @Override
    public List<Fund> selectAll (){
        return fundMapper.selectAll();
    }
}
