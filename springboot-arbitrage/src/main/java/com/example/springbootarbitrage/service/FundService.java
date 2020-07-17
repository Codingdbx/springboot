package com.example.springbootarbitrage.service;

import com.example.springbootarbitrage.entity.Fund;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/3 9:34
 * @since JDK1.8
 */
public interface FundService {
    List<Fund> selectAll();
}
