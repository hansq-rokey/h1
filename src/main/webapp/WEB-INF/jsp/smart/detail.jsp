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
	    <span class="selectinput plate" style="display:none;">
            <span class="selectvalue">全部产品</span>
            <i class="arrow arrowright"></i>
            <ul class="option">
                <li>一个产品</li>
                <li>一个产品</li>
                <li>一个产品</li>
            </ul>
        </span>
		<span class="selectinput plate" style="display:none;">
            <span class="selectvalue">全部状态</span>
            <i class="arrow arrowright"></i>
            <ul class="option">
                <li>一个状态</li>
                <li>一个状态</li>
                <li>一个状态</li>
            </ul>
        </span>
		<input type="text" class="personname" placeholder="输入用户名或产品编号" style="display:none;">
		<input type="button" class="searchbtn" value="搜索" style="display:none;">
		<span class="shu" style="display:none;"></span><span>操作日志</span>
	</div>
	<div class="bg">
	    <table class="biaoge">
		    <thead>
			<tr>
			    <td>序号</td>
				<td>操作账号</td>
				<td>操作时间</td>
				<td>操作详情</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ listSmartLog }" var="log">
			    <tr>
				    <td>${ log.id }</td>
					<td>${ log.user.userName }</td>
					<td><fmt:formatDate value="${ log.createTime }" pattern="YYYY-MM-dd HH:MM:SS" /></td>
					<td>${ log.logDes }</td>
				</tr>
			</c:forEach>			
			</tbody>
		</table>
		<form action="/smart/detail.html" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<input type="hidden" name="bxcode" value="${ bxcode }">
			<input type="submit" class="search" style="display:none;" value="搜索">
		</form>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
</body>
</html>