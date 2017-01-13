package com.liang.model;

import org.apache.solr.client.solrj.beans.Field;

public class User implements SeachableUser {
	@Field(ID_FIELD)
	private String id;
	@Field(NAME_FIELD)
	private String name;
	@Field(ADDRESS_FIELD)
	private String address;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address
				+ "]";
	}
}
