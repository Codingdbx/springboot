package com.example.springbootautoweb.aop;


import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * <p>Description: 自定义mybatis插件</p>
 * <p>
 * mybatis在执行期间，主要有四大核心接口对象：
 * 执行器Executor，执行器负责整个SQL执行过程的总体控制。
 * 参数处理器ParameterHandler，参数处理器负责PreparedStatement入参的具体设置。
 * 语句处理器StatementHandler，语句处理器负责和JDBC层具体交互，包括prepare语句，执行语句，以及调用ParameterHandler.parameterize()设置参数。
 * 结果集处理器ResultSetHandler，结果处理器负责将JDBC查询结果映射到java对象。
 *
 * @author dbx
 * @date 2020/3/12 16:49
 * @since JDK1.8
 */

@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class}),
})
@Component
public class SqlExecuteInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(SqlExecuteInterceptor.class);

    /**
     * 打印的参数字符串的最大长度
     */
    private final static int MAX_PARAM_LENGTH = 500;

    /**
     * 记录的最大SQL长度
     */
    private final static int MAX_SQL_LENGTH = 2000;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

//        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
//        SqlCommandType sqlCommandType = ms.getSqlCommandType();

        Object target = invocation.getTarget();
        StatementHandler statementHandler = (StatementHandler) target;

        BoundSql boundSql = statementHandler.getBoundSql();
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
        String sql = boundSql.getSql();

        int num = 0;
        Object result;
        if (sql.toLowerCase().indexOf("select") == 0) {//查询sql
            //获取总记录数
            String countSql = String.format("select count(1) as num from (%s) as sql", sql);

            PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];

            Connection connection = ps.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(countSql);

            //参数处理
            ParameterHandler parameterHandler = statementHandler.getParameterHandler();
            parameterHandler.setParameters(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                num = rs.getInt("num");
            }

            long startTime = System.currentTimeMillis();
            result = invocation.proceed();
            long endTime = System.currentTimeMillis();
            long count = endTime - startTime;

            // 格式化Sql语句，去除换行符，替换参数
            sql = formatSQL(sql, parameterObject, parameterMappingList);
            logger.info("执行 SQL：[ {} ]执行耗时[ {} ms]总记录数[{}]", sql, count, num);
        } else {
            long startTime = System.currentTimeMillis();
            result = invocation.proceed();
            long endTime = System.currentTimeMillis();
            long count = endTime - startTime;

            //记录影响行数
            int affectedRows = Integer.parseInt(result.toString());
            // 格式化Sql语句，去除换行符，替换参数
            sql = formatSQL(sql, parameterObject, parameterMappingList);
            logger.info("执行 SQL：[ {} ]执行耗时[ {} ms]总影响行数[{}]", sql, count, affectedRows);
        }

        return result;
    }

    /**
     * 格式化/美化 SQL语句
     *
     * @param sql                  sql 语句
     * @param parameterObject      参数的Map
     * @param parameterMappingList 参数的List
     * @return 格式化之后的SQL
     */
    private String formatSQL(String sql, Object parameterObject, List<ParameterMapping> parameterMappingList) {
        // 输入sql字符串空判断
        if (sql == null || sql.length() == 0) {
            return "";
        }
        // 美化sql
        sql = beautifySql(sql);
        // 不传参数的场景，直接把sql美化一下返回出去
        if (parameterObject == null || parameterMappingList == null || parameterMappingList.size() == 0) {
            return sql;
        }

        return sql;
    }


    /**
     * 美化sql
     *
     * @param sql sql语句
     */
    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n]+", "  ");
        return sql;
    }


    /**
     * 必须要重写
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
