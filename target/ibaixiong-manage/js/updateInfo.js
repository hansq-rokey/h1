
		/*************用户信息及密码修改*************/
		$(function(){
    		$('#setInfo').click(function(){
				$('#UserInfoPwdupdate').show();
			});
    		$('#UserInfoPwdupdate').mouseleave(function(){
    			$('#UserInfoPwdupdate').hide();
    		});
    		var genderv=$("#genderv").val();
    		if(genderv=="女"){
    			document.getElementById("gender").options[1].selected=true;
    		}else{
    			document.getElementById("gender").options[0].selected=true;
    		}
		})
		
		function popupDiv(div_id) {
    		var genderv=$("#gender").val();
    		if(genderv=="w"){
    			document.getElementById("gender").options[1].selected=true;
    		}else{
    			document.getElementById("gender").options[0].selected=true;
    		}
    		
    		var div_obj = $("#" + div_id);
			div_obj.show("slow").css("top",($(document).height()-200)/2 ).css("left",($(document).width()-400)/2 );
			
		}
		function hideDiv(div_id) {
			$("#" + div_id).hide("slow"); 
		}

		function checkPWD(){
			var oldPWD=$("#oldPWD").val();
			var newPWD=$("#newPWD").val();
			var newPWD2=$("#newPWD2").val();
			
			if(oldPWD==""||oldPWD==null||$.trim(oldPWD)==""){
				alertLayel("原密码不能为空！");
				$("#oldPWD").val("");
				$("#oldPWD").focus();
				return false;
			}else if(newPWD==""||newPWD==null||$.trim(newPWD)==""){
				alertLayel("新密码不能为空！");
				$("#newPWD").val("");
				$("#newPWD2").val("");
				$("#newPWD").focus();
				return false;
			}else if(newPWD2==""||newPWD2==null||$.trim(newPWD2)==""){
				alertLayel("确认密码不能为空！");
				$("#newPWD").val("");
				$("#newPWD2").val("");
				$("#newPWD2").focus();
				return false;
			}else if(newPWD!=newPWD2){
				$("#newPWD").val("");
				$("#newPWD2").val("");
				alertLayel("2次密码不一致，请重新输入！");
				$("#newPWD").focus();
				return false;
			}else{
				var url = '../login/updatePWD.action';
		        // 获取所需的表单值，并以json的数据格式保存到params中
		        var params = {
		            "newPWD" : $("#newPWD").val()
		        };
		         // 使用$.post方式
		        $.post(//主动提交数据
		            url,        // 服务器要接受的url
		            params
		        );
		        $("#updateUserPwd").hide("slow"); 
		        alertLayel("密码修改成功！");
		        $("#oldPWD").val("");
	            $("#newPWD").val("");
	            $("#newPWD2").val("");
				return false;
			}
		}
		
		function checkOldPWD(){
			var url = '../login/check.action';
	        // 获取所需的表单值，并以json的数据格式保存到params中
	        var params = {
	            "password" : $("#oldPWD").val()
	        };
	         // 使用$.post方式
	        $.post(//主动提交数据
	            url,        // 服务器要接受的url
	            params,     // 传递的参数
	            // 下面是服务器返回后要执行的函数，其中参数 data保存的就是服务器发送到客户端的数据
	            function callback(data){//提交数据后响应的处理
	            	var re=data.reg;
	                if (re == "existed"){
	                	$("#oldPWD").val('');
	                    $("#newPWD").val('');
	                   	$("#newPWD2").val('');
	                   	$("#oldPWD").focus();
	                   	alertLayel("原密码有误，请重新输入!");
	                }
				},
	        	'json'  //数据传递的类型  json
	        );
		}
		

		
		function checkInfo(){
			var username=$("#username").val();
			var cnname=$("#cnname").val();
			var certno=$("#certno").val();
			var phoneno=$("#phoneno").val();
			var detailaddr=$("#detailaddr").val();
			
			if(username==""||username==null||$.trim(username)==""){
				alertLayel("用户名不能为空！");
				$("#username").val("");
				$("#username").focus();
				return false;
			}else{
				var url = '../login/checkUname.action';
		        // 获取所需的表单值，并以json的数据格式保存到params中
		        var params = {
		            "userVO2.user_name" : $("#username").val(),
		            "userVO2.cn_name" : $("#cnname").val(),
		            "userVO2.certno" : $("#certno").val(),
		            "gender" : $("#gender").val(),
		            "userVO2.phoneno" : $("#phoneno").val(),
		            "userVO2.detailaddr" : $("#detailaddr").val()
		        };
		         // 使用$.post方式
		        $.post(//主动提交数据
		            url,        // 服务器要接受的url
		            params,
		            function callback(data){
		            	var re=data.cuname;
		            	if(re=="existed"){
		            		alertLayel("用户名已存在，请重新修改!");
		            		$("#username").val($("#hidname").val());
		            		$("#username").focus();
		            		return false;
		            	}else
		            		$("#UpdateUserInfo").hide("slow"); 
		            	alertLayel("用户信息修改成功！");
		            		return false;
		            },
		            'json'
		        );
		        
				return false;
			}
		}
