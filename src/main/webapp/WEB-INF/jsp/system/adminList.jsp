<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    	function setStatus(id,name){
		   $("#status").val(id);
		   $("#statusSel").text(name);
	    }
    	function setOrg(id,name){
 		   $("#orgId").val(id);
 		   $("#orgSel").text(name);
 	    }
    	function toRoleList(){
    		location.href = "/system/role/getRoleList.html";
    	}
    	function check(){
    		var loginName = $("#loginName").val();
    		var userPwd = $("#userPwd").val();
    		var userPwd1 = $("#userPwd1").val();
    		var userName = $("#userName").val();
    		var orgId = $("#orgId").val();
    		//请求检测登陆名是否存在
    		var id = $("#id").val();
    		if(loginName == null || loginName ==undefined || loginName == ''){
    			alertLayel("登陆账号不可为空");
				return false;
			}
    		if(id == null || id ==undefined || id == ''){
    			//新增逻辑必须验证密码
	    		if(userPwd == null || userPwd ==undefined || userPwd == ''){
	    			alertLayel("密码不可为空");
					return false;
				}
	    		if(userPwd1 == null || userPwd1 ==undefined || userPwd1 == ''){
	    			alertLayel("确认密码不可为空");
					return false;
				}
	    		if(userPwd != userPwd1){
	    			alertLayel("密码与确认新密码不一致");
					return false;
	       		}
    		}else{
    			if(userPwd != userPwd1){
    				alertLayel("密码与确认新密码不一致");
					return false;
	       		}
    		}
    		if(userName == null || userName ==undefined || userName == ''){
    			alertLayel("姓名不可为空");
				return false;
			}
    		if(orgId == null || orgId ==undefined || orgId == ''){
    			alertLayel("部门不可为空");
				return false;
			}
    		//如果是新增该字段没有
    		if(id == null || id ==undefined || id == ''){
    			var b = false;
    			$.ajax({
     			   url: "/system/admin/checkLoginName.html?loginName="+loginName,
     			   type: "post",
     			   dataType:"json",
     			   cache:false,
     			   async: false,
     			   success: function(obj){
     		  			if ( !checkCode( obj ) ) {
     		  				b=true;
     						return;
     				    }
     			   }
     			});
    			if(b){
    				return false;
    			}
    		}
    		return true;
    	}
    	function showAdmin(id){
    		$('.addpersonpop').show();
    		$("#opTitle").text("修改员工");
    		//获取员工相关信息
    		$.ajax({
  			   url: "/system/admin/getAdminById.html?id="+id,
  			   type: "post",
  			   dataType:"json",
  			   cache:false,
  			   async: false,
  			   success: function(obj){
  		  			if ( !checkCode( obj ) ) {
  						return;
  				    }else{
  				    	//设置返回值
  				    	$("#id").val(id);
  				    	$("#orgId").val(obj.result.orgId); 
  				    	$("#orgSel").text(obj.result.orgName);
  				    	$("#loginName").val(obj.result.loginName);
  				    	$("#userPwd").val(obj.result.userPwd);
  				    	$("#userPwd1").val(obj.result.userPwd);
  				    	$("#userName").val(obj.result.userName);
  				    	$("#phone").val(obj.result.phone);
  				    	//设置所有checked不选中
  				    	$("input[name='roles']").removeAttr("checked");
  				    	if(obj.result.roles != null){
	  				    	var roleids = obj.result.roles.split(",");
	  				    	for (var i=0;i<roleids.length;i++){
	  				    		$("#role"+roleids[i]).attr("checked",'true');
	  				    	}
  				    	}
  				    }
  			   }
  			});
    	}
    	function toAdd(){
    		$('.addpersonpop').show();
    		$("#opTitle").text("新增员工");
    		$("#id").val("");
	    	$("#orgId").val(""); 
	    	$("#orgSel").text("-部门-");
	    	$("#loginName").val("");
	    	$("#userPwd").val("");
	    	$("#userPwd1").val("");
	    	$("#userName").val("");
	    	$("#phone").val("");
	    	//设置所有checked不选中
	    	$("input[name='roles']").removeAttr("checked");
    	}
    	function updateStatus(id,status){
    		if(status == 1){
    			//说明是需要解除禁用
    			$("#statusTitle").text("解除禁用");
    			$("#statusText").text("是否解除该账号禁用？");
    		}
    		if(status == -2){
    			//说明是需要禁用
    			$("#statusTitle").text("禁用账号");
    			$("#statusText").text("是否禁用该账号？");
    		}
    		$('.forbiddenpop').show();
    		$("#adminId").val(id);
    		$("#adminstatus").val(status);
    	}
    	$(document).ready(function(){
	    	var status = $("#status").val();
    		if(status != null && status != ""){
    			$('.option li').each(function(){
    				var id=$(this).attr('data-id');
    				if(status==id){
    					$('#statusSel').text($(this).text());
    				}
    			});
    		}
	    });
    </script>
