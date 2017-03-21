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
	    function update(id,name,code){
			$("#id").val(id);
			$("#name").val(name);
			$("#code").val(code);
			$("#spanTitle").html("修改轮播");
			$('.addpop').show();
		}
		function add(){
			$("#id").val("");
			$("#spanTitle").html("新增轮播");
			$('.addpop').show();
		}
		function check(){
    		var name = $("#name").val();
    		if(name == null || name ==undefined || name == ''){
    			alertLayel("名称不可为空");
				return false;
			}
    		var code = $("#code").val();
    		if(code == null || code ==undefined || code == ''){
    			alertLayel("编码不可为空");
				return false;
			}
    		return true;
    	}
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
                                <td>名称</td>
                                <td>编码</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="item" varStatus="st">
                            <tr class="menutr">
                                <td>${item.name }</td>
                                <td>${item.code }</td>
                                <td>
                                	<a href="#" class="link deletelink" onclick="update(${item.id},'${item.name }','${item.code }')">修改</a>
                                	<a href="/app/delRoll.html?id=${item.id }" class="link deletelink" >删除</a>
                                	<a href="/app/getItemList.html?rollId=${item.id }" class="link deletelink" >查看轮播图</a>
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
<form action="/app/saveRoll.html" onsubmit="return check()"  method="post">
<input type="hidden" name="id" id="id" value="">
<div class="pop addpop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle"><span id="spanTitle">新增轮播</span><i class="closeicon"></i> </h3>
        <div class="row">
            <span class="addtypename">名称：</span>
            <input type="text" class="urlvalue" name="name" id="name" value="">
        </div>
        <div class="row">
            <span class="addtypename">编码：</span>
            <input type="text" class="urlvalue" id="code" name="code" value="">
        </div>
        <div class="row tc">
            <input type="submit" value="保存" class="delete">
        </div>
    </div>
</div>
</form>
</body>
</html>
