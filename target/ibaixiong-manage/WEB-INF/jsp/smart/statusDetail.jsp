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
    function Add(type,_this){
    	if(type ==1){
    		$("#spanTitle").html("新增状态");
    		$('.addpop').show();
    	}
    	if(type ==2){
   			var thisId=_this.attr("data-id");//当前data-id
   			var thisname=_this.attr("data-name");//当前状态名称
   			var thisval=_this.attr("data-val");//当前状态值
   			var tName="";
   			if(thisname == "temperatureUp"){
   				tName=_this.parents(".menutr").next().children('td').eq(3).text();
   			}else if(thisname == "temperatureDown"){
   				tName=_this.parents(".menutr").prev().children('td').eq(3).text();
   			}else{
   				tName=_this.siblings('.state_value').text();
   			}
   			console.log(thisId);
   			$("#editor_id").val(thisId);
   			$(".editor_name").text(thisname);
   			$(".editor_urlvalue").val(thisval);
   			$('.editor_addpop').show();
   			$("#thisName").val(tName);
    	}
	}
    function editor_check(){
    	var value=$(".editor_urlvalue").val();
    	if(value == null || value ==undefined || value == ''){
			alertLayel("状态值输入有误");
			return false;
		}
    	return true;
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
    //状态值编辑
    function editor_state(_this){
   		 var startVal=parseInt($("#thisName").val());//最大或者最小值 
   		 var thisVal=_this.val();//输入值
   		 console.log(thisVal);
		 var thisName=_this.parents(".row").prev().children(".editor_name").text();
		 if(thisName == "temperatureUp"){
			 if(!isNaN(thisVal) && (thisVal >= startVal && thisVal <= 28) ){
				 return true;
			 }else{
				 alertLayel("状态值范围在28以内");
				 _this.val("");
				 return false;
			 }
		 }else if(thisName == "temperatureDown"){
			if(!isNaN(thisVal) && (thisVal >= 10 && thisVal <= startVal)){ 
				 return true;
			 }else{
				 alertLayel("状态值范围在10到最大值之间");
				 _this.val("");
				 return false;
			 }
		 }else{
			 if(!isNaN(thisVal) && (thisVal ==0 || thisVal ==1)){
				 return true;
			 }else{
				 alertLayel("状态值是0或1");
				 _this.val("");
				 return false;
			 }
		}
    }
    $(function(){
    	if($(".menutr:last").find("td").eq(4).text()!=null && $(".menutr:last").find("td").eq(4).text()!=""){
    		$("#sort").val(parseInt($(".menutr:last").find("td").eq(4).text())+1);
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
                    <input type="button" class="addlevel" onclick="Add(1)" value="添加">
                        <table class="gradelist">
                            <thead>
                            <tr>
                                <td>名称</td>
                                <td>编码</td>
                                <td>状态名称</td>
                                <td>状态值</td>
                                <td>序号</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${codes}" var="item" varStatus="st">
                            <tr class="menutr">
                                <td>${item.dictName }</td>
                                <td>${item.dictType }</td>
                                <td class="state_Name">${item.dictCodeName }</td>
                                <td class="state_value">${item.dictCodeValue }</td>
                                <td>${item.sort }</td>
                                <td class="state_editor" onclick="Add(2,$(this))" data-id="${item.id}" data-name="${item.dictCodeName }"  data-val="${item.dictCodeValue}">编辑</td>
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
  <form action="/smart/intelligence/update.html" method="post" onsubmit="return editor_check()">
	<input type="hidden" id="thisName" value="">
	<input type="hidden" name="editor_id" id="editor_id" value="">
	<div class="pop editor_addpop">
	    <div class="popbg"></div>
	    <div class="layel">
	        <h3 class="poptitle"><span id="spanTitle">编辑状态</span><i class="closeicon"></i> </h3>
	        <div class="row">
	            <span class="addtypename">状态名称：</span>
	          	<p class="editor_name"></p>
	        </div>
	        <div class="row">
	            <span class="addtypename">状态值：</span>
	            <input type="text" class="editor_urlvalue" onchange="editor_state($(this))" id="code" name="dictCodeValue" value="">
	        </div>
	        <div class="row tc">
	            <input type="submit" value="保存" class="delete">
	        </div>
	    </div>
	</div>
</form>
</body>
</html>
