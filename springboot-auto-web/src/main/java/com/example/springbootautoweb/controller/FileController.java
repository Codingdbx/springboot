package com.example.springbootautoweb.controller;
import com.example.springbootautoweb.entity.DataSet;
import com.example.springbootautoweb.entity.DataSourceSet;
import com.example.springbootautoweb.enums.DataSourceType;
import com.example.springbootautoweb.processor.Processor;
import com.example.springbootautoweb.processor.ProcessorFactory;
import com.example.springbootautoweb.service.DataSetService;
import com.example.springbootautoweb.service.DataSourceSetService;
import com.example.springbootautoweb.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/10 10:30
 * @since JDK1.8
 */
@RestController
public class FileController {

    private final DataSourceSetService dataSourceSetService;
    private final DataSetService dataSetService;

    @Autowired
    public FileController(DataSourceSetService dataSourceSetService, DataSetService dataSetService) {
        this.dataSourceSetService = dataSourceSetService;
        this.dataSetService = dataSetService;
    }

    @RequestMapping("/file/fileUpLoad")
    public String fileUpLoad(@RequestParam("upload") MultipartFile[] files) {

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extString = "";
            if (originalFilename != null && originalFilename.length() > 0) {
                extString = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            DataSourceType sourceType = DataSourceType.instance(extString);

            if (sourceType == null) {
                return "this file is not in the required format.";
            }

            int select = dataSourceSetService.selectBySourceUrl(originalFilename);

            if (select > 0) {
                return "this file has already import.";
            }

            Processor processor = ProcessorFactory.getProcessor(sourceType);

            try (InputStream is = file.getInputStream()) {
                DataSourceSet record = new DataSourceSet();
                record.setSourceId(StringUtils.UUID());
                record.setSourceUrl(originalFilename);
                record.setSourceType(extString);

                int insert = dataSourceSetService.insert(record);

                if (insert > 0) {

                    List<DataSet> dataSetList = processor.getDataSetListByStream(is);

                    for (DataSet dataSet : dataSetList) {
                        dataSet.setSourceId(record.getSourceId());
                        dataSetService.insert(dataSet);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "upload failure.";
            }

        }

        return "upload success.";
    }
}
