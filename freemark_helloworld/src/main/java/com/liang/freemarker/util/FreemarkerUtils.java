package com.liang.freemarker.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FreemarkerUtils {
	private static Configuration config = new Configuration();
	
	public static void processTemplate(String templateName, Map<?,?> root, Writer out){
		try {
			Template template = config.getTemplate(templateName, "UTF-8");
			template.process(root, out);
			out.flush();
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	public static void initConfig(ServletContext servletContext, String templateDir){
		config.setLocale(Locale.CHINA);
		config.setDefaultEncoding("UTF-8");
		config.setEncoding(Locale.CHINA, "UTF-8");
		config.setServletContextForTemplateLoading(servletContext, templateDir);
		config.setObjectWrapper(new DefaultObjectWrapper());
	}
}
