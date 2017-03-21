package org.apache.jsp.WEB_002dINF.jsp.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class tb_005forder_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\r\n");
      out.write("    <link href=\"../../../plug/bootstrap/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    <link href=\"../../../css/pubstyle.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    <link href=\"../../../css/order-manage.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    <script src=\"../../../plug/jQuery/jquery-1.8.3.min.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("    <script src=\"../../../js/public.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("    <script src=\"../../../js/aftersalemanage.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("    <script src=\"../../../plug/adddatetime.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("    <script src=\"../../../plug/jQuery/LodopFuncs.js\" type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"../../../js/base.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("    <title>订单管理</title>\r\n");
      out.write("    <style class=\"style1\">\r\n");
      out.write(".code-input {\r\n");
      out.write("    width: 300px;\r\n");
      out.write("    height: 40px;\r\n");
      out.write("    border: 1px solid #dcdcdc;\r\n");
      out.write("    margin-top: 30px;\r\n");
      out.write("    margin-left: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".print-bigbox {\r\n");
      out.write("    width: 350px;\r\n");
      out.write("    height: 350px;\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".print-logo {\r\n");
      out.write("    float: left;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".print-box {\r\n");
      out.write("    border: 2px solid black;\r\n");
      out.write("    width: 348px;\r\n");
      out.write("    height: 340px;\r\n");
      out.write("    margin: 5px;\r\n");
      out.write("    margin-left:-5px;\r\n");
      out.write("    padding: 7px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".header {\r\n");
      out.write("    padding-bottom: 5px;\r\n");
      out.write("    border-bottom: 1px solid black;\r\n");
      out.write("    overflow: hidden;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".print-title {\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("    font-size: 16px;\r\n");
      out.write("    float: right;\r\n");
      out.write("    vertical-align: middle;\r\n");
      out.write("    line-height: 75px;\r\n");
      out.write("    float: right;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".print-product {\r\n");
      out.write("    margin-top: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".print-product p, .text, .textname {\r\n");
      out.write("    vertical-align: middle;\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("print-product p {\r\n");
      out.write("    margin-left: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".prod-name {\r\n");
      out.write("    float: left;\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".text {\r\n");
      out.write("    width: 240px;\r\n");
      out.write("    float: left;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".textname {\r\n");
      out.write("    vertical-align: top;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".print-code {\r\n");
      out.write("    margin-top: 15px;\r\n");
      out.write("    padding-bottom: 20px;\r\n");
      out.write("    border-bottom: 1px solid #000;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".two-dimension {\r\n");
      out.write("    width: 330px;\r\n");
      out.write("    height: 66px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".prod-table {\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    width: 300px;\r\n");
      out.write("}\r\n");
      out.write(".prod-table tr td{\r\n");
      out.write("    height:20px;\r\n");
      out.write("    padding:0 5px;\r\n");
      out.write("    font-size:12px;\r\n");
      out.write("}\r\n");
      out.write(".line {\r\n");
      out.write("    height: 0px;\r\n");
      out.write("    width: 90px;\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    margin-bottom: 5px;\r\n");
      out.write("    float: left;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".company-name {\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    width: 160px;\r\n");
      out.write("    background: #fff;\r\n");
      out.write("    padding: 0 10px;\r\n");
      out.write("    margin: 0 auto;\r\n");
      out.write("    font-size:12px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".print-footer {\r\n");
      out.write("    line-height: 35px;\r\n");
      out.write("    height: 35px;\r\n");
      out.write("    margin-top: -17px;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write(".fail-remind{\r\n");
      out.write("    font-size:18px;\r\n");
      out.write("    line-height:20px;\r\n");
      out.write("    width:100%;\r\n");
      out.write("    text-align:center;\r\n");
      out.write("    position:absolute;\r\n");
      out.write("    bottom:10px;\r\n");
      out.write("    left:0;\r\n");
      out.write("}\r\n");
      out.write(".only-code{\r\n");
      out.write("    position:relative;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <section>\r\n");
      out.write("        <div class=\"content\" style=\"margin-top: 20px;\">\r\n");
      out.write("            <form action=\"/tbOrder/list.html\" method=\"post\">\r\n");
      out.write("                <input type=\"text\" name=\"keywords\" class=\"packing-code\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${keywords }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"用户名或订单号\">\r\n");
      out.write("                <input type=\"text\" name=\"start\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${start }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  class=\"strat-time datetimepicker startdata\" placeholder=\"开始时间\" onclick=\"SelectDate(this,'yyyy-MM-dd')\">\r\n");
      out.write("                <span>至</span>\r\n");
      out.write("                <input type=\"text\" name=\"end\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${end }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  class=\"end time datetimepicker startdata\" placeholder=\"结束时间\" onclick=\"SelectDate(this,'yyyy-MM-dd')\">\r\n");
      out.write("                <input type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" id=\"status\" name=\"status\">\r\n");
      out.write("                <input type=\"hidden\" name=\"pageNo\" id=\"pageNo\" value=\"\">\r\n");
      out.write("                <input type=\"submit\" class=\"search\" value=\"搜索\">         \r\n");
      out.write("            </form>\r\n");
      out.write("        </div>  \r\n");
      out.write("        <ul class=\"partlist\">\r\n");
      out.write("            <li>\r\n");
      out.write("                <a href=\"/tbOrder/list.html\"><span class=\"switch ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\">全部</span></a>\r\n");
      out.write("                    ");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </li>\r\n");
      out.write("            <li>\r\n");
      out.write("                <a href=\"/tbOrder/list.html?status=20\"><span class=\"switch ");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("\" >待发货</span></a>\r\n");
      out.write("                    ");
      if (_jspx_meth_c_005fif_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </li>\r\n");
      out.write("            <li>\r\n");
      out.write("                <a href=\"/tbOrder/list.html?status=30\"><span class=\"switch ");
      if (_jspx_meth_c_005fif_005f4(_jspx_page_context))
        return;
      out.write("\" >配货中</span></a>\r\n");
      out.write("                    ");
      if (_jspx_meth_c_005fif_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </li>\r\n");
      out.write("            <li>\r\n");
      out.write("                <a href=\"/tbOrder/list.html?status=40\"><span class=\"switch ");
      if (_jspx_meth_c_005fif_005f6(_jspx_page_context))
        return;
      out.write("\" >已发货</span></a>\r\n");
      out.write("                    ");
      if (_jspx_meth_c_005fif_005f7(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </li>\r\n");
      out.write("            <li>\r\n");
      out.write("                <a href=\"/tbOrder/list.html?status=50\"><span class=\"switch ");
      if (_jspx_meth_c_005fif_005f8(_jspx_page_context))
        return;
      out.write("\" >已签收</span></a>\r\n");
      out.write("                    ");
      if (_jspx_meth_c_005fif_005f9(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </li>\r\n");
      out.write("            \r\n");
      out.write("        </ul>\r\n");
      out.write("    </section>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!--弹窗-->\r\n");
      out.write("<div class=\"pop\">\r\n");
      out.write("    <div class=\"popbg\"></div>\r\n");
      out.write("    <div class=\"layel\" style=\"width:600px\">\r\n");
      out.write("        <h3 class=\"poptitle\">修改支付价格<i class=\"closeicon\"></i> </h3>\r\n");
      out.write("        <div><laber>订单号:</laber><input type=\"text\" id=\"Order_number\"></div>\r\n");
      out.write("        <table class=\"commodity_box\">\r\n");
      out.write("         <thead>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td  class=\"table_w60\"><laber>商品名称</laber></td>\r\n");
      out.write("                <td  class=\"table_w15\"><laber>商品数量</laber></td>\r\n");
      out.write("                <td  class=\"table_w25\"><laber>商品价格</laber></td>\r\n");
      out.write("             </tr>\r\n");
      out.write("         </thead>\r\n");
      out.write("        </table>\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <span class=\"infortype tr\" style=\"width: 100px;\">订单总价：</span>\r\n");
      out.write("            <input type=\"text\" id=\"price\">\r\n");
      out.write("            <input type=\"hidden\" id=\"orderNumber\">\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <input type=\"button\" value=\"确认\" onclick=\"update()\" class=\"confirm\">\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function toUpdate(orderNumber,shouldPayMoney){//总价格\r\n");
      out.write("    var tbody=\"\";\r\n");
      out.write("      $.ajax({\r\n");
      out.write("           url: \"/tbOrder/toUpdate.html\",\r\n");
      out.write("           type: \"post\",\r\n");
      out.write("           data:{orderNumber:orderNumber},\r\n");
      out.write("           dataType:\"json\",\r\n");
      out.write("           cache:false,\r\n");
      out.write("           success: function(obj){\r\n");
      out.write("              $.each(obj.result,function(i,item){\r\n");
      out.write("            \t  $(\"#Order_number\").val(orderNumber);\r\n");
      out.write("            \t  var cData = item.id+\"-\"+item.totalPrice;\r\n");
      out.write("                  var trs=\"\";\r\n");
      out.write("                  trs += \"<tr> <td> \"+item.productTitle+item.productModelFormatName+\" </td><td> \"+item.num +\" </td><td><input class='pic' data-id='\"+item.id+\"' type='text' value='\"+item.totalPrice+\"'></td><input type='hidden' name='c_data' value='\"+cData+\"'></tr>\";\r\n");
      out.write("                  tbody +=trs;\r\n");
      out.write("              });\r\n");
      out.write("              $(\".commodity_box\").append(tbody);\r\n");
      out.write("           }\r\n");
      out.write("        });\r\n");
      out.write("     $(\"#orderNumber\").val(orderNumber);\r\n");
      out.write("     $(\"#price\").val(shouldPayMoney);\r\n");
      out.write("     $('.pop').show();\r\n");
      out.write("}\r\n");
      out.write("function update(){\r\n");
      out.write("    var orderNumber = $(\"#orderNumber\").val();\r\n");
      out.write("    var price = $(\"#price\").val();\r\n");
      out.write("    if( orderNumber == null || orderNumber  ==undefined || orderNumber  == ''){\r\n");
      out.write("        alertLayel(\"编码不可为空!\");\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("    var r = /^\\d+(\\.\\d+)?$/;　　//非负浮点数（正浮点数 + 0）\r\n");
      out.write("    if(!r.test(price)){  \r\n");
      out.write("        alert(\"只支持非负浮点数!\");\r\n");
      out.write("        return false;\r\n");
      out.write("    }  \r\n");
      out.write("  //获取订单下所有规格商品的id和变更金额组合\r\n");
      out.write("    var d_name = \"\";\r\n");
      out.write("\tvar d_value = document.getElementsByName(\"c_data\");\r\n");
      out.write("\tfor(var i=0;i<d_value.length;i++){\r\n");
      out.write("\t\tif(d_value[i].value==null || d_value[i].value==\"\"){\r\n");
      out.write("\t\t\tlayer.msg(\"请先点击确认按钮再保存数据！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\td_name+=d_value[i].value+\",\";\r\n");
      out.write("\t}\r\n");
      out.write("    $.ajax({\r\n");
      out.write("       url: \"/tbOrder/updatePrice.html\",\r\n");
      out.write("       data:{\"orderNumber\":orderNumber,\"price\":price,\"dName\":d_name},\r\n");
      out.write("       type: \"post\",\r\n");
      out.write("       dataType:\"json\",\r\n");
      out.write("       cache:false,\r\n");
      out.write("       success: function(obj){\r\n");
      out.write("            if ( !checkCode( obj ) ) {\r\n");
      out.write("                return;\r\n");
      out.write("            }\r\n");
      out.write("            if ( obj.code == 0 ) {\r\n");
      out.write("                alertLayel(\"修改成功!\");\r\n");
      out.write("                $('.pop').hide();\r\n");
      out.write("                $(\".commodity_box tbody\").remove();\r\n");
      out.write("                $(\"#price\").val('');\r\n");
      out.write("                $('.search').click();\r\n");
      out.write("            }\r\n");
      out.write("       }\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("function toUpdateStatus(orderNumber){\r\n");
      out.write("    if( orderNumber == null || orderNumber  ==undefined || orderNumber  == ''){\r\n");
      out.write("        alertLayel(\"编码不可为空!\");\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("    $.ajax({\r\n");
      out.write("           url: \"/tbOrder/updateStatus.html?orderNumber=\"+orderNumber,\r\n");
      out.write("           type: \"post\",\r\n");
      out.write("           dataType:\"json\",\r\n");
      out.write("           cache:false,\r\n");
      out.write("           success: function(obj){\r\n");
      out.write("                if ( !checkCode( obj ) ) {\r\n");
      out.write("                    return;\r\n");
      out.write("                }\r\n");
      out.write("                if ( obj.code == 0 ) {\r\n");
      out.write("                    alertLayel(\"修改成功!\");\r\n");
      out.write("                    $('.pop').hide();\r\n");
      out.write("                    $('.search').click();\r\n");
      out.write("                }\r\n");
      out.write("           }\r\n");
      out.write("        });\r\n");
      out.write("}\r\n");
      out.write("/* function computed(id,pic){\r\n");
      out.write("    if(pic > 0){\r\n");
      out.write("         $.ajax({\r\n");
      out.write("             url: \"/tbOrder/updateItemPrice.html?id=\"+id+\"&itemPrice=\"+pic,\r\n");
      out.write("             type: \"post\",\r\n");
      out.write("             dataType:\"json\",\r\n");
      out.write("             cache:false,\r\n");
      out.write("             success: function(obj){\r\n");
      out.write("             }\r\n");
      out.write("          });\r\n");
      out.write("    }else{\r\n");
      out.write("        return;\r\n");
      out.write("    }\r\n");
      out.write("} */\r\n");
      out.write("$(document).on('blur','.commodity_box .pic', function(event) {\r\n");
      out.write("    var that=$(this);\r\n");
      out.write("    var pic=parseFloat(that.val());\r\n");
      out.write("    var picid=that.attr(\"data-id\");\r\n");
      out.write("    var sum=0;\r\n");
      out.write("    $(\".commodity_box .pic\").each(function(index, el) {\r\n");
      out.write("        var tav=$(this).val();\r\n");
      out.write("        if(tav){\r\n");
      out.write("         sum =parseFloat(tav)+parseFloat(sum);\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("    $(\"#price\").val(sum);\r\n");
      out.write("    var da = that.parent().siblings(\"input[type='hidden']\").val(picid+\"-\"+pic);\r\n");
      out.write("   // computed(picid,pic);\r\n");
      out.write("    \r\n");
      out.write(" });\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(162,65) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("switched");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(163,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                        <div class=\"inforbox selectinforbox\">\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "tb_order_list_status.jsp", out, false);
        out.write("\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/pages.jsp", out, false);
        out.write("\r\n");
        out.write("                        </div>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(171,75) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==20}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("switched");
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(172,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==20 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                        <div class=\"inforbox selectinforbox\">\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "tb_order_list_status.jsp", out, false);
        out.write("\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/pages.jsp", out, false);
        out.write("\r\n");
        out.write("                        </div>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f4.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(180,75) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==30}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
    if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("switched");
        int evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f5.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(181,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==30 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f5 = _jspx_th_c_005fif_005f5.doStartTag();
    if (_jspx_eval_c_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                        <div class=\"inforbox selectinforbox\">\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "tb_order_list_status.jsp", out, false);
        out.write("\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/pages.jsp", out, false);
        out.write("\r\n");
        out.write("                        </div>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f6 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f6.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(189,75) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f6.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==40}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f6 = _jspx_th_c_005fif_005f6.doStartTag();
    if (_jspx_eval_c_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("switched");
        int evalDoAfterBody = _jspx_th_c_005fif_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f7 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f7.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(190,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f7.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==40 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f7 = _jspx_th_c_005fif_005f7.doStartTag();
    if (_jspx_eval_c_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                        <div class=\"inforbox selectinforbox\">\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "tb_order_list_status.jsp", out, false);
        out.write("\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/pages.jsp", out, false);
        out.write("\r\n");
        out.write("                        </div>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f8 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f8.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(198,75) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f8.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==50}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f8 = _jspx_th_c_005fif_005f8.doStartTag();
    if (_jspx_eval_c_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("switched");
        int evalDoAfterBody = _jspx_th_c_005fif_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f9 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f9.setParent(null);
    // /WEB-INF/jsp/order/tb_order_list.jsp(199,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f9.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status==50 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f9 = _jspx_th_c_005fif_005f9.doStartTag();
    if (_jspx_eval_c_005fif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                        <div class=\"inforbox selectinforbox\">\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "tb_order_list_status.jsp", out, false);
        out.write("\r\n");
        out.write("                            ");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/pages.jsp", out, false);
        out.write("\r\n");
        out.write("                        </div>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f9);
    return false;
  }
}
