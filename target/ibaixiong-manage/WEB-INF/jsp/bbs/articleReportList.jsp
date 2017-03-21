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
    	function del(id){
    		$("#articleId").val(id);
    		$('.delpostpop').show();
    	}
    	function closeReport(id){
    		$("#article1Id").val(id);
    		$('.closepostpop').show();
    	}
    </script>
</head>
<body>
<div style="display: none;">
<form action="/bbs/article/getArticleList.html?fromType=3" method="post">
 	<input type="hidden" name="pageNo" id="pageNo" value="">
    <input type="submit" id="pageSubmit" class="search" value="搜索">        	
</form>
</div>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch" onclick="toGeneralList()">普通帖子</span>
            </li>
            <li>
                <span class="switch" onclick="toActivityList()">活动帖子</span>
            </li>
            <li>
                <span class="switch switched">举报帖子</span>
                <div class="inforbox selectinforbox">
                    <div class="row">
                        <table class="actpostlist">
                            <thead>
                            <tr>
                                <td>序号</td>
                                <td>白熊号</td>
                                <td>帖子标题</td>
                                <td>发布时间</td>
                                <td>举报人数</td>
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
	                                <td>${article.reportcount}</td>
	                                <td>
	                                	<a href="#" onclick="del(${article.id})" class="link deletelink">删除</a>
	                                	<a href="#" onclick="closeReport(${article.id})" class="link deletelink">关闭举报</a>
	                                </td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </div>
            </li>
            <li>
                <span class="switch" onclick="toSchoolList()">学院稿件</span>
            </li>
        </ul>
    </section>
<form action="/bbs/article/delete.html"  method="post">
<input type="hidden" name="id" id="articleId">
<input type="hidden" name="fromType" value="3" id="fromType"/>
<!--删除帖子弹窗-->
<div class="pop delpostpop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">删除帖子<i class="closeicon"></i> </h3>
        <div class="row">
            <textarea class="reasontext" placeholder="请输入删除帖子的原因" name="reasonStr"></textarea>
        </div>
        <div class="row tc">
            <input type="submit" value="删除" class="delete">
            <input type="button" value="取消" class="cancel">
        </div>
    </div>
</div>
</form>
<form action="/bbs/article/close.html" method="post">
<input type="hidden" name="id" id="article1Id">
<input type="hidden" name="fromType" value="3" id="fromType"/>
<!-- 关闭举报 -->
<div class="pop closepostpop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">关闭帖子<i class="closeicon"></i> </h3>
        <div class="row">
            <textarea class="reasontext" placeholder="请输入关闭帖子的原因" name="closeMemo"></textarea>
        </div>
        <div class="row tc">
            <input type="submit" value="确定" class="delete">
            <input type="button" value="取消" class="cancel">
        </div>
    </div>
</div>
</form>
</body>
</html>