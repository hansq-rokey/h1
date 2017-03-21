<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/daterangepicker-bs3.css" />
    <link rel="stylesheet" type="text/css" href="/plug_new/webupdata/webuploader.css">
    <link rel="stylesheet" type="text/css" href="/css_new/common.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/Process.css" />
    <title>商品发布流程第三步</title>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<!-- <div class="addBtn"><i></i></div> -->
<div class="right-part">
 <jsp:include page="../include/top_new.jsp"/>
    <div class="wrap add_productwrap calcHeight">
       <div class="m_content" style="padding-bottom: 100px;">
          <div class="p_title">
            <ul class="p_list">
              <li>
                <span>1</span>
              </li>
              <li>
                <span>2</span>
              </li>
              <li class="p_on">
                <span>3</span>
              </li>
            </ul>
          </div>
        <div class="p_imgbox">
          <p class="atag">App缩略图</p>
          <div class="p_imgadd ibox" id="uploader-demo">
            <div class="fileUp" id="filePicker"></div>
            <div id="fileList" class="uploader-list"></div>
            <span class="-uploader -uploadercolor">最多上传5张</span>
          </div>
         <p class="fileadd"> <button id="ctlBtn" class="btn btn-default">开始上传</button></p>
        </div>
	        <div class="p_content" >
	          <p class="atag">规格信息</p>
	         <table class="row_box">
	           <tbody>
	             <tr>
	               <th class="modify_width">序号</th>
	               <th>缩略图</th>
	               <c:forEach items="${properties }" var="item">
		               <th class="modify_width">${item.propertyName }</th>
	               </c:forEach>
	               <th>商品编号</th>
	               <th class="modify_width">扩展</th>
	               <th class="modify_width">物料</th>
	               <th>操作</th>
	             </tr>
	             <c:forEach items="${formats }" var="item" varStatus="vs">
	             	<tr>
		               <td class="modify_width">${vs.count }</td>
		               <td>
		                  <div class="rowimg">
			                <input type="file" class="uploadimgbtn file" data-id="${item.id }">
			                <c:if test="${fn:length(item.pics)>0 }">
		                	<c:forEach items="${item.pics }" var="picItem">
				                <img src="${picItem.url }">		                		
		                	</c:forEach>
		                </c:if>
		                <c:if test="${fn:length(item.pics)==0 }">
			                <img src="/images_new/sl_01.png">		                
		                </c:if>
			              </div>
						</td>
		                 <c:forEach items="${item.propertiesValues }" var="proItem">
			               <td class="modify_width">
			                 <p>${proItem.value }</p>
			               </td>
		                </c:forEach>
		               <td>${item.categoryModelFormatNumber}</td>
		              	<td>
                   			<span class="p_set" data-id="${item.id }">设置</span>
                	  	</td>
                 		<td>
                   			<span class="material" data-id="${item.id }">设置</span>
                 		</td>
		               	<td>
		                 <span class="Operation_set">操作</span>
		                 <input type="hidden" name="formatId" value="${item.id }"/>
		                 <input type="hidden" name="price" value="${item.price}"/>
		                 <input type="hidden" name="productPurchasePrice" value="${item.productPurchasePrice}"/>
		                 <input type="hidden" name="areaMoney" value="${item.areaMoney}"/>
		                 <input type="hidden" name="unit" value="${item.unit}"/>
		                 <input type="hidden" name="cDisplay" value="${item.cDisplay}"/>
                 		 <input type="hidden" name="bDisplay" value="${item.bDisplay}"/>
                 		 <input type="hidden" name="freight_template_id" value="${item.freightTemplateId}"/>
                 		 <input type="hidden" name="template_name" value="${item.templateName}"/>
                 	  </td>
		       		</tr>
	             </c:forEach>
	           </tbody>
	         </table>
	        </div>
        <!-- <p class="p_btn">
          <a class="p_btna" href="###">发布</a>
          <a class="p_btnb" href="###">取消</a>
        </p> -->
    </div>
  </div>
