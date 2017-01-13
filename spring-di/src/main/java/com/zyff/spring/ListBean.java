package com.zyff.spring;

import java.util.List;

public class ListBean {
	private List<String> values;

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	public void printValues(){
		for(String s : values){
			System.out.println(s);
		}
	}
	
}
