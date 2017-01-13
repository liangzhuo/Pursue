package com.liang.springmvc.model;

import javax.validation.constraints.NotNull;


public class UserModel {
	@NotNull(message="{username.not.empty}")
	private String username;
	private String password; 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
