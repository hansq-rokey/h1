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
    </script>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch switched">增长规则</span>
                <div class="inforbox selectinforbox">
                    <div class="row">
                        <table class="riselist">
                            <thead>
                            <tr>
                                <td>类型</td>
                                <td>积分</td>
                                <td>经验</td>
                                <td>活跃度</td>
                                <td>上限值</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pointsTypeList}" var="pointsType" varStatus="st">
	                            <tr>
	                                <td>${pointsType.pointsName }</td>
	                                <td><input type="text" value="${pointsType.pointsNum}" onchange="updatePoint(this.value,${pointsType.id })" class="pointtext"> </td>
	                                <td><input type="text" value="${pointsType.expNum}" onchange="updateExp(this.value,${pointsType.id })" class="exptext"></td>
	                                <td><input type="text" value="${pointsType.activeNum}" onchange="updateActive(this.value,${pointsType.id })" class="acttext"></td>
	                                <td>${pointsType.opCounts}</td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
            <li>
                <span class="switch" onclick="toGradeList()">等级管理</span>
            </li>
        </ul>
    </section>
</body>
</html>
