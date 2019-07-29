package edu.seu.cpopirs_util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectTypeHandler extends BaseTypeHandler<Object> {
    private final Class clazz;
    public ObjectTypeHandler(Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz not null");
        }
        // 在这里对应com.test.CardExt.class 对象
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        return JSON.parseObject(s, clazz);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        return JSON.parseObject(s, clazz);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        return JSON.parseObject(s, clazz);
    }
}
