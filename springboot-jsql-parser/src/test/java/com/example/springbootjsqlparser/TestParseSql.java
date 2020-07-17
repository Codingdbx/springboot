package com.example.springbootjsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.values.ValuesStatement;
import net.sf.jsqlparser.util.AddAliasesVisitor;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/23 10:05
 * @since JDK1.8
 */
class TestParseSql {


    /**
     * 从SQL中提取表名
     */
    @Test
    void parseSql() {

        String sql = "select * from table where id = 3";
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);

            //方法2
            CCJSqlParser ccjSqlParser = new CCJSqlParser(sql);
            Statement statement2 = ccjSqlParser.Statement();

            if (statement instanceof Select) {
                // select case
                TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
                List<String> tableList = tablesNamesFinder.getTableList(statement);

                for (String table_name : tableList) {
                    System.out.println("table_name:" + table_name);
                }


            } else if (statement instanceof Update) {
                // update case
            } else if (statement instanceof Insert) {
                // insert case
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询字段，where 条件处理
     */
    @Test
    void parseSql2() {
        String sql = "select * from table where id = 3";
        String sql2 = "select d,c from (select * from table where id = 3) as a where c = '22' group by d";
        try {

            //此处的运行时类是Select
            Select select = (Select) CCJSqlParserUtil.parse(sql2);
            SelectBody selectBody = select.getSelectBody();

            //向SELECT添加一列或表达式
            SelectUtils.addExpression(select, new Column("B"));
            System.out.println(select.toString());//SELECT *, B FROM table WHERE id = 3


            PlainSelect plainSelect = (PlainSelect) selectBody;

            //mysql
            Limit limit = new Limit();
            limit.setRowCount(new LongValue(10));
            plainSelect.setLimit(limit);
            System.out.println(select.toString());

            //sql server
            Top top = new Top();
            top.setExpression(new LongValue(10));
            plainSelect.setTop(top);
            System.out.println(select.toString());

            //oracle
            Expression expression = CCJSqlParserUtil.parseCondExpression("rownum <=10");
            plainSelect.setWhere(expression);


            //where条件
            Expression where0 = plainSelect.getWhere();
            System.out.println("where0:" + where0.toString());

            //向SQL替换where条件
            Expression where1 = CCJSqlParserUtil.parseCondExpression("a=1 and b=2");
            plainSelect.setWhere(where1);

            System.out.println("where1:" + where1.toString());

            //拼接where 条件
            Expression where2 = CCJSqlParserUtil.parseCondExpression(where0.toString() + "\tand\t" + where1.toString());
            plainSelect.setWhere(where2);
            System.out.println("where2:" + where2.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 将别名应用于所有表达式
     */
    @Test
    void parseSql3() {
        //将别名应用于所有表达式
        Select select = null;//此处的运行时类是Select
        try {
            select = (Select) CCJSqlParserUtil.parse("SELECT A,B,C FROM TABLE1");
            SelectBody selectBody = select.getSelectBody();
            AddAliasesVisitor addAliasesVisitor = new AddAliasesVisitor();
            addAliasesVisitor.setPrefix("K");//设置前缀（如不进行设置默认为“A”）
            selectBody.accept(addAliasesVisitor);
            System.out.println(selectBody.toString());//SELECT A AS K1, B AS K2, C AS K3 FROM TABLE1


        } catch (JSQLParserException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testParse() {

        String sql = "select a,b,d.* from table0 inner join table1 on table0.id = table1.eid inner join table2 b on a.id =b.eid where flowId=$baseinfo.id$ order by id,eid,pid;";
        Statement statement = null;
        try {
            statement = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }

        if (statement instanceof Select) {//select
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            if (selectBody instanceof PlainSelect) {

                PlainSelect plainSelect = (PlainSelect) selectBody;

                //select部分
                List<SelectItem> selectItems = plainSelect.getSelectItems();

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < selectItems.size(); i++) {
                    String s = selectItems.get(i).toString();
                    if (i == selectItems.size() - 1) {
                        sb.append(s);
                    } else {
                        sb.append(s).append(",");
                    }
                }
                System.out.println("select部分-------------" + sb.toString());

                //From部分
                StringBuilder sb1 = new StringBuilder();
                FromItem fromItem = plainSelect.getFromItem();
                sb1.append(fromItem.toString()).append(" ");

                List<Join> joins = plainSelect.getJoins();

                for (int i = 0; i < joins.size(); i++) {
                    Join join = joins.get(i);
                    String s = join.toString();
                    sb1.append(s).append(" ");
                }
                System.out.println("select部分-------------" + sb1.toString());

                //where条件
                Expression where = plainSelect.getWhere();
                System.out.println("where条件-------------" + where.toString());

                //最外层 order by  字段
                List<OrderByElement> orderByElements = plainSelect.getOrderByElements();

                StringBuilder sb2 = new StringBuilder();

                for (int i = 0; i < orderByElements.size(); i++) {
                    String s = orderByElements.get(i).toString();
                    if (i == orderByElements.size() - 1) {
                        sb2.append(s);
                    } else {
                        sb2.append(s).append(",");
                    }
                }
                System.out.println("order by部分-------------" + sb2.toString());

            } else if (selectBody instanceof WithItem) {
                //One of the parts of a "WITH" clause of a "SELECT" statement
                WithItem withItem = (WithItem) selectBody;

            } else if (selectBody instanceof SetOperationList) {
                SetOperationList operationList = (SetOperationList) selectBody;
                List<OrderByElement> orderByElements = operationList.getOrderByElements();

            } else {
                ValuesStatement valuesStatement = (ValuesStatement) selectBody;

            }
        }

    }
}
