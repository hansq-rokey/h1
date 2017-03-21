<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/catgedetail.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/prodbasedata.js" type="text/javascript" ></script>
    <title>基础数据</title>
</head>
<body>
    <section>
        <div class="pathbox">
            <span class="light">产品型号</span>><span class="darker">型号详情</span>
        </div>
        <div class="inforbox selectinforbox">
		<form action="/mall/base/model/save.html" method="post" onsubmit="return checkEmpty();">
            <div class="row">
                <span class="addprodinforname">商品名称:</span>
                <input type="text" value="${model.name }" name="name" class="installcost prod-name">
            </div>
            <div class="row">
            	<c:if test="${model==null }">
	        		<span class="addprodinforname">产品品类:</span>
		            <span class="selectinput">
		                <span class="selectvalue category-name"></span>
		                <i class="arrow arrowright"></i>
		                <ul class="option">
		                	<c:forEach items="${categoryList }" var="category">
			                    <li data-id="${category.id }">${category.name }</li>		                	
		                	</c:forEach>
		                </ul>
		            </span>
	        	</c:if>
	        	<c:if test="${model!=null }">
	        		<span class="addprodinforname">产品品类:</span>
	        		<span class="selectinput">
		                <span class="selectvalue">${model.category.name }</span>
		                <i class="arrow arrowright"></i>
		            </span>
	        	</c:if>
            </div>
            <div class="row">
                <span class="addprodinforname">商品型号:</span>
                <input type="text" value="${model.code }" ${model==null?'':'disabled=\"disabled\"' }  name="code" id="code" class="installcost prod-model" placeholder="型号编码">
            </div>
            <table class="model-list">
                <thead>
                <tr>
                    <td width="100">规格名称</td>
                    <td>规格列表</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <input type="text" name="properties[0].propertyName" class="type-name" value="智能类型">
                        <span class="type-name hide">智能类型</span>
                    </td>
                    <td>
                        <input type="checkbox" class="smart-type"  checked="checked" isCheck="true" value="智能" name="properties[0].propertiesValuees[0].value"><span>智能</span>
                        <input type="checkbox" class="smart-type" value="非智能" name="properties[0].propertiesValuees[1].value"><span>非智能</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="properties[1].propertyName" class="type-name" value="">
                        <span class="type-name hide"></span>
                    </td>
                    <td>
                        <div class="type-value-box">
                            <input type="text" class="type-value" name="properties[1].propertiesValuees[0].value">
                            <span class="type-value hide"></span>
                            <img src="/images/type-add.png" class="type-img">
                            <img src="/images/delete-img.png" class="type-delete">
                            <img src="/images/update-img.png" class="type-update">
                            <input type="hidden" class="img-hidden"  name="properties[1].propertiesValuees[0].imageUrl">
                            <input type="file" class="file type-file">
                        </div>
                        <div class="type-value-box add-type-box">
                            <img src="/images/type-add.png" class="type-add" data-id="1">
                        </div>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="2"><i class="showicon addprodtype"></i></td>
                </tr>
                </tfoot>
            </table>
            <div class="row">
                <input type="submit" value="保存" class="save-model">
                <input type="button" value="编辑" class="edit-model" style="display: none;">
                <input type="button" value="取消" class="cancel-model">
            </div>
            <input name="categoryId" type="hidden" id="categoryId" />
        </form>
        </div>
    </section>
