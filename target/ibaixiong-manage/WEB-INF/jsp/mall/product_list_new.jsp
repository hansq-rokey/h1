<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/daterangepicker-bs3.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/common.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/puts.css" />
    <script src="/plug_new/jQuery/jquery-1.9.1.min.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/bootstrap.min.js" type="text/javascript" ></script>
	<script src="/plug_new/bootstrap/moment.js" type="text/javascript"></script>
	<script src="/plug_new/bootstrap/daterangepicker.js" type="text/javascript"></script>
	<script src="/plug_new/laypage/laypage.js" type="text/javascript"></script>
	<script src="/js_new/common.js" type="text/javascript" ></script>
    <title>出库列表</title>
</head>
<body>
<div class="city_box">
<jsp:include page="../include/left_new.jsp"></jsp:include>
 <a  target= _blank href="/mall/product/toadd.html"><div class="addBtn" ><i></i></div></a>
<div class="right-part">
    <div class="right-top">
        <div class="tab-list" style="margin-top:170px;left:60px">
            <ul>
                <li class="${status==1?'on':'' } ripple-event"><a  class="a_click" href="/mall/product/list.html">已上线</a></li>
                <li class="${status==-1?'on':'' } ripple-event"><a class="a_click" href="/mall/product/list.html?status=-1">已下线</a></li>
                <li class="${status==2?'on':'' } ripple-event"><a class="a_click" href="/mall/product/list.html?status=2">已删除</a></li>
            </ul>
        </div>
    </div>
    <input type="hidden" name="pageNo" id="pageNo" value="">
    <input type="hidden" name="status" id="status" value="${status }">
    <div class="right-bottom">
        <div class="content ">
	       	 <table class="add_content add_cityMerchant">
		    		<thead>	
			    		<tr>
			    			<th>产品类型</th>
			    			<th>产品名称</th>
			    			<th>产品型号</th>
			    			<th>产品状态</th>
			    			<th>操作</th>
			    		</tr>
			    	</thead>
			    		<tbody>	
			    		<c:forEach items="${list }" var="item" varStatus="vs">
				    		<tr class="${vs.count%2==0?'even':'' }">
				    			<td> <p class="only-line bold">${item.basicCategory.name }</p></td>
				    			<td> <p class="red only-line bold table_w">${item.title }</p></td>
				    			<td> <p class="only-line">${item.basicCategoryModel.code }</p></td>
				    			<td>
				    				<p class="only-line">
			                    	<c:forEach items="${proStatusList }" var="dict">
					                	<c:if test="${dict.dictCodeValue==item.status }">${dict.dictCodeName }</c:if>
					                </c:forEach>
			                    </p>
				    			</td>
				    			<td>
				    			<p class="only-line">
				    				<c:choose>
				    					<c:when test="${item.status==1 }">
				    						<a class="blue" data-type="1" href="/mall/product/edit.html?id=${item.id }">编辑</a>
			                    			<a class="blue " data-type="2"  href="/mall/product/update/status.html?id=${item.id }">下架</a>
				    					</c:when>
				    					<c:when test="${item.status==-1 }">
				    						<a class="blue" data-type="1" href="/mall/product/edit.html?id=${item.id }">编辑</a>
			                    			<a class="blue " data-type="2"  href="/mall/product/update/status.html?id=${item.id }">上架</a>
			                    			<a class="blue " data-type="2" onclick="if(confirm('确定删除?')==false)return false;" href="/mall/product/delete.html?id=${item.id }">删除</a>
				    					</c:when>
				    					<c:otherwise>
				    					
				    					</c:otherwise>
				    				</c:choose>
			                    </p>
				    			</td>
				    		</tr>
			    		</c:forEach>
		    		</tbody>	
		    	</table>
		    <%-- <jsp:include page="../include/pages_new.jsp"></jsp:include> --%>
        </div>
        <c:if test="${pager.pages>1 }">
			<div class="list-page">
				<c:if test="${pager.hasPreviousPage }">
					<a class="prev" href="${url }${pager.pageNumber-1}"></a>
				</c:if>
				<c:if test="${pager.start-1>0 }">
					<a href="${url }1">1</a>
				</c:if>
				<c:if test="${pager.hasBeforPoint }">
					...
				</c:if>
				<div class="list-page-num">
					<c:forEach items="${pager.navigatePageNumbers }" var="item">
						<a class="${item==pager.pageNumber?'current':'' }" href="${url }${item }">${item }</a>
					
					</c:forEach>
				</div>
				<c:if test="${pager.hasAfterPoint }">
					...
				</c:if>
				<c:if test="${ pager.hasNextPage}">
					<a class="next" href="${url }${pager.pageNumber+1}"></a>						
				</c:if>
			</div>
		</c:if>
		
    </div>
    <!-- 新增出库-->
   <!--  <div class="pop addStockExportPop">
        <div class="popBg"></div>
        <div class="smallLayel">
            <h3>新增出库</h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-item">
                        <p class="topItem">一级类目</p>
                        <div class="selectinput selectLong">
                            <i class="select-arrow"></i>
                            <span class="selectvalue"></span>
                            <ul class="opation">
                                <li>下拉一下拉一下拉一</li>
                                <li>下拉er下拉er下拉er</li>
                                <li>下拉三下拉三下拉三</li>
                                <li>下拉四下拉四下拉四</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-item">
                        <p class="topItem">物料名称</p>
                        <div class="selectinput selectLong">
                            <i class="select-arrow"></i>
                            <span class="selectvalue"></span>
                            <ul class="opation">
                                <li>下拉一下拉一下拉一</li>
                                <li>下拉er下拉er下拉er</li>
                                <li>下拉三下拉三下拉三</li>
                                <li>下拉四下拉四下拉四</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-item">
                        <p>数量</p>
                        <input type="text" value="">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-item">
                        <p>备注</p>
                        <input type="text" value="">
                    </div>
                </div>
            </div>
            <div class="row tc">
                <span class="popSure ripple-event">确认</span>
                <span class="popCancel">取消</span>
            </div>
        </div>
    </div> -->
	<div style="display: none;">
		<form action="/mall/product/list.html" method="post">
		 	<input type="hidden" name="pageNo" id="pageNo" value="">
		 	<input type="hidden" name="status" id="status" value="${status }">
		    <input type="submit" id="pageSubmit" class="search" value="搜索">        	
		</form>
	</div>
     <jsp:include page="../include/pages_new.jsp"></jsp:include>
	</div>
</div>
</body>
</html>
