function showLocation(province , city , town) {
	
	var loc	= new Location();
	var title	= ['省份' , '城市' , '县/区'];
	
	
	var v1='',v2='',v3='';
	var timer;
	$(document).click(function(){
		setTimeout(function(){$('.loc').hide()},200);
	});

	$('.sele em').click(function(e){
		$(this).parent().find('.loc').show();
		$(this).parent().siblings().find('.loc').hide();
		stopPropagation(e) 
	});
	
	$('.rowopation').on('click','li',function(e){
		var t=$(this).text();
		if(t=='服务站'){
			t=1;
		}else{
			t=2;
		}
		$("#type").val(t);
	})

	$('.loc_province').on('click','li',function(e) {
		var oddData=$(this).parents('ul').data('odd');
		var thisvalue =$("#this-value");
		v1=$(this).attr('value');
		var t=$(this).text();
		if (oddData==1) {
          $("#add2_address1").text(t);
          $("add2_address1").attr('data-value',v1);
          $("#provinceId").val(v1);
  		  $("#provinceName").val(t);
		}else if(oddData==2){
           $("#sele_prov").val(t); 
           $("#provinceId_area").val(v1);
   		   $("#provinceName_area").val(t);
		}
		  thisvalue.val(t);
          thisvalue.attr("data-id",v1);
		//$(this).parents('ul').prev("input").val(t);
		$(this).parents('.prov').find('em').text(t).attr('value', v1);
		var $sib_city = $(this).closest('.prov').siblings('.city');
		var $sib_town = $(this).closest('.prov').siblings('.town');
		$sib_city.find('.loc_city').empty();
		$sib_city.find('em').text(title[1]);
		loc.fillOption('loc_city' , '0,'+v1);
		$sib_town.find('.loc_town').empty();
		$sib_town.find('em').text(title[2]);
		showSele($(this),e);
	})
	
	$('.loc_city').on('click','li',function(e) {
		var oddData=$(this).parents('ul').data('odd');
		var $sib_town = $(this).closest('.city').siblings('.town');
		var thisvalue =$("#this-value");
		v2=$(this).attr('value');
		var t=$(this).text();
		if (oddData==1) {
          $("#add2_address2").text(t);
          $("#cityId").val(v2);
  		  $("#cityName").val(t);
		}else if(oddData==2){
          $("#sele_city").val(t);
          $("#cityId_area").val(v2);
  		  $("#cityName_area").val(t);
		}
		$("#sele_city").val(t);
		 thisvalue.val(t);
         thisvalue.attr("data-id",v2);
		//$(this).parents('ul').prev("input").val(t);
		$(this).parents('.city').find('em').text(t).attr('value', v2);
		$sib_town.find('.loc_town').empty();
		$sib_town.find('em').text(title[2]);
		loc.fillOption('loc_town' , '0,' + v1 + ',' + v2);
		showSele($(this),e);
	})
	
	$('.loc_town').on('click','li',function(e) {
		var thisvalue =$("#this-value");
		var oddData=$(this).parents('ul').data('odd');
		v3 = $(this).attr('value');
		var t=$(this).text();
		if (oddData==1) {
          $("#add2_address3").text(t);
          $("#countyId").val(v3);
  		  $("#countyName").val(t);
		}else if(oddData==2){
		   $("#town_value").val(v3);
           $("#sele_town").val(t);
           $("#countyId_area").val(v3);
   		   $("#countyName_area").val(t);
		}
		//$(this).parents('ul').prev("input").val(t);
		$(this).parents('.town').find('em').text(t).attr('value', v2);
		showSele($(this),e);
		 thisvalue.val(t);
         thisvalue.attr("data-id",v3);
	});
	if (province) {
		loc.fillOption('loc_province' , '0' , province);		
		if (city) {
			loc.fillOption('loc_city' , '0,'+province , city);			
			if (town) {
				loc.fillOption('loc_town' , '0,'+province+','+city , town);
			}
		};
	}else {
		loc.fillOption('loc_province' , '0');
	};
};

