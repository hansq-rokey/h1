<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/plug_new/bootstrap/daterangepicker-bs3.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/common.css" />
    <link rel="stylesheet" type="text/css" href="/css_new/Process.css" />
    <title>商品发布流程第一步</title>
</head>
<body>
<jsp:include page="../include/left_new.jsp"/>
<!-- <div class="addBtn"><i></i></div> -->
<div class="right-part">
 <jsp:include page="../include/top_new.jsp"/>
 	<form action="/mall/product/update.html" method="post" id="form1">
    <div class="wrap add_productwrap">
       <div class="m_content" style="padding-bottom: 100px;">
          <div class="p_title">
            <ul class="p_list">
               <li class="p_on"><a href="/mall/product/edit.html?id=${productId }"><span>1</span></a></li>
               <li><a href="/mall/product/edit.html?id=${productId }&level=2"><span>2</span></a></li>
               <li><a href="/mall/product/edit.html?id=${productId }&level=3"><span>3</span></a></li>
            </ul>
          </div>
        <div class="p_content" >
          <div class="content_list">
            <div class="content_mian">
              <div class="selectskin">
                <span class="selectcontent">${product.basicCategoryModel.name }+${product.basicCategoryModel.code }</span>
              </div>
            </div>
            <div class="selectskin selecttemplate" style="margin-top: -19px;">
                <i class="selectpoint"></i>
                <span class="selectcontent template_id" data-id="${product.freightTemplateId }">${product.templateName }</span>
                <ul class="selectopation" style="display: none;">
                	<c:forEach items="${templates}" var="template">
                        <li data-id="${template.id}">${template.name}</li>
                   	</c:forEach>
                </ul>
              </div>
            <div class="content_mian">
              <div class="p_check">
                   <label>是否上架</label>
                   <p><input type="radio" id="checkbox1" name="status" ${product.status==1?'checked="checked"':'' } value="1" class="regular-checkbox" /><label for="checkbox1"></label><span>是</span></p>
                   <p><input type="radio" id="checkbox2" name="status" ${product.status==-1?'checked="checked"':'' } value="-1"  class="regular-checkbox" /><label for="checkbox2"></label><span>否</span></p> 
                </div>   
                <div class="p_check">
                   <label>是否是白熊产品</label>
                   <p><input type="radio" id="checkbox3" name="isCompanyProduct" ${product.isCompanyProduct==1?'checked="checked"':'' } value="1" class="regular-checkbox" /><label for="checkbox3"></label><span>是</span></p>
                   <p><input type="radio" id="checkbox4" name="isCompanyProduct" ${product.isCompanyProduct==0?'checked="checked"':'' } value="0" class="regular-checkbox" /><label for="checkbox4"></label><span>否</span></p> 
               </div>
            </div>
            <div class="content_mian">
             <p class="ProfitInput">
             <%--  <span>总利润</span>
              <span><input type="text" class="p_Profit" id="" value="${product.totalProfit }" name="totalProfit"></span>
              <span>%</span> --%>
                <span class="Profit_input_list Profit_input_list3" style="display:none"><label>市场价</label><input type="text" class="Price" name="price" id="price" value="${price }"></span>
                <span class="Profit_input_list Profit_input_list2"><label>代理商提货价</label><input type="text" name="productPurchasePrice" value="${product.productPurchasePrice==null?'':product.productPurchasePrice }" class="Price" id=""></span>
            </p>
            </div>
            <div class="content_mian">
              <div class="p_check">
                   <label>物料计算</label>
                    <p><input type="radio" id="checkbox13" name="isMaterialCalculate" ${product.isMaterialCalculate==1?'checked="checked"':'' } value="1" class="regular-checkbox" /><label for="checkbox13"></label><span>是</span></p>
                    <p><input type="radio" id="checkbox14" name="isMaterialCalculate" ${product.isMaterialCalculate==0?'checked="checked"':'' } value="0" class="regular-checkbox" /><label for="checkbox14"></label><span>否</span></p>
               </div>
               <div class="p_check">
                   <label>是否允许使用优惠券</label>
                   <p><input type="radio" id="checkbox11" name="specialType" ${product.specialType==1?'checked="checked"':'' } value="1" class="regular-checkbox" /><label for="checkbox11"></label><span>是</span></p>
                   <p><input type="radio" id="checkbox12" name="specialType" ${product.specialType==0?'checked="checked"':'' } value="0" class="regular-checkbox" /><label for="checkbox12"></label><span>否</span></p> 
               </div>
            </div>
             <div class="p_check">
                   <label style="font-weight: 400;">产品标签 </label>
                   <ul class="goodsbox">
                    <c:forEach items="${tags }" var="tag" varStatus="status">
                    	<li><input type="checkbox" id="check${status.count}" name="tagId" ${tag.type==0?'checked="checked"':'' } value="${tag.id }" class="regular-checkbox" /><label for="check${status.count}"></label>${tag.tagName }</li>
                    </c:forEach>
                    </ul>
               </div>
          </div>
          <div class="content_list">
              <div class="content_input">
                <label style="top: -17px; color: rgb(44, 163, 229);">产品特点</label>
                <input type="text" class="Feature ab" id="Feature" value="${product.feature }" name="feature">
              </div>
            <div class="content_mian">
                 <div class="p_check">
                   <label>是否允许下定金</label>
                   <p><input type="radio" id="checkbox5" name="isUseDeposit" value="1" ${product.isUseDeposit==1?'checked="checked"':'' } class="regular-checkbox chbox chfade" /><label for="checkbox5"></label><span>是</span></p>
                   <p><input type="radio" id="checkbox6" name="isUseDeposit" value="0" ${product.isUseDeposit==0?'checked="checked"':'' } class="regular-checkbox chbox" /><label for="checkbox6"></label><span>否</span></p> 
               </div>
               <div class="p_check p_fadeout"  ${product.isUseDeposit==1?'':'style="display:none"' }>
                 <label class="p_Unit">定金</label>
                 <input type="text" class="p_money2" value="${product.depositMoney }" name="depositMoney" id="">
                 <span >元</span>
               </div>
                <div class="content_mian">
	              <div class="isSee_total">
	                  <label class="issee_label">可见性</label>
	                  <p><input type="checkbox" name="cDisplay" value="1"  ${product.cDisplay==1?'checked="checked"':'' } /><label for="checkbox7"> </label><span class="Client">C端用户</span></p>
	                  <p><input type="checkbox"  name="bDisplay" value="1"  ${product.bDisplay==1?'checked="checked"':'' } /><label for="checkbox8"> </label><span class="Client">B端用户</span></p> 
	              </div>
              </div>
              <div class="content_mian">
	              <div class="p_check">
	                  <label>是否有区域利润</label>
	                  <p><input type="radio" id="checkbox9" name="isSetAreaProfit" value="1" ${product.isSetAreaProfit==1?'checked="checked"':'' } class="regular-checkbox isprofit isprofiton" /><label for="checkbox9"></label><span>是</span></p>
	                  <p><input type="radio" id="checkbox10" name="isSetAreaProfit" value="0" ${product.isSetAreaProfit==0?'checked="checked"':'' } class="regular-checkbox isprofit" /><label for="checkbox10"></label><span>否</span></p> 
	              </div>
	             <div class="PriceBox" ${product.isSetAreaProfit==1?'style="display:block"':'' }>
                  <input type="text" class="Price Price1" name="areaMoney" id="" value="${product.areaMoney }">
                 </div>
              </div>
              </div>
          </div>
        </div>  
        <p class="p_btn">
          <a class="p_btna" href="javascript:void(0);">保存</a>
          <a class="p_btnb" href="###">取消</a>
        </p>
    </div>
  </div>
  <input type="hidden" name="level" value="1" />
  <input type="hidden" name="id" value="${product.id }" />
  <input type="hidden" name="categoryModelId" value="${product.categoryModelId }" />
  <input type="hidden" name="freightTemplateId" id="templateId" value="${product.freightTemplateId }" />
  </form>