</div>
<input type="hidden" name="productId" id="productId" value="${productId }" />
<form id="formatPropertyForm">
	<div class="p_popup" style="display:none">
	  <div class="popup_mian">
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
	     <ul class="boxcc">
	     </ul>
	   <p class="add_p"><a class="plusProprety">+</a></p>
	   <div class="popButtonArea">
	      <a class="popButton prosave">保存</a><a class="popButton procancel">取消</a>
	      <p class="-uploadercolor -uploadermargin">关于标识符说明：(长：length；宽：width;高：height)</p>
	  </div>
	  </div>
	  	<input type="hidden" name="formatId" id="formatId" value="" />
		<input type="hidden" name="len" id="len" value="" />
		<input type="hidden" name="freightTemplateId" id="templateId" value="" />
	</div>
</form>
<!-- url:/mall/pic/delete.html?id=xxx -->
<div class="delect_on" style="display:none">
 <div class="-sure">
   <p>确认删除？</p>
   <div class="-delect_list">
     <a class="-delecta" href="#">确定</a>
     <a class="-delectb" href="#">取消</a>
   </div>   
 </div>
</div>
<!--物料弹窗-->
<div class="getData" style="display:none">
  <div class="getDataChild">
    <ul class="DataChildbox"> </ul>
    <a class="Data_sure" href="###">确定</a>
     <input type="hidden"  id="DataCreat" value="" />
  </div>    
</div>
<!--商品操作-->
<form action="/mall/product/save.html" method="post" id="form3">
<div class="goods_set" style="display:none" >
   <div class="goods_setbox">
    <ul class="goods_setmian">
     <li>
       <input type="hidden" id="set_formatId" name="id" value=""/>
       <laber>长</laber>
       <div class="roww roww_set">
               <input type="text" id="set_length" name="length" value="" >
               <label>cm</label>
          </div>
     </li>
     <li>
       <laber>宽</laber>
       <div class="roww roww_set">
           <input type="text" id="set_width" name="width" value="">
           <label>cm</label>
        </div>
     </li>
     <li>
        <laber>预留库存</laber>
        <div class="roww roww_set">
               <input type="text" id="set_stock" name="stock" value="">
               <label class="nuedit"></label>
          </div>
     </li>
       <li>
           <laber>市场价</laber>
           <div class="roww roww_set">
                <input type="text" id="set_money" name="price" value="">
                <label class="nuedit1">元/<b class="edit_num"></b></label>
            </div>
       </li>
       <li>
         <laber>进货价</laber>
         <div class="roww roww_set">
            <input type="text" id="set_import" name="productPurchasePrice" value="">
            <label class="set_Unit">元</label>
            </div>
       </li>
       <li>
         <laber>区域金额</laber>
         <div class="roww roww_set">
            <input type="text" id="set_zone" name="areaMoney" value="">
            <label class="set_Unit">元</label>
         </div>
        </li>
        <li style="margin-top:0px">
         <laber>单位</laber>
          <div class="rowc_mian setrowc_mian" >
             <div class="rowskin">
               <i class="selectpoint"></i>
               <span class="selectcontent"></span>
               <ul class="rowopation" style="display: none;">
                   <li>件</li>
                   <li>个</li>
                   <li>平方米</li>
                   <li>卷</li>
               </ul>
                <input type="hidden" class="c_m" name="unit" value="" id="c_m1" >
              </div>
           </div>
       </li>
       <li>
         <laber>可见性</laber>
         <div class="roww roww_set">
            <p>
               <input type="checkbox" id="cDisplay" name="cDisplay" value="1" onchange="selectChecked($(this))" /><label for="checkbox7"> </label><span class="Client">C端用户</span>
	           <input type="checkbox" id="bDisplay" name="bDisplay" value="1" onchange="selectChecked($(this))" /><label for="checkbox8"> </label><span class="Client">B端用户</span>
	        </p> 
         </div>
         <div class="selectskin select-layebox">
         <i class="selectpoint"></i>
         <span class="selectcontent template_id" data-id="">模板名称</span>
         <ul class="selectopation" style="display: none;">
            <c:forEach items="${templates}" var="template">
               <li data-id="${template.id}">${template.name}</li>
            </c:forEach>
         </ul>
    </div>
        </li>
    </ul>
    <div class="set_textare">
      <label>说明</label>
      <textarea id="set_explain" name="explain" class="set_textaremian"></textarea>
    </div>
    <div class="goods_savebox">
       <a class="goods_save _save_btn1" href="###">保存</a>
       <a class="goods_save _save_btn2" href="###">取消</a>
     </div>
   </div>
