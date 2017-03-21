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
	<script src="../../../plug_new/layer/layer.js" type="text/javascript" ></script>
    <title>城市运营中心详情</title>
    <script type="text/javascript">
    function showPage(number){
    	var id = $("#id").val();
    	window.location.href="/crm/cityMerchant/orderDetails.html?pageNo="+number+"&id="+id;
	}
    </script>
</head>
<body>
<div class="city_box">
	<jsp:include page="../include/left_new.jsp"/>
	<div class="right-bottom">
		<div class="right-top" style="left:280px;">
	        <div class="input-list" style="display:block">
	           <!--  <input type="text">
	            <span class="search-icon ripple-event"></span>
	            <div class="setDateBox">
	                <input type="text" name="daterange" class="daterange">
	                <i class="select-arrow"></i>
	                <i class="date-icon"></i>
	            </div> -->
	            <div class="tab-list">
		            <ul class="Operation_tag">
		              <a href="/crm/cityMerchant/cityMerchant.html">城市运营中心</a>
		              <b class="Operation_icon"></b>
		              <c:if test="${parentId!=null }">
		              	<a href="/crm/cityMerchant/downMerchant.html?parentId=${parentId }">${parentCity.cityMerchantName }</a>
		              	<b class="Operation_icon"></b>
		              </c:if>
		              <a href="/crm/cityMerchant/orderDetails.html?id=${city.id }">${city.cityMerchantName }-详情</a>
		            </ul>
           		 </div>
	        </div>
	        <input type="hidden" name="pageNo" id="pageNo" value="">
	        <input type="hidden" id="id" name="merchantId" value="${city.id }">
	        <input type="hidden" id="level" name="level" value="${city.level }">
	    </div>
	    <div class="a_content">
         <div class="table_scoll">  
		    <div class="table-responsive table_wrap">
		      <table class="table">
		        <thead>
		         	<tr class="table_h80">
			     		<th class="table_w">支付单号</th>
			         	<th class="table_w">订单号</th>
			         	<th>账号</th>
			         	<th>时间</th>
			         	<th>金额</th>
			         	<th>状态</th>
			         	<th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pageInfo.list}" var="item">
			         <tr class="table_h80">
			          	<td class="table_w">${item.payNumber}<i class="Operationicon Operationicon1"></i></td>
			          	<td class="table_w">${item.orderNumber}</td>
			          	<td>${item.userName}</td>
			          	<td><fmt:formatDate value="${item.createDateTime }" pattern="yyyy.MM.dd"/></td>
			          	<td>
			          		<p>${item.totalPrice}</p>
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
			          	<td>
			          		<a href="/crm/cityMerchant/orderDetail.html?orderNumber=${item.orderNumber}">详情</a>
			          	</td>
			         </tr>
		           </c:forEach>
		        </tbody>
		      </table>
			</div>
			<div class="box_Operation_list box_Operation_listright">
				 	<div class="Operationicon_right">
				       <div class="Operationicon_content">
					     <p class="Operationicon_box">${city.cityMerchantName}<span>ID：${city.merchantCode}</span></p> 
					     <ul class="Operationicon_money">
					     	<li class="Op_box">
					     		<h3>配送利润</h3>
					     		<span class="Op_icon Op_icon1"></span>
					     		<p>${city.areaProfit}</p>
					     	</li>
					     	<li class="Op_box">
					     		<h3>固定利润</h3>
					     		<span class="Op_icon Op_icon2"></span>
					     		<p>${city.fixateProfit}</p>
					     	</li>
					     	<li class="Op_box">
					     		<h3>首批提货款</h3>
					     		<span class="Op_icon Op_icon3"></span>
					     		<p>总额:<fmt:formatNumber value="${city.firstGoodsMoney}" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
					     		</p>
					     		<p style="color:red">余额:<fmt:formatNumber value="${city.firstGoodsMoneyBalance}" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
					     		</p>
					     		<a href="javascript:" class="add_paymen commtxtcolor" id="add_paymen">追加</a>
					     		<a href="javascript:" class="minus_paymen commtxtcolor" id="minus_paymen">扣除</a>
					     		<a href="firstGoodsMoneyRecordList.html?merchantId=${city.id }" class="commtxtcolor">详情</a>
					     	</li>
					     </ul>
					    <div class="Op_name">
					    	<span class="name_img name_img4 name_imgheigth"></span>
					        <div class="Op_name_content Op_name_content2">
					     		<p>优惠券冻结金额：<fmt:formatNumber value="${city.freezeCoupon}" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
					     		</p>
					     		<p style="color:red">优惠券解冻金额：<fmt:formatNumber value="${city.unfreezeCoupon}" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
					     		</p>
					     		<a href="javascript:" class="add_coupon commtxtcolor" id="add_coupon">追加</a>
					     		<a href="javascript:" class="minus_coupon commtxtcolor" id="minus_coupon">扣除</a>
					     		<a href="couponRecord.html?merchantId=${city.id }" class="commtxtcolor">记录详情</a>
					     		<i class="Op_sanjiao"></i>
					        </div>
					     </div>
					    <div class="Op_name">
				     		<span class="name_img name_img4 name_imgheigth"></span>
					        <div class="Op_name_content Op_name_content2">
					     		<p style="color:red">总销售额：<fmt:formatNumber value="${city.saleTotalMoney}" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
					     		</p>
					     		<a href="saleRecordList.html?merchantId=${city.id }" class="commtxtcolor">记录详情</a>
					        </div>
					     </div>	
					     <div class="Op_name">
				     		<span class="name_img name_img4 name_imgheigth"></span>
					        <div class="Op_name_content Op_name_content2">
					     		<p style="color:red">保证金总额：<fmt:formatNumber value="${city.bondMoney}" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
					     		</p>
					     		<p style="color:red">待返还保证金：<fmt:formatNumber value="${city.bondMoneyBalance}" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
					     		</p>
					     		<a href="bondRecord.html?merchantId=${city.id }" class="commtxtcolor">记录详情</a>
					        </div>
					     </div>
					     <div class="Op_name">
				     		<span class="name_img name_img4 name_imgheigth"></span>
					        <div class="Op_name_content Op_name_content2">
					     		<p style="color:red">返利金额：<fmt:formatNumber value="${city.rebateMoney==null?0:city.rebateMoney}" type="currency" maxFractionDigits="2" minFractionDigits="2"/>
					     		</p>
					     		<a href="rebateRecord.html?merchantId=${city.id }" class="commtxtcolor">记录详情</a>
					        </div>
					     </div>		
					     <div class="Op_name">
				     		<span class="name_user name_imgheigth"></span>
					        <div class="Op_name_content Op_name_content2">
					     		<p>${city.linkMan}</p>
					     		<p>${city.linkTel}</p>
					     		<p>${city.identityCard}</p>
					     		<i class="Op_sanjiao"></i>
					        </div>
					     </div>	
					      <div class="Op_name">
				     		<span class="name_address name_imgheigth"></span>
					        <div class="Op_name_content Op_name_content2">
					     		<p  class="name-font name-margin">
					     			${city.provinceName}${city.cityName}${city.countyName}${city.cityMerchantAddress}
					     		</p>
					     		<i class="Op_sanjiao"></i>
					        </div>
					     </div>	
					      <div class="Op_name">
				     		<span class="name_bank name_imgheigth"></span>
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
				</div>
		<jsp:include page="../include/city_pages.jsp"></jsp:include>
	  </div>
	</div>
</div>
</div>	
</body>
</html>
