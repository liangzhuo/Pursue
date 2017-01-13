package com.liang.util;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;


public class SolrUtil {
	private static SolrServer client;
	private static final String URL = "http://192.168.0.242:8080/solr/test";
	
	private SolrUtil() {
		
	}
	
	static {
		client = new HttpSolrServer(URL);
	}
	
	public static SolrServer getSolrClient() {
		synchronized(SolrUtil.class){
			if(client == null) {
				client = new HttpSolrServer(URL);
			}
		}
		return client;
	}
}
