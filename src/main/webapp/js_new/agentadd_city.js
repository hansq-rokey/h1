var EventUtil = { //跨浏览器事件
	addHandler:function(element,type,handler){//绑定addEventListener
		if(element.addEventLintener){
			element.addEventener(type,handler,false);
		}else if(element.attacEvent){
			element.attacEvent("on"+type,handler);
		}else{
			element['on'+type] = handler;
		}
	},
	removeHandler:function(element,type,handler){//移出removeEventener
		if(element.removeEventLintener){
			element.removeEventener(type,handler,false);
		}else if(element.datachEvent){
			element.datachEvent("on"+type,handler);
		}else{
			element['on'+type] = null;
		}
	},
	getEvent:function(event){//获取event 兼容写法
		return  event ? event : window.event;
	},
	stopPropagation:function(event){//兼容 阻止事件冒泡
		if(event.stopPropagation){
			event.stopPragation();
		}else{
			event.cancelBubble = true;
		}
	},
	pereventDefault:function(event){//阻止浏览器默认行为
		if(event.perventdefault){
			event.pereventDefault();
		}else{
			event.returnValue = false;
		}
	},
	getTarget:function(event){//兼容 浏览器target事件
		return event.target || event.srcElement;
	}
};
var Verify= {
	checkPhone:function(number){ /*电话号码验证*/
		if(!(/^(((13[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|177|170|176|178|171|145|147|(18[0-9]{1}))+\d{8})$/.test(number))){
		    return false; 
		}else{
			return true;
		}
	},
	banklength:function(content){//银行卡位数
		if(content< 15 || content>20){
			return false;
		}else{
			return true;
		}
	},
	carId:function(content){//身份证位数
		console.log(content);
		 var regIdNo = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		 if(!regIdNo.test(content)){
		  return false;
		 }else{
			 return true;
		 }
	},
	noLetter:function(obj){//只能是数字
		var val = document.getElementById(obj).value;
		if(isNaN(value)){
			 alertLayel("请输出数字");
			 val = "";
		 }
	}
};
function selectmian(e){//下拉选择
		var thisClass = e.target.parentNode.getAttribute('id');//判断当前下拉
		var addressSelect = document.getElementById('addressSelect');//
		var thisValue = document.getElementById('this-value');
		var e = e || window.e; 
		var thisparent = e.target.parentNode.getAttribute("id");
		var thishtml = e.target.innerHTML;
		var dataid = e.target.getAttribute("data-id");
		var createli = '';
		var thisarr = [];
		var agetadd = $("#agetadd-mian");
		var cityId = e.target.getAttribute('data-id');
		
		if (e.target && e.target.nodeName == "LI") {
			e.target.parentNode.parentNode.children[0].innerText = thishtml;
			e.target.parentNode.parentNode.children[0].setAttribute("data-id",dataid);
			e.target.parentNode.parentNode.children[1].value = dataid;
			if(thisparent == "top_agetadd"){
				$.ajax({
					   url:'/crm/cityMerchant/selectType.html',
					   type:'post',
					   data:{'cityId':cityId},
					   dataType:'JSON',
					   success:function(data){
						   for(var i in data.result){
							  createli +="<li data-id="+data.result[i].dictCodeValue+">"+data.result[i].dictCodeName+"</li>";
						   }
						   agetadd.siblings(".age-tac").text("--选择--");
						   agetadd.prev("#age-MerchantType").val('');
						   agetadd.empty();
						   agetadd.append(createli);						   
					   }
				   });
				   addressSelect.children[0].children[0].innerHTML="省份";
				   addressSelect.children[0].children[1].value="";
				   addressSelect.children[0].children[2].value="";
					   
				   addressSelect.children[1].children[0].innerHTML="城市";
				   addressSelect.children[1].children[1].value="";
				   addressSelect.children[1].children[2].value="";

				   addressSelect.children[2].children[0].innerHTML="地区";
				   addressSelect.children[2].children[1].value="";
				   addressSelect.children[2].children[2].value="";
				   
				   thisValue.value = "";
				   thisValue.dataset.id = '';
			}else if(thisparent =="agetadd-mian"){
				eidtreadonly();
				console.log(agentType());
				if(agentType()){
					$("#addressSelect .town").fadeIn(200);
				}else{
					$("#addressSelect .town").fadeOut(200);//地区隐藏
					$(".txtcity").text('城市');//初始化地址
					$(".txtprovince").text('省份');//初始化地址
					$('#this-value').val('');//清空隐藏域
					$('#this-value').attr("data-id",'');
				}
			}
		}
	}
function eidtreadonly(){//可编辑
	var areaProfit =$("#areaProfit");//区域系数
	var dataId = document.getElementById('age_MerchantType').value;//
	if(dataId != 1){
		areaProfit.prop("readonly","readonly");
		areaProfit.val('0');
	}else{
		areaProfit.prop("readonly",false);
	}
}
var agentType = function(){//判断代理商类型
	var thisType = $("#age_MerchantType").val();
	if(thisType == 1){
		return false;
	}else{
		return true;
	}
}
/*账号验证*/
function  verifyNumber(obj){
	var thisvalue = obj.value;
	if(Verify.checkPhone(thisvalue)){
		$.ajax({
            url: "/crm/cityMerchant/check.html?tel="+thisvalue,
            type: "POST",
            dataType: "json",
            async: false,
            success: function (data) {
            	if(data.code == 1){
            		alertLayel("该号码已添加");
            		obj.value="";
            		console.log(obj);
            	} 
            }
       })
	}else{
		 obj.value = "";
		 alertLayel("号码输入有误");
       	 return false;
	}
}
$(document).on('click','.sele_box',function(){
	var that=$(this);
    var thisOf=that.index();//下标
    var id=that.parents("#add2_addressc").data("id");
    var county_Id=$("#add2_value input").eq(thisOf).val();
	 $.ajax({
	        url: "/crm/cityMerchant/delete.html",
	        data:{"cityId":id,"areaCode":county_Id},
	        type: "POST",
	        dataType: "json",
	        async: false,
	        success: function (data) {
	        	that.remove();
	            $("#add2_value input").eq(thisOf).remove();
	      }
	   })
});
function check(){//保存
	
	var cityMerchantType =document.getElementById('age_MerchantType').value;
	var addNumber =document.getElementById('addNumber').value;
	var principal =document.getElementById('principal').value;
	var companyName =document.getElementById('companyName').value;
	var countyId = document.getElementById('countyId').value;

	if(addNumber != '' && countyId !='' && principal != '' && companyName != '' && cityMerchantType !='' && addNumber != null && countyId !=null && principal != null && companyName != null && cityMerchantType != null ){
		return true;
	}else{
		alertLayel("请完善信息");
		return false ;
	}
};
function edit(obj,coupon){//保证金输入 插入冻结金额
	var cupValue = document.getElementById(coupon);
	var value = obj.value;
		cupValue.value = value;
}
EventUtil.addHandler(window,'load',function(){//文本框值验证
	
	var areaProfit = document.getElementById('areaProfit');
	var agetadd_mian = document.getElementById('agetadd-mian');
	var top_agetadd = document.getElementById('top_agetadd');
	var banklenght = document.getElementById('banklenght');
	var carId = document.getElementById('carId');
	var CashDeposit = document.getElementById('CashDeposit');
	var firstPayment = document.getElementById('firstPayment');
	var pickUp = document.getElementById('pickUp');
	
	EventUtil.addHandler(agetadd_mian,"click",selectmian);
	EventUtil.addHandler(top_agetadd,"click",selectmian);
	EventUtil.addHandler(banklenght,"blur",function(event){//银行卡号
		var event = EventUtil.getEvent(event);
		var lenght = (event.target.value).trim().length;
		if(!Verify.banklength(lenght)){
			event.target.value = '';
			alertLayel("请输入正确的银行卡号");
		}
	});
	EventUtil.addHandler(carId,"blur",function(event){//身份证号
		
		var event = EventUtil.getEvent(event);
		var val = event.target.value;
		
		if(!Verify.carId(val)){
			alertLayel("请输入正确的身份证号");
			event.target.value = '';
		}
		
	});
	EventUtil.addHandler(areaProfit,"focus",eidtreadonly);//区域系数
	EventUtil.addHandler(areaProfit,"change",function(event){//区域系数
		var event = EventUtil.getEvent(event);
		var val = event.target.value;//获取值
		if(isNaN(val)){
			 alertLayel("请输出数字");
			 event.target.value = "";
		 }
	});
	
	EventUtil.addHandler(CashDeposit,"change",function(event){//保证金
		var event = EventUtil.getEvent(event);
		var val = event.target.value;//获取值
		if(isNaN(val)){
			 alertLayel("请输出数字");
			 event.target.value = "";
		 }
	});
	
	EventUtil.addHandler(firstPayment,"change",function(event){//提货款
		var event = EventUtil.getEvent(event);
		var val = event.target.value;//获取值
		if(isNaN(val)){
			 alertLayel("请输出数字");
			 event.target.value = "";
		 }
	});
	
	EventUtil.addHandler(pickUp,"change",function(event){//提货款
		var event = EventUtil.getEvent(event);
		var val = event.target.value;//获取值
		if(isNaN(val)){
			 alertLayel("请输出数字");
			 event.target.value = "";
		 }
	});
	
});
$(function(){
	//添加区域地址
	$(document).on('click', '#add_address', function(event) {
/*	   var sele_town=$("#this-value").val();*/
	   var town_value=$("#this-value").val();
	   var town_id =$("#this-value").attr("data-id");
	   var hiddenval=$('#add2_value input[type="hidden"]');
	   var arr=[];
	     if(town_value == ''){
	    	 alertLayel("请先选择地区");
	    	 return false;
	     }else{
		   for(var i=0 ; i<hiddenval.length;i++){
			    arr.push(parseInt(hiddenval[i].value));
		   }
		   for(var m in arr){
			   var tval=arr[m];
			   if(tval==parseInt(town_id)){
				   alertLayel("请不要重复选择地区"); 
				   return false;
			   }
		   	}
		   $.ajax({
			   url:'/proxyArea/add.html',
			   type:'post',
			   data:{"areaCode":town_id},
			   dataType:'JSON',
			   success:function(data){
				   if(data.success){
					   var box='<span class="sele_box">'+town_value+'<i class="delect_city">+<i></span>';
					   var value='<input type="hidden" name="areaCode" value='+town_id+'>'
					   $(".address_null").remove();
					   $("#add2_addressc").append(box);
					   $("#add2_value").append(value);
				   }else{
					   alertLayel(data.message);
					   return false;
				   }
			   }
		   })
	   	}
	 });
})
