<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/basedata.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <link href="../../../css/personmanage.css" rel="stylesheet" type="text/css">
    <script src="../../../js/personmanage.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>部门设置</title>
    <script type="text/javascript">
    	function toModelList(){
    		location.href = "/system/baseData/model/getModelList.html";
    	}
    	function addOrg(){
   	      $('.addrolepop').show();
    	}
    	function check(){
    		var orgName = $("#orgName").val();
    		if(orgName == null || orgName ==undefined || orgName == ''){
    			alertLayel("部门不可为空");
				return false;
			}
    		return true;
    	}
    	function updateOrgName(name,id){
    		if(name == null || name ==undefined || name == ''){
    			alertLayel("部门不可为空");
				return false;
			}
    		$.ajax({
  			   url: "/system/baseData/org/updateOrg.html?name="+name+"&id="+id,
  			   type: "post",
  			   dataType:"json",
  			   cache:false,
  			   async: false,
  			   success: function(obj){
  		  			if ( !checkCode( obj ) ) {
  						return;
  				    }else{
  				    	alertLayel("修改部门名称成功");
  				    }
  			   }
  			});
    	}
    </script>
</head>
<body>
<div style="display: none;">
<form action="/system/baseData/org/getOrgList.html" method="post">
 	<input type="hidden" name="pageNo" id="pageNo" value="">
    <input type="submit" id="pageSubmit" class="search" value="搜索">        	
</form>
</div>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch switched">部门设置</span>
                <div class="inforbox selectinforbox">
	                <c:if test="${msg != null}">
		        		<span style="color: red;">${msg }</span>
		        	</c:if>
                    <div class="row">
                        <input type="button" class="addpart" onclick="addOrg()" value="新增部门">
                        <table class="departlist">
                            <thead>
                            <tr>
                                <td>部门</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orgList}" var="org" varStatus="st">
	                            <tr>
	                                <td><input type="text" class="departname" value="${org.name }" onchange="updateOrgName(this.value,${org.id })"></td>
	                                <td><a href="/system/baseData/org/delete.html?id=${org.id }" class="delete">删除</a> </td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </div>
            </li>
            <li>
                <span class="switch" onclick="toModelList()">菜单管理</span>
            </li>
        </ul>
    </section>
<form action="/system/baseData/org/save.html" onsubmit="return check()"  method="post">
	<div class="pop addrolepop">
	    <div class="popbg"></div>
	    <div class="layel" style="height: 155px;z-index: 999">
		        <h3 class="addtitle">新增部门<i class="closeicon"></i></h3>
		        <div class="row">
		            <span class="personattr">部门名称:</span>
		            <input type="text" value="" id="orgName" name="name" class="rolename">
		        </div>
		  		<input type="submit" class="addrole" value="保存">
	    </div>
	</div>
</form>
</body>
</html>