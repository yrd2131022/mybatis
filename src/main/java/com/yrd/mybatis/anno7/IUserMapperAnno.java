package com.yrd.mybatis.anno7;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yrd.mybatis.domain.User;
import com.yrd.mybatis.domain.UserWithOrder;

public interface IUserMapperAnno {
	
	@Select("select t_id id, t_username username,t_password password from tbl_user")
	public List<User> findAll() throws Exception;
	
	@Select("select t_id id, t_username username,t_password password  from tbl_user where t_id=#{id}")
	public User findById(int id) ;
	
	@Insert("insert into tbl_user(t_username,t_password) values(#{username},#{password})")
	public void add(User user);
	
	@Update("update tbl_user set t_username=#{username},t_password=#{password} where t_id = #{id}")
	public void update(User user);
	
	@Delete("delete from tbl_user where t_id=#{id}")
	public void delete(int id);
	
	
	/*需要实现以下两个查询
	 * SELECT * FROM tbl_user
	 * SELECT * FROM tbl_order WHERE t_uid=1
	 */
	@Select("SELECT * FROM tbl_user")
	@Results({
			@Result(id = true,column = "t_id" ,property = "id"),
			@Result(column = "t_username" ,property = "username"),
			@Result(column = "t_password" ,property = "password"),
			@Result(
					column = "t_id",
					property = "orderList",
					javaType = List.class,
					many = @Many(select = "com.yrd.mybatis.anno7.IOrderMapperAnno.findByUid")
					)
	})
	public List<UserWithOrder> findUserAndOrderAll();
	
	
	
	
	/*需要实现以下两个查询
	 * SELECT * FROM tbl_user
	 * SELECT *,r.t_id role_id FROM tbl_user_role ur,tbl_role r 
	 * WHERE  r.t_id = ur.t_roleid AND ur.t_userid=1
	 */
	@Select("SELECT * FROM tbl_user")
	@Results({
		@Result(id = true,column = "t_id" ,property = "id"),
		@Result(column = "t_username" ,property = "username"),
		@Result(column = "t_password" ,property = "password"),
		@Result(
				column = "t_id",
				property = "roleList",
				javaType = List.class,
				many = @Many(select = "com.yrd.mybatis.anno7.IRoleMapperAnno.findByUid")
				)
})
	public List<UserWithOrder> findUserAndRoleAll();



}
