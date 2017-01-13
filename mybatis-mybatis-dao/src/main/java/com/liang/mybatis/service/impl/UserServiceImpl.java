package com.liang.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liang.mybatis.dao.IUserDAO;
import com.liang.mybatis.model.Article;
import com.liang.mybatis.model.User;
import com.liang.mybatis.service.IUserService;

@Component
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDAO userDAO;

	public User selectUserById(int id) {
		return userDAO.selectUserById(id);
	}

	public List<User> selectUsers(String username) {
		return userDAO.selectUsers(username);
	}

	public void addUser(User user) {
		userDAO.addUser(user);
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

	public List<Article> getUserArticles(int id) {
		return userDAO.getUserArticles(id);
	}

}
