package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.biz.IUsersBiz;
import com.yc.commons.MD5Encryption;
import com.yc.dao.DBHelper;
import com.yc.po.UsersPO;

public class UsersBizImpl implements IUsersBiz{
    DBHelper db = new DBHelper();
	@Override
	public UsersPO login(UsersPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsersPO> findByPage(UsersPO po, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UsersPO po) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(UsersPO po) {
		String sql="insert into  users(uid_seq.nextval,?,?,?)";
		String pwd =po.getUpwd();
		List<Object>  params = new ArrayList<Object>();
		 params.add(po.getUname());
		 params.add(MD5Encryption.createPassword(pwd));
		 params.add(po.getEmail());
		int i=db.update(sql, params);
		if(i>0){
			return true;
		}
		return false;
	}

}
