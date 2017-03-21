<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <title>后台管理首页</title>
</head>
<body>
<div style="display: none;">
<form action="/mall/product/list.html" method="post">
 	<input type="hidden" name="pageNo" id="pageNo" value="">
    <input type="submit" id="pageSubmit" class="search" value="搜索">        	
</form>
</div>
    <section>
        <div class="pathbox">
            <span class="light">商品管理</span>><span class="darker">商品列表</span>
            <a href="/mall/product/toadd.html"> <input type="button" class="addprod" value="+ 添加商品"></a>
        </div>
        <table class="prodlist">
            <thead>
            <tr>
                <td>产品名称</td>
                <td>产品品类</td>
                <td>产品型号</td>
                <td>产品状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list }" var="product">
            	<tr>
	                <td>${product.title }</td>
	                <td>${product.basicCategory.name }</td>
	                <td>${product.basicCategoryModel.code }</td>
	                <td>
	                <c:forEach items="${proStatusList }" var="dict">
	                	<c:if test="${dict.dictCodeValue==product.status }">${dict.dictCodeName }</c:if>
	                </c:forEach>
	                </td>
	                <td><a class="link edit darker" href="/mall/product/toupdate.html?id=${product.id }">详情</a><a class="link delete dark" href="/mall/product/delete.html?id=${product.id }">删除</a>  </td>
	            </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
    <jsp:include page="../include/pages_old.jsp"></jsp:include>
</body>
</html>