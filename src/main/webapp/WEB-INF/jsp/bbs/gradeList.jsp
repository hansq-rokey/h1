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
    <title>通用管理</title>
    <script type="text/javascript">
	    function toPointsTypeList(){
			location.href = "/bbs/base/queryPointsTypeList.html";
		}
	    function toGradeList(){
			location.href = "/bbs/base/queryGradeList.html";
		}
	    function updatePoint(v,id){
			var r = /^\+?[1-9][0-9]*$/;//正整数
		    if(!r.test(v)){
		    	alertLayel("请输入正整数");
		    	return;
		    }
		    save("pointsNum",v,id);
		}
	    function updateExp(v,id){
			var r = /^\+?[1-9][0-9]*$/;//正整数
		    if(!r.test(v)){
		    	alertLayel("请输入正整数");
		    	return;
		    }
		    save("expNum",v,id);
		}
	    function updateActive(v,id){
			var r = /^\+?[1-9][0-9]*$/;//正整数
		    if(!r.test(v)){
		    	alertLayel("请输入正整数");
		    	return;
		    }
		    save("activeNum",v,id);
		}
		function save(pro,val,id){
			$.ajax({
  			   url: "/bbs/base/updatePointsType.html?"+pro+"="+val+"&id="+id,
  			   type: "post",
  			   dataType:"json",
  			   cache:false,
  			   async: false,
  			   success: function(obj){
  		  			if ( !checkCode( obj ) ) {
  						return;
  				    }else{
  				    	alertLayel("修改成功");
  				    }
  			   }
  			});
		}
		function addGrade(){
			$('.addgradepop').show();
		}
		function check(){
    		var name = $("#name").val();
    		if(name == null || name ==undefined || name == ''){
    			alertLayel("名称不可为空");
				return false;
			}
    		var file = $("#file").val();
    		if(file == null || file ==undefined || file == ''){
    			alertLayel("文件不可为空");
				return false;
			}
    		var expNum = $("#expNum").val();
    		if(expNum == null || expNum ==undefined || expNum == ''){
    			alertLayel("经验值不可为空");
				return false;
			}
    		return true;
    	}
    </script>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch" onclick="toPointsTypeList()">增长规则</span>
            </li>
            <li>
                <span class="switch switched" >等级管理</span>
                <div class="inforbox selectinforbox">
                    <div class="row">
                        <input type="button" class="addlevel" onclick="addGrade()" value="新增等级">
                        <table class="gradelist">
                            <thead>
                            <tr>
                                <td>等级</td>
                                <td>图标</td>
                                <td>经验值</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${gradeTypeList}" var="grade" varStatus="st">
                            <tr class="menutr">
                                <td>${grade.name }</td>
                                <td><img src="${grade.url }" class="levelgrade"> </td>
                                <td>${grade.expNum }</td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
<form action="/bbs/base/addGrade.html" onsubmit="return check()"  method="post" enctype="multipart/form-data">
<div class="pop addgradepop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">新增等级<i class="closeicon"></i> </h3>
        <div class="row">
            <span class="addtypename">等级名称：</span>
            <input type="text" class="addmodulename" name="name" id="name" value="">
            <span class="filebox"><input type="file" name="file" id="file" class="file"> <input type="button" class="uploadimg" value="上传图片"></span>
        </div>
        <div class="row">
            <span class="addtypename">经验值：</span>
            <input type="text" class="urlvalue" id="expNum" name="expNum" value="">
        </div>
        <div class="row tc">
            <input type="submit" value="保存" class="delete">
        </div>
    </div>
</div>
</form>
</body>
</html>
