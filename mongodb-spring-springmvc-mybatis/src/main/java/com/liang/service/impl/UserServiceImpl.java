package com.liang.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.dao.IUserDAO;
import com.liang.model.User;
import com.liang.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDAO userDao;

	@Override
	public User selectById(int id) {
		return userDao.selectById(id);
	}

	@Override
	public List<User> getList(User obj) {
		return userDao.getList(obj);
	}

	@Override
	public void add(User obj) {
		User user1 = new User();
		user1.setId(100);
		user1.setUsername("liangz01");
		user1.setPassword("123");
		user1.setAddress("北京");
		userDao.add(user1);
		userDao.add(obj);
	}

	@Override
	public void update(User obj) {
		userDao.update(obj);
	}

	@Override
	public void deleteById(int id) {
		userDao.deleteById(id);
	}

	@Override
	public List<User> getPageList(Map<String, String> parameters,
			RowBounds rowBounds) {
		return userDao.getPageList(parameters, rowBounds);
	}

	@Override
	public void saveUser(User user) {
		if(user.getId() != null){
			userDao.update(user);
		}else{
			userDao.add(user);
		}
	}
	
	

}
