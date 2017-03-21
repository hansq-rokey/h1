<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="/">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../css/pubstyle.css" rel="stylesheet" type="text/css">
	<link href="../css/equipdetails.css" rel="stylesheet" type="text/css">
	<script src="../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
	<script src="../js/public.js" type="text/javascript" ></script>
	<title>设备版本管理</title>
</head>
<body>
	<div class="xl">
		<a class="list_Device Device_upgrade ${status==2?'list_ative':'' }" onclick="add(0)" href="javascript:">添加</a>
	</div>
	<div class="bg">
	    <table class="biaoge">
		    <thead>
			<tr>
			    <td>序号</td>
				<td>bxid4</td>
				<td>固件最新版本号</td>
				<td>硬件版本</td>
				<td>软件版本号</td>
				<td>升级地址</td>
				<td>创建时间</td>
				<td>操作日志</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ pageInfo.list }" var="item">
			    <tr>
				    <td>${ item.id }</td>
				    <td>${ item.bxid4 }</td>
					<td>${ item.cVersionLast }</td>
					<td>${ item.cVersionLast2 }</td>
					<td>${ item.cVersionLast4 }</td>
					<td>${ item.upgradeBin }</td>
					<td>
						<fmt:formatDate value="${ item.createDateTime }" pattern="YYYY-MM-dd HH:MM" />
					</td>
					<td><a href="/smart/version/history/list.html?id=${item.id }" class="link">历史记录</a> <a href="javascript:" data-id="${item.id }" onclick="add(1)" class="link upload">上传安装包</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<form action="/smart/version/list.html" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<input type="submit" class="search" style="display:none;" value="搜索">
		</form>
		<div class="pop addpop">
	    <div class="popbg"></div>
	    <form action="/smart/version/save.html" method="post" enctype="multipart/form-data" onsubmit="return  verifyDevice()">
		    <div class="deviceadd_layel">
		        <h3 class="poptitle"><span id="spanTitle">新增设备版本</span><i class="closeicon" data-class="0"></i> </h3>
		        <div class="row">
		            <span class="addtypename">bxid4：</span>
		            <input type="text" class="bxidvalue" name="bxid4" id="bxidvalue" value="">
		        </div>
		        <div class="row">
		            <span class="addtypename">固件最新版本号：</span>
		            <input type="text" class="urlvalue" id="urlvalue" name="cVersionLast" value="">
		        </div>
		        <div class="row">
		            <span class="addtypename">硬件版本：</span>
		            <input type="text" class="hardwarevalue" id="hardwarevalue" name="cVersionLast2" value="">
		        </div>
		        <div class="row">
		            <span class="addtypename">软件版本号：</span>
		            <input type="text" class="versionvalue" id="versionvalue" name="cVersionLast4" value="">
		        </div>
		         <div class="row">
		            <span class="addtypename">升级地址：</span>
		            <input type="file"  class="filevalue" id="addfilevalue" name="file" value="">
		        </div>
		        <div class="row tc">
		            <input type="submit" value="保存" onclick="verifyDevice()"   class="deviceadd">
		        </div>
		    </div>
		   </form> 
	</div>
	<div class="pop addpackage">
	    <div class="popbg"></div>
	    <form action="/smart/version/upgrade.html" method="post" enctype="multipart/form-data" onsubmit="return  verifypackage()">
		    <div class="deviceadd_layel">
		        <h3 class="poptitle"><span id="spanTitle">上传安装包</span><i class="closeicon" data-class="1"></i> </h3>
		         <div class="row">
		            <span class="addtypename">升级地址：</span>
		            <input type="file" onchange="verifyfile(this.id)" class="filevalue" id="packfilevalue" name="file" value="">
		            <input type="hidden" name="id" id="id" />
		        </div>
		        <div class="row tc">
		            <input type="submit" value="保存" onclick="verifypackage()"  class="deviceadd">
		        </div>
		    </div>
		   </form> 
	</div>
		<jsp:include page="../include/pages_old.jsp"></jsp:include>
	</div>
	 <script type="text/javascript">
		var Verifyfile = {
			verifyadd:function(obj){//bin文件验证
				var url = obj.value.split('.');
				var flag = false;
				for(var i=0;i < url.length;i++){
					if(url[i] == "bin"){
						flag = true;
						break;
					}
				}
				if(!flag){
					alertLayel("文件不正确"); 
					obj.value = "";
				}
			},
			
		 }
		var layeadd = document.getElementById("addfilevalue");//弹窗验证
		var filevalue = document.getElementById('packfilevalue');//安装包上传验证
			layeadd.onchange = function(){
				Verifyfile.verifyadd(this);
	    	}
			filevalue.onchange = function(){
				Verifyfile.verifyadd(this);
			}
		function add(obj){
			if(obj == 0){
				$('.addpop').show();
			}else if(obj == 1){
				$('.addpackage').show();
			}
		}
    	function  verifyDevice(){//升级地址上传验证
    		var bxid = document.getElementById('bxidvalue').value;
    		//var url = document.getElementById('urlvalue').value;
    		//var hardware = document.getElementById('hardwarevalue').value;
    		//var version = document.getElementById('versionvalue').value;
    		var file = document.getElementById('addfilevalue').value;
    		if(bxid != '' && bxid !=null   && file != '' && file != null){
    			return true;
    		}else{ alertLayel("检查是否有值未填");return false}
    	}
    	function verifypackage(){//安装包上传验证
    		var packgvalue = document.getElementById("packfilevalue");
    		if(packgvalue != null && packgvalue != ""){
    			return true;
    		}else{
    			return false;
    		}
    	}
		$(document).ready(function(){
		    $(document).on('click','.closeicon',function(){
		    	var thisClass = $(this).data("class");
		    	console.log($(this));
		    	if(thisClass == 0){
		    		$('.bxidvalue, .urlvalue , .hardwarevalue , .versionvalue ,.filevalue').val('');
			    	$('.addpop').hide();
		    	}else if(thisClass == 1){
		    		$("#packfilevalue").val('');
		    		$(".addpackage").hide();
		    	}
		    });
		    
		    $('.upload').on('click',function(){
		    	var id=$(this).attr('data-id');
		    	$('#id').val(id);
		    	
		    });
		})
	</script>
</body>
</html>