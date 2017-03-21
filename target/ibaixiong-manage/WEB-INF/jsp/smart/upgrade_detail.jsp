<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="/plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/pubstyle.css" rel="stylesheet" type="text/css">
	<link href="/css/equipdetails.css" rel="stylesheet" type="text/css">
	<script src="/plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
	<script src="/js/public.js" type="text/javascript" ></script>
	<title>升级日志</title>
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
			    <td>升级时间</td>
				<td>bxid</td>
				<td>升级类型</td>
				<td>版本号</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ pageInfo.list }" var="log">
			    <tr>
					<td><fmt:formatDate value="${ log.createTime }" pattern="YYYY-MM-dd hh:mm:ss" /></td>
				    <td>${ log.bxid }</td>
					<td>
						<c:choose>
							<c:when test="${log.type==259 }">参数配置类型</c:when>
							<c:when test="${log.type==192 }">参数配置类型</c:when>
							<c:otherwise>去火星找吧</c:otherwise>
						</c:choose>
					</td>
					<td>${ log.cVersion }</td>
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