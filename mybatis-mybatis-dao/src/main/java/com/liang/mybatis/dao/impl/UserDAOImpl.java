package com.liang.mybatis.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liang.mybatis.dao.IUserDAO;
import com.liang.mybatis.model.Article;
import com.liang.mybatis.model.User;

@Repository
public class UserDAOImpl implements IUserDAO {
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;

	public User selectUserById(int id) {
		try {
			return sqlSessionFactory.getObject().openSession().selectOne("com.liang.mybatis.inter.IUserOperation.selectUserById",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> selectUsers(String username) {
		return null;
	}

	public void addUser(User user) {

	}

	public void updateUser(User user) {

	}

	public void deleteUser(int id) {

	}

	public List<Article> getUserArticles(int id) {
		try {
			return sqlSessionFactory.getObject().openSession().selectList("com.liang.mybatis.inter.IUserOperation.getUserArticles",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
