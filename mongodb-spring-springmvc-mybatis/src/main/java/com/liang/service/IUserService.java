package com.liang.service;

import com.liang.model.User;

public interface IUserService extends IBaseService<User>{
	void saveUser(User user);
}
