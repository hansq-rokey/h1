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
    <script src="../../../plug_new/layer/layer.js" type="text/javascript" ></script>
    <title>下级代理列表</title>
    <script type="text/javascript">
    function showPage(number){
    	var parentId = $("#id").val();
        window.location.href="/crm/cityMerchant/downMerchant.html?parentId="+parentId+"&pageNo="+number;
    }
    function showList(){
        window.location.href="/crm/cityMerchant/cityMerchant.html";
    }
    </script>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<div class="addBtn"><i></i></div>
<div class="right-bottom">
    <div class="right-top" style="left: 280px;">
	    <div class="tab-list">
           <ul class="Operation_tag">
             <a href="/crm/cityMerchant/cityMerchant.html">城市运营中心</a>
             <b class="Operation_icon"></b>
             <c:if test="${ppCityId!=null }">
             	<a href="/crm/cityMerchant/downMerchant.html?parentId=${ppCityId }">${ppCity.cityMerchantName }</a>
             	<b class="Operation_icon"></b>
             </c:if>
             <a href="/crm/cityMerchant/downMerchant.html?parentId=${parentId }">${parentCity.cityMerchantName }</a>
           </ul>
        </div>
    	<input type="hidden" name="parentId" id="id" value="${parentId }">
        <input type="hidden" name="pageNo" id="pageNo" value="">
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
                <c:forEach items="${citys}" var="item">
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
                        <a class="blue add-margin" id="showDetail" data-type="1" href="/crm/cityMerchant/orderDetails.html?id=${item.id }&parentId=${parentId}">详情</a>
                        <a class="blue add-margin" id="showDetail" data-type="1" href="/crm/cityMerchant/downMerchant.html?parentId=${item.id }">下级代理</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <jsp:include page="../include/pages_new.jsp"></jsp:include>
      </div>
    </div> 
<script type="text/javascript">
    $(function(){
        showLocation();
    })
</script>
</body>
</html>
