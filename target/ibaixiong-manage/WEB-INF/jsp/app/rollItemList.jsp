<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/commanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>app轮播图管理</title>
    <script type="text/javascript">
	    function update(id,imgUrl,jumpUrl,type,sort){
			$("#id").val(id);
			$("#imgUrl").val(imgUrl);
			$("#jumpUrl").val(jumpUrl);
			$("#type").val(type);
			$("#sort").val(sort);
			$("#imgShow").attr("src",imgUrl);
			$('.addpop').show();
		}
		function add(){
			$("#id").val("");
			$('.addpop').show();
		}
		function check(){
    		var imgUrl = $("#imgUrl").val();
    		if(imgUrl == null || imgUrl ==undefined || imgUrl == ''){
    			alertLayel("图片不可为空");
				return false;
			}
    		var type = $("#type").val();
    		if(type == null || type ==undefined || type == ''){
    			alertLayel("类型不可为空");
				return false;
			}
    		var r = /^\+?[0-9][0-9]*$/;//正整数
		    if(!r.test(type)){
		    	alertLayel("类型请输入正整数");
		    	return false;
		    }
		    var sort = $("#sort").val();
    		if(sort == null || sort ==undefined || sort == ''){
    			alertLayel("排序不可为空");
				return false;
			}
    		var r = /^\+?[1-9][0-9]*$/;//正整数
		    if(!r.test(sort)){
		    	alertLayel("排序请输入正整数");
		    	return false;
		    }
    		return true;
    	}
		$(document).on('change','#file',function(){
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
					&& strExtension != 'png' && strExtension != 'bmp'&& strExtension != 'JPG'&& strExtension != 'PNG') {
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
				url: "/app/upload.html",
				data: data,
				cache: false,
				contentType: false,    //不可缺
				processData: false,    //不可缺
				dataType:"json",
				success: function(data) {
					if(!data.success){
						alertLayel(data.message);
					}else{
						$("#imgUrl").val(data.result.url);
						$("#imgShow").attr("src",data.result.url);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alertLayel("上传失败，请检查网络后重试");
				}
			});
		});
    </script>
</head>
<body>
    <section>
        <ul class="partlist" style="border-bottom: 0px;margin-top: -45px;">
            <li>
                <div class="inforbox selectinforbox">
                    <div class="row">
                        <input type="button" class="addlevel" onclick="add()" value="添加">
                        <table class="gradelist">
                            <thead>
                            <tr>
                                <td>图片</td>
                                <td>跳转路径</td>
                                <td>类型</td>
                                <td>排序</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="item" varStatus="st">
                            <tr class="menutr">
                                <td><img src="${item.imgUrl }"/></td>
                                <td>${item.jumpUrl }</td>
                                <td>${item.type }</td>
                                <td>${item.sort }</td>
                                <td>
                                	<a href="#" class="link deletelink" onclick="update(${item.id},'${item.imgUrl }','${item.jumpUrl }',${item.type},${item.sort})">修改</a>
                                	<a href="/app/delRollItem.html?id=${item.id }&rollId=${roll.id }" class="link deletelink" >删除</a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
<form action="/app/saveRollItem.html" onsubmit="return check()"  method="post">
<input type="hidden" name="id" id="id" value="">
<input type="hidden" name="rollId" id="rollId" value="${roll.id }">
<div class="pop addpop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle"><span id="spanTitle">新增轮播</span><i class="closeicon"></i> </h3>
        <div class="row">
            <span class="addtypename">图片：</span>
            <div class="img_box"> <img src="" id="imgShow"/></div>
            <input style="display:inline-block;width:200px;" type="file" name="file" id="file"/>
            <input type="hidden" class="urlvalue" name="imgUrl" id="imgUrl" value="">
        </div>
        <div class="row">
            <span class="addtypename">跳转路径：</span>
            <input type="text" class="urlvalue" id="jumpUrl" name="jumpUrl" value="">
            <span>如果跳转类型为产品，这里填产品ID</span>
        </div>
        <div class="row">
            <span class="addtypename">类型：</span>
            <input type="text" class="urlvalue" id="type" name="type" value="">
            <span>0：非产品跳转；1：产品跳转</span>
        </div>
        <div class="row">
            <span class="addtypename">排序：</span>
            <input type="text" class="urlvalue" id="sort" name="sort" value="">
        </div>
        <div class="row tc">
            <input type="submit" value="保存" class="delete">
        </div>
    </div>
</div>
</form>
</body>
</html>
