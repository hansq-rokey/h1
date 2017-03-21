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
    <script src="../../../js/area.js"></script>
	<script src="../../../js/location.js"></script>
    <title>钉钉管理</title>
</head>
<body>
    <section>
    	<div class="content" style="margin-top: 20px;">
        	<form action="/ding/department/users.html" method="post" id="selectForm">
        		<input type="hidden" name="year" value="${year }" id="year"/>
        		<input type="hidden" name="month" value="${month }" id="month"/>
	          	<div class="row">
                    <span class="selectinput plate">
                        <span class="selectvalue" id="yearTitle">年份</span>
                        <i class="arrow arrowright"></i>
                        <ul class="option">
                            <li data-id='2016' onclick="setYear(2016,'2016')">2016</li>
                            <li data-id='2017' onclick="setYear(2017,'2017')">2017</li>
                            <li data-id='2018' onclick="setYear(2018,'2018')">2018</li>
                            <li data-id='2019' onclick="setYear(2019,'2019')">2019</li>
                            <li data-id='2020' onclick="setYear(2020,'2020')">2020</li>
                        </ul>
                    </span>
                    <span class="selectinput plate">
                        <span class="selectvalue" id="monthTitle">月份</span>
                        <i class="arrow arrowright"></i>
                        <ul class="option">
                            <li data-id='1' onclick="setMonth(1,'1')">1</li>
                            <li data-id='2' onclick="setMonth(2,'2')">2</li>
                            <li data-id='3' onclick="setMonth(3,'3')">3</li>
                            <li data-id='4' onclick="setMonth(4,'4')">4</li>
                            <li data-id='5' onclick="setMonth(5,'5')">5</li>
                            <li data-id='6' onclick="setMonth(6,'6')">6</li>
                            <li data-id='7' onclick="setMonth(7,'7')">7</li>
                            <li data-id='8' onclick="setMonth(8,'8')">8</li>
                            <li data-id='9' onclick="setMonth(9,'9')">9</li>
                            <li data-id='10' onclick="setMonth(10,'10')">10</li>
                            <li data-id='11' onclick="setMonth(11,'11')">11</li>
                            <li data-id='12' onclick="setMonth(12,'12')">12</li>
                        </ul>
                    </span><input type="button" class="search" id="sub" value="搜索" style="width:100px;height:30px;background:#ff6200;color:#fff;"> 
                    	<c:if test="${departmentId == 0 }"><input type="button" class="search" id="export" value="导出" style="width:100px;height:30px;background:#ff6200;color:#fff;"> </c:if>
                </div>
	            <input type="hidden" value="${departmentId}" id="departmentId" name="departmentId">
	            <input type="hidden" name="pageNo" id="pageNo" value="">
	            
        	</form>
        </div>	
        <div class="inforbox selectinforbox" style="position: relative;top:0;">
            <div class="row">
                <table class="gradelist">
                    <thead>
                    <tr>
                        <td>序号</td>
                        <td>部门</td>
                        <td>姓名</td>
                        <td>职位</td>
                        <td>头像</td>
                        <td>本月实发</td>
                        <td>本月应发</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="item" varStatus="st">
                    <tr class="menutr">
                        <td>${st.count }</td>
                        <td>${item.orgNames }</td>
                        <td>${item.userName }</td>
                        <td>${item.position }</td>
                        <td><c:if test="${item.avatar!=null and item.avatar!=''}"><img alt="" src="${item.avatar}" width="30px" height="30px"/></c:if></td>
                        <td>${item.sendCount }</td>
                        <td>${item.shouldSendCount }</td>
                        <td>
                        	<a href="javascript:void(0);" data-id="${item.id }" class="link deletelink">设置部门</a>
                        	<a href="javascript:void(0);" data-id="${item.id }" data-name="${item.userName }" class="link setAdminUserlink">设置下属</a>
                        	<a href="/ding/department/users/workLog.html?userId=${item.id}&year=${year}&month=${month}">查看报告</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
<form action="/crm/info/save.html" onsubmit="return check()"  method="post">
<div class="pop addpop">
	<input type="hidden" id="userId" /> 
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle"><span id="spanTitle">选择部门</span><i class="closeicon"></i></h3>
        <div id="msg"></div>
        <div class="departments">
        
        </div>
    </div>
