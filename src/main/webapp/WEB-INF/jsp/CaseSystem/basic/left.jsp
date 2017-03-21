<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div style="background:url(<%=path %>/CaseSystem/images/fr_leftbg.png) repeat-y;width:100%;height:100%;">
    <div class="fr_left">
        
        <div onclick="changeSystem('<%=path %>/basic/main.action?fparentid=300000');" class="fr_left_tab fr_left_tabbg1" style="cursor:pointer">
             <a>
                检<br />
                案<br />
                系<br />
                统
            </a>
            <div class="fr_loc_hidden"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	$(".fr_left_tab").click(function(){
		$(".fr_left_tab").attr('class','fr_left_tab fr_left_tabbg1');
		$(this).attr('class','fr_left_tab fr_left_tabbg2');
	});
	$(".fr_left_tab").eq(0).click();
});
</script>

