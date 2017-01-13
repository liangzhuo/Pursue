package com.liang.springmvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.liang.springmvc.model.UserModel;
import com.liang.springmvc.service.IUserService;

public class UserServiceImpl implements IUserService{
	private Map<String,UserModel> users = new HashMap<String,UserModel>();

	public void create(UserModel user) {
		if(users.get(user.getUsername()) == null){
			users.put(user.getUsername(), user);
		}
	}

	public UserModel get(String username) {
		return users.get(username);
	}

	public void update(UserModel user) {
		UserModel userModel = users.get(user.getUsername());
		userModel.setUsername(user.getUsername());
		userModel.setRealname(user.getRealname());
		users.put(user.getUsername(), userModel);
	}

	public void delete(UserModel user) {
		users.remove(user.getUsername());
	}

	public List<UserModel> list() {
		List<UserModel> list = new ArrayList<UserModel>();
		Set<Entry<String,UserModel>> userEntrys = users.entrySet();
		Iterator<Entry<String,UserModel>> ite = userEntrys.iterator();
		while(ite.hasNext()){
			Entry<String,UserModel> entry = ite.next();
			list.add(entry.getValue());
		}
		return list;
	}

}
