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
	<title>设备版本管理</title>
</head>
<body>
	<div class="bg">
	    <table class="biaoge">
		    <thead>
			<tr>
			    <td>序号</td>
				<td>bxid4</td>
				<td>固件最新版本号</td>
				<td>硬件版本</td>
				<td>软件版本号</td>
				<td>升级地址</td>
				<td>创建时间</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ pageInfo.list }" var="item">
			    <tr>
				    <td>${ item.id }</td>
				    <td>${ item.bxid4 }</td>
					<td>${ item.cVersionLast }</td>
					<td>${ item.cVersionLast2 }</td>
					<td>${ item.cVersionLast4 }</td>
					<td>${ item.upgradeBin }</td>
					<td>
						<fmt:formatDate value="${ item.createDateTime }" pattern="YYYY-MM-dd HH:MM" />
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<form action="/smart/version/history/list.html" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<input type="hidden" name="id"  value="${id }">
			<input type="submit" class="search" style="display:none;" value="搜索">
		</form>
		<div class="pop addpop">	    
	</div>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
</body>
</html>