package com.yc.biz;

import java.util.List;

import com.yc.po.CommentsPO;

public interface ICommentsBiz {

	/**
	 * 添加评论
	 * @param po
	 * @return
	 */
	public  boolean add(CommentsPO po);
	
	/**
	 * 查看评论
	 * @param po
	 * @return
	 */
	public List<CommentsPO> findComments(CommentsPO po);
	
	/**
	 * 修改评论
	 * @param po
	 * @return
	 */
	public boolean update(CommentsPO po);
	
	/**
	 * 删除评论
	 * @param po
	 * @return
	 */
	public boolean delete(CommentsPO po);
}
