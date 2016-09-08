//查看新闻类别
function findAll(){
	   var xmlHttp=createXMLHttpRequest();
	   //状态发生改变时调用handleStateChange
	   xmlHttp.onreadystatechange=function(){
	   	//回调函数
	   	if(xmlHttp.readyState ==4){
	     	if(xmlHttp.status ==200){//服务器响应正常
	     		 var data =xmlHttp.responseText;
	     		 //console.info(data);
	     		 var json = eval("("+data+")");
	     		 var topic =json.topicList;
	     		 var div =document.getElementById("class_month");
	     		 for(var i=0;i<topic.length;i++){
	     		 	createA(div,topic[i].tid,topic[i].tname);
	     		 }
	     		 return data;
	     	}
	     }
	   } 
	 	xmlHttp.open("POST", "topic.do?op=findAll", true);
	 	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 	xmlHttp.send(null);
	}
	
	//创建a标签
	function createA(obj,tid,tname){
//		var a = "<a href=\"javascript:void(0)\" onclick=\"findByTypeId("+tid+")\">"+tname+"</a>";
//	 	var span = document.createElement("span");
//	 	span.href="javascript:void(0)";
//	 	span.innerHTML=a;
		//第二种方式：
//		var a = document.createElement("a");
//		a.href="javascript:findByTypeId("+tid+")";
//		a.innerHTML=tname;
//		obj.appendChild(a);
		//第三种： jQuery
		//console.info($(obj));
		//在根据新闻类别ID分页查看新闻信息调用findNews(1,tid)
		$(obj).append("<a href=\"javascript:findNews(1,"+tid+")\">"+tname+"</a>");
	}
	
	
	

	window.onload=function(){//页面加载时执行
		findAll();//新闻类别
		findNews();//新闻信息
	}
		
	/**
	 * 查看新闻信息
	 */
	function findNews(){
		//清空新闻信息
		$("#newsList_lydia").html("");
		var xmlHttp= createXMLHttpRequest();
		var arg =arguments;
		xmlHttp.onreadystatechange=function(){
			//回调函数
		   	if(xmlHttp.readyState ==4){
		     	if(xmlHttp.status ==200){//服务器响应正常
		     		 var data =xmlHttp.responseText;
		     		 var json = eval("("+data+")");
		     		var news =json.newsList;
		     		var pages =json.pageUtil;
		     		for(var i=0;i<news.length;i++){
		     			var li ="<li><a href=\"javascript:findByNid("+news[i].nid+")\">"+news[i].ntitle+"</a><span>"+news[i].ncreatedate+"</span></li>";	     	
		     			$("#newsList_lydia").append(li);
		     			if((i+1)%5==0){
		     				var li2 ="<li class=\"space\"></li>";
		     				$("#newsList_lydia").append(li2);
		     			} 
		     		}
		     		//添加分页  判断findNews是否传入了两个参数
		     		if(arg.length==2){alert(arg.length+"-----"+arg[1]);
		     			var li3 ="<li class=\"space\"><span>当前页数：["+pages.pageNo+"/"+pages.totalPages+"] <a href=\"javascript:findNews("+pages.nextPageNo+","+arg[1]+")\">下一页</a> <a href=\"javascript:findNews("+pages.totalPages+")\">末页</a></span></li>";
			     		$("#newsList_lydia").append(li3);
		     		}else{
		     			var li3 ="<li class=\"space\"><span>当前页数：["+pages.pageNo+"/"+pages.totalPages+"] <a href=\"javascript:findNews("+pages.nextPageNo+")\">下一页</a> <a href=\"javascript:findNews("+pages.totalPages+")\">末页</a></span></li>";
			     		$("#newsList_lydia").append(li3);
		     			
		     		}
		     		
		     	}
		   	}
		};
		//传入参数判断
		if(arg.length==1){//传入的是pageNo
			xmlHttp.open("POST","news.do?op=findAllPage&pageNo="+arg[0],true);
			
		}else if(arg.length==2){//传入的是pageNo,tid
			xmlHttp.open("POST","news.do?op=findAllPage&pageNo="+arg[0]+"&tid="+arg[1],true);
		}else{
			xmlHttp.open("POST","news.do?op=findAllPage&pageNo=1",true);
		}
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 	xmlHttp.send(null); 
		
	}
	
	//查看新闻详情
	function findByNid(nid){
		var xmlHttp=createXMLHttpRequest();
		   //状态发生改变时调用handleStateChange
		   xmlHttp.onreadystatechange=function(){
		   	//回调函数
		   	if(xmlHttp.readyState ==4){
		     	if(xmlHttp.status ==200){//服务器响应正常
		     		 var data =xmlHttp.responseText;
		     		 //console.info(data);
		     		 var json = eval("("+data+")");
		     		 var news =json.newsVO[0];
		     		 console.info(news);
		     		 showNews(news);
		     		 
		     		 
		     		 return data;
		     	}
		     }
		   } ;
		 	xmlHttp.open("POST", "news.do?op=findByNid&nid="+nid, true);
		 	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		 	xmlHttp.send(null);
		
	}
	
	function showNews(news){
		//隐藏main。jsp页面
		document.getElementById("main_lydia").style.display='none';
		document.getElementById("showNews_lydia").style.display='block'; 
		$("#content_lydia h1").html(news.ntitle);
	}
	
	
	
	
