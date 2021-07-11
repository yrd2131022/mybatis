package com.yrd.mybatis.service_xml;

import java.util.List;

import com.yrd.mybatis.dao_xml.UserMapper;
import com.yrd.mybatis.dao_xml.impl.UserMapperImpl;
import com.yrd.mybatis.domain.User;

public class UserServiceDemo {

	public static void main(String[] args) throws Exception {
		//创建dao层对象  当前dao层实现是手动编写的(传统方式的)
		UserMapper userMapper = new UserMapperImpl();
		List<User> findAll = userMapper.findAll();
		
		System.out.println(findAll);
	}
}
