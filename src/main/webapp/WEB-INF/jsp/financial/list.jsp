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
	    <form action="/financial/queryList.html" method="post">
	        <input type="text" name="queryStr" value="${queryStr }" class="ordercodebtn" placeholder="支付流水号、订单号">
	        <input type="text" name="startTime" value="${startTime }" placeholder="开始时间" class="datetimepicker startdata" onclick="SelectDate(this,'yyyy-MM-dd')">
	        <span class="space"></span><span class="space"></span>至
	        <input type="text" name="endTime" value="${endTime }" placeholder="结束时间" class="datetimepicker enddata" onclick="SelectDate(this,'yyyy-MM-dd')">
	        <span class="selectinput" style="float:none;">
                    <span class="selectvalue">支付类型:</span>
                    <i class="arrow arrowright"></i>
                    <ul class="option">
                    	<c:forEach items="${payTypeList }" var="pay">
	                        <li data-id="${pay.dictCodeValue }">${pay.dictCodeName }</li>
                    	</c:forEach>
                    </ul>
                </span>
           	<input name="payType" value="${payType }" type="hidden" id="payType" />
           	<input type="hidden" name="pageNo" id="pageNo" value="0">
	        <input type="submit" value="搜索" class="searchbtn search">
	        <input type="submit" value="小计" class="searchbtn search">
	        <input type="submit" value="累计" class="searchbtn search">
	    </form>
    </div>
        <table class="finanstatlist">
            <thead>
            <tr>
                <td>支付号</td>
                <td>订单号</td>
                <td>用户账号</td>
                <td>白熊号</td>
                <td>时间</td>
                <td>付款金额</td>
                <td>支付类型</td>
                <td>支付状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
              <c:forEach items="${dataList}" var="item" varStatus="st">
	            <tr>
	                <td class="serial">${item.payHistory.thirdNumber} </td>
	                <td class="serial">${item.orderNumber }</td>
	                <td class="serial">${item.phone }</td>
	                <td class="serial">${item.bxNum }</td>
	                <td class="serial"><fmt:formatDate value="${item.createDateTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                <td class="serial">${item.payHistory.money }</td>
	                <td class="serial">
	                	<c:forEach items="${payTypeList }" var="pay">
	                        <c:if test="${item.payHistory.payType ==pay.dictCodeValue }">
		                		${pay.dictCodeName }
		                	</c:if>               	
                    	</c:forEach>
	                </td>
	                <td class="serial">
	                	<c:if test="${item.payHistory.status == 0 }">
		                	未支付
		                </c:if>
		                <c:if test="${item.payHistory.status == 1 }">
		                	已支付
		                </c:if>
		                <c:if test="${item.payHistory.status == -1 }">
		                	关闭
		                </c:if>
	                </td>
	                <td class="serial"><a href="/financial/detail.html?orderNumber=${item.orderNumber}">查看订单</a></td>
	            </tr>
	          </c:forEach>
            </tbody>
        </table>
</section>
<jsp:include page="../include/pages.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('.option li').each(function(){
		var typeId=$('#payType').val();
		var id=$(this).attr('data-id');
		if(typeId==id){
			$('.selectvalue').text($(this).text());
		}
	})
	$(document).on('click','.selectinput li',function(e){
	    var id=$(this).attr("data-id");
	   	$("#payType").val(id);
	});
});
</script>
</body>
</html>