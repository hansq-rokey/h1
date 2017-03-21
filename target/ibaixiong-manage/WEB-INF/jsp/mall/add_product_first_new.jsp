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
<div class="right-part">
	<jsp:include page="../include/top_new.jsp"/>
 	<form action="/mall/product/save.html" method="post" id="form1">
    <div class="wrap calcHeight">
       <div class="m_content" style="padding-bottom: 100px;">
          <div class="p_title">
            <ul class="p_list">
              <li class="p_on"><span>1</span></li>
              <li><span>2</span></li>
              <li><span>3</span></li>
            </ul>
          </div>
        <div class="p_content" >
          <div class="content_list">
            <div class="content_mian">
              <div class="selectskin selectModel">
                <i class="selectpoint"></i>
                <span class="selectcontent data_id" data-id="">产品型号</span>
                <ul class="selectopation" style="display: none;">
                	<c:forEach items="${modelList}" var="model">
                        <li data-id="${model.id}">${model.name}+${model.code}</li>
                   	</c:forEach>
                </ul>
              </div>
              <div class="selectskin selecttemplate">
                <i class="selectpoint"></i>
                <span class="selectcontent template_id" data-id="">模板名称</span>
                <ul class="selectopation" style="display: none;">
                	<c:forEach items="${templates}" var="template">
                        <li data-id="${template.id}">${template.name}</li>
                   	</c:forEach>
                </ul>
              </div>
            </div>
            <div class="content_mian">
              <div class="p_check">
                   <label>是否上架</label>
                   <p><input type="radio" id="checkbox1" name="status" value="1" class="regular-checkbox" /><label for="checkbox1"></label>是</p>
                   <p><input type="radio" id="checkbox2" name="status" checked="checked" value="-1" class="regular-checkbox" /><label for="checkbox2"></label>否</p> 
                </div>   
                <div class="p_check">
                   <label>是否是白熊产品</label>
                   <p><input type="radio" id="checkbox3" name="isCompanyProduct" value="1" class="regular-checkbox" /><label for="checkbox3"></label>是</p>
                   <p><input type="radio" id="checkbox4" name="isCompanyProduct" checked="checked" value="0" class="regular-checkbox" /><label for="checkbox4"></label>否</p> 
               </div>
            </div>
            <div class="content_mian">
             <p class="ProfitInput">
             <!--  <span>总利润</span>
              <span><input type="text" class="p_Profit" id="" name="totalProfit"></span>
              <span>%</span> -->
              <span class="Profit_input_list Profit_input_list3"><label>市场价</label><input type="text" value="0" class="Price" name="price" id="_price"></span>
              <span class="Profit_input_list Profit_input_list2"><label>代理商提货价</label><input type="text" value="0" name="productPurchasePrice" class="Price" id="_pick"></span>
            </p>
            </div>
             <div class="content_mian">
              <div class="p_check">
                   <label>物料计算</label>
                    <p><input type="radio" id="checkbox13" name="isMaterialCalculate" value="1" class="regular-checkbox" /><label for="checkbox13"></label>是</p>
               		<p><input type="radio" id="checkbox14" name="isMaterialCalculate" checked="checked" value="0" class="regular-checkbox" /><label for="checkbox14"></label>否</p>
               </div>
               <div class="p_check">
                   <label>是否允许使用优惠券</label>
                   <p><input type="radio" id="checkbox11" name="specialType" value="1" class="regular-checkbox" /><label for="checkbox11"></label>是</p>
                   <p><input type="radio" id="checkbox12" name="specialType" checked="checked" value="0" class="regular-checkbox" /><label for="checkbox12"></label>否</p> 
               </div>
            </div>
             <div class="p_check">
                   <label style="font-weight: 400;">产品标签 </label>
                   <ul class="goodsbox">
                    <c:forEach items="${tags }" var="tag" varStatus="status">
                    	<li><input type="checkbox" id="check${status.count}" name="tagId" value="${tag.id }" class="regular-checkbox" /><label for="check${status.count}"></label>${tag.tagName }</li>
                    </c:forEach>
                    </ul>
               </div>
          </div>
          <div class="content_list">
              <div class="content_input">
                <label>产品特点</label>
                <input type="text" class="Feature" id="Feature" name="feature">
              </div>
            <div class="content_mian">
	              <div class="p_check">
	                  <label>是否允许下定金</label>
	                  <p><input type="radio" id="checkbox5" name="isUseDeposit" value="1" class="regular-checkbox chbox chfade" /><label for="checkbox5"></label>是</p>
	                  <p><input type="radio" id="checkbox6" checked="checked" name="isUseDeposit" value="0" class="regular-checkbox chbox" /><label for="checkbox6"></label>否</p> 
	              </div>
               <div class="p_check p_fadeout" style="display:none">
                 <label class="p_Unit">定金</label>
                 <input type="text" class="p_money2" name="depositMoney" id="">
                 <span >元</span>
               </div>
              </div>
              <div class="content_mian">
	              <div class="isSee_total" style="line-height: 4;">
	                  <label class="issee_label">可见性</label>
	                  <p><input type="checkbox" id="checkbox7" name="cDisplay" value="1" class="regular-checkbox" /><label for="checkbox7"></label><span class="Client">C端用户</span></p>
	                  <p><input type="checkbox" id="checkbox8" name="bDisplay" value="1" class="regular-checkbox" /><label for="checkbox8"></label><span class="Client">B端用户</span></p> 
	              </div>
              </div>
              <div class="content_mian">
	              <div class="p_check">
	                  <label>是否有区域利润</label>
	                  <p><input type="radio" id="checkbox9" name="isSetAreaProfit" value="1" class="regular-checkbox isprofit isprofiton" /><label for="checkbox9"></label>是</p>
	                  <p><input type="radio" id="checkbox10" name="isSetAreaProfit" checked="checked" value="0" class="regular-checkbox isprofit" /><label  for="checkbox10"></label>否</p> 
	              </div>
	             <div class="PriceBox">
                  <input type="text" class="Price Price1" name="areaMoney" id="">
                 </div>
              </div>
          </div>
        </div>  
        <p class="p_btn">
          <a class="p_btna" href="javascript:void(0);">发布</a>
          <a class="p_btnb" href="###">取消</a>
        </p>
    </div>
  </div>
  <input type="hidden" name="level" value="1" />
  <input type="hidden" name="categoryModelId" id="modelId" value="" />
  <input type="hidden" name="freightTemplateId" id="templateId" value="" />
  </form>
</div>
<script type="text/javascript" src="/plug_new/jQuery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="/plug_new/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="/js_new/common.js"></script>
<script type="text/javascript">
	 //输入框焦点事件
	 $(document).on('focus', '#Feature', function() {
	    $(this).prev("label").css({top: '-17px',color: '#2CA3E5'});
	    $(this).addClass('ab');
	 });
	 $(document).on('blur', '#Feature', function() {
	  var _htmlc=$(this).val();
	    if (_htmlc=="") {
	          $(this).prev("label").css({top: '-2px',color: '#5a6677'});
	          $(this).removeClass('ab');
	    }else{
	          $(this).prev("label").css({top: '-17px',color: '#2CA3E5'});
	          $(this).addClass('ab');
	    }
	 });
	$(function(){
		$(".p_btna").click(function(){
			var p_m=$(".data_id").attr("data-id");//产品型号
			var _price=$("#_price").val();//市场价
			var _pick=$("#_pick").val();//代理商提货价
			if(p_m == "" || _price == "" || _pick == ""){
				$(".data_id").css({"color":"#f00"});
				return false;
			}
			$("#form1").submit();
		});
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
