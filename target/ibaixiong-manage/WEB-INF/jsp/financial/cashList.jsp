<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>提现审核管理</title>
    <script type="text/javascript">
	    function updateStatus(id,status){
	    	$.ajax({
		 		   url: "/cash/setStatus.html?id="+id+"&status="+status,
		 		   type: "POST",
		 		   dataType:"json",
		 		   cache:false,
		 		   success: function(obj){
		 			  if ( !checkCode( obj ) ) {
		 					return;
		 			  }
		 			  $(".search").click();
		 		   }
	   		});
	    }
    </script>
</head>
<body>
    <section>
        <ul class="partlist" style="border-bottom: 0px;margin-top: -45px;">
            <li>
                <div class="inforbox selectinforbox">
                    <div class="row">
	                    <div style="display: none;">
		                    <form action="/cash/list.html" method="post">
						        <input type="submit" value="搜索" class="searchbtn search">
						    </form>
					    </div>
                        <table class="gradelist">
                            <thead>
                            <tr>
                                <td>4s店名</td>
                                <td>城运商</td>
                                <td>用户</td>
                                <td>金额</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dataList}" var="item" varStatus="st">
                            <tr class="menutr">
                                <td>
                                	<c:if test="${not empty item.ssssInfo }">${item.ssssInfo.ssssName }</c:if>
                                </td>
                                <td>
                                	<c:if test="${not empty item.cityMerchant }">${item.cityMerchant.cityMerchantName }</c:if>
                                </td>
                                <td>${item.user.userName }</td>
                                <td>${item.money }</td>
                                <td>
                                	<c:if test="${item.status==0 }">
                                		<a href="#" class="link" onclick="updateStatus(${item.id},1)">同意提现（已打款）</a>
                                		<a href="#" class="link" onclick="updateStatus(${item.id},-1)">拒绝</a>
                                	</c:if>
                                	<c:if test="${item.status==1 }">
                                		已同意
                                	</c:if>
                                	<c:if test="${item.status==-1 }">
                                		已拒绝
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
