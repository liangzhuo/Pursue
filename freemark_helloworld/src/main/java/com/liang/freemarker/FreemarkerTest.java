package com.liang.freemarker;

import java.io.File;
import java.io.IOException;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public class FreemarkerTest {
	public static void main(String[] args) throws IOException {
		FileTemplateLoader ftl1 = new FileTemplateLoader(new File("D:\\workspace_personal\\freemark_helloworld\\src\\main\\webapp\\WEB-INF\\ftl"));
		FileTemplateLoader ftl2 = new FileTemplateLoader(new File("D:\\workspace_personal\\freemark_helloworld\\src\\main\\webapp\\WEB-INF\\ftl"));
		ClassTemplateLoader ct1 = new ClassTemplateLoader(FreemarkerTest.class,"");
		TemplateLoader[] loaders = new TemplateLoader[]{ftl1, ftl2, ct1};
		MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(mtl);
	}
}
