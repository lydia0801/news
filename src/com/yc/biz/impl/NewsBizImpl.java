package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.biz.INewsBiz;
import com.yc.dao.DBHelper;
import com.yc.po.NewsPO;
import com.yc.vo.NewsVO;

public class NewsBizImpl implements INewsBiz {
	DBHelper db =new DBHelper();
	@Override
	public List<NewsPO> findByPage(NewsPO po, Integer pageNo, Integer pageSize) {
		
		return null;
	}

	@Override
	public boolean add(NewsPO po) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(NewsPO po) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据条件分页查看新闻信息  
	 */
	@Override
	public List<NewsVO> findByView(NewsVO vo, Integer pageNo, Integer pageSize) {
		StringBuffer sb=new StringBuffer();
		 
		List<Object> params =new ArrayList<Object>();
		if(null==vo&&null==pageNo&&null==pageSize){//查看所有的新闻信息
			String sql="select * from news_view order by nmodifydate desc";
			sb.append(sql);
		}else if(null==vo&&null!=pageNo&&null!=pageSize){//分页查看所有的新闻信息
			String sql="select * from (select a.*,rownum rn from "+
					"(select * from news_view order by nmodifydate desc,ncreatedate desc)a "+
					"where rownum<="+pageSize*pageNo+") b where rn>"+pageSize*(pageNo-1);   
			sb.append(sql);
		}else{//分页查询 按新闻类型ID查询
			//System.out.println(vo.getTid()+"----------------------");
			String sql="select * from (select a.*,rownum rn from "+
					"(select * from news_view where tid=? order by nmodifydate desc,ncreatedate desc)a "+
					"where rownum<="+pageSize*pageNo+") b where rn>"+pageSize*(pageNo-1);  
			params.add(new Integer(vo.getTid()));   //将int值封装到包转类中 
			sb.append(sql); //将sql语句拼接到sb
		}
		List<NewsVO> list =db.find(sb.toString(), params, NewsVO.class);
		System.out.println(list.size()+"adaf");
		return list;
				
	}

	/**
	 * 统计所有新闻或根据新闻类别名称统计
	 */
	@Override
	public double getTotalCount(NewsVO vo) {
		
		StringBuffer sb=new StringBuffer("select count(*) from news_view");
		List<Object> params =new ArrayList<Object>();
		if(null==vo){
			return db.selectPloymer(sb.toString(), null);
		}else{
			sb.append(" where tid=? ");
			params.add(vo.getTid());
			return db.selectPloymer(sb.toString(), params);
		}
	}

	@Override
	public NewsVO findByNid(NewsVO vo) {
		String sql="select * from news_view where  nid=?";
		List<Object> params =new ArrayList<Object>();
		params.add(vo.getNid());
		List<NewsVO> list =db.find(sql, params, NewsVO.class);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	

}
