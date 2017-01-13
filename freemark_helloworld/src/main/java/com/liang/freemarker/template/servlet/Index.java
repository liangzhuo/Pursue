package com.liang.freemarker.template.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Directory;

import com.liang.freemarker.template.client.ProcessClient;
import com.liang.freemarker.util.FreemarkerUtils;

public class Index extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String dirPath = req.getSession().getServletContext().getRealPath("/WEB-INF/html");
		File path = new File(dirPath);
		final String indexFileName = "index.html";
		String[] indexFileList = path.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if(name.equals(indexFileName)){
					return true;
				}
				return false;
			}
		});
		if(indexFileList.length <= 0){
			Writer out = new OutputStreamWriter(new FileOutputStream(dirPath + "/" + indexFileName), "UTF-8");
			ProcessClient.processBody(out);
			req.getRequestDispatcher("/WEB-INF/html/index.html").forward(req, resp);
		}else{
			req.getRequestDispatcher("/WEB-INF/html"+indexFileList[0]).forward(req, resp);
		}
	}
	
	public void init(ServletConfig config){
		String templateDir = config.getInitParameter("templateDir");
		FreemarkerUtils.initConfig(config.getServletContext(), templateDir);
	}
}
