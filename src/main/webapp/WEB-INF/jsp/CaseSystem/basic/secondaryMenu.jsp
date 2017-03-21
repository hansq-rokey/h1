<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function () {
    $('.menuUl li').bind('click', function () {
        $('.menuUl li b').attr("class", "");
        $(this).children("b").attr("class", "b1bg");
    });
  
});
</script>
  <s:iterator  value="menuList" var="m">  
  	<li name="menuLI" onclick="linkToMainFrame('<%=path %><s:property value="#m.url"/>');" title="<s:property value="#m.name"/>">
  	<b><s:property value="#m.name"/></b>
  	</li>
  </s:iterator>
