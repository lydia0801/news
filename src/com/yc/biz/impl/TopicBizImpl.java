package com.yc.biz.impl;

import java.util.List;

import com.yc.biz.ITopicBiz;
import com.yc.dao.DBHelper;
import com.yc.po.TopicPO;

public class TopicBizImpl implements ITopicBiz{
	DBHelper db = new DBHelper();
	@Override
	public boolean add(TopicPO po) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TopicPO> findAll() {
		String sql="select * from topic order by tid";
		return db.find(sql, null, TopicPO.class);
	}

	@Override
	public boolean update(TopicPO po) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TopicPO po) {
		// TODO Auto-generated method stub
		return false;
	}

}
