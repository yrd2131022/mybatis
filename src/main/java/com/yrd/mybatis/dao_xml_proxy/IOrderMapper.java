package com.yrd.mybatis.dao_xml_proxy;

import java.util.List;

import com.yrd.mybatis.domain.Order;

public interface IOrderMapper {

	
	public List<Order> findAll() throws Exception;
}
