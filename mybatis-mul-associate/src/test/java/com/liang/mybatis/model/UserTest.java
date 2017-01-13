package com.liang.mybatis.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.liang.mybatis.inter.IUserOperation;
import com.liang.mybatis.utils.SessionFactoryUtil;

public class UserTest{
	private SqlSession session = null;
	
	@Before
	public void before(){
		session = SessionFactoryUtil.openSession();
	}
	
	@Test
	public void testListUser(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		List<User> users = userOperation.selectUsers("%");
		for(User user : users){
			System.out.println(user.getUsername());
			System.out.println(user.getAddress());
		}
	}
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUsername("liangzhuo");
		user.setAge(26);
		user.setAddress("湖北天门");
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		userOperation.addUser(user);
	}
	
	@Test
	public void testUpdateUser(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = userOperation.selectUserById(1);
		user.setUsername("liangzhuo1");
		user.setAge(25);
		user.setAddress("湖北天门黄潭");
		userOperation.updateUser(user);
	}
	
	@Test
	public void testDeleteUser(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		userOperation.deleteUser(1);
	}
	
	@Test
	public void testSelectArticleList(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		List<Article> articles = userOperation.getUserArticles(3);
		for(Article art : articles){
			System.out.println(art.getTitle() + ":" + art.getUser().getAddress());
		}
	}
	
	@After
	public void after(){
		//记住这一步，增加、修改或删除需提交，与Spring集成后不用关心
		session.commit();
		SessionFactoryUtil.closeSession(session);
	}
	
	
}
