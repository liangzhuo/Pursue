package com.liang.springmvc.model;

public class SchoolInfoModel {
	private String type;//学校类型：高中、中专、大学
	private String name;//学校名称
	private String specialty;//专业
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
}
