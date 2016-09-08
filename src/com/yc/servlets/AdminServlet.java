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

import com.yc.biz.IAdminBiz;
import com.yc.biz.impl.AdminBizImpl;
import com.yc.po.AdminPO;

public class AdminServlet extends HttpServlet {
	JSONArray json ;
	JSONObject jb;
	PrintWriter out;
	IAdminBiz biz = new AdminBizImpl();
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		 
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String op =request.getParameter("op");
		if("login".equals(op)){
			login(request,response);
		}else if("findBypage".equals(op)){
			findBypage(request,response);
		}else if("updateAdmin".equals(op)){
			updateAdmin(request,response);
		}else if("deleteAdmin".equals(op)){
			deleteAdmin(request,response);
		}else if("addAdmin".equals(op)){
			addAdmin(request,response);
		}
		 
	}
	
	//用户登陆
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String admin_name = request.getParameter("admin_name");
		String admin_pwd= request.getParameter("admin_pwd");
		AdminPO po = new AdminPO();
		po.setAname(admin_name);
		po.setApwd(admin_pwd);
		AdminPO admin =biz.login(po);
		if(null!=admin){//登陆成功
			//将对象存入session中
			request.getSession().setAttribute("admin", admin);
			admin.setMessage("用户登陆成功");
		}else{
			admin = new AdminPO();
			admin.setMessage("用户登陆失败");
		}
		json = JSONArray.fromObject(admin);
		out =response.getWriter();
		out.print(json.toString());
		
	}
	//查看用户信息
	public void findBypage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pageNo = request.getParameter("page");
		String pageSize=request.getParameter("rows");
		System.out.println("aaaaaaaaaaaaaaa");
		List<AdminPO> list =null;
		if(null==pageNo||"".equals(pageNo)||null==pageSize||"".equals(pageSize)){
			list =biz.findByPage(null, null);
		}else{
			list =biz.findByPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		}
		json =JSONArray.fromObject(list);
		jb=new JSONObject();
		jb.put("total", (int)biz.getCount());
		jb.put("rows", json);
		out=response.getWriter();
		out.print(jb.toString());
	}
	
	//修改密码
	public void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String apwd =request.getParameter("apwd");
		String aid =request.getParameter("aid");
		AdminPO admin =new AdminPO();
		admin.setApwd(apwd);
		admin.setAid(Integer.parseInt(aid));
		boolean flag=biz.updateAdmin(admin);
		out =response.getWriter();
		if(flag){//成功
			out.print("1");
		}	
	}
	
	//删除用户
	public void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String aids =request.getParameter("aids"); //获取数据格式： 1001,1002,1003
		String [] strs=aids.split(",");
		boolean flag=biz.deleteAdmin(aids);
		out =response.getWriter();
		if(flag){//成功
			out.print("1");
		}	
	}
	
	//添加管理员
	public void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String aname = request.getParameter("aname");
		String apwd = request.getParameter("apwd");
		AdminPO  po = new AdminPO();
		po.setAname(aname);
		po.setApwd(apwd);
		boolean flag =biz.addAdmin(po);
		out =response.getWriter();
		if(flag){//成功
			out.print("1");
		}	
	}
	 
}
