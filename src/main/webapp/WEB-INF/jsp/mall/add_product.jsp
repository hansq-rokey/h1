<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/addprod.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/addprod.js" type="text/javascript" ></script>
    <title>添加产品</title>
</head>
<body>
    <section>
        <div class="pathbox">
            <span class="light">商品管理</span>><span class="darker">新增商品</span><div style="color: red;">${msg }</div>
        </div>
        <div class="addprodlist">
        <form action="/mall/product/save.html" method="post" enctype="multipart/form-data" onsubmit="return checkEmpty();">
            <div class="row">
                <span class="addprodinforname">产品型号</span>
                <span class="selectinput">
                    <span class="selectvalue">型号代码+产品名称</span>
                    <i class="arrow arrowright"></i>
                    <ul class="option">
                    	<c:forEach items="${modelList }" var="model">
	                        <li data-id="${model.id }">${model.name }+${model.code }</li>                    	
                    	</c:forEach>
                    </ul>
                </span>
            </div>
            <!-- div class="row">
                <span class="addprodinforname">安装费</span>
                <input type="text" name="installationCost" class="installcost">
                <span>元</span>
            </div> -->
            <div class="row">
            	<span class="addprodinforname">产品特点</span>
                <input type="text" name="feature" class="installcost">
            </div>
            <div class="row">
            	<span class="addprodinforname">WEB链接</span>
                <input type="text" name="detailUrl" class="installcost">
            </div>
            <div class="row">
            	<span class="addprodinforname">APP链接</span>
                <input type="text" name="detailUrlApp" class="installcost">
            </div>
            <div class="row">
                <span class="addprodinforname">App缩略图</span>
                <div class="upbtnbox">
                    <input type="file" name="file" class="file">
                    <input type="button" class="uploadbtn" value="选择图片">
                </div>
                <div class="box">
                	<span>选择图片</span>
               		<img src="" class="thumbnail">
                </div>
            </div>
            <div class="row">
                <span class="addprodinforname">是否上架</span>
                <input type="radio" name="status" class="putaway" value="1" checked="checked">是
                <input type="radio" name="status" class="putaway" value="2" >否
            </div>
            <div class="row">
                <span class="addprodinforname">优惠活动</span>
                <input type="radio" name="IsSpecialOffers" class="favactive" value="1">是
                <input type="radio" name="IsSpecialOffers" class="favactive" value="0"  checked="checked">否
            </div>
            <!-- 添加优惠码相关基础数据  start-->
            <div class="row">
            	<span class="addprodinforname">优惠券金额</span>
                <input type="text" name="disMoney" class="installcost">
            </div>
            <div class="row">
            	<span class="addprodinforname">分销返利</span>
                <input type="text" name="profitMoney" class="installcost">
            </div>
            <div class="row">
            	<span class="addprodinforname">有效期天数</span>
                <input type="text" name="timeCount" class="installcost">
            </div>
            <!-- 添加优惠码相关基础数据 stop -->
            <table class="addprodtable">
                <thead>
                <tr id="threadTable">
                    <td>序号</td>
                    <td>缩略图</td>
                    <td>商品编码</td>
                    <td>库存</td>
                    <td>单价</td>
                    <td>优惠价</td>
                    
                </tr>
                </thead>
                <tbody id="formatT">
                </tbody>
            </table>
            <div class="row">
                <input type="submit" class="publishbtn" value="发布">
                <input type="button" class="cancel" value="取消">
            </div>
        <input type="hidden" name="basicCategoryModel.id" id="modelId" value="" />
        <input type="hidden" name="picId" id="picId" />
        </form>
        
        <form id="formatPropertyForm">
				<div class="popProperty">
					<div class="popbg"></div>
					<div class="popContent">
						<div class="propValue">
							<div class="propType" style="color: #333;">
								<p>类型</p>
							</div>
							<div class="propType">
								<p>名称</p>
							</div>
							<div class="propType">
								<p>标识符</p>
							</div>
						</div>
						<div>
							<a class="plusProprety">+</a>
						</div>
						<div class="popButtonArea">
							<a class="popButton prosave">保存</a><a class="popButton procancel">取消</a>
						</div>
					</div>
				</div>
				<input type="hidden" name="formatId" id="formatId" value="" />
				<input type="hidden" name="len" id="len" value="" />
			</form>
			
        </div>
    </section>
