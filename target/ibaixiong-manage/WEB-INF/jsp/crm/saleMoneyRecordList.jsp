<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="/plug_new/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/plug_new/bootstrap/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
	<link href="/css_new/common.css" rel="stylesheet" type="text/css">
	<link href="/css_new/balanceDetail.css" rel="stylesheet" type="text/css">
	<script src="/plug_new/jQuery/jquery-1.9.1.min.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/bootstrap.min.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/moment.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/daterangepicker.js" type="text/javascript" ></script>
	<script src="/js_new/common.js" type="text/javascript" ></script>
    <title>总销售额详情</title>
    <script type="text/javascript">
    function showPage(number){
    	var id = $("#merchantId").val();
    	window.location.href="/crm/cityMerchant/saleRecordList.html?pageNo="+number+"&merchantId="+id;
	}
    </script>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<div class="right-bottom">
	<div class="right-top" style="left: 280px;">
		<div class="tag-box">
			<a class="tag-list" href="/crm/cityMerchant/cityMerchant.html">城市运营中心</a>
			<a class="tag-list" href="/crm/cityMerchant/orderDetails.html?id=${merchantId }">城市运营中心详情</a>
			<a class="tag-list" href="/crm/cityMerchant/saleRecordList.html?merchantId=${merchantId }">总销售额详情</a>
		</div>
		<input type="hidden" name="pageNo" id="pageNo" value="">
	    <input type="hidden" id="merchantId" name="merchantId" value="${merchantId }">
	</div>
	<div class="a_content">
	   	<table class="balanceDetail">
	   		<thead>
	   			<tr>
		   			<th>时间</th>
		   			<th>订单号</th>
		   			<th>代理商类型</th>
		   			<th>改变金额</th>
		   			<th>备注</th>
		   			<th>修改形式</th>
		   			<th>总销售额</th>
	   			</tr>
	   		</thead>
	   		<tbody>
	   		<c:forEach items="${records }" var="record">
	   			<tr>
		   			<td><fmt:formatDate value="${record.createDateTime }" pattern="yyyy.MM.dd"/></td>
		   			<td>${record.orderNumber }</td>
		   			<c:forEach items="${types }" var="item">
		   				<c:if test="${record.merchantLevel==item.dictCodeValue }">
		   					<td>${item.dictCodeName }</td>
		   				</c:if>
		   			</c:forEach>
		   			<td class="editor-color">
		   				<fmt:formatNumber value="${record.money }" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
		   			</td>
		   			<td>${record.remark }</td>
		   			<c:forEach items="${codes }" var="code">
		   				<c:if test="${record.status==code.dictCodeValue }">
		   					<td>${code.dictCodeName }</td>
		   				</c:if>
		   			</c:forEach>
		   			<td>
		   				<fmt:formatNumber type="currency" maxFractionDigits="2" minFractionDigits="2" value="${record.saleTotalMoney }"/>
		   			</td>
	   			</tr>
	   		</c:forEach>
	   		</tbody>
	   	</table>
	   	<jsp:include page="../include/pages_new.jsp"></jsp:include>
	</div>
  </div> 
</div>
</body>
</html>
