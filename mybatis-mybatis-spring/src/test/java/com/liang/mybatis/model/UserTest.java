package com.liang.mybatis.model;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liang.mybatis.inter.IUserOperation;

public class UserTest{
	
	@Test
	public void testMyBatisSpring(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserOperation userOperation = ac.getBean("userMapper",IUserOperation.class);
		List<User> users = userOperation.selectUsers("%");
		for(User user : users){
			System.out.println(user.getUsername());
			System.out.println(user.getAddress());
		}
	}
	
}
