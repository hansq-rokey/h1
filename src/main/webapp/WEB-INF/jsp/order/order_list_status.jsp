<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="com.ibaixiong.entity.MallOrder"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="ordermanagetable refundlist">
   	<thead>
   		<tr>
			<td style="min-width:70px;" >订单号</td>
			<td style="min-width:70px;">产品名称</td>
			<td style="min-width:70px;">规格</td>
			<td style="min-width:60px;">数量</td>
			<td>用户名</td>
			<td>白熊号</td>
			<td style="min-width:160px;">下单日期</td>
			<td style="max-width:300px;">邮寄地址</td>
			<td style="max-width:200px;">备注</td>
			<td style="min-width:100px;">订单状态</td>
			<td style="min-width:100px;">订单金额</td>
			<!-- <td style="min-width:100px;">优惠金额</td> -->
			<td style="min-width:160px;">操作</td>
		</tr>
   	</thead>
   	<tbody>
   		<c:forEach items="${orderList }" var="order">
   			<c:forEach  items="${order.orderItems }" var="item" varStatus="itemStatus">
   				<c:choose>
   					<c:when test="${fn:length(order.orderItems)==1 }">
   						<tr>
       						<td><c:if test="${order.isCustomMade==1 }"><img alt="" src="/images/custom.png"> </c:if> ${order.orderNumber }</td>
       						<td>${item.productTitle }</td>
       						<td>${item.productModelFormatName }</td>
       						<td>x${item.num }</td>
       						<td>
       							<c:choose>
       								<c:when test="${order.email!=null }">${order.email }</c:when>
       								<c:when test="${order.phone!=null }">${order.phone }</c:when>
       								<c:otherwise>${order.bxNum }</c:otherwise>
       							</c:choose>
       						</td>
       						<td>${order.bxNum }</td>
       						<td><fm:formatDate value="${order.createDateTime }" pattern="YYYY/MM/dd HH:mm"/> </td>
       						<td style="max-width:300px;">
       							<p class="reasontd" title="${order.information.provinceName }${order.information.cityName }${order.information.districtName }${order.information.detailAddress }">
       							<c:if test="${order.information!=null }">
       								${order.information.provinceName }${order.information.cityName }${order.information.districtName }${order.information.detailAddress }
       							</c:if>
       							</p>
       						</td>
							<td style="max-width:200px;min-width:100px;">${order.remark }</td>
       						<td>
       							<c:forEach items="${orderStatusList }" var="statusItem">
       								<c:if test="${statusItem.dictCodeValue==order.status }">
       								${statusItem.dictCodeName }
       								</c:if>
       							</c:forEach>
       						</td>
       						<td>${order.shouldPayMoney }</td>
       						<%-- <td>${order.discountPrice }</td> --%>
       						<td>
       							<c:if test="${order.status==10 }">
	       							<a href="#" class="link" onclick="toUpdate('${order.orderNumber}','${order.shouldPayMoney }')">改价</a>
       							</c:if>
       							<a href="/order/detail.html?orderNumber=${order.orderNumber }" class="link">详情订单</a>
       							<a href="###" class="n_link" onclick="updateInvalid('${order.orderNumber}')">无效订单</a>
       						</td>
       					</tr>
   					</c:when>
   				<c:otherwise>
   					<c:if test="${itemStatus.first }">
   						<tr>
        					<td td rowspan="${fn:length(order.orderItems) }">${order.orderNumber }</td>
        					<td>${item.productTitle }</td>
        					<td>${item.productModelFormatName }</td>
        					<td>x${item.num }</td>
        					<td rowspan="${fn:length(order.orderItems) }">
	        					<c:choose>
	        						<c:when test="${order.email!=null }">${order.email }</c:when>
	        						<c:when test="${order.phone!=null }">${order.phone }</c:when>
	        						<c:otherwise></c:otherwise>
	        					</c:choose>
	        				</td>
	        				<td rowspan="${fn:length(order.orderItems) }">${order.bxNum }</td>
	        				<td rowspan="${fn:length(order.orderItems) }"><fm:formatDate value="${order.createDateTime }" pattern="YYYY/MM/dd HH:mm"/> </td>
	        				<td style="max-width:300px;" rowspan="${fn:length(order.orderItems) }">
								<p class="reasontd" title="${order.information.provinceName }${order.information.cityName }${order.information.districtName }${order.information.detailAddress }">	
								<c:if test="${order.information!=null }">
       								${order.information.provinceName }${order.information.cityName }${order.information.districtName }${order.information.detailAddress }
        						</c:if>
        						</p> 
							</td>
							<td rowspan="${fn:length(order.orderItems) }" style="max-width:200px;min-width:100px;">${order.remark }</td>
        					<td rowspan="${fn:length(order.orderItems) }">
        						<c:forEach items="${orderStatusList }" var="statusItem">
       								<c:if test="${statusItem.dictCodeValue==order.status }">
       								${statusItem.dictCodeName }
       								</c:if>
       							</c:forEach>
        					</td>
        					<td rowspan="${fn:length(order.orderItems) }">${order.shouldPayMoney }</td>
       						<%-- <td rowspan="${fn:length(order.orderItems) }">${order.discountPrice }</td> --%>
        					<td rowspan="${fn:length(order.orderItems) }">
	        					<c:if test="${order.status==10 }">
	       							<a href="#" class="link" onclick="toUpdate('${order.orderNumber}','${order.shouldPayMoney }')">改价</a>
       							</c:if>
	        					<a href="/order/detail.html?orderNumber=${order.orderNumber }" class="link">详情订单</a>
	        					<a href="###" class="n_link" onclick="updateInvalid('${order.orderNumber}')">无效订单</a>
	        				</td>
	        			</tr>
   					</c:if>
   					<c:if test="${itemStatus.index>0 }">
   						<tr>
	        				<td>${item.productTitle }</td>
	        				<td>${item.productModelFormatName }</td>
	        				<td>x${item.num }</td>
	        			</tr>
   					</c:if>
   				</c:otherwise>
   			</c:choose>
   		</c:forEach>
   	</c:forEach>
   </tbody>
</table>
