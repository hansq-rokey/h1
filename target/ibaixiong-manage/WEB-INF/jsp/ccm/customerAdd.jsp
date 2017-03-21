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
    <link href="../../../css/addcustomer.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../plug/adddatetime.js" type="text/javascript"></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>客诉管理</title>
    <script type="text/javascript">
    $(document).ready(function(){
    	setType();
    	setUser();
    });
    function setType(){
    	var questionId = $("#questionId").val();
    	if(questionId == ""){
	    	$.ajax({
		 		   url: "/ccm/type/getTypeList.html",
		 		   type: "POST",
		 		   dataType:"json",
		 		   cache:false,
		 		   success: function(obj){
		 			  if ( !checkCode( obj ) ) {
		 					return;
		 			  }
		 			  //获取数据 生成菜单部分
		 			  var html = "<i class='arrowtop'></i>";
		 			  var data = obj.result.typs;
		 			  for(var o in data){
		 				 html = html+ "<li onclick='setCsTypeVal("+data[o].id+")' id='li"+data[o].id+"' _name='"+data[o].name+"'>"+data[o].name+"</li>";
		 			  }
		 			  $("#csType").append(html);
		 		   }
	   		});
    	}
    }
    function setUser(){
    	$.ajax({
	 		   url: "/system/admin/getAdminListByRole.html?roleId=5",
	 		   type: "POST",
	 		   dataType:"json",
	 		   cache:false,
	 		   success: function(obj){
	 			  if ( !checkCode( obj ) ) {
	 					return;
	 			  }
	 			  //获取数据 生成菜单部分
	 			  var html = "<i class='arrowtop'></i>";
	 			  var data = obj.result.admins;
	 			  for(var o in data){
	 				 html = html+ "<li onclick='setUserVal("+data[o].id+")' id='liu"+data[o].id+"' _name='"+data[o].name+"'>"+data[o].name+"</li>";
	 			  }
	 			  $("#userU").append(html);
	 		   }
   		});
    }
    var setCsTypeVal=function(id){
	   var _name = $("#li"+id).attr("_name");
	   $("#csTypeSel").text(_name);
	   $("#ccTypeId").val(id);
	   $('#csType').fadeOut(10);
    }
    var setUserVal=function(id){
 	   var _name = $("#liu"+id).attr("_name");
 	   $("#userSel").text(_name);
 	   $("#adminId").val(id);
 	   $('#userU').fadeOut(10);
     }
    function check(){
    	var questionId = $("#questionId").val();
    	if(questionId == ""){
	    	var coustomerName = $('#coustomerName').val();
	    	if(coustomerName == null || coustomerName ==undefined || coustomerName == ''){
	    		alertLayel("客户姓名不可为空");
	    		return false;
	    	}
	    	var  callMe = $('#callMe').val();
	    	if( callMe == null || callMe  ==undefined ||  callMe == ''){
	    		alertLayel("称呼不可为空");
	    		return false;
	    	}
	    	var tel= $('#tel').val();
	    	if( tel == null || tel  ==undefined ||  tel == ''){
	    		alertLayel("联系电话不可为空");
	    		return false;
	    	}
	    	if (!tel.match(/^(((13[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|177|170|176|178|145|147|(18[0-9]{1}))+\d{8})$/)) {
				alertLayel("联系电话格式不正确");
				return false;
			}
	    	var ccTime= $('#ccTime').val();
	    	if( ccTime == null || ccTime  ==undefined || ccTime  == ''){
	    		alertLayel("客诉时间不可为空");
	    		return false;
	    	}
	    	var  ccTypeId = $('#ccTypeId').val();
	    	if( ccTypeId == null || ccTypeId  ==undefined || ccTypeId  == ''){
	    		alertLayel("客诉类型不可为空");
	    		return false;
	    	}
    	}
    	var csMemo  = $('#csMemo').val();
    	if( csMemo == null || csMemo  ==undefined || csMemo  == ''){
    		alert("问题描述不可为空");
    		return false;
    	}
    	var adminId  = $('#adminId').val();
    	if( adminId == null || adminId  ==undefined ||  adminId == ''){
    		alert("指派人员不可为空");
    		return false;
    	}
    	return true;
    }
    </script>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <div class="inforbox selectinforbox">
                	<form action="/ccm/question/save.html" onsubmit="return check()" method="post">
                	<c:if test="${question.id == null}">
                	<input type="hidden" name="ccmType.id" value="" id="ccTypeId"/>
                	</c:if>
                	<input type="hidden" name="selAdmin" value="" id="adminId"/>
                	<input type="hidden" name="id" id="questionId" value="${question.id }"/>
                	<input type="hidden" name="queryType" id="queryType" value="${queryType }"/>
                	<c:if test="${question.id == null}">
                	<div class="row">
                        <span class="addtypename">客户姓名:</span>
                        <input type="text" name="coustomerName" class="" id="coustomerName" >
                    </div>
                    <div class="row">
                        <span class="addtypename">称呼:</span>
                        <input type="text" name="callMe" class="" id="callMe">
                    </div>
                    <div class="row">
                        <span class="addtypename">联系电话:</span>
                        <input type="text" name="tel" class="" id="tel" >
                    </div>
                    <div class="row">
                        <span class="addtypename">客诉时间:</span>
                        <input type="text" name="ccTimeStr" id="ccTime" value="" placeholder="客诉时间" class="datetimepicker startdata" onclick="SelectDate(this,'yyyy-MM-dd')">
                    </div>
                    <div class="row">
                        <span class="addtypename">客诉类型:</span>
                        <span class="selectinput">
                            <span class="selectvalue" id="csTypeSel">-请选择-</span>
                            <i class="arrow arrowright"></i>
                            <ul class="option" id="csType">
                            </ul>
                        </span>
                    </div>
                    </c:if>
                    <c:if test="${question.id != null}">
	                	<div class="row">
	                        <span class="addtypename">客户姓名:</span>
	                        ${question.coustomerName }
	                    </div>
	                    <div class="row">
	                        <span class="addtypename">称呼:</span>
	                        ${question.callMe }
	                    </div>
	                    <div class="row">
	                        <span class="addtypename">联系电话:</span>
	                        ${question.tel }
	                    </div>
	                    <div class="row">
	                        <span class="addtypename">客诉时间:</span>
	                    	<fmt:formatDate value="${question.ccTime }" pattern="yyyy/MM/dd"/>
	                    </div>
	                    <div class="row">
	                        <span class="addtypename">用户描述:</span>
	                        ${question.customersMemo }
	                    </div>
                    </c:if>
                    <div class="row">
                        <span class="addtypename">问题描述:</span>
                        <textarea class="describe" name="csMemo" id="csMemo"></textarea>
                    </div>
                    <div class="row">
                        <span class="addtypename">指派人员:</span>
                        <span class="selectinput">
                            <span class="selectvalue" id="userSel">-选择-</span>
                            <i class="arrow arrowright"></i>
                            <ul class="option" id="userU">
                            </ul>
                        </span>
                    </div>
                    <div class="row">
                        <input type="submit" class="savebtn" value="保存">
                    </div>
                    </form>
                </div>
            </li>
        </ul>
    </section>
</body>
</html>
