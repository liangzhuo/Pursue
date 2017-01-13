package com.liang.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface IBaseDAO<T> {
	T selectById(int id);
	
	List<T> getList(T obj);
	
	void add(T obj);
	
	void update(T obj);
	
	void deleteById(int id);
	
	List<T> getPageList(Map<String,String> parameters, RowBounds rowBounds);
}
