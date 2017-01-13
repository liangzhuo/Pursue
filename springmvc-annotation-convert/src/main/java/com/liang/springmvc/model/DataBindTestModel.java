package com.liang.springmvc.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataBindTestModel {
	private String username;
	private boolean bool;//boolean测试
	private List hobbyList;//集合测试，此处可改为数组 set进行测试
	private Map map;//map测试
	private PhoneNumberModel phoneNumber;//string -> 自定义对象的转换测试
	private Date date;//日期类型测试
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public List getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(List hobbyList) {
		this.hobbyList = hobbyList;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public PhoneNumberModel getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(PhoneNumberModel phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "DataBindTestModel [username=" + username + ", bool=" + bool
				+ ", hobbyList=" + hobbyList + ", map=" + map
				+ ", phoneNumber=" + phoneNumber + ", date=" + date + "]";
	}
}
