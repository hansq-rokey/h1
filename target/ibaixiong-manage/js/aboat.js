/**
 * Created by Administrator on 2015/9/2.
 */
$(document).ready(function(){
    $('.leftnavlist li').on('click',function(){
        $('.selectli').removeClass('selectli');
        $(this).addClass('selectli');
        $('.currentbox').removeClass('currentbox');
        $('.rightbox').eq($(this).index()).addClass('currentbox');
    })
});