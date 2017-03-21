<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="/">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../css/pubstyle.css" rel="stylesheet" type="text/css">
	<link href="../css/equipdetails.css" rel="stylesheet" type="text/css">
	<script src="../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
	<script src="../js/public.js" type="text/javascript" ></script>
	<script src="../js/base.js" type="text/javascript" ></script>
	<script src="../../../plug/adddatetime.js" type="text/javascript"></script>
	<title>任务列表</title>
</head>
<body>
<div class="xl">
	</div>
	<div class="bg">
	    <table class="biaoge">
		    <thead>
			<tr>
			    <td>序号</td>
				<td>任务名称</td>
				<td>任务描述</td>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>间隔时间</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ tasks }" var="task" varStatus="status">
			    <tr>
				    <td>${ status.count }</td>
					<td>${ task.jobName }</td>
					<td>${ task.jobDesc }</td>
					<td>
						<fmt:formatDate value="${ task.startDateTime }" pattern="YYYY-MM-dd" />
					</td>
					<td>
						<fmt:formatDate value="${ task.endDateTime }" pattern="YYYY-MM-dd" />
					</td>
					<td>${task.intervalTime }</td>
					<c:if test="${task.jobStatus==0 }"><td>暂停</td></c:if>
					<c:if test="${task.jobStatus==1 }"><td>执行</td></c:if>
					<td>
						<a href="javascript:" data-id="${task.id}" onclick="Began_show(this)">开始</a>
						<a href="javascript:" onclick="stop_task(${task.id})">暂停</a>
						<a href="javascript:" onclick="clear_log(${task.type})">清空日志</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
	<div class="alertTime" id="alertpop" style="display: block;position: fixed;width: 100%;height:100%;top:0;left:0;z-index: 9999;display:none">
		<div class="popbg closepop" style="width: 100%;height:100%;position: absolute;top:0;left:0;z-index: 989;background: #000;opacity: 0.5;"></div>
		<div class="alertLayel" style="width:400px;height:250px;padding-top:10px;background:#fff;position:absolute;margin:auto;top:0;left:0;right:0;bottom:0;z-index:9999;"><h3 style="height:10px;position:relative;"><i class="closeyelar"style="margin-right:12px;margin-top:0;"></i> </h3>
			<input type="hidden" name="id" id="id" value=""/>
			<p class="alertContent" style="line-height:30px;font-size:14px;text-align:center;height:30px;">设置</p> 
			<p style="width: 80%;margin: 10px auto;"> <label style="font-weight: 100;">开始时间：</label><input type="text" id="startdata" name="startTime" value="" placeholder="开始时间" class="datetimepicker startdata" onclick="SelectDate(this,'yyyy-MM-dd')"></p>
			<p style="width: 80%;margin: 10px auto;"> <label style="font-weight: 100;">结束时间：</label><input type="text" id="enddata" name="endTime"  value="" placeholder="结束时间" class="datetimepicker enddata"  onclick="SelectDate(this,'yyyy-MM-dd')"></p>
			<p style="width: 80%;margin: 10px auto;"><label  style="font-weight: 100;">时间间隔：</label><span id="intervalTime" style="border:1px solid #ccc;width:165px;height:30px;display:inline-block;vertical-align:middle;line-height: 30px;text-indent: 10px;"></span></p>
			<div class="row tc" style="margin:0;">
				<input type="button" value="确定" onclick="beganBtn()" class="beganBtn" style="height:30px;border:none;width:80px;background:#ff6200;color:#fff;"></div>
		</div>
	</div>
</body>
<script>
	function Began_show(that){
		var alertpop=document.getElementById("alertpop");
		var intervalTime=document.getElementById("intervalTime");
		var thisvalue=that.parentNode.parentNode.children;
		var thisId=that.getAttribute("data-id");
		var str;
		document.getElementById("id").value = thisId;
		alertpop.style.display="block";
		str = thisvalue[5].innerHTML;
		intervalTime.textContent = str;
	}
	function beganBtn(){
		var startTime=document.getElementById("startdata").value;
		var endTime=document.getElementById("enddata").value;
		var alertpop=document.getElementById("alertpop");
		var id = $("#id").val();
		//console.log(startTime+"==="+endTime);
		if(startTime != null && startTime !='' ){
			$.ajax({
		 		   url: "/quartz/startTask.html",
		 		   data: {"id":id,"startTime":startTime,"endTime":endTime},//参数
		 		   type: "POST",
		 		   dataType:"json",
		 		   cache:false,
		 		   success: function(obj){
		 			  alertLayel(obj.message);
		 			  alertpop.setAttribute("style","display:none");
		 			  setTimeout(function(){window.location.href="/smart/taskList.html"},1000);
		 		   }
		 	});			
		}else{
			alertLayel("请先选择时间");
		}
	}
	function stop_task(id){
		$.ajax({
	 		   url: "/quartz/removeTask.html?id="+id,
	 		   type: "POST",
	 		   dataType:"json",
	 		   cache:false,
	 		   success: function(obj){
	 			  alertLayel(obj.message);
	 			  alertpop.setAttribute("style","display:none");
	 			  setTimeout(function(){window.location.href="/smart/taskList.html"},1000);
	 		   }
	 	});
	}
	
	function clear_log(type){
		$.ajax({
	 		   url: "/quartz/clearLog.html?type="+type,
	 		   type: "POST",
	 		   dataType:"json",
	 		   cache:false,
	 		   success: function(obj){
	 			  alertLayel(obj.message);
	 			  alertpop.setAttribute("style","display:none");
	 			  setTimeout(function(){window.location.href="/smart/taskList.html"},1000);
	 		   }
	 	});
	}
	
	$(function(){
		$(".closeyelar").on("click",function(){
			$("#alertpop").hide();
		});
	})
</script>
</html>