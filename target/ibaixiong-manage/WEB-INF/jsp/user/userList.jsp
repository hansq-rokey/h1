<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/usermanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>用户管理</title>
    <script type="text/javascript">
    	function toUser(){
    		location.href = "/bbs/user/getUserList.html?queryType=1";
    	}
    	function toUserDisable(){
    		location.href = "/bbs/user/getUserList.html?queryType=2&status=-2";
    	}
    	$(document).ready(function(){
	    	setRole();
	    	var roleId = $("#roleId").val();
    		if(roleId != null && roleId != ""){
    			$('.option li').each(function(){
    				var id=$(this).attr('data-id');
    				if(roleId==id){
    					$('#rolesel').text($(this).text());
    				}
    			});
    		}
	    });
	    function setRole(){
	    	$.ajax({
		 		   url: "/bbs/role/getRolesListAjax.html",
		 		   type: "POST",
		 		   dataType:"json",
		 		   cache:false,
		 		   async: false,
		 		   success: function(obj){
		 			  if ( !checkCode( obj ) ) {
		 					return;
		 			  }
		 			  //获取数据 生成菜单部分
		 			  var html = "<i class='arrowtop'></i>";
		 			  var data = obj.result.roles;
		 			  for(var o in data){
		 				 html = html+ "<li data-id='"+data[o].id+"' onclick='setRoleVal("+data[o].id+")' id='liu"+data[o].id+"' _name='"+data[o].name+"'>"+data[o].name+"</li>";
		 			  }
		 			  $("#roleu").append(html);
		 		   }
	   		});
	    }
	    var setRoleVal=function(id){
	  	   var _name = $("#liu"+id).attr("_name");
	  	   $("#rolesel").text(_name);
	  	   $("#roleId").val(id);
	  	   $('#roleu').fadeOut(10);
	    }
	    function toDisable(id){
			$('.frozenpop').show();
			$("#userId").val(id);
			$("#memo").val("");
		}
    	function disableUser(){
    		var userId = $("#userId").val();
    		var memo = $("#memo").val();
    		if( memo == null || memo  ==undefined || memo  == ''){
    			alertLayel("请输入冻结理由");
        		return false;
        	}else{
	    		$.ajax({
			 		   url: "/bbs/user/updateStatus.html?id="+userId+"&blockMemo="+memo+"&status=-2",
			 		   type: "POST",
			 		   dataType:"json",
			 		   cache:false,
			 		   success: function(obj){
			 			  if ( !checkCode( obj ) ) {
							return;
			 			  }else{
			 				window.location.reload();//重载页面
			 			  }
			 		   }
		   		});
        	}
    	}
    	function updateRole(userId){
    		$('.updateRole').show();
    		$('#userRoleId').val(userId);
			//获取保存过的权限记录
			$('input[name=roles]:checkbox').removeAttr("checked");
			$.ajax({
  			   url: "/bbs/user/getAjaxUserRole.html?userId="+userId,
  			   type: "post",
  			   dataType:"json",
  			   cache:false,
  			   async: false,
  			   success: function(obj){
  		  			if ( !checkCode( obj ) ) {
  						return;
  				    }else{
  				    	var perList = obj.result.roleList;
  				    	for(var i=0;i<perList.length;i++){
  				    		$("#roles"+perList[i].id).attr("checked","checked");
  				    	}
  				    }
  			   }
  			});
    	}
    	function checkrole(){
			var b = false;
			$('input:checkbox[name=roles]:checked').each(function(i){
		       b = true;
		    });
			if(!b){
				alertLayel("必须选中一个");
				return false;
			}
			return true;
		}
    	
    	function updateUser(userId){
    		$('.updateUser').show();
    		$('#id').val(userId);
    		$.ajax({
   			   url: "/bbs/user/getUser.html?id="+userId,
   			   type: "post",
   			   dataType:"json",
   			   async: false,
   			   success: function(data){
   					var cType = $("#cAccount").val();
   		  			var tbType = $("#tbAccount").val();
   		  			var bxType = $("#bxAccount").val();
   		  			if(cType==data.result.type){
		  				$("#cAccount").attr("checked","checked");
		  			}
   		  			if(tbType==data.result.type){
   		  				$("#tbAccount").attr("checked","checked");
   		  			}
   		  			if(bxType==data.result.type){
		  				$("#bxAccount").attr("checked","checked");
		  			}
   			   }
   			});
    	}
    </script>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch switched" onclick="toUser()">普通用户</span>
                <div class="inforbox selectinforbox">
                	<form action="/bbs/user/getUserList.html" method="post">
                    <div class="row">
                        <span class="selectinput plate">
                            <span class="selectvalue" id="rolesel">-请选择-</span>
                            <i class="arrow arrowright"></i>
                            <ul class="option" id="roleu">
                            </ul>
                        </span>
                        <input type="text" class="personname" name="queryName" value="${queryName }" placeholder="登录账号">
                        <input type="submit" class="searchbtn search" value="搜索">
                        <input type="hidden" name="queryType" value="1" >
	                    <input type="hidden" name="roleId" value="${roleId }" id="roleId">
	                    <input type="hidden" name="pageNo" id="pageNo" value="">
                    </div>
                    </form>
                    <div class="row">
                        <table class="">
                            <thead>
                            <tr>
                                <td>白熊号</td>
                                <td>账号</td>
                                <td>昵称</td>
                                <td>组</td>
                                <td>账号类型</td>
                                <td>注册时间</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${userList }" var="user" varStatus="qv">
	                            <tr>
	                                <td>${user.bxNum }</td>
	                                <td>${user.userName }</td>
	                                <td>${user.nickName }</td>
	                                <td>${user.roleNames }</td>
	                                <td>
	                                	<c:if test="${user.type==0 }">
	                                		C端普通账号
	                                	</c:if>
	                                	<c:if test="${user.type==1 }">
	                                		C端淘宝账号
	                                	</c:if>
	                                	<c:if test="${user.type==2 }">
	                                		C端业务员账号
	                                	</c:if>
	                                </td>
	                                <td><fmt:formatDate value="${user.createDateTime }" pattern="yyyy/MM/dd"/></td>
	                                <td>
	                                	<a href="#" class="link" onclick="toDisable(${user.id })">冻结</a>
	                                	<a href="#" class="link" onclick="updateRole(${user.id })">角色设置</a>
	                                	<a href="#" class="link" onclick="updateUser(${user.id })">员工类型设置</a>
	                                	<a href="#" class="link">查看日志</a>
	                                </td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </div>
            </li>
            <li>
                <span class="switch" onclick="toUserDisable()">冻结用户</span>
            </li>
        </ul>
    </section>
