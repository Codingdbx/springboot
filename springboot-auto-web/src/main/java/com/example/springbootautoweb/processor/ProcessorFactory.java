package com.example.springbootautoweb.processor;

import com.example.springbootautoweb.enums.DataSourceType;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * <p>Description: 简单工厂模式</p>
 *
 * @author dbx
 * @date 2020/3/12 9:39
 * @since JDK1.8
 */
public class ProcessorFactory {

    private static final Map<DataSourceType, Processor> PROCESSOR_MAP = new ImmutableMap.Builder<DataSourceType, Processor>()
            .put(DataSourceType.DOC, new WordDocProcessor())
            .put(DataSourceType.XLS, new ExcelXlsProcessor())
            .put(DataSourceType.DOCX, new WordDocXProcessor())
            .put(DataSourceType.XLSX, new ExcelXlsXProcessor())
            .build();

    public static Processor getProcessor(DataSourceType type){
        return PROCESSOR_MAP.get(type);
    }
}
