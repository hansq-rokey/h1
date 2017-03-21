<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/daterangepicker-bs3.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/common.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/Process.css" />
    <script type="text/javascript" src="/ueditor.config.js"></script>
    <script type="text/javascript" src="/ueditor.all.min.js"></script>
    <title>商品发布流程第二步</title>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<!-- <div class="addBtn"><i></i></div> -->
<form action="/mall/product/update.html" method="post" id="form2">
<div class="right-part">
 <jsp:include page="../include/top_new.jsp"/>
    <div class="wrap add_productwrap">
       <div class="m_content" style="padding-bottom: 100px;">
          <div class="p_title">
            <ul class="p_list">
              <li><a href="/mall/product/edit.html?id=${productId }"><span>1</span></a></li>
              <li class="p_on"><a href="/mall/product/edit.html?id=${productId }&level=2"><span>2</span></a></li>
              <li><a href="/mall/product/edit.html?id=${productId }&level=3"><span>3</span></a></li>
            </ul>
          </div>
        <div class="p_content" >
          <div class="t_lista">
            <!-- div class="t_mian">
              <span>Web链接</span>
              <input type="text" disabled="disabled" id="web_href">
            </div> -->
            <p class="p_tag">详情页图片</p>
            <div class="t_edit">
                <script id="editor1" name="appProductDetail"  type="text/plain" style="width:100%;height:300px;">${detail.appProductDetail}</script>
            </div>
          </div>
          <div class="t_lista">
            <!-- <div class="t_mian">
              <span>参数页URl</span>
              <input type="text" id="argument_href" disabled="disabled">
            </div> -->
            <p class="p_tag">参数图片</p>
            <div class="t_edit">
                <script id="editor2" name="appParamterDetail" type="text/plain" style="width:100%;height:300px;">${detail.appParamterDetail}</script>
            </div>
          </div>
        </div>
        <p class="p_btn">
          <a class="p_btna" href="###">保存</a>
        </p>
    </div>
  </div>
</div>
<input type="hidden" name="level" value="2" />
<input type="hidden" name="productId" value="${detail.productId }" />
</form>
<script type="text/javascript" src="/plug_new/jQuery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/moment.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="/plug_new/layer/layer.js"></script>
<script type="text/javascript" src="/js_new/common.js"></script>
<script type="text/javascript">
     var ue = UE.getEditor('editor1', {
      //初始化工具栏菜单 
          toolbars: [
                     ['bold','fontsize','forecolor','backcolor','rowspacingtop','justifyleft','justifycenter','justifyright','justifyjustify','simpleupload','insertimage','insertvideo','link','unlink','attachment']
             ]
         });
     var ue = UE.getEditor('editor2', {
      //初始化工具栏菜单 
          toolbars: [
                     ['bold','fontsize','forecolor','backcolor','rowspacingtop','justifyleft','justifycenter','justifyright','justifyjustify','simpleupload','insertimage','insertvideo','link','unlink','attachment']
             ]
         });
  //输入框焦点事件
 $(document).on('focus', '#web_href,#argument_href', function() {
    $(this).prev("span").css({top: '-17px',color: '#2CA3E5'});
    $(this).addClass('ab');
 });
 $(document).on('blur', '#web_href,#argument_href', function() {
  var _htmlc=$(this).val();
  /*console.log(_htmlc);*/
    if (_htmlc=="") {
        $(this).prev("span").css({top: '5px',color: '#8c98a6'});
        $(this).removeClass('ab');
    }else{
          $(this).prev("span").css({top: '-17px',color: '#2CA3E5'});
          $(this).addClass('ab');
    }
 });

 //URl是否正确
   
 //表单提交
 	$(".p_btna").click(function(){
		$("#form2").submit();
	});
</script>

</body>
</html>