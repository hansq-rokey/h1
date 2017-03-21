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
	    function toEnable(id){
			$('.frozenpop').show();
			$("#userId").val(id);
			$("#memo").val("");
		}
    	function enableUser(){
    		var userId = $("#userId").val();
    		var memo = $("#memo").val();
    		if( memo == null || memo  ==undefined || memo  == ''){
    			alertLayel("请输入解除冻结理由");
        		return false;
        	}else{
	    		$.ajax({
			 		   url: "/bbs/user/updateStatus.html?id="+userId+"&blockMemo="+memo+"&status=1",
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
    </script>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch " onclick="toUser()">普通用户</span>
            </li>
            <li>
                <span class="switch switched" onclick="toUserDisable()">冻结用户</span>
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
                        <input type="hidden" name="queryType" value="2" >
                        <input type="hidden" name="status" value="-2" >
	                    <input type="hidden" name="roleId" id="roleId" value="${roleId }">
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
                            <td>冻结时间</td>
                            <td>冻结原因</td>
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
	                             <td><fmt:formatDate value="${user.blockTime }" pattern="yyyy/MM/dd"/></td>
	                             <td>${user.blockMemo }</td>
	                             <td><a href="#" class="link" onclick="toEnable(${user.id })">解除冻结</a><a href="#" class="link">查看日志</a> </td>
	                         </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <jsp:include page="../include/pages_old.jsp"></jsp:include>
            </div>
            </li>
        </ul>
    </section>
<div class="pop frozenpop">
<div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">解除冻结<i class="closeicon"></i> </h3>
        
        <div class="row tl">
            <span class="addtypename" style="width: 120px;text-align: left;">解除冻结原因:</span>
            <textarea class="frozentext" id="memo"></textarea>
        </div>
        <div class="row">
            <input type="button" value="确认" onclick="enableUser()" class="frozenbtn">
            <input type="hidden" id="userId">
        </div>
    </div>
</div>
</body>
</html>