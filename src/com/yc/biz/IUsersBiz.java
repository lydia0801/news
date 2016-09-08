package com.yc.biz;

import java.util.List;

import com.yc.po.UsersPO;

public interface IUsersBiz {

	/**
	 * 用户登录
	 * @param po
	 * @return
	 */
	public  UsersPO login(UsersPO po);
	
	/**
	 * 查询用户信息
	 * @param po
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<UsersPO> findByPage(UsersPO po ,Integer pageNo,Integer pageSize);
	
	/**
	 * 修改用户信息
	 * @param po
	 * @return
	 */
	public  boolean update(UsersPO po);
	
	/**
	 * 添加用户信息||注册用户信息
	 * @param po
	 * @return
	 */
	public boolean register(UsersPO po);
}
