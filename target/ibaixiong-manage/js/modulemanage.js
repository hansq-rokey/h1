/**
 * Created by junfei on 2015/8/28.
 */
$(document).ready(function(){
    var width=$('.partlist').width();
    console.log(width);
    $('.inforbox').css('width',width);
    $(document).on('click','.moduletr',function(e){
        $('.selectedtr').removeClass('selectedtr');
        $(this).addClass('selectedtr');
        e.stopPropagation();
    });
    //选中菜单
    $(document).on('click','.moduletr',function(e){
        $('.selectedtr').removeClass('selectedtr');
        $(this).addClass('selectedtr');
        e.stopPropagation();
    });
    icon();
    function icon(){
        $('.moduletr').each(function(){
            if($(this).next('tr').hasClass('secondtr')||$(this).next('tr').hasClass('thirdtr')){
                $(this).find('i').addClass('showicon').removeClass('hideicon');
            }else{
                $(this).find('i').addClass('hideicon').removeClass('showicon');
            };
        });
    }
    //tdwidth();
    //function tdwidth(){
    //    var width=$('.modulelist').width();
    //    $('.modulelist').find('td').css('width',width/6);
    //}
    $(document).on('click','.showicon',function(){
        if($(this).parent().parent().next('tr').hasClass('secondtr')){
            $(this).parent().parent().next('.secondtr').toggle();
        }
        if($(this).parent().parent().next('tr').hasClass('thirdtr')){
            $(this).parent().parent().next('.thirdtr').toggle();
        }

    });
    
});