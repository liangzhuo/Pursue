package com.liang.mybatis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void testDynamicIfTest(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = new User();
		//user.setUsername("liangzhuo");
		user.setAge(26);;
		//user.setAddress("湖北天门");
		User resultUser = userOperation.dynamicIfTest(user);
		System.out.println(resultUser.toString());
	}
	
	@Test
	public void testDynamicChooseTest(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = new User();
		user.setUsername("liangzhuo");
		user.setAge(27);;
		//user.setAddress("湖北天门");
		User resultUser = userOperation.dynamicChooseTest(user);
		System.out.println(resultUser.toString());
	}
	
	@Test
	public void testDynamicTrimTest(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = new User();
		user.setUsername("liangzhuo");
		//user.setAge(27);;
		//user.setAddress("湖北天门");
		User resultUser = userOperation.dynamicTrimTest(user);
		System.out.println(resultUser.toString());
	}
	
	@Test
	public void testDynamicWhereTest(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = new User();
		//user.setUsername("liangzhuo");
		//user.setAge(26);;
		user.setAddress("湖北天门");
		User resultUser = userOperation.dynamicWhereTest(user);
		System.out.println(resultUser.toString());
	}
	
	@Test
	public void testDynamicSetTest(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = new User();
		user.setId(3);
		user.setUsername("liangz1");
		//user.setAge(27);;
		user.setAddress("湖北天门");
		userOperation.dynamicSetTest(user);
	}
	
	@Test
	public void testDynamicListTest(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(3);
		ids.add(6);
		List<User> users = userOperation.dynamicForEachListTest(ids);
		for(User user : users){
			System.out.println(user);
		}
	}
	
	@Test
	public void testDynamicArrayTest(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		Integer[] ids = new Integer[2];
		ids[0]=3;
		ids[1]=6;
		User[] users = userOperation.dynamicForEachArrayTest(ids);
		for(User user : users){
			System.out.println(user);
		}
	}
	
	@Test
	public void testDynamicMapTest(){
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", "liangzhuo");
		map.put("age", 26);
		List<User> users = userOperation.dynamicForEachMapTest(map);
		for(User user : users){
			System.out.println(user);
		}
	}
	
	@After
	public void after(){
		//记住这一步，增加、修改或删除需提交，与Spring集成后不用关心
		session.commit();
		SessionFactoryUtil.closeSession(session);
	}
	
	
}
