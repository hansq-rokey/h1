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
    	function setStatus(v){
    		if(v>0){
    			$("#questionStatus").val(v);
    		}else{
    			$("#questionStatus").val("");
    		}
    	}
    	$(document).ready(function(){
	    	var questionStatus = $("#questionStatus").val();
    		if(questionStatus != null && questionStatus != ""){
    			$('.option li').each(function(){
    				var id=$(this).attr('data-id');
    				if(questionStatus==id){
    					$('#selectvalue').text($(this).text());
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
                <span class="switch " onclick="toAssignMeList()">派给我的</span>
            </li>
            <li>
                <span class="switch" onclick="toAllList()">全部问题</span>
            </li>
            <li>
                <span class="switch" onclick="toCustomerList()">客诉反馈</span>
            </li>
            <li>
                <span class="switch switched" onclick="toMeList()">我录入的</span>
                <div class="inforbox selectinforbox">
                    <form action="/ccm/question/queryList.html" method="post">
	                    <div class="row">
	                        <span class="selectinput">
	                            <span class="selectvalue" id="selectvalue">-请选择-</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option">
	                                <li data-id="0" onclick="setStatus(0)">全部</li>
	                                <li data-id="2" onclick="setStatus(2)">处理中</li>
	                                <li data-id="3" onclick="setStatus(3)">已处理</li>
	                            </ul>
	                        </span>
	                        <input type="submit" class="searchbtn search" value="搜索">
	                        <input type="hidden" name="queryType" value="4" >
	                         <input type="hidden" name="pageNo" id="pageNo" value="">
	                        <input type="hidden" name="questionStatus" id="questionStatus" value="${questionStatus }">
	                    </div>
                    </form>
                    <div class="row">
                        <table class="riselist refundlist">
                            <thead>
                            <tr>
                                <td>序号</td>
                                <td>称呼</td>
                                <td>客诉时间</td>
                                <td>客诉类型</td>
                                <td>联系方式</td>
                                <td>指派人</td>
                                <td>问题描述</td>
                                <td>处理进程</td>
                                <td>处理状态</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dataList }" var="question" varStatus="qv">
                            <tr>
                                <td>${qv.index+1 }</td>
                                <td>${question.callMe }</td>
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
                                <td>
                                	<c:if test="${question.processStatus == 1 }">待处理</c:if>
                                	<c:if test="${question.processStatus == 2 }">处理中</c:if>
                                	<c:if test="${question.processStatus == 3 }">已处理</c:if>
                                	<c:if test="${question.processStatus == 4 }">已关闭</c:if>
                                </td>
                                <td><a href="/ccm/question/toQuestionView.html?id=${question.id}&queryType=4" class="link">查看详情</a> </td>
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