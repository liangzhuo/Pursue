package com.liang.freemarker.template.client;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liang.freemarker.ftl.model.Footer;
import com.liang.freemarker.ftl.model.Header;
import com.liang.freemarker.model.User;
import com.liang.freemarker.service.FooterService;
import com.liang.freemarker.service.HeaderService;
import com.liang.freemarker.service.UserService;
import com.liang.freemarker.util.FreemarkerUtils;

public class ProcessClient {
	private static Map<String, Object> root = new HashMap<String, Object>();
	
	public static void processBody(Writer out){
		Header h = HeaderService.getHeader();
		root.put("h", h);
		Footer f = FooterService.getFooter();
		root.put("f", f);
		List<User> users = UserService.getUsers();
		FreemarkerUtils.processTemplate("body.ftl", root, out);
	}
}
