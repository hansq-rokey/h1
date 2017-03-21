<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="javascript:;"><span class="firstnav" onclick="openRight(0);">全部<i class="arrow arrow-right"></i></span></a>
<c:forEach items="${orgList}" var="item">
	<li class="firstnavli">
	    <a href="javascript:;"><span class="firstnav" onclick="openRight(${item.id });">${item.orgName}<i class="arrow arrow-right"></i></span></a>
<%-- 	    <ul>
	    	<c:if test="${item.childList != null }">
	    		<c:forEach items="${item.childList}" var="child">
		        	<li class=secondnavli><span class="secondnav" onclick="openRight(${child.id });">${child.orgName}</span></li>
		        </c:forEach>
	        </c:if>
	    </ul> --%>
	</li>
</c:forEach>
   