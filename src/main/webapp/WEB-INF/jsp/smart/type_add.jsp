<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<base href="/">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<link href="../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../css/pubstyle.css" rel="stylesheet" type="text/css">
<link href="../css/equipdetails.css" rel="stylesheet" type="text/css">
<script src="../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="../js/public.js" type="text/javascript"></script>
<title>类型管理添加</title>
</head>
<body>
	<form action="/smart/type/save.html" method="POST" enctype="multipart/form-data">
		<ul class="Type-box">
			<li><span class="type-addText">类目选择:</span>
				<div class="select-box" id="categoryId" data-distinction="0">
					<em>请选择</em>
					<ul class="select-mian"  id="select-mian01">
						<c:forEach items="${categoryList }" var="item">
							<li data-id="${item.id }" data-name="${item.name }" data-code="${item.code }">${item.name }</li>
						</c:forEach>
					</ul>
				</div>
				<div class="select-box select-width" data-distinction="1">
					<em>选择</em>
					<ul class="select-mian"  id="selectsecond" ></ul>
				</div>
			</li>
			<li>
				<span class="type-addText">产品LOGO:</span> 
				<input	class="typeLogo" name="logoFile" id="typeLogo"	onchange="noemptyimg(this)" type="file">
			</li>
			<input type="hidden" name="categoryId"  id="hcategoryId"/>
			<input type="hidden" name="categoryCode"  id="hcategoryCode" />
			<input type="hidden" name="categoryName"  id="hcategoryName" />
			<input type="hidden" name="categoryModelId"  id="hcategoryModelId" />
			<input type="hidden" name="categoryModelCode"  id="hcategoryModelCode" />
			<input type="hidden" name="categoryModelName"  id="hcategoryModelName" />
			<li>
				<span class="type-addText">重置提示图:</span> 
				<input class="resetimg" name="resetImgFile" id="resetimg" onchange="noemptyimg(this)" type="file">
			</li>
			<li>
				<span class="type-addText">状态就绪提示图:</span>
				<input class="stateimg" name="readyYesImgFile" id="stateimg" onchange="noemptyimg(this)" type="file">
			</li>
			<li>
				<span class="type-addText">状态未就绪提示图:</span> 
				<input class="tipimg" name="readyNoImgFile" id="tipimg"	onchange="noemptyimg(this)" type="file">
			</li>
			<li>
				<span class="type-addText">wifi名称:</span> 
				<input class="wifiname" name="wifiName" id="wifiname" onchange="noempty(this.id)" type="text">
			</li>
			<li>
				<span class="type-addText">wifi密码:</span> 
				<input class="wifipassword" name="wifiPassword" id="wifipassword" onchange="noempty(this.id)" type="text">
			</li>
			<li>
				<span class="type-addText">wifi-ip:</span> 
				<input class="wifiIp" name="wifiHost" id="wifiIp" onchange="noempty(this.id)" type="text">
			</li>
			<li>
				<span class="type-addText">wifi端口:</span> 
				<input class="wifihost" name="wifiPort" id="wifihost" onchange="noempty(this.id)" type="text">
			</li>
			<li>
				<span class="type-addText">bxid前4位:</span> 
				<input class="bxid" name="bxid4" id="bxid" onchange="noempty(this.id)" type="text">
			</li>
			<li>
				<span class="type-addText">操作界面:</span> 
				<input class="Interface" name="ui" id="Interface" onchange="noempty(this.id)" value="default" type="text">
			</li>
			<li>
				<span class="type-addText">cVersionLast:</span> 
				<input class="cVersionLast" name="cVersionLast" id="cVersionLast" onchange="noempty(this.id)" type="text">
			</li>
		</ul>
		<div class="type-btn">
			<input class="type-sub" id="type-sub" type="submit" value="提交">
		</div>
	</form>
	<script type="text/javascript">
		var noempty = function(id) {
			var thisid = document.getElementById(id);
			if (thisid.value != "" && thisid.value != null) {
				return true;
			} else {
				return false
			}
		}
		var noemptyimg = function(obj) {
			var imagesize = 1024 * 1024 * 1;//1M
			var arr = [ 'JPG', 'PNG', 'GIF', 'JPEG', 'BMP' ];
			var thisvalue = (obj.value).split(".");
			if (arr.indexOf(thisvalue[thisvalue.length - 1].toUpperCase()) == -1) {
				alertLayel("只支持jpg png gif jpeg bmp 图片格式");
				obj.value = "";
			} else {
				if ((obj.files[0].size * 1024) > imagesize) {
					return true;
				} else {
					alertLayel("图片尺寸不能大于1M");
					return false
				}
				;
			}
		}
		function TypeDevice() {
			/* var categoryId = document.getElementById('categoryId');
			var categoryCode =  document.getElementById('categoryCode');
			var categoryCode = document.getElementById('categoryName');
			var categoryModelId = document.getElementById('categoryModelId');
			var categoryModelCode= document.getElementById('categoryModelCode');
			var categoryModelName = document.getElementById('categoryModelName');
			var typeLogo = document.getElementById('typeLogo');
			var resetimg = document.getElementById('resetimg');
			var stateimg = document.getElementById('stateimg');
			var tipimg = document.getElementById('tipimg');
			var wifiname = document.getElementById('wifiname');
			var wifipassword = document.getElementById('wifipassword');
			var wifiIp= document.getElementById('wifiIp');
			var wifiIp = document.getElementById('wifihost');
			var wifiIp =  document.getElementById('bxid');
			var cVersionLast =  document.getElementById('cVersionLast'); */
			var noempty = document.getElementsByTagName('input');
			for (var i = 0; i < noempty.length; i++) {
				if (noempty[i].value == "" && noempty[i].value == null) {
					/* 	alertLayel("检查是否有空值"); */
					return false;
				} else {
					return true;
				}
			}
		}
		function selectmian(e){
			var dataId,dataName,dataCode;
			var categoryId = document.getElementById('hcategoryId');
			var categoryCode =  document.getElementById('hcategoryCode');
			var categoryName = document.getElementById('hcategoryName');
			var categoryModelId = document.getElementById('hcategoryModelId');
			var categoryModelCode= document.getElementById('hcategoryModelCode');
			var categoryModelName = document.getElementById('hcategoryModelName');
			var thisClass = e.target.parentNode.getAttribute('id');//判断当前下拉
			if (e.target && e.target.nodeName == "LI" && thisClass == "select-mian01") {
				var e = e || window.e; 
					dataId = e.target.getAttribute('data-id');
					dataName = e.target.getAttribute('data-name');
					dataCode = e.target.getAttribute('data-code');
					categoryId.value = dataId ;
					categoryCode.value = dataCode;
					categoryName.value =  dataName ;
					e.target.parentNode.parentNode.children[0].innerText = e.target.innerHTML;
					selectajax(dataId,dataName,dataCode);
			}else if(e.target && e.target.nodeName == "LI" && thisClass == "selectsecond"){
				var e = e || window.e; 
				dataId = e.target.getAttribute('data-id');
				dataName = e.target.getAttribute('data-name');
				dataCode = e.target.getAttribute('data-code');
				categoryModelId.value = dataId ;
				categoryModelCode.value =  dataCode;
				categoryModelName.value =dataName  ;
				e.target.parentNode.parentNode.children[0].innerText = e.target.innerHTML;
			}
		}
		document.getElementById('select-mian01').addEventListener("click",selectmian);
		document.getElementById('selectsecond').addEventListener("click",selectmian);
		function selectajax(dataId,dataName,dataCode){
			var createli = '';
			var selectid = $("#selectsecond");
			console.log(selectid);
			$.ajax({
				   url:'mall/model/category.html?id='+dataId,
				   type:'post',
				   data:{"dataName":dataName,"dataCode":dataCode},
				   dataType:'JSON',
				   success:function(data){
					   for(var i in data){
						  createli +='<li data-id="'+data[i].id+'" data-name="'+data[i].name+'" data-code="'+data[i].code+'">'+data[i].name+'</li>';
					   }
					   selectid.append(createli);
				   }
			   })
		}
		$(document).ready(function() {
			$(document).on('click', '.closeicon', function() {
				$('.bxidvalue').val('');
				$('.urlvalue').val('');
				$('.hardwarevalue').val('');
				$('.versionvalue').val('');
				$('.filevalue').val('');
				$('.addpop').hide();
			});
		})
	</script>
</body>
</html>