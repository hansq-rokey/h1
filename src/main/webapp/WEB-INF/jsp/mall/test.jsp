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
		<form action="/mall/base/model/save.html" method="post">
            <div class="row">
                <span class="addprodinforname">商品名称:</span>
                <input type="text" value="${model.name }" name="name" class="installcost">
            </div>
            <div class="row">
            	<c:if test="${model==null }">
	        		<span class="addprodinforname">产品品类:</span>
		            <span class="selectinput">
		                <span class="selectvalue">&nbsp;</span>
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
                <input type="text" value="${model.code }" ${model==null?'':'disabled=\"disabled\"' } name="code" class="installcost" placeholder="型号编码"><p style="color: red;">2位字母+1位数字,异步判断ajax，不允许该商品重复</p>
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
                        <input type="text" name="properties[0].propertyName" class="type-name hide" value="智能类型">
                        <span class="type-name">智能类型</span>
                    </td>
                    <td>
                        <input type="checkbox" class="smart-type"  checked="checked" value="智能" name="properties[0].propertiesValuees[0].value"><span>智能</span>
                        <input type="checkbox" class="smart-type" value="非智能" name="properties[0].propertiesValuees[1].value"><span>非智能</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="properties[1].propertyName" class="type-name hide" value="智能类型">
                        <span class="type-name">智能类型</span>
                    </td>
                    <td>
                        <div class="type-value-box">
                            <input type="text" class="type-value hide" name="properties[1].propertiesValuees[0].value">
                            <span class="type-value"></span>
                            <img src="/images/type-add.png" class="type-img">
                            <img src="/images/delete-img.png" class="type-delete">
                            <img src="/images/update-img.png" class="type-update">
                            <input type="hidden" class="img-hidden"  name="properties[1].propertiesValuees[0].imageUrl">
                            <input type="file" class="file type-file hide">
                        </div>
                        <div class="type-value-box add-type-box hide">
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
                <input type="button" value="编辑" class="edit-model">
                <input type="button" value="取消" class="cancel-model">
            </div>
            <input name="categoryId" type="hidden" id="categoryId" />
        </form>
            <table class="catgetable">
                <thead>
                <tr>
                    <td width="100">序号</td>
                    <c:forEach items="${properties }" var="propertyItem">
                    	<td width="250">${propertyItem.propertyName }</td>
                    </c:forEach>
                    <td>商品编码</td>
                </tr>
                </thead>
                <tbody>
					<c:forEach items="${formats }" var="formatItem">
                		<tr>
							<td class="serial">${formatItem.code }</td>
								<c:forEach items="${formatItem.propertiesValues }" var="valueItem">
									<td class="serial">${valueItem.value }</td>
								</c:forEach>
		                    <td>${formatItem.categoryModelFormatNumber }</td>
	                    </tr>
					</c:forEach>                
                </tbody>
            </table>
        </div>
    </section>
