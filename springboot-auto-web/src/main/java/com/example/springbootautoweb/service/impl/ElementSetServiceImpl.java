package com.example.springbootautoweb.service.impl;


import com.example.springbootautoweb.dao.ElementSetMapper;
import com.example.springbootautoweb.entity.ElementSet;
import com.example.springbootautoweb.service.ElementSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/9 11:32
 * @since JDK1.8
 */
@Service
public class ElementSetServiceImpl implements ElementSetService {

    private final ElementSetMapper elementSetMapper;

    @Autowired
    public ElementSetServiceImpl(ElementSetMapper elementSetMapper) {
        this.elementSetMapper = elementSetMapper;
    }

    @Override
    public List<ElementSet> selectListByPageId(String pageId) {
        return elementSetMapper.selectListByPageId(pageId);
    }


}
