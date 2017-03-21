<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../../../plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../css/pubstyle.css" rel="stylesheet" type="text/css">
    <link href="../../../css/commanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../plug/setDate.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/basedata.js" type="text/javascript" ></script>
    <script src="../../../js/base.js" type="text/javascript" ></script>
    <title>钉钉管理</title>
    <style>
   	.setDate,.addSetDate{background: #ff6200;
		    color: white;
		    width: 100px;
		    height: 30px;
    }
    .addSetDate{
    	margin-top:20px;
    	margin-left:700px;
    }
    #calendarTable tr td{
    	border:1px solid #fff;
    	background:#ff0000;
    	height:24xp;
    }
    </style>
</head>
<body>
    <section>
        <ul class="partlist" style="border-bottom: 0px;margin-top: -45px;">
            <li>
                <div class="inforbox selectinforbox">
                	<input type="button" class="addSetDate" value="新增">
                    <div class="row">
                        <table class="setDateList" style="width:800px;">
                            <thead>
                            <tr>
                                <td width="200">年月</td>
                                <td width="400">工作日</td>
                                <td width="200">操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list }" var="item">
	                            <tr>
	                                <td class="date"><span class="year">${item.yyyy }</span>.<span class="month"><c:if test="${item.mm<10 }">0${item.mm }</c:if><c:if test="${item.mm>9 }">${item.mm }</c:if></span></td>
	                                <td class="day">${item.dayCount }</td>
	                                <td><input type="button" value="编辑" data-year="${item.yyyy }" data-month="<c:if test="${item.mm<10 }">0${item.mm }</c:if><c:if test="${item.mm>9 }">${item.mm }</c:if>"  class="setDate" data-value="${item.yyyy }-${item.mm }-01" placeholder="${item.yyyy }-<c:if test='${item.mm<10 }'>0${item.mm }</c:if><c:if test='${item.mm>9 }'>${item.mm }</c:if>-01"></td>
	                            </tr>
                            </c:forEach>
							<c:if test="${fn:length(list)==0 }">
								<tr>
	                                <td class="date"></td>
	                                <td class="day"></td>
	                                <td><input type="button" value="编辑"  class="setDate" placeholder=""></td>
	                            </tr>
							</c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
    <script>
    $(document).ready(function(){
    	$(document).on('click','.setDate',function(){
    		if($('#ContainerPanel').css("display")=='none'||$('#ContainerPanel').length==0){
    			$(this).parent().parent().addClass('cur');
            	SelectDate(this,"yyyy-MM-dd");
           		var year=$(this).parent().parent().find('.year').text();
           		var month=$(this).parent().parent().find('.month').text();
           		if(year&&month){
               		$("#calendarYear option").each(function(){
         			   if($(this).text() === year){
         			      $(this).attr('selected', true);
         			   }else{
          			      $(this).attr('selected', false);
         			   }
         			});
         			$("#calendarMonth option").each(function(){
         		   		if($(this).text() === month){
         		      		$(this).attr('selected', true);
         		   		}else{
            			      $(this).attr('selected', false);
          			   }
         			});
           		}

    			$.ajax({
                    type: 'POST',
                    url: '/ding/date/days.html',
                    data: {year:year,month:month},
                    datatype: "text",
                    success: function(data){
                    	data=JSON.parse(data);
                    	if(data.code==1){
                    		var days=data.result.days;
                            for(i=0;i<days.length;i++){
                            	$('#calendarTable tr td').each(function(){
                            		var text=$(this).text();
                            		if(text===days[i]){
                            			$(this).addClass('on').css('background','#ffcc00');
                            		}
                            	})
                        	}
                    	}
                    }
                });
    		}
    	})
    	function change(){
       		var year=$('.cur').find('.year').text();
       		var month=$('.cur').find('.month').text();
    		var mm=$('#calendarMonth').find("option:selected").text();
    		var yy=$('#calendarYear').find("option:selected").text();
    		if(year==yy&&month==mm){
    			$.ajax({
                    type: 'POST',
                    url: '/ding/date/days.html',
                    data: {year:year,month:month},
                    datatype: "text",
                    success: function(data){
                    	data=JSON.parse(data);
                    	if(data.code==1){
                    		var days=data.result.days;
                            for(i=0;i<days.length;i++){
                            	$('#calendarTable tr td').each(function(){
                            		var text=$(this).text();
                            		if(text===days[i]){
                            			$(this).addClass('on').css('background','#ffcc00');
                            		}
                            	})
                        	}
                    	}
                    }
                });
    		}else{
    			$('.on').removeClass('on');
    		}
    	}
    	$(document).on('change','#calendarMonth,#calendarYear',function(){
    		change();
    	})
    	$(document).on('click','#prevMonth,#nextMonth',function(){
    		var mm=$('#calendarMonth').find("option:selected").val();
    		var yy=$('#calendarYear').find("option:selected").val();
    		
    		change();
    	})
        $(document).on('click','#calendarSure',function(){
            var days=[];
            var year=$('#calendarYear').find("option:selected").text();
            var month=$('#calendarMonth').find("option:selected").text();
            $('.on').each(function(){
                var day=$(this).text();
                days.push(day);
                $(this).removeClass('on');
            })
            var html='<span class="year">'+year+'</span>.<span class="month">'+month+'</span>';
            $('.cur').find('.date').html(html);
            $('.cur').find('.day').text(days.length);
            $('#ContainerPanel').hide();
            $.ajax({
                type: 'POST',
                url: '/ding/date/save.html',
                data: {year:year,month:month,days:days.join(',')},
                datatype: "text",
                success: function(data){
                	data=JSON.parse(data);
                	//alert(data.code);
                    if(data.code==1){//成功
                    	//$('#ContainerPanel').remove();
                        $('.cur').find('.setDate').attr('placeholder',year+"-"+month+"-01");
                        $('.cur').removeClass('cur');
                    }else{//失败
                    	
                    }
                }
            });
        })
        $(document).on('click','#calendarClear',function(){
            $('.on').removeClass('on').css('background-color','#efefef');
        })
        $(document).on('click','#calendarClose',function(){
            $('.on').removeClass('on').css('background-color','#efefef');
            $('.cur').removeClass('cur');
            $('#ContainerPanel').hide();
            //$('#calendarMonth').empty();
        })
        $('.addSetDate').on('click',function(){
        	var html='<tr><td class="date"></td><td class="day"></td><td><input type="button" value="编辑" class="setDate"></td></tr>';
        	$('.setDateList').prepend(html);
        })
    })
    </script>
</body>
</html>
