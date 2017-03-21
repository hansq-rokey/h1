<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function changePassword(){
	var oldPWD=$("#oldPWD").val();
	var newPWD=$("#newPWD").val();
	var newPWD2=$("#newPWD2").val();
	
	if(oldPWD==""||oldPWD==null||$.trim(oldPWD)==""){
		$.messager.alertLayel('提示', '原密码不能为空！');
		$("#oldPWD").val("");
		$("#oldPWD").focus();
		return false;
	}else if(newPWD==""||newPWD==null||$.trim(newPWD)==""){
		$.messager.alertLayel('提示', '新密码不能为空！');
		$("#newPWD").val("");
		$("#newPWD2").val("");
		$("#newPWD").focus();
		return false;
	}else if(newPWD2==""||newPWD2==null||$.trim(newPWD2)==""){
		$.messager.alertLayel('提示', '确认密码不能为空！');
		$("#newPWD").val("");
		$("#newPWD2").val("");
		$("#newPWD2").focus();
		return false;
	}else if(newPWD!=newPWD2){
		$.messager.alertLayel('提示', '2次密码不一致，请重新输入！');
		$("#newPWD").val("");
		$("#newPWD2").val("");
		$("#newPWD").focus();
		return false;
	}else{
        $.ajax({
 		   type: "POST",
 		   url: "<%=path%>/login/updatePWD.action",
 		   data: {newPWD:$("#newPWD").val(),oldPWD:$("#oldPWD").val()},
 		   dataType:"json",
 		   success: function(result){
 			   if(result.status){
 				  $.messager.alertLayel('提示', '修改成功');
 				  $('#popup_first').window('close');
 		   		}else{
 		   			$.messager.alertLayel('提示', result.msg);
 		   		}
 		   }
 		});
	}
}
</script>
<div class="updateUserPwd_win_condition">
		<table cellspadding="0" cellspacing="3">
			<tr>
				<td class="UpdatePwd_td1">
					原密码
				</td>
				<td class="">
					<input class="Pwdinput" type="text" id="oldPWD" name="oldPWD" />
				</td>
			</tr>
			<tr>
				<td class="UpdatePwd_td1">
					修改密码
				</td>
				<td class="">
					<input class="Pwdinput" type="password" id="newPWD" name="newPWD" />
				</td>
			</tr>
			<tr>
				<td class="UpdatePwd_td1">
					确认密码
				</td>
				<td class="">
					<input class="Pwdinput" type="password" id="newPWD2" name="newPWD2" />
				</td>
			</tr>
			<tr>
				<td colspan="2"
					style="height: 30px; vertical-align: middle; text-align: center;">
					<span> 
						<input type="button" onclick="changePassword();" style="cursor:pointer" value="确定"/>
					 </span>
				</td>

			</tr>
		</table>
</div>
