package com.yrd.mybatis.handler6;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class DateTypeHandler extends BaseTypeHandler<Date> {

	// java-->mysql
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date date, JdbcType jdbcType) throws SQLException {
		// date-->bigint
		long time = date.getTime();
		ps.setLong(i, time);
	}

	// mysql-->java
	// string参数：要转换的字段名称
	// ResultSet：查询出的结果集
	@Override
	public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		// 获取结果集中需要的数据（long） 转换Date 返回。
		long along = rs.getLong(columnName);
		Date date = new Date(along);
		return date;
	}

	//// mysql-->java
	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		// 获取结果集中需要的数据（long） 转换Date 返回。
		long along = rs.getLong(columnIndex);
		Date date = new Date(along);
		return date;
	}

	//// mysql-->java
	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		long along = cs.getLong(columnIndex);
		Date date = new Date(along);
		return date;
	}

}
