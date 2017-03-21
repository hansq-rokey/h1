<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="/plug_new/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/plug_new/bootstrap/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
    <link href="/css_new/common.css" rel="stylesheet" type="text/css">
    <link href="/css_new/adress.css" rel="stylesheet" type="text/css">
    <link href="/css_new/addCity.css" rel="stylesheet" type="text/css"/>
    <script src="/plug_new/jQuery/jquery-1.9.1.min.js" type="text/javascript" ></script>
    <script src="/plug_new/bootstrap/bootstrap.min.js" type="text/javascript" ></script>
    <script src="/plug_new/bootstrap/moment.js" type="text/javascript" ></script>
    <script src="/plug_new/bootstrap/daterangepicker.js" type="text/javascript" ></script>
    <script src="/js_new/common.js" type="text/javascript" ></script>
    <script type="text/javascript" src="/js_new/area.js"></script>
    <script type="text/javascript" src="/js_new/location.js"></script>
    <script src="/js_new/addCity.js" type="text/javascript" ></script>
    <script src="/js_new/base.js" type="text/javascript" ></script>
    <title>APP应用列表</title>
    <script type="text/javascript">
    function showList(){
        window.location.href="/app/info/list.html";
    }
    function showPage(number){
        window.location.href="/app/info/list.html?pageNo="+number;
    }
    </script>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<div class="addBtn"><i></i></div>
<div class="right-bottom">
    <div class="right-top" style="left: 280px;">
        <input type="hidden" name="pageNo" id="pageNo" value="">
    </div>
    <div class="a_content">
     <div class="tab-list">
            <ul>
                <li class="money_on  ripple-event" onclick="showList()">正常</li>
                <!-- <li class="ripple-event" onclick="disShowList()">禁用</li> -->
            </ul>
        </div>
            <table class="add_content add_cityMerchant">
                <tr>
                    <th>中文名称</th>
                    <th>英文名称</th>
                    <th>简写</th>
                    <th>添加时间</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.appName}</td>
                    <td>${item.appEnglishName}</td>
                    <td>${item.appAbbreviation }</td>
                    <td>
                        <fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <a class="blue add-margin" href="/app/log/list.html?name=${item.appAbbreviation }">日志列表</a>
                        
                    </td>
                </tr>
                </c:forEach>
            </table>
            <jsp:include page="../include/pages_new.jsp"></jsp:include>
      </div>
    </div> 
</div>
<div class="add_layer add_layer1" style="display:none">
    <div class="layer_content">
      <h3 id="add_1">新增</h3>    
      <div class="mian_content">
        <div class="add_right" style="padding:10px;">
          <div class="add_body">
            <span class="input_name">
            	<input type="text" class="clear_dataval" name="appName" id="appName" placeholder="APP中文名称" >
            </span>
          </div>
          <div class="add_body">
            <span class="input_name">
            	<input type="text" class="clear_dataval" name="appEnglishName" id="appEnglishName" placeholder="APP英文名称">
            </span>
          </div>
          <div class="add_body">
            <span class="input_name">
            	<input type="text" class="clear_dataval" name="appAbbreviation" id="appAbbreviation" placeholder="名称简写">
            </span>
          </div>
        </div>
      </div>
      <div class="btn_add">
        <a class="add_ct add_ct1" onclick="return addAppInfo();" href="###">保存</a>
        <a class="add_undo1" href="###">取消</a>
     </div>
 </div>
</div>
</div>
<script type="text/javascript">
    function addAppInfo(){
    	var appName=$('#appName').val();
    	var appEnglishName=$('#appEnglishName').val();
    	var appAbbreviation=$('#appAbbreviation').val();
    	if(appName==''||appEnglishName==''||appAbbreviation=='' ){
    		alert('信息不能为空！');
    		return false;
    	}
		$.ajax({
			url:"/app/info/add.html",
			type:"POST",
			data:{"appName":appName,"appEnglishName":appEnglishName,"appAbbreviation":appAbbreviation},
			dataType:"JSON",
			async: false,
			success:function(data){
				if(data.success){
					window.location.reload()
				}
			}
		});
    }
</script>
</body>
</html>
