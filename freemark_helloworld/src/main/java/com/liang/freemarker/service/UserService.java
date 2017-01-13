package com.liang.freemarker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liang.freemarker.model.User;

public class UserService {
	private static List<User> users = new ArrayList<User>();
	
	static{
		for(int i=0; i<10; i++){
			User u = new User();
			u.setId(i);
			u.setAge(i+10);
			u.setName("name"+i);
			u.setBirthday(new Date());
			users.add(u);
		}
	}
	
	public static List<User> getUsers(){
		return users;
	}
	
	public static void delete(int index){
		for(int i=0; i<users.size(); i++){
			User u = users.get(i);
			if(u.getId() == index){
				users.remove(u);
			}
		}
	}
}
