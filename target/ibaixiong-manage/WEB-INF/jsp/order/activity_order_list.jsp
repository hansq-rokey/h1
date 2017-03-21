<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/order-manage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/aftersalemanage.js" type="text/javascript" ></script>
    <script src="../../../plug/adddatetime.js" type="text/javascript" ></script>
    <script src="../../../plug/jQuery/LodopFuncs.js" type="text/javascript"></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>货到付款订单</title>
    <style class="style1">
.code-input {
    width: 300px;
    height: 40px;
    border: 1px solid #dcdcdc;
    margin-top: 30px;
    margin-left: 10px;
}

.print-bigbox {
    width: 350px;
    height: 350px;
    display: inline-block;
}

.print-logo {
    float: left;
}

.print-box {
    border: 2px solid black;
    width: 348px;
    height: 340px;
    margin: 5px;
    margin-left:-5px;
    padding: 7px;
}

.header {
    padding-bottom: 5px;
    border-bottom: 1px solid black;
    overflow: hidden;
}

.print-title {
    font-weight: bold;
    font-size: 16px;
    float: right;
    vertical-align: middle;
    line-height: 75px;
    float: right;
}

.print-product {
    margin-top: 20px;
}

.print-product p, .text, .textname {
    vertical-align: middle;
    display: inline-block;
}

print-product p {
    margin-left: 10px;
}

.prod-name {
    float: left;
    display: inline-block;
}

.text {
    width: 240px;
    float: left;
}

.textname {
    vertical-align: top;
}

.print-code {
    margin-top: 15px;
    padding-bottom: 20px;
    border-bottom: 1px solid #000;
}

.two-dimension {
    width: 330px;
    height: 66px;
}

.prod-table {
    text-align: center;
    width: 300px;
}
.prod-table tr td{
    height:20px;
    padding:0 5px;
    font-size:12px;
}
.line {
    height: 0px;
    width: 90px;
    display: inline-block;
    margin-bottom: 5px;
    float: left;
}

.company-name {
    display: inline-block;
    width: 160px;
    background: #fff;
    padding: 0 10px;
    margin: 0 auto;
    font-size:12px;
}

.print-footer {
    line-height: 35px;
    height: 35px;
    margin-top: -17px;
    text-align: center;
}
.fail-remind{
    font-size:18px;
    line-height:20px;
    width:100%;
    text-align:center;
    position:absolute;
    bottom:10px;
    left:0;
}
.only-code{
    position:relative;
}
</style>
</head>
<body>
    <section>
        <div class="content" style="margin-top: 20px;">
            <form action="/activityOrder/list.html" method="post">
                <input type="text" name="keywords" class="packing-code" value="${keywords }" placeholder="用户名或订单号">
                <input type="text" name="start" value="${start }"  class="strat-time datetimepicker startdata" placeholder="开始时间" onclick="SelectDate(this,'yyyy-MM-dd')">
                <span>至</span>
                <input type="text" name="end" value="${end }"  class="end time datetimepicker startdata" placeholder="结束时间" onclick="SelectDate(this,'yyyy-MM-dd')">
                <input type="hidden" value="${status}" id="status" name="status">
                <input type="hidden" name="pageNo" id="pageNo" value="">
                <input type="submit" class="search" value="搜索">         
            </form>
        </div>  
        <ul class="partlist">
            <li>
                <a href="/activityOrder/list.html"><span class="switch <c:if test="${status==null}">switched</c:if>">全部</span></a>
                    <c:if test="${status==null}">
                        <div class="inforbox selectinforbox">
                            <jsp:include page="activity_order_list_status.jsp"></jsp:include>
                            <jsp:include page="../include/pages.jsp"></jsp:include>
                        </div>
                    </c:if>
            </li>
            <li>
                <a href="/activityOrder/list.html?status=20"><span class="switch <c:if test="${status==20}">switched</c:if>" >待发货</span></a>
                    <c:if test="${status==20 }">
                        <div class="inforbox selectinforbox">
                            <jsp:include page="activity_order_list_status.jsp"></jsp:include>
                            <jsp:include page="../include/pages.jsp"></jsp:include>
                        </div>
                    </c:if>
            </li>
            <li>
                <a href="/activityOrder/list.html?status=30"><span class="switch <c:if test="${status==30}">switched</c:if>" >配货中</span></a>
                    <c:if test="${status==30 }">
                        <div class="inforbox selectinforbox">
                            <jsp:include page="activity_order_list_status.jsp"></jsp:include>
                            <jsp:include page="../include/pages.jsp"></jsp:include>
                        </div>
                    </c:if>
            </li>
            <li>
                <a href="/activityOrder/list.html?status=40"><span class="switch <c:if test="${status==40}">switched</c:if>" >已发货</span></a>
                    <c:if test="${status==40 }">
                        <div class="inforbox selectinforbox">
                            <jsp:include page="activity_order_list_status.jsp"></jsp:include>
                            <jsp:include page="../include/pages.jsp"></jsp:include>
                        </div>
                    </c:if>
            </li>
            <li>
                <a href="/activityOrder/list.html?status=50"><span class="switch <c:if test="${status==50}">switched</c:if>" >已签收</span></a>
                    <c:if test="${status==50 }">
                        <div class="inforbox selectinforbox">
                            <jsp:include page="activity_order_list_status.jsp"></jsp:include>
                            <jsp:include page="../include/pages.jsp"></jsp:include>
                        </div>
                    </c:if>
            </li>
            <li>
                <a href="/activityOrder/list.html?status=60"><span class="switch <c:if test="${status==60}">switched</c:if>" >关闭订单</span></a>
                    <c:if test="${status==60 }">
                        <div class="inforbox selectinforbox">
                            <jsp:include page="activity_order_list_status.jsp"></jsp:include>
                            <jsp:include page="../include/pages.jsp"></jsp:include>
                        </div>
                    </c:if>
            </li>
            <li>
                <a href="/activityOrder/list.html?status=80"><span class="switch <c:if test="${status==80}">switched</c:if>" >有效订单</span></a>
                    <c:if test="${status==80 }">
                        <div class="inforbox selectinforbox">
                            <jsp:include page="activity_order_list_status.jsp"></jsp:include>
                            <jsp:include page="../include/pages.jsp"></jsp:include>
                        </div>
                    </c:if>
            </li>
        </ul>
    </section>
