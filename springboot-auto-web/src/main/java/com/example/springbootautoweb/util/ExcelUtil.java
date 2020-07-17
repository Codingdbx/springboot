package com.example.springbootautoweb.util;

import com.example.springbootautoweb.entity.DataSet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/5 11:23
 * @since JDK1.8
 */
public final class ExcelUtil {

    /**
     * 从 xlsx 文件中读取数据信息
     *
     * @param dataSourceUrl
     * @return
     * @throws Exception
     */
    public static List<DataSet> readXlsXData(String dataSourceUrl) throws Exception {
        try (InputStream is = new FileInputStream(dataSourceUrl)) {
            Workbook wb = new XSSFWorkbook(is);
            return getDataCell(wb);
        }
    }

    public static List<DataSet> readXlsXData(InputStream is) throws Exception {
        Workbook wb = new XSSFWorkbook(is);
        return getDataCell(wb);
    }

    /**
     * 从 xls文件中读取数据
     *
     * @param dataSourceUrl
     * @return
     * @throws Exception
     */
    public static List<DataSet> readXlsData(String dataSourceUrl) throws Exception {
        try (InputStream is = new FileInputStream(dataSourceUrl)) {
            Workbook wb = new HSSFWorkbook(is);
            return getDataCell(wb);
        }
    }

    public static List<DataSet> readXlsData(InputStream is) throws Exception {
        Workbook wb = new HSSFWorkbook(is);
        return getDataCell(wb);
    }

    private static List<DataSet> getDataCell(Workbook wb) {
        List<DataSet> dataSetList = new LinkedList<>();
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            // 获取第i个sheet
            Sheet sheet = wb.getSheetAt(i);
            String sheetName = sheet.getSheetName();

            Row row;
            Cell cell;
            String cellData = "";
            DataFormatter formatter = new DataFormatter();

            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                    cell = row.getCell(k);
                    DataSet dataSet = new DataSet();

                    if (cell == null) {
                        continue;
                    }

                    // 判断cell类型
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            cellData = formatter.formatCellValue(cell);
//                            cellData = String.valueOf(cell.getNumericCellValue());
                            break;
                        case STRING:
                            cellData = cell.getRichStringCellValue().getString();
                            break;
                        default:
                            cellData = "";
                    }

                    //内容为空不读取数据
                    if (cellData.length() > 0) {
                        dataSet.setDataId(StringUtils.UUID());
                        dataSet.setExcelSheetNum(i + "");
                        dataSet.setExcelSheetName(sheetName);
                        dataSet.setExcelRow(j + "");
                        dataSet.setExcelCell(k + "");
                        dataSet.setDataValue(cellData);
                        dataSetList.add(dataSet);
                    }

                }
            }
        }
        return dataSetList;
    }
}
