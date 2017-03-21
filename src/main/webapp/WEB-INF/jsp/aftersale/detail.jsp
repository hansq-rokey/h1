<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="../../../css/order-detail.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/aftersalemanage.js" type="text/javascript" ></script>
    <title>订单管理</title>
    <script>
 // 匿名函数
    $(function(){

    	// 文本框只能输入数字
    	var $numTxtBox = $('input[data-txt="number"]');// 锁键状态
    	$numTxtBox.keydown(function(e){// 绑定事件
    		var oEvent = e || window.event;// 兼容处理
    		if(!(oEvent.keyCode==46)&&!(oEvent.keyCode==8)&&!(oEvent.keyCode==9)&&!(oEvent.keyCode==37)&&!(oEvent.keyCode==39))// 键值
    		if(!((oEvent.keyCode>=48&&oEvent.keyCode<=57)||(oEvent.keyCode>=96&&oEvent.keyCode<=105))) // 键值
    			return false;
    		//oEvent.returnValue = false; // firefox 兼容问题
    	});
    	// 处理函数
    	$('.numbtn').on('click',function(){
    		var $txtBox = $(this).siblings('input[data-txt="number"]'),// 输入框
    		perprice = $txtBox.attr('data-price'),// 允许最大值
			max = parseInt($txtBox.attr('data-max'));// 允许最大值
		var _num = parseInt($txtBox.val());// 计算
		console.log(_num,perprice)
		// 是加是减
		if ($(this).hasClass('minusbtn')) {
			if(_num>0){
				_num-= 1;// 计算
				$txtBox.val(_num);// 设置数
				prices=perprice*_num;
				$(this).parent().parent().next().text(prices.toFixed(2));
			}
		}else{
			if(_num<max){
				_num=_num+1;
	        	$txtBox.val(_num);// 设置数
	        	prices=perprice*_num;
				$(this).parent().parent().next().text(prices.toFixed(2));
		        }
			};
    	});
    	
    	//确认退款
    	$('.refund-sure').on('click',function(){
    		var num=$('input[data-txt="number"]').val();
    		var prodId=$(this).attr('data-id');
    		var amount=$(this).parent().parent().find('.amount').text();
    		$.ajax({
		        url: "/u/car/add.htm",
		        type: 'post',
		        data:{ "prodId": prodId,"num":num,"amount":amount },
		        dataType: 'json',//here
		        success: function (data) {
		        	alertLayel(data.message);
		        }
		    });
    	})
    		
    });
    </script>
</head>
<body>
    <section>
        <a href="<%=basePath %>aftetSale/refund/list.html"><span class="light">售后款项</span>></a>
        <span class="darker">详情</span>
        <div class="content">
        	<span>订单号：</span>
        	<span class="text">${service.orderNumber}</span>
        	<span>客户：</span>
        	<span class="text">${receiver.reveiveUserName }</span>
        	<span>联系电话：</span>
        	<span class="text">${receiver.mobilePhone }</span>
        	<span>配送地址：</span>
        	<span class="text">${receiver.provinceName }${receiver.cityName }${receiver.districtName }${receiver.detailAddress }</span>
        </div>
        <%-- <div class="route">
        	<ul>
        		<li class="orange">
					<span>提交申请</span>
					<span class="time"><fmt:formatDate value="${status10.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</li>
        		<li class="${status20==null?'':'orange' }">
					<span>待签收</span>
					<c:if test="${status20!=null }"><span><fmt:formatDate value="${status20.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></c:if>
				</li>
				<li class="${status30==null?'':'orange' }">
					<span>审核中</span>
					<c:if test="${status30!=null }"><span><fmt:formatDate value="${status30.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></c:if>
				</li>
				<li class="${status40==null?'':'orange' }">
					<c:if test="${service.status<40 }"><span>同意/拒绝</span></c:if>
					<c:if test="${service.status>=41 }"><span>同意</span></c:if>
					<c:if test="${service.status==42 }"><span>同意/拒绝</span></c:if>
					<c:if test="${status40!=null }"><span><fmt:formatDate value="${status40.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></c:if>
				</li>
				<li class="${status50==null?'':'orange' }">
					<span>已退款</span>
					<c:if test="${status50!=null }"><span><fmt:formatDate value="${status50.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></c:if>
				</li>
        	</ul>
        </div> --%>
        <table>
        <thead>
        	<tr>
        		<td>序号</td>
        		<td>名称</td>
        		<td>规格</td>
        		<td>单价</td>
        		<td>数量</td>
        		<td>小计</td>
        		<!-- 
        		<td>退货数量</td>
        		<td>退货金额</td>
        		<td>操作</td> -->
        	</tr>
        </thead>
        <tbody>
        	<c:forEach items="${list }" var="item" varStatus="statusItem">
	        	<tr>
	        		<td>${statusItem.index+1}</td>
	        		<td>${item.productTitle }</td>
	        		<td>${item.productModelFormatName }</td>
	        		<td>${item.discountUnitPrice }</td>
	        		<td>${item.num }</td>
	        		<td><fmt:formatNumber type="number" value="${item.discountUnitPrice*item.num }" pattern="0.00" maxFractionDigits="2"/></td>
	        		<!-- 
	        		<td>
						<div class="change">
							<span class="minusbtn numbtn"></span>
							<input type="text" name="" data-txt="number" data-max="${item.num }" data-min="0" data-price="${item.discountUnitPrice }" class="number" value="${item.num }"/>
							<span class="plusbtn numbtn"></span>
							
						</div>
					</td>
	        		<td class="amount"><fmt:formatNumber type="number" value="${item.discountUnitPrice*item.num }" pattern="0.00" maxFractionDigits="2"/></td>
	        		<td><a href="javascript:;" data-id="${item.productId }" class="refund-sure">确认退款</a></td>
	        		 -->
	        	</tr>
        	</c:forEach>
        </tbody>
        </table>
     </section>
</body>
</html>
