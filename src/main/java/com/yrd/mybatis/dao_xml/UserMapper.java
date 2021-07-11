package com.yrd.mybatis.dao_xml;

import java.util.List;

import com.yrd.mybatis.domain.User;

public interface UserMapper {
	
	public List<User> findAll() throws Exception;

}
