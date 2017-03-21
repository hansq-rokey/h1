<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/finanstat.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../plug/adddatetime.js" type="text/javascript"></script>
    <title>私人订制</title>
</head>
<body>
<section>
    <div class="row">
    </div>
        <table class="finanstatlist">
            <thead>
            <tr>
                <td>序号</td>
                <td>名称</td>
                <td>类型</td>
                <td>上传时间</td>
                <td>宽度(px)</td>
                <td>高度(px)</td>
                <td>大小(K)</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
              <c:forEach items="${pics}" var="item" varStatus="st">
	            <tr>
	                <td class="serial">${st.count }</td>
	                <td class="serial">${item.picName }</td>
	                <td class="serial">
	                	<c:choose>
	                		<c:when test="${item.type==1 }">原图</c:when>
	                		<c:when test="${item.type==2 }">用户裁剪图</c:when>
	                		<c:otherwise>公司处理图</c:otherwise>
	                	</c:choose>
	              	</td>
	                <td class="serial"><fmt:formatDate value="${item.createDateTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                <td class="serial">${item.width }</td>
	                <td class="serial">${item.heigth }</td>
	                <td class="serial"><fmt:formatNumber value="${item.size/1024 }" minFractionDigits="0" maxFractionDigits="0" /> </td>
	                <td class="serial">
	                	<a href="/mall/custom/download.html?id=${item.id }">下载图片 &nbsp;&nbsp;&nbsp;&nbsp;</a>
	                	<%-- <div class="btn-group">
	                		<input type="button" class="upload-top" value="上传图片">
	                		<input type="file" id="upload" class="upload" data-val="${item.orderNumber }" value="上传">
	                	</div> --%>
	                	
	                </td>
	            </tr>
	          </c:forEach>
            </tbody>
        </table>
</section>
<jsp:include page="../include/pages_old.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('.option li').each(function(){
		var typeId=$('#payType').val();
		var id=$(this).attr('data-id');
		if(typeId==id){
			$('.selectvalue').text($(this).text());
		}
	})
	$(document).on('click','.selectinput li',function(e){
	    var id=$(this).attr("data-id");
	   	$("#payType").val(id);
	});
	
    //图片上传
    $(document).on('change','.upload',function(){
    		  var typebtn=$(this);
              var imgPath = $(this).val();
              var orderNumber=$(this).attr("data-val");
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
                  url: "/mall/custom/upload.html?orderNumber="+orderNumber,
                  data: data,
                  cache: false,
                  contentType: false,    //不可缺
                  processData: false,    //不可缺
                  dataType:"json",
                  success: function(data) {
					//alert(data);
                  },
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                	  alertLayel("上传失败，请检查网络后重试");
                  }
              });
    });
});
</script>
</body>
</html>