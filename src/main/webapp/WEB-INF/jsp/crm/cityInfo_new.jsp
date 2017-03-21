<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" style="overflow:hidden;">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="/plug_new/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/plug_new/bootstrap/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
	<link href="/css_new/common.css" rel="stylesheet" type="text/css">
	<link href="/css_new/addCity.css" rel="stylesheet" type="text/css"/>
	<script src="/plug_new/jQuery/jquery-1.9.1.min.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/bootstrap.min.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/moment.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/daterangepicker.js" type="text/javascript" ></script>
	<script src="/js_new/common.js" type="text/javascript" ></script>
	<script src="/js_new/addCity.js" type="text/javascript" ></script>
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
    </div>
     <jsp:include page="cityInfo_left_new.jsp"/>
</div>
</body>
</html>