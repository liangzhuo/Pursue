package com.liang.springmvc.web.controller.contenttype;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class RequestContentTypeClient {
	public static void main(String[] args) throws IOException, URISyntaxException {
		String url = "http://localhost:8091/springmvc-annotation-newfeatures/request/ContentType";
		ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(url), HttpMethod.POST);
		request.getHeaders().set("Content-Type", "application/json;charset=gbk");
		String jsonData = "{\"username\":\"zhang\",\"password\":\"123\"}";
		request.getBody().write(jsonData.getBytes("gbk"));
		ClientHttpResponse response = request.execute();
		System.out.println(response.getStatusCode());
	}
}
