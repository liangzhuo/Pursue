package com.liang.mybatis.inter;

import com.liang.mybatis.model.User;

/**
 * 使用合理描述参数和SQL语句返回值的借口
 * @author liangz
 *
 */
public interface IUserOperation {
	public User selectUserById(int id);
}
