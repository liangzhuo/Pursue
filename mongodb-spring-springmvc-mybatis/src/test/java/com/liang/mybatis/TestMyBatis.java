package com.liang.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liang.model.User;
import com.liang.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class TestMyBatis {
	private static Logger logger = Logger.getLogger("TestMyBatis");
	@Autowired
	private IUserService userService;
	
	@Test
	public void testMyBatis(){
		User user = userService.selectById(1);
		logger.info(user.toString());
	}
	
	@Test
	public void testMyBatisPage(){
		Map<String,String> parameters = new HashMap<String,String>();
		List<User> users = userService.getPageList(parameters, new RowBounds(0, 3));
		System.out.println(users.size());
	}
	
	@Test
	public void testSave(){
		User user = new User();
		user.setId(1);
		user.setUsername("liangz01");
		user.setPassword("123");
		user.setAddress("北京");
		
		userService.add(user);
	}
	
}
