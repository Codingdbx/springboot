package com.example.springbootautoweb.service;


import com.example.springbootautoweb.entity.ElementSet;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/9 11:32
 * @since JDK1.8
 */
public interface ElementSetService {

    List<ElementSet> selectListByPageId(String pageId);
}
