package com.liang.mybatis.model;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.liang.mybatis.inter.IUserOperation;
import com.liang.mybatis.utils.SessionFactoryUtil;

import junit.framework.TestCase;

public class UserTest{
	@Test
	public void testUser(){
		SqlSession session = null;
		session = SessionFactoryUtil.openSession();
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = userOperation.selectUserById(1);
		System.out.println(user.getUsername());
		System.out.println(user.getAddress());
		SessionFactoryUtil.closeSession(session);
	}
	
	
}
