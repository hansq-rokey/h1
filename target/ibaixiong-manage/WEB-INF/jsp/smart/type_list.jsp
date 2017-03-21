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
	<title>智能硬件类型列表</title>
</head>
<body>
	<div class="xl">
		<a class="list_Device list_ative" href="/smart/type/toadd.html">添加</a>
	</div>
	<div class="bg">
	    <table class="biaoge">
		    <thead>
			<tr>
			    <td>序号</td>
<!-- 				<td>categoryId</td>
				<td>categoryCode</td>
				<td>categoryName</td>
				<td>categoryModelId</td>
				<td>categoryModelCode</td> -->
				<td>categoryModelName</td>
				<td>产品LOGO</td>
				<td>重置提示图</td>
				<td>状态就绪提示图</td>
				<td>状态未就绪提示图</td>
				<td>wifi名称</td>
				<!-- <td>wifi密码</td> -->
				<td>wifi-ip</td>
				<td>wifi端口</td>
				<td>bxid前4位</td>
				<td>操作界面</td>
				<td>cVersionLast</td>
				<!-- <td>创建时间</td> -->
				<td>操作日志</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ pageInfo.list }" var="item">
			    <tr>
				    <td>${ item.id }</td>
<%-- 					<td>${ item.categoryId }</td>
					<td>${ item.categoryCode }</td>
					<td>${ item.categoryName }</td>
					<td>${ item.categoryModelId }</td>
					<td>${ item.categoryModelCode }</td> --%>
					<td>${ item.categoryModelName }</td>
					<td><img alt="" src="${ item.logoImg }" height="60px" width="60px"> </td>
					<td><img alt="" src="${ item.resetImg }" height="60px" width="60px"></td>
					<td><img alt="" src="${ item.readyYesImg }" height="60px" width="60px"></td>
					<td><img alt="" src="${ item.readyNoImg }" height="60px" width="60px"></td>
					<td>${ item.wifiName }</td>
					<%-- <td>${ item.wifiPassword }</td> --%>
					<td>${ item.wifiHost }</td>
					<td>${ item.wifiPort }</td>
					<td>${ item.bxid4 }</td>
					<td>${ item.ui }</td>
					<td>${ item.cVersionLast }</td>
					<%-- <td>
						<fmt:formatDate value="${ item.createDateTime }" pattern="YYYY-MM-dd HH:MM" />
					</td> --%>
					<td><a href="/smart/type/detail.html?id=${ item.id }" class="link">详情</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<form action="/item/date/list.html?status=${status }" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<input type="submit" class="search" style="display:none;" value="搜索">
		</form>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
</body>
</html>