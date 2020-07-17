package com.example.springbootjsqlparser;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.List;

/**
 * <p>Description: Druid 格式化示例</p>
 *
 * @author dbx
 * @date 2020/4/23 11:25
 * @since JDK1.8
 */
public class TestDruidFormat {

    @Test
    void format(){
        /**
         * 其中dbType支持mysql/postgresql/odps/oracle/db2/sqlserver
         * option缺省有SQLUtils.DEFAULT_FORMAT_OPTION（大写）、SQLUtils.DEFAULT_LCASE_FORMAT_OPTION（小写）两种可以选择，也可按需要定制化。
         */
        String sql = "update student set name = 'R' where id < 100 limit 10";
        String result = SQLUtils.format(sql, JdbcConstants.MYSQL);
        System.out.println("-- 这是缺省的大写格式");
        System.out.println(result); // 缺省大写格式

        System.out.println("-- 这是小写格式");
        String result_lcase = SQLUtils.format(sql, JdbcConstants.MYSQL, SQLUtils.DEFAULT_LCASE_FORMAT_OPTION);
        System.out.println(result_lcase); // 小写格式

    }

    @Test
    void parse(){
        String sql = "select * from t where id=1 and name=ming group by uid limit 1,200 order by ctime";

        // 新建 MySQL Parser
        SQLStatementParser parser = new MySqlStatementParser(sql);

        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLStatement sqlStatement = parser.parseStatement();

        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        sqlStatement.accept(visitor);

        System.out.println("getTables:" + visitor.getTables());
        System.out.println("getParameters:" + visitor.getParameters());
        System.out.println("getOrderByColumns:" + visitor.getOrderByColumns());
        System.out.println("getGroupByColumns:" + visitor.getGroupByColumns());
        System.out.println("---------------------------------------------------------------------------");

        // 使用select访问者进行select的关键信息打印
        SelectPrintVisitor selectPrintVisitor = new SelectPrintVisitor();
        sqlStatement.accept(selectPrintVisitor);
        System.out.println("---------------------------------------------------------------------------");


        // 最终sql输出
        StringWriter out = new StringWriter();
        TableNameVisitor outputVisitor = new TableNameVisitor(out);
        sqlStatement.accept(outputVisitor);
        System.out.println(out.toString());
        System.out.println("---------------------------------------------------------------------------");
    }

    /**
     * 查询语句访问者
     *
     * @author xiezhengchao
     * @since 2018/6/1 12:08
     */
    public class SelectPrintVisitor extends SQLASTVisitorAdapter {

        @Override
        public boolean visit(SQLSelectQueryBlock x) {
            List<SQLSelectItem> selectItemList = x.getSelectList();
            selectItemList.forEach(selectItem -> {
                System.out.println("attr:" + selectItem.getAttributes());
                System.out.println("expr:" + SQLUtils.toMySqlString(selectItem.getExpr()));
            });

            System.out.println("table:" + SQLUtils.toMySqlString(x.getFrom()));
            System.out.println("where:" + SQLUtils.toMySqlString(x.getWhere()));
            System.out.println("order by:" + SQLUtils.toMySqlString(x.getOrderBy().getItems().get(0)));
            System.out.println("limit:" + SQLUtils.toMySqlString(x.getLimit()));

            return true;
        }

    }

    /**
     * 数据库表名访问者
     *
     * @author xiezhengchao
     * @since 2018/6/1 11:52
     */
    public class TableNameVisitor extends MySqlOutputVisitor {

        TableNameVisitor(Appendable appender) {
            super(appender);
        }

        @Override
        public boolean visit(SQLExprTableSource x) {
            SQLName table = (SQLName) x.getExpr();
            String tableName = table.getSimpleName();

            // 改写tableName
            print0("new_" + tableName.toUpperCase());

            return true;
        }

    }

}
