package com.yrd.mybatis;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yrd.mybatis.dao_xml_proxy.IOrderMapper;
import com.yrd.mybatis.dao_xml_proxy.IUserMapper;
import com.yrd.mybatis.domain.Order;
import com.yrd.mybatis.domain.UserWithOrder;
import com.yrd.mybatis.quick.User;

public class MyBatisTest {
	
	
	@Test
	public void test1() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//4.执行操作  参数：namespace+id
		List<User> userList = sqlSession.selectList("userMapper.findAll");
		
		//5.打印数据
		System.out.println(userList);
		//6.释放资源
		sqlSession.close();
	}
	
	@Test//插入
	public void test2() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//模拟user对象
		User user = new User();
		user.setUsername("lili");
		user.setPassword("123");
		
		//4.执行操作  参数：namespace+id
		int insert = sqlSession.insert("userMapper.save",user);
		
		//============myBatis执行更新操做  提交事务=================
		sqlSession.commit();
		
		//5.打印数据
		System.out.println(insert);
		//6.释放资源
		sqlSession.close();
	}
	
	
	@Test//修改
	public void test3() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//模拟user对象
		User user = new User();
		user.setId(2);
		user.setUsername("lucy");
		user.setPassword("123");
		
		//4.执行操作  参数：namespace+id
		int insert = sqlSession.update("userMapper.update",user);
		
		//============myBatis执行更新操做  提交事务=================
		sqlSession.commit();
		
		//5.打印数据
		System.out.println(insert);
		//6.释放资源
		sqlSession.close();
	}
	
	
	@Test//删除
	public void test4() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象(true 自动提交，默认值false)
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		
		//4.执行操作  参数：namespace+id
		 int update = sqlSession.update("userMapper.delete",5);
		
		//5.打印数据
		System.out.println(update);
		//6.释放资源
		sqlSession.close();
	}
	
	
	@Test
	public void test5() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//4.执行操作  参数：namespace+id
		 User user = sqlSession.selectOne("userMapper.findById",1);
		
		//5.打印数据
		System.out.println(user);
		//6.释放资源
		sqlSession.close();
	}
	
	@Test
	public void test6() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//4.执行操作  参数：namespace+id
		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		
		//设置分页的相关参数   当前页+每页显示的条数
		PageHelper.startPage(2, 4);
		
		List<com.yrd.mybatis.domain.User> userList = mapper.findAll();
		//5.打印数据
		for (com.yrd.mybatis.domain.User user : userList) {
			System.out.println(user);
		}
		
		//获得与分页相关参数
		PageInfo<com.yrd.mybatis.domain.User> pageInfo = new PageInfo<com.yrd.mybatis.domain.User>(userList);
		System.out.println("当前页："+pageInfo.getPageNum());
		System.out.println("每页显示的条数："+pageInfo.getPageSize());
		System.out.println("总条数："+pageInfo.getTotal());
		System.out.println("总页数："+pageInfo.getPages());
		System.out.println("上一页："+pageInfo.getPrePage());
		System.out.println("下一页："+pageInfo.getNextPage());
		System.out.println("是否是第一页："+pageInfo.isIsFirstPage());
		System.out.println("是否是最后一页："+pageInfo.isIsLastPage());
		//6.释放资源
		sqlSession.close();
	}
	
	
	@Test
	public void test7() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//4.执行操作  
		IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
		
		List<Order> orders = mapper.findAll();
		
		for (Order order : orders) {
			System.out.println(order);
		}
		//6.释放资源
		sqlSession.close();
	}
	
	
	@Test
	public void test8() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//4.执行操作  
		 IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		
		List<UserWithOrder> userWithOrders = mapper.findAllWithOrder();
		
		for (UserWithOrder userWithOrder : userWithOrders) {
			System.out.println(userWithOrder);
		}
		//6.释放资源
		sqlSession.close();
	}
	
	
	@Test
	public void test9() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//4.执行操作  
		 IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		
		List<UserWithOrder> userWithOrders = mapper.findUserAndRoleAll();
		
		for (UserWithOrder userWithOrder : userWithOrders) {
			System.out.println(userWithOrder);
		}
		//6.释放资源
		sqlSession.close();
	}
	
	@Test
	public void test10() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//4.执行操作  
		 IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		 
		 HashMap<String,Object> map = new HashMap<String, Object>();
		 map.put("userid2", 1);
		
		 List<Map<String,Object>> list = mapper.spFindUser(map);
		 
		 for (Map<String, Object> map2 : list) {
			System.out.println(map2);
		}
		System.out.println(map.get("username"));
	
		//6.释放资源
		sqlSession.close();
	}
	
	@Test
	public void test11() throws Exception {
		//1.获得核心配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.获得session工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//3.获得session的会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//4.执行操作  
		 IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		 
		 HashMap<String,Object> map = new HashMap<String, Object>();
		 map.put("userid", 0);
		
		 List<Map<String,Object>> list = mapper.findMap(1);
		 
		 for (Map<String, Object> map2 : list) {
			System.out.println(map2.toString());
		}
		
	
		//6.释放资源
		sqlSession.close();
	}

}
