<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div style="background:#e1eafe; overflow-x:hidden;width:100%;height:100%;">
    <div class="mint">
    <s:iterator value="menuList" var="m" status="count">
        <div class="menuList" >
            <span class="menuTitle" urlMenu="<%=path %>/basic/getUserMenu.action?fparentid=<s:property value="#m.id"/>&flevel=2"><em class="embg1"><s:property value="#m.name"/></em></span>
            <ul class="menuUl menuUl1">
               
            </ul>
        </div>
     </s:iterator>
    </div>
</div>
<script type="text/javascript">
$(function(){
	$('.menuTitle').click(function(){
		$('li[name=menuLI]').remove();
		var obj = $(this).parent("div").children('ul');
		var url =$(this).attr("urlMenu");
		$('.embg2').attr("class", "embg1");
		$(this).children('em').attr("class", "embg2");
		$(obj).load(url,'{flevel:2}',function(){
			$('.menuUl li b').eq(0).attr("class", "b1bg");
			$('.menuUl li').eq(0).click();
		});
	});
	$('.menuTitle').eq(0).click();
});
</script>