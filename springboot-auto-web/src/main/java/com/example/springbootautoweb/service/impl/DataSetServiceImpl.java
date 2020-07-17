package com.example.springbootautoweb.service.impl;


import com.example.springbootautoweb.dao.DataSetMapper;
import com.example.springbootautoweb.entity.DataSet;
import com.example.springbootautoweb.entity.DataSourceSet;
import com.example.springbootautoweb.enums.DataSourceType;
import com.example.springbootautoweb.exception.ServiceException;
import com.example.springbootautoweb.processor.Processor;
import com.example.springbootautoweb.processor.ProcessorFactory;
import com.example.springbootautoweb.service.DataSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/5 14:16
 * @since JDK1.8
 */
@Service
public class DataSetServiceImpl implements DataSetService {

    private final Logger logger = LoggerFactory.getLogger(DataSetServiceImpl.class);

    private final DataSetMapper dataSetMapper;


    @Autowired
    public DataSetServiceImpl(DataSetMapper dataSetMapper) {
        this.dataSetMapper = dataSetMapper;
    }

    /**
     * 批量插入 数据信息
     * <p>
     * Transactional 事务级别默认为READ_COMMITTED。使用事务必须抛出异常，且默认RuntimeException级别才回滚
     *
     * @param dataSourceSet
     * @return
     */
    @Override
    @Transactional
    public int batchInsertByDataSourceSet(DataSourceSet dataSourceSet) {
        List<DataSet> dataSetList = null;
        DataSourceType sourceType = DataSourceType.instance(dataSourceSet.getSourceType());
        Processor processor = ProcessorFactory.getProcessor(sourceType);
        int insert = 0;//默认插入失败
        try {
            dataSetList = processor.getDataSetListByUrl(dataSourceSet.getSourceUrl());
            for (DataSet dataSet : dataSetList) {
                dataSet.setSourceId(dataSourceSet.getSourceId());
                insert = dataSetMapper.insert(dataSet);

                if (insert <= 0) {
                    return insert;
                }
            }
            logger.info(String.format("insert success.the sourceId is %s,the sourceUrl is %s",dataSourceSet.getSourceId(),dataSourceSet.getSourceUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new ServiceException(String.format("batchInsertByDataSourceSet occur error.the exception is %s", e.getMessage()));
        }

        return insert;
    }

    @Override
    public List<DataSet> selectAll() {
        return dataSetMapper.selectAll();
    }

    @Override
    public int selectCountBySourceId(String source_id){
        return dataSetMapper.selectCountBySourceId(source_id);
    }

    @Override
    public DataSet selectByPrimaryKey(String dataId){
        return dataSetMapper.selectByPrimaryKey(dataId);
    }

    public int insert(DataSet record){
        return dataSetMapper.insert(record);
    }
}
