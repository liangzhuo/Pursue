package com.liang.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPTest {
	public static void main(String[] args){
		BasicDataSource dataSource = MyDBCPDataSource.getDataSource();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery("select user from user");
			while(rs.next()){
				String username = rs.getString(1);
				System.out.println(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(statement != null){
					statement.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
