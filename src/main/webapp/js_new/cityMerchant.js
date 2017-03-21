var  allSelect={
	allSelect:function(obj,id){
		
	}	
}
function showPage(number){//页面分页
        var keywords = $("#keywords").val();
        var dictCodeValue = $("#dictCodeValue").val();
        window.location.href="/crm/cityMerchant/cityMerchant.html?keywords="+keywords+"&dictCodeValue="+dictCodeValue+"&pageNo="+number;
  }
    function showUser(id){//获取用户列表
    	$(".salsman_name").find("p").remove();
    	$.ajax({
    		url:"/crm/cityMerchant/getUserList.html?departmentId="+id,
    		type:"POST",
    		dataType:"JSON",
    		async:false,
    		success:function(data){
    			if(data.result != null || data.result != undefined){
		   			$.each(data.result,function(i,item){
		   				var htm = '<p class="salsman_name_box" data-id="'+this.id+'">'+this.userName+'<em class="salsman_iphone">'+this.mobile+'</em></p>';
		   				$(".salsman_name").append(htm);
		   			});
    			}else{return ;}
    		}
    	});
    }
    function addUserMerchant(){//增加业务员
    	var arr=[];
    	$(".salsman_name_hidden input[type='hidden']").each(function(i,item){
    		var thisid=$(this).val();
    		arr.push(thisid);
    	})
    	var merchantId = $("#merchantid").val();
    	$.ajax({
    		url:"/crm/cityMerchant/saveUserMerchant.html?merchantId="+merchantId,
    		data:{"merchantId":merchantId,"ids":arr},
    		type:"POST",
    		dataType:"JSON",
    		async:false,
    		success:function(data){
    			window.location.reload();
    		}
    	});
    }
    //获取代理商层级
    $(document).ready(function(){
    	setType();
    	var typeValue = $("#dictCodeValue").val();
		if(typeValue != null && typeValue != ""){
			$('.selet-Consignee li').each(function(){
				var typeVal=$(this).attr('data-id');
				if(typeValue==typeVal){
					$('#typeName').text($(this).text());
				}
			});
		}
		$(".Consignee").on("click",function(){
			$('#selet-Consignee').fadeIn(10);
		});
    })
    var setTypeVal=function(typeVal){
	  	   var _name = $("#liu"+typeVal).attr("_name");
	  	   $("#typeName").text(_name);
	  	   $("#dictCodeValue").val(typeVal);
	  	   $('#selet-Consignee').fadeOut(10);
	}
    function setType(){
    	$.ajax({
	 		   url: "/status/selectType.html?typeName=CITY_MERCHANT_TYPE",
	 		   type: "POST",
	 		   dataType:"json",
	 		   cache:false,
	 		   async: false,
	 		   success: function(obj){
	 			  //获取数据 生成菜单部分
	 			  var html = "";
	 			  var data = obj.result;
	 			  for(var o in data){
	 				 html += "<li data-id='"+data[o].dictCodeValue+"' onclick='setTypeVal("+data[o].dictCodeValue+")' id='liu"+data[o].dictCodeValue+"' _name='"+data[o].dictCodeName+"'>"+data[o].dictCodeName+"</li>";
	 			  }
	 			  $("#selet-Consignee").append(html);
	 		   }
   		});
    }
