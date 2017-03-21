<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/addcustomer.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../plug/adddatetime.js" type="text/javascript"></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>客诉管理</title>
    <script type="text/javascript">
	    function toAdd(){
			$('.addgradepop').show();
		}
	    function check(){
	    	var type = $('#typeName').val();
	    	if(type == null || type ==undefined || type == ''){
	    		alertLayel("名称不可为空");
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
                <div class="inforbox selectinforbox">
                    <div class="row">
                        <input class="addcustypebtn" value="新增类型" type="button" onclick="toAdd()">
                    </div>
                    <div class="row">
                        <table>
                            <thead>
                            <tr><td>客诉类型</td></tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${typeList}" var="ty" varStatus="st">
	                            <tr>
	                                <td style="text-indent: 30px;">${ty.name }</td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
<form action="/ccm/type/save.html" onsubmit="return check()" method="post">
<div class="pop addgradepop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">新增类型<i class="closeicon"></i> </h3>
        <div class="row">
            <span class="addtypename">类型名称：</span>
            <input type="text" class="addmodulename" name="name" id="typeName">
        </div>
        <div class="row tc">
           	<input type="submit" value="确认" class="savebtn">
        </div>
    </div>
</div>
</form>
</body>
</html>
