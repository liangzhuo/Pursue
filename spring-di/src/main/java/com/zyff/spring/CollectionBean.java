package com.zyff.spring;

import java.util.Set;

public class CollectionBean {
	private Set<String> values;

	public Set<String> getValues() {
		return values;
	}

	public void setValues(Set<String> values) {
		this.values = values;
	}

	public void printValues(){
		for(String s : values){
			System.out.println(s);
		}
	}
	
}
