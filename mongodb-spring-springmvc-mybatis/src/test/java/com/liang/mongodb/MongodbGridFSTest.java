package com.liang.mongodb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/spring-mongodb.xml")
public class MongodbGridFSTest {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private GridFsOperations gridFsTemplate;
	
	
	@Before
	public void setUp(){
		//gridFsTemplate.delete(null);
	}
	
	/*
	@Test
	public void testGridFsTemplateStore(){
		InputStream  inputStream = null;
		try {
			inputStream = new FileInputStream(new File("D:\\t4.mp4"));
			DBObject dbObject = new BasicDBObject("key", "value");
			GridFSFile gridFSFile = gridFsTemplate.store(inputStream, "t4.mp4", "mp5", dbObject);
			System.out.println(gridFSFile.getId().toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void testGridFsTemplateRead(){
		Query query = new Query().addCriteria(Criteria.where("filename").is("t4.mp4"));
		List<GridFSDBFile> gridFSFiles = gridFsTemplate.find(query);
		for(GridFSDBFile file : gridFSFiles){
			try {
				System.out.println(file.getFilename());
				System.out.println(file.getContentType());
				file.writeTo("d:\\test.mp4");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testMongoTemplateStore(){
		String collectionName = "t_file";
		File file = new File("d:\\t5.mp4");
		String companyId = "anyv1";
		String fileName = "t5.mp4";
		try {
			DB db = mongoTemplate.getDb();
			GridFS gridFS = new GridFS(db, collectionName);
			GridFSInputFile gridFSInputFile = gridFS.createFile(file);
			gridFSInputFile.put("_id", new ObjectId("5642dcd020c62b1d2810425b"));
			gridFSInputFile.put("aliases", companyId);
			gridFSInputFile.put("filename", fileName);
			gridFSInputFile.put("contentType", fileName.substring(fileName.lastIndexOf(".")+1));
			gridFSInputFile.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testMongoTemplateRead(){
		String collectionName = "t_file";
		String fileName = "t5.mp4";
		DB db = mongoTemplate.getDb();
		GridFS gridFS = new GridFS(db, collectionName);
		GridFSFile gridFSDBFile = gridFS.findOne(fileName);
		System.out.println(gridFSDBFile.getLength());
	}
	*/
	
	
}
