package com.liang.shiro.authorization;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class PermissionTest {
	private Subject subject;
	
	private void login(String configFile, String username, String password) {
		// 1.获取 SecurityManage工厂，此处使用 ini 配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		
		// 2.得到 SecurityManager 实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		// 3.得到 Subject 及创建用户名/密码身份验证 Token （即可用户身份/凭证）
		subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		subject.login(token);
	}
	
	@Test
	public void testHasPermitted() {
		login("classpath:shiro-permission.ini", "liang", "123");
		// 判断拥有角色：user:create
		Assert.assertTrue(subject.isPermitted("user:create"));
		// 判断拥有角色 user:update user:delete
		Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
		// 判断没有角色 user:view
		Assert.assertFalse(subject.isPermitted("user:view"));
	}

	@Test(expected = AuthorizationException.class)
	public void testCheckPermission() {
		login("classpath:shiro-permission.ini", "liang", "123");
		// 断言拥有权限 user:create
		subject.checkPermission("user:create");
		// 断言拥有权限 user:delete user:update
		subject.checkPermissions("user:update", "user:delete");
		// 断言拥有权限 user:view, 没有则抛出异常
		subject.checkPermission("user:view");
	}
	
}
