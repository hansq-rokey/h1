<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/order-detail.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/aftersalemanage.js" type="text/javascript" ></script>
    <title>订单管理</title>
</head>
<body>
    <section>
        <a href="/financial/queryList.html"><span class="light">财务统计</span>></a>
        <span class="darker">订单详情</span>
        <c:forEach items="${dataList}" var="order" varStatus="st">
        <div class="content">
        	<span>订单号：</span>
        	<span class="text">${order.orderNumber}</span>
        	<span>客户：</span>
        	<span class="text">${reciver.reveiveUserName }</span>
        	<span>联系电话：</span>
        	<span class="text">${reciver.mobilePhone }</span>
        	<span>配送地址：</span>
        	<span class="text">${reciver.provinceName }${reciver.cityName }${reciver.districtName }${reciver.detailAddress }</span>
        </div>
        <div class="route">
        	<ul>
        	<c:forEach items="${dictCodeList }" var="item" varStatus="status">
        	<c:if test="${status.index==st.index }">
	        	<c:forEach items="${item }" var="dc">
	        			<li class="${dc.flow ? 'orange':'' }">
							<span>${dc.dictCodeName }</span>
							<c:if test="${dc.flow }">
								<span class="time"><fmt:formatDate value="${dc.orderHistory.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
							</c:if>
						</li>
	        		
	        	</c:forEach>        	
        	</c:if>
        	</c:forEach>
        	</ul>
        </div>
        <table>
        <thead>
        	<tr>
        		<td>序号</td>
        		<td>名称</td>
        		<td>规格</td>
        		<td>价格</td>
        		<td>数量</td>
        		<td>小计</td>
        	</tr>
        </thead>
        <tbody>
        	<c:forEach items="${order.orderItems }" var="item" varStatus="statusItem">
	        	<tr>
	        		<td>${statusItem.index+1}</td>
	        		<td>${item.productTitle }</td>
	        		<td>${item.productModelFormatName }</td>
	        		<td>${item.discountUnitPrice }</td>
	        		<td>${item.num }</td>
	        		<td><fmt:formatNumber type="number" value="${item.discountUnitPrice*item.num }" pattern="0.00" maxFractionDigits="2"/></td>
	        	</tr>
        	</c:forEach>
        </tbody>
        </table>
        <div class="foot-box">
            <p><span class="type-name">商品总额:</span><span  class="type-value"><fmt:formatNumber type="currency" value="${pay.payShould }" minFractionDigits="2" maxFractionDigits="2"/></span> </p>
            <!-- p><span class="type-name">白熊积分:</span><span  class="type-value yellow">￥123</span> </p> -->
            <p><span class="type-name">活动优惠:</span><span  class="type-value yellow"><fmt:formatNumber type="currency" value="${pay.payShould-pay.payReal }" minFractionDigits="2" maxFractionDigits="2"/></span> </p>
            <p><span class="type-name">运费:</span><span  class="type-value yellow">0</span> </p>
            <p style="margin-top:10px;"><span class="type-name">实付:</span><span  class="type-value yellow" style="font-size:18px;"><fmt:formatNumber type="currency" value="${pay.payReal }"  minFractionDigits="2" maxFractionDigits="2"/></span> </p>
        </div>
        
        </c:forEach>
    </section>
</body>
</html>
