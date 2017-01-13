package com.liang.springmvc.service;

import java.util.List;

import com.liang.springmvc.model.UserModel;

public interface IUserService {

	void create(UserModel user);

	UserModel get(String username);

	void update(UserModel user);

	void delete(UserModel user);

	List<UserModel> list();

}
