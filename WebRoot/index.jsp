<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="shortcut icon" href="images/favicon.ico" type="images/x-icon" />

	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/login.css" />
	
	<script src="js/jquery-1.9.1.js" charset="utf-8"></script>
	<script src="js/main.js" charset="utf-8"></script>
	<script src="js/login.js" charset="utf-8"></script>
	<script src="js/ajax.js" charset="utf-8"></script>
	
  </head>
  
  <body>
	<jsp:include page="header.jsp"></jsp:include>   
	<div id="main_lydia">
		<jsp:include page="main.jsp"></jsp:include>
	</div>
	<div id="showNews_lydia" style="display: none">
		<jsp:include page="showNews.jsp"></jsp:include>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<jsp:include page="login.jsp"></jsp:include>




  </body>
</html>
