package com.liang.springmvc.web.controller.contenttype;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class ResponseContentTypeClient {
	public static void jsonRequest() throws IOException, URISyntaxException {
		//请求的地址
		String url = "http://localhost:8091/springmvc-annotation-newfeatures/response/ContentType";
		//创建Http Request(内部使用HttpURLConnection)
		ClientHttpRequest request =
				new SimpleClientHttpRequestFactory().
					createRequest(new URI(url), HttpMethod.POST);
		//设置客户端可接受的媒体类型(即需要什么类型的响应体数据)
		request.getHeaders().set("Accept", "application/json");
		
		ClientHttpResponse response = request.execute();
		Charset charset = response.getHeaders().getContentType().getCharSet();
		InputStream is = response.getBody();
		byte bytes[] = new byte[(int)response.getHeaders().getContentLength()];
		is.read(bytes);
		String jsonData = new String(bytes,charset);
		System.out.println("charset:" + charset + ",json data:" + jsonData);
	}
	
	
	public static void xmlRequest() throws IOException, URISyntaxException {
		//请求的地址
		String url = "http://localhost:8091/springmvc-annotation-newfeatures/response/ContentType";
		//创建Http Request(内部使用HttpURLConnection)
		ClientHttpRequest request =
				new SimpleClientHttpRequestFactory().
					createRequest(new URI(url), HttpMethod.POST);
		//设置客户端可接受的媒体类型(即需要什么类型的响应体数据)
		request.getHeaders().set("Accept", "application/xml");
		
		ClientHttpResponse response = request.execute();
		Charset charset = response.getHeaders().getContentType().getCharSet();
		InputStream is = response.getBody();
		byte bytes[] = new byte[(int)response.getHeaders().getContentLength()];
		is.read(bytes);
		String xmlData = new String(bytes,charset);
		System.out.println("charset:" + charset + ",xml data:" + xmlData);
	}
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		//jsonRequest();;
		xmlRequest();
	}
	
	
}
