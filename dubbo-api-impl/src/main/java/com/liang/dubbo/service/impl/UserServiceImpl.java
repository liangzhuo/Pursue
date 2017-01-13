package com.liang.dubbo.service.impl;

import org.springframework.stereotype.Service;

import com.liang.dubbo.model.User;
import com.liang.dubbo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Override
	public User getUser(Long id) {
		System.out.println("get user");
		return new User(id, "username" + id);
	}
	
}
