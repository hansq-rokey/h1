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
    <link href="../../../css/aftersalemanage.css" rel="stylesheet" type="text/css">
    <script src="../../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="../../../js/public.js" type="text/javascript" ></script>
    <script src="../../../js/aftersalemanage.js" type="text/javascript" ></script>
    <title>售后管理</title>
    <script type="text/javascript">
    	function toList(type){
    		location.href = "/ccm/question/queryList.html?queryType="+type;
    	}
    </script>
</head>
<body>
    <section>
        <ul >
            <li>
                <div class="inforbox selectinforbox" style="margin-top: -30px;">
                    <div class="row">
                        <span class="light"><a href="#" onclick="toList(${queryType})">退货单</a>></span>详情
                    </div>
                    <div class="row" style="width: 794px;">
                        <img src="../images/returncargo01.png">
                    </div>
                    <div class="row tc" style="width: 794px;">
                        <div class="col-lg-2">3015-06-06<br>12:22:23</div>
                        <div class="col-lg-2">3015-06-06<br>12:22:23</div>
                        <div class="col-lg-2">3015-06-06<br>12:22:23</div>
                        <div class="col-lg-2">3015-06-06<br>12:22:23</div>
                        <div class="col-lg-2">3015-06-06<br>12:22:23</div>
                        <div class="col-lg-2">3015-06-06<br>12:22:23</div>
                    </div>
                    <div class="row">
                        <table class="refundcargolist">
                            <thead>
                            <tr>
                                <td>白熊号</td>
                                <td>账号</td>
                                <td>昵称</td>
                                <td>组</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><img src="../images/loginbg.png" class="returnprodimg"> </td>
                                <td>1232655</td>
                                <td>1232655</td>
                                <td>1232655</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </li>
        </ul>
    </section>
</body>
</html>