</div>
<script type="text/javascript" src="/plug_new/jQuery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/moment.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="/plug_new/layer/layer.js"></script>
<script type="text/javascript" src="/js_new/common.js"></script>
<script type="text/javascript">
	$(".p_btna").click(function(){
		$("#form1").submit();
	});
	 //输入框焦点事件
	 $(document).on('focus', '#Feature', function() {
			 $(this).prev("label").css({top: '-17px',color: '#2CA3E5'});
			 $(this).addClass('ab');
	 });
	 $(document).on('blur', '#Feature', function() {
	  var _htmlc=$(this).val();
	  console.log(_htmlc);
	    if (_htmlc=="") {
	          $(this).prev("label").css({top: '-2px',color: '#5a6677'});
	          $(this).removeClass('ab');
	    }else{
	          $(this).prev("label").css({top: '-17px',color: '#2CA3E5'});
	          $(this).addClass('ab');
	    }
	 });
	 $(function(){
		  $(".chbox").on('click', function() {
		    if ($(this).hasClass('chfade')) {
		      $(".p_fadeout").fadeIn(200);
		    }else{
		      $(".p_fadeout").fadeOut(200);
		    }
		  });
		  $(".isprofit").on('click', function() {
			    if ($(this).hasClass('isprofiton')) {
			      $(".PriceBox").fadeIn(200);
			    }else{
			      $(".PriceBox").fadeOut(200);
			    }
			  });
		})
</script>
</body>
</html>