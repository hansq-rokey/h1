<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" style="overflow:hidden;">
<head>
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
	<script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
	<script src="../../../js/public.js" type="text/javascript" ></script>
	<script src="../../../js/index.js" type="text/javascript" ></script>
    <script type="text/javascript">
    	function openRight(id){
    		var url = '/ding/department/users.html?departmentId='+id;
    		$("#rightFrame").attr('src',url);
    	}
    	$(document).ready(function(){
    		var width=$('.secondrightbox').width();
    		//$('.secondrightbox').css('width',width+220);
    	})
    </script>
</head>
<body>
<div class="row crmLeftNavUl">
        <jsp:include page="info_left.jsp"></jsp:include>
</div>
<div class="rightbox secondrightbox" style="margin-top: -5px;margin-left:220px;">
    <iframe id="rightFrame" name="rightFrame" width="100%" height="100%" src="">
    </iframe>
</div>
</body>
</html>