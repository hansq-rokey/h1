<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../css/pubstyle.css" rel="stylesheet" type="text/css">
	<link href="../css/equipdetails.css" rel="stylesheet" type="text/css">
	<script src="../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
	<script src="../js/public.js" type="text/javascript" ></script>
	<title>设备列表</title>
	<style type="text/css">
	.search {
    	margin-left: 20px;
    	background: #ff6200;
    	color: white;
    	width: 100px;
    	height: 30px;
	}
	.content {
    	margin-top: -36px;
    	padding-left: 191px;
	}
	</style>
</head>
<body>
	<div class="xl">
		<a class="list_Device" href="/smart/list.html">全部</a>
      	<a class="list_Device list_ative" href="/smart/onlinelist.html">已连接设备</a>
      	<div class="content">
        	<form action="/smart/onlinelist.html" method="post">
	          	<input type="text" name="keywords" id="keywords" class="packing-code" value="${keyword }" placeholder="智能bxid">
	            <input type="hidden" name="pageNo" id="pageNo" value="">
				<input type="hidden" name="type"  value="${type }">
				<input type="hidden" name="sortName" value="${sortName }">
	            <input type="submit" class="search" value="搜索">        	
        	</form>
        </div>
	</div>
	<div class="bg">
	    <table class="biaoge">
		    <thead>
			<tr>
		 		<td>序号    <a class="${type==2 and sortName=='date' ?'order_by_down':'order_by_up' } order serial_number" data-id="${type }" data-title="1" >升序</a></td>
				<td>版本号 <a class="${type==2 and sortName=='version' ?'order_by_down':'order_by_up' } order" data-title="2">升序</a></td>
				<td>智能bxid</td>
				<td>产品名称</td>
				<td>激活时间</td>
				<td>操作日志</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ listSmart }" var="smart">
			    <tr>
				    <td>${ smart.id }</td>
					<td>${ smart.cVersion }</td>
					<td>${ smart.bxid }</td>
					<td>${ smart.productName }</td>
					<td>
						<fmt:formatDate value="${ smart.regTime }" pattern="YYYY-MM-dd HH:mm:ss" />
					</td>
					<td>
						<a href="/smart/detail.html?bxcode=${ smart.bxcode }" class="link">查看详情</a>|
						<a href="javascript:void(0);" class="link upgrade" data-id="${smart.id }">升级</a>|
						<a href="javascript:void(0);" class="link config" data-id="${smart.id }">设置配置</a>|
						<a href="/smart/upgrade/detail.html?bxid=${ smart.bxid }" class="link">升级日志</a>				
					</td>
				</tr>
			</c:forEach>			
			</tbody>
		</table>
		<%-- <form action="/smart/onlinelist.html" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<input type="hidden" name="type"  value="${type }">
			<input type="hidden" name="sortName" value="${sortName }">
			<input type="submit" class="search" style="display:none;" value="搜索">
		</form> --%>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".upgrade").click(function(){
			var id=$(this).attr("data-id");
			if(confirm("确定对该设备进行远程升级吗？")){
				$.ajax({
					  type: 'POST',
					  url: '/smart/upgrade.html',
					  data: {id:id},
					  dataType: 'json',
					  success: function(data){
						  if(data==1){
							  alert('正在升级中');
						  }else{
							  alert('升级请求失败！');
						  }
					  }
					});
				
			}
			
		});
		$(".config").click(function(){
			var id=$(this).attr("data-id");
			if(confirm("确定发送配置信息吗？")){
				$.ajax({
					  type: 'POST',
					  url: '/smart/config.html',
					  data: {id:id},
					  dataType: 'json',
					  success: function(data){
						  if(data==1){
							  alert('设置配置已发送！');
						  }else{
							  alert('设置配置请求失败！');
						  }
					  }
					});
			}
			
			
		});
		
	});
	
	
	</script>
</body>
</html>