<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function(){
		$("#xiugaimima").click(function(){
			$('#popup_first').window({title:'密码修改',
				cache:false, 
			    width:300,
			    height:170,
			    modal:true
			}).window('open').window('refresh','<%=path%>/CaseSystem/basic/modPassword.jsp');
		});
	});
	setTimeout("setTaskCount()",1000);//出来的时候调用一次
	setInterval("setTaskCount()",1000*60*5);//5分钟调用一次
	function setTaskCount(){
		$.ajax({
			url:'<%=path%>/entrust/homePageCount.action',
			type:'post',
			dataType:'json',
			success:function(msg){
				$('#taskCount').html(msg.result);
			},
			error:function(msg){
			}
		});
	}
	function show_taskWin(){
		var url = '<%=path%>/entrust/homePage.action';
		parent.parent.$('#popup_fifth').window({title:'任务处理',
			cache:false,
		    modal:true,
		    width:900,    
		    height:500
		    }).window('open').window('refresh',url);
	}
</script>
<div  style="width:100%;height:100%;">
    <div class="Frame_top">
        <div class="fr_logo">
            <img src="<%=path %>/CaseSystem/images/logo_top.jpg"/>
        </div>
        <div class="top_shutbutton">
         	<span>
        		<a id="xiugaimima" style="color: white;text-decoration:underline;cursor:pointer;" >修改密码</a>
        	</span>
        	<span>
        		<a style="color: white;text-decoration:underline;cursor:pointer;" href="<%=path%>/commonpage/pehelp.pdf" target="_blank">用户帮助</a>
        	</span>
        	<span>
        		<a style="color: red;text-decoration:underline;cursor:pointer;" href="<%=path%>/" >安全退出</a>
        	</span>
        </div>
        <div class="fr_user_infor">
        	 待办任务:<a data="allInformation" onclick="show_taskWin()"><font style="color: red;font-size: 20px;" id="taskCount" ></font></a> ${user.cn_name}，您好！欢迎登录刑侦实战应用平台！
        </div>
    </div>
</div>
