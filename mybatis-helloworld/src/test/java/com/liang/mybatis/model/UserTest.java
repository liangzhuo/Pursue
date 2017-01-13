package com.liang.mybatis.model;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.liang.mybatis.utils.SessionFactoryUtil;

import junit.framework.TestCase;

public class UserTest{
	@Test
	public void testUser(){
		SqlSession session = null;
		session = SessionFactoryUtil.openSession();
		//直接执行已映射的sql语句
		User user = (User)session.selectOne("com.liang.mybatis.model.UserMapper.selectUserById",3);
		System.out.println(user.getUsername());
		System.out.println(user.getAddress());
		SessionFactoryUtil.closeSession(session);
	}
	
	
}
