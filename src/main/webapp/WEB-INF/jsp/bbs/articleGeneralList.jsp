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
    <link href="../../../css/postmanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>帖子管理</title>
    <script type="text/javascript">
    	function toActivityList(){
    		location.href = "/bbs/article/getArticleList.html?fromType=2";
    	}
    	function toReportList(){
    		location.href = "/bbs/article/getArticleList.html?fromType=3";
    	}
    	function toSchoolList(){
    		location.href = "/bbs/article/getArticleList.html?fromType=4";
    	}
    	function setForm(id,title){
    		$("#form1").val("");
    		$("#form").val("");
    		if(id == 4||id==30||id==31){
    			$("#form").val(id);
    		}else{
    			$("#form1").val(id);
    			$("#form2NameSel").text("二级版块");
    			$("#form2NameUl").find("li").remove();
    			//获取下级版块
    			$.ajax({
   		 		   url: "/bbs/form/queryAjaxFormList.html?formId="+id,
   		 		   type: "POST",
   		 		   dataType:"json",
   		 		   cache:false,
   		 		   async: false,
   		 		   success: function(obj){
   		 			  if ( !checkCode( obj ) ) {
   		 					return;
   		 			  }
   		 			  //获取数据 生成菜单部分
   		 			  var html = "<i class='arrowtop'></i>";
   		 			  var data = obj.result.form;
   		 			  for(var o in data){
   		 				 html = html+ "<li onclick='setForm2Val("+data[o].id+")' data-id='"+data[o].id+"' id='li"+data[o].id+"' _name='"+data[o].name+"'>"+data[o].name+"</li>";
   		 			  }
   		 			  $("#form2NameUl").append(html);
   		 		   }
    		 	});
    		}
    		$("#formTitle").text(title);
    	}
    	function setForm2Val(id){
 		   var _name = $("#li"+id).attr("_name");
 		   $("#form2NameSel").text(_name);
 		   $("#form").val(id);
 		   $("#form2NameUl").hide();
 	    }
    	function setSort1(id,name){
    		if(id != 0)
    			$("#sort").val(id);
			$("#sortTitle").text(name);
    	}
    	function setTagType(id,name){
    		if(id != 0)
    			$("#tagType").val(id);
			$("#tagTypeTitle").text(name);
    	}
    	function setIsTop(id,name){
    		if(id != 0)
    			$("#isTop").val(id);
			$("#isTopTitle").text(name);
    	}
    	function setGood(id){
    		var check = $("#goodcheck"+id).attr("checked");
    		var good = 0;
    		if(check == "checked"){
    			good = 1;
    		}
    		if(check == undefined){
    			good = 0;
    		}
    		setUpdateTagType("good",good,id);
    	}
    	function setNotice(id){
    		var check = $("#noticecheck"+id).attr("checked");
    		var notice = 0;
    		if(check == "checked"){
    			notice = 1;
    		}
    		if(check == undefined){
    			notice = 0;
    		}
    		setUpdateTagType("notice",notice,id);
    	}
    	function setTop(id){
    		var check = $("#topcheck"+id).attr("checked");
    		var top = 0;
    		if(check == "checked"){
    			top = 1;
    		}
    		if(check == undefined){
    			top = 0;
    		}
    		setUpdateTagType("top",top,id);
    	}
    	function setOfficialCertification(id){
    		var check = $("#officialCertificationcheck"+id).attr("checked");
    		var officialCertification = 0;
    		if(check == "checked"){
    			officialCertification = 1;
    		}
    		if(check == undefined){
    			officialCertification = 0;
    		}
    		setUpdateTagType("officialCertification",officialCertification,id);
    	}
    	function setUpdateTagType(pro,val,id){
    		$.ajax({
		 		   url: "/bbs/article/updateTagType.html?"+pro+"="+val+"&id="+id,
		 		   type: "POST",
		 		   dataType:"json",
		 		   cache:false,
		 		   success: function(obj){
		 			  if ( !checkCode( obj ) ) {
		 					return;
		 			  }
		 			 alertLayel("设置成功");
		 		   }
 		 	});
    	}
    	function setSort(v,id){
    		$.ajax({
		 		   url: "/bbs/article/updateSort.html?sort="+v+"&id="+id,
		 		   type: "POST",
		 		   dataType:"json",
		 		   cache:false,
		 		   success: function(obj){
		 			  if ( !checkCode( obj ) ) {
		 					return;
		 			  }
		 			 alertLayel("设置成功");
		 		   }
		 	});
    	}
    	function del(id){
    		$("#articleId").val(id);
    		$('.delreasonpop').show();
    	}
    	$(document).ready(function(){
    		var form = $("#form").val();
    		if(form != null && form != ""){
    			var title = "";
    			if(form == 4){
    				title = "社区";
    				setForm(4,title);
    			}else if(form == 30){
    				title = "白熊资讯";
    				setForm(30,title);
    			}else if(form == 31){
    				title = "行业洞察";
    				setForm(31,title);
    			}else{
    				title = "产品讨论";
    				setForm(1,title);
    				$('#form2NameUl li').each(function(){
        				var id=$(this).attr('data-id');
        				if(form==id){
        					$('#form2NameSel').text($(this).text());
        				}
        			});
    			}
    		}
    		var sort = $("#sort").val();
    		if(sort != null && sort != ""){
    			$('#sortUI li').each(function(){
    				var id=$(this).attr('data-id');
    				if(sort==id){
    					$('#sortTitle').text($(this).text());
    				}
    			});
    		}
    		var tagType = $("#tagType").val();
    		if(tagType != null && tagType != ""){
    			$('#tagTypeUI li').each(function(){
    				var id=$(this).attr('data-id');
    				if(tagType==id){
    					$('#tagTypeTitle').text($(this).text());
    				}
    			});
    		}
    		var isTop = $("#isTop").val();
    		if(isTop != null && isTop != ""){
    			$('#isTopUI li').each(function(){
    				var id=$(this).attr('data-id');
    				if(isTop==id){
    					$('#isTopTitle').text($(this).text());
    				}
    			});
    		}
    	});
    </script>
