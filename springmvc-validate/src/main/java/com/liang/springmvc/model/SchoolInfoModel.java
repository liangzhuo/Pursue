package com.liang.springmvc.model;

public class SchoolInfoModel {
	private String type;
	private String name;
	private String specialty;
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
	@Override
	public String toString() {
		return "SchoolInfoModel [type=" + type + ", name=" + name
				+ ", specialty=" + specialty + "]";
	}
	
	
	
}
