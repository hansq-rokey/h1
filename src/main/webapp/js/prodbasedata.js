/**
 * Created by Administrator on 2015/9/7.
 */
$(document).ready(function(){
    $(document).on('click','.editlink',function(){
        $(this).hide().siblings('.savelink,.cancellink').show().parent('td').siblings('td').find('.hideinput').show();
        $(this).parent().siblings('td').find('.hideinput').each(function(){
            $(this).prev('.hidespan').hide();
            if($(this).hasClass('selectinput')){
                $(this).css('display','block').find('.selectvalue').html($(this).prev('.hidespan').html());
            }else{
                $(this).val($(this).prev('.hidespan').html());
            }
        });
        $(this).parent().siblings('td').find('.uploadimgbox').each(function(){
            $(this).addClass('curuploadimgbox');
        })
    });
    $(document).on('click','.savelink',function(){
    	var _data={};
    	var emptyCheck=false;
    	$(this).parent().siblings('td').find('.hidespan').each(function(index){
    		var _input=$(this).next('.hideinput');
    		var value=_input.val();
        	if(value==''){
        		alertLayel('内容不能为空!');
        		emptyCheck=true;
        		return false;
        	}
        	if(_input.attr('name')=='code'){
        		value=value.toUpperCase();//转化大写
        		_input.val(value);
        		if(value.length!=2){
        			alertLayel('类目代码长度不对！');
            		emptyCheck=true;
            		return false;
            	}
        		reg=/^[a-zA-Z]{2}$/g;
        		if(!reg.test(value)){
        			alertLayel('类目代码只能为字母！');
            		emptyCheck=true;
            		return false;
        		}
        	}
    	});
    	var L=$('.categoryCode').val().length;
    	console.log(L,11);
    	if(L>2){
    		alertLayel('类目代码长度不对！');
    		return false;
    	}
		console.log(emptyCheck)
    	if(emptyCheck==true){
        	return false;
        }
        $(this).parent().siblings('td').find('.hidespan').each(function(index){
            if(index==0){
            	var _id=$(this).attr("data-id");
            	_data.id=_id;
            }
            if($(this).next('.hideinput').hasClass('selectinput')){
                $(this).html($(this).next('.hideinput').find('.selectvalue').html()).show();
            }else{
            	var _input=$(this).next('.hideinput');
            	var key=_input.attr('name');
            	var value=_input.val();
        		_data[key]=value;
                $(this).text(value).show();
                $(this).next('.hideinput').hide();
            }
        });
        $(this).hide().prev('.editlink').show().next('.cancellink').hide();
        $(this).next('.cancellink').hide();
//        console.dir(_data);
        //数据提交
        $.addCategoryFn(_data,function(data){ });
        $(this).parent().siblings('td').find('.uploadimgbox').each(function(){
            $(this).removeClass('curuploadimgbox');
        })
    });
    $(document).on('click','.cancellink',function(){
        $(this).hide().siblings('.editlink').show();
        $(this).prev('.savelink').hide();
        $(this).parent().siblings('td').find('.hidespan').each(function(){
            $(this).show().next('.hideinput').hide();
        });
    });
    //新增类目
    $('.addcatge').on('click',function(){
        var html='<tr> <td><span class="hidespan" data-id=""></span><input type="text" name="name" class="hideinput on" value=""> </td> <td><span class="hidespan"></span><input type="text" name="code" class="hideinput on" value=""> </td> <td><a href="#" style="display:none" class="link editlink">编辑</a><a href="#" class="link savelink on">保存</a><a href="#" class="link cancellink on">取消</a> </td> </tr>';
        $('.prodcateglist tbody tr:last-child').after(html);
    });
    //$('.addcatge').on('click',function(){
    //    $(".prodcateglist").jqprint();
    //})
    //新增智能连接
    $('.addlink').on('click',function(){
        var html='<tr> <td> <span class="hidespan"></span> <span class="selectinput hideinput on"> <span class="selectvalue">奇思妙想</span> <i class="arrow arrowright"></i> <ul class="option"> <li>上发生地</li> <li>发的发鬼</li> <li>个地方官</li> </ul> </span> </td> <td><span class="hidespan"></span><input type="text" class="hideinput on" value=""></td> <td><div class="uploadimgbox"> <div class="uploadbg"></div> <p class="uploadword">上传图片</p> <img src="#" class="prodattrimg"> <input type="file" class="file"> </div> </td> <td> <div class="uploadimgbox"> <div class="uploadbg"></div> <p class="uploadword">上传图片</p> <img src="#" class="prodattrimg"> <input type="file" class="file"> </div> </td> <td> <div class="uploadimgbox"> <div class="uploadbg"></div> <p class="uploadword">上传图片</p> <img src="#" class="prodattrimg"> <input type="file" class="file"> </div> </td> <td> <div class="uploadimgbox"> <div class="uploadbg"></div> <p class="uploadword">上传图片</p> <img src="#" class="prodattrimg"> <input type="file" class="file"> </div> </td> <td><a href="#" class="link editlink">编辑</a><a href="#" class="link savelink on">保存</a><a href="#" class="link cancellink on">取消</a> </td> </tr>';
        $('.prdlinklist tbody tr:last-child').after(html);
//        console.log(html)
    });
    //$('.uploadimgbox').mousemove(function(){
    //    $(this).addClass('curuploadimgbox')
    //}).mouseout(function(){
    //    $(this).re('curuploadimgbox')
    //})
    //类型详情
//    function num(){
//        $('.serial').each(function(){
//            $(this).html($(this).index()+1);
//        })
//    }
//    num();
    $('.addprodtype').on('click',function(){
    	var _serial=$('.serial');
        var n=$('.serial').length;
        var index=n;
        n+=1;
//        console.log(n)
        var html;
        if($(this).hasClass('edit')){
        	return ;
        	html=['<tr>',
                   	'<td class="serial">'+n+'</td>',
                   	'<td> <span class="hidespan"></span><input type="text" name="formats['+index+'].name" class="perprice hideinput on " value=""> </td>',
                   	'<td><span class="hidespan"></span><input type="text" name="formats['+index+'].walt" class="actprice hideinput on" value=""> </td>',
                   	'<td> <input type="radio" name="formats['+index+'].isSmart" class="putaway" value="1" checked="checked">是 <input type="radio" name="formats['+index+'].isSmart" class="putaway" value="0">否 </td>',
                   	'<td></td>',
                   	'<td>',
                   		'<a href="#" class="link editlink">编辑</a>',
                   		'<a href="#" class="link savelink on">保存</a>',
                   		'<a href="#" class="link cancellink on">取消</a>',
                   		'<a href="#" class="link deletelink">删除</a>',
                   	'</td>',
                   '</tr>'].join('');
        }else{
        	html=['<tr>',
        	          '<td class="serial">'+n+'</td>',
        	          '<td> <span class="hidespan"></span><input type="text" name="formats['+index+'].name" class="perprice hideinput on " value=""> </td>',
        	          '<td><span class="hidespan"></span><input type="text" name="formats['+index+'].walt" class="actprice hideinput on" value=""> </td>',
        	          '<td> <input type="radio" name="formats['+index+'].isSmart" class="putaway" value="1" checked="checked">是 <input type="radio" name="formats['+index+'].isSmart" class="putaway" value="0">否 </td>',
        	          '</tr>'].join('');
        }
        if($('.catgetable').find('tbody').find('tr').length>0){
        	$('.catgetable tbody tr:last-child').after(html);
        }else{
        	$('.catgetable tbody').append(html);
        }
    });
});
$.extend({
	'addCategoryFn': function(data, fn){
//		console.log(data);
		$.ajax({
	 		   url: "/mall/base/data/save.html",
	 		   data: data,//参数
	 		   type: "POST",
	 		   dataType:"json",
	 		   cache:false,
	 		   success: function(obj){
//		  console.log(obj);
	 			  if ( obj.code!=1 ) {
	 				 alertLayel(obj.message);
	 				 return;
	 			  }else{
	 			  	//异步处理成功后回调TODO
	 			  }
	 		   }
	 	});
	}
});