function showSele(o,e){
	$('.loc').css({'display':'none'});
	o.parents('.sele').next('.sele').find('ul').show();
	stopPropagation(e);
};
function stopPropagation(e) {
    if (e.stopPropagation){
    	e.stopPropagation();
    }else{
    	e.cancelBubble = true;
    }        
};

function  agentShowLocation(province , city , town) {//代理商添加 页面 城市增加
	
	var loc	= new Location();
	var title	= ['省份' , '城市' , '县/区'];
	var thisvalue =$("#this-value");
	var v1='',v2='',v3='';
	var timer;
	
	$(document).click(function(){
		setTimeout(function(){$('.loc').hide()},200);
	});

	$('.sele em').click(function(e){
		$(this).parent().find('.loc').show();
		$(this).parent().siblings().find('.loc').hide();
		stopPropagation(e) 
	});
	
	$('.rowopation').on('click','li',function(e){
		var t=$(this).text();
		if(t=='服务站'){
			t=1;
		}else{
			t=2;
		}
		$("#type").val(t);
	})
	
	//添加负责区域
	$('#addressSelect .loc_province').on('click','li',function(e) {
		var oddData=$(this).parents('ul').data('odd');
		v1=$(this).attr('value');
		var t=$(this).text();
		if (oddData==1) {
          $("#add2_address1").text(t);
          $("add2_address1").attr('data-value',v1);
          $("#provinceId").val(v1);
  		  $("#provinceName").val(t);
		}else if(oddData==2){
           $("#sele_prov").val(t); 
           $("#provinceId_area").val(v1);
   		   $("#provinceName_area").val(t);
		}
          if(agentType()){
				$("#addressSelect .town").fadeIn(200);
			}else{
				$("#addressSelect .town").fadeOut(200);
				$('#this-value').val('');
				$('#this-value').attr("data-id",'');
			}
          thisvalue.val(t);
          thisvalue.attr("data-id",v1);
		$(this).parents('.prov').find('em').text(t).attr('value', v1);
		var $sib_city = $(this).closest('.prov').siblings('.city');
		var $sib_town = $(this).closest('.prov').siblings('.town');
		$sib_city.find('.loc_city').empty();
		$sib_city.find('em').text(title[1]);
		loc.fillOption('loc_city' , '0,'+v1);
		$sib_town.find('.loc_town').empty();
		$sib_town.find('em').text(title[2]);
		showSele($(this),e);
	})
	
	$('#addressSelect .loc_city').on('click','li',function(e) {
		
		var oddData=$(this).parents('ul').data('odd');
		var $sib_town = $(this).closest('.city').siblings('.town');
		v2=$(this).attr('value');
		var t=$(this).text();
		ajxaFn($(this),v2);
		if (oddData==1) {
          $("#add2_address2").text(t);
          $("#cityId").val(v2);
  		  $("#cityName").val(t);
		}else if(oddData==2){
          $("#sele_city").val(t);
          $("#cityId_area").val(v2);
  		  $("#cityName_area").val(t);
		}
		$("#sele_city").val(t);
		 thisvalue.val(t);
         thisvalue.attr("data-id",v2);
		//$(this).parents('ul').prev("input").val(t);
		$(this).parents('.city').find('em').text(t).attr('value', v2);
		$sib_town.find('.loc_town').empty();
		$sib_town.find('em').text(title[2]);
		loc.fillOption('loc_town' , '0,' + v1 + ',' + v2);
		showSele($(this),e);
	})
	
	$('#addressSelect .loc_town').on('click','li',function(e) {
		var oddData=$(this).parents('ul').data('odd');
		v3 = $(this).attr('value');
		var t=$(this).text();
		ajxaFn($(this),v3);
		if (oddData==1) {
          $("#add2_address3").text(t);
          $("#countyId").val(v3);
  		  $("#countyName").val(t);
		}else if(oddData==2){
		   $("#town_value").val(v3);
           $("#sele_town").val(t);
           $("#countyId_area").val(v3);
   		   $("#countyName_area").val(t);
		}
		//$(this).parents('ul').prev("input").val(t);
		$(this).parents('.town').find('em').text(t).attr('value', v2);
		 showSele($(this),e);
		 thisvalue.val(t);
         thisvalue.attr("data-id",v3);
	});
	
	//选择地址
	$('#address .loc_province').on('click','li',function(e) {
		var oddData=$(this).parents('ul').data('odd');
		v1=$(this).attr('value');
		var t=$(this).text();
		if (oddData==1) {
          $("#add2_address1").text(t);
          $("add2_address1").attr('data-value',v1);
          $("#provinceId").val(v1);
  		  $("#provinceName").val(t);
		}else if(oddData==2){
           $("#sele_prov").val(t); 
           $("#provinceId_area").val(v1);
   		   $("#provinceName_area").val(t);
		}
		//$(this).parents('ul').prev("input").val(t);
		$(this).parents('.prov').find('em').text(t).attr('value', v1);
		var $sib_city = $(this).closest('.prov').siblings('.city');
		var $sib_town = $(this).closest('.prov').siblings('.town');
		$sib_city.find('.loc_city').empty();
		$sib_city.find('em').text(title[1]);
		loc.fillOption('loc_city' , '0,'+v1);
		$sib_town.find('.loc_town').empty();
		$sib_town.find('em').text(title[2]);
		showSele($(this),e);
	})
	
	$('#address .loc_city').on('click','li',function(e) {
		var oddData=$(this).parents('ul').data('odd');
		var $sib_town = $(this).closest('.city').siblings('.town');
		v2=$(this).attr('value');
		var t=$(this).text();
		if (oddData==1) {
          $("#add2_address2").text(t);
          $("#cityId").val(v2);
  		  $("#cityName").val(t);
		}else if(oddData==2){
          $("#sele_city").val(t);
          $("#cityId_area").val(v2);
  		  $("#cityName_area").val(t);
		}
		$("#sele_city").val(t);
		//$(this).parents('ul').prev("input").val(t);
		$(this).parents('.city').find('em').text(t).attr('value', v2);
		$sib_town.find('.loc_town').empty();
		$sib_town.find('em').text(title[2]);
		loc.fillOption('loc_town' , '0,' + v1 + ',' + v2);
		showSele($(this),e);
	})
	
	$('#address .loc_town').on('click','li',function(e) {
		var oddData=$(this).parents('ul').data('odd');
		v3 = $(this).attr('value');
		var t=$(this).text();
		if (oddData==1) {
          $("#add2_address3").text(t);
          $("#countyId").val(v3);
  		  $("#countyName").val(t);
		}else if(oddData==2){
		   $("#town_value").val(v3);
           $("#sele_town").val(t);
           $("#countyId_area").val(v3);
   		   $("#countyName_area").val(t);
		}
		//$(this).parents('ul').prev("input").val(t);
		$(this).parents('.town').find('em').text(t).attr('value', v2);
		showSele($(this),e);
	});
	
	if (province) {
		loc.fillOption('loc_province' , '0' , province);		
		if (city) {
			loc.fillOption('loc_city' , '0,'+province , city);			
			if (town) {
				loc.fillOption('loc_town' , '0,'+province+','+city , town);
			}
		};
	}else {
		loc.fillOption('loc_province' , '0');
	};
};
function ajxaFn(that,areaCode){
	var hiddenValue =[],
		txtValue,
		thisValue,
		parentId;
	
		hiddenValue = that.parent('ul').siblings("input[type = hidden]");
	    txtValue = that.parent('ul').siblings(".txt");
	    thisValue = $("#this-value");
	    parentId = $("#age-MerchantId").val();

		$.ajax({
			   url:'/crm/cityMerchant/checkArea.html',
			   type:'post',
			   data:{'parentId': parentId,'areaCode':areaCode},
			   dataType:'JSON',
			   success:function(data){
				   if(!data.success){
					   alertLayel(data.message);
					   hiddenValue.val('');
					   txtValue.val('');
					   txtValue.text('城市');
					   thisValue.val('');
					   thisValue.attr('data-id','');
				   }
			   }
		   })
}
