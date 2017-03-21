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
	<script src="/plug_new/bootstrap/moment.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/daterangepicker.js" type="text/javascript" ></script>
	<script src="/js_new/common.js" type="text/javascript" ></script>
	<script type="text/javascript" src="/js_new/area.js"></script>
	<script type="text/javascript" src="/js_new/location.js"></script>
	<script src="/js_new/addCity.js" type="text/javascript" ></script>
	<script src="/js_new/base.js" type="text/javascript" ></script>
    <title>4s店详情</title>
    <script type="text/javascript">
    
	
    function showPage(number){
    	var id = $("#id").val();
		window.location.href="/crm/info/orderDetails.html?pageNo="+number+"&id="+id;
	}
	
	function showList(pid){
		window.location.href="/crm/info/list.html?pid="+pid;
	}
	function stopList(pid){
		window.location.href="/crm/info/stopList.html?pid="+pid;
	}
	
	function stop(id){
		var status = -2;
		$.ajax({
		 	url: "/crm/info/stop.html",
		 	type: "POST",
		 	data:{"id":id,"status":status},
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
	
    function update(id, type,ssssName, ssssAddress,bankAccountName, bankName 
        	,bankNumber, bankAddress, linkMan, identityCard, linkTel,userId
        	,provinceCode,provinceName,cityCode,cityName,goodsProfit,fixateProfit,firstGoodsMoney){
    	$("#id").val(id);
    	$("#userId").val(userId);
    	if(type==1){
    		$(".rowskin>.selectcontent").text("服务站");
    	}
    	if(type==2){
    		$(".rowskin>.selectcontent").text("代销商");
    	}
    	$("#edit1_Account").val(linkTel);
    	$("#edit1_person").val(linkMan);
    	$("#edit1_shopName").val(ssssName);
    	$("#provinceId").val(provinceCode);
    	$("#provinceName").val(provinceName);
    	$("#provinceName").siblings("em").text(provinceName);
    	$("#cityId").val(cityCode);
    	$("#cityName").val(cityName);
    	$("#cityName").siblings("em").text(cityName);
    	$("#edit2_address").val(ssssAddress);
    	$("#edit3_address").val(bankAddress);
    	$("#edit3_Name").val(bankAccountName);
    	$("#edit3_number").val(bankNumber);
    	$("#edit3_bank").val(bankName);
    	$("#edit3_id").val(identityCard);
    	$("#edit4_lra").val(fixateProfit);
    	$("#edit4_lrb").val(goodsProfit);
    	$("#edit4_lrc").val(firstGoodsMoney);
    	$("#add_1").text("修改");
    	$(".add_layer1").show();
    	
    	if(type==1){
    		$("#add1_Type").text("服务站");
    	}if(type==2){
    		$("#add1_Type").text("代销商");
    	}
    	$("#add1_Account").text(linkTel);
    	$("#add1_person").text(linkMan);
    	$("#add1_shopName").text(ssssName);
    	$("#add2_address1").text(provinceName);
    	$("#add2_address2").text(cityName);
    	$("#add2_address").text(ssssAddress);
    	$("#add3_bank").text(bankName);
    	$("#add3_number").text(bankNumber);
    	$("#add3_Name").text(bankAccountName);
    	$("#add3_address").text(bankAddress);
    	$("#add3_id").text(identityCard);
    	$("#add4_lra").text(fixateProfit);
    	$("#add4_lrb").text(goodsProfit);
    	$("#add4_lrc").text(firstGoodsMoney);
    }
    
    function check(){
		var linkTel = $("#edit1_Account").val();
		var id = $("#id").val();
		if(id == null || id  == ''){
    		var b = true;
    		$.ajax({
                url: "/crm/info/check.html?tel="+linkTel,
                type: "POST",
                dataType: "json",
                async: false,
                success: function (obj) {
                	if ( !checkCode( obj ) ) {
                		b = false;
     					return;
     			    }
                }
           });
    	   if(!b){
    		   return false;
    	   }
		}
		return true;
	}
    </script>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<input type="hidden" name="id" id="id" value="${city.id }">
<div class="right-bottom">
	<div class="right-top" style="left: 280px;">
        <div class="input-list">
            <input type="text">
            <span class="search-icon ripple-event"></span>
            <div class="setDateBox">
                <input type="text" name="daterange" class="daterange">
                <i class="select-arrow"></i>
                <i class="date-icon"></i>
            </div>
        </div>
        <input type="hidden" name="pageNo" id="pageNo" value="">
    </div>
    <div class="a_content">
    	   <div class="tab-list" style="left: 45px; margin-top: -30px;">
	            <ul class="Operation_tag">
	              <a href="/crm/info/info.html">4s店</a>
	              <b class="Operation_icon"></b>
	              <a href="###">4s店详情</a>
	            </ul>
            </div>
	    	<table class="Operation Operation1">
	    		<tr>
	    			<th>支付单号</th>
	    			<th>订单号</th>
	    			<th>账号</th>
	    			<th>时间</th>
	    			<th>支付类型</th>
	    			<th>支付金额/利润</th>
	    			<th>状态</th>
	    			<th>操作</th>
	    		</tr>
	    		<c:forEach items="${dataList}" var="item">
	    		<tr>
	    			<td>${item.payNumber}<i class="Operationicon Operationicon1"></i></td>
	    			<td>${item.orderNumber}</td>
	    			<td>${item.userName}</td>
	    			<td><fmt:formatDate value="${item.createDateTime}" pattern="yyyy.MM.dd"/></td>
	    			<td>支付宝</td>
	    			<td>
	    				<p class=" bold red">${item.totalPrice}</p>
	    				<p>${item.discountPrice}</p>
	    			</td>
	    			<td>
	    				<c:choose>
							<c:when test="${item.status==10 }">待付款</c:when>
							<c:when test="${item.status==20 }">已付款</c:when>
							<c:when test="${item.status==30 }">配货中</c:when>
							<c:when test="${item.status==40 }">已发货</c:when>
							<c:when test="${item.status==50 }">已完成</c:when>
							<c:otherwise>已关闭</c:otherwise>
						</c:choose>
	    			</td>
	    			<td><a href="/crm/info/orderDetail.html?orderNumber=${item.orderNumber}">详情</a></td>
	    		</tr>
	    		</c:forEach>
	    	</table>
	    <div class="Operationicon_right">
	    	<h3 class="Operationicon_point">
		    	<span>1</span>
		    	<span>2</span>
		    	<span>3</span>
	       </h3>
	       <div class="Operationicon_content">
		     <div class="Operationicon_box">
		       	<span>${city.ssssName}</span>
		       	<span>ID：${city.ssssCode}</span>
		     </div> 
		     <div class="Operationicon_money">
		     	<div class="Op_box">
		     		<h3>配送利润</h3>
		     		<span class="Op_icon Op_icon1"></span>
		     		<p>${city.goodsProfit}</p>
		     	</div>
		     	<div class="Op_box">
		     		<h3>固定利润</h3>
		     		<span class="Op_icon Op_icon2"></span>
		     		<p>${city.fixateProfit}</p>
		     	</div>
		     	<div class="Op_box">
		     		<h3>首批提货款</h3>
		     		<span class="Op_icon Op_icon3"></span>
		     		<p>${city.firstGoodsMoney}</p>
		     	</div>
		     </div> 
		     <div class="Op_name">
		     	<div class="Op_name_content Op_name_content1 "><span class="name_img name_img1"></span></div>
		        <div class="Op_name_content Op_name_content2">
		     		<p>${city.linkMan}</p>
		     		<p>${city.linkTel}</p>
		     		<p>${city.identityCard}</p>
		     		<i class="Op_sanjiao"></i>
		        </div>
		     </div>	
		      <div class="Op_name">
		     	<div class="Op_name_content Op_name_content1 "><span class="name_img name_img2"></span></div>
		        <div class="Op_name_content Op_name_content2">
		     		<p  class="name-font name-margin">${city.ssssAddress}</p>
		     		<!-- <p>11111 2542 2625 2415</p> -->
		     		<i class="Op_sanjiao"></i>
		        </div>
		     </div>	
		      <div class="Op_name">
		     	<div class="Op_name_content Op_name_content1 "><span class="name_img name_img3"></span></div>
		        <div class="Op_name_content Op_name_content2">
		     		<p>${city.bankNumber}</p>
		     		<p>${city.bankAccountName}</p>
		     		<p>${city.bankAddress }</p>
		     		<i class="name_bl name_bl1"></i>
		     		<i class="Op_sanjiao"></i>
		        </div>
		     </div>	
	       </div>
	    </div>
	    <jsp:include page="../include/pages_new.jsp"></jsp:include>
      </div>
</div>
</body>
</html>
