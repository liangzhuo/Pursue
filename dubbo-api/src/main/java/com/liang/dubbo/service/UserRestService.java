package com.liang.dubbo.service;

import javax.validation.constraints.Min;

import com.liang.dubbo.model.User;

public interface UserRestService {
	
	User getUser(@Min(value=1L, message="User ID must be greater than 1") Long id);
}
