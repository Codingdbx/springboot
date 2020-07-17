package com.example.springbootautoweb.processor;


import com.example.springbootautoweb.entity.DataSet;

import java.io.InputStream;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/12 9:49
 * @since JDK1.8
 */
public interface Processor {
    List<DataSet> getDataSetListByUrl(String dataSourceUrl) throws Exception;
    List<DataSet> getDataSetListByStream(InputStream is) throws Exception;
}
