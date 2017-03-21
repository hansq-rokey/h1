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
    <script src="../../../js/base.js" type="text/javascript" ></script>
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
    	function closeQuestion(id){
			$('.addgradepop').show();
			$("#questionId").val(id);
			$("#memo").val("");
		}
    	function closeque(){
    		var questionId = $("#questionId").val();
    		var memo = $("#memo").val();
    		if( memo == null || memo  ==undefined || memo  == ''){
    			alertLayel("请输入关闭理由");
        		return false;
        	}else{
	    		$.ajax({
			 		   url: "/ccm/question/closeQuestion.html?questionId="+questionId+"&memo="+memo,
			 		   type: "POST",
			 		   dataType:"json",
			 		   cache:false,
			 		   success: function(obj){
			 			  if ( !checkCode( obj ) ) {
							return;
			 			  }else{
			 				window.location.reload();//重载页面
			 			  }
			 		   }
		   		});
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
                <span class="switch switched" onclick="toCustomerList()">客诉反馈</span>
                <div class="inforbox selectinforbox">
                	<form action="/ccm/question/queryList.html" method="post">
	                    <div class="row">
	                        <span class="selectinput">
	                            <span class="selectvalue" id="selectvalue">-请选择-</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option">
	                                <li data-id="0" onclick="setStatus(0)">全部</li>
	                                <li data-id="1" onclick="setStatus(1)">待处理</li>
	                                <li data-id="2" onclick="setStatus(2)">处理中</li>
	                                <li data-id="3" onclick="setStatus(3)">已处理</li>
	                                <li data-id="4" onclick="setStatus(4)">已关闭</li>
	                            </ul>
	                        </span>
	                        <input type="submit" class="searchbtn search" value="搜索">
	                        <input type="hidden" name="queryType" value="3" >
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
                                <td>用户名</td>
                                <td>白熊号</td>
                                <td>客诉时间</td>
                                <td>联系方式</td>
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
                                <c:if test="${question.user != null }">
	                                <td>${question.user.userName}</td>
	                                <td>${question.user.bxNum}</td>
                                </c:if>
                                <c:if test="${question.user == null }">
                                	<td></td>
                                	<td></td>
                                </c:if>
                                <td><fmt:formatDate value="${question.ccTime }" pattern="yyyy/MM/dd"/></td>
                                <td>${question.tel }</td>
                                <td><p class="reasontd" title="${question.customersMemo}"> ${question.customersMemo}</p></td>
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
                                <td>
                                	<c:if test="${question.processStatus == 1 }">
                                	<a href="/ccm/question/toAddOrUpdate.html?id=${question.id}&queryType=3" class="link">立即处理</a>
                                	&nbsp;&nbsp;&nbsp;&nbsp;
                                	<a href="#" class="link" onclick="closeQuestion(${question.id})">关闭</a>
                                	</c:if>
                                	<c:if test="${question.processStatus != 1 }">
                                		<a href="/ccm/question/toQuestionView.html?id=${question.id}&queryType=3" class="link">查看详情</a>
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
            <li>
                <span class="switch" onclick="toMeList()">我录入的</span>
            </li>
        </ul>
    </section>
    <div class="pop addgradepop">
	    <div class="popbg"></div>
	    <div class="layel">
        <h3 class="poptitle">关闭<i class="closeicon"></i> </h3>
        <div class="row" style="text-align:left;padding-left:50px;">
            <span class="addtypename" style="">关闭原因：</span>
        </div>
        <div class="row" style="text-align:left;padding-left:50px;">
          <textarea class="custtext" style="width: 390px;height: 80px;" name="memo" id="memo"></textarea>
        </div>
        <div class="row tc">
           	<input type="button" value="确认" onclick="closeque()" class="popBtn">
           	<input type="hidden" id="questionId">
        </div>
    </div>
</div>
</body>
</html>
