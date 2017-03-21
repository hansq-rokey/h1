<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/prodbasedata.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/prodbasedata.js" type="text/javascript" ></script>
    <title>基础数据</title>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch switched">产品品类</span>
                <div class="inforbox selectinforbox">
                    <input type="button" class="addbtn addcatge" value="新增品类">
                    <div class="row">
                        <table class="prodcateglist">
                            <thead>
                            <tr>
                                <td width="250">类目名称</td>
                                <td width="250">类目代码</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${categoryList }" var="category">
                            	<tr>
	                                <td><span class="hidespan" data-id="${category.id }">${category.name }</span><input type="text" name="name" class="hideinput" value=""> </td>
	                                <td><span class="hidespan">${category.code }</span><input type="text" name="code" class="hideinput categoryCode" value=""> </td>
	                                <td><a href="#" class="link editlink">编辑</a><a href="#" class="link savelink">保存</a><a href="#" class="link cancellink">取消</a> </td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="3"><i class="showicon addcatge"></i> </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </li>
            <li>
                <span class="switch">产品型号</span>
                <div class="inforbox">
                    <a href="/mall/base/model/edit.html"> <input type="button" class="addbtn addtype" value="新增型号"></a>
                    <div class="row">
                        <table class="prodtylelist">
                            <thead>
                            <tr>
                                <td width="250">产品名称</td>
                                <td width="250">产品品类</td>
                                <td width="250">产品型号</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody id="modelList">
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
            <li style="display:none;">
                <span class="switch">智能连接</span>
                <div class="inforbox">
                    <input type="button" class="addbtn addlink" value="新增型号">
                    <div class="row">
                        <table class="prdlinklist">
                            <thead>
                            <tr>
                                <td width="200">智能类型</td>
                                <td width="200">wifi名称</td>
                                <td width="110">缩略图</td>
                                <td width="110">指示灯√</td>
                                <td width="110">指示灯x</td>
                                <td width="110">重置开关</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <span class="hidespan"></span>
                                    <span class="selectinput hideinput">
                                        <span class="selectvalue">奇思妙想</span>
                                        <i class="arrow arrowright"></i>
                                        <ul class="option">
                                            <li>上发生地</li>
                                            <li>发的发鬼</li>
                                            <li>个地方官</li>
                                        </ul>
                                    </span>
                                </td>
                                <td><span class="hidespan"></span><input type="text" class="hideinput" value=""></td>
                                <td>
                                    <div class="uploadimgbox">
                                        <div class="uploadbg"></div>
                                        <p class="uploadword">上传图片</p>
                                        <img src="#" class="prodattrimg">
                                        <input type="file" class="file">
                                    </div>
                                </td>
                                <td>
                                    <div class="uploadimgbox">
                                        <div class="uploadbg"></div>
                                        <p class="uploadword">上传图片</p>
                                        <img src="#" class="prodattrimg">
                                        <input type="file" class="file">
                                    </div>
                                </td>
                                <td>
                                    <div class="uploadimgbox">
                                        <div class="uploadbg"></div>
                                        <p class="uploadword">上传图片</p>
                                        <img src="#" class="prodattrimg">
                                        <input type="file" class="file">
                                    </div>
                                </td>
                                <td>
                                    <div class="uploadimgbox">
                                        <div class="uploadbg"></div>
                                        <p class="uploadword">上传图片</p>
                                        <img src="#" class="prodattrimg">
                                        <input type="file" class="file">
                                    </div>
                                </td>
                                <td><a href="#" class="link editlink">编辑</a><a href="#" class="link savelink">保存</a><a href="#" class="link cancellink">取消</a> </td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="7"><i class="showicon addlink"></i> </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
    <script type="text/javascript">
    $('.switch').on('click',function(){
	    var html=$(this).html();
	    console.log("----"+html);
	    if(html=='产品型号'){
	   	_data = {};
	   	settings.formatsListFn(_data);	    	
	    }
	});

var settings = {// 根据不同ID获取相应地址接口
   		formatsListFn: function(data){
   			$.ajax({
   		 		   url: "/mall/base/model/list.html",
   		 		   data: data,//参数
   		 		   type: "POST",
   		 		   dataType:"json",
   		 		   cache:false,
   		 		   success: function(obj){
   		 			  console.dir(obj);
   		 			  if ( obj.code!=1 ) {
   		 				alertLayel(obj.message);
   		 					return;
   		 			  }else{   		 			  	
   		 			  		fillData(obj.result.models);
   		 			  }
   		 		   }
   		 	});
   		}
    };
    
    function fillData(data){
    	var arrayObj = new Array();　//创建一个数组
     	$.each(data, function(i, item){
    		var _temp = ['<tr>',
    						'<td>'+item.name+'</td>',
    					    '<td>'+item.category.code+' </td>',
    					    '<td>'+item.code+' </td>',
    					    '<td><a class="link" href="/mall/base/model/edit.html?id='+item.id+'">详情</a></td>',
    					  '</tr>'].join('');
    		arrayObj.push(_temp);
    	});
    	$("#modelList").empty().append(arrayObj.join(''));
    }
</script>
</body>
</html>