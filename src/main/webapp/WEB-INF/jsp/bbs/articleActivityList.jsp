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
    			$("#activityStatus").val(id-1);
    		else
    			$("#activityStatus").val("");
    		$("#sub").click();
    	}
    	
    	$(document).ready(function(){
    		var activityStatus = $("#activityStatus").val();
    		if(activityStatus != null && activityStatus != ""){
    			activityStatus = parseInt(activityStatus) +1;
    			$('.option li').each(function(){
    				var id=$(this).attr('data-id');
    				if(activityStatus==id){
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
                <span class="switch switched">活动帖子</span>
                <div class="inforbox selectinforbox">
                	<form action="/bbs/article/getArticleList.html" method="post">
                		<input type="hidden" name="fromType" value="2" id="fromType"/>
                		<input type="hidden" name="activityStatus" value="${activityStatus }" id="activityStatus"/>
	                    <div class="row">
	                        <span class="selectinput plate">
	                            <span class="selectvalue" id="statusTitle">全部状态</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option">
	                                <li data-id='0' onclick="setStatus(0,'全部状态')">全部状态</li>
	                                <li data-id='1' onclick="setStatus(1,'进行中')">进行中</li>
	                                <li data-id='2' onclick="setStatus(2,'已结束')">已结束</li>
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
                                <td>帖子标题</td>
                                <td>发布时间</td>
                                <td>结束时间</td>
                                <td>活动状态</td>
                                <td>浏览数</td>
                                <td>回复数</td>
                                <td>点赞数</td>
                                <td>报名人数</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${articleList}" var="article" varStatus="st">
	                            <tr>
	                                <td>${st.index+1 }</td>
	                                <td>${article.title}</td>
	                                <td><fmt:formatDate value="${article.activityStartTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                                <td><fmt:formatDate value="${article.activityEndTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                                <td>
	                                	<c:if test="${article.activityIsEnd == 0}">进行中</c:if>
	                                	<c:if test="${article.activityIsEnd != 0}">已结束</c:if>
	                                </td>
	                                <td>${article.viewCount }</td>
	                                <td>${article.replyCount }</td>
	                                <td>${article.pariseCount }</td>
	                                <td>${article.applyCount }</td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </div>
            </li>
            <li>
                <span class="switch" onclick="toReportList()">举报帖子</span>
            </li>
            <li>
                <span class="switch" onclick="toSchoolList()">学院稿件</span>
            </li>
        </ul>
    </section>
</body>
</html>