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
    <link href="../../../css/finanstat.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../plug/adddatetime.js" type="text/javascript"></script>
    <title>财务统计</title>
</head>
<body>
<section>
    <div class="row">
	    <form action="/aftetSale/refund/list.html" method="post">
	        <input type="text" name="keywords" value="${keywords }" class="ordercodebtn" placeholder="订单号或用户名">
	        <input type="text" name="startTime" value="${startTime }" placeholder="开始时间" class="datetimepicker startdata" onclick="SelectDate(this,'yyyy-MM-dd')">
	        <span class="space"></span><span class="space"></span>至
	        <input type="text" name="endTime" value="${endTime }" placeholder="结束时间" class="datetimepicker enddata" onclick="SelectDate(this,'yyyy-MM-dd')">
	        <input type="hidden" name="pageNo" id="pageNo" value="0">
	        <input type="submit" value="搜索" class="searchbtn search">
	    </form>
    </div>
        <table class="finanstatlist">
            <thead>
            <tr>
                <td>订单号</td>
                <td>用户名</td>
                <td>白熊号</td>
                <td>提交时间</td>
                <td>退货原因</td>
                <td>退货状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
              <c:forEach items="${list}" var="item" varStatus="st">
	            <tr>
	                <td class="serial">${item.orderNumber }</td>
	                <td class="serial">${item.userName }</td>
	                <td class="serial">${item.bxNum }</td>
	                <td class="serial"><fmt:formatDate value="${item.createDateTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                <td class="serial">${item.description }</td>
	                <td class="serial">
		                <c:if test="${item.status == 10 }">
                        	未收件
                        </c:if>
                        <c:if test="${item.status == 20 }">
                        	审核中
                        </c:if>
                        <c:if test="${item.status == 30 }">
                        	关闭
                        </c:if>
                        <c:if test="${item.status == 41 }">
                        	同意
                        </c:if>
                        <c:if test="${item.status == 42 }">
                        	拒绝
                        </c:if>
                        <c:if test="${item.status == 50 }">
                        	已退款
                        </c:if>		                
	                </td>
	                <td class="serial">
	                	<a class="link" href="#" style="display:none">打款</a>
	                	<c:if test="${item.status==41 }">
	                		<a class="link refund-sure" data-id="${item.id}" href="javascript:void(0);">确认退款</a>
	                	</c:if>
	                	<a class="link" href="/aftetSale/refund/detail.html?id=${item.id}">查看详情</a>
	                </td>
	            </tr>
	          </c:forEach>
            </tbody>
        </table>
</section>
<jsp:include page="../include/pages.jsp"></jsp:include>
    <script>
 // 匿名函数
    $(function(){
    	//确认退款
    	$('.refund-sure').on('click',function(){
    		var id=$(this).attr('data-id');
    		console.log(id);
    		$.ajax({
		        url: "/aftetSale/refund/operate.html",
		        type: 'post',
		        data:{ "id": id},
		        dataType: 'json',//here
		        success: function (data) {
		        	if(data.code==1){
		        		alertLayel("退款成功!");
		        		setTimeout(function(){window.location.reload();},2000);
		        	}else{
		        		alertLayel("退款失败!");
		        		setTimeout(function(){window.location.reload();},2000);
		        	}
		        }
		    });
    	})
    		
    });
    </script>
</body>
</html>
