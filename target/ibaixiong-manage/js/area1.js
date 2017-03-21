function showLocation(province , city ) {
	var loc	= new Location();
	var title	= ['省份'];
	var v1='',v2='';
	var timer;
	$('.sele em').click(function(e){
		$(this).parent().find('.loc').show();
		$(this).parent().siblings().find('.loc').hide();
		stopPropagation(e) 
	});
	$('.loc_province').on('click','li',function(e) {
		v1=$(this).attr('value');//当前地址value
		var templateId = $("#id").val();//当前模板id
		var loc_show=$(".sele_box");
		var loc_box=$(this).parents('.addressSelect').siblings('.s_text');
		var value_box=$(this).parents('.addressSelect').siblings('.s_value');
		var t=$(this).text();
		var flag=false;
		$.ajax({
			url:"/mall/template/checkArea.html",
			type:"POST",
			data:{"provinceId":v1,"templateId":templateId},
			dataType:"JSON",
			success:function(data){
				if(data.success){
					for(var i=0;i<loc_show.length;i++){
						if($(loc_show[i]).text() == t){	
							flag = true;break;
						}else{flag = false;}
					}
					if(!flag){
						loc_box.append('<span class="sele_box" data-id="'+v1+'" style="display: inline-block;">'+t+'</span>');
						value_box.append('<input type="hidden" name="province_id" value="'+v1+'">');
				   }else{return false;}
				}
			}
		});
		$(this).parents('.prov').find('em').text(t).attr('value', v1);
		$("#location_id").val(v1);
		showSele($(this),e);
	})
	if (province) {
		loc.fillOption('loc_province' , '0' , province);		
	}else {
		loc.fillOption('loc_province' , '0');
	};
};

function showLocation_addCity(obj) {
	var loc	= new Location();
	var title	= ['省份'];
	var v1='',v2='';
	var timer;
	$('.sele em').click(function(e){
		$(this).parent().find('.loc').show();
		$(this).parent().siblings().find('.loc').hide();
		stopPropagation(e) 
	});
	obj.on('click','li',function(e) {
		v1=$(this).attr('value');//当前地址value
		var templateId = $("#id").val();//当前模板id
		var loc_show=$(".sele_box");
		var loc_box=$(this).parents('.addressSelect').siblings('.s_text');
		var value_box=$(this).parents('.addressSelect').siblings('.s_value');
		var t=$(this).text();
		var flag=false;
		$.ajax({
			url:"/mall/template/checkArea.html",
			type:"POST",
			data:{"provinceId":v1,"templateId":templateId},
			dataType:"JSON",
			success:function(data){
				if(data.success){
					for(var i=0;i<loc_show.length;i++){
						if($(loc_show[i]).text() == t){	
							flag = true;break;
						}else{flag = false;}
					}
					if(!flag){
						loc_box.append('<span class="sele_box" data-id="'+v1+'" style="display: inline-block;">'+t+'</span>');
						value_box.append('<input type="hidden" name="province_id" value="'+v1+'">');
				   }else{return false;}
				}
			}
		});
		$(this).parents('.prov').find('em').text(t).attr('value', v1);
		$("#location_id").val(v1);
		showSele($(this),e);
	})
	loc.fillOption('loc_province' , '0');
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