package com.liang.shiro.authertication;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {

	@Override
	public String getName() {
		return "MyRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持 UsernamePasswordToken 类型的 Token
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal(); // 得到用户名
		String password = new String((char[])token.getCredentials());
		if (!"liang".equals(username)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		// 如果身份验证成功， 返回一个 AuthenticationInfo 实现
		return new SimpleAuthenticationInfo(username, password, getName());
	}
	

}
