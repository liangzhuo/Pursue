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

public class RoleTest {
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
	public void testHasRole() {
		login("classpath:shiro-role.ini", "liang", "123");
		// 判断是否有角色：role1
		Assert.assertTrue(subject.hasRole("role1"));
		// 判断是否拥有角色：role1和role2
		Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
		boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
		// 判断拥有角色 role1 role2 role3
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(false, result[2]);
	}
	
	@Test(expected = AuthorizationException.class)
	public void testCheckRole() {
		login("classpath:shiro-role.ini", "liang", "123");
		// 断言拥有角色 role1
		subject.checkRole("role1");
		// 断言拥有角色 role1 和 role3, 失败抛出异常
		subject.checkRoles(Arrays.asList("role1", "role3"));
	}
}
