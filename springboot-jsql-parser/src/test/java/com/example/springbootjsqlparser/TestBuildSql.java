package com.example.springbootjsqlparser;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.SelectUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/23 16:35
 * @since JDK1.8
 */
class TestBuildSql {

    @Test
    void select(){
        Select select = SelectUtils.buildSelectFromTable(new Table("user"));
        // SELECT * FROM user
        System.out.println(select.toString());

    }

    @Test
    void delete(){
        Delete delete = new Delete();
        delete.setTable(new Table("user"));
        // DELETE FROM user
        System.out.println(delete);
    }

    @Test
    void insert(){
        Insert insert = new Insert();
        insert.setTable(new Table("user"));

        List<Column> columns = new ArrayList<>();
        columns.add(new Column("age"));
        insert.setColumns(columns);

        List<Expression> expressions = new ArrayList<>();
        expressions.add(new LongValue(20));

        ExpressionList expressionList = new ExpressionList();
        expressionList.setExpressions(expressions);
        insert.setItemsList(expressionList);

        // INSERT INTO user (age) VALUES (20)
        System.out.println(insert.toString());

    }

    @Test
    void update(){
        Update update = new Update();
        update.setTable(new Table("user"));

        List<Column> columns = new ArrayList<>();
        columns.add(new Column("age"));
        update.setColumns(columns);

        List<Expression> expressions = new ArrayList<>();
        expressions.add(new LongValue(20));
        update.setExpressions(expressions);

        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new StringValue("user_id"));
        equalsTo.setRightExpression(new StringValue("111000011"));
        update.setWhere(equalsTo);

        // UPDATE user SET age = 20 WHERE 'user_id' = '111000011'
        System.out.println(update.toString());

    }
}
