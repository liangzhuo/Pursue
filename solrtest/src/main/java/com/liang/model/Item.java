package com.liang.model;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Item {
	@Field
	private String id;
	@Field
	private String[] categories;
	@Field
	private List<String> features;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

}
