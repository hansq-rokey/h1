<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../css/handlelog.css" rel="stylesheet" type="text/css">
    <script src="../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../js/public.js" type="text/javascript" ></script>
    <script src="../../plug/adddatetime.js" type="text/javascript"></script>
    <title>操作日志</title>
</head>
<body>
<section>
	<form action="/system/log/getLogList.html" method="post">
	    <div class="row">
	        <span>员工名:</span>
	        <input type="text" class="personnelname" name="queryName" value="${queryName }">
	        <input type="text" name="startDate" value="${startDate }" placeholder="开始时间" class="datetimepicker startdata" onclick="SelectDate(this,'yyyy-MM-dd')">
	        <span class="space"></span><span class="space"></span>至
	        <input type="text" name="endDate" value="${endDate }" placeholder="结束时间" class="datetimepicker enddata" onclick="SelectDate(this,'yyyy-MM-dd')">
	        <input type="checkbox" name="isown">仅看本人
	        <input type="hidden" name="pageNo" id="pageNo" value="">
	        <input type="submit" value="搜索" class="searchbtn search">
	    </div>
    </form>
    <table class="handleloglist">
        <thead>
        <tr>
        	<td>序号</td>
            <td>操作人</td>
            <td>操作时间</td>
            <td>操作类型</td>
            <td>操作内容</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logList}" var="log" varStatus="st">
	        <tr>
	        	<td>${st.index+1 }</td>
	            <td class="serial">${log.adminName }</td>
	            <td class="serial"><fmt:formatDate value="${log.addTime }" pattern="yyyy/MM/dd HH:mm:ss"/> </td>
	            <td class="serial">${log.type }</td>
	            <td class="serial">${log.content }</td>
	        </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="../include/pages_old.jsp"></jsp:include>
</section>
</body>
</html>