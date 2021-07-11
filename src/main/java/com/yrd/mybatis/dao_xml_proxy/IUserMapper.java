package com.yrd.mybatis.dao_xml_proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yrd.mybatis.domain.User;
import com.yrd.mybatis.domain.UserWithOrder;

public interface IUserMapper {
	
	public List<User> findAll() throws Exception;
	
	public User findById(int id) ;
	
	public List<User> findByCondition(User user);
	
	public List<User> findByIds(List<Integer> list); 
	
	//将java的Date类型保存到mysql中的bigint中
	public void save(User user);
	
	public List<UserWithOrder> findAllWithOrder() throws Exception;
	
	public List<UserWithOrder> findUserAndRoleAll();
	
	public List<Map<String, Object>> spFindUser(HashMap<String, Object> map);
	
	public List<Map<String, Object>>  findMap(int id);

}
