<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="/">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../css/pubstyle.css" rel="stylesheet" type="text/css">
	<link href="../css/equipdetails.css" rel="stylesheet" type="text/css">
	<script src="../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
	<script src="../js/public.js" type="text/javascript" ></script>
	<title>智能硬件类型列表</title>
</head>
<body>
	<div class="bg">
	    <lu class="detailbox">
	    	<li>
	    		<span class="detail-txt">categoryId:</span>${ item.categoryId }
	    	</li>
	    	<li>
	    		<span class="detail-txt">categoryCode:</span>${ item.categoryCode }
	    	</li>
	    	<li>
	    		<span class="detail-txt">categoryName:</span>${ item.categoryName }
	    	</li>
	    	<li>
	    		<span class="detail-txt">categoryModelId:</span>${ item.categoryModelId }
	    	</li>
	    	<li>
	    		<span class="detail-txt">categoryModelCode:</span>${ item.categoryModelCode }
	    	</li>
	    	<li>
	    		<span class="detail-txt">categoryModelName:</span>${ item.categoryModelName }
	    	</li>
	    	<li>
	    		<span class="detail-txt">产品LOGO:</span><img alt="" src="${ item.logoImg }" height="60px" width="60px"> 
	    	</li>
	    	<li>
	    		<span class="detail-txt">重置提示图:</span><img alt="" src="${ item.resetImg }" height="60px" width="60px">
	    	</li>
	    	<li>
	    		<span class="detail-txt">状态就绪提示图:</span><img alt="" src="${ item.readyYesImg }" height="60px" width="60px">
	    	</li>
	    	<li>
	    		<span class="detail-txt">状态未就绪提示图:</span><img alt="" src="${ item.readyNoImg }" height="60px" width="60px">
	    	</li>
	    	<li>
	    		<span class="detail-txt">wifi名称:</span>${ item.wifiName }
	    	</li>
	    	<li>
	    		<span class="detail-txt">wifi密码:</span>${ item.wifiPassword }
	    	</li>
	    	<li>
	    		<span class="detail-txt">wifi-ip:</span>${ item.wifiHost }
	    	</li>
	    	<li>
	    		<span class="detail-txt">wifi端口:</span>${ item.wifiPort }
	    	</li>
	    	<li>
	    		<span class="detail-txt">bxid前4位:</span>${ item.bxid4 }
	    	</li>
	    	<li>
	    		<span class="detail-txt">操作界面:</span>${ item.ui }
	    	</li>
	    	<li>
	    		<span class="detail-txt">cVersionLast:</span>${ item.cVersionLast }
	    	</li>
	    </ul>
		<form action="/item/date/list.html?status=${status }" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<input type="submit" class="search" style="display:none;" value="搜索">
		</form>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
</body>
</html>