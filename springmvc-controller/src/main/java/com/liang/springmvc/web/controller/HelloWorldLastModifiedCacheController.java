package com.liang.springmvc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

public class HelloWorldLastModifiedCacheController extends AbstractController implements LastModified{
	private long lastModified;
	/**
	 * 通过response写响应
	 * 1.对于一般的跳转如链接跳转、通过js调用window.open打开新页面使用浏览器缓存
	 * 	 在未过期下直接使用浏览器缓存
	 * 2.对于刷新页面如F5刷新，会再发一次请求到服务器
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.getWriter().write("<a href=''>this</a>");
		return null;
	}

	public long getLastModified(HttpServletRequest request) {
		if(lastModified == 0L){
			lastModified = System.currentTimeMillis();
		}
		return lastModified;
	}
	
}
