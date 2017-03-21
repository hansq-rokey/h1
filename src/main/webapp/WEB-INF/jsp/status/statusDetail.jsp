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
    <title>状态信息列表</title>
    <script type="text/javascript">
    function add(){
		$("#spanTitle").html("新增状态");
		$('.addpop').show();
	}
    function check(){
		var name = $("#name").val();
		if(name == null || name ==undefined || name == ''){
			alertLayel("状态名称不可为空");
			return false;
		}
		var code = $("#code").val();
		if(code == null || code ==undefined || code == ''){
			alertLayel("状态值不可为空");
			return false;
		}
		return true;
	}
    $(function(){
    	if($(".menutr>td:last").text()!=null && $(".menutr>td:last").text()!=""){
    		$("#sort").val(parseInt($(".menutr>td:last").text())+1);
    	}
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
                                <td>名称</td>
                                <td>编码</td>
                                <td>状态名称</td>
                                <td>状态值</td>
                                <td>序号</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${codes}" var="item" varStatus="st">
                            <tr class="menutr">
                                <td>${item.dictName }</td>
                                <td>${item.dictType }</td>
                                <td>${item.dictCodeName }</td>
                                <td>${item.dictCodeValue }</td>
                                <td>${item.sort }</td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
    <form action="/status/saveCode.html" onsubmit="return check()"  method="post">
	<input type="hidden" name="dictType" id="dictType" value="${dictType }">
	<input type="hidden" name="sort" id="sort" value="1">
	<div class="pop addpop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle"><span id="spanTitle">新增状态</span><i class="closeicon"></i> </h3>
        <div class="row">
            <span class="addtypename">状态名称：</span>
            <input type="text" class="urlvalue" name="dictCodeName" id="name" value="">
        </div>
        <div class="row">
            <span class="addtypename">状态值：</span>
            <input type="text" class="urlvalue" id="code" name="dictCodeValue" value="">
        </div>
        <div class="row tc">
            <input type="submit" value="保存" class="delete">
        </div>
    </div>
</div>
</form>
</body>
</html>
