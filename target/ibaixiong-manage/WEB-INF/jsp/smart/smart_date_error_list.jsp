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
	<title>设备列表</title>
</head>
<body>
<div class="xl">
		<a class="list_Device ${status==2?'list_ative':'' }" href="/smart/date/list.html">已发短信</a>
		<a class="list_Device ${status==1?'list_ative':'' }" href="/smart/date/list.html?status=1">未发短信</a>
	</div>
	<div class="bg">
	    <table class="biaoge">
		    <thead>
			<tr>
			    <td>序号</td>
				<td>bxid</td>
				<td>bxcode</td>
				<td>连接时间</td>
				<td>错误时间</td>
				<td>操作日志</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ listSmart }" var="smart">
			    <tr>
				    <td>${ smart.id }</td>
					<td>${ smart.bxid }</td>
					<td>${ smart.bxcode }</td>
					<td>
						<fmt:formatDate value="${ smart.createTime }" pattern="YYYY-MM-dd HH:MM:SS" />
					</td>
					<td>
						<fmt:formatDate value="${ smart.smartTime }" pattern="YYYY-MM-dd HH:MM:SS" />
					</td>
					<td><a href="/smart/date/update.html?id=${ smart.id }&status=3" class="link">已处理</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<form action="/smart/date/list.html?status=${status }" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<input type="submit" class="search" style="display:none;" value="搜索">
		</form>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
</body>
</html>