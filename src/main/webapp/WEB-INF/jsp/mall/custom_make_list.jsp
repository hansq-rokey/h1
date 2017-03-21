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
	    <form action="/mall/custom/list.html" method="post">
	        <input type="text" name="queryStr" value="${queryStr }" class="ordercodebtn" placeholder="支付流水号、订单号或用户名">
	        <input type="text" name="startTime" value="${startTime }" placeholder="开始时间" class="datetimepicker startdata" onclick="SelectDate(this,'yyyy-MM-dd')">
	        <span class="space"></span><span class="space"></span>至
	        <input type="text" name="endTime" value="${endTime }" placeholder="结束时间" class="datetimepicker enddata" onclick="SelectDate(this,'yyyy-MM-dd')">
	        <%-- <span class="selectinput" style="float:none">
                    <span class="selectvalue">支付类型:</span>
                    <i class="arrow arrowright"></i>
                    <ul class="option">
                    	<c:forEach items="${payTypeList }" var="pay">
	                        <li data-id="${pay.dictCodeValue }">${pay.dictCodeName }</li>                    	
                    	</c:forEach>
                    </ul>
                </span>
           	<input name="payType" value="${payType }" type="hidden" id="payType" /> --%>
           	<input type="hidden" name="pageNo" id="pageNo" value="0">
	        <input type="submit" value="搜索" class="searchbtn search">
	    </form>
    </div>
        <table class="finanstatlist">
            <thead>
            <tr>
                <td>订单号</td>
                <td>用户名</td>
                <td>联系方式</td>
                <td>订单日期</td>
                <td>产品名称</td>
                <td>产品规格</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
              <c:forEach items="${list}" var="item" varStatus="st">
	            <tr>
	                <td class="serial">${item.orderNumber }</td>
	                <td class="serial">${item.userName }</td>
	                <td class="serial">${item.phone }</td>
	                <td class="serial"><fmt:formatDate value="${item.createDateTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	               	<c:forEach items="${item.orderItems }" var="orderItem">
		                <td class="serial">${orderItem.productTitle }</td>
		                <td class="serial">${orderItem.productModelFormatName }</td>
	               	</c:forEach>
	                <td class="serial">
	                	<a href="/mall/custom/product/list.html?orderNumber=${item.orderNumber }">查看 &nbsp;&nbsp;&nbsp;&nbsp;</a>
	                	<div class="btn-group">
	                		<input type="button" class="upload-top" value="上传图片">
	                		<input type="file" id="upload" class="upload" data-val="${item.orderNumber }" value="上传">
	                	</div>
	                </td>
	            </tr>
	          </c:forEach>
            </tbody>
        </table>
</section>
<jsp:include page="../include/pages.jsp"></jsp:include>
<div class="pop addpop">
		<div class="popbg"></div>
		<span class="uploading-animate" style="margin:auto;position:absolute;left:0px;right:0px;top:0px;bottom:0px;right:0px;z-index:999;display:block;"></span>
	</div>
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
	      $('.addpop').show();
	      $.ajax({
	          type: "POST",
	          url: "/mall/custom/upload.html?orderNumber="+orderNumber,
	          data: data,
	          cache: false,
	          contentType: false,    //不可缺
	          processData: false,    //不可缺
	          dataType:"json",
	          success: function(data) {
	        	  console.log(data);
	        	  if(data.code==1){
	        		  $('.addpop').hide();
	        		  alertLayel("上传成功");

	        	  }
	          },
	          error: function(XMLHttpRequest, textStatus, errorThrown) {
	        	  $('.addpop').hide();
	        	  alertLayel("上传失败，请检查网络后重试");
	          }
	      });
    });
});
</script>
</body>
</html>
