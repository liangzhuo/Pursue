package com.liang.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.repository.support.SolrRepositoryFactory;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

/**
 * 推荐使用该类进行仓库的实例化
 * @author liangz
 *
 */
@Configuration
@PropertySource("classpath:solr.properties")
@EnableSolrRepositories(basePackages="com.liang.repository")
public class SolrConfiguration {
	private static final String SOLR_SERVER_URL = "solr.host";
	@Resource
	private Environment environment;
	
	@Bean
	public SolrOperations solrTemplate() throws ParserConfigurationException, IOException, SAXException {
		return new SolrTemplate(solrServer());
	}

	@Bean
	public SolrServer solrServer() {
	    return new HttpSolrServer(environment.getRequiredProperty(SOLR_SERVER_URL));
	}
	
}
