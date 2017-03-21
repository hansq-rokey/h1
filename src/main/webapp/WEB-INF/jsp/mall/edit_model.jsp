<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<form action="/mall/base/model/update.html" method="post" onsubmit="return checkEmpty();">
            <div class="row">
                <span class="addprodinforname">商品名称:</span>
                <input type="text" value="${model.name }" name="name" class="installcost">
                <input type="hidden" value="${model.id }" name="id"/>
            </div>
            <div class="row">
            	<c:if test="${model==null }">
	        		<span class="addprodinforname">产品品类:</span>
		            <span class="selectinput">
		                <span class="selectvalue category-name">&nbsp;</span>
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
		                <span class="selectvalue category-name">${model.category.name }</span>
		                <i class="arrow arrowright"></i>
		            </span>
	        	</c:if>
            </div>
            <div class="row">
                <span class="addprodinforname">商品型号:</span>
                <input type="text" value="${model.code }" ${model==null?'':'disabled=\"disabled\"' } name="code" class="installcost" placeholder="型号编码">
            </div>
            <table class="model-list">
                <thead>
                <tr>
                    <td width="100">规格名称</td>
                    <td>规格列表</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${properties }" var="property" varStatus="propertyStatus">
                	<c:if test="${propertyStatus.index==0 }">
                		<tr>
		                    <td>
		                        <input type="text" name="properties[0].propertyName" class="type-name" value="${property.propertyName }">
		                        <input type="hidden" value="${property.id }" name="properties[0].id" />
		                        <span class="type-name hide">${property.propertyName }</span>
		                    </td>
		                    <td>
		                    	<c:set var="smart" value="0"/>
		                    	<c:set var="noSmart" value="0"/>
		                    	<c:forEach items="${property.propertiesValues }" var="propertyValue" varStatus="propertyValueStatus">
		                    		<c:if test="${propertyValue.value=='智能' }">
		                    			<c:set var="smart" value="1"/>
		                    		</c:if>
		                    		<c:if test="${propertyValue.value=='非智能' }">
		                    			<c:set var="noSmart" value="1"/>
		                    		</c:if>
		                    		<input type="hidden" value="${propertyValue.value}" name="properties[0].propertiesValuees[${propertyValueStatus.index }].value" />
		                    		<input type="hidden" value="${propertyValue.id}" name="properties[0].propertiesValuees[${propertyValueStatus.index}].id" />
		                    	</c:forEach>
		                        <input type="checkbox" class="smart-type"  ${smart==1?'checked=\"checked\" disabled="disabled"':'' } value="智能" name="properties[0].propertiesValuees[0].value"><span>智能</span>
		                        <input type="checkbox" class="smart-type" ${noSmart==1?'checked=\"checked\" disabled="disabled"':'' } value="非智能" name="properties[0].propertiesValuees[1].value"><span>非智能</span>
		                    </td>
		                </tr>
                	</c:if>
                	<c:if test="${propertyStatus.index>0 }">
                		<tr>
		                    <td>
		                        <input type="text" name="properties[${propertyStatus.index }].propertyName" class="type-name" value="${property.propertyName }">
		                        <input type="hidden" value="${property.id }" name="properties[${propertyStatus.index }].id" />
		                        <span class="type-name hide">${property.propertyName }</span>
		                    </td>
		                    <td>
		                    	<c:forEach items="${property.propertiesValues }" var="propertyValue" varStatus="propertyValueStatus">
		                    		<div class="type-value-box">
			                            <input type="text" class="type-value" value="${propertyValue.value }" name="properties[${propertyStatus.index }].propertiesValuees[${propertyValueStatus.index }].value">
			                            <span class="type-value hide">${propertyValue.value }</span>
			                            <img src="/images/type-add.png" class="type-img">
			                            <!-- <img src="/images/delete-img.png" class="type-delete"> -->
			                            <img src="${propertyValue.imageUrl==''?'/images/update-img.png':propertyValue.imageUrl }" class="type-update">
			                            <input type="hidden" class="img-hidden" value="${propertyValue.imageUrl}"  name="properties[${propertyStatus.index }].propertiesValuees[${propertyValueStatus.index }].imageUrl">
			                            <input type="hidden" value="${propertyValue.id}"  name="properties[${propertyStatus.index }].propertiesValuees[${propertyValueStatus.index }].id">
			                            <input type="file" class="file type-file">
			                        </div>
		                    	</c:forEach>
		                        <div class="type-value-box add-type-box">
		                            <img src="/images/type-add.png" class="type-add" data-id="${propertyStatus.index }">
		                        </div>
		                    </td>
		                </tr>
                	</c:if>
                </c:forEach>
                </tbody>
<!--                 <tfoot>
                <tr>
                    <td colspan="2"><i class="showicon addprodtype"></i></td>
                </tr>
                </tfoot> -->
            </table>
            <div class="row">
                <input type="submit" value="保存" class="save-model">
                <!-- <input type="button" value="编辑" class="edit-model"> -->
                <!-- <input type="button" value="取消" class="cancel-model"> -->
            </div>
        </form>
            <table class="catgetable">
                <thead>
                <tr>
                    <td width="100">序号</td>
                    <td width="100">缩略图</td>
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
							<td>
							    <div class="uploadbox">
							        <%-- <input type="file" data-id="${formatItem.id }" class="uploadimgbtn file" value="上传"> --%>
							        <c:if test="${fn:length(formatItem.pics)==0}">
							        	<img src=""  class="uploadimg">	
							        </c:if>
							        <c:forEach items="${formatItem.pics }" var="pic">
								        <img src="${pic.url }"  class="uploadimg">							        
							        </c:forEach>
							        <!-- <div class="barrier">重新上传</div> -->
							    </div>
							</td>
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
        });
        //删除属性
        $(document).on('click','.type-delete',function(){
        	$(this).parent('.type-value-box').remove();
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
        $(document).on('change','.type-file,.uploadimgbtn',function(){
        		var typebtn=$(this);
                  var imgPath = $(this).val();
                  //判断是否有选择上传文件
                  if (imgPath == "") {
                	  alertLayel("请选择上传图片！");
                      return;
                  }
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
                    	  if($(typebtn).hasClass('type-file')){
                    		  $(typebtn).parent().find('.type-update').attr('src',data.result.url).attr('data-value',data.result.url);
                              $(typebtn).parent().find('.img-hidden').val(data.result.url);
                    	  }
                          if($(typebtn).hasClass('uploadimgbtn')){
                        	  $(typebtn).next().attr('src',data.result.url);
                          }
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
        	if($('.smart-type[checked="checked"]').length==0){
        		alertLayel("智能类型最少选择一个!");
        		return false;
        	}
/*         	var imgBreak = false;
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