<script type="text/javascript">
//保存前校验
function checkEmpty(){
	var perpriceEmpty=false;
	$('.perprice').each(function(){
		if($(this).val()==''){
			alertLayel("价格不能为空！");
			perpriceEmpty=true;
			return false;
		}
	});
	if(perpriceEmpty==true){
		return false;
	}
}
$(document).on('click','.option li',function(e){
    var id=$(this).attr("data-id");
   	$("#modelId").val(id);
   	_data = {"modelId": id,"type":1};
   	//settings.changeFormatsGetPropertiesFn(_data);
   	settings.changeFormatsFn(_data);
});
//规格图片上传
$(document).on('change','.type-file,.uploadimgbtn',function(){
		var typebtn=$(this);
          var imgPath = $(this).val();
          var formatId=$(this).attr("data-id");
          if(formatId==null)formatId=0;
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
              url: "/mall/product/upload.html?type=2&formatId="+formatId,
              data: data,
              cache: false,
              contentType: false,    //不可缺
              processData: false,    //不可缺
              dataType:"json",
              success: function(data) {
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
//APP图片上传
$(document).on('change','.file,.uploadbtn',function(){
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
              url: "/mall/product/upload.html?type=1",
              data: data,
              cache: false,
              contentType: false,    //不可缺
              processData: false,    //不可缺
              dataType:"json",
              success: function(data) {
            	$(".thumbnail").prev().remove();
                $(".thumbnail").attr('src',data.result.url);
                $("#picId").val(data.result.id);
              },
              error: function(XMLHttpRequest, textStatus, errorThrown) {
            	  alertLayel("上传失败，请检查网络后重试");
              }
          });
});
var settings = {// 根据不同ID获取相应地址接口
   		changeFormatsFn: function(data){
   			$.ajax({
   		 		   url: "/mall/base/formats.html",
   		 		   data: data,//参数
   		 		   type: "POST",
   		 		   dataType:"json",
   		 		   cache:false,
   		 		   success: function(obj){
   		 			  if ( obj.code!=0 ) {
   		 				alertLayel(obj.message);
   		 					return;
   		 			  }else{   	
   	   		 			console.dir(obj);	 	
   	   		 	   		settings.changeFormatsGetPropertiesFn(_data);	
   	   		 	    	//settings.changeFormatsFn(_data);	  	
 		 			  	fillData(obj.result.formats);
   		 			  }
   		 		   }
   		 	});
   		},
   		changeFormatsGetPropertiesFn: function(data){
   			$.ajax({
   		 		   url: "/mall/base/properties.html",
   		 		   data: data,//参数
   		 		   type: "POST",
   		 		   dataType:"json",
   		 		   cache:false,
   		 		   success: function(obj){
   		 			  console.dir(obj);
   		 			  if ( obj.code!=0 ) {
   		 				alertLayel(obj.message);
   		 					return;
   		 			  }else{
   		 				  var td='';
   		 			  	$.each(obj.result.properties, function(i, item){
   		 	     			td+='<td>'+item.propertyName+'</td>';
   		 	     		});
   		 			$('#threadTable').html('<td>序号</td><td>缩略图</td><td>商品编码</td><td>预设库存</td><td>单价</td><td>优惠价</td><td>长</td><td>宽</td><td>单位</td><td>扩展</td><td>说明</td>');
   		 			  	$('#threadTable').find('td').eq(1).after(td);
   		 			  }
   		 		   }
   		 	});
   		}
    };
     
    function fillData(data){
    	var arrayObj = new Array();　//创建一个数组
     	$.each(data, function(i, item){
     		var dynamicData='';
     		var values=item.propertiesValues;
     		$.each(values, function(y, valueItem){
     			dynamicData+='<td>'+valueItem.value+'</td>';
     		});
    		var _temp = ['<tr>',
    						'<td class="serial">'+item.code+'</td>',
    					    '<td>',
    					       ' <div class="uploadbox">',
    					            ' <input type="file" data-id="'+item.id+'" class="uploadimgbtn file" value="上传">',
    					            '<img src="/images/userimg.png" class="uploadimg">',
    					            '<div class="barrier">重新上传</div>',
    					         '</div>',
    						'</td>',
    						dynamicData,
    						'<td>'+item.categoryModelFormatNumber+'</td>',
    					    '<td>','<input type="text" name="formstList['+i+'].stock" class="hideinput" value="'+item.stock+'"></td>',
    					    '<td>',
    					    '<input type="text" name="formstList['+i+'].price" class="hideinput perprice" value="">',
    					    '<input type="hidden" name="formstList['+i+'].id" class="hideinput" value="'+item.id+'"></td>',
    					    '<td>','<input type="text" name="formstList['+i+'].discountPrice" class="hideinput" value=""></td>',
    					    '<td>','<input type="text" name="formstList['+i+'].length" class="hideinput" value="'+valueHandle(item.length)+'"></td>',
    					    '<td>','<input type="text" name="formstList['+i+'].width" class="hideinput" value="'+valueHandle(item.width)+'"></td>',
    					    '<td>',''+seleteUnit(item.unit,i)+'</td>',
    					    '<td>','<input type="button" id="addFormatPro" onclick="addFormatProperty('+item.id+');" value="设置" /> <input type="hidden" name="formstList['+i+'].isExtProperties" id="format'+item.id+'" class="hideinput" value="'+valueHandle(item.isExtProperties)+'"></td>',
    					    '<td>','<input type="text" name="formstList['+i+'].explain" class="hideinput" value="'+valueHandle(item.explain)+'"></td>',
    					  '</tr>'].join('');
    		arrayObj.push(_temp);
    	});
     	$("#formatT").html('');
     	console.log(arrayObj)
    	$("#formatT").append(arrayObj.join(''));
    }
    function addFormatProperty(formatId){
    	$('#formatId').val(formatId);
    	initExtValue(formatId);
    }
    function valueHandle(value){
    	if(typeof(value)=='undefined'){
    		return '';
    	}else{
    		return value;
    	}
    }
    function initExtValue(formatId){
    	$.ajax({
            cache: false,
            type: "POST",
            url:"/mall/format/ext/list.html",
            data:{'formatId':formatId},// 你的formid
            dataType: "json", 
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
            	if(data.code==0){
            		alert(data.message);
            	}else{
            		var arr=new Array();
            		if(data.result.result.length>0){
            			$.each(data.result.result,function(i,item){
                			var html=['<div class="propValue"><div class="propType">',
                						'<select name="type-'+(i+1)+'" style="height: 24px;">',
                						'<option value="1" '+selectType(parseInt(item.type),1)+'>输入框</option>',
                						'<option value="2" '+selectType(parseInt(item.type),2)+'>单选</option>',
                						'<option value="3" '+selectType(parseInt(item.type),3)+'>多选</option>',
                						'<option value="4" '+selectType(parseInt(item.type),4)+'>图片</option></select>',
                						'</div>',
                						'<div class="propType"><input name="propertyName-'+(i+1)+'" value="'+item.propertyName+'"></input></div>',
                						'<div class="propType"><input name="identify-'+(i+1)+'" value="'+item.identify+'"></input></div>',
                						'<input type="hidden" name="id-'+(i+1)+'" value="'+item.id+'"></input>',
                						'</div>'
                						].join('');
                			arr.push(html);
                		});
            		}else{
            			var html=['<div class="propValue"><div class="propType">',
          						'<select name="type-1" style="height: 24px;">',
          						'<option value="1">输入框</option>',
          						'<option value="2">单选</option>',
          						'<option value="3">多选</option>',
          						'<option value="4">图片</option></select>',
          						'</div>',
          						'<div class="propType"><input name="propertyName-1" value=""></input></div>',
          						'<div class="propType"><input name="identify-1" value=""></input></div>',
          						'<input type="hidden" name="id-1" value=""></input>',
          						'</div>'
          						].join('');
          				arr.push(html);
            		}
            		var len=$('.propValue').length-1;
        			$('.propValue').each(function(i){
        				if(i==len){
        					$(this).after(arr.join(''));
    	      				$('#len').val(arr.length);
        				}
        			});
            		$('.popProperty').show();
            	}
            }
        });
    }
    function selectType(value,type){
    	if(type==value){
    		return 'selected';
    	}
    }
    function seleteUnit(unit,y){
    	var value=valueHandle(unit);
    	var arr=new Array('件','个','平方米','卷');
    	var html='<select name="formstList['+y+'].unit">';
    	for(var i=0;i<arr.length;i++){
    		if(arr[i]==value){
    			html+='<option value="'+arr[i]+'" selected="selected">'+arr[i]+'</option>';
    			
    		}else{
    			html+='<option value="'+arr[i]+'">'+arr[i]+'</option>';
    		}
    	}
    	html+='</select>';   	
        return html;
    }

    $(document).on('click','.plusProprety',function(){
    	var len=$('.propValue').length;
    	var html=['<div class="propValue"><div class="propType">',
    				'<select name="type-'+len+'" style="height: 24px;">',
    				'<option value="1" selected>输入框</option>',
    				'<option value="2">单选</option>',
    				'<option value="3">多选</option>',
    				'<option value="4">图片</option></select>',
    				'</div>',
    				'<div class="propType"><input name="propertyName-'+len+'" value=""></input></div>',
    				'<div class="propType"><input name="identify-'+len+'" value=""></input></div>',
    				'<input type="hidden" name="id-'+len+'" value=""></input>',
    				'</div>'
    				].join('');
    	$('.propValue').each(function(i){
    		if(i==(len-1)){
    			$(this).after(html);
    			$('#len').val(len);
    		}
    	});
    });
    $(document).on('click','.prosave',function(){
    	$.ajax({
            cache: false,
            type: "POST",
            url:"/mall/format/ext/add.html",
            data:$('#formatPropertyForm').serialize(),// 你的formid
            dataType: "json", 
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
            	if(data.code==0){
            		alert(data.message);
            	}else{
            		$('.popProperty').hide();
            		var id=$('#formatId').val();
            		$('#format'+id).val(1);
            		clearValue();
            	}
            }
        });
    	
    });
    $(document).on('click','.procancel',function(){
    	$('.popProperty').hide();
    	clearValue();
    });
    function clearValue(){
    	var len=$('.propValue').length-1;
    	$('.propValue').each(function(i){
    /* 		if(i==1){
    			$(this).find('input').each(function(){
    				$(this).val('');
    			});
    			$(this).find('select[name="type"]').val('1');
    		}  */
    		if(i>0){
    			$(this).remove();
    		}
    	});
    }
</script>
</body>
</html>