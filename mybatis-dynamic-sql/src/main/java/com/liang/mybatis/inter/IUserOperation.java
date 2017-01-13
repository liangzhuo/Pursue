package com.liang.mybatis.inter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	
	//以下测试使用
	public User dynamicIfTest(User user);
	
	public User dynamicChooseTest(User user);
	
	public User dynamicTrimTest(User user);
	
	public User dynamicWhereTest(User user);
	
	public void dynamicSetTest(User user);
	
	public List<User> dynamicForEachListTest(List<Integer> ids);
	
	public User[] dynamicForEachArrayTest(Integer[] ids);
	
	/*
	 * Map类型没有默认的map，所以不能直接写collection="map",
	 * 如果这么写，需要保证传入的Map参数有@Param("map")注解
	 */
	public List<User> dynamicForEachMapTest(@Param("map") Map<String,Object> ids);	
}
