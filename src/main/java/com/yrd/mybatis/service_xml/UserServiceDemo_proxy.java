package com.yrd.mybatis.service_xml;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yrd.mybatis.dao_xml_proxy.IUserMapper;
import com.yrd.mybatis.domain.User;

public class UserServiceDemo_proxy {

	public static void main(String[] args) throws Exception {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//代理方式对Dao进行实现
		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		
//		List<User> findAll = mapper.findAll();
//		System.out.println(findAll);
//		
		User user = mapper.findById(12);
		System.out.println(user);
		
//		User user = new User();
////		user.setId(1);
//		user.setUsername("tom");
//		//user.setPassword("123");
//		List<User> list = mapper.findByCondition(user);
		
		
		//模拟ids数据
//		List<Integer> ids = new ArrayList<Integer>();
//		ids.add(1);
//		ids.add(7);
//		ids.add(8);
//		List<User> findByIds = mapper.findByIds(ids);
//		
//		System.out.println(findAll);
		
//		User user = new User();
//		user.setId(12);
//		user.setUsername("tomni");
//		user.setPassword("123456");
//		user.setBirthday(new Date());
//		mapper.save(user);
		
		
		sqlSession.commit();
		sqlSession.close();
		
		
	}
}
