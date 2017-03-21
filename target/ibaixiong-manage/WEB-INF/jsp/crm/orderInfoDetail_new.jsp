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
	<link href="/css_new/adress.css" rel="stylesheet" type="text/css">
	<link href="/css_new/addCity.css" rel="stylesheet" type="text/css"/>
	<script src="/plug_new/jQuery/jquery-1.9.1.min.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/bootstrap.min.js" type="text/javascript" ></script>
	<script src="/js_new/common.js" type="text/javascript" ></script>
	<script type="text/javascript" src="/js_new/area.js"></script>
	<script src="/js_new/base.js" type="text/javascript" ></script>
    <title>订单详情</title>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<div class="right-bottom">
	<div class="right-top" style="left: 280px;"></div>
    <div class="a_content">
    	   <div class="b_content b_content1" style="margin-top: 35px;">
    	   	    <div class="tab-list" style="left:45px;top: -135px;">
		            <ul class="Operation_tag" style="margin-top: 90px;">
		              <a href="/crm/info/info.html">4s店</a>
		              <b class="Operation_icon"></b>
		              <a href="###" onclick="history.go(-1)">4s店详情</a>
		              <b class="Operation_icon"></b>
		              <a href="###">订单详情</a>
		            </ul>
                </div>
    	   	    <div class="order_box">
		        	<ul class="order-mian">
		        	 <c:forEach items="${orderStatusList }" var="item">
		        		<li>
		        		  <p  class="order_text">${item.dictCodeName }</p>
		        		  <p  class="${item.flow ?'order_plan order_planHover':'order_plan' }"></p>
		        		  <c:if test="${item.flow }">
		        		  	<p  class="order_date"><fmt:formatDate value="${item.orderHistory.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
		        		  </c:if>
		        		</li>
		        	 </c:forEach>
		        	 </ul>
                 <a class="order_btn" href="###">查看物流</a>
                </div>
                <div class="table-responsive">
			    	<table class="table">
			    		<tr>
			    			<th>订单号</th>
			    			<th>包装码</th>
			    			<th>名称</th>
			    			<th>单价</th>
			    			<th>数量</th>
			    			<th>总额</th>
			    		</tr>
			    		<c:forEach items="${dataList}" var="item">
			    		<tr>
			    			<td>${item.orderNumber}</td>
			    			<td>${item.productId}</td>
			    			<td>
		                      <div class="order_b">
		                      	 <span class="Op_img">
		                      	 	<c:forEach items="${item.pics }" var="pic">
		                      	 		<img src="${pic.url }">
		                      	 	</c:forEach>
		                      	 <span>
		                      	 	<a href="###">${item.productTitle }</a>
		                      	 	<span>
		                      	 		<c:if test="${item.orderItemExtend!=null }">
		                      	 			${item.orderItemExtend.length }cm*${item.orderItemExtend.width }cm*${item.orderItemExtend.height }cm
		                      	 		</c:if>
		                      	 	</span>
		                      	 </span>
		                      </div>
			    			</td>
			    			<td>${item.unitPrice}</td>
			    			<td>${item.num}</td>
			    			<td>${item.totalPrice }</td>
			    		</tr>
			    		</c:forEach>
			    	</table>
	    		</div>
	     </div>	
	    <div class="Operationicon_right" style="margin-top:20px">
	     <div class="order-wrap">
	     	<p ><i class="order-icon order-icon1"></i>${orderInfomation.orderNumber }</p>
	     	<p>
		     	<span><i class="order-icon order-icon3"></i>${orderInfomation.reveiveUserName }</span>
		     	<span><i class="order-icon order-icon4"></i>${orderInfomation.mobilePhone }</span>
		    </p>
		    <p><i class="order-icon order-icon5"></i>${orderInfomation.provinceName}${orderInfomation.cityName}${orderInfomation.districtName}${orderInfomation.detailAddress }</p> 	
	     </div>
	    <!--  <div class="order-lr">
	     	<div class="order-top">
	     		<p>总利润</p>
	     		<p>100</p>
	     	</div>
	     	<div class="order-bottom">
	     		<div class="order_server">
	     		  <p class=" order-a order-a1"></p>
	     		  <p class="order-a order-a2"></p>
	     		</div>
	     		<div class="order_list">
	     			<div class="order_mian">
	     			  <p>1000000</p>
	     			  <p>宁波中山街道</p>
	     			</div>
	     			<div class="order_mian">
	     			  <p>100</p>
	     			  <p>宁波中山街道</p>
	     			</div>
	     		</div>
	     	</div>
	     </div> -->
	    </div>
      </div>
</div>
</body>
</html>
