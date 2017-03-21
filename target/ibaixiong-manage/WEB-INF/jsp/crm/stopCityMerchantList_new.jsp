<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>城运商管理</title>
    <script type="text/javascript">
    
 	function showPage(number){
 		window.location.href="/crm/cityMerchant/stopCityMerchant.html?pageNo="+number;
 	}
	
	function showList(){
		window.location.href="/crm/cityMerchant/cityMerchant.html";
	}
	function disShowList(){
		window.location.href="/crm/cityMerchant/stopCityMerchant.html";
	}
	
	function startMerchant(id){
		var status = 1;
		$.ajax({
		 	url: "/crm/cityMerchant/start.html",
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
	
    function update(id,cityMerchantName, cityMerchantAddress,bankAccountName, bankName 
        	,bankNumber, bankAddress, linkMan, identityCard, linkTel,userId
        	,provinceCode,provinceName,cityCode,cityName,areaProfit,fixateProfit,firstGoodsMoney){
    	$("#id").val(id);
    	$("#userId").val(userId);
    	$(".rowskin>.selectcontent").text("城市运营中心");
    	$("#edit1_Account").val(linkTel);
    	$("#edit1_person").val(linkMan);
    	$("#edit1_shopName").val(cityMerchantName);
    	$("#provinceId").val(provinceCode);
    	$("#provinceName").val(provinceName);
    	$("#provinceName").siblings("em").text(provinceName);
    	$("#cityId").val(cityCode);
    	$("#cityName").val(cityName);
    	$("#cityName").siblings("em").text(cityName);
    	$("#edit2_address").val(cityMerchantAddress);
    	$("#edit3_address").val(bankAddress);
    	$("#edit3_Name").val(bankAccountName);
    	$("#edit3_number").val(bankNumber);
    	$("#edit3_bank").val(bankName);
    	$("#edit3_id").val(identityCard);
    	$("#edit4_lra").val(fixateProfit);
    	$("#edit4_lrb").val(areaProfit);
    	$("#edit4_lrc").val(firstGoodsMoney);
    	$("#add_1").text("修改");
    	$(".add_layer1").show();
    	
    	$(".rowskin>.selectcontent").text("城市运营中心");
    	$("#add1_Account").text(linkTel);
    	$("#add1_person").text(linkMan);
    	$("#add1_shopName").text(cityMerchantName);
    	$("#add2_address1").text(provinceName);
    	$("#add2_address2").text(cityName);
    	$("#add2_address").text(cityMerchantAddress);
    	$("#add3_bank").text(bankName);
    	$("#add3_number").text(bankNumber);
    	$("#add3_Name").text(bankAccountName);
    	$("#add3_address").text(bankAddress);
    	$("#add3_id").text(identityCard);
    	$("#add4_lra").text(fixateProfit);
    	$("#add4_lrb").text(areaProfit);
    	$("#add4_lrc").text(firstGoodsMoney);
    }
    
    function check(){
		var linkTel = $("#edit1_Account").val();
		var id = $("#id").val();
		if(id == null || id  == ''){
    		var b = true;
    		$.ajax({
                url: "/crm/cityMerchant/check.html?tel="+linkTel,
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
<div class="addBtn"><i></i></div>
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
     <div class="tab-list">
            <ul>
                <li class="ripple-event" onclick="showList()">正常</li>
                <li class="money_on  ripple-event" onclick="disShowList()">禁用</li>
            </ul>
        </div>
	    	<table class="add_content">
	    		<tr>
	    			<th>账号</th>
	    			<th>公司名称</th>
	    			<th>类型</th>
	    			<th>固定利润/区域利润</th>
	    			<th>操作</th>
	    		</tr>
	    		<c:forEach items="${dataList}" var="item">
	    		<tr>
	    			<td>${item.linkTel}</td>
	    			<td>${item.cityMerchantName}</td>
	    			<td>城市运营中心</td>
	    			<td>
	    				<p class=" bold red">
	    					<c:if test="${item.fixateProfit!=null}">
	    						${item.fixateProfit}%
	    					</c:if>
							<c:if test="${item.areaProfit!=null}">
	    						/${item.areaProfit}%
	    					</c:if>
	    				<p>
	    			</td>
	    			<td>
	    				<a class="blue add-margin" data-type="1" href="/crm/cityMerchant/orderDetails.html?id=${item.id }">详情</a>
	    				<a class="blue add-margin" data-type="1" href="#" onclick="update(${item.id},'${item.cityMerchantName }','${item.cityMerchantAddress }','${item.bankAccountName }','${item.bankName }'
                        	,'${item.bankNumber }','${item.bankAddress }','${item.linkMan }','${item.identityCard }','${item.linkTel }','${item.userId }','${item.provinceCode }'
                        	,'${item.provinceName }','${item.cityCode }','${item.cityName }','${item.areaProfit }','${item.fixateProfit }','${item.firstGoodsMoney}'
                        	)">修改</a>
	    				<a class="blue add-margin" data-type="1" href="###" onclick="startMerchant(${item.id})">启用</a>
	    			</td>
	    		</tr>
	    		</c:forEach>
	    	</table>
	    	<jsp:include page="../include/pages_new.jsp"></jsp:include>
      </div>
      </div> 
</div>
<form action="/crm/cityMerchant/saveStatus.html" onsubmit="return check()" method="post">
<input type="hidden" name="id" id="id" value="">
<input type="hidden" name="userId" id="userId" value="">
<div class="add_layer add_layer1" style="display:none">
	<div class="layer_content">
	  <h3 id="add_1">新增</h3>	
	  <div class="mian_content">
	  	<div class="add_left">
	  		<div class="add_box">
		  		<div class="add_c3">
		  		  <ul class="Message_list Message_list1">
		  		  	<li>
		  		  		<p class="Message_title">账 号</p>
		  		     	<p class="Message_content" id="add1_Account">12345678</p>
		  		    </li>
		  		  <li>
		  		  		<p class="Message_title">负责人 </p>
		  		     	<p class="Message_content" id="add1_person">无</p>
		  		  	</li>
		  		  	
		  		  </ul>	
		  		  <ul class="Message_list Message_list2">
		  		  	<li>
		  		  		<p class="Message_title">类型 </p>
		  		     	<p class="Message_content" id="add1_Type">无</p>
		  		  	</li>
		  		  	<li>
		  		  		<p class="Message_title">店铺名称 </p>
		  		     	<p class="Message_content" id="add1_shopName">无</p>
		  		  	</li>
		  		  </ul>
		  		  <div class="add_logo"></div>
		  		</div>
		  		<div class="add_c4"></div>
		  		<div class="add_c5"></div>
		  	</div>	
	  	</div>
	  	<div class="add_right" style="padding:20px;">
	  	  <div class="add_body">
	  	  	<div class="rowc_mian">
                <div class="rowskin">
                  <i class="selectpoint"></i>
                  <span class="selectcontent">类型</span>
                  <ul class="rowopation" style="display: none;">
                      <li>城市运营中心</li>
                  </ul>
                  <input type="hidden" class="c_m" id="c_m1" value="1">
                 </div>
               </div>
	  	  </div>
	  	  <div class="add_body">
	  	  	<span class="input_text"><input type="text" id="edit1_Account" name="linkTel" placeholder="账号"  oninput="edit(this.id,'add1_Account')"></span>
	  	  	<span class="input_text"><input type="text" id="edit1_person" name="linkMan" placeholder="负责人" oninput="edit(this.id,'add1_person')"></span>
	  	  </div>	
	  	  <div class="add_body">
	  	  	<span class="input_name"><input type="text" id="edit1_shopName" name="cityMerchantName" placeholder="公司名称" oninput="edit(this.id,'add1_shopName')"></span>
	  	  </div>
	  	</div>
	  </div>
	  <div class="btn_add">
		<a id="add_next1" class="add_ct add_ct1" data-step="1" href="###">下一步</a>
		<a class="add_undo1" href="###">取消</a>
	 </div>
	 <ul class="step_add">
	   <li class="on_stepColor"></li>
	   <li></li>
	   <li></li>
	   <li></li>
	 </ul>
	</div>
</div><!--第一步-->
<div class="add_layer add_layer2"style="display:none">
  	<div class="layer_content">
  	  <h3>新增</h3>	
  	  <div class="mian_content"> 
  	  	 <div class="add_left">
  	  	 	<div class="add_box">
  	  	 		<div class="add_c6">
  	  	 			<ul class="add_Region">
                       <li>
                       	<p>地址</p>
                       	<p id="add2_addressa"><span id="add2_address1">无</span><span id="add2_address2">无</span> <span id="add2_address3">无</span></p>
                       </li>
                       <li>
                       	<p>详细地址</p>
                       	<p id="add2_address">无</p>
                       </li>
                       <li>
                       	<p>负责区</p>
                       	<p id="add2_addressc"></p>
                       	<p id="add2_value"></p>
                       </li>
  	  	 			</ul>
  	  	 		</div>
                 <div class="add_c7"></div>
  		  		 <div class="add_c8"></div>
  	  	 	</div>
  	  	 </div>
  	  	 <div class="add_right">
  	  	 	 <div class="add_body">
  	  	 	 	<h3>地址</h3>
  	  	 	 	<div class="wrap_inputa">
              <div class="addressSelect clearfix">
    						<div class="sele prov">
    							<em class="txt">省份</em>
    							<input type="hidden" value="" id="provinceId" name="provinceCode">
    							<input type="hidden" value="" id="provinceName" name="provinceName">
    							<ul class="loc loc_province"  data-odd="1"></ul>
    						</div>
    						<div class="sele city">
    							<em class="txt">城市</em>
    							<input type="hidden" value="" id="cityId" name="cityCode">
    							 <input type="hidden" value="" id="cityName" name="cityName">
    						     <ul class="loc loc_city" data-odd="1"></ul>
    						</div>
    						<div class="sele town">
    							<em class="txt" id="towna">区/县</em>
    							<input type="hidden" value="" id="countyId" name="countyId">
    							<input type="hidden" value="" id="countyName" name="countyName">
    							<ul class="loc loc_town "  data-odd="1"></ul>                          
    						</div>
    					</div>
           	</div>
  	  	 	 </div>
  	  	 	 <div class="add_body">
  	  	 	 	<p class="xiangx_adress"><input type="text" name="cityMerchantAddress" id="edit2_address"  oninput="edit(this.id,'add2_address')"  placeholder="详细地址"></p>
  	  	 	 </div>
             <div class="add_body">
             	<h3>负责地区</h3>
             	<div class="addressSelect clearfix">
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
               <input type="hidden" id="town_value">
             </div> 
  	  	 </div>
  	  </div>
  	  <div class="btn_add">
  		<a class="add_ct add_ct2" data-step="2" href="###">下一步</a>
  		<a  class="add_undo1" href="###">取消</a>
  	 </div>
  	 <ul class="step_add">
  	   <li></li>
  	   <li class="on_stepColor"></li>
  	   <li></li>
  	   <li></li>
  	 </ul>
  	</div>
  </div><!--第二步-->
  <div class="add_layer add_layer3" style="display:none">
  	<div class="layer_content">
  	  <h3>新增</h3>	
  	  <div class="mian_content">
  	  	<div class="add_left">
  	  		<div class="add_box">
  		  		<div class="add_c9">
  		  			<ul class="add_black">
  		  			  <li>
  		  			  	<p  class="add-text" id="add3_bank">中国银行</p>
  		  			  </li>
  		  			  <li>
  		  			  	<p class="add-text add-font" id="add3_number" >5555 5555 5555 5555 55</p>
  		  			  </li>
  		  			  <li>
  		  			  	<p class="add-text"><label id="add3_Name">张三</label><span id="add3_id">411524199311018010</span></p>
  		  			  	<p class="add-text" id="add3_address">杭州高新街高新支行</p>
  		  			  </li>
  		  			</ul>
  		  		</div>
  		  		<div class="add_c10"></div>
  		  		<div class="add_c11"></div>
  		  	</div>	
  	  	</div>
  	  	<div class="add_right">
  	  			<ul class="addf_lr">
  	  				<li>
  	  					<p><input type="text" id="edit3_address" name="bankAddress" oninput="edit(this.id,'add3_address')" placeholder="开户行"></p>
  	  				    <p><input type="text"  id="edit3_Name" name="bankAccountName" oninput="edit(this.id,'add3_Name')" placeholder="开户名"></p>
  	  				</li>
  	  				<li>
  	  					<p><input type="text" data-txt="number" id="edit3_number" name="bankNumber" oninput="edit(this.id,'add3_number')" placeholder="银行账号"></p>
  	  				    <p><input type="text" id="edit3_bank" name="bankName" oninput="edit(this.id,'add3_bank')" placeholder="银行名"></p>
  	  				</li>
  	  				<li>
  	  					<p style="width:100%"><input id="edit3_id" name="identityCard" type="text" oninput="edit(this.id,'add3_id')" placeholder="身份证" style="width:92%"></p>
  	  				</li>
  	  			</ul>
  	  	</div>
  	  </div>
  	  <div class="btn_add">
  		<a class="add_ct add_ct3" data-step="3" href="###">下一步</a>
  		<a class="add_undo1" href="###">取消</a>
  	 </div>
  	 <ul class="step_add">
  	   <li></li>
  	   <li></li>
  	   <li class="on_stepColor"></li>
  	   <li ></li>
  	 </ul>
  	</div>
  </div><!--第三步-->
  <div class="add_layer" style="display:none">
  	<div class="layer_content">
  	  <h3>新增</h3>	
  	  <div class="mian_content">
  	  	<div class="add_left">
  	  		<div class="add_box">
  		  		<div class="add_c">
  		  			<ul class="add_value">
  		  				<li>
  		  					<p class="add_text4"><i class="add_icon add_icon4"></i><span id="add4_lra">1000000</span>%</p>
  		  					<p class="add_text3">固定利润</p>
  		  				</li>
  		  				<li>
  		  					<p class="add_text4"><i class="add_icon add_icon5"></i><span id="add4_lrb">1000000</span>%</p>
  		  					<p class="add_text3">配送利润</p>
  		  				</li>
  		  				<li>
  		  					<p class="add_text4"><i class="add_icon add_icon6"></i><span id="add4_lrc">1000000</span></p>
  		  					<p class="add_text3">首批提货款</p>
  		  				</li>
  
  		  			</ul>
  		  		</div>
  		  		<div class="add_c1"></div>
  		  		<div class="add_c2"></div>
  		  	</div>	
  	  	</div>
  	  	<div class="add_right">
  	  			<ul class="addf_lr">
  	  				<li>
  	  					<p><input type="text" id="edit4_lra" name="fixateProfit"  data-txt="number" oninput="edit(this.id,'add4_lra')"  placeholder="固定利润"></p>
  	  				    <p><input type="text" id="edit4_lrb" name="areaProfit" data-txt="number"  oninput="edit(this.id,'add4_lrb')" placeholder="配送利润"></p>
  	  				</li>
  	  				<li>
  	  					<p><input type="text" id="edit4_lrc" name="firstGoodsMoney" data-txt="number"  oninput="edit(this.id,'add4_lrc')" placeholder="首批提货款"></p>
  	  				</li>
  	  			</ul>
  	  	</div>
  	  </div>
  	 <ul class="step_add">
  	   <li></li>
  	   <li></li>
  	   <li></li>
  	   <li class="on_stepColor"></li>
  	 </ul>
  	  <div class="btn_add">
  		<input type="submit" class="add_ct add_ct" data-step="4" value="保存">
  		<a class="add_undo1" href="###">取消</a>
  	 </div>
  	</div>
  </div> <!--第四步-->
</form>
<script type="text/javascript">
	$(function(){
		showLocation();
	})
</script>
</body>
</html>
