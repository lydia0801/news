package com.yc.biz;

import java.util.List;

import com.yc.po.TopicPO;

public interface ITopicBiz {

	/**
	 * 添加主题
	 * @param po
	 * @return
	 */
	public boolean  add(TopicPO po);
	
	/**
	 * 查看主题
	 * @return
	 */
	public List<TopicPO>  findAll();
	
	/**
	 * 修改主题
	 * @param po
	 * @return
	 */
	public boolean update(TopicPO po);
	
	/**
	 * 删除主题
	 * @param po
	 * @return
	 */
	public boolean delete(TopicPO po);
}