</body>
<script>
    $(document).ready(function(){
    	$(document).on('click','.cancel-model',function(e){
    	    window.location.href="/mall/base/data.html";
    	});
    	$(document).on('click','.selectinput li',function(e){
    	    var id=$(this).attr("data-id");
    	   	$("#categoryId").val(id);
    	});
    	//校验商品型号
        $(document).on('blur','#code',function(){
        	var categoryId=$("#categoryId").val();
        	var code=$(this).val();
        	reg=/^[a-zA-Z0-9]{3}$/g;
        	if(!reg.test(code)){
        		alertLayel('格式不正确');
        		$("#code").val('');
        		return;
        	}else{
        		$(this).val(code.toUpperCase());
        	}
        	$.ajax({
        		url:"/mall/model/check.html",
        		type:"POST",
        		data:{categoryId:categoryId,code:code},
        		dataType:"json",
        		success:function(data){
        			if(data.code==1){
        				if(data.result.success==1){
        					alertLayel('该商品型号已存在，请重新填写！');        					
        				}
        			}else{
        				alertLayel('校验失败！');
        			}
        		}
        	});
        });
        $(document).on('click','.type-add',function(){
        	var index=$(this).attr('data-id');
        	var num=$(this).parent().parent('td').find('.type-value-box').length-1;
        	console.log(num);
            var html='<div class="type-value-box"> <input type="text" name="properties['+index+'].propertiesValuees['+num+'].value" class="type-value"><span class="type-value hide"></span> <img src="/images/type-add.png" class="type-img"> <img src="/images/delete-img.png" class="type-delete"> <img src="/images/update-img.png" class="type-update"><input type="hidden" class="img-hidden"  name="properties['+index+'].propertiesValuees['+num+'].imageUrl"> <input type="file" class="file type-file"> </div>';
            $(this).parent('.add-type-box').before(html);
        })
        //添加规格行
        $(document).on('click','.addprodtype',function(){
        	var index=$('.model-list tbody').find('tr').length;
            var html='<tr class="value-tr"> <td> <input type="text"  name="properties['+index+'].propertyName" class="type-name"> <span class="type-name hide">智能类型</span> </td> <td> <div class="type-value-box add-type-box show"> <img src="/images/type-add.png" class="type-add" data-id="'+index+'"> </div> </td> </tr>';
            if($('.model-list').find('tbody').find('tr')){
                $('.model-list tbody tr:last-child').after(html);
            }
        });
        
       //删除属性
       $(document).on('click','.type-delete',function(){
    	   $(this).parent('.type-value-box').remove();
       })
        //图片上传
        $(document).on('change','.type-file',function(){
        			var typebtn=$(this);
                  var imgPath = $(this).val();
                  //判断是否有选择上传文件
                  if (imgPath == "") {
                	  alertLayel("请选择上传图片！");
                      return;
                  }
                  console.log(33)
                  //判断上传文件的后缀名
                  var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
                  if (strExtension != 'jpg' && strExtension != 'gif'
                          && strExtension != 'png' && strExtension != 'bmp') {
                	  alertLayel("请选择图片文件");
                      return;
                  }
                  //创建FormData对象
                  var data = new FormData();
                  //为FormData对象添加数据
                  //
                  $.each($(this)[0].files, function(i, file) {
                   	data.append('file', file);
                  });
                  $.ajax({
                      type: "POST",
                      url: "/mall/upload.html",
                      data: data,
                      cache: false,
                      contentType: false,    //不可缺
                      processData: false,    //不可缺
                      dataType:"json",
                      success: function(data) {
                          $(typebtn).parent().find('.type-update').attr('src',data.result.url).attr('data-value',data.result.url);
                          $(typebtn).parent().find('.img-hidden').val(data.result.url);
                          //$(this).next('.uploadimg').attr('src',data);
                      },
                      error: function(XMLHttpRequest, textStatus, errorThrown) {
                    	  alertLayel("上传失败，请检查网络后重试");
                      }
                  });
        });
        
        //编辑
        var spans=$('span.type-value,span.type-name');
        var inputs=$('input.type-value,input.type-name,input.type-file');
        $(document).on('click','.edit-model',function(){
            $(inputs).each(function(){
                var text=$(this).next('span').text();
                $(this).removeClass('hide').val(text);
            })
            //$(inputs).addClass('show');
            $('.add-type-box').addClass('show');
            $(spans).addClass('hide');
        })
	    $('input[type=checkbox]').on('click',function(){
			$(this).each(function(){
				if($(this).attr('isCheck')=='true'){
					$(this).removeAttr("isCheck")
				}else{
					$(this).attr('isCheck',true);
				}
			})
		})
    })
    
    //保存前校验
        function checkEmpty(){
        	if($('.prod-name').val()==''){
        		alertLayel("商品名称不能为空！");
        		return false;
        	}
        	if($('.category-name').text()==''){
        		alertLayel('商品品类不能为空！');
        		return false;
        	}
        	if($('.prod-model').val()==''){
        		alertLayel('商品型号不能为空！');
        		return false;
        	}
        	var typeChecked=false;
    		$('.smart-type').each(function(){
    			if($(this).attr('isCheck')=='true'){
    				typeChecked=true;
    				return;
    			}
    		})
        	if(typeChecked==false){
        		alertLayel("智能类型最少选择一个!");
        		return false;
        	}
/*     		var imgBreak = false;
        	$('.type-update').each(function(){
        		if($(this).attr('src')=='/images/update-img.png'||$(this).attr('src')==''){
        			imgBreak=true;
        			alertLayel('规格图片不能为空!');
        			return false;
        		}
        	});
        	if(imgBreak){
        		return false;
        	} */
        	var valueBreak = false;
        	$('input.type-value').each(function(){
        		console.log($(this).val());
        		if($(this).val()==''){
        			valueBreak=true;
        			alertLayel('规格名称不能为空!');
        			return false;
        		}
        	});
        	if(valueBreak){
        		return false;
        	}
        }
</script>
</html>