package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.biz.IAdminBiz;
import com.yc.commons.MD5Encryption;
import com.yc.dao.DBHelper;
import com.yc.po.AdminPO;

public class AdminBizImpl implements IAdminBiz {

	DBHelper db = new DBHelper();

	@Override
	public boolean addAdmin(AdminPO po) {
		String sql = "insert into admin values(aid_seq.nextval,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(po.getAname());
		params.add(MD5Encryption.createPassword(po.getApwd()));// 明文加密后存储到数据库
		int i = db.update(sql, params);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateAdmin(AdminPO po) {
		String sql = "";
		List<Object> params = new ArrayList<Object>();
		sql = "update admin set apwd=? where aid=?";
		params.add(MD5Encryption.createPassword(po.getApwd()));
		params.add(po.getAid());

		int i = db.update(sql, params);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<AdminPO> findByPage(Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer();
		if (null == pageNo && null == pageSize) {
			String sql = "select * from admin";
			sb.append(sql);
		} else if (null != pageNo && null != pageSize) {
			String sql = "select * from (select a.*,rownum rn from "
					+ "(select * from admin   order by aid )a "
					+ "where rownum<=" + pageSize * pageNo + ") b where rn>"
					+ pageSize * (pageNo - 1);
			sb.append(sql);
		}
		return db.find(sb.toString(), null, AdminPO.class);
	}

	@Override
	public AdminPO login(AdminPO po) {
		String sql = "select * from admin where aname=? and apwd=?";
		List<Object> params = new ArrayList<Object>();
		params.add(po.getAname());
		params.add(MD5Encryption.createPassword(po.getApwd()));// 对输入的密码进行加密
		List<AdminPO> list = db.find(sql, params, AdminPO.class);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public double getCount() {
		String sql = "select count(*) from admin";
		return db.selectPloymer(sql, null);
	}

	//删除管理员     一次删除多条记录，所以页面传过来的是aid的字符串拼接 1001,1002,1003
	@Override
	public boolean deleteAdmin(String aids) {
		List<List<Object>> params = new ArrayList<List<Object>>();
		List<String> sqls = new ArrayList<String>();
		List<Object> param =null;
		if(null!=aids&&!"".equals(aids)){
			String [] strs=aids.split(",");
			String sql="delete from admin where aid=?";
			if(strs.length>0){
				for(String aid:strs){
					param = new ArrayList<Object>();
					param.add(aid);
					params.add(param);
					sqls.add(sql);
				}
			}
		}
		try {
			int i = db.doUpdate(sqls, params);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
