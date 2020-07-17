package com.example.springbootjsqlparser.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;

import java.util.List;

/**
 * <p>Description: sql parse utils</p>
 *
 * @author dbx
 * @date 2020/5/13 17:59
 * @since JDK1.8
 */
public final class SQLParseUtils {

    /**
     * 获取select部分
     * @param sql parse sql
     * @return select items
     * @throws Exception e
     */
    public static String parseSelectBody(String sql) throws Exception {

        SelectBody selectBody = parseSqlToSelectBody(sql);

        if (selectBody instanceof PlainSelect) {
            PlainSelect plainSelect = (PlainSelect) selectBody;
            //select部分
            List<SelectItem> selectItems = plainSelect.getSelectItems();
            if (selectItems == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < selectItems.size(); i++) {
                String s = selectItems.get(i).toString();
                if (i == selectItems.size()-1) {
                    sb.append(s);
                } else {
                    sb.append(s).append(",");
                }
            }

            return sb.toString();

        } else if (selectBody instanceof WithItem) {
            //One of the parts of a "WITH" clause of a "SELECT" statement
            WithItem withItem = (WithItem) selectBody;
            // TODO: 2020/5/14

        } else if (selectBody instanceof SetOperationList) {
            SetOperationList operationList = (SetOperationList) selectBody;
            // TODO: 2020/5/14

        }


        return "";
    }

    /**
     * 获取From部分
     * @param sql parse sql
     * @return from body
     * @throws Exception e
     */
    public static String parseFromBody(String sql) throws Exception {

        SelectBody selectBody = parseSqlToSelectBody(sql);

        if (selectBody instanceof PlainSelect) {
            PlainSelect plainSelect = (PlainSelect) selectBody;
            //From分析
            StringBuilder sb1 = new StringBuilder();
            FromItem fromItem = plainSelect.getFromItem();
            sb1.append(fromItem.toString()).append(" ");

            List<Join> joins = plainSelect.getJoins();

            if (joins == null) {
                return "";
            }
            for (Join join:joins) {
                String s = join.toString();
                sb1.append(s).append(" ");
            }

            return sb1.toString();

        }

        return "";
    }

    /**
     * 获取where部分
     * @param sql parse sql
     * @return where body
     * @throws Exception e
     */
    public static String parseWhereBody(String sql) throws Exception {

        SelectBody selectBody = parseSqlToSelectBody(sql);

        if (selectBody instanceof PlainSelect) {
            PlainSelect plainSelect = (PlainSelect) selectBody;
            //where 条件
            Expression where = plainSelect.getWhere();
            return where == null ? "":where.toString();
        }

        return "";
    }

    /**
     * 获取order by 部分
     * @param sql parse sql
     * @return order by body
     * @throws Exception e
     */
    public static String parseOrderByBody(String sql) throws Exception {

        SelectBody selectBody = parseSqlToSelectBody(sql);

        if (selectBody instanceof PlainSelect) {
            PlainSelect plainSelect = (PlainSelect) selectBody;
            //order by 条件
            List<OrderByElement> orderByElements = plainSelect.getOrderByElements();

            if (orderByElements == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < orderByElements.size(); i++) {
                String s = orderByElements.get(i).toString();
                if (i == orderByElements.size()-1) {
                    sb.append(s);
                } else {
                    sb.append(s).append(",");
                }
            }
            return sb.toString();

        }

        return "";
    }


    private static SelectBody parseSqlToSelectBody(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        if (statement instanceof Select) {//select
            Select select = (Select) statement;
            return select.getSelectBody();
        }

        return null;
    }
}