</head>
<body>
<section>
    <ul class="partlist">
        <li>
            <span class="switch switched">员工列表</span>
            <div class="inforbox selectinforbox">
            	<form action="/system/admin/getAdminList.html" method="post">
            		<input type="hidden" name="status" value="${status }" id="status"/><!-- 下拉选择状态 -->
	                <div class="row">
	                    <span>状态:</span>
	                    <span class="selectinput plate">
	                        <span class="selectvalue" id="statusSel">全部状态</span>
	                        <i class="arrow arrowright"></i>
	                        <ul class="option">
	                            <li data-id="-2" onclick="setStatus(-2,'禁用')">禁用</li>
	                            <li data-id="1" onclick="setStatus(1,'解除禁用')">解除禁用</li>
	                        </ul>
	                    </span>
	                    <input type="text" name="queryName" class="personname" value="${queryName }" placeholder="登录账号、姓名">
	                    <input type="submit" class="searchbtn search" value="搜索">
	                    <input type="hidden" name="pageNo" id="pageNo" value="">
	                    <input type="button" class="addperson" onclick="toAdd()" value="新增员工">
	                    <table class="personlist">
	                        <thead>
	                        <tr>
	                            <td>序号</td>
	                            <td>姓名</td>
	                            <td>用户名</td>
	                            <td>部门</td>
	                            <td>联系方式</td>
	                            <td>操作</td>
	                        </tr>
	                        </thead>
	                        <tbody>
	                        	<c:forEach items="${adminList}" var="admin" varStatus="st">
			                        <tr>
			                            <td>${st.index+1 }</td>
			                            <td>${admin.userName }</td>
			                            <td>${admin.loginName}</td>
			                            <td>${admin.org.name}</td>
			                            <td>${admin.phone}</td>
			                            <td>
			                            	<a href="#" class="link amend" onclick="showAdmin(${admin.id})">修改</a>
			                            	<c:if test="${admin.status == 1 }"><a href="#" class="link" onclick="updateStatus(${admin.id},-2)">禁用</a></c:if>
			                            	<c:if test="${admin.status == -2 }"><a href="#" class="link" onclick="updateStatus(${admin.id},1)">解除禁用</a></c:if>
			                            	<!-- <a href="/system/admin/delete.html?id=${admin.id }" class="link delete">删除</a>  -->
			                            </td>
			                        </tr>
		                        </c:forEach>
	                        </tbody>
	                    </table>
	                </div>
	                <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </form>
            </div>
            <!-- 新增员工-->
        <form action="/system/admin/save.html" onsubmit="return check()" method="post">
        <input type="hidden" id="orgId" name="org.id" value="">
        <input type="hidden" id="id" name="id" value="">
        <div class="pop addpersonpop">
            <div class="popbg"></div>
            <div class="layel" style="z-index: 999">
                <h3 class="addtitle"><span id="opTitle">新增员工</span><i class="closeicon"></i></h3>
                <div class="row">
                    <span class="personattr">登录账号:</span>
                    <input type="text" name="loginName" id="loginName" value="" class="personaccount">
                </div>
                <div class="row">
                    <span class="personattr">登录密码:</span>
                    <input type="text" name="userPwd" id="userPwd" value="" class="personpass">
                </div>
                <div class="row">
                    <span class="personattr">确认密码:</span>
                    <input type="text" name="userPwd1" id="userPwd1" value="" class="surepass">
                </div>
                <div class="row">
                    <span class="personattr">姓名:</span>
                    <input type="text" name="userName" id="userName" value="" class="personname">
                </div>
                <div class="row">
                    <span class="personattr">部门:</span>
	            <span class="selectinput personaccount">
	            <span class="selectvalue" id="orgSel">-部门-</span>
                <i class="arrow arrowright"></i>
                <ul class="option">
               		<c:forEach items="${orgs}" var="org">
               			<li onclick="setOrg(${org.id},'${org.name}')">${org.name}</li>
               		</c:forEach>
                </ul>
            </span>
                </div>
                <div class="row">
                    <span class="personattr">手机:</span>
                    <input type="text" name="phone" value="" id="phone" class="personmobile">
                </div>
                <div class="row">
                    <span class="personattr">角色</span>
            	<span class="rolecheckbox">
                <c:forEach items="${roles}" var="role">
                	<input type="checkbox" class="rolecheck" id="role${role.id  }" value="${role.id }" name="roles">${role.name }<span class="space"></span>
                </c:forEach>
            	</span>
                </div>
                <div class="row">
                    <input type="submit" class="addpersonbtn" value="保存">
                </div>
            </div>
        </div>
		</form>
        <!-- 新增员工结束-->
    </li>
    <li>
        <span class="switch" onclick="toRoleList()">角色列表</span>
        <!-- 新增角色结束-->
        </li>
    </ul>
</section>
	<form action="/system/admin/updateStatus.html" method="post">
		<div class="pop forbiddenpop">
		<div class="popbg"></div>
			<input type="hidden" id="adminId" name="adminId">
			<input type="hidden" id="adminstatus" name="status">
		    <div class="layel">
		        <h3 class="poptitle"><span id="statusTitle">禁用账号</span><i class="closeicon"></i> </h3>
		        <div class="row">
		            <p class="warningtext"><span id="statusText"></span></p>
		        </div>
		        <div class="row">
		            <input type="submit" value="确认" class="confirm">
		        </div>
		    </div>
		</div>
    </form>
</body>
</html>