</div>
</form>
<!-- 设置人员先择-->
<div class="pop addrolepop">
    <div class="popbg"></div>
    <div class="layel" style="z-index: 999">
        <h3 class="addtitle"><span id="roleTitle">人员先择</span><i class="closeicon"></i></h3>
        <div class="row">
            <span class="personattr">管理员名称:</span>
            <input type="text" value="" id="adminName" class="adminName">
            <input type="hidden" id="adminId" name="adminId">
        </div>
        <div class="row">
            <p>人员</p>
        </div>
        <!-- 人员树页面 -->
	  	<div data-options="region:'center'" style="width: 100%;height: 300px;">
	       <iframe src=""  width="100%" height="99.5%" name="contentFrame" id="contentFrame">
	       </iframe>
	  	</div>
	  	<input type="button" class="addrole" onclick="saveAdminUser()" value="保存">
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var year = $("#year").val();
	if(year != null && year != ""){
		$('.option li').each(function(){
			var id=$(this).attr('data-id');
			if(year==id){
				$('#yearTitle').text($(this).text());
			}
		});
	}
	var month = $("#month").val();
	if(month != null && month != ""){
		$('.option li').each(function(){
			var id=$(this).attr('data-id');
			if(month==id){
				$('#monthTitle').text($(this).text());
			}
		});
	}
    $(document).on('click','.deletelink',function(){
    	var userId=$(this).attr("data-id");
    	$('#userId').val(userId);
    	$.ajax({
    		type:"get",
    		url: "/ding/ajax/department.html",
    		data:{userId:userId},
    		cache:false,
    		dataType:'json',
    		success: function(data){
           		var html='';
            	$.each(data.orgs,function(i,item){
            		var flag=0;
            		$.each(data.permisses,function(y,per){
            			if(per.orgId==item.id){
            				flag=1;
            			}
            		});
            		if(flag==1){
            			html+='<div class="row"><input type="checkbox" class="tt" value="'+item.id+'" checked="checked" /> '+item.orgName+'</div>';
            		}else{
            			html+='<div class="row"><input type="checkbox"  class="tt" value="'+item.id+'"/> '+item.orgName+'</div>';
            		}
            	});
	           $('.departments').html(html);
          }});
    	$('.addpop').show();
    });
    $(document).on('click','.setAdminUserlink',function(){
    	var adminId=$(this).attr("data-id");
    	var name=$(this).attr("data-name");
    	$('#adminId').val(adminId);
    	$('#adminName').val(name);
    	$('.addrolepop').show();
		showTree(adminId);
    });
    $(document).on('click','.tt',function(){
    	var checked=$(this).prop('checked');
    	var orgId=$(this).val();
    	var userId=$('#userId').val();
    	var flag=0;
    	if(checked){
    		flag=1;
    	}
    	$.ajax({
    		type:"get",
    		url: "/ding/ajax/update.html",
    		data:{userId:userId,orgId:orgId,flag:flag},
    		cache:false,
    		dataType:'json',
    		success: function(data){
    			$('#msg').text(data.message);
    			setTimeout('$("#msg").text("")', 1000);
          }});
    })
    $('#export').click(function(){
    	$('#selectForm').attr('action','<%=path%>/ding/department/users/exportExcel.html');
		$('#selectForm').submit();       
     });
    $('#sub').click(function(){
    	$('#selectForm').attr('action','<%=path%>/ding/department/users.html');
		$('#selectForm').submit();       
     });
})
function setYear(id,name){
	if(id != 0){
		$("#year").val(id);
		$('#yearTitle').text(name);
	}else{
		$("#year").val("");
		$('#yearTitle').text("");
	}
}
function setMonth(id,name){
	if(id != 0){
		$("#month").val(id);
		$('#monthTitle').text(name);
	}else{
		$("#month").val("");
		$('#monthTitle').text("");
	}
}
function showTree(id){
	var src = '<%=path%>/ding/toTree.html?adminId='+id;
	$('#contentFrame').attr('src',src);
}
function saveAdminUser(){
 		//调用子页面选中的
 		var models = window.frames["contentFrame"].getSelectModel();
 		var adminId = $('#adminId').val();
 		var url = "/ding/saveAdminUser.html";
 		$.ajax({
			   type: "POST",
			   url: url,
			   data:{"adminId":adminId,"privilegeids":models},
			   cache:false,
			   success: function(obj){
    				//保存成功点击查询按钮
    				//$('#searchbtn').click();
    				alert("设置成功");
			   }
		});
}
</script>
</body>
</html>
