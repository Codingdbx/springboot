package com.example.springbootautoweb.processor;


import com.example.springbootautoweb.entity.DataSet;
import com.example.springbootautoweb.util.ExcelUtil;

import java.io.InputStream;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/12 10:01
 * @since JDK1.8
 */
public class ExcelXlsXProcessor implements Processor {
    @Override
    public List<DataSet> getDataSetListByUrl(String dataSourceUrl) throws Exception {
        return ExcelUtil.readXlsXData(dataSourceUrl);
    }

    @Override
    public List<DataSet> getDataSetListByStream(InputStream is) throws Exception {
        return ExcelUtil.readXlsXData(is);
    }
}
