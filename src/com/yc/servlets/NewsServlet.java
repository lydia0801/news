package com.yc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.yc.biz.INewsBiz;
import com.yc.biz.impl.NewsBizImpl;
import com.yc.commons.JsonUtil;
import com.yc.commons.PageUtil;
import com.yc.vo.NewsVO;
import net.sf.json.JSONArray;

public class NewsServlet extends HttpServlet {
	JSONArray json;
	JSONObject  jb ;
	PrintWriter  out;
	INewsBiz biz = new NewsBizImpl();//实例化
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request,response);
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String op = request.getParameter("op");
		if("findAllPage".equals(op)){
			findAllPage(request,response);
		}else if("findByNid".equals(op)){
			findByNid(request,response);
		}else if("findBackByPage".equals(op)){
			findBackByPage(request,response);
		}
		 
	}
	
	public void findAllPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 //获取当前页数
		int  pageNo = Integer.parseInt(request.getParameter("pageNo"));
		//获取tid值
		String tid =request.getParameter("tid");
		
		PageUtil  pageUtil=new PageUtil();
		pageUtil.setPageNo(pageNo);
		pageUtil.setPageSize(8);
		//声明一个vo的空对象
		NewsVO vo = null;
		//判断tid是否有值
		if(null==tid||"".equals(tid)){//没有传入，则查看所有新闻信息
			pageUtil.setTotalSize((int)biz.getTotalCount(null));
		}else{//传入tid,那就是根据新闻类别ID分页查看数据
			vo = new NewsVO();
			vo.setTid(Integer.parseInt(tid));//将tid设置到vo对象中
			pageUtil.setTotalSize((int)biz.getTotalCount(vo) );
		}
		
		List<NewsVO>  newsList =biz.findByView(vo, pageUtil.getPageNo(), pageUtil.getPageSize());
		//转换json
		json =JSONArray.fromObject(newsList);
		jb = new JSONObject();
		jb.put("newsList", json);
		json=JSONArray.fromObject(pageUtil);
		jb.put("pageUtil", pageUtil);
		out =response.getWriter();
		out.print(jb.toString());	
		
	}
	
	//根据新闻编号查看新闻详情
	public void findByNid(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String nid  = request.getParameter("nid");
		NewsVO vo =new NewsVO ();
		vo.setNid(Integer.parseInt(nid));
		NewsVO newsVO =biz.findByNid(vo);
		json =JSONArray.fromObject(newsVO);
		jb = new JSONObject();
		jb.put("newsVO", json);
		out =response.getWriter();
		out.print(jb.toString());	
	}
		
	
	//后台使用easyui分页操作
	@SuppressWarnings("unused")
	public void findBackByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		NewsVO vo =null;
		List<NewsVO> newsList=null;
		if(null!=vo){
			newsList=biz.findByView(vo, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		}else{
			newsList=biz.findByView(null, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		}
		json = JSONArray.fromObject(newsList);
		jb =new JSONObject();
		jb.put("total", biz.getTotalCount(vo));
		jb.put("rows", json);
		out =response.getWriter();
		out.print(jb.toString());	
	}
	

}
