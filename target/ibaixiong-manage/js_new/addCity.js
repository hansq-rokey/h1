//添加数据
$(function(){
//键盘锁 文本框只能输入数字
	var $numTxtBox = $('input[data-txt="number"]');// 锁键状态
	$numTxtBox.keydown(function(e){// 绑定事件
		var oEvent = e || window.event;// 兼容处理
		if(!(oEvent.keyCode==46)&&!(oEvent.keyCode==8)&&!(oEvent.keyCode==9)&&!(oEvent.keyCode==37)&&!(oEvent.keyCode==39))// 键值
		if(!((oEvent.keyCode>=48&&oEvent.keyCode<=57)||(oEvent.keyCode>=96&&oEvent.keyCode<=105))) // 键值
			return false;
		//oEvent.returnValue = false; // firefox 兼容问题
	});
	//代理商详情追加货款弹窗
	$(document).on("click","#add_paymen,#add_coupon",function(){
		var num = "";
		//判断是首批提货款弹窗，还是优惠券追加弹窗
		if($(this).attr("id")=="add_paymen"){
			num = 1;
		}
		if($(this).attr("id")=="add_coupon"){
			num = 2;
		}
		var html='<div class="paymen_wrap"><div class="paymen_content"><h1 class="paymen_title">追加金额</h1><div class="paymen_list"><label class="text_tag_money">金额:</label><input type="text" onblur="verify(this.id,1)" class="paymen_money" id="paymen_money" autocomplete="off" ></div><div class="paymen_list"><label class="text_tag_passw">备注:</label><input class="paymen_remark" onblur="verify(this.id,2)" id="paymen_remark" type="text" autocomplete="new-password"></div><div class="paymen_list"><label class="text_tag_passw">密码:</label><input class="paymen_passw" onblur="verify(this.id,2)" id="paymen_passw" type="password" autocomplete="new-password"></div><div class="paymen_btn"><a class="paymen_sub" href="###">确定</a><a class="paymen_cancel" class="###">取消</a></div></div><input type="hidden" id="num" value="'+num+'"/></div>';
		$(".city_box").append(html);
	});
	$(document).on("click",".paymen_cancel",function(){//取消
		$(".paymen_wrap").remove();
	});
	$(document).on("click",".paymen_sub",function(){
		var money =parseFloat($("#paymen_money").val());//金额
		var pwd = $("#paymen_passw").val();//密码
		var remark = $("#paymen_remark").val();
		var level = $("#level").val();
		var id = $("#id").val();//代理商ID
		var num = $("#num").val();
		var path = "";
		if(num==1){
			path = "/crm/cityMerchant/addFirstMoney.html";
		}
		if(num==2){
			path = "/crm/cityMerchant/addCoupon.html";
		}
		if(pwd !=null && money >0 && remark!=null){
			$.ajax({
				url:path,
				type:"POST",
				data:{"merchantId":id,"money":money,"pwd":pwd,"remark":remark,"level":level},
				dataType:"JSON",
				success:function(data){
					console.log(data.message);
					if(data.success){
						layer.msg(data.message);
						//window.location.href="/crm/cityMerchant/orderDetails.html?id="+id+"&pageNo=1";
						setTimeout(function(){window.location.href="/crm/cityMerchant/orderDetails.html?id="+id+"&pageNo=1";},1000);
					}else{
						var blackstate=data.message;
							if(blackstate =="备注不能为空"){
								$("#paymen_remark").css("border","1px solid #f00");
								$(this).focus();
							}
							if(blackstate =="密码不能为空" || blackstate=="密码错误"){
								$("#paymen_remark").removeAttr("style");
								$("#paymen_passw").css("border","1px solid #f00");
								$(this).focus();
							}
					}
				}
			});
		}
	});
	//代理商详情扣除货款弹窗
	$(document).on("click","#minus_paymen,#minus_coupon",function(){
		var num = "";
		//判断是首批提货款弹窗，还是优惠券追加弹窗
		if($(this).attr("id")=="minus_paymen"){
			num = 1;
		}
		if($(this).attr("id")=="minus_coupon"){
			num = 2;
		}
		var html='<div class="pay_wrap"><div class="paymen_content"><h1 class="paymen_title">扣除金额</h1><p class="verify"></p><div class="paymen_list"><label class="text_tag_money">金额:</label><input type="text" onblur="verify(this.id,1)" class="pay_money" id="pay_money" autocomplete="off" ></div><div class="paymen_list"><label class="text_tag_passw">备注:</label><input class="pay_remark" onblur="verify(this.id,1)" id="pay_remark" type="text" autocomplete="new-password"></div><div class="paymen_list"><label class="text_tag_passw">密码:</label><input class="pay_passw" onblur="verify(this.id,1)" id="pay_passw" type="password" autocomplete="new-password"></div><div class="pay_btn"><a class="pay_sub" href="###">确定</a><a class="pay_cancel" class="###">取消</a></div></div><input type="hidden" id="num" value="'+num+'"/></div>';
		$(".city_box").append(html);
	});
	$(document).on("blur","#pay_money",function(){//验证金额
		var money =parseFloat($(this).val());
		if(!isNaN(money) && money > 0){
			$(this).css("border","1px solid #ccc");
		}else{
			$(this).val(" ");
			$(this).css("border","1px solid #f00");
		}
	});
	$(document).on("click",".pay_cancel",function(){//取消
		$(".pay_wrap").remove();
	});
	$(document).on("click",".pay_sub",function(){
		var money =parseFloat($("#pay_money").val());//金额
		var pwd = $("#pay_passw").val();//密码
		var remark = $("#pay_remark").val();
		var level = $("#level").val();
		//console.log(pwd);
		var id = $("#id").val();//代理商ID
		var num = $("#num").val();
		var path = "";
		if(num==1){
			path = "/crm/cityMerchant/minusFirstMoney.html";
		}
		if(num==2){
			path = "/crm/cityMerchant/minusCoupon.html";
		}
		if(pwd !=null && money >0 && remark!=null){
			$.ajax({
				url:path,
				type:"POST",
				data:{merchantId:id,"money":money,"pwd":pwd,"remark":remark,"level":level},
				dataType:"JSON",
				success:function(data){
					if(data.success){
						layer.msg(data.message);
						setTimeout(function(){window.location.href="/crm/cityMerchant/orderDetails.html?id="+id+"&pageNo=1";},1000);
					}else{
						var blackstate=data.message;
						if(blackstate =="备注不能为空"){
							$("#pay_remark").css("border","1px solid #f00");
							$(this).focus();
						}
						if(blackstate =="密码不能为空" || blackstate=="密码错误"){
							$("#pay_remark").removeAttr("style");
							$("#pay_passw").css("border","1px solid #f00");
							$(this).focus();
						}
					}
				}
			});
		}
	})
});
function verify(value,type){
		var thisValue=$("#"+value).val();//获取当前值
		console.log(thisValue+"~~~"+type);
		if(type ==1){//金额
			if(!isNaN(thisValue) && thisValue > 0){
					$(value).css("border","1px solid #ccc");
				}else{
					$(value).val(" ");
					$(value).css("border","1px solid #f00");
					$(value).focus();
				}
			}
		if(type == 2){//备注
			if(thisValue !=null && thisValue !=""){
				$(value).css("border","1px solid #ccc");
			}else{
				$(value).val(" ");
				$(value).css("border","1px solid #f00");
				$(value).focus();
			}
		}
	}
///*数据处理*/
function showPage(number){
	var linkTel = $("#linkTel").val();
	var daterange = $("#daterange").val();
	window.location.href="/crm/cityMerchant/cityMerchant.html?linkTel="+linkTel+"&daterange="+daterange+"&pageNo="+number;
}
function showList(){
	window.location.href="/crm/cityMerchant/cityMerchant.html";
}
function ajaxFn(url,dataId,sFn,eFn){//ajax公共函数
	$.ajax({
		url:url,
		type:"POST",
		data:{"merchantId":dataId},
		dataType:"JSON",
		async:false,
		success:function(data){
			sFn(data);
		}
	});
}
function checkCode( obj ) {
	if ( obj.code != 0 ) {
		alertLayel(obj.message);
		return false;
	} else {
		return true;
	}
}
function money_contrast(a,b){
	if(parseInt(a) > parseInt(document.getElementById(b).value)){
		layer.msg("解冻金额不能大于冻结金额");
		return false;
	}else{
		return true;
	}
}