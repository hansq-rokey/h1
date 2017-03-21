<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function logout(){
		location.href = "/logout.html";
	}
</script>

<div class="left-part">
    <div class="left-top">
        <div class="user-infor">
            <img src="/images_new/pic.jpg" class="user-image">
            <div class="infor-box">
                <span>白熊ERP系统</span>
                <span class="user-name"><span class="name-value">${admin.userName}</span><i class="user-arrow"></i></span>
            </div>
            <div class="_botselect">
                <ul><a href="#" onclick="logout()">注销</a></ul>
            </div>
        </div>
        <div class="new-date" style="display:none">
            <div class="date-item">
                <span class="date-num">21</span>
                <span class="date-late">新订单</span>
            </div>
            <div class="date-item">
                <span class="date-num">21</span>
                <span class="date-late">新订单</span>
            </div>
            <div class="date-item">
                <span class="date-num">21</span>
                <span class="date-late">新订单</span>
            </div>
        </div>
    </div>
    
	<div class="left-bottom">
		<c:forEach items="${sessionScope.models}" var="models">
			<h3>${models.name}<i class="arrow"></i></h3>
			<ul>
				<c:if test="${models.childList != null }">
		    		<c:forEach items="${models.childList}" var="modelsChild">
		    		<c:if test="${modelsChild.name != '4s店管理' }">
		    			<li class="curLi"><a href="${modelsChild.url }">${modelsChild.name}</a><span class="item-event">0</span></li>
		    		</c:if>
			        </c:forEach>
		        </c:if>
			</ul>
		</c:forEach>
	</div>
    <div class="_indent">
      <span class="_indenta"></span>
    </div>
</div>