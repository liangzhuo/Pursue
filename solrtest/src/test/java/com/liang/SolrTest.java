package com.liang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import com.liang.model.Item;
import com.liang.util.SolrUtil;


public class SolrTest {
	private SolrServer solrClient;
	
	@Before
	public void init() {
		solrClient = SolrUtil.getSolrClient();
	}
	
	/**
	 * 获取SolrClient
	 * 方法1
	 */
	@Test
	public void solrClientTest() {
		try {
			String url = "http://192.168.0.242:8983/solr/test";
			/**
			 * 尽量使用相同的实例
			 */
			HttpSolrServer client = new HttpSolrServer(url);
			// 禁止TCP
			client.setConnectionTimeout(5000);
			// 用来跨版本兼容，其它情况不用
			client.setParser(new XMLResponseParser());
			// socket read time,一般不需要使用
			client.setSoTimeout(1000);
			// 设置每个主机的最大连接数，超过则不允许
			client.setDefaultMaxConnectionsPerHost(100);
			// 设置给定时间的最大连接数
			client.setMaxTotalConnections(100);
			// 设置客户端是否重定向，默认值false
			client.setFollowRedirects(false);
			// 是否允许压缩，如果服务端允许压缩，客户端才可设置
			client.setAllowCompression(false);
			
			QueryResponse resp = client.query(new SolrQuery("*:*"));
			System.out.println(resp.toString());
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取SolrClient
	 * 方法2
	 */
	@Test
	public void solrClientTest1() {
		try {
			String url = "http://192.168.0.242:8983/solr/test";
			SolrServer  client = new HttpSolrServer(url);
			QueryResponse resp = client.query(new SolrQuery("*:*"));
			System.out.println(resp.toString());
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteAllTest() throws SolrServerException, IOException {
		// 删除指定集合的文档
		solrClient.deleteByQuery("*:*");
		// 显示提交
		solrClient.commit();
	}
	
	/**
	 * 普通方式增加文档
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void addTest() throws SolrServerException, IOException {
		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("id", "id1", 1.0f);
		doc1.addField("name", "doc1", 1.0f);
		doc1.addField("price", 10);
		
		SolrInputDocument doc2 = new SolrInputDocument();
		doc2.addField("id", "id2", 1.0f);
		doc2.addField("name", "doc2", 1.0f);
		doc2.addField("price", 20);
		
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		docs.add(doc1);
		docs.add(doc2);
		
		
		solrClient.add(docs);
		// 提交需要指明Collection
		solrClient.commit();
		
		/**
		 * 增加文档后立刻提交
		 */
		/*
		UpdateRequest req = new UpdateRequest();
		req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
		req.add( docs );
		UpdateResponse rsp = req.process( solrClient );
		*/
	}
	
	@Test
	public void updateTest() throws SolrServerException, IOException {
		SolrInputDocument doc3 = new SolrInputDocument();
		doc3.addField("id", "id3", 1.0f);
		//doc3.addField("name", "doc3", 1.0f);
		//doc3.addField("price", 30);
		doc3.addField("testfield", 30);
		
		SolrInputDocument doc4 = new SolrInputDocument();
		doc4.addField("id", "id4", 1.0f);
		//doc4.addField("name", "doc4", 1.0f);
		//doc4.addField("price", 40);
		
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		docs.add(doc3);
		docs.add(doc4);
		
		solrClient.add(docs);
		solrClient.commit();
	}
	
	/**
	 * 增加POJO，有利于简化代码
	 * @throws IOException
	 * @throws SolrServerException
	 */
	@Test
	public void addPOJO() throws IOException, SolrServerException {
		Item item = new Item();
		item.setId("one");
		item.setCategories(new String[]{ "aaa", "bbb", "ccc" });
		solrClient.addBean(item);
		solrClient.commit();
	}
	
	/**
	 * 可直接读数据库数据写入Solr
	 * 这种方式目前不是最好的，研究解决方案
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	
	@Test
	public void selectTest() throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.setQuery( "id:one" );
		//query.addSort( "price", SolrQuery.ORDER.asc );
		
		QueryResponse response = solrClient.query(query);
		SolrDocumentList docs = response.getResults();
		System.out.println(docs);
		
		List<Item> beans = response.getBeans(Item.class);
		for(Item item : beans) {
			System.out.println(item.getId());
		}
	}
	
	
}
