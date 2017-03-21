/**
 * Created by junfei on 2015/8/26.
 */
$(document).ready(function(){
    var width=$('.partlist').width();
    //console.log(width);
    $('.inforbox').css('width',width);
    //增加部门
    $('.addpart').on('click',function(){
        var html='<tr> <td><input type="text" class="departname" value=""> </td> <td><a href="#" class="delete">删除</a> </td> </tr>';
        console.log(html)
        $('.departlist tbody tr:last-child').after(html)
    });
    $(document).on('click','.delete',function(){
        $(this).parent().parent('tr').remove();
    });
    $('.switch').on('click',function(){
        $('.switched').removeClass('switched');
        $('.selectinforbox').removeClass('selectinforbox');
        $(this).addClass('switched').next('.inforbox').addClass('selectinforbox');
    });
    //选中菜单
    $(document).on('click','.menutr',function(e){
        $('.selectedtr').removeClass('selectedtr');
        $(this).addClass('selectedtr');
        e.stopPropagation();
    });
    icon();
    function icon(){
        $('.menutr').each(function(){
            if($(this).next('tr').hasClass('secondtr')){
                $(this).find('i').addClass('showicon').removeClass('hideicon');
            }else{
                $(this).find('i').addClass('hideicon').removeClass('showicon');
            };
        });
    }
    $(document).on('click','.showicon',function(){
        $(this).parent().parent().next('.secondtr').toggle();
    })
});