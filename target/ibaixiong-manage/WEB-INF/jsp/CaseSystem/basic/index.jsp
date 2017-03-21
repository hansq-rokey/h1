<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <jsp:include page="../../CaseSystem/basic/base.jsp" flush="true" />
    <script type="text/javascript">
		$(function(){
			$(document).keydown(function (event) {
  			  	if (event.keyCode == 8) {
  			  	 e = window.event || e;  
  			    var code = e.keyCode || e.which;  
  			    if (code == 8) {  
  			        var src = e.srcElement || e.target;  
  			        var tag = src.tagName;  
  			        if (tag != "INPUT" && tag != "TEXTAREA") {  
  			            e.returnValue = false;    
  			            return false;  
  			        } else if ((tag == "INPUT" || tag == "TEXTAREA") && src.readOnly == true) {  
  			            e.returnValue = false;  
  			            return false;    
  			        }  
  			    }  
  			  	}
			});	
		});
    
function changeSystem(url){
	$("#mainFrame", parent.document).attr('src',url);
	
}
</script>
<body id="mainBody" class="easyui-layout">
	<div data-options="region:'north',border:false,href:'<%=path %>/CaseSystem/basic/top.jsp'" style="height:90px;overflow:hidden;">
	</div>   
    <div data-options="region:'south',border:false,href:'<%=path %>/CaseSystem/basic/footer.jsp'" style="height:26px;overflow:hidden;">
    </div>   
    <div data-options="region:'west',border:false,href:'<%=path %>/CaseSystem/basic/left.jsp'" style="width:38px;overflow:hidden;">
    </div>   
    <div data-options="region:'center',border:false">
    	 <iframe id="mainFrame" name="mainFrame" width="100%" height="100%"></iframe>
    </div>
</body>
</html>
