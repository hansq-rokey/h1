/**
 * Created by junfei on 2015/8/24.
 */
$(document).ready(function(e){
    //判断IE浏览器版本
    if( $.browser.msie && ( $.browser.version == '6.0' || $.browser.version == '7.0'|| $.browser.version == '8.0') ){
        alert("您的浏览器版本过低，请尽快升级，否则会影响网页性能和操作！");
        return;
    }
    //左侧导航切换
    $('.firstnav').on('click',function(){
        var slide=$(this).parent().parent('.firstnavli').hasClass('currentli');
        if(!slide){
        	console.log(111);
            $('.currentli').find('ul').slideUp('slow');
            $('.currentli').removeClass('currentli');
            $(this).parent().parent('.firstnavli').addClass('currentli').find('ul').slideToggle('slow');
        }
        if(slide){
            $(this).parent().parent('.firstnavli').addClass('currentli').find('ul').slideToggle('slow');
            $(this).parent().parent('.currentli').removeClass('currentli');
        }
        //$(this).parent().parent('.firstnavli').addClass('currentli').find('ul').slideToggle('slow');
    });
    $('.secondnavli').click(function(){
        $('.selectsecondnavli').removeClass('selectsecondnavli');
        $(this).addClass('selectsecondnavli');
    });
    //右边内容模块宽度
    function rw(){
        var width=$(window).width();
        var height=$(window).height();
        $('.pop').css({'width':width,'height':height});
        width=width-237;
        height=height-55;
        $('.rightbox').css({'width':width,'height':height});
        $("#rightFrame").height(height);//计算右边高度：窗口高度-头部高度
        $('.publeftnav').css('height',height);
    }
    $('.secondrightbox').each(function(){
    	var width=$(this).width();
    	$(this).css('width',width+237);
    })
    rw();
    //窗口变化时
    $(window).resize(function(){
    	rw();
    	});
    //下拉菜单
    $(document).on('click','.selectinput',function(){
        $('.option').hide();
        $(this).find('.option').toggle();
        console.log(11)
    });
    //点击空白处下拉框隐藏
    $(document).bind("click",function(e){
        var target = $(e.target);
        if(target.closest(".option").length == 0&&target.closest(".selectinput").length == 0){
            $(".option").hide();
        }
    });
    $(document).on('click','.option li',function(e){
        var val=$(this).html();
        $(this).parent().parent('.selectinput').find('.selectvalue').text(val);
        $(this).parent().parent().find('.option').hide();
        e.stopPropagation();
    })
    //关闭弹窗
    $('.closeicon,.cancel').on('click',function(){
        $(this).parent().parent().parent('.pop').hide();
        $(".commodity_box tbody").remove();
        $("#price").val('');
    });
    //title提示样式
    $(function() {
        $(".reasontd").each(function(b) {//这里是控制标签
            if (this.title) {
                var c = this.title; //把title的赋给自定义属性 myTilte ，屏蔽自带提示
                var a = 30; //设置提示框相对于偏移位置，防止遮挡鼠标
                $(this).mouseover(function(d) { //鼠标移上事件
                    //console.log(d.pageX);
                    this.title = "";
                    $("body").append('<div id="tooltip">' + c + "</div>"); //创建提示框,添加到页面中
                    $("#tooltip").css({
                        left: (d.pageX + a) + "px",
                        top: d.pageY + "px",
                        width:tdwidth+"px"
                    }).show(250) //设置提示框的坐标，并显示
                }).mouseout(function() { //鼠标移出事件
                    this.title = c; //重新设置title
                    $("#tooltip").remove() //移除弹出框
                }).mousemove(function(d) { //跟随鼠标移动事件
                    $("#tooltip").css({
                        left: (d.pageX + a) + "px",
                        top: d.pageY + "px"
                    })
                })
            }
        })
    });
    //左右模块切换
    $('.switch').on('click',function(){
        $('.switched').removeClass('switched');
        $('.selectinforbox').removeClass('selectinforbox');
        $(this).addClass('switched').next('.inforbox').addClass('selectinforbox');
    });
    //table宽度
    var tabwidth=$('.partlist').width();
    $('.inforbox').css('width',tabwidth);
    //退款原因
    var tdwidth=$('.refundlist').width();
    //console.log(tdwidth);
    tdwidth=tdwidth*0.15;
    $('.reasontd').css('width',tdwidth).parent('td').css('width',tdwidth);
    //弹窗
    $(document).on('click','.alertBtn,.closeicon,.closepop',function(){
    	$('.alertpop').remove();
    });
});
//弹窗
function alertLayel(e){
	var html='<div class="alertpop" style="display: block;position: fixed;width: 100%;height:100%;top:0;left:0;z-index: 9999;"><div class="popbg closepop" style="    width: 100%;height:100%;position: absolute;top:0;left:0;z-index: 989;background: #000;opacity: 0.5;"></div><div class="alertLayel" style="width:400px;height:200px;padding-top:10px;background:#fff;position:absolute;margin:auto;top:0;left:0;right:0;bottom:0;z-index:9999;"><h3 style="height:30px;position:relative;"><i class="closeicon" style="margin-right:12px;margin-top:0;"></i> </h3><p class="alertContent" style="line-height:30px;font-size:14px;text-align:center;height:100px;">'+e+'</p><div class="row tc" style="margin:0;"><input type="button" value="确定" class="alertBtn" style="height:30px;border:none;width:80px;background:#ff6200;color:#fff;"></div></div></div>';
	$("body").append(html);
}
//设备排序
$(function(){
	$(".order_by_up").on("click",function(e){
		    var type=$(this).attr('data-id');
		    var sortName=$(this).attr('data-title');
		    if(parseInt(sortName)==1){
		    	window.location.href='/smart/onlinelist.html?sortName=date&type=2';		    		    	
		    }else{
		    	window.location.href='/smart/onlinelist.html?sortName=version&type=2';
		    }
	});
	$(".order_by_down").on("click",function(e){
		var type=$(this).attr('data-id');
	    var sortName=$(this).attr('data-title');
	    if(parseInt(sortName)==1){
	    	window.location.href='/smart/onlinelist.html?sortName=date&type=1';		    		    	
	    }else{
	    	window.location.href='/smart/onlinelist.html?sortName=version&type=1';
	    }
});
	
})