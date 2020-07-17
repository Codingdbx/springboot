package com.example.springbootautoweb.enums;

/**
 * <p>Description: 数据源类型</p>
 *
 * @author dbx
 * @date 2020/3/5 11:29
 * @since JDK1.8
 */
public enum DataSourceType {

    DOC(".doc"),DOCX(".docx"),XLS(".xls"),XLSX(".xlsx");

    private String type;

    DataSourceType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public static DataSourceType instance(String type){
        for (DataSourceType entity : DataSourceType.values()) {
            if (entity.getType().equals(type)) {
                return entity;
            }
        }
        return null;
    }

}
