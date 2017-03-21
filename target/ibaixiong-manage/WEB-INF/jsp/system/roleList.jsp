<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../css/personmanage.css" rel="stylesheet" type="text/css">
    <script src="../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../js/public.js" type="text/javascript" ></script>
    <script src="../../js/personmanage.js" type="text/javascript" ></script>
    <script src="../../js/base.js" type="text/javascript" ></script>
    <title>员工管理</title>
    <script type="text/javascript">
    	function toAdminList(){
    		location.href = "/system/admin/getAdminList.html";
    	}
    	function showTree(id){
   			var src = '<%=path%>/system/role/perModelTree.html?roleId='+id;
            $('#contentFrame').attr('src',src);
    	}
    	function toAddRole(){
    		$('.layel').show();
    		showTree(0);
    		$('#roleTitle').text("新增角色");
    		$('#roleId').val("");
			$('#roleName').val("");
    	}
		function toUpdate(id,name){
			$('.addrolepop').show();
			showTree(id);
			$('#roleId').val(id);
			$('#roleName').val(name);
			$('#roleTitle').text("修改角色");
    	}
    	function saveRole(){
    		//调用子页面选中的
    		var models = window.frames["contentFrame"].getSelectModel();
    		var roleId = $('#roleId').val();
			var roelName = $('#roleName').val();
    		//var url = "/system/role/save.html?id="+roleId+"&roleName="+roelName+"&"+models;
    		var url = "/system/role/save.html";
    		$.ajax({
   			   type: "POST",
   			   url: url,
   			   data:{"id":roleId,"roleName":roelName,"privilegeids":models},
   			   cache:false,
   			   success: function(obj){
			    	//保存成功点击查询按钮
			    	$('#searchbtn').click();
   			   }
   			});
    	}
    </script>
</head>
<body>
<section>
    <ul class="partlist">
    <li>
       <span class="switch" onclick="toAdminList()">员工列表</span>
    </li>
    <li>
        <span class="switch switched">角色列表</span>
        <div class="inforbox selectinforbox">
	        <c:if test="${msg != null}">
	        	<span style="color: red;">${msg }</span>
	        </c:if>
            <div class="row">
            	<form action="/system/role/getRoleList.html" method="post">
	                <input type="text" class="rolename" name="queryName" value="${queryName }" placeholder="角色名称">
	                <input type="hidden" name="pageNo" id="pageNo" value="">
	                <input type="submit" class="searchbtn search" id="searchbtn" value="搜索">
	                <input type="button" class="addrole" onclick="toAddRole()" value="新增角色">
                </form>
                <table class="menubarlist">
                    <thead>
                    <tr>
                        <td>角色名称</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${roleList}" var="role" varStatus="st">
	                    <tr class="menutr">
	                        <td>${role.name }</td>
	                        <td><a href="#" class="checkrole" onclick="toUpdate(${role.id},'${role.name }')">查看权限</a> <a href="/system/role/delete.html?id=${role.id }" class="delrole">删除权限</a> </td>
	                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <jsp:include page="../include/pages_old.jsp"></jsp:include>
        </div>
        <!-- 新增角色-->
        <div class="pop addrolepop">
            <div class="popbg"></div>
            <div class="layel" style="z-index: 999">
                <h3 class="addtitle"><span id="roleTitle">新增角色</span><i class="closeicon"></i></h3>
                <div class="row">
                    <span class="personattr">角色名称:</span>
                    <input type="text" value="" id="roleName" class="rolename">
                    <input type="hidden" id="roleId" name="roleId">
                </div>
                <div class="row">
                    <p>角色权限</p>
                </div>
                <!-- 权限树页面 -->
		        <div data-options="region:'center'" style="width: 100%;height: 300px;">
		             <iframe src=""  width="100%" height="99.5%" name="contentFrame" id="contentFrame">
		             </iframe>
		        </div>
		        <input type="button" class="addrole" onclick="saveRole()" value="保存">
            </div>
        </div>

        <!-- 新增角色结束-->
        </li>
    </ul>
</section>
</body>
</html>