package com.liang.mybatis.inter;

import java.util.List;

import com.liang.mybatis.model.Article;
import com.liang.mybatis.model.User;

/**
 * 使用合理描述参数和SQL语句返回值的接口
 * @author liangz
 *
 */
public interface IUserOperation {
	public User selectUserById(int id);
	
	public List<User> selectUsers(String username);
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
	
	public List<Article> getUserArticles(int id);	
}
