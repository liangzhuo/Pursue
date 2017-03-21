package com.liang.dbcp;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class MyDBCPDataSource {
	private static Properties properties;
	
	static {
		try {
			properties = new Properties();
			properties.load(MyDBCPDataSource.class.getResourceAsStream("/dbcp.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BasicDataSource getDataSource(){
		if(properties == null){
			return null;
		}
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername(properties.getProperty("username"));
		dataSource.setPassword(properties.getProperty("password"));
		dataSource.setUrl(properties.getProperty("url"));
		dataSource.setDriverClassName(properties.getProperty("driverClassName"));
		dataSource.setDefaultAutoCommit(true);
		
		dataSource.setInitialSize(GenericObjectPoolConfig.DEFAULT_MIN_IDLE);
		dataSource.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL);
		dataSource.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE);
		dataSource.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE);
		dataSource.setMaxWaitMillis(GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS);
		dataSource.setTestOnBorrow(GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW);
		dataSource.setTestOnReturn(GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN);
		dataSource.setTestWhileIdle(GenericObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE);
		
		return dataSource;
	}
}
