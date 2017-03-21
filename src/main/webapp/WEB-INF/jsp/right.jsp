<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="title currenttitle">商品列表<i class="closeicon"></i> </h3>
<div class="pathbox">
    <span class="light">商品管理</span>><span class="darker">商品列表</span>
    <a href="../html/addprod.html"> <input type="button" class="addprod" value="+ 添加商品"></a>
</div>
<table class="prodlist">
    <thead>
    <tr>
        <td>产品</td>
        <td>状态</td>
        <td>单价</td>
        <td>库存</td>
        <td>操作</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>白熊牌电暖器</td>
        <td>状态</td>
        <td>单价</td>
        <td>库存</td>
        <td><a class="edit darker" href="#">编辑</a><a class="delete dark" href="#">删除</a>  </td>
    </tr>
    </tbody>
</table>
