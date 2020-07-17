package com.example.springbootautoweb.processor;



import com.example.springbootautoweb.entity.DataSet;
import com.example.springbootautoweb.util.WordUtil;

import java.io.InputStream;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/12 9:59
 * @since JDK1.8
 */
public class WordDocXProcessor implements Processor {
    @Override
    public List<DataSet> getDataSetListByUrl(String dataSourceUrl) throws Exception {
        return WordUtil.readDocXData(dataSourceUrl);
    }

    @Override
    public List<DataSet> getDataSetListByStream(InputStream is) throws Exception {
        return WordUtil.readDocXData(is);
    }
}
