package com.yrd.mybatis.anno7;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yrd.mybatis.domain.Order;
import com.yrd.mybatis.domain.User;

public interface IOrderMapperAnno {

//	@Select("SELECT *,o.t_id order_id,u.t_id user_id FROM tbl_order o  JOIN tbl_user u ON o.t_uid = u.t_id")
//	@Results({
//		@Result(column = "order_id" ,property = "id"),
//		@Result(column = "t_ordertime",property = "orderTime"),
//		@Result(column = "t_total",property = "total"),
//		@Result(column = "user_id",property = "user.id"),
//		@Result(column = "t_username",property = "user.username"),
//		@Result(column = "t_password",property = "user.password")
//		
//	})
//	public List<Order> findAll() throws Exception;

	@Select("SELECT * FROM tbl_order   ")
	@Results({ @Result(column = "t_id", property = "id"), @Result(column = "t_ordertime", property = "orderTime"),
			@Result(column = "t_total", property = "total"), @Result(property = "user", // 要封装的属性名称
					column = "t_uid", // 根据哪个字段去查询user表的数据
					javaType = User.class, // 要封装的实体类型
					// select 属性 代表查询哪个接口的方法获得数据
					one = @One(select = "com.yrd.mybatis.anno7.IUserMapperAnno.findById"))

	})
	public List<Order> findAll() throws Exception;
	
	
	
	@Select("select t_id id, t_ordertime ordertime,t_total total,t_uid uid from tbl_order WHERE t_uid=#{uid}")
	public List<Order> findByUid(int uid);
	
	
	
	
}
