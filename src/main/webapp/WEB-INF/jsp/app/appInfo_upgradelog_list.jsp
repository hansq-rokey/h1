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
    <title>APP升级日志管理</title>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<div class="addBtn"><i></i></div>
<div class="right-bottom">
    <div class="right-top" style="left: 280px;">
    </div>
    <div class="a_content">
     <div class="tab-list">
        </div>
            <table class="add_content add_cityMerchant">
                <tr>
                    <th>APP中文名称</th>
                    <th>版本号</th>
                    <th>版本代码</th>
                    <th>下载路径</th>
                    <th>应用大小</th>
                    <th>是否自动升级</th>
                    <th>日志内容</th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="item">
                <tr>
                    <td>${item.appChineseName}</td>
                    <td>${item.appVersionName}</td>
                    <td>${item.appVersionCode }</td>
                    <td>${item.installUrl }${item.appDownloadName }</td>
                    <td><fmt:formatNumber value="${item.appSize/1024/1024 }" pattern="#.##"/>  M</td>
                    <td>${item.autoUpdate==1?'是':'否' }</td>
                    <td>${item.upgradeLog }</td>
                </tr>
                </c:forEach>
            </table>
            <jsp:include page="../include/pages_new.jsp"></jsp:include>
      </div>
    </div> 
</div>
<form action="/app/log/add.html" id="saveLogform" onsubmit=" return addAppInfoLog();" method="post" enctype="multipart/form-data">
<div class="add_layer add_layer1" style="display:none">
    <div class="layer_content">
      <h3 id="add_1">新增</h3>    
      <div class="mian_content" style="margin-top:15px;">
        <div class="add_right" style="padding:20px;">
          <div class="add_body">
            <div class="rowc_mian">
                <div class="rowskin">
                  <i class="selectpoint"></i>
                  <span class="selectcontent">应用名称</span>
                  <ul class="rowopation" style="display: none;">
                      <c:forEach items="${appInfoList }" var="item">
	                      <li data-id="${item.id }">${item.appName }</li>                      
                      </c:forEach>
                  </ul>
                  <input type="hidden" class="c_m" id="c_m1" value="1">
                 </div>
               </div>
          </div>
          <div class="add_body">
            <span class="input_text">
            	<input type="text" class="clear_dataval" id="appVersionName" name="appVersionName" placeholder="版本名称" >
            </span>
            <span class="input_text">
            	<input type="text" class="clear_dataval" id="appVersionCode" name="appVersionCode" placeholder="版本代码">
            </span>
          </div>
        </div>
        <div class="add_right">
        	 <div class="add_body">
            <span class="input_name">
            	是否自动升级：<input type="radio" class="clear_dataval" id="autoUpdate" value="1" name="autoUpdate"> 是
            	<input type="radio" class="clear_dataval" id="autoUpdate" value="0" checked="checked" name="autoUpdate">否
            </span>
          </div>
          <div class="add_body">
          	<label>升级日志：</label>
            <textarea id="upgradeLog" rows="2" name="upgradeLog">
            </textarea>
          </div>
          <div class="add_body">
          	<label>升级文件：</label><input type="file" name="file" />
          </div>
        </div>
      </div>
      <div class="btn_add">
        <a class="add_ct add_ct1" href="###" id="saveLogSubmit">保存</a>
        <a class="add_undo1" href="###">取消</a>
     </div>
 </div>
</div>
<input type="hidden" name="appInfoId" id="appInfoId" />
<input type="hidden" name="appAbbreviation" value="${name }" />
</form>
</div>
<script type="text/javascript">
    function addAppInfoLog(){
    	var appVersionName=$('#appVersionName').val();
    	var appVersionCode=$('#appVersionCode').val();
    	var upgradeLog=$('#upgradeLog').text();
    	if(appVersionName==''||appVersionCode==''||upgradeLog=='' ){
    		alert('信息不能为空！');
    		return false;
    	}
    }
    $(document).ready(function(){
   	　　$("#saveLogSubmit").on("click",function(){
   	　　　　$('#saveLogform').submit();
   	　　});
   	　　$(".rowopation > li").click(function(){
   	　　　　var id=$(this).attr('data-id');
   			$('#appInfoId').val(id);
   	　　});
   });
</script>
</body>
</html>
