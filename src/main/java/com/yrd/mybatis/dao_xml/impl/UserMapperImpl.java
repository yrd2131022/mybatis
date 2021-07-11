package com.yrd.mybatis.dao_xml.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yrd.mybatis.dao_xml.UserMapper;
import com.yrd.mybatis.domain.User;

public class UserMapperImpl implements UserMapper {

	@Override
	public List<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<User> selectList = sqlSession.selectList("dao_xml.userMapper.findAll");
		
		return selectList;
	}

}
