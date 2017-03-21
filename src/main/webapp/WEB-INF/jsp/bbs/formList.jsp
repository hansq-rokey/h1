<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/personinfor.css" rel="stylesheet" type="text/css">
    <link href="../../../css/modulemanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/modulemanage.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <script src="../../../js/personmanage.js" type="text/javascript" ></script>
    <title>版块管理</title>
    <style  type="text/css">
    .rolename{
    	width: 200px;
   	 	height:30px;
    	border:1px solid #dcdcdc;
	}
	.infortype{
		width:100px;
	}
	.addrole{
		  width: 100px;
  		  height: 30px;
  	      background: #ff6200;
          color: #fff;
          margin-bottom: 20px;
          margin-top: 10px;
          margin-left:150px;
	}
    </style>
    <script type="text/javascript">
    	function addForm(type){
    		clean();
    		var goalId = $('#goalId').val();
    		if(goalId == null || goalId ==undefined || goalId == ''){
    			alertLayel("请选择目标！");
				return false;
			}
    		var goalName = $('#goalName').val();
    		if(type == 1){
    			//新增同级
    			var goalPid = $('#goalPid').val();
    			$('#formPid').val(goalPid);
    			$('#formTitle').text("将添加版块与【"+goalName+"】同级");
    			//显示相关不显示的div
    			$('#descriptionDiv').show();
    			$('#operateDiv').show();
    			$('#perDiv').show();
    		}
    		if(type == 2){
    			//新增下级
    			var goalId = $('#goalId').val();
    			$('#formPid').val(goalId);
    			$('#formTitle').text("将添加版块到【"+goalName+"】下级");
    			//隐藏相关不显示的div
    			$('#descriptionDiv').hide();
    			$('#operateDiv').hide();
    			$('#perDiv').hide();
    		}
     	    $('.addrolepop').show();
      	}
    	function setGoal(id,name,pid){
    		$('#goalId').val(id);
    		$('#goalName').val(name);
    		$('#goalPid').val(pid);
    	}
    	function updateName(name,id){
    		if(name == null || name ==undefined || name == ''){
    			alertLayel("版块名称不可为空");
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
		    save("sort",order,id);
		}
		function save(pro,val,id){
			$.ajax({
  			   url: "/bbs/form/updateForm.html?"+pro+"="+val+"&id="+id,
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
		function setThumUrl(v,id){
			$("#subImg"+id).click();
		}
		function checkThumUrl(){
	    	return true ; 
	    }
		function check(){
    		var modelName = $("#formName").val();
    		if(modelName == null || modelName ==undefined || modelName == ''){
    			alertLayel("版块名称不可为空");
				return false;
			}
    		var formPid = $("#formPid").val();
    		if(formPid == 0){
    			var permissionsTag = $("#permissionsTag").val();
        		if(permissionsTag == null || permissionsTag ==undefined || permissionsTag == ''){
        			alertLayel("此为一级权限标签不可为空");
    				return false;
    			}
    		}
    		return true;
    	}
		function clean(){
			$("#formPid").val("");
			$("#formName").val("");
			$("#file").val("");
			$("#description").val("");
			$("#formlUrl").val("");
			$("#permissionsTag").val("");
			$("#formOrder").val("");
			$("#formId").val("");
			$('input[name=operates]:checkbox').removeAttr("checked");
		}
		function updatePer(id){
			$("#formId").val(id);
			$('.addrolepop1').show();
			//获取保存过的权限记录
			$('input[name=operates]:checkbox').removeAttr("checked");
			$.ajax({
  			   url: "/bbs/form/getAjaxFormPer.html?formId="+id,
  			   type: "post",
  			   dataType:"json",
  			   cache:false,
  			   async: false,
  			   success: function(obj){
  		  			if ( !checkCode( obj ) ) {
  						return;
  				    }else{
  				    	var perList = obj.result.perList;
  				    	for(var i=0;i<perList.length;i++){
  				    		$("#operate"+perList[i].operate.id).attr("checked","checked");
  				    	}
  				    }
  			   }
  			});
		}
		function checkPer(){
			var b = false;
			$('input:checkbox[name=operates]:checked').each(function(i){
		       b = true;
		    });
			if(!b){
				alertLayel("必须选中一个");
				$('.addrolepop').hide();
				return false;
			}
			return true;
		}
    </script>
</head>
<body>
<input type="hidden" id="goalId" value="">
<input type="hidden" id="goalName" value="">
<input type="hidden" id="goalPid" value="">
    <section>
            <div class="inforbox selectinforbox" style="margin-left: 20px">
            	<c:if test="${msg != null}">
		        	<span style="color: red;">${msg }</span>
		        </c:if>
                <div class="row">
                    <input type="button" class="addmodule" onclick="addForm(1)" value="新增同级模块">
                    <input type="button" class="addsonmodule" onclick="addForm(2)" value="新增下级模块">
                    <table class="modulelist">
                        <thead>
                        <tr>
                            <td>版块</td>
                            <td>概要描述</td>
                            <td width="100">缩略图</td>
                            <td>URL</td>
                            <td>排序</td>
                            <td width="100">操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${formList}" var="form" varStatus="st">
	                            <tr class="moduletr">
	                                <td><i class="showicon"></i> <input type="text" class="modulename" value="${form.name }" onfocus="setGoal(${form.id },'${form.name }',${form.parentForm.id })" onchange="updateName(this.value,${form.id })"> </td>
	                                <td>${form.description }</td>
	                                <td>
	                               	 <form action="/bbs/form/saveThumUrl.html" onsubmit="return checkThumUrl()" method="post" enctype="multipart/form-data">
	                                	<div class="userimgbox" style="display:block"> <img src="${form.thumUrl }" class="userimg"><p class="changeuserimg">上传图片</p><input type="file" id="file" name="file" class="uploadicon hidebtn"  onchange="setThumUrl(this.value,${form.id })"></div>	                                	</div>
	                                	<div style="display: none;">
	                                	<input type="text" name="id" value="${form.id }">
	                                	<input type="submit" id="subImg${form.id }">
	                                	</div>
	                                 </form>
	                                </td>
	                                <td><input type="text" value="${form.url }" class="moduleurl" onchange="updateUrl(this.value,${form.id })"> </td>
	                                <td><input type="text" value="${form.sort }" class="modulesort" onchange="updateOrder(this.value,${form.id })"> </td>
	                                <td ><span class="change"><a href="#" class="link deletelink" onclick="updatePer(${form.id})">修改权限</a></span></td>
	                            </tr>
	                            <c:if test="${form.childList != null}">
	                            <tr class="moduletr secondtr">
	                                <td colspan="6" class="secondtd">
	                                    <table class="secondmodulelist">
	                                        <tbody>
	                                        <c:forEach items="${form.childList}" var="modelChild" varStatus="st1">
		                                        <tr class="moduletr secmoduletr">
		                                            <td><i class="hideicon secondicon"></i> <input type="text" class="modulename fn" value="${modelChild.name }"  onfocus="setGoal(${modelChild.id },'${modelChild.name }',${modelChild.parentForm.id })" onchange="updateName(this.value,${modelChild.id })"> </td>
		                                            <td>${modelChild.description }</td>
	                                				<td>
                                					<form action="/bbs/form/saveThumUrl.html" onsubmit="return checkThumUrl()" method="post" enctype="multipart/form-data">
					                                	<div class="userimgbox" style="display:block"> <img src="${modelChild.thumUrl }" class="userimg"><p class="changeuserimg">上传图片</p><input type="file" id="file" name="file" class="uploadicon hidebtn"  onchange="setThumUrl(this.value,${modelChild.id })"></div>
					                                	<div style="display: none;">
					                                	<input type="text" name="id" value="${modelChild.id }">
					                                	<input type="submit" id="subImg${modelChild.id }">
					                                	</div>
					                                 </form>
	                                				</td>
		                                            <td><input type="text" value="${modelChild.url }" class="moduleurl" onchange="updateUrl(this.value,${modelChild.id })"> </td>
		                                            <td><input type="text"  class="modulesort" value="${modelChild.sort }" onchange="updateOrder(this.value,${modelChild.id })"> </td>
		                                        	<td ><span class="change"></span></td>
		                                        </tr>
		                                        <c:if test="${modelChild.childList != null}">
					                              <tr class="moduletr thirdtr">
						                                <td colspan="6" class="thirdtd">
						                                    <table class="thirdmodulelist">
					                                        <tbody>
					                                        <c:forEach items="${modelChild.childList}" var="modelChild1" varStatus="st1">
						                                        <tr class="moduletr thirdmoduletr">
						                                            <td><input type="text" class="modulename fn" value="${modelChild1.name }"  onfocus="setGoal(${modelChild1.id },'${modelChild1.name }',${modelChild1.parentForm.id })" onchange="updateName(this.value,${modelChild1.id })"> </td>
						                                            <td>${modelChild1.description }</td>
	                                								<td>
	                                								<form action="/bbs/form/saveThumUrl.html" onsubmit="return checkThumUrl()" method="post" enctype="multipart/form-data">
									                                	<div class="userimgbox" style="display:block"> <img src="${modelChild1.thumUrl }" class="userimg"><p class="changeuserimg">上传图片</p><input type="file" id="file" name="file" class="uploadicon hidebtn"  onchange="setThumUrl(this.value,${modelChild1.id })"></div>
									                                	<div style="display: none;">
									                                	<input type="text" name="id" value="${modelChild1.id }">
									                                	<input type="submit" id="subImg${modelChild1.id }">
									                                	</div>
									                                </form>
	                                								</td>
						                                            <td><input type="text" value="${modelChild1.url }" class="moduleurl" onchange="updateUrl(this.value,${modelChild1.id })"> </td>
						                                            <td><input type="text"  class="modulesort" value="${modelChild1.sort }" onchange="updateOrder(this.value,${modelChild1.id })"> </td>
						                                        	<td><span class="change"></span></td>
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
	                                </td>
	                            </tr>
	                            </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
    </section>
<form action="/bbs/form/saveForm.html" onsubmit="return check()"  method="post" enctype="multipart/form-data">
	<div class="pop addrolepop">
		<div class="popbg"></div>
	    <div class="layel">
		        <h3 class="poptitle">新增版块<i class="closeicon"></i></h3>
		        <input type="hidden" value="" id="formPid" name="parentForm.id">
		        <h3 class="addtitle"><span id="formTitle"></span></h3>
		        <div class="row">
		            <span class="infortype tr">名称:</span>
		            <input type="text" value="" id="formName" name="name" class="rolename">
		        </div>
		        <div class="row">
		            <span class="infortype tr" style="float:left;display:inline;">缩略图:</span>
		            <input type="file" value="" id="file" name="file" style="padding-left:10px;">
		        </div>
		        <div class="row" id="descriptionDiv">
		            <span class="infortype tr">概要描述:</span>
		            <input type="text" value="" id="description" name="description" class="rolename">
		        </div>
		        <div class="row">
		        	<span class="infortype tr">url:</span>
		            <input type="text" value="" id="formlUrl" name="url" class="rolename">
		        </div>
		        <div class="row" id="perDiv">
		        	<span class="infortype tr">权限标签:</span>
		            <input type="text" value="" id="permissionsTag" name="permissionsTag" class="rolename">
		        </div>
		        <div class="row">
		        	<span class="infortype tr">排序:</span>
		            <input type="text" value="" id="formOrder" name="sort" class="rolename">
		        </div>
		        <div class="row">
		        	<span class="infortype tr">页面效果:</span>
		        	<select name="displayType" class="rolename">
			        	<c:forEach items="${displays}" var="item">
			        		<option value="${item.key }">${item.value }</option>
			        	</c:forEach>
		        	</select>
		        </div>
		        <div class="row">
		        	<span class="infortype tr">版块标题:</span>
		            <input type="text" value="" id="formHeadTitle" name="headTitle" class="rolename">
		        </div>
		        <div class="row">
		        	<span class="infortype tr">版块描述:</span>
		            <input type="text" value="" id="formHeadDesc" name="headDesc" class="rolename">
		        </div>
		        <div class="row">
		        	<span class="infortype tr">版块关键词:</span>
		            <input type="text" value="" id="formHeadKewords" name="headKewords" class="rolename">
		        </div>
		        <div class="row" id="operateDiv">
		        	<span class="infortype tr">权限:</span>
		            <c:forEach items="${operateList}" var="operate" varStatus="st">
		            	<input type="checkbox" name="operates" value="${operate.id }">${operate.operatename }
		            </c:forEach>
		        </div>
		  		<input type="submit" class="addrole" value="保存">
	    </div>
	</div>
</form>

<form action="/bbs/form/saveForm.html" onsubmit="return checkPer()" method="post" enctype="multipart/form-data">
	<div class="pop addrolepop1">
	    <div class="popbg"></div>
	    <div class="layel">
			        <h3 class="poptitle">修改权限<i class="closeicon"></i></h3>
			        <input type="hidden" value="" id="formId" name="id">
			        <div class="row" style="text-align:center;">
			        	<span class="infortype tr" style="width:auto;">权限:</span>
			            <c:forEach items="${operateList}" var="operate" varStatus="st">
			            	<input type="checkbox" name="operates" id="operate${operate.id }" value="${operate.id }">${operate.operatename }
			            </c:forEach>
			        </div>
			  		<input type="submit" class="addrole" value="保存">
		 </div>
	</div>
</form>
</body>
</html>