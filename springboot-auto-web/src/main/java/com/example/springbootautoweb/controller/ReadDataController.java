package com.example.springbootautoweb.controller;

import com.alibaba.fastjson.JSONObject;


import com.example.springbootautoweb.entity.DataSet;
import com.example.springbootautoweb.entity.DataSourceSet;
import com.example.springbootautoweb.enums.DataSourceType;
import com.example.springbootautoweb.processor.Processor;
import com.example.springbootautoweb.processor.ProcessorFactory;
import com.example.springbootautoweb.service.DataSetService;
import com.example.springbootautoweb.service.DataSourceSetService;
import com.example.springbootautoweb.service.PageSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/3 16:58
 * @since JDK1.8
 */
@RestController
public class ReadDataController {

    private final PageSetService pageSetService;
    private final DataSourceSetService dataSourceSetService;
    private final DataSetService dataSetService;

    /**
     * 构造方法初始化 先于 @Autowired ，建议这种写法
     *
     * @param pageSetService pageSetService
     */
    @Autowired
    public ReadDataController(PageSetService pageSetService, DataSourceSetService dataSourceSetService,
                              DataSetService dataSetService) {
        this.pageSetService = pageSetService;
        this.dataSourceSetService = dataSourceSetService;
        this.dataSetService = dataSetService;

    }

    @RequestMapping("/test")
    public String test() {
        return JSONObject.toJSONString(pageSetService.selectByPrimaryKey("1"));
    }

    @RequestMapping("/getData")
    public String getData() {
        final String dataSourceUrl = "C:\\Users\\Administrator\\Desktop\\开票机器人测试笔记.xlsx";
        String extString = dataSourceUrl.substring(dataSourceUrl.lastIndexOf("."));

        List<DataSet> dataSetList = null;
        DataSourceType sourceType = DataSourceType.instance(extString);
        Processor processor = ProcessorFactory.getProcessor(sourceType);
        try {
            dataSetList = processor.getDataSetListByUrl(dataSourceUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(dataSetList);
    }

    @RequestMapping("/insert")
    public String insert(){
        List<DataSourceSet> dataSourceSetList = dataSourceSetService.selectAll();

        int insert = 0;
        for (DataSourceSet dataSourceSet:dataSourceSetList) {
            int count = dataSetService.selectCountBySourceId(dataSourceSet.getSourceId());

            if (count > 0) {
                return "该数据表已经导入过";
            } else {
                insert = dataSetService.batchInsertByDataSourceSet(dataSourceSet);
            }

        }

        return insert+"";
    }
}
