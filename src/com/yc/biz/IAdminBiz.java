package com.yc.biz;

import java.util.List;

import com.yc.po.AdminPO;

public interface IAdminBiz {

	public boolean addAdmin(AdminPO po);
	
	public boolean updateAdmin(AdminPO po);
	
	public List<AdminPO> findByPage(Integer pageNo,Integer pageSize);
	
	public AdminPO login(AdminPO po);
	public double getCount();
	
	public boolean deleteAdmin(String aids);
}
