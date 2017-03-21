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
    <link href="../../../css/postmanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <title>帖子管理</title>
    <script type="text/javascript">
    	function toGeneralList(){
    		location.href = "/bbs/article/getArticleList.html?fromType=1";
    	}
    	function toActivityList(){
    		location.href = "/bbs/article/getArticleList.html?fromType=2";
    	}
    	function toReportList(){
    		location.href = "/bbs/article/getArticleList.html?fromType=3";
    	}
    	function toSchoolList(){
    		location.href = "/bbs/article/getArticleList.html?fromType=4";
    	}
    	function setStatus(id,name){
    		if(id != 0)
    			$("#status").val(id);
    		else
    			$("#status").val("");
    		$("#sub").click();
    	}
    	$(document).ready(function(){
    		var status = $("#status").val();
    		if(status != null && status != ""){
    			$('.option li').each(function(){
    				var id=$(this).attr('data-id');
    				if(status==id){
    					$('#statusTitle').text($(this).text());
    				}
    			});
    		}
    	});
    </script>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch" onclick="toGeneralList()">普通帖子</span>
            </li>
            <li>
                <span class="switch" onclick="toActivityList()">活动帖子</span>
            </li>
            <li>
                <span class="switch" onclick="toReportList()">举报帖子</span>
            </li>
            <li>
                <span class="switch switched" onclick="toSchoolList()">学院稿件</span>
                <div class="inforbox selectinforbox">
                	<form action="/bbs/article/getArticleList.html" method="post">
                		<input type="hidden" name="fromType" value="4" id="fromType"/>
                		<input type="hidden" name="status" value="${status}" id="status"/>
	                    <div class="row">
	                        <span class="selectinput plate">
	                            <span class="selectvalue" id="statusTitle">全部</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option">
	                                <li data-id='0' onclick="setStatus(0,'全部')">全部</li>
	                                <li data-id='1' onclick="setStatus(1,'未处理')">未处理</li>
	                                <li data-id='2' onclick="setStatus(2,'已处理')">已处理</li>
	                            </ul>
	                        </span>
	                    </div>
	                    <div style="display: none;">
	                    	<input type="hidden" name="pageNo" id="pageNo" value="">
	                    	<input type="submit" id="sub" class="search">
	                    </div>
                    </form>
                    <div class="row">
                        <table class="actpostlist">
                            <thead>
                            <tr>
                                <td>序号</td>
                                <td>白熊号</td>
                                <td>帖子标题</td>
                                <td>投稿时间</td>
                                <td>状态</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${articleList}" var="article" varStatus="st">
	                            <tr>
	                                <td>${st.index+1 }</td>
	                                <td>${article.user.bxNum }</td>
	                                <td>${article.title}</td>
	                                <td><fmt:formatDate value="${article.createDateTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                                <td>
	                                	<c:if test="${article.status == 1}">已处理</c:if>
	                                	<c:if test="${article.status == 0}">未处理</c:if>
	                                	<c:if test="${article.status == -2}">不录用</c:if>
	                                </td>
	                                <td>
	                                	<c:if test="${article.status == 1}">已处理</c:if>
	                                	<c:if test="${article.status == 0}">
	                                		<a href="/bbs/article/setStatus.html?id=${article.id }&fromType=4&status=1" class="link">录用</a> 
	                                		<a href="/bbs/article/setStatus.html?id=${article.id }&fromType=4&status=-2" class="link">不录用</a>
	                                	</c:if>
	                                </td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </div>
            </li>
        </ul>
    </section>
</body>
</html>