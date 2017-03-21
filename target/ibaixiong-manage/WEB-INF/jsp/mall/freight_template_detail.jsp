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
    <script src="../../../plug_new/layer/layer.js" type="text/javascript" ></script>
    <title>模板详细信息列表</title>
    <script type="text/javascript">
    function deleteMethod(id,templateId){
    	layer.confirm('是否删除本行？', {
			  btn: ['确定','否'] //按钮
			},function(){
				$.ajax({
		    		url:"/mall/template/delete.html?id="+id,
		    		type:"POST",
		    		dataType:"JSON",
		    		success:function(data){
		    			if(data.success){
		    				window.location.href="/mall/template/list.html";
	    		  			layer.closeAll('dialog');
		    			}else{
		    				alertLayel("删除失败");
		    			}
		    		}
		    	});
			//layer.msg('删除成功', {icon: 1},{layer.closeAll('dialog')});
		})
    }
  </script>
</head>
<body>
    <section>
        <ul class="partlist" style="border-bottom: 0px;margin-top: -45px;">
            <li>
                <div class="inforbox selectinforbox">
                    <div class="row" style="padding: 50px 0px;margin: 0px;">
                    <input type="button" class="addlevel" onclick="add()" value="添加" style="display:none">
                        <table class="gradelist">
                            <thead>
                            <tr>
                                <td>模板名称</td>
                                <td>计价方式</td>
                                <td>邮寄省份</td>
                                <td>首重</td>
                                <td>首费</td>
                                <td>续重</td>
                                <td>续费</td>
                                <!-- <td>总运费</td> -->
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${values}" var="value">
                            <tr class="menutr">
                                <td>${value.freightTemplate.name }</td>
                                <c:if test="${value.valuationType==1 }">
                                	<td>按件数计算</td>
                                </c:if>
                                <c:if test="${value.valuationType==2 }">
                                	<td>按重量计算</td>
                                </c:if>
                                <td>
                                	<c:forEach items="${value.valueAreas }" var= "area">
                                		${area.provinceName } 
                                	</c:forEach>
                                </td>
                                <td>${value.numInner } ${value.unit }</td>
                                <td>￥${value.priceInner }</td>
                                <td>${value.numOut } ${value.unit }</td>
                                <td>￥${value.priceOut }</td>
                                <%-- <td>${value.priceAll }</td> --%>
                                <c:if test="${value.provinceName==null }">
                                	<td></td>
                                </c:if>
                                <c:if test="${value.provinceName!=null }">
                                	<td><a href="javascript:;" style="color:#2ca3e4;" onclick="deleteMethod(${value.id },${value.templateId })">删除</a></td>
                                </c:if>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
</body>
</html>
