<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <link href="../../../css/commanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <script src="../../../plug/adddatetime.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>价格设置</title>
    <script type="text/javascript">
    	function toSetPrice(formatId,priceId,price){
    		$("#id").val(priceId);
    		$("#formatId").val(formatId);
    		$("#price").val(price);
    		$('.addpop').show();
    	}
    	function check(){
    		var price = $("#price").val();
    		if( price == null ||  price ==undefined || price  == ''){
    			alertLayel("价格不可为空");
				return false;
			}
    		var r = /^\d+(\.\d+)?$/;//非负浮点数（正浮点数 + 0）
    		if(!r.test(price)){  
    			alertLayel("价格只支持非负浮点数!");
    	        return false;
    	    }
    		return true;
    	}
    	$(document).ready(function() {
    		var error = $("#error").val();
    		if(error == 1){
    			alert("设置价格失败，产品未找到");
    		}
    		if(error == 2){
    			alert("设置价格失败，设置价格过大超过了官网设置的价格");
    		}
    		if(error == 3){
    			alert("设置价格失败，省/市代未设置价格，需要先设置省/市代价格");
    		}
    		if(error == 4){
    			alert("设置价格失败，设置价格过小，要设置超过省/市代价格");
    		}
    	});
    </script>
</head>
<body>
    <section>
        <ul class="partlist" style="border-bottom: 0px;margin-top: -10px;">
            <li>
                <div class="inforbox selectinforbox">
                	<div class="content">
                    	<form action="/crm/info/setModelFormatMonyelist.html" method="post">
                    		<input type="hidden" name="pageNo" id="pageNo" value="">
                    		<input type="hidden" name="error" id="error" value="${error }">
	          			    <input type="text" class="packing-code" name="keywords" value="${keywords }" placeholder="产品名称">
	                        <input type="text" class="strat-time datetimepicker startdata" name="startTime" value="${startTime }" placeholder="开始时间" onclick="SelectDate(this,'yyyy-MM-dd')">
	                        <span>至</span>
	           			    <input type="text" class="end time datetimepicker startdata" name="endTime" value="${endTime }" placeholder="结束时间" onclick="SelectDate(this,'yyyy-MM-dd')">
	                       	<input type="hidden" value="${ssssid }" name="ssssid">
	                        <input type="submit" class="search" style="width:100px;height:30px;background:#ff6200;color:#fff;" value="搜索">
                        </form>
        			</div>
                    <div class="row">
                        <table class="gradelist">
                            <thead>
	                            <tr>
				        			<td>产品名称</td>
				        			<td>产品编号</td>
				        			<td>产品规格</td>
				        			<td>价格</td>
				        			<td>操作</td>
			        			</tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list }" var="model">
					   			<c:forEach  items="${model.formats }" var="item" varStatus="itemStatus">
					   				<c:choose>
					   					<c:when test="${fn:length(model.formats)==1 }">
							        		<tr>
							        			<td >${model.name }</td>
							        			<td>${item.categoryModelFormatNumber }</td>
							        			<td>${item.name}</td>
							        			<td>${item.crmMoney}</td>
							        			<td>
							        				<a href="javascript:void(0);" onclick="toSetPrice(${item.id},${item.priceId },${item.crmMoney })" class="link editlink">修改价格</a>
							        			</td>
							        		</tr>
				        				</c:when>
		   								<c:otherwise>
		   									<c:if test="${itemStatus.first }">
		   										<tr>
								        			<td rowspan="${fn:length(model.formats) }">${model.name }</td>
								        			<td>${item.categoryModelFormatNumber }</td>
							        				<td>${item.name}</td>
							        				<td>${item.crmMoney}</td>
								        			<td>
								        				<a href="javascript:void(0);" onclick="toSetPrice(${item.id},${item.priceId },${item.crmMoney })" class="link editlink">修改价格</a>
								        			</td>
								        		</tr>
		   									</c:if>
		   									<c:if test="${itemStatus.index>0 }">
		   										<tr>
								        			<td>${item.categoryModelFormatNumber }</td>
							        				<td>${item.name}</td>
							        				<td>${item.crmMoney}</td>
								        			<td>
								        				<a href="javascript:void(0);" onclick="toSetPrice(${item.id},${item.priceId },${item.crmMoney })" class="link editlink">修改价格</a>
								        			</td>
								        		</tr>
		   									</c:if>
				        				</c:otherwise>
		   							</c:choose>
		   						</c:forEach>
		   					</c:forEach>
                            </tbody>
                        </table>
                        <jsp:include page="../include/pages_old.jsp"></jsp:include>
                    </div>
                </div>
            </li>
        </ul>
    </section>
<form action="/crm/info/savePrice.html" onsubmit="return check()"  method="post">
<input type="hidden" name="id" id="id" value="">
<input type="hidden" name="ssssId" id="ssssId" value="${ssssid }">
<input type="hidden" name="formatId" id="formatId" value="">
<div class="pop addpop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle"><span id="spanTitle">设置价格</span><i class="closeicon"></i> </h3>
        <div class="row">
            <span class="addtypename">价格：</span>
            <input type="text" class="urlvalue" name="price" id="price" value="">
        </div>
        <div class="row tc">
            <input type="submit" value="保存" class="delete">
        </div>
    </div>
</div>
</form>
</body>
</html>
