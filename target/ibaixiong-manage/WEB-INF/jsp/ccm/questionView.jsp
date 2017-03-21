<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/customerlist.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>客诉管理</title>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	setUser();
	    	setRadioCheck();
	    });
	    function setUser(){
	    	$.ajax({
		 		   url: "/system/admin/getAdminListByRole.html?roleId=5",
		 		   type: "POST",
		 		   dataType:"json",
		 		   cache:false,
		 		   success: function(obj){
		 			  if ( !checkCode( obj ) ) {
		 					return;
		 			  }
		 			  //获取数据 生成菜单部分
		 			  var html = "<i class='arrowtop'></i>";
		 			  var data = obj.result.admins;
		 			  for(var o in data){
		 				 html = html+ "<li onclick='setUserVal("+data[o].id+")' id='liu"+data[o].id+"' _name='"+data[o].name+"'>"+data[o].name+"</li>";
		 			  }
		 			  $("#userU").append(html);
		 		   }
	   		});
	    }
	    var setUserVal=function(id){
	  	   var _name = $("#liu"+id).attr("_name");
	  	   $("#userSel").text(_name);
	  	   $("#adminId").val(id);
	  	   $('#userU').fadeOut(10);
	    }
    	function toList(type){
    		location.href = "/ccm/question/queryList.html?queryType="+type;
    	}
    	function setRadioCheck(){
    		var rateV = $("#rateV").val();
    		if(rateV>0){
    			$("#rate"+rateV).attr("checked","checked");
    		}
    	}
    	function check(){
    		var  rate = $('input[name="rate"]:checked').val(); 
        	if( rate == null || rate  ==undefined || rate  == ''){
        		alertLayel("请选择进度");
        		return false;
        	}
        	
        	var adminId = $('#adminId').val(); 
        	if(rate != 3){
	        	if( adminId == null || adminId  ==undefined || adminId  == ''){
	        		alertLayel("请选择指派人员");
	        		return false;
	        	}
        	}
        	var memo = $('#memo').val(); 
        	if( memo == null || memo  ==undefined || memo  == ''){
        		alertLayel("请填写处理意见");
        		return false;
        	}
        	return true;
    	}
    </script>
</head>
<body>
<form action="/ccm/question/saveQuestionProcess.html" onsubmit="return check()" method="post">
	<input type="hidden" name="queryType" value="${queryType}">
	<input type="hidden" name="id" value="${question.id}">
	<input type="hidden" id="rateV" value="${question.rate}">
	<input type="hidden" name="selAdmin" value="" id="adminId"/>
    <section>
        <ul >
            <li>
                <div class="inforbox selectinforbox" style="margin-top: -30px;">
                    <div class="row">
                        <span class="light"><a href="#" onclick="toList(${queryType})">客户反馈</a>></span>客诉详情
                    </div>
                    <div class="row custinforp">
                        <p class="infortitle" style="font-weight:800;">基本信息</p>
                        <div class="row">
	                        <div class="col-lg-2">客户姓名:</div>
	                        <div class="col-lg-10 ">${question.coustomerName }</div>
                        </div>
                        <div class="row">
                        <div class="col-lg-2">称呼:</div>
                        <div class="col-lg-10 "> ${question.callMe }</div>
                        </div>
                        <div class="row">
                        <div class="col-lg-2">联系方式:</div>
                        <div class="col-lg-10 "> ${question.tel }</div>
                        </div>
                        <div class="row">
                        <div class="col-lg-2">客诉时间:</div>
                        <div class="col-lg-10 "><fmt:formatDate value="${question.ccTime }" pattern="yyyy/MM/dd HH:mm:ss"/></div>
                        </div>
                        <div class="row">
                        <div class="col-lg-2">指派时间:</div>
                        <div class="col-lg-10 "><fmt:formatDate value="${question.assignTime }" pattern="yyyy/MM/dd HH:mm:ss"/> </div>
                        </div>
                        <div class="row">
                        <div class="col-lg-2">客诉类型:</div>
                        <div class="col-lg-10 "> ${question.ccmType.name }</div>
                        </div>
                        <div class="row">
                        <div class="col-lg-2">客服描述:</div>
                        <div class="col-lg-10 ">${question.csMemo } </div>
                        </div>
                        <div class="row">
                        <div class="col-lg-2">客户描述:</div>
                        <div class="col-lg-10 "> ${question.customersMemo }</div>
                        </div>
                        <div class="row">
                        <div class="col-lg-2">指派人:</div>
                        <div class="col-lg-10 ">${question.admin.userName } </div>
                        </div>
                    </div>
                    <div class="row custinforp">
                        <p class="infortitle" style="font-weight:800;">处理情况</p>
                        <div class="row">
                        <div class="col-lg-2">处理进度:</div>
                        <div class="col-lg-10 ">
                            <span class="checkspan"><input type="radio" name="rate" id="rate1" value="1" class="checkradio">30% </span>
                            <span class="checkspan"><input type="radio" name="rate" id="rate2" value="2" class="checkradio">60% </span>
                            <span class="checkspan"><input type="radio" name="rate" id="rate3" value="3" class="checkradio">100% </span>
                        </div>
                    	</div>
                        <div class="row">
                        <div class="col-lg-2" style="margin-top:13px;">指派人员:</div>
                        <div class="col-lg-10 ">
                            <span class="selectinput">
                                <span class="selectvalue" id="userSel">-选择-</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option" id="userU">
	                            </ul>
                            </span>
                        </div>
                    	</div>
                        <div class="row">
                        <div class="col-lg-2">处理记录:</div>
                        <div class="col-lg-10 ">
                        	<c:forEach items="${processList }" var="process" varStatus="qv">
                        		<p><fmt:formatDate value="${process.createDateTime }" pattern="yyyy/MM/dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;${process.admin.userName }&nbsp;&nbsp;&nbsp;&nbsp;${process.memo}</p>
                        	</c:forEach>
                        </div>
                    	</div>
                    </div>
                    <div class="row">
                        <textarea class="custtext" name="memo" id="memo"></textarea>
                    </div>
                    <div class="row">
                    	<c:if test="${question.processStatus == 2}">
                        	<input type="submit" class="savebtn" value="保存">
                        </c:if>
                    </div>
                </div>
            </li>
        </ul>
    </section>
</form>
</body>
</html>