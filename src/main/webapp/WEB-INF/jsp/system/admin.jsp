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
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/personinfor.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>操作日志</title>
    <script type="text/javascript">
    	function updateMobile(mobile){
    		if (!mobile.match(/^(((13[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|177|170|176|178|145|147|(18[0-9]{1}))+\d{8})$/)) {
				alertLayel("手机格式不正确");
				return;
			}
    		//alert(mobile);
    		$.ajax({
   			   url: "/system/admin/updatePhone.html?phone="+mobile,
   			   type: "post",
   			   dataType:"json",
   			   cache:false,
   			   success: function(obj){
   		  			if ( !checkCode( obj ) ) {
   						return;
   				    }
   				 	if ( obj.code == 0 ) {
   						//获取登陆用户成功之后给值
   						alertLayel("修改电话号码成功!");
   					}
   			   }
   			});
    	}
    	function updatePwd(){
       		//alert(mobile);
       		var oldPwd = $("#oldPwd").val();
       		var newPwd1 = $("#newPwd1").val();
       		var newPwd2  = $("#newPwd2").val();
       		if(oldPwd == null || oldPwd ==undefined || oldPwd == ''){
       			alertLayel("原密码不可为空");
				return false;
			}
       		if(newPwd1 == null || newPwd1 ==undefined || newPwd1 == ''){
       			alertLayel("新密码不可为空");
				return false;
			}
       		if(newPwd2 == null || newPwd2 ==undefined || newPwd2 == ''){
       			alertLayel("确认新密码不可为空");
				return false;
			}
       		if(newPwd1 != newPwd2){
       			alertLayel("新密码与确认新密码不一致");
				return false;
       		}
       		$.ajax({
    			   url: "/system/admin/updatePwd.html?oldPwd="+oldPwd+"&newPwd="+newPwd1,
    			   type: "post",
    			   dataType:"json",
    			   cache:false,
    			   success: function(obj){
    		  			if ( !checkCode( obj ) ) {
    						return;
    				    }
    				 	if ( obj.code == 0 ) {
    				 		alertLayel("修改密码成功!");
    				 		$('.pop').hide();
    					}
    			   }
    			});
    	}
    </script>
</head>
<body>
    <section>
        <div class="row">
            <span class="infortype">登录账号:</span>
            <span>${admin.loginName }</span>
        </div>
        <div class="row">
            <span class="infortype">姓    名:</span>
            <span>${admin.userName }</span>
        </div>
        <div class="row">
            <span class="infortype">部    门:</span>
            <span>${admin.org.name }</span>
        </div>
        <div class="row">
            <span class="infortype">手    机:</span>
            <span><input type="text" class="mobile" value="${admin.phone }" onblur="updateMobile(this.value)"> </span>
        </div>
        <div class="row">
            <input type="button" value="修改密码" class="changepass">
        </div>
    </section>
<div class="pop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">修改密码<i class="closeicon"></i> </h3>
        <div class="row">
            <span class="infortype tr" style="width: 100px;">原密码：</span>
            <input type="password" class="oldpass" id="oldPwd">
        </div>
        <div class="row">
            <span class="infortype tr" style="width: 100px;">新密码：</span>
            <input type="password" class="newpass" id="newPwd1">
        </div>
        <div class="row">
            <span class="infortype tr" style="width: 100px;">确认新密码：</span>
            <input type="password" class="connewpass" id="newPwd2">
        </div>
        <div class="row">
            <input type="button" value="确认" onclick="updatePwd()" class="confirm">
        </div>
    </div>
</div>
</body>
</html>