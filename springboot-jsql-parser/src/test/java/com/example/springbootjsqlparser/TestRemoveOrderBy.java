package com.example.springbootjsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;
import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * <p>Description: </p>
 * <p>
 * 　　SELECT select_list
 * 　　[ INTO new_table ]
 * 　　FROM table_source
 * 　　[ WHERE search_condition ]
 * 　　[ GROUP BY group_by_expression ]
 * 　　[ HAVING search_condition ]
 * 　　[ ORDER BY order_expression [ ASC | DESC ] ]
 *
 * @author dbx
 * @date 2020/4/23 16:47
 * @since JDK1.8
 */
class TestRemoveOrderBy {

    @Test
    void baseParse() {
//        String sql = "select t1 from table1 t1 union all select t2.b,t3.c from table2 t2,table3 t3 where t2.b = t3.c";
        String sql2 = "select d,c from (select name from table where id = 3 group by name order by name ) as a where c = '22' group by d order by c";
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(sql2);
            Select select = (Select) stmt;
            SelectBody selectBody = select.getSelectBody();

            if (selectBody instanceof PlainSelect) {
                //The core of a "SELECT" statement
                PlainSelect plainSelect = (PlainSelect) selectBody;

                //最外层 order by  字段
                List<OrderByElement> orderByElements = plainSelect.getOrderByElements();

                //去除 order by 语句
                if (null != orderByElements) {
                    plainSelect.setOrderByElements(null);
                }

                //最外层 group by 语句
                GroupByElement groupBy = plainSelect.getGroupBy();

                //去除 group by 语句
                if (groupBy != null) {
                    plainSelect.setGroupByElement(null);
                }


                //From分析
                FromItem fromItem = plainSelect.getFromItem();//(select name from table where id = 3 group by name order by name ) as a
                //Table table = (Table) fromItem; // table.getAlias().getName(), table.getName()

                // join分析
                List<Join> joins = plainSelect.getJoins();
                if (joins != null) {
                    for (Join join : joins) {
                        FromItem rightItem = join.getRightItem();
                        Table tab = (Table) rightItem; // (table.getAlias().getName(), table.getName());
                    }
                }


                // where 分析
                Expression where = plainSelect.getWhere();
                if (where instanceof EqualsTo) {
                    EqualsTo equalsTo = (EqualsTo) where;
                    Expression rightExpression = equalsTo.getRightExpression();
                    Expression leftExpression = equalsTo.getLeftExpression();
                    if (rightExpression instanceof Column && leftExpression instanceof Column) {
                        Column rightColumn = (Column) rightExpression;
                        Column leftColumn = (Column) leftExpression;

                    }
                } else if (where instanceof AndExpression) {
                    AndExpression andExpression = (AndExpression) where;
                    Expression leftExpression = andExpression.getLeftExpression();

                    Expression rightExpression = andExpression.getRightExpression();
                }

            } else if (selectBody instanceof WithItem) {
                //One of the parts of a "WITH" clause of a "SELECT" statement
                WithItem withItem = (WithItem) selectBody;
                if (withItem.getSelectBody() != null) {

                }
            } else if (selectBody instanceof SetOperationList) {
                SetOperationList operationList = (SetOperationList) selectBody;
                List<OrderByElement> orderByElements = operationList.getOrderByElements();

            } else {
                ValuesStatement valuesStatement = (ValuesStatement) selectBody;

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    void removeOrderBy() {
        String sql = "select id from user where name = ? and id=? order by id";
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(sql);

            Select select = (Select) stmt;
            SelectBody selectBody = select.getSelectBody();
            if (selectBody instanceof PlainSelect) {
                //The core of a "SELECT" statement
                PlainSelect plainSelect = (PlainSelect) selectBody;

                System.out.println(plainSelect.toString());

            } else if (selectBody instanceof WithItem) {
                //One of the parts of a "WITH" clause of a "SELECT" statement
                WithItem withItem = (WithItem) selectBody;
                if (withItem.getSelectBody() != null) {

                }
            } else if (selectBody instanceof SetOperationList) {
                SetOperationList operationList = (SetOperationList) selectBody;

            } else {
                ValuesStatement valuesStatement = (ValuesStatement) selectBody;

            }

        } catch (JSQLParserException e) {
            e.printStackTrace();
        }

    }


}