</div>

<!--弹窗-->
<div class="pop">
    <div class="popbg"></div>
    <div class="layel" style="width:600px">
        <h3 class="poptitle">修改支付价格<i class="closeicon"></i> </h3>
        <div><laber>订单号:</laber><input type="text" id="Order_number"></div>
        <table class="commodity_box">
         <thead>
            <tr>
                <td  class="table_w60"><laber>商品名称</laber></td>
                <td  class="table_w15"><laber>商品数量</laber></td>
                <td  class="table_w25"><laber>商品价格</laber></td>
             </tr>
         </thead>
        </table>
        <div class="row">
            <span class="infortype tr" style="width: 100px;">订单总价：</span>
            <input type="text" id="price">
            <input type="hidden" id="orderNumber">
        </div>
        <div class="row">
            <input type="button" value="确认" onclick="update()" class="confirm">
        </div>
    </div>
</div>
<script type="text/javascript">
function toUpdate(orderNumber,shouldPayMoney){//总价格
    var tbody="";
      $.ajax({
           url: "/tbOrder/toUpdate.html",
           type: "post",
           data:{orderNumber:orderNumber},
           dataType:"json",
           cache:false,
           success: function(obj){
              $.each(obj.result,function(i,item){
            	  $("#Order_number").val(orderNumber);
                  var trs="";
                  trs += "<tr> <td> "+item.productTitle+item.productModelFormatName+" </td><td> "+item.num +" </td><td><input class='pic' data-id='"+item.id+"' type='text' value='"+item.totalPrice+"'></td></tr>";
                  tbody +=trs;
              });
              $(".commodity_box").append(tbody);
           }
        });
     $("#orderNumber").val(orderNumber);
     $("#price").val(shouldPayMoney);
     $('.pop').show();
}
function update(){
    var orderNumber = $("#orderNumber").val();
    var price = $("#price").val();
    if( orderNumber == null || orderNumber  ==undefined || orderNumber  == ''){
        alertLayel("编码不可为空!");
        return false;
    }
    var r = /^\d+(\.\d+)?$/;　　//非负浮点数（正浮点数 + 0）
    if(!r.test(price)){  
        alert("只支持非负浮点数!");
        return false;
    }  
    $.ajax({
       url: "/tbOrder/updatePrice.html?orderNumber="+orderNumber+"&price="+price,
       type: "post",
       dataType:"json",
       cache:false,
       success: function(obj){
            if ( !checkCode( obj ) ) {
                return;
            }
            if ( obj.code == 0 ) {
                alertLayel("修改成功!");
                $('.pop').hide();
                $(".commodity_box tbody").remove();
                $("#price").val('');
                $('.search').click();
            }
       }
    });
}
function toUpdateStatus(orderNumber){
    if( orderNumber == null || orderNumber  ==undefined || orderNumber  == ''){
        alertLayel("编码不可为空!");
        return false;
    }
    $.ajax({
           url: "/activityOrder/sure.html?orderNumber="+orderNumber,
           type: "post",
           dataType:"json",
           cache:false,
           success: function(obj){
                if ( !checkCode( obj ) ) {
                    return;
                }
                if ( obj.code == 0 ) {
                    alertLayel("修改成功!");
                    $('.pop').hide();
                    $('.search').click();
                }
           }
        });
}
function computed(id,pic){
    if(pic > 0){
         $.ajax({
             url: "/tbOrder/updateItemPrice.html?id="+id+"&itemPrice="+pic,
             type: "post",
             dataType:"json",
             cache:false,
             success: function(obj){
             }
          });
    }else{
        return;
    }
}
$(document).on('blur','.commodity_box .pic', function(event) {
    var that=$(this);
    var pic=parseFloat(that.val());
    var picid=that.attr("data-id");
    var sum=0;
    $(".commodity_box .pic").each(function(index, el) {
        var tav=$(this).val();
        if(tav){
         sum =parseFloat(tav)+parseFloat(sum);
        }
    });
    $("#price").val(sum);
    computed(picid,pic);
    
 });
</script>
</body>
</html>
