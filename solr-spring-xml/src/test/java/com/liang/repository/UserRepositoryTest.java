package com.liang.repository;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.repository.support.SolrRepositoryFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liang.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserRepositoryTest {
	
	@Autowired
	private SolrOperations solrOperations;
	
	private UserRepository userRepository;
	
	@Before
	public void setUp() {
		userRepository = new SolrRepositoryFactory(this.solrOperations).getRepository(UserRepository.class);
	}
	
	@After
	public void destory() {
		//solrOperations.delete(new SimpleQuery(new SimpleStringCriteria("*:*")));
		//solrOperations.commit();
	}
	
	@Test
	public void testQuery() {
		Pageable pageable = new SolrPageRequest(0, 1);
		Page<User> users = userRepository.findByAddress("武汉", pageable);
		for(User user : users) {
			System.out.println(user);
		}
		Assert.assertEquals(1, users.getSize());
	}
	
	
	@Test
	public void testAdd() {
		User user = createUser("3");
		user = userRepository.save(user);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testFindOne() {
		User user = userRepository.findOne("3");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testUpdate() {
		User user = userRepository.findOne("3");
		user.setAddress("南京3");
		user = userRepository.save(user);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testDelete() {
		User user = userRepository.findOne("3");
		userRepository.delete(user);
		Assert.assertEquals(2, userRepository.count());
	}

	private User createUser(String id) {
		User user = new User();
		user.setId(id);
		user.setName("liangz" + id);
		user.setAddress("武汉" + id);
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
