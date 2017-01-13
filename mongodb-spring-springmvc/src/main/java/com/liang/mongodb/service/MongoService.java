package com.liang.mongodb.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.liang.mongodb.model.Customer;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Component("mongoService")
public class MongoService {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public String save(String filePath,String fileName){
		String id = "";
		try {
			GridFS gridFS = getGridFS();
			GridFSInputFile gridFSInputFile = gridFS.createFile(new File(filePath));
			gridFSInputFile.put("comanyId", "anyv");
			gridFSInputFile.put("filename", fileName);
			gridFSInputFile.put("contentType", fileName.substring(fileName.lastIndexOf(".")+1));
			gridFSInputFile.save();
			id = gridFSInputFile.getId().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String save(InputStream input,String fileName){
		String id = "";
		try {
			GridFS gridFS = getGridFS();
			GridFSInputFile gridFSInputFile = gridFS.createFile(input);
			gridFSInputFile.put("comanyId", "anyv");
			gridFSInputFile.put("filename", fileName);
			gridFSInputFile.put("contentType", fileName.substring(fileName.lastIndexOf(".")+1));
			gridFSInputFile.save();
			id = gridFSInputFile.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public GridFSDBFile queryByObjectId(String id){
		GridFS gridFS = getGridFS();
		ObjectId objectId = new ObjectId(id);
		GridFSDBFile gridFSDBFile = gridFS.findOne(objectId);
		return gridFSDBFile;
	}
	
	public List<GridFSDBFile> queryList(){
		Query query = new Query(Criteria.where("firstName").is("liang"));
		List<Customer> customers = mongoTemplate.find(query, Customer.class);
		System.out.println(customers.size());
		//GridFS gridFS = getGridFS();
		//List<GridFSDBFile> gridFSDBFiles = gridFS.find(new BasicDBObject());
		return null;
	}
	
	public void delete(String id){
		GridFS gridFS = getGridFS();
		gridFS.remove(new ObjectId(id));
	}
	
	private GridFS getGridFS(){
		DB db = mongoTemplate.getDb();
		GridFS gridFS = new GridFS(db, "t_file");
		return gridFS;
	}
}
