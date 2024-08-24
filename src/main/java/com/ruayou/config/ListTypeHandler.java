package com.ruayou.config;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @Author：ruayou
 * @Date：2024/8/24 14:31
 * @Filename：ListTypeHandler
 */
public class ListTypeHandler implements TypeHandler<List<String>> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<String> list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(list));
    }

    @Override
    public List<String> getResult(ResultSet resultSet, String s) throws SQLException {
        String re = resultSet.getString(s);
        List<String> list = JSON.parseArray(re, String.class);
        return list;
    }

    @Override
    public List<String> getResult(ResultSet resultSet, int i) throws SQLException {
        return Collections.emptyList();
    }

    @Override
    public List getResult(CallableStatement callableStatement, int i) throws SQLException {
        return Collections.emptyList();
    }
}
