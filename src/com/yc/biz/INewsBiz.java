package com.yc.biz;

import java.util.List;

import com.yc.po.NewsPO;
import com.yc.vo.NewsVO;

public interface INewsBiz {

	/**
	 * 查询新闻
	 * @param po
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<NewsPO> findByPage(NewsPO po ,Integer pageNo,Integer pageSize);
	
	/**
	 * 添加新闻
	 * @param po
	 * @return
	 */
	public boolean add(NewsPO po);
	
	/**
	 * 修改数据操作
	 * @param po
	 * @return
	 */
	public boolean update(NewsPO po);

	/**
	 * 
	 * @param vo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<NewsVO> findByView(NewsVO vo, Integer pageNo, Integer pageSize);

	public double getTotalCount(NewsVO vo);

	public NewsVO findByNid(NewsVO vo);
}
