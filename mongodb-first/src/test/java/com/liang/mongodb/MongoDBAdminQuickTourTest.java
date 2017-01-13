package com.liang.mongodb;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;

public class MongoDBAdminQuickTourTest {
	private MongoClient mongoClient = null;
	private MongoDatabase database = null;
	
	@Before
	public void setUp(){
		String serverIP = "127.0.0.1";
		int port = 27017;
		String username = "liangz";
		String password = "liangz";
		String databaseName = "liangz";
		mongoClient = MongoUtil.getAuthMongoClient(serverIP,port,username,password,databaseName);
		database = MongoUtil.getAuthMongoDatabase(serverIP, port, username, password, databaseName);
	}
	
	@Test
	public void testDatabases(){
		for(String name : mongoClient.getDatabaseNames()){
			System.out.println(name);
		}
	}
	
	@Test
	public void testDropDatabase(){
		mongoClient.getDatabase("liangz").drop();
	}
	
	/**
	 * 集合在插入文档时自动创建
	 * 手动创建可以自定义它的配置
	 * 
	 */
	@Test
	public void testCreateCollection(){
		database.createCollection(
						   "cappedCollection", 
							new CreateCollectionOptions().capped(true).sizeInBytes(0x100000));
	}
	
	@Test
	public void testGetCollections(){
		for(String name : database.listCollectionNames()){
			System.out.println(name);
		}
	}
	
	@Test
	public void testDropCollection(){
		MongoCollection<Document> collection = database.getCollection("cappedCollection");
		collection.drop();
	}
	
	/**
	 * MongoDB支持二级索引
	 * 创建索引指定 字段或组合字段
	 * 1:ascending
	 * -1:descending
	 */
	@Test
	public void testCreateIndex(){
		MongoCollection<Document> collection = database.getCollection("test");
		collection.createIndex(new Document("i",1));
	}
	
	@Test
	public void testGetCollectionIndexs(){
		MongoCollection<Document> collection = database.getCollection("test");
		for(final Document index : collection.listIndexes()){
			System.out.println(index.toJson());
		}
	}
	
	@Test
	public void testTextIndex(){
		MongoCollection<Document> collection = database.getCollection("test");
		collection.createIndex(new Document("content","text"));
	}
	
	/**
	 * 关于索引查询需要仔细研究
	 */
	@Test
	public void testTextIndex1(){
		MongoCollection<Document> collection = database.getCollection("test");
//		collection.insertOne(new Document("_id", 0).append("content", "textual content"));
//		collection.insertOne(new Document("_id", 1).append("content", "additional content"));
//		collection.insertOne(new Document("_id", 2).append("content", "irrelevant content"));
		
		long matchCount = collection.count(Filters.text("textual content -irrelevant"));
		System.out.println(matchCount);
		
		Bson textSearch = Filters.text("textual content -irrelevant", "english");
		matchCount = collection.count(textSearch);
		System.out.println("Text search matches (english): " + matchCount);
		
		Document projection = new Document("score",new Document("$meta","textScore"));
		Document myDoc = collection.find(textSearch).projection(projection).first();
		System.out.println("Highest scoring document: " + myDoc.toJson());
	}
	
	@Test
	public void testRunCommand(){
		Document buildInfo = database.runCommand(new Document("buildInfo",1));
		System.out.println(buildInfo);
	}
}
