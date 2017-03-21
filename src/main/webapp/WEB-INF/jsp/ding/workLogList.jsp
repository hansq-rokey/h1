<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/commanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <script src="../../../js/area.js"></script>
	<script src="../../../js/location.js"></script>
    <title>工作报告</title>
</head>
<body>
    <section>
        <ul class="partlist" style="border-bottom: 0px;margin-top: 0;">
            <li>
                <div class="inforbox selectinforbox">
                    <div class="row">
                        <table class="gradelist">
                            <thead>
                            <tr>
                                <td>序号</td>
                                <td>时间</td>
                                <td>报告标示</td>
                                <td>查阅标示</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${logList}" var="item" varStatus="st">
                            <tr class="menutr">
                                <td>${st.count }</td>
                                <td><fmt:formatDate value="${item.workDay }" pattern="yyyy/MM/dd"/></td>
                                <td>
                                	<c:if test="${item.sendTag == 0}">
                                		未报告
                                	</c:if>
                                	<c:if test="${item.sendTag == 1}">
                                		已报告
                                	</c:if>
                                </td>
                                <td>
                                	<c:if test="${item.redTag == 1}">
                                		已阅
                                	</c:if>
                                	<c:if test="${item.redTag == 0}">
                                		未阅
                                	</c:if>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
</body>
</html>
