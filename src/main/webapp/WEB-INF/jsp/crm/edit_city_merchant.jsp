<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/daterangepicker-bs3.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/common.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/agentadd_city.css" />
    <title>编辑代理商</title>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<div class="right-part">
	<jsp:include page="../include/top_new.jsp"/>
 	<div class="agentadd-city-box">
 		<form action="/crm/cityMerchant/save.html" onsubmit ="return sava()"  method="post">
 			<input type="hidden" name="id" id="id" value="${city.id }">
			<input type="hidden" name="userId" id="userId" value="${city.userId }">
			<div class="agentadd-city row ">
				<div class="col-lg-6 col-md-6 agentadd-city-comm">
					<span class="agetadd-txt age-tar">上级代理：</span>
					<div class="agetaddselect" id="agetaddselect">
						<em class="age-tac" data-id="${city.parentCityMerchantId }">
							${city.parentCityMerchantId==null || city.parentCityMerchantId==0?"--选择--":parentCity.cityMerchantName}
						</em>
						<input type="hidden" name="parentCityMerchantId" id="age-MerchantId" value="${city.parentCityMerchantId }">
						<ul class="agetadd-mian" id="top_agetadd">
							<c:forEach items="${merchants }" var="city" >
								<li data-id="${city.id }">${city.cityMerchantName }</li>
							</c:forEach>
						</ul>
					</div>
                </div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm">
					<span class="agetadd-txt age-tar">类型：</span>
					<div class="agetaddselect" id="agetaddselect">
						<em class="age-tac" data-id="${city.level }">
						<c:forEach items="${types }" var="item">
							<c:if test="${item.dictCodeValue==city.level }">
								${item.dictCodeName }
							</c:if>
						</c:forEach>
						</em>
						<input type="hidden" name="level" id="age_MerchantType" value="${city.level }">
						<ul class="agetadd-mian" id="agetadd-mian">
							<c:forEach items="${types }" var="item">
								<li data-id="${item.dictCodeValue}">${item.dictCodeName }</li>
							</c:forEach>
						</ul>
					</div>
               </div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">电话号：</span> <input type="text" class="clear_dataval" value="${city.linkTel }"  readonly onchange="verifyNumber(this)" name="linkTel" placeholder="电话号"  ></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">负责人：</span> <input type="text" class="clear_dataval"  name="linkMan" value="${city.linkMan }" placeholder="负责人" oninput="edit(this.id,'add1_person')"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">公司名称：</span> <input type="text" class="clear_dataval"  name="cityMerchantName" value="${city.cityMerchantName }" placeholder="公司名称"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">开户行：</span> <input type="text"  class="clear_dataval"  name="bankAddress" placeholder="开户行" value="${city.bankAddress }"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">开户名：</span> <input type="text"  class="clear_dataval"  name="bankAccountName" placeholder="开户名" value="${city.bankAccountName }"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">银行账号：</span> <input type="text" data-txt="number" id="banklenght" class="clear_dataval" onchange="verifBank(this)" value="${city.bankNumber }"  name="bankNumber"  placeholder="银行账号"> </div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">银行名：</span> <input type="text"  class="clear_dataval" name="bankName" placeholder="银行名" value="${city.bankName }"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">身份证：</span> <input id="carId" class="clear_dataval" onchange="verifBank(this)" name="identityCard" type="text"  placeholder="身份证" value="${city.identityCard }"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">提货系数：</span> <input type="text" class="clear_dataval"  name="fixateProfit" id="pickUp"  data-txt="number" value="${city.fixateProfit }"   placeholder="提货系数"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">区域系数：</span> <input type="text" class="clear_dataval"  name="areaProfit" id="areaProfit"  data-txt="number" value="${city.areaProfit }"   placeholder="区域系数"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">保证金：</span> <input type="text" class="clear_dataval"  name="bondMoney" data-txt="number" id="CashDeposit" readonly  placeholder="保证金" value="${city.bondMoney }"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">首批提货款：</span> <input type="text" class="clear_dataval"  name="firstGoodsMoney" data-txt="number" id="firstPayment" readonly  placeholder="首批提货款" value="${city.firstGoodsMoney }"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">优惠券冻结金额：</span> <input type="text" class="clear_dataval" name="freezeCoupon"  data-txt="number" readonly value="${city.freezeCoupon }"   placeholder="优惠券冻结金额" readonly></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">地址：</span> 
					 <div class="addressSelect clearfix" id="address">
						 <div class="sele prov">
		                    <em class="txt">${city.provinceName }</em>
		                    <input type="hidden" value="${city.provinceCode }" id="provinceId" name="provinceCode">
		                    <input type="hidden" value="${city.provinceName }" id="provinceName" name="provinceName">
		                    <ul class="loc loc_province"  data-odd="1"></ul>
		                  </div>
		                  <div class="sele city">
		                      <em class="txt">${city.cityName }</em>
		                      <input type="hidden" value="${city.cityCode }" id="cityId" name="cityCode">
		                       <input type="hidden" value="${city.cityName }" id="cityName" name="cityName">
		                       <ul class="loc loc_city" data-odd="1"></ul>
		                  </div>
		                  <div class="sele town">
		                      <em class="txt" id="towna">${city.countyName }</em>
		                      <input type="hidden" value="${city.countyCode }" id="countyId" name="countyCode">
		                      <input type="hidden" value="${city.countyName }" id="countyName" name="countyName">
		                      <ul class="loc loc_town "  data-odd="1"></ul>                          
		                  </div>
	                  </div> 
                  </div>
                  <div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">详细地址：</span> <input type="text" class="clear_dataval" value="${city.cityMerchantAddress }" name="cityMerchantAddress"  placeholder="详细地址"></div>
				<div class="col-lg-6 col-md-6 agentadd-city-comm"><span class="agetadd-txt">负责地区：</span>
					 <div class="addressSelect clearfix"  id="addressSelect">
	                        <div class="sele prov">
	                            <em class="txt">省份</em>
	                            <input type="hidden" value="" id="provinceId" name="provinceId">
	                            <input type="hidden" value="" id="provinceName_area" name="provinceName_area">
	                            <ul class="loc loc_province" data-odd="2"></ul>
	                        </div>
	                        <div class="sele city">
	                            <em class="txt">城市</em>
	                            <input type="hidden" value="" id="cityId_area" name="cityId">
	                            <input type="hidden" value="" id="cityName_area" name="cityName_area">
	                            <ul class="loc loc_city" data-odd="2"></ul>                           
	                        </div>
	                        <div class="sele town">
	                            <em class="txt" id="townb">区/县</em>
	                            <input type="hidden" value="" id="countyId_area" name="countyId">
	                            <input type="hidden" value="" id="countyName_area" name="countyName_area">
	                            <ul class="loc loc_town loc_add" data-odd="2"></ul>                          
	                        </div>
	                        <i class="add_address" id="add_address">+</i>
                     </div>
                   <input type="hidden" id="sele_prov">
	               <input type="hidden" id="sele_city">
	               <input type="hidden" id="sele_town">
	               <input type="hidden" id="this-value">
	               	<div class="clear_datatxt clear_address" id="add2_addressc">
	               		<c:forEach items="${areas }" var="area">
	               			<c:if test="${area.areaName!=null }">
	               				<span class="sele_box">${area.areaName }<i class="delect_city">+<i></i></i></span>
	               			</c:if>
	               		</c:forEach>
	               	</div>
					<div class="clear_datatxt" id="add2_value">
					<c:forEach items="${areas }" var="area">
               			<c:if test="${area.areaName!=null }">
               				<input type="hidden" name="areaCode" value="${area.areaCode }">
               			</c:if>
	               	</c:forEach>
	               	</div>
				</div>
			</div>
			  <div class="btn_add age-tac">
			        <input type="submit" class="add_ct add_ct submit_addct" value="保存">
     		  </div>
 		</form>
 	</div>
</div>
<script type="text/javascript" src="/plug_new/jQuery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="/js_new/common.js"></script>
<script type="text/javascript" src="/js_new/area.js"></script>
<script type="text/javascript" src="/js_new/location.js"></script>
<script type="text/javascript" src="/js_new/agentadd_city.js"></script>
<script type="text/javascript">
	$(function(){
		agentShowLocation();
	})
</script>
 
</body>
</html>
