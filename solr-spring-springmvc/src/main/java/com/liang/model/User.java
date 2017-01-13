package com.liang.model;

import org.apache.solr.client.solrj.beans.Field;

public class User implements UserSearchable{
	@Field(ID_FIELD)
	private String id;
	@Field(NAME_FIELD)
	private String name;
	@Field(FIRST_NAME_FIELD)
	private String firstName;
	@Field(LAST_NAME_FIELD)
	private String lastName;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}
