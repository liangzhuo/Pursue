package com.liang.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

public class MongoUtil {
	private static MongoClient mongoClient = null;
	private static GridFSBucket gridFSBucket = null;
	
	/**
	 * 获取非认证数据库
	 * @return
	 */
	public static MongoDatabase getMongoDatabase(String databaseName){
		return getMongoClient().getDatabase(databaseName);
	}
	
	/**
	 * 获取非认证MongoClient实例
	 * @return
	 */
	public static MongoClient getMongoClient(){
		if(mongoClient == null){
			MongoClientURI connStr = new MongoClientURI("mongodb://localhost:27017");
			mongoClient = new MongoClient(connStr);
		}
		return mongoClient;
	}
	
	/**
	 * 通过认证获取指定数据库
	 * getDatabase时不创建数据库，当给一个不存在的集合创建索引或插入文档时才会创建数据库
	 * @return
	 */
	public static MongoDatabase getAuthMongoDatabase(String serverIP, int port,String username,String password,String databaseName){
		return getAuthMongoClient(serverIP,port,username,password,databaseName).getDatabase(databaseName);
	}
	
	/**
	 * 获取MongoClient实例
	 * @param serverIP
	 * @param port
	 * @param username
	 * @param password
	 * @param databaseName
	 * @return
	 */
	public static MongoClient getAuthMongoClient(String serverIP, int port,String username,String password,String databaseName){
		if(mongoClient == null){
			ServerAddress serverAddress = new ServerAddress(serverIP,port);
			
			MongoCredential credential = MongoCredential.createScramSha1Credential(
					username,
					databaseName,
					password.toCharArray());
			List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
			credentialList.add(credential);
			//数据库连接池
			mongoClient = new MongoClient(serverAddress,credentialList);
		}
		return mongoClient;
	}
	
	
	public static GridFSBucket getGridFSBucket(String databaseName){
		gridFSBucket = GridFSBuckets.create(mongoClient.getDatabase(databaseName));
		return gridFSBucket;
	}
	
	public static GridFSBucket getGridFSBucket(String serverIP, int port,String username,String password,String databaseName){
		gridFSBucket = GridFSBuckets.create(getAuthMongoDatabase(serverIP,port,username,password,databaseName));
		return gridFSBucket;
	}
	
	/**
	 * 释放资源
	 */
	public static void closeMongoClient(){
		if(mongoClient != null){
			mongoClient.close();
		}
	}
	
	
}
