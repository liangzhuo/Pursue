package com.liang.model;

import org.apache.solr.client.solrj.beans.Field;

public class ZipCode implements ZipCodeSearchable{
	@Field(ID_FIELD)
	private String id;
	@Field(CODE_FIELD)
	private String code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
