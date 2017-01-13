package com.liang.springmvc.web.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResponseContentTypeController {
	@RequestMapping(value="/response/ContentType")
	public void response1(HttpServletResponse response) throws IOException{
		//表示响应的内容区数据的媒体类型为html格式，且编码为utf8（客户端应该以utf-8解码）
		response.setContentType("text/html;charset=utf-8");
		//写出响应体内容
		response.getWriter().write("<font style='color:red'></font>");
	}
	
	@RequestMapping(value="/response/ContentType",headers="Accept=application/json")
	public void response2(HttpServletResponse response) throws IOException{
		//表示响应的内容区数据的媒体类型为json格式，且编码为utf-8（客户端应以utf-8解码）
		response.setContentType("application/json;charset=utf-8");
		String jsonData = "{\"username\":\"liangz\",\"password\":\"123\"}";
		//写出响应体内容
		response.getWriter().write(jsonData);
	}
	
	@RequestMapping(value="/response/ContentType",headers="Accept=application/xml")
	public void response3(HttpServletResponse response) throws IOException{
		response.setContentType("application/xml;charset=utf-8");
		String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
		xmlData += "<user><username>liangzhuo</username><password>123</password></user>";
		response.getWriter().write(xmlData);
	}
}
