$(function(){
	//window.main_panel=$("#content_lydia");
	//使用选项卡
	$("#content_index").tabs("add",{
		title:'源辰信息',
		selected:true,
		closable:false,
		href:'yc.html'
	});

	$("#menu_tree").tree({
		onClick:function(node){
			var obj=$("#content_index");
			if(node.id=='admin_content'){//说明是管理员信息管理
				//判断管理员界面是否展开
				if(obj.tabs('exists','管理员信息管理')){//则选中
					obj.tabs('select','管理员信息管理');
				}else{//添加一个选项卡
					$("#content_index").tabs("add",{
						title:'管理员信息管理',
						selected:true,
						closable:true,
						href:'admin.html'
					});
				}
			}else if(node.id=='user_content'){ 
				if(obj.tabs('exists','会员信息管理')){//则选中
					obj.tabs('select','会员信息管理');
				}else{//添加一个选项卡
					$("#content_index").tabs("add",{
						title:'会员信息管理',
						selected:true,
						closable:true,
						href:'users.html'
					});
				}			
			}else if(node.id=='type_content'){ 
				if(obj.tabs('exists','新闻类型管理')){//则选中
					obj.tabs('select','新闻类型管理');
				}else{//添加一个选项卡
					$("#content_index").tabs("add",{
						title:'新闻类型管理',
						selected:true,
						closable:true,
						href:'type.html'
					});
				}			
			}else if(node.id=='addNews_content'){ 
				if(obj.tabs('exists','新闻管理')){//则选中
					obj.tabs('select','新闻管理');
				}else{//添加一个选项卡
					$("#content_index").tabs("add",{
						title:'新闻管理',
						selected:true,
						closable:true,
						href:'news.html'
					});
				}			
			}else if(node.id=='findNews_content'){ 
				if(obj.tabs('exists','新闻管理')){//则选中
					obj.tabs('select','新闻管理');
				}else{//添加一个选项卡
					$("#content_index").tabs("add",{
						title:'新闻管理',
						selected:true,
						closable:true,
						href:'news.html'
					});
				}			
			}
		}
		
	});
});