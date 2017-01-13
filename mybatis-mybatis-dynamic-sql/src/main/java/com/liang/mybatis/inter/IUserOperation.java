package com.liang.mybatis.inter;

import java.util.List;
import java.util.Map;

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
	
	//以下测试使用
	public User dynamicIfTest(User user);
	
	public User dynamicChooseTest(User user);
	
	public User dynamicTrimTest(User user);
	
	public User dynamicWhereTest(User user);
	
	public void dynamicSetTest(User user);
	
	public List<User> dynamicForEachListTest(List<Integer> ids);
	
	public User[] dynamicForEachArrayTest(Integer[] ids);
	
	public List<User> dynamicForEachMapTest(Map<String,Object> ids);	
}
