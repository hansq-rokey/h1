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
</head>
<body>
	<div class="xl">
	  <a class="list_Device list_ative" href="/smart/list.html">全部</a>
      <a class="list_Device " href="/smart/onlinelist.html">已连接设备</a>
	  <span class="shu">${ onlineNum }<em>台设备连接成功</em></span>
	</div>
	<div class="bg">
	    <table class="biaoge">
		    <thead>
			<tr>
			    <td>序号</td>
				<td>产品类型</td>
				<td>产品编号</td>
				<td>产品名称</td>
				<td>激活时间</td>
				<td>操作日志</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ listSmart }" var="smart">
			    <tr>
				    <td>${ smart.id }</td>
					<td>${ smart.erpHardwareProduct.categoryModel.name }</td>
					<td>${ smart.erpHardwareProduct.uniqueCode }</td>
					<td>${ smart.productName }</td>
					<td>
						<fmt:formatDate value="${ smart.regTime }" pattern="YYYY-MM-dd HH:MM:SS" />
					</td>
					<td><a href="/smart/detail.html?bxcode=${ smart.bxcode }" class="link">查看详情</a></td>
				</tr>
			</c:forEach>			
			</tbody>
		</table>
		<form action="/smart/list.html" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<input type="submit" class="search" style="display:none;" value="搜索">
		</form>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
</body>
</html>