</div>
	<input type="hidden" name="level" value="3" />
	<input type="hidden" name="freightTemplateId" id="templateId" value="" />
</form>
<script type="text/javascript" src="/plug_new/jQuery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/moment.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="/plug_new/webupdata/webuploader.js"></script>
<script type="text/javascript" src="/plug_new/layer/layer.js"></script>
<script type="text/javascript" src="/js_new/common.js"></script>
<script type="text/javascript">

//图片上传
jQuery(function() {
    var $ = jQuery,
        $btn = $('#ctlBtn'),
        $list = $('#fileList'),
        state = 'pending',
        productId=$('#productId').val(),
        // 添加的文件数量
        fileCount = 0,
        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,
        // 缩略图大小
        thumbnailWidth =85 * ratio,
        thumbnailHeight =95 * ratio,
        // Web Uploader实例
        uploader;
    
       // 所有文件的进度信息，key为file id
        percentages = {},

    // 初始化Web Uploader
    uploader = WebUploader.create({

        // 自动上传。
        auto:false,
        
        formData: {
            productId: productId
        },

        // swf文件路径
        swf:'/plug/webuploader/Uploader.swf',

        // 文件接收服务端。
        server: '/mall/upload/thumbnail.html',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',

        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info"><i class="reimg">0</i></div>' +
                '</div>'
                ),
            $img = $li.find('img');

        $list.append( $li );

        // 创建缩略图
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }
        $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).addClass('upload-state-done');
    });

    // 文件上传失败，现实上传出错。
    uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }

        $error.text('上传失败');
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });
  
    uploader.on( 'all', function( type ) {
        if ( type === 'startUpload' ) {
            state = 'uploading';
        } else if ( type === 'stopUpload' ) {
            state = 'paused';
        } else if ( type === 'uploadFinished' ) {
            state = 'done';
        }

        if ( state === 'uploading' ) {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });
   $btn.on( 'click', function() {
        if ( state === 'uploading' ) {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
});

//图片上传
$(document).on('change','.uploadimgbtn',function(){
    var typebtn=$(this);
          var imgPath = $(this).val();
          var formatId=$(this).attr("data-id");
          if(formatId==null)formatId=0;
          var productId=$('#productId').val();
          //判断是否有选择上传文件
          if (imgPath == "") {
            alertLayel("请选择上传图片！");
              return;
          }
          //判断上传文件的后缀名
          var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
          if (strExtension != 'jpg' && strExtension != 'gif'
                  && strExtension != 'png' && strExtension != 'bmp') {
            alertLayel("请选择正确图片文件");
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
              url: "/mall/product/upload.html?type=2&formatId="+formatId+"&productId="+productId,
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
$("._save_btn1").click(function(){
  $("#form3").submit();
});
/*删除确认弹窗*/
 $(function(){
   $(document).on('click', '.-delectb', function() {
      $(".p_popup").hide();
   });
   $(document).on('click', '.-delecta', function() {
     var conetnt=$("#-delect-").val();
     var _this=$(this);
     $.ajax({
              cache: false,
              type: "POST",
              data:{id:conetnt},
              url:"/mall/pic/delete.html",
          dataType:"json",
          cache:false,
              error: function(request) {
                  alert("删除失败");
              },
              success: function(data) {
                $('.-view_list').each(function(){
                  var id=$(this).find('span').attr('data-id');
                  if(id==conetnt){
                    $(this).remove();
                  }
                });
                $(".delect_on").hide();
              }
        });
   });
   $(".-view_list").hover(function() {
       $(this).children(".-delete").animate({top:"0px"});
    }, function() {
      $(this).children(".-delete").animate({top:"-30px"})
   });
   $(".-delete").on('click', function() {
     var content=$(this).data("id");
    $("#-delect-").val(content);
    $(".delect_on").fadeIn();
  });
   $('.material').on('click',function(){
     var formatId=$(this).attr('data-id');
     var _formats;
     $(".getData").fadeIn(200);
      /* console.log(_formats);  */
     ajaxFn('POST',{formatId:formatId},'/mall/material/format.html',function(data){
       _formats=data.result;
    });
     ajaxFn('POST',{},'/mall/material.html',function(data){
       $.each(data.result,function(i,item){
         var _this=$(this);
         var _check='';
         $.each(_formats,function(y,formatItem){
           if(item.id==formatItem.materialId){
             _check='checked="checked"'; 
           }
          });
              DataArry(item,i,formatId,_check);  
         });
     });
   });
 })
 var ajaxFn=function(type,data,url,fn){
   $.ajax({
         cache: false,
         type: type,
         data:data,
         url:url,
         dataType:"json",
         cache:false,
         error: function(request) {
         },
         success: function(data) {
          fn(data);
         }
     });
}
/*弹框添加数据*/
function DataArry(item,i,formatId,_check){
   var $itemName=item.name;
   var content='<li><label>'+$itemName+'</label><input type="checkbox" data-formid="'+formatId+'" '+_check+' id="'+item.id+'" ></li>';
    $(".DataChildbox").append(content);
}
/*sure*/
$(document).on('click', '.Data_sure', function() {
  $(this).siblings('.DataChildbox').html(" ");
  $(this).parents(".getDataChild").parents(".getData").fadeOut(200);
});

/*checked*/
$(document).on('click','.DataChildbox>li>input', function() {
  var $this=$(this);
  var $thisId=$this.attr('id');
  var formId=$this.attr('data-formid');
  ajaxFn("POST",{formatId:formId,materialId:$thisId},'/mall/material/update.html');
});
/*操作*/
$(document).on('click', '.Operation_set', function(event) {
   var that=$(this);
  
   var formatId=that.siblings("input[name='formatId']").val();
   var price=that.siblings("input[name='price']").val();
   var productPurchasePrice=that.siblings("input[name='productPurchasePrice']").val();
   var areaMoney=that.siblings("input[name='areaMoney']").val();//区域金额
   var unit=that.siblings("input[name='unit']").val();
   var cDisplay=that.siblings("input[name='cDisplay']").val();
   var bDisplay=that.siblings("input[name='bDisplay']").val();
   var templateId=that.siblings("input[name='freight_template_id']").val();
   var templateName=that.siblings("input[name='template_name']").val();
   var set_formatId=$("#set_formatId").val(formatId);//表单id
   var set_money=$("#set_money").val(price);//市场价
   var set_import=$("#set_import").val(productPurchasePrice);//进货价
   var set_zone=$("#set_zone").val(areaMoney);//区域金额
   var set_edit_num=$(".edit_num").text(unit);
   var set_selectcontent=$(".selectcontent").text(unit);//单位
   var c_display=$("#cDisplay").val(cDisplay);//可见性
   var b_display=$("#bDisplay").val(bDisplay);//可见性
   document.getElementById("cDisplay").checked=select(cDisplay);//可见性
   document.getElementById("bDisplay").checked=select(bDisplay);//可见性
   $(".template_id").text(templateName);
   $("#templateId").val(templateId);
   $(".goods_set").fadeIn(200);
});
//
function select(thisValue){//数值转换
	if(thisValue == 1){
		return true;
	}else{
		return false;
	}
}
function selectChecked(tValue){
	if(tValue.prop("checked")){
		tValue.val(1);
	}else{
		tValue.val(0);
	}
}
$(document).on('click', '._save_btn2', function(event) {
   var set_formatId=$("#set_formatId").val('');//表单id
   var set_money=$("#set_money").val('');//市场价
   var set_import=$("#set_import").val('');//进货价
   var set_zone=$("#set_zone").val('');//区域金额
   var set_selectcontent=$(".selectcontent").text('');//单位
   $(".goods_set").fadeOut(200);
});
</script>
</body>
</html>
