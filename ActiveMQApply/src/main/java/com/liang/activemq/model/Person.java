package com.liang.activemq.model;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 2028137916636393099L;
	private String id;
	private String name;
	public String getId() {

		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
