package com.liang.repository;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liang.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void destroy() {
		//userRepository.deleteAll();
	}
	
	@Test
	public void testQuery() {
		List<User> users = userRepository.findByLastName("梁");
		for(User user : users){
			System.out.println(user.getLastName());
		}
	}
	
	@Test
	public void testAdd() {
		User user = createUser("1");
		user = userRepository.save(user);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testFindOne() {
		User user = userRepository.findFirstByLastName("梁");
		System.out.println(user);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testUpdate() {
		User user = userRepository.findOne("1");
		user.setFirstName("梁卓");
		user = userRepository.save(user);
		System.out.println(user);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testDelete() {
		userRepository.delete("1");
		User user = userRepository.findOne("1");
		Assert.assertNull(user);
	}
	
	private User createUser(String id) {
		User user = new User();
		user.setId(id);
		user.setName("liangz" + id);
		user.setFirstName("梁" + id);
		user.setLastName("卓" + id);
		return user;
	}
	
	private List<User> createUserList() {
		List<User> userList = new ArrayList<User>();
		for (int i=0; i<10; i++) {
			User user = createUser(i + "");
			userList.add(user);
		}
		return userList;
	}
}
