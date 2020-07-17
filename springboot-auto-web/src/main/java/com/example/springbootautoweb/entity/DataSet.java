package com.example.springbootautoweb.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "DataSet")
public class DataSet implements Serializable {
    @Id
    @Column(name = "DATA_ID")
    private String dataId;

    @Column(name = "SOURCE_ID")
    private String sourceId;

    @Column(name = "DATA_VALUE")
    private String dataValue;

    @Column(name = "EXCEL_SHEET_NUM")
    private String excelSheetNum;

    @Column(name = "EXCEL_SHEET_NAME")
    private String excelSheetName;

    @Column(name = "EXCEL_ROW")
    private String excelRow;

    @Column(name = "EXCEL_CELL")
    private String excelCell;

    @Column(name = "WORD_TABLE_NUM")
    private String wordTableNum;

    @Column(name = "WORD_TABLE_ROW")
    private String wordTableRow;

    @Column(name = "WORD_TABLE_CELL")
    private String wordTableCell;

    @Column(name = "WORD_PARAGRAPH_NUM")
    private String wordParagraphNum;

    @Column(name = "WORD_RUN_NUM")
    private String wordRunNum;

    @Column(name = "WORD_BOOK_MARK")
    private String wordBookMark;

    private static final long serialVersionUID = 1L;

    /**
     * @return DATA_ID
     */
    public String getDataId() {
        return dataId;
    }

    /**
     * @param dataId
     */
    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    /**
     * @return SOURCE_ID
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    /**
     * @return DATA_VALUE
     */
    public String getDataValue() {
        return dataValue;
    }

    /**
     * @param dataValue
     */
    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }

    /**
     * @return EXCEL_SHEET_NUM
     */
    public String getExcelSheetNum() {
        return excelSheetNum;
    }

    /**
     * @param excelSheetNum
     */
    public void setExcelSheetNum(String excelSheetNum) {
        this.excelSheetNum = excelSheetNum == null ? null : excelSheetNum.trim();
    }

    /**
     * @return EXCEL_SHEET_NAME
     */
    public String getExcelSheetName() {
        return excelSheetName;
    }

    /**
     * @param excelSheetName
     */
    public void setExcelSheetName(String excelSheetName) {
        this.excelSheetName = excelSheetName == null ? null : excelSheetName.trim();
    }

    /**
     * @return EXCEL_ROW
     */
    public String getExcelRow() {
        return excelRow;
    }

    /**
     * @param excelRow
     */
    public void setExcelRow(String excelRow) {
        this.excelRow = excelRow == null ? null : excelRow.trim();
    }

    /**
     * @return EXCEL_CELL
     */
    public String getExcelCell() {
        return excelCell;
    }

    /**
     * @param excelCell
     */
    public void setExcelCell(String excelCell) {
        this.excelCell = excelCell == null ? null : excelCell.trim();
    }

    /**
     * @return WORD_TABLE_NUM
     */
    public String getWordTableNum() {
        return wordTableNum;
    }

    /**
     * @param wordTableNum
     */
    public void setWordTableNum(String wordTableNum) {
        this.wordTableNum = wordTableNum == null ? null : wordTableNum.trim();
    }

    /**
     * @return WORD_TABLE_ROW
     */
    public String getWordTableRow() {
        return wordTableRow;
    }

    /**
     * @param wordTableRow
     */
    public void setWordTableRow(String wordTableRow) {
        this.wordTableRow = wordTableRow == null ? null : wordTableRow.trim();
    }

    /**
     * @return WORD_TABLE_CELL
     */
    public String getWordTableCell() {
        return wordTableCell;
    }

    /**
     * @param wordTableCell
     */
    public void setWordTableCell(String wordTableCell) {
        this.wordTableCell = wordTableCell == null ? null : wordTableCell.trim();
    }

    /**
     * @return WORD_PARAGRAPH_NUM
     */
    public String getWordParagraphNum() {
        return wordParagraphNum;
    }

    /**
     * @param wordParagraphNum
     */
    public void setWordParagraphNum(String wordParagraphNum) {
        this.wordParagraphNum = wordParagraphNum == null ? null : wordParagraphNum.trim();
    }

    /**
     * @return WORD_RUN_NUM
     */
    public String getWordRunNum() {
        return wordRunNum;
    }

    /**
     * @param wordRunNum
     */
    public void setWordRunNum(String wordRunNum) {
        this.wordRunNum = wordRunNum == null ? null : wordRunNum.trim();
    }

    /**
     * @return WORD_BOOK_MARK
     */
    public String getWordBookMark() {
        return wordBookMark;
    }

    /**
     * @param wordBookMark
     */
    public void setWordBookMark(String wordBookMark) {
        this.wordBookMark = wordBookMark == null ? null : wordBookMark.trim();
    }
}