/*代理利润设置*/
$(document).on("click",".proa_set",function(){
	$(".prt_set").fadeIn(500);
	var dataId=$(this).attr("data-id");
	$("#merchantId").val(dataId);
	ajaxFn("/crm/cityMerchant/getProduct.html",dataId,function(data){
		if(data.success){
			var profits = data.result;
			$(profits).each(function(item){
				var id = this.id;
				$("input:checkbox[name='product1']").each(function(item){
					if(id==$(this).val()){
						$(this).prop("checked",true);
						return false;
					}
				});
			});
		}
	})
	ajaxFn("/crm/cityMerchant/getProductProfit.html",dataId,function(data){
		if(data.success){
			var profits = data.result;
			$(profits).each(function(item){
				var id = this.id;
				$("input:checkbox[name='product2']").each(function(item){
					if(id==$(this).val()){
						$(this).prop("checked",true);
						return false;
					}
				});
			});
		}
	})
})
$(document).on("click",".pro_close",function(){//业务员弹窗取消
	$(".prt_set,.salesman").fadeOut(200);
	$(".salsman_name_hidden,.salsman_name_data").empty();
	$(".pro_list input[type='checkbox']").each(function(){
		$(this).prop("checked",false);
	});
});
function addProduct(){//区域利润保存验证
	var merchantId = $("#merchantId").val();
	var checked1 = [];
	var checked2 = [];
	$("input:checkbox[name='product1']:checked").each(function(){
		checked1.push($(this).val());
	}); 
	$("input:checkbox[name='product2']:checked").each(function(){
		checked2.push($(this).val());
	});
	$.ajax({
		url:"/crm/cityMerchant/addProduct.html",
		type:"POST",
		data:{"merchantId":merchantId,"productIds":checked1,"productProfitIds":checked2},
		dataType:"JSON",
		async: false,
		success:function(data){
			if(data.success){
				window.location.reload();
			}
		}
	});
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
};
$(document).on("click",".content_on a",function(){//区域设置切换
    var index=$(this).index();
    $(this).addClass('activeon').siblings().removeClass('activeon');
    $(".procut_list .pro_list").eq(index).css('display', 'block').siblings(".pro_list").css('display', 'none');
});
$(document).on('click', '.pro_tag input[type="radio"]', function() {
	var  $this_id=$(this).attr('id');
		 tick($this_id);
});
function tick(id){//产品切换
	var  $this_top=$("#pro_top input[type='checkbox']");//代理产品
	var  $this_bottom=$("#pro_bottom input[type='checkbox']");//区域利润
	if(id != null){
		if(id =="ch1"){
			$this_top.each(function(i,item) {
				 $(this).prop("checked",false);
				});
		}
		if(id =="ch2"){
			$this_top.each(function(i,item) {
				 $(this).prop("checked","checked");
				});
		}
		if(id =="che1"){
			$this_bottom.each(function(i,item) {
				 $(this).prop("checked",false);
				});
		}
		if(id =="che2"){
			$this_bottom.each(function(i,item) {
				 $(this).prop("checked","checked");
				});
		}
	}
}
/*业务员设置*/
$(document).on("click",".org_set",function(){
	var thisId=$(this).attr("data-id");
	$("#merchantid").val(thisId);
	$.ajax({
		url:"/crm/cityMerchant/getUserMerchants.html?merchantId="+thisId,
		type:"POST",
		dataType:"JSON",
		async:false,
		success:function(data){
			$.each(data.result,function(i,item){
				var html='<p class="salsman_name_databox" data-id="'+this.id+'">'+this.userName+this.mobile+'<i class="salsman_delect">+</i></p>';
				var htmlInput='<input type="hidden" name="ids" value="'+this.id+'">';
				$(".salsman_name_hidden").append(htmlInput);
				$(".salsman_name_data").append(html);
			});
		}
	});
	$(".salesman").fadeIn(200);
});
$(document).on("click",".salesman_box p",function(){//选择部门对应员工
	var thisname=$(this).text();
	var thisId = $(this).attr("data-id");
	var dataIds=$(".salsman_name_databox");
	var flag=true;
	$.each(dataIds,function(i,item){
		var eachId=$(this).attr("data-id");
		if( eachId==thisId){
			 layer.msg("数据已经添加");
			flag=false;
		}
	});
	if(!flag)return;
	 var html='<p class="salsman_name_databox" data-id="'+thisId+'">'+thisname+'<i class="salsman_delect">+</i></p>';
	 var htmlInput='<input type="hidden" name="ids" value="'+thisId+'">';
	$(".salsman_name_hidden").append(htmlInput);
	$(".salsman_name_data").append(html);
});
$(document).on("click",".salsman_name_databox",function(){//删除已经选择的姓名
	var thisindex=$(this).index();
	$(".salsman_name_hidden input[type='hidden']").eq(thisindex).remove();
	$(this).remove();
});
$(document).on("click",".salsman_list a",function(){//left选择部门
	$(this).addClass("salsman_list_active").siblings().removeClass("salsman_list_active");
});