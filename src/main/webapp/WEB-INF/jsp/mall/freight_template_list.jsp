<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
     <link href="../../../css/fre_template.css" rel="stylesheet" type="text/css">
     <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
     <script src="../../../js/freight_template.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <script src="../../../js/area1.js" type="text/javascript" ></script>
    <script src="../../../js/location.js" type="text/javascript" ></script>
    <script src="../../../plug_new/layer/layer.js" type="text/javascript" ></script>
    <title>运费模板列表</title>
</head>
<body>
	<div style="display: none;">
		<form action="/mall/template/list.html" method="post">
 			<input type="hidden" name="pageNo" id="pageNo" value="">
    		<input type="submit" id="pageSubmit" class="search" value="搜索">        	
		</form>
	</div>
    <section>
        <ul class="partlist" style="border-bottom: 0px;margin-top: -45px;">
            <li>
                <div class="inforbox selectinforbox">
                    <div class="row">
                    	<input type="button" class="addlevel" onclick="add()" value="添加">
                        <table class="gradelist">
                            <thead>
                            <tr>
                            	<td>序号</td>
                                <td>模板名称</td>
                                <td>创建时间</td>
                                <td>邮寄方式</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${templates}" var="template" varStatus="status">
                            <tr class="menutr">
                            	<td>${status.count }</td>
                                <td>${template.name }</td>
                                <td><fmt:formatDate value="${template.createDateTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                                <c:if test="${template.sendType==10 }">
                                	<td>全国包邮</td>
                                	<td><%-- <a class="link deletelink" onclick="update(${template.id})" style="color: blue;">编辑</a> --%></td>
                                </c:if>
                                <c:if test="${template.sendType==20 }">
                                	<td>自定义</td>
                                	<td>
                                		<a class="link deletelink" href="/mall/template/detail.html?templateId=${template.id }" >详情</a>
                                		<a class="link deletelink" style="color:#2ca3e4" onclick="update(${template.id})">编辑</a>
                                		<a class="link deletelink" onclick="deleteTemplate(${template.id})">删除</a>
                                	</td>
                                </c:if>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </div>
            </li>
        </ul>
    </section>
    <input type="hidden" name="id" id="id">
	<div class="pop addpop">
    <div class="popbg"></div>
    <div class="layel-template">
        <h3 class="poptitle"><span id="spanTitle">新增运费模板</span><i class="closeicon" onclick="closeMethod()"></i> </h3>
        <div class="row">
            <span class="addtypename">模板名称：</span>
            <input type="text" class="urlvalue" name="name" id="name" value="">
        </div>
        <div class="row">
            <span class="addtypename">包邮方式：</span>
            <input type="radio" id="checkbox1" name="status" value="10" class="regular-checkbox" onclick="show(this)" style="display:none"/><label for="checkbox1" style="display:none">全国包邮</label>
            <input type="radio" id="checkbox2" name="status" checked value="20" class="regular-checkbox" onclick="show(this)" /><label for="checkbox2">自定义</label> 
        </div>
        <div class="p_row">
            <span class="addtypename">计价方式：</span>
            <input type="radio" id="checkbox3" name="type" checked="checked" value="1" class="regular-checkbox isprofit isprofiton" onclick="show(this)"/><label for="checkbox3">按件数</label>
            <input type="radio" id="checkbox4" name="type" value="2" class="regular-checkbox isprofit" onclick="show(this)"/><label for="checkbox4">按重量</label>
        </div>
        <div class="s_row">
            <span class="addtypename">计价公式：</span><span class="text_p">除指定地区外，其他地区的运费采用“默认运费”</span>
        </div>
        <div class="n_row">
      	  <div class="n-rowtag">
   	     		<span>默认运费：</span><input type="text" class="urlvalue" name="numInnerValue" id="numInnerValue" value=""/> 件内，
     		  	<input type="text" class="urlvalue" name="priceInnerValue" id="priceInnerValue" value=""/> 元；
               	每增加 <input type="text" class="urlvalue" name="numOutValue" id="numOutValue" value=""/> 件，
	        	增加运费 <input type="text" class="urlvalue" name="priceOutValue" id="priceOutValue" value="" /> 元；
	        	<span class="n-row-add " onclick="appendHtml(this)">确认</span>
        	</div>
        	<table>
        		<tr style="background:#ccc">
        			<td>运送到</td>
        			<td>首重（件）</td>
        			<td>首费（元）</td>
        			<td>续重（件）</td>
        			<td>续费（元）</td>
        			<td>操作</td>
        		</tr>
        	</table>
        	<span class="add_template"><a href="javascript:;" onclick="addArea(this)">新增指定区域运费</a></span>
        </div>
        <div class="m_row">
        	<div class="n-rowtag">
        		<span>默认运费：</span><input type="text" class="urlvalue" name="numInnerValue" id="numInnerValue" value=""/> kg内，
        		<input type="text" class="urlvalue" name="priceInnerValue" id="priceInnerValue" value=""/> 元；
        		每增加 <input type="text" class="urlvalue" name="numOutValue" id="numOutValue" value=""/> kg，
        		增加运费 <input type="text" class="urlvalue" name="priceOutValue" id="priceOutValue" value=""/> 元；
        		<span class="n-row-add " onclick="appendHtml(this)">确认</span>
        	</div>
        	<table>
        		<tr style="background:#ccc">
        			<td>运送到</td>
        			<td>首重（kg）</td>
        			<td>首费（元）</td>
        			<td>续重（kg）</td>
        			<td>续费（元）</td>
        			<td>操作</td>
        		</tr>
        	</table>
        	<span class="add_template"><a href="javascript:;" onclick="addArea(this)">新增指定区域运费</a></span>
        </div>
        <div class="row tc">
            <input type="submit" value="保存" class="delete" onclick="submitData()">
        </div>
    </div>
	</div>
	<input type="hidden" name="m_name" id="m_name" value=""/>
	<!-- <input type="hidden" name="d_name" id="d_name" value=""/> -->
	<input type="hidden" id="location_id" name="location_id" />
</body>
	<script type="text/javascript">
		$(function(){
        	showLocation();
    	});
	</script>
</html>