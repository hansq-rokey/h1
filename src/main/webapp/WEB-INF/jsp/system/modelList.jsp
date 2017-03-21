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
    	function toOrgList(){
    		location.href = "/system/baseData/org/getOrgList.html";
    	}
    	function addModel(type){
    		var goalId = $('#goalId').val();
    		if(goalId == null || goalId ==undefined || goalId == ''){
    			alertLayel("请选择目标！");
				return false;
			}
    		var goalName = $('#goalName').val();
    		if(type == 1){
    			//新增同级
    			var goalPid = $('#goalPid').val();
    			$('#modelPid').val(goalPid);
    			$('#modelTitle').text("将添加菜单与【"+goalName+"】同级");
    		}
    		if(type == 2){
    			//新增下级
    			var goalId = $('#goalId').val();
    			$('#modelPid').val(goalId);
    			$('#modelTitle').text("将添加菜单到【"+goalName+"】下级");
    		}
     	    $('.addrolepop').show();
      	}
    	function setGoal(id,name,pid){
    		$('#goalId').val(id);
    		$('#goalName').val(name);
    		$('#goalPid').val(pid);
    	}
    	function check(){
    		var modelName = $("#modelName").val();
    		if(modelName == null || modelName ==undefined || modelName == ''){
    			alertLayel("菜单名称不可为空");
				return false;
			}
    		return true;
    	}
    	function updateName(name,id){
    		if(name == null || name ==undefined || name == ''){
    			alertLayel("菜单名称不可为空");
				return;
			}
    		save("name",name,id);
    	}
		function updateUrl(url,id){
			save("url",url,id);
    	}
		function updateOrder(order,id){
			var r = /^\+?[1-9][0-9]*$/;//正整数
		    if(!r.test(order)){
		    	alertLayel("请输入正整数");
		    	return;
		    }
		    save("order",order,id);
		}
		function save(pro,val,id){
			$.ajax({
  			   url: "/system/baseData/model/updateModel.html?"+pro+"="+val+"&id="+id,
  			   type: "post",
  			   dataType:"json",
  			   cache:false,
  			   async: false,
  			   success: function(obj){
  		  			if ( !checkCode( obj ) ) {
  						return;
  				    }else{
  				    	alertLayel("修改成功");
  				    }
  			   }
  			});
		}
    </script>
</head>
<body>
<input type="hidden" id="goalId" value="">
<input type="hidden" id="goalName" value="">
<input type="hidden" id="goalPid" value="">
    <section>
        <ul class="partlist">
            <li>
                <span class="switch" onclick="toOrgList()">部门设置</span>
            </li>
            <li>
                <span class="switch switched">菜单管理</span>
                <div class="inforbox selectinforbox">
                    <div class="row">
                        <input type="button" class="addsibling" onclick="addModel(1)" value="新增同级菜单">
                        <input type="button" class="addlower" onclick="addModel(2)" value="新增下级菜单">
                        <table class="menubarlist">
                            <thead>
                            <tr>
                                <td>名称</td>
                                <td>URL</td>
                                <td>排序</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${modelList}" var="model" varStatus="st">
	                            <tr class="menutr">
	                                <td><i class="showicon"></i> <input type="text" class="departname" value="${model.name }" onfocus="setGoal(${model.id },'${model.name }',${model.parentModel.id })" onchange="updateName(this.value,${model.id })"> </td>
	                                <td><input type="text" value="${model.url }" class="menubarurl" onchange="updateUrl(this.value,${model.id })"> </td>
	                                <td><input type="text"  class="sortorder" value="${model.order }" onchange="updateOrder(this.value,${model.id })"> </td>
	                            </tr>
	                            <c:if test="${model.childList != null}">
	                            <tr>
	                                <td colspan="3">
	                                    <table class="secondmenulist">
	                                        <tbody>
	                                        <c:forEach items="${model.childList}" var="modelChild" varStatus="st1">
		                                        <tr class="menutr">
		                                            <td><i class="hideicon"></i> <input type="text" class="departname" value="${modelChild.name }"  onfocus="setGoal(${modelChild.id },'${modelChild.name }',${modelChild.parentModel.id })" onchange="updateName(this.value,${modelChild.id })"> </td>
		                                            <td><input type="text" value="${modelChild.url }" class="menubarurl" onchange="updateUrl(this.value,${modelChild.id })"> </td>
		                                            <td><input type="text"  class="sortorder" value="${modelChild.order }" onchange="updateOrder(this.value,${modelChild.id })"> </td>
		                                        </tr>
	                                        </c:forEach>
	                                        </tbody>
	                                    </table>
	                                </td>
	                            </tr>
	                            </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
<form action="/system/baseData/model/save.html" onsubmit="return check()"  method="post">
	<div class="pop addrolepop">
	    <div class="popbg"></div>
	    <div class="layel" style="z-index: 999">
		        <h3 class="addtitle">新增菜单<i class="closeicon"></i></h3>
		        <input type="hidden" value="" id="modelPid" name="parentModel.id">
		        <h3 class="addtitle"><span id="modelTitle"></span></h3>
		        <div class="row">
		            <span class="personattr">名称:</span>
		            <input type="text" value="" id="modelName" name="name" class="rolename">
		        </div>
		        <div class="row">
		        	<span class="personattr">url:</span>
		            <input type="text" value="" id="modelUrl" name="url" class="rolename">
		        </div>
		        <div class="row">
		        	<span class="personattr">排序:</span>
		            <input type="text" value="" id="modelOrder" name="order" class="rolename">
		        </div>
		        <div class="row">
		        	<span class="personattr">系统:</span>
		            <input type="radio" name="sysTag" checked="checked" value="cms">cms<input type="radio" name="sysTag" value="erp">erp
		        </div>
		  		<input type="submit" class="addrole" value="保存">
	    </div>
	</div>
</form>
</body>
</html>