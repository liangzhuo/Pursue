package com.liang.mongodb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSDownloadByNameOptions;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;

public class MongoDBGridFSTest {
	private GridFSBucket gridFSBucket = null;
	
	@Before
	public void setUp(){
		String serverIP = "127.0.0.1";
		int port = 27017;
		String username = "liangz";
		String password = "liangz";
		String databaseName = "liangz";
		gridFSBucket = MongoUtil.getGridFSBucket(serverIP,port,username,password,databaseName);
	}
	
	@Test
	public void testCreateGridFSBucket(){
		MongoClient mongoClient = new MongoClient("localhost");
		MongoDatabase database = mongoClient.getDatabase("liangz");
		// use default bucket name "fs"
		GridFSBucket gridFSBucket = GridFSBuckets.create(database);
		// use a custom name "files"
		//GridFSBucket gridFSBucket = GridFSBuckets.create(database, "files");
	}
	
	/**
	 * 上传文件
	 */
	@Test
	public void testUploadGridFS(){
		GridFSUploadOptions options = new GridFSUploadOptions()
									.chunkSizeBytes(1024)
									.metadata(new Document("type","mp4"));
									
		try {
			InputStream uploadStream = new FileInputStream(new File("d:\\t4.mp4"));
			ObjectId fileId = gridFSBucket.uploadFromStream("mongodb-tutorial", uploadStream, options);
			System.out.println(fileId);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 当第一次上传数据到GridFS bucket时，GridFS 将自动创建索引
	 */
	@Test
	public void testOpenUploadStream(){
		GridFSUploadOptions options = new GridFSUploadOptions()
										.chunkSizeBytes(1024)
										.metadata(new Document("type","presentation"));
		byte[] data = "Data to upload into GridFS".getBytes(StandardCharsets.UTF_8);
		GridFSUploadStream uploadStream = gridFSBucket.openUploadStream("sampleData", options);
		uploadStream.write(data);
		uploadStream.close();
		System.out.println("The fileId of upload file is : " + uploadStream.getFileId().toHexString());
	}
	
	@Test
	public void testFindGridFS(){
		Block block = new Block<GridFSFile>() {
			public void apply(GridFSFile gridFSFile) {
				System.out.println(gridFSFile.getFilename());
			}
		};
		//gridFSBucket.find().forEach(block);
		gridFSBucket.find(Filters.eq("metadata.type", "mp4")).forEach(block);
	}
	
	@Test
	public void testDownloadFromStream(){
		try {
			FileOutputStream out = new FileOutputStream(new File("D:\\test.mp4"));
			gridFSBucket.downloadToStream(new ObjectId("5641add020c62b03048696e3"), out);
			out.close();
			System.out.println(out.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDownloadToStreamByName(){
		try {
			FileOutputStream out = new FileOutputStream(new File("D:\\test.mp4"));
			GridFSDownloadByNameOptions downloadOptions = new GridFSDownloadByNameOptions().revision(0);
			gridFSBucket.downloadToStreamByName("mongodb-tutorial", out, downloadOptions);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOpenDownloadStream(){
		GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(new ObjectId("5641ab4020c62b19c4d2269b"));
		int fileLength = (int)downloadStream.getGridFSFile().getLength();
		byte[] bytesToWriteTo = new byte[fileLength];
		downloadStream.read(bytesToWriteTo);
		downloadStream.close();
		System.out.println(new String(bytesToWriteTo, StandardCharsets.UTF_8));
	}
	
	@Test
	public void testOpenDownloadStreamByName(){
		GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStreamByName("sampleData");
		int fileLength = (int)downloadStream.getGridFSFile().getLength();
		byte[] bytesToWriteTo = new byte[fileLength];
		downloadStream.read(bytesToWriteTo);
		downloadStream.close();
		System.out.println(new String(bytesToWriteTo, StandardCharsets.UTF_8));
	}
	
	
	@Test
	public void testRenameFiles(){
		// 重命名只能使用_id
		gridFSBucket.rename(new ObjectId("5641a9bf20c62b18ec5e4770"), "hello");
	}
	
	@Test
	public void testDeleteFiles(){
		gridFSBucket.delete(new ObjectId("5641add020c62b03048696e3"));
	}
	
}
