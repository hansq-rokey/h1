/**
* 判断ajax请求返回的code值
* @param object obj ajax返回的json对象
* @return boolen true代表成功，false代表失败
* @author zhaolei
* @date 2015年8月4日
*/
function checkCode( obj ) {
	if ( obj.code != 0 ) {
		alertLayel(obj.message);
		return false;
	} else {
		return true;
	}
}
//弹窗
function alertLayel(e){
	var html='<div class="alertpop" style="display: block;position: fixed;width: 100%;height:100%;top:0;left:0;z-index: 9999;"><div class="popbg closepop" style="    width: 100%;height:100%;position: absolute;top:0;left:0;z-index: 989;background: #000;opacity: 0.5;"></div><div class="alertLayel" style="width:400px;height:200px;padding-top:10px;background:#fff;position:absolute;margin:auto;top:0;left:0;right:0;bottom:0;z-index:9999;"><h3 style="height:30px;position:relative;"><i class="closeicon" style="margin-right:12px;margin-top:0;"></i> </h3><p class="alertContent" style="line-height:30px;font-size:14px;text-align:center;height:100px;">'+e+'</p><div class="row tc" style="margin:0;"><input type="button" value="确定" class="alertBtn" style="height:30px;border:none;width:80px;background:#ff6200;color:#fff;"></div></div></div>';
	$("body").append(html);
}
$(document).ready(function(){
    $(document).on('click','.alertBtn,.closeicon,.closepop',function(){
    	$('.alertpop').remove();
    });
})
/*
$(document).on('mousemove', '.sele_box', function(event) {
	$(this).children('.delect_city').fadeIn(200);
});
$(document).on('mouseout', '.sele_box', function(event) {
    $(this).children('.delect_city').fadeOut(200);
});*/