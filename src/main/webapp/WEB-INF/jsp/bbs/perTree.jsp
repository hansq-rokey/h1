<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="../CaseSystem/basic/base.jsp" flush="true" />
    <script type=text/javascript src="<%=path%>/js/login.js"></script>
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <script src="../../../js/base.js" type="text/javascript" ></script>
<script type="text/javascript">
$(function(){

	//全部展开
	$('#expandAll').click(function(){
		$('#rightTreeGrid').treegrid('expandAll');
	});
	//全部收缩
	$('#collapseAll').click(function(){
		$('#rightTreeGrid').treegrid('collapseAll');
	});

	//保存权限  group_privilege_form
	$('#save_right').bind('click',function(){
		var group_privilege_form = $('#group_privilege_form');
		$.ajax({
		   type: "POST",
		   url: "<%=path%>/bbs/role/savePer.html?roleId=${roleId}",
		   data: group_privilege_form.serialize(),
		   dataType:"json",
		   cache:false,
		   success: function(result){
		   		if(result.status){
		   			alertLayel('保存成功！');
		   		}else{
		   			alertLayel('保存失败！');
		   		}
		   }
		});
	});

	//全选
	$("#selectAll").click(function(){
		$('input[name=privilegeids]:checkbox').attr('checked',"checked");
	});
	//取消全选
	$("#clearAll").click(function(){
		$('input[name=privilegeids]:checkbox').removeAttr("checked");
	});
	
});

//备用  树形checkbox
	function title_formatter(value,node){    
		var content=''; 
	  	if(node.CHECKED==null){
	  		content='<input name="privilegeids" id="checkbox_'+node.ID+'" nid="'+node.ID+'" pid="'+node.PARENT+'" onclick="set_power_status('+node.ID+')" type="checkbox" value="'+node.ID+'" />'+value;
	  	}else{
	  		content='<input name="privilegeids" id="checkbox_'+node.ID+'" nid="'+node.ID+'" pid="'+node.PARENT+'" onclick="set_power_status('+node.ID+')" checked type="checkbox" value="'+node.ID+'" />'+value;
	  	}
	    return content;  
	} 
	
function set_power_status(nid){
	var nid = $('#checkbox_'+nid).attr('nid');
	var pid = $('#checkbox_'+nid).attr('pid');
	var is_checked = false;
	if($('#checkbox_'+nid).is(':checked')==true){
		is_checked = true;
	}
	//处理上级
	updateParentNodes(pid,is_checked);
	//处理下级
	updateChildNodes(nid,is_checked);

}
/** 
   * 级联选择父节点 
   */  
  function updateParentNodes(pid,is_checked){  
	$('input[nid='+pid+']:checkbox').each(function(){
			if(is_checked){
				$(this).attr('checked',true);
				//alert($(this).is(':checked'));
			}
			else{
				if($('input:checkbox[name=privilegeids][pid='+pid+']:checked').length < 1){
					$(this).attr('checked',false);
					//$(this).removeAttr("checked");
				}
			}
			var parentid = $(this).attr('pid');
			var parent_obj = $('input[nid='+parentid+']:checkbox');
			if(parent_obj.length > 0){
				updateParentNodes(parentid,is_checked);
			}
	});
  }  
  /** 
   * 级联选择子节点 
   */  
  function updateChildNodes(nid,is_checked){  
	$('input[pid='+nid+']:checkbox').each(function(){
    	if(is_checked){
        	//alert($(this).attr('type'));
    		$(this).attr('checked',true);
    		//alert($(this).is(':checked'));
        }else{
        	$(this).attr('checked',false);
        	//$(this).removeAttr("checked");
        }
		var childid = $(this).attr('nid');
		var next_obj = $('input[pid='+childid+']:checkbox');
		if(next_obj.length > 0 ){
			updateChildNodes(childid,is_checked);
		}
	});
  }

</script>
</head>
<body>
<div>
		<div class="detail_location" style="height:34px;">
			<em class="de_em1"></em>
			<div  class="op_button" style="height:34px;">
				<input id="expandAll" type="button"  class="bpbutton_style" style="width:80px;" value="全部展开" />
				<input id="collapseAll" type="button"  class="bpbutton_style" style="width:80px;" value="全部收缩" />
				<input id="selectAll" type="button"  class="bpbutton_style" style="width:60px;" value="全选" />
				<input id="clearAll" type="button"  class="bpbutton_style" style="width:90px;" value="取消全选" />
				<input id="roleId" type="hidden" name="roleId" value="${roleId }"/>
			</div>
		</div>
			<form id="group_privilege_form">
				<table id="rightTreeGrid" class="easyui-treegrid"
				data-options="url: '<%=path%>/bbs/role/perTreeSelect.html?roleId=${roleId }',
	                 method: 'post',
	                 rownumbers: true,
	                 idField: 'ID',
	                 treeField: 'NAME',
	                 lines: false, 
	                 dnd: true,
	                 animate: true,
	                 collapsible: true, 
	                 singleSelect: true,
	                 border:false,
	                 nowrap:false,
	                 fitColumns:true"
					style="height: 600px;">
					<thead>
						<tr>
							<th field="NAME" data-options="formatter:title_formatter"
								width="200">
								名称
							</th>
							<th data-options="field:'NOTE'" width="100%">
								说明
							</th>

						</tr>
					</thead>
				</table>
			</form>
		</div>
		<div style="text-align: center; padding: 5px">
			<input id="save_right" type="button"  class="bpbutton_style" style="width:60px;" value="保存" />
		</div>
	</div>
	</body>
</html>