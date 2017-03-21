<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="../CaseSystem/basic/base.jsp" flush="true" />
    <script type=text/javascript src="<%=path%>/js/login.js"></script>
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/rolemanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <script type="text/javascript">
    	function showTree(id){
   			var src = '<%=path%>/bbs/role/perTree.html?roleId='+id;
            $('#contentFrame').attr('src',src);
    	}
    </script>
  </head>
  
  <body>
		<section>
        <ul class="partlist">
              		<c:forEach items="${roleList}" var="role" varStatus="status">
              		<li>
              			<c:if test="${status.index == 0 }"><span class="switch switched" onclick="showTree(${role.id})">${role.name}</span></c:if>
              			<c:if test="${status.index != 0 }"><span class="switch" onclick="showTree(${role.id})">${role.name}</span></c:if>
              		</li>
              		</c:forEach>
        </ul>
        </section>
        <!-- 权限树页面 -->
        <div data-options="region:'center'" style="width: 100%;height: 800px;">
             <iframe src="<%=path%>/bbs/role/perTree.html?roleId=1"  width="100%" height="99.5%"  id="contentFrame">
             </iframe>
        </div>
  </body>
</html>