</body>
<script>
    $(document).ready(function(){
    	$(document).on('click','.selectinput li',function(e){
    	    var id=$(this).attr("data-id");
    	   	$("#categoryId").val(id);
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
        //保存
        $(document).on('click','.save-model',function(){
            //获取数据
            var data=[];
            var row={};
            var tr=$('.model-list').find('tbody').find('tr');
            var num=$(tr).length;
            for(i=1;i<num;i++){
                var name=$(tr).eq(i).find('td:first-child').find('.type-name').eq(0).val();
                var type_box=$(tr).eq(i).find('.type-value-box');
                var values=[];
                for(j=0;j<$(type_box).length;j++){
                    var value={};
                    value.val=$(type_box).eq(j).find('.type-value').eq(0).val();
                    value.url=$(type_box).eq(j).find('.type-img').attr('src');
                    values.push(value);
                }
                row.name=name;
                row.val=values;
                data.push(row);
            }
            console.log(data);
            var length=data.length;
            var html='';
            var html_body='';
            var value_span='';
            var type_name='';
            var html_head='';
            for(i=0;i<length;i++){
                var check=$('.smart-type:checked');
                var head_name=data[i].name;
                type_name+='<td>'+head_name+'</td>';
                html_head='<tr> <td>序号</td> <td>缩略图</td>'+type_name+'<td>商品编码</td> <td>操作</td> </tr>';
                if(check){
                    var type=$(this).next('span').text();
                    if(i=0){
                        for(j=0;j<data[0].val.length;j++){
                            value_span+='<td>'+type+'</td><td>'+data[0].val[j]+'</td>';
                            html_body+='<tr><td class="serial"></td><td><div class="uploadbox"> <input type="file" class="file uploadimgbtn" value="上传"> <img src="/images/userimg.png" class="uploadimg"> <div class="barrier">重新上传</div> </div><td>'+value_span+'</td><td>商品编码</td></td><td><a href="#" class="forbidden">禁用</a><a href="#" class="activate">激活</a> </td></tr>';

                        }
                    }
                    if(i=1){
                        for(j=0;j<data[0].val.length;j++){
                            for(k=0;k<data[1].val.length;k++) {
                                value_span='';
                                html_body='';
                                value_span += '<td>' + type + '</td><td>' + data[0].val[j] + '</td><td>' + data[1].val[k] + '</td>';
                                html_body+='<tr><td class="serial"></td><td><div class="uploadbox"> <input type="file" class="file uploadimgbtn" value="上传"> <img src="/images/userimg.png" class="uploadimg"> <div class="barrier">重新上传</div> </div></td><td>'+value_span+'</td><td>商品编码</td></td><td><a href="#" class="forbidden">禁用</a><a href="#" class="activate">激活</a> </td></tr>';
                            }
                        }

                    }
                    if(i=2){
                        for(j=0;j<data[0].val.length;j++){
                            for(k=0;k<data[1].val.length;k++) {
                                for(L=0;L<data[2].val.length;L++) {
                                    value_span = '';
                                    html_body='';
                                    value_span += '<td>' + type + '</td><td>' + data[0].val[j] + '</td><td>' + data[1].val[k] + '</td><td>' + data[2].val[L] + '</td>';
                                    html_body+='<tr><td class="serial"></td><td><div class="uploadbox"> <input type="file" class="file uploadimgbtn" value="上传"> <img src="/images/userimg.png" class="uploadimg"> <div class="barrier">重新上传</div> </div><td>'+value_span+'</td>'+value_spans+'<td>商品编码</td></td><td><a href="#" class="forbidden">禁用</a><a href="#" class="activate">激活</a> </td></tr>';
                                }
                            }
                        }
                    }
                    if(i=3){
                        for(j=0;j<data[0].val.length;j++){
                            for(k=0;k<data[1].val.length;k++) {
                                for(L=0;L<data[2].val.length;L++) {
                                    for(m=0;L<data[3].val.length;m++) {
                                        value_span = '';
                                        html_body='';
                                        value_span += '<td>' + type + '</td><td>' + data[0].val[j] + '</td><td>' + data[1].val[k] + '</td><td>' + data[2].val[L] + '</td><td>' + data[3].val[m] + '</td>';
                                        html_body+='<tr><td class="serial"></td><td><div class="uploadbox"> <input type="file" class="file uploadimgbtn" value="上传"> <img src="/images/userimg.png" class="uploadimg"> <div class="barrier">重新上传</div> </div><td>'+value_span+'</td>'+value_spans+'<td>商品编码</td></td><td><a href="#" class="forbidden">禁用</a><a href="#" class="activate">激活</a> </td></tr>';
                                    }
                                }
                            }
                        }
                    }
                $('.catgetable').find('thead').html(html_head);
                $('.catgetable').find('tbody').html(html_body);
                }
            }
//            $(spans).each(function(){
//                var text=$(this).prev('input').val();
//                $(this).removeClass('hide').text(text);
//            })
//            $(inputs).addClass('hide');
//            $('.add-type-box').addClass('hide').removeClass('show');
//            var html='';
//            var html_head='';
//            var type_name='';
//            var type_value='';
//            var html_body='';
//            var smart_type=$('.smart-type');
//            var value_span='';
//            for(i=1;i<num;i++){
//                var trs=$(tr).eq(i);
//                var type=trs.find('td:first-child').find('.type-name').eq(0).val();
//                type_name+='<td>'+type+'</td>';
//                html_head='<tr> <td>序号</td> <td>缩略图</td>'+type_name+'<td>商品编码</td> <td>操作</td> </tr>';
//                var check=$('.smart-type:checked');
//                if(check){
//                    $(check).each(function(){
//                        var type=$(this).next('span').text();
//                        console.log(type)
//                        var type_box='';
//                        var x=$(tr).eq(i).find('type-value-box').length;
//                        if(i>0){
//                            type_box=$(tr).eq(1).find('.type-value-box');
//                            for(k=0;k<type_box.length;k++){
//                                var value_spans='';
//                                var value_span_1=type_box.eq(k).find('.type-value').eq(0).val();
//                                value_spans+='<td>'+value_span_1+'</td>';
//                                console.log(value_spans,i)
//                                if(i>1){
//                                    type_box=$(tr).eq(2).find('.type-value-box');
//                                    for(L=0;L<type_box.length;L++){
//                                        value_spans='';
//                                        var value_span_2=type_box.eq(L).find('.type-value').eq(0).val();
//                                        value_spans+='<td>'+value_span_1+'</td><td>'+value_span_2+'</td>';
//                                        console.log(value_spans)
//                                        if(i>2){
//                                            type_box=$(tr).eq(3).find('.type-value-box');
//                                            for(m=0;m<type_box.length;m++) {
//                                                value_spans='';
//                                                var value_span_3 = type_box.eq(m).find('.type-value').eq(0).val();
//                                                value_spans += '<td>'+value_span_1+'</td><td>'+value_span_2+'</td><td>' + value_span_3 + '</td>';
//                                                if(i>3){
//                                                    type_box=$(tr).eq(4).find('.type-value-box');
//                                                    for(n=0;n<type_box.length;n++) {
//                                                        value_spans='';
//                                                        var value_span_4 = type_box.eq(n).find('.type-value').eq(0).val();
//                                                        value_spans += '<td>'+value_span_1+'</td><td>'+value_span_2+'</td><td>' + value_span_3 + '</td><td>' + value_span_4 + '</td>';
//                                                        if(i>4){
//                                                            type_box=$(tr).eq(5).find('.type-value-box');
//                                                            for(o=0;o<type_box.length;o++) {
//                                                                value_spans='';
//                                                                var value_span_5 = type_box.eq(o).find('.type-value').eq(0).val();
//                                                                value_spans += '<td>'+value_span_1+'</td><td>'+value_span_2+'</td><td>' + value_span_3 + '</td><td>' + value_span_4 + '</td><td>' + value_span_5 + '</td>';
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                                type_value+='<tr><td class="serial"></td><td><div class="uploadbox"> <input type="file" class="file uploadimgbtn" value="上传"> <img src="../images/userimg.png" class="uploadimg"> <div class="barrier">重新上传</div> </div><td>'+type+'</td>'+value_spans+'<td>商品编码</td></td><td><a href="#" class="forbidden">禁用</a><a href="#" class="activate">激活</a> </td></tr>';
//                            }
//                        }
//
//                    })
//                }
//                if(i>0){
//
//                }
//                $('.catgetable').find('thead').html(html_head)
//                $('.catgetable').find('tbody').html(type_value)
//            }
            var obj={};

        })
    })
</script>
</html>