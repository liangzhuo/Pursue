package com.zyff.spring;

public class IdRefBean {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void printId(){
		System.out.println("id:"+id);
	}
	
}
