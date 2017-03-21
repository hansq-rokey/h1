<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/customerlist.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <title>客诉管理</title>
    <script type="text/javascript">
    	function toAssignMeList(){
    		location.href = "/ccm/question/queryList.html?queryType=1";
    	}
    	function toAllList(){
    		location.href = "/ccm/question/queryList.html?queryType=2";
    	}
    	function toCustomerList(){
    		location.href = "/ccm/question/queryList.html?queryType=3";
    	}
    	function toMeList(){
    		location.href = "/ccm/question/queryList.html?queryType=4";
    	}
    </script>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch" onclick="toAssignMeList()">派给我的</span>
            </li>
            <li>
                <span class="switch switched" onclick="toAllList()">全部问题</span>
                <div class="inforbox selectinforbox">
                	<form action="/ccm/question/queryList.html" method="post">
                    <div class="row">
                        <input type="text" class="rolename" style="width: 250px;" name="name" value="${name }" placeholder="称呼、用户名、联系电话、指派人">
                        <input type="hidden" name="pageNo" id="pageNo" value="">
                        <input type="hidden" name="queryType" value="2" >
                        <input type="submit" class="searchbtn search" value="搜索">
                    </div>
                    </form>
                    <div class="row">
                        <table class="riselist refundlist">
                            <thead>
                            <tr>
                                <td>序号</td>
                                <td>称呼</td>
                                <td>用户名</td>
                                <td>白熊号</td>
                                <td>客诉时间</td>
                                <td>客诉类型</td>
                                <td>联系方式</td>
                                <td>指派人</td>
                                <td>问题描述</td>
                                <td>处理进程</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dataList }" var="question" varStatus="qv">
                            <tr>
                                <td>${qv.index+1 }</td>
                                <td>${question.callMe }</td>
                                <c:if test="${question.user != null }">
	                                <td>${question.user.userName}</td>
	                                <td>${question.user.bxNum}</td>
                                </c:if>
                                <c:if test="${question.user == null }">
                                	<td></td>
                                	<td></td>
                                </c:if>
                                <td><fmt:formatDate value="${question.ccTime }" pattern="yyyy/MM/dd"/></td>
                                <td>${question.ccmType.name }</td>
                                <td>${question.tel }</td>
                                <td>${question.admin.userName }</td>
                                <td><p class="reasontd" title="${question.csMemo}"> ${question.csMemo}</p></td>
                                <!-- 1:30%2:603:100% -->
                                <td>
                                	<c:if test="${question.rate == 0 }">0%</c:if>
                                	<c:if test="${question.rate == 1 }">30%</c:if>
                                	<c:if test="${question.rate == 2 }">60%</c:if>
                                	<c:if test="${question.rate == 3 }">100%</c:if>
                                </td>
                                <td><a href="/ccm/question/toQuestionView.html?id=${question.id}&queryType=2" class="link">查看详情</a> </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </div>
            </li>
            <li>
                <span class="switch" onclick="toCustomerList()">客诉反馈</span>
            </li>
            <li>
                <span class="switch" onclick="toMeList()">我录入的</span>
            </li>
        </ul>
    </section>
</body>
</html>