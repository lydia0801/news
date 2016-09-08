package com.yc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.yc.biz.ITopicBiz;
import com.yc.biz.impl.TopicBizImpl;
import com.yc.commons.JsonUtil;
import com.yc.po.TopicPO;

public class TopicServlet extends HttpServlet {

	ITopicBiz  biz = new TopicBizImpl();//实例化
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request, response);
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//从页面获取op 
		String op = request.getParameter("op");
		if("findAll".equals(op)){//查询所有新闻类别
			findAll(request,response);
		}
	}
	
	
	
	
	//查看所有新闻类别
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<TopicPO> topicList = biz.findAll();
		PrintWriter  out =response.getWriter();
		//实例化jsonUitl
//		JsonUtil  util = new JsonUtil();
//		Object json =util.put("topicList", topicList);
//		//String json = util.toArray(topicList);
//		out.println(json.toString());
		
		JSONArray json = JSONArray.fromObject(topicList);
		JSONObject  jb = new JSONObject();
		jb.put("topicList", json);
		out.print(jb.toString());
	}

}
