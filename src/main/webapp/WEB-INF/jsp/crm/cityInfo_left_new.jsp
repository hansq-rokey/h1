<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	<div class="add_nav">
    	  <ul class="add_wp">
    	   <c:forEach items="${dataList}" var="item">
    	 	<li><a href="/crm/info/list.html?pid=${item.id}&pageNo=1">${item.cityMerchantName}</a></li>
    	   </c:forEach>
    	  </ul>	  
    	</div>
