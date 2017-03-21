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


	$('.loc_province').on('click','li',function(e) {
		v1=$(this).attr('value');
		var t=$(this).text();
		$(this).parent().parent().find('.locName').val(t);
		$(this).parent().parent().find('.locId').val(v1);
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
		v2=$(this).attr('value');
		var t=$(this).text();
		$(this).parent().parent().find('.locName').val(t);
		$(this).parent().parent().find('.locId').val(v2);
		$(this).parents('.city').find('em').text(t).attr('value', v2);
		var $sib_town = $(this).closest('.city').siblings('.town');
		$sib_town.find('.loc_town').empty();
		$sib_town.find('em').text(title[2]);
		loc.fillOption('loc_town' , '0,' + v1 + ',' + v2);
		showSele($(this),e);
	})
	
	$('.loc_town').on('click','li',function(e) {
		v3 = $(this).attr('value');
		var t=$(this).text();
		$(this).parent().parent().find('.locName').val(t);
		$(this).parent().parent().find('.locId').val(v3);
		$(this).parents('.town').find('em').text(t).attr('value', v3);
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