<div class="pop frozenpop">
	<div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">禁用账号<i class="closeicon"></i> </h3>
        <div class="row tl">
            <span class="addtypename">冻结原因:</span>
            <textarea class="frozentext" id="memo"></textarea>
        </div>
        <div class="row">
            <input type="button" value="确认" onclick="disableUser()" class="frozenbtn">
            <input type="hidden" id="userId">
        </div>
    </div>
</div>
<form action="/bbs/user/saveUserRole.html" onsubmit="return checkrole()"  method="post">
<div class="pop updateRole">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">修改角色<i class="closeicon"></i> </h3>
        <div class="row">
            <span class="addtypename">角色：</span>
           	<c:forEach items="${roleList}" var="role" varStatus="st">
            	<input type="checkbox" name="roles" id="roles${role.id }" value="${role.id }">${role.name }
            </c:forEach>
        </div>
        <div class="row tc">
        	<input type="hidden" id="userRoleId" name="userId">
            <input type="submit" value="保存" class="frozenbtn">
        </div>
    </div>
</div>
</form>
<form action="/bbs/user/updateUser.html"  method="post">
<div class="pop updateUser">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">修改账户类型<i class="closeicon"></i> </h3>
        <div class="row">
            <span class="addtypename">类型：</span>
            	<input type="radio" name="type" id="cAccount" value="0">C端普通账号
            	<input type="radio" name="type" id="tbAccount" value="1">C端淘宝账号
            	<input type="radio" name="type" id="bxAccount" value="2">C端业务员账号
        </div>
        <div class="row tc">
        	<input type="hidden" id="id" name="id">
            <input type="submit" value="保存" class="frozenbtn">
        </div>
    </div>
</div>
</form>
</body>
</html>