package com.liang.mybatis.service;

import java.util.List;

import com.liang.mybatis.model.Article;
import com.liang.mybatis.model.User;

public interface IUserService {
public User selectUserById(int id);
	
	public List<User> selectUsers(String username);
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
	
	public List<Article> getUserArticles(int id);
}
