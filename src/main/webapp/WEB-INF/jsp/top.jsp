<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function logout(){
		location.href = "/logout.html";
	}
</script>
<header class="pubtop">
    <img src="../images/userimg.png" class="userimg fl">
    <span class="username fl">${sessionScope.admin.userName}<i class="arrow arrowdown"></i> <i onclick="logout()">注销</i></span>
    <a class="jump" target="_blank" href="/mall/product/toadd.html">新版入口</a>
</header>
<style>
  .pubtop a{
        color: #fff;
	    height: 20px;
	    margin-top: 20px;
	    display: inline-block;
	    position: absolute;
	    right: 45px;
	    cursor: pointer;
  }
  .pubtop a:hover{
        color:#fff;
  }
  .pubtop a:active{
        color:#fff;
  }
  
</style>