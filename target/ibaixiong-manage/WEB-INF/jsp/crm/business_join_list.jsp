  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="/plug_new/jQuery/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/pubstyle.css"/>
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/daterangepicker-bs3.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/common.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/puts.css" />
    <title>招商管理</title>
    <script type="text/javascript">
    	function showPage(number){
        	var daterange = $("#daterange").val();
        	var status = $("#status").val();
        	window.location.href="/crm/business/list.html?daterange="+daterange+"&status="+status+"&pageNo="+number;
    	}
    	function exportText(){
        	var daterange = $("#daterange").val();
        	var status = $("#status").val();
        	window.location.href="/crm/business/export.html?daterange="+daterange+"&status="+status;
    	}
    </script>
</head>
<body>
<jsp:include page="../include/left_new.jsp"></jsp:include>
<div class="right-part">
    <div class="right-top">
        <div class="input-list">
        	<div class="setDateBox">
          		<input type="text" id="daterange" name="daterange" class="daterange" value="${dateTime }">
            	<i class="select-arrow"></i>
            	<i class="date-icon"></i>
        	</div>
        	<span class="search-icon ripple-event" onclick="showPage(1)"></span>
        	<input type="hidden" value="${status }" id="status"/>
        	<button  class="export" id="export" onclick="exportText()">导出</button>
        </div>
        <div class="tab-list" style="position:static;">
            <ul>
                <li class="${status==null?'on':'' } ripple-event"><a href="/crm/business/list.html">全部</a></li>
                <li class="${status==1?'on':'' } ripple-event"><a href="/crm/business/list.html?status=1">未读</a></li>
                <li class="${status==2?'on':'' } ripple-event"><a href="/crm/business/list.html?status=2">已读</a></li>
            </ul>
        </div>
    </div>
    <div class="right-bottom">
        <div class="content ">
         <div class="creatData">
           <div class="creatData_list">姓名</div>
           <div class="creatData_list">联系电话</div>
           <div class="creatData_list">意向城市</div>
           <div class="creatData_list">投资金额</div>
           <div class="creatData_list">备注</div>
           <div class="creatData_list">来源</div>
           <div class="creatData_list">创建时间</div>
           <div class="creatData_list">广告来源</div>
           <div class="creatData_list">着落页</div>
           <div class="creatData_list">广告系列</div>
           <div class="creatData_list">操作</div>
         </div>
         <c:forEach items="${pageInfo.list }" var="item" varStatus="vs">
          <div class="creatData_content">
            <div class="creatData_list"><p class="only-line bold">${item.name }</p></div>
            <div class="creatData_list"><span class="creattel">${item.tel}</span></div>
            <div class="creatData_list">${item.cities }</div>
            <div class="creatData_list">${item.investMoney }</div>
            <div class="creatData_list creatData_hover "><a href="###" class="text_hidden" title="${item.remark }">${item.remark }</a></div>
            <div class="creatData_list">
              <p class="only-line">
                 <c:if test="${item.origin==1 }">移动端</c:if>
                 <c:if test="${item.origin==2 }">网站</c:if>
              </p>
            </div>
            <div class="creatData_list">
              <p class="only-line"><fmt:formatDate value="${item.createDateTime }" pattern="yyyy-MM-dd"/> 
            </div>
            <div class="creatData_list">
              <p class="only-line">
              <c:forEach items="${dictCodes}" var="code">
              	<c:if test="${item.adType==code.dictCodeValue }">${code.dictCodeName }</c:if>
              </c:forEach>
              <c:if test="${item.adType==null or item.adType==0 }">自然</c:if>
              </p>
            </div>
            <div class="creatData_list">${item.pageValue }</div>
            <div class="creatData_list">${item.advertValue }</div>
            <div class="creatData_list">
              <p class="only-line">
                <a class="blue delete" data-type="1" href="javascript:void(0);" data-id="${item.id }">删除</a>
                <c:if test="${item.status==1 }">
                  <a class="blue read" data-type="2" href="javascript:void(0);" data-id="${item.id }">已读</a>
                </c:if>
              </p>
            </div>
          </div>
         </c:forEach>
        </div>
      <div style="display: none;">
        <form action="/crm/business/list.html" method="post">
            <input type="hidden" name="pageNo" id="pageNo" value="">
            <input type="hidden" name="status" id="status" value="${status }">
            <input type="submit" id="pageSubmit" class="search" value="搜索">         
        </form>
      </div>
    <jsp:include page="../include/pages_new.jsp"></jsp:include>
    </div>
</div>
<script type="text/javascript" src="/plug_new/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/moment.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="/plug_new/laypage/laypage.js"></script>
<script type="text/javascript" src="/js_new/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  var ajaxFn=function(type,data,url,fn){
    $.ajax({
      url:url,
      data:data,
      dataType:'json',
      success:function(data){
        window.location.reload();
      }
    });
  }
  $('.read').on('click',function(){
    var id=$(this).attr('data-id');
    ajaxFn('POST',{id:id},'/crm/business/read.html');
  });
  $('.delete').on('click',function(){
    var id=$(this).attr('data-id');
    ajaxFn('POST',{id:id},'/crm/business/delete.html');
  });
 /*  /*超出文字预览*/
 /*  $(".creatData_hover").hover(function() {
	    var that=$(this);
	    var thatvalue=that.children('span').text();
	    var valuelength=thatvalue.length;
	    if(valuelength > 8){
    	 var content='<p class="text_css">'+thatvalue+'</p>'
	     that.append(content);	
	    }
	  }, function() {
	   $(".creatData_hover").children('p').remove();
	  }); */
});
</script>
</body>