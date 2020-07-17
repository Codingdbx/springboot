package com.example.springbootautoweb.processor;

import com.example.springbootautoweb.entity.DataSet;
import com.example.springbootautoweb.util.WordUtil;
import org.springframework.beans.factory.InitializingBean;

import java.io.InputStream;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/12 9:50
 * @since JDK1.8
 */
public class WordDocProcessor implements Processor, InitializingBean {

    @Override
    public List<DataSet> getDataSetListByUrl(String dataSourceUrl) throws Exception {
        return WordUtil.readDocData(dataSourceUrl);
    }

    @Override
    public List<DataSet> getDataSetListByStream(InputStream is) throws Exception {
        return WordUtil.readDocData(is);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ProcessorFactory2.register(null,this);
    }
}
