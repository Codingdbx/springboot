package com.example.springbootautoweb.processor;

import com.example.springbootautoweb.enums.DataSourceType;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/12 11:18
 * @since JDK1.8
 */
public class ProcessorFactory2 {

    private static final Map<DataSourceType, Processor> PROCESSOR_MAP = new ConcurrentHashMap<>();

    public static Processor getProcessor(DataSourceType type){
        return PROCESSOR_MAP.get(type);
    }

    public static void register(DataSourceType type,Processor processor){
        Assert.notNull(processor, "processor can not be null.");
        PROCESSOR_MAP.put(type, processor);
    }

}
