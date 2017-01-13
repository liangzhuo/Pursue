package com.liang.mybatis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 构建SqlSessionFactory
 * @author liangz
 *
 */
public class SessionFactoryUtil {
	private static SqlSessionFactory sqlSessionFactory;
	static{
		try {
			Reader reader = Resources.getResourceAsReader("configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			//String resource = "configuration.xml";
			//InputStream inputStream = Resources.getResourceAsStream(resource);
			//SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession openSession(){
		return sqlSessionFactory.openSession();
	}
	
	public static void closeSession(SqlSession session){
		if(session != null){
			session.close();
		}
	}
}
