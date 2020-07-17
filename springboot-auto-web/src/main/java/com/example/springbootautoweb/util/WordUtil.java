package com.example.springbootautoweb.util;

import com.example.springbootautoweb.entity.DataSet;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/5 11:22
 * @since JDK1.8
 */
public final class WordUtil {

    /**
     * 读取docx 文档数据
     *
     * @param dataSourceUrl
     * @return
     * @throws Exception
     */
    public static List<DataSet> readDocXData(String dataSourceUrl) throws Exception {
        try (InputStream is = new FileInputStream(dataSourceUrl)) {
            XWPFDocument doc = new XWPFDocument(is);
            return getDataSetList(doc);
        }
    }

    public static List<DataSet> readDocXData(InputStream is) throws Exception {
        XWPFDocument doc = new XWPFDocument(is);
        return getDataSetList(doc);
    }

    private static List<DataSet> getDataSetList(XWPFDocument doc) throws Exception {
        List<DataSet> dataSetList = new LinkedList<>();

        //获取文档中的所有段落
        List<XWPFParagraph> paras = doc.getParagraphs();

        String paraValue = "";
        for (int para = 0; para < paras.size(); para++) {
            paraValue = paras.get(para).getText();

            if (paraValue.length() > 0) {
                DataSet dataSet = new DataSet();
                dataSet.setDataId(StringUtils.UUID());
                dataSet.setWordParagraphNum(para + "");
                dataSet.setDataValue(paraValue);
                dataSetList.add(dataSet);
            }

            List<XWPFRun> runs = paras.get(para).getRuns();
            String runData = "";
            for (int run = 0; run < runs.size(); run++) {
                runData = runs.get(run).text();
                if (runData.length() > 0) {
                    DataSet dataSet = new DataSet();
                    dataSet.setDataId(StringUtils.UUID());
                    dataSet.setWordRunNum(run + "");
                    dataSet.setDataValue(runData);
                    dataSetList.add(dataSet);
                }
            }

            //获取文档中所有的表格
            List<XWPFTable> tables = doc.getTables();

            for (int i = 0; i < tables.size(); i++) {
                //获取所有行
                List<XWPFTableRow> rows = tables.get(i).getRows();
                for (int j = 0; j < rows.size(); j++) {
                    //获取当前行所有列
                    List<XWPFTableCell> cells = rows.get(j).getTableCells();
                    for (int k = 0; k < cells.size(); k++) {
                        String cellData = cells.get(k).getText();
                        if (cellData.length() > 0) {
                            DataSet dataSet = new DataSet();
                            dataSet.setDataId(StringUtils.UUID());
                            dataSet.setWordTableNum(i + "");
                            dataSet.setWordTableRow(j + "");
                            dataSet.setWordTableCell(k + "");
                            dataSet.setDataValue(cellData);
                            dataSetList.add(dataSet);
                        }
                    }
                }

            }
            doc.close();
        }

        return dataSetList;
    }

    /**
     * 读取 doc 文档数据
     *
     * @param dataSourceUrl
     * @return
     * @throws Exception
     */
    public static List<DataSet> readDocData(String dataSourceUrl) throws Exception {
        try (InputStream is = new FileInputStream(dataSourceUrl)) {
//            WordExtractor extractor = new WordExtractor(is);
//            //获取各个段落的文本
//            String[] paraTexts = extractor.getParagraphText();
//            for (int para = 0; para < paraTexts.length; para++) {
//                String paraValue = paraTexts[para];
//                if (paraValue.length() > 0) {
//                    DataSet dataSet = new DataSet();
//                    dataSet.setDataId(StringUtils.UUID());
//                    dataSet.setWordParagraphNum(para + "");
//                    dataSet.setDataValue(paraValue);
//                    dataSetList.add(dataSet);
//                }
//            }

            HWPFDocument doc = new HWPFDocument(is);
            return getDataSetList2(doc);
        }
    }

    public static List<DataSet> readDocData(InputStream is) throws Exception {
        HWPFDocument doc = new HWPFDocument(is);
        return getDataSetList2(doc);
    }

    private static  List<DataSet> getDataSetList2( HWPFDocument doc){
        List<DataSet> dataSetList = new LinkedList<>();

        //获取文档内容区域
        Range range = doc.getRange();

        for (int para = 0; para < range.numParagraphs(); para++) {
            String paraValue = range.getParagraph(para).text();
            if (paraValue.length() > 0) {
                DataSet dataSet = new DataSet();
                dataSet.setDataId(StringUtils.UUID());
                dataSet.setWordParagraphNum(para + "");
                dataSet.setDataValue(paraValue);
                dataSetList.add(dataSet);
            }
        }

        for (int run = 0; run < range.numCharacterRuns(); run++) {
            String runValue = range.getCharacterRun(run).text();
            if (runValue.length() > 0) {
                DataSet dataSet = new DataSet();
                dataSet.setDataId(StringUtils.UUID());
                dataSet.setWordRunNum(run + "");
                dataSet.setDataValue(runValue);
                dataSetList.add(dataSet);
            }
        }

        //遍历range范围内的table
        TableIterator tableIter = new TableIterator(range);
        Table table;
        TableRow row;
        TableCell cell;

        int i = 0;

        while (tableIter.hasNext()) {
            table = tableIter.next();
            for (int j = 0; j < table.numRows(); j++) {
                row = table.getRow(j);
                for (int k = 0; k < row.numCells(); k++) {
                    cell = row.getCell(k);
                    String cellData = cell.text();
                    if (cellData.length() > 0) {
                        DataSet dataSet = new DataSet();
                        dataSet.setDataId(StringUtils.UUID());
                        dataSet.setWordTableNum(i + "");
                        dataSet.setWordTableRow(j + "");
                        dataSet.setWordTableCell(k + "");
                        dataSet.setDataValue(cellData);
                        dataSetList.add(dataSet);
                    }
                }
            }
            i++;
        }
        return dataSetList;
    }

}
