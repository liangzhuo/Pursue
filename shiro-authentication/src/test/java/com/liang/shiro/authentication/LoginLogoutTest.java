package com.liang.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class LoginLogoutTest {
	
	private void login(String configFile, String username, String password) {
		// 1.获取 SecurityManage工厂，此处使用 ini 配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		// 2.得到 SecurityManager 实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		// 3.得到 Subject 及创建用户名/密码身份验证 Token （即可用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		subject.login(token);
	}
	
	@Test
	public void testHelloWorld() {
		// 1.获取 SecurityManage工厂，此处使用 ini 配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		// 2.得到 SecurityManager 实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		// 3.得到 Subject 及创建用户名/密码身份验证 Token （即可用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("liang", "123");
		
		try {
			// 4.登录，身份认证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5.身份验证失败
		}
		
		// 断言用户已经登录
		Assert.assertEquals(true, subject.isAuthenticated());
		
		// 6.退出
		subject.logout();
	}
}
