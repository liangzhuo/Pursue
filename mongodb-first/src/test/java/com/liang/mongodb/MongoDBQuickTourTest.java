package com.liang.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.Block;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoDBQuickTourTest {
	private MongoDatabase database = null;
	
	@Before
	public void setUp() {
		String serverIP = "127.0.0.1";
		int port = 27017;
		String username = "liangz";
		String password = "liangz";
		String databaseName = "liangz";
		database = MongoUtil.getAuthMongoDatabase(serverIP,port,username,password,databaseName);
	}
	
	@After
	public void destory(){
		MongoUtil.closeMongoClient();
	}
	
	@Test
	public void testCollection(){
		MongoCollection<Document> collection = database.getCollection("test");
		Document document = collection.find().first();
		System.out.println(document.get("content"));
	}
	
	@Test
	public void testInsert(){
		MongoCollection<Document> collection = database.getCollection("test");
		Document doc = new Document("name", "MongoDB")
					        .append("type", "database")
					        .append("count", 1)
					        .append("info", new Document("x", 203).append("y", 102));
		collection.insertOne(doc);
	}
	
	@Test
	public void testManyInsert(){
		MongoCollection<Document> collection = database.getCollection("test");
		List<Document> documentList = new ArrayList<Document>();
		for(int i=10; i<100; i++){
			documentList.add(new Document("i",i));
		}
		collection.insertMany(documentList);
	}
	
	@Test
	public void testDocumentCount(){
		MongoCollection<Document> collection = database.getCollection("test");
		System.out.println(collection.count());
	}
	
	/**
	 * "_"和"$"是MongoDB的保留字段,MongoDB内部使用
	 */
	@Test
	public void testQueryFirstDocument(){
		MongoCollection<Document> collection = database.getCollection("test");
		Document document = collection.find().first();
		System.out.println(document.toJson());
	}
	
	@Test
	public void testQueryAllDocuments(){
		MongoCollection<Document> collection = database.getCollection("test");
		MongoCursor<Document> cursor = collection.find().iterator();
		while(cursor.hasNext()){
			System.out.println(cursor.next().toJson());
		}
	}
	
	/**
	 * 不推荐用此方法
	 */
	@Test
	public void testQueryAllDocuments1(){
		MongoCollection<Document> collection = database.getCollection("test");
		for(Document cur : collection.find()){
			System.out.println(cur.toJson());
		}
	}
	
	/**
	 * 可通过Filters,Sorts,Projections辅助类进行查询语句的编写
	 */
	@Test
	public void testQueryFilter(){
		MongoCollection<Document> collection = database.getCollection("test");
		Document document = collection.find(Filters.eq("i", 71)).first();
		System.out.println(document.toJson());
	}
	
	@Test
	public void testQueryDocuments(){
		MongoCollection<Document> collection = database.getCollection("test");
		Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(final Document document) {
				System.out.println(document.toJson());
			}
		};
		collection.find(Filters.gt("i", 50)).forEach(printBlock);
		System.out.println("####################");
		collection.find(
						Filters.and(Filters.gt("i", 50),Filters.lte("i", 100))
						).forEach(printBlock);
	}
	
	/**
	 * Sorts
	 */
	@Test
	public void testSortDocuments(){
		MongoCollection<Document> collection = database.getCollection("test");
		Document document = collection.find(Filters.exists("i")).sort(Sorts.descending("i")).first();
		System.out.println(document.toJson());
	}
	
	/**
	 * 映射字段，只取文档中的部分字段
	 * Projections
	 */
	@Test
	public void testProjectDocuments(){
		MongoCollection<Document> collection = database.getCollection("test");
		Document document = collection.find().projection(Projections.excludeId()).first();
		System.out.println(document.toJson());
	}
	
	@Test
	public void testUpdateDocument(){
		MongoCollection<Document> collection = database.getCollection("test");
		collection.updateOne(Filters.eq("i", 10), new Document("$set", new Document("i",110)));
	}
	
	@Test
	public void testUpdateDocuments(){
		MongoCollection<Document> collection = database.getCollection("test");
		UpdateResult updateResult = collection.updateMany(Filters.lt("i", 100), new Document("$inc",new Document("i",100)));
		System.out.println(updateResult.getModifiedCount());
	}
	
	@Test
	public void testDeleteDocument(){
		MongoCollection<Document> collection = database.getCollection("test");
		DeleteResult deleteResult = collection.deleteOne(Filters.eq("i", 110));
		System.out.println(deleteResult.getDeletedCount());
	}
	
	/**
	 * 按顺序执行的批量操作
	 * 不推荐在MongoDB 2.6之前的版本使用
	 */
	@Test
	public void testOrderBulkOperation(){
		MongoCollection<Document> collection = database.getCollection("test");
		BulkWriteResult bulkWriteResult = collection.bulkWrite(
				  Arrays.asList(new InsertOneModel<Document>(new Document("_id", 4)),
				                new InsertOneModel<Document>(new Document("_id", 5)),
				                new InsertOneModel<Document>(new Document("_id", 6)),
				                new UpdateOneModel<Document>(new Document("_id", 1),
				                                     new Document("$set", new Document("x", 2))),
				                new DeleteOneModel<Document>(new Document("_id", 2)),
				                new ReplaceOneModel<Document>(new Document("_id", 3),
				                                      new Document("_id", 3).append("x", 4))));
		bulkWriteResult.getModifiedCount();
	}
	
	/**
	 * 不推荐在MongoDB 2.6之前的版本使用
	 */
	@Test
	public void testUnorderBulkOperation(){
		MongoCollection<Document> collection = database.getCollection("test");
		BulkWriteResult bulkWriteResult = collection.bulkWrite(
				  Arrays.asList(new InsertOneModel<Document>(new Document("_id", 4)),
				                new InsertOneModel<Document>(new Document("_id", 5)),
				                new InsertOneModel<Document>(new Document("_id", 6)),
				                new UpdateOneModel<Document>(new Document("_id", 1),
				                                     new Document("$set", new Document("x", 2))),
				                new DeleteOneModel<Document>(new Document("_id", 2)),
				                new ReplaceOneModel<Document>(new Document("_id", 3),
				                                      new Document("_id", 3).append("x", 4))),
				  new BulkWriteOptions().ordered(false));
		System.out.println(bulkWriteResult.getInsertedCount());
		System.out.println(bulkWriteResult.getDeletedCount());
		System.out.println(bulkWriteResult.getMatchedCount());
		System.out.println(bulkWriteResult.getModifiedCount());
	}
}