</head>
<body>
    <section>
        <ul class="partlist">
            <li>
                <span class="switch switched">普通帖子</span>
                <div class="inforbox selectinforbox">
                	<form action="/bbs/article/getArticleList.html" method="post">
                		<input type="hidden" name="fromType" value="1" id="fromType"/>
                		<input type="hidden" name="form1" value="" id="form1"/>
	                	<input type="hidden" name="form" value="${form }" id="form"/>
	                	<input type="hidden" name="sort" value="${sort }" id="sort"/>
	                	<input type="hidden" name="tagType" value="${tagType }" id="tagType"/>
	                	<input type="hidden" name="isTop" value="${isTop }" id="isTop"/>
	                    <div class="row">
	                        <span class="selectinput plate">
	                            <span class="selectvalue" id="formTitle">一级版块</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option">
	                                <li onclick="setForm(30,'白熊资讯')">白熊资讯</li>
	                                <li onclick="setForm(31,'行业洞察')">行业洞察</li>
	                                <li onclick="setForm(4,'社区')">社区</li>
	                                <li onclick="setForm(1,'产品讨论')">产品讨论</li>
	                            </ul>
	                        </span>
	                        <span class="selectinput plate">
	                            <span class="selectvalue" id="form2NameSel">二级版块</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option" id="form2NameUl">
		                		</ul>
	                        </span>
	                         <span class="selectinput plate">
	                            <span class="selectvalue" id="sortTitle">默认排序</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option" id="sortUI">
	                            	<li data-id="0" onclick="setSort1(0,'默认排序')">默认排序</li>
	                                <li data-id="1" onclick="setSort1(1,'回复数')">回复数</li>
	                                <li data-id="2" onclick="setSort1(2,'点赞数')">点赞数</li>
	                                <li data-id="3" onclick="setSort1(3,'查看数')">查看数</li>
	                                <li data-id="4" onclick="setSort1(4,'发布时间')">发布时间</li>
	                            </ul>
	                        </span>
	                        <span class="selectinput plate">
	                            <span class="selectvalue" id="tagTypeTitle">全部</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option" id="tagTypeUI">
	                                <li data-id="0" onclick="setTagType(0,'全部')">全部</li>
	                                <li data-id="1" onclick="setTagType(1,'精华')">精华</li>
	                                <li data-id="2" onclick="setTagType(2,'公告')">公告</li>
	                                <li data-id="3" onclick="setTagType(3,'置顶')">置顶</li>
	                              	<li data-id="4" onclick="setTagType(4,'官方认证')">官方认证</li>
	                            </ul>
	                        </span>
	                        <span class="selectinput plate">
	                            <span class="selectvalue" id="isTopTitle">全部种类</span>
	                            <i class="arrow arrowright"></i>
	                            <ul class="option" id="isTopUI">
	                                <li data-id="0" onclick="setIsTop(0,'全部种类')">全部种类</li>
	                                <li data-id="1" onclick="setIsTop(1,'头条帖')">头条帖</li>
	                                <li data-id="2" onclick="setIsTop(2,'普通帖')">普通帖</li>
	                            </ul>
	                        </span>
	                        <input type="text" value="${queryName }" name="queryName" class="postname">
	                        <input type="hidden" name="pageNo" id="pageNo" value="">
	                        <input type="submit" class="searchbtn search" value="搜索">
	                    </div>
                    </form>
                    <div class="row">
                        <table class="postlist">
                            <thead>
                            <tr>
                                <td>序号</td>
                                <td>白熊号</td>
                                <td>帖子标题</td>
                                <td>设置标签</td>
                                <td>发布板块</td>
                                <td>时间</td>
                                <td>设置排序</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${articleList}" var="article" varStatus="st">
	                            <tr>
	                                <td>${st.index+1 }</td>
	                                <td>${article.user.bxNum }</td>
	                                <td>${article.title}</td>
	                                <td>
	                                	<c:if test="${article.good == 1}"><input type="checkbox" class="postcheck" checked="checked" id="goodcheck${article.id}" onclick="setGood(${article.id})">精华</c:if>
	                                	<c:if test="${article.good != 1}"><input type="checkbox" class="postcheck" id="goodcheck${article.id}"  onclick="setGood(${article.id})">精华</c:if>
	                                	<c:if test="${article.notice == 1}"><input type="checkbox" class="postcheck" checked="checked" id="noticecheck${article.id}"  onclick="setNotice(${article.id})">公告</c:if>
	                                	<c:if test="${article.notice != 1}"><input type="checkbox" class="postcheck" id="noticecheck${article.id}"  onclick="setNotice(${article.id})">公告</c:if>
	                            		<c:if test="${article.top == 1}"><input type="checkbox" class="postcheck"  checked="checked" id="topcheck${article.id}"  onclick="setTop(${article.id})">置顶</c:if>
	                            		<c:if test="${article.top != 1}"><input type="checkbox" class="postcheck" id="topcheck${article.id}"  onclick="setTop(${article.id})">置顶</c:if>
	                            		<c:if test="${article.officialCertification == 1}"><input type="checkbox"  checked="checked" class="postcheck" id="officialCertificationcheck${article.id}"  onclick="setOfficialCertification(${article.id})">官方认证</c:if>
	                            		<c:if test="${article.officialCertification != 1}"><input type="checkbox" class="postcheck" id="officialCertificationcheck${article.id}"  onclick="setOfficialCertification(${article.id})">官方认证</c:if>
	                                </td>
	                                <td>${article.fromName}</td>
	                                <td><fmt:formatDate value="${article.createDateTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                                <td><input type="text" class="postsort" value="${article.sort}" onchange="setSort(this.value,${article.id})"> </td>
	                                <td><a href="#" class="link deletelink" onclick="del(${article.id})">删除</a> </td>
	                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../include/pages_old.jsp"></jsp:include>
                </div>
            </li>
            <li>
                <span class="switch" onclick="toActivityList()">活动帖子</span>
            </li>
            <li>
                <span class="switch" onclick="toReportList()">举报帖子</span>
            </li>
            <li>
                <span class="switch" onclick="toSchoolList()">学院稿件</span>
            </li>
        </ul>
    </section>
<form action="/bbs/article/delete.html">
<input type="hidden" name="id" id="articleId">
<input type="hidden" name="fromType" value="1" id="fromType"/>
<div class="pop delreasonpop">
    <div class="popbg"></div>
    <div class="layel">
        <h3 class="poptitle">删除帖子<i class="closeicon"></i> </h3>
        <div class="row derrearow">
            <div class="col-lg-6"><input type="radio" class="delreason" name="reason" value="1" checked="checked">广告删除 </div>
            <div class="col-lg-6"><input type="radio" class="delreason" name="reason" value="2">恶意灌水 </div>
            <div class="col-lg-6"><input type="radio" class="delreason" name="reason" value="3">重复发帖 </div>
            <div class="col-lg-6"><input type="radio" class="delreason" name="reason" value="4">谩骂 </div>
            <div class="col-lg-6"><input type="radio" class="delreason" name="reason" value="5">违规信息 </div>
        </div>
        <div class="row tc">
            <input type="submit" value="删除" class="delete">
            <input type="button" value="取消" class="cancel">
        </div>
    </div>
</div>
</form>
</body>
</html>