<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:include page="../../CaseSystem/basic/base.jsp" flush="true" />
<script type="text/javascript">
function linkToMainFrame(url){
	$("#rightFrame").attr('src',url);
}
</script>
    <div id="divleft" class="easyui-layout" data-options="fit:true">   
        <div data-options="region:'west',title:'菜单',href:'<%=path %>/basic/getUserMenu.action?fparentid=${fparentid }'" style="width: 180px;height:100%">
        </div>
        <div data-options="region:'center',border:false" style="width: 100%;" id="div_right">
        	<iframe id="rightFrame" name="rightFrame" width="100%" height="100%">
        	</iframe>
        </div>
    </div>
</html>