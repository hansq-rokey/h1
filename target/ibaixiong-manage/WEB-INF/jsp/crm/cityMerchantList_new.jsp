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
    <script src="/js_new/cityMerchant.js" type="text/javascript" ></script>
    <script src="../../../plug_new/layer/layer.js" type="text/javascript" ></script>
    <title>城市运商管理</title>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<a class="addBtn" href="/crm/cityMerchant/addMerchant.html"><i></i></a>
<div class="right-bottom">
    <div class="right-top" style="left: 280px;">
        <div class="input-list">
            <span class="selectinput plate">
               <span class="selectvalue Consignee" id="typeName">--请选择--</span>
               <ul class="selet-Consignee" id="selet-Consignee"></ul>
           	</span>
           	<input type="text" id="keywords" name="keywords" value="${keywords }" placeholder="账号或公司名称">
            <span class="search-icon ripple-event" onclick="showPage(1)"></span>
        </div>
        <input type="hidden" name="pageNo" id="pageNo" value="">
        <input type="hidden" name="dictCodeValue" value="${dictCodeValue }" id="dictCodeValue">
    </div>
    <div class="a_content">
            <table class="add_content add_cityMerchant">
                <tr>
                    <th>账号</th>
                    <th>公司名称</th>
                    <th>类型</th>
                    <th>提货系数/区域系数</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${dataList}" var="item">
                <tr>
                    <td>${item.linkTel}</td>
                    <td>${item.cityMerchantName}</td>
                    <td>
                    	<c:forEach items="${typeNames }" var="name">
                    		${name.dictCodeValue==item.level?name.dictCodeName:"" }
                    	</c:forEach>
                    </td>
                    <td>
                        <p class=" bold red">
                            <c:choose>
                                <c:when test="${item.fixateProfit==null}">1.0</c:when>
                                <c:otherwise>${item.fixateProfit }</c:otherwise>
                            </c:choose>
                            
                            <c:choose>
                                <c:when test="${item.areaProfit==null}">/1.0</c:when>
                                <c:otherwise>/${item.areaProfit}</c:otherwise>
                            </c:choose>
                        <p>
                    </td>
                    <td>
                        <a class="blue add-margin" id="showDetail" data-type="1" href="/crm/cityMerchant/orderDetails.html?id=${item.id }">详情</a>
                        <a class="blue add-margin" id="showDetail" data-type="1" href="/crm/cityMerchant/edit.html?cityId=${item.id }">编辑</a>
                        <a class="blue add-margin proa_set" data-id="${item.id}" href="###">设置</a>
                        <a class="blue add-margin org_set" data-id="${item.id}" href="###">业务员</a>
                        <a class="blue add-margin" id="showDetail" data-type="1" href="/crm/cityMerchant/downMerchant.html?parentId=${item.id }">下级代理</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <jsp:include page="../include/pages_new.jsp"></jsp:include>
      </div>
    </div> 
<!--设置-->
<div class="prt_set" style="display:none">
    <div class="layer_content prt_content">
      <h3>设置</h3>   
      <div class="mian_content content_list">
            <div class="content_on" id='content_on'>
            	<a class="activeon" href="###">代理产品</a>
            	<!-- <input type="radio" name="content_on" class="selectradio" id="content_onft"><label for="content_onft">代理产品</label>
            	<input type="radio" name="content_on" class="selectradio" id="content_ondb"><label for="content_ondb">区域利润</label> -->
            	<a href="###">区域利润</a>
            </div>
        <div class="procut_list" >
            <div class="pro_list">
       			<ul class="pro_tag">
	                  <li> <p><input type="radio" id="ch1" checked="checked" name="pro_tag1" class="regular-checkbox" /><label for="ch1"></label><span>部分产品</span></p></li>
	                  <li> <p><input type="radio" id="ch2"  name="pro_tag1"  class="regular-checkbox" /><label for="ch2"></label><span>全部产品</span></p></li>
          		</ul>
	            <ul class="pro_content" id="pro_top">
					<c:forEach items="${productListB }" var="product" varStatus="v">
		           		<li> 
		           		  <p><input type="checkbox" id="checkbox${v.count}" name="product1" value="${product.id }" class="regular-checkbox" /><label for="checkbox${v.count }"></label></p>
		           		  <span>${product.title }</span>
		           		</li>
		           	</c:forEach>
	           	</ul>
            </div>
            <div class="pro_list" style="display:none">
            	 <ul class="pro_tag">
	                  <li> <p><input type="radio" id="che1" name="pro_tag2" checked="checked"  class="regular-checkbox" /><label for="che1"></label><span>部分产品</span></p></li>
	                  <li> <p><input type="radio" id="che2" name="pro_tag2"  class="regular-checkbox" /><label for="che2"></label><span>全部产品</span></p></li>
           		 </ul>
          		  <ul class="pro_content" id="pro_bottom">
					<c:forEach items="${productListC }" var="product" varStatus="v">
	            		<li> <p><input type="checkbox" id="c${v.count}" name="product2" value="${product.id }" class="regular-checkbox" /><label for="c${v.count}"></label></p><span>${product.title }</span></li>
	            	</c:forEach>
       			 </ul>
            </div>
        </div>
      </div>
      <div class="btn_add">
      	<input type="hidden" id="merchantId">
        <input type="submit" class="pro_save" onclick="addProduct()" value="保存">
        <a class="add_undo1 pro_close" href="###">取消</a>
     </div>
  </div>
</div>
<!-- 业务员 -->
<div class="salesman" style="display:none">
    <div class="layel_salesman prt_content">
      <h3 class="layel_salesman_tag">业务员设置</h3>   
      <div class="salesman_box">
      	<div class="salsman_list">
      		<c:forEach items="${orgList }" var="org">
      			<a  href="###" data-id="${org.id}" onclick="showUser(${org.id})">${org.orgName }</a>
      		</c:forEach>
      	</div>
      	<div class="salsman_name"></div>
      </div>
      <div class="salsman_name_data"> </div>
       <div class="salsman_name_hidden"></div>	
       <div class="btn_add">
      	<input type="hidden" id="merchantid">
        <input type="submit" class="pro_save" onclick="addUserMerchant()" value="保存">
        <a class="add_undo1 pro_close" href="###">取消</a>
     </div>
  </div>
</div>
</body>
</html>
