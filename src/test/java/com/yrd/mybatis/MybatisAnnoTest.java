package com.yrd.mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.yrd.mybatis.anno7.IOrderMapperAnno;
import com.yrd.mybatis.anno7.IUserMapperAnno;
import com.yrd.mybatis.domain.Order;
import com.yrd.mybatis.domain.User;
import com.yrd.mybatis.domain.UserWithOrder;

public class MybatisAnnoTest {
	
	private IUserMapperAnno userMapperAnno;
	private IOrderMapperAnno orderMapperAnno;
	SqlSession openSession;
	
	@Before
	public void before() throws Exception{
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		 openSession = sqlSessionFactory.openSession(true);
		 
	}
	
	@Test
	public void testAdd() {
		userMapperAnno = openSession.getMapper(IUserMapperAnno.class);
		User user = new User();
		user.setUsername("yrd1");
		user.setPassword("1234");
		
		userMapperAnno.add(user);
	}
	
	@Test
	public void testFindAll() throws Exception {
		orderMapperAnno = openSession.getMapper(IOrderMapperAnno.class);
		
		List<Order> allOrders = orderMapperAnno.findAll();
		for (Order order : allOrders) {
			System.out.println(order);
		}
	}
	
	@Test
	public void testFindUserAndOrderAll() {
		userMapperAnno = openSession.getMapper(IUserMapperAnno.class);
		
		List<UserWithOrder> list = userMapperAnno.findUserAndOrderAll();
		for (UserWithOrder userWithOrder : list) {
			System.out.println(userWithOrder);
		}
	}
	
	
	@Test
	public void testFindUserAndRoleAll() {
		userMapperAnno = openSession.getMapper(IUserMapperAnno.class);
		
		List<UserWithOrder> list = userMapperAnno.findUserAndRoleAll();
		for (UserWithOrder userWithOrder : list) {
			System.out.println(userWithOrder);
		}
	}
	
	

}
