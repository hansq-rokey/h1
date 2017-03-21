package org.apache.jsp.WEB_002dINF.jsp.crm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class orderList_005fnew_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.release();
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n");
      out.write("    <link href=\"/plug_new/bootstrap/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\t<link href=\"/plug_new/bootstrap/daterangepicker-bs3.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\t<link href=\"/css_new/common.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\t<link href=\"/css_new/adress.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\t<link href=\"/css_new/addCity.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("\t<script src=\"/plug_new/jQuery/jquery-1.9.1.min.js\" type=\"text/javascript\" ></script>\n");
      out.write("\t<script src=\"/plug_new/bootstrap/bootstrap.min.js\" type=\"text/javascript\" ></script>\n");
      out.write("\t<script src=\"/plug_new/bootstrap/moment.js\" type=\"text/javascript\" ></script>\n");
      out.write("\t<script src=\"/plug_new/bootstrap/daterangepicker.js\" type=\"text/javascript\" ></script>\n");
      out.write("\t<script src=\"/js_new/common.js\" type=\"text/javascript\" ></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/js_new/area.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"/js_new/location.js\"></script>\n");
      out.write("\t<script src=\"/js_new/addCity.js\" type=\"text/javascript\" ></script>\n");
      out.write("\t<script src=\"/js_new/base.js\" type=\"text/javascript\" ></script>\n");
      out.write("\t<script src=\"../../../plug_new/layer/layer.js\" type=\"text/javascript\" ></script>\n");
      out.write("    <title>城市运营中心详情</title>\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("    function showPage(number){\n");
      out.write("    \tvar id = $(\"#id\").val();\n");
      out.write("    \twindow.location.href=\"/crm/cityMerchant/orderDetails.html?pageNo=\"+number+\"&id=\"+id;\n");
      out.write("\t}\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"city_box\">\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/left_new.jsp", out, false);
      out.write("\n");
      out.write("\t<div class=\"right-bottom\">\n");
      out.write("\t\t<div class=\"right-top\" style=\"left:280px;\">\n");
      out.write("\t        <div class=\"input-list\" style=\"display:block\">\n");
      out.write("\t           <!--  <input type=\"text\">\n");
      out.write("\t            <span class=\"search-icon ripple-event\"></span>\n");
      out.write("\t            <div class=\"setDateBox\">\n");
      out.write("\t                <input type=\"text\" name=\"daterange\" class=\"daterange\">\n");
      out.write("\t                <i class=\"select-arrow\"></i>\n");
      out.write("\t                <i class=\"date-icon\"></i>\n");
      out.write("\t            </div> -->\n");
      out.write("\t            <div class=\"tab-list\">\n");
      out.write("\t\t            <ul class=\"Operation_tag\">\n");
      out.write("\t\t              <a href=\"/crm/cityMerchant/cityMerchant.html\">城市运营中心</a>\n");
      out.write("\t\t              <b class=\"Operation_icon\"></b>\n");
      out.write("\t\t              ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t              <a href=\"/crm/cityMerchant/orderDetails.html?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.id }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('"');
      out.write('>');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.cityMerchantName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("-详情</a>\n");
      out.write("\t\t            </ul>\n");
      out.write("           \t\t </div>\n");
      out.write("\t        </div>\n");
      out.write("\t        <input type=\"hidden\" name=\"pageNo\" id=\"pageNo\" value=\"\">\n");
      out.write("\t        <input type=\"hidden\" id=\"id\" name=\"merchantId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.id }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\t        <input type=\"hidden\" id=\"level\" name=\"level\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.level }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\t    </div>\n");
      out.write("\t    <div class=\"a_content\">\n");
      out.write("         <div class=\"table_scoll\">  \n");
      out.write("\t\t    <div class=\"table-responsive table_wrap\">\n");
      out.write("\t\t      <table class=\"table\">\n");
      out.write("\t\t        <thead>\n");
      out.write("\t\t         \t<tr class=\"table_h80\">\n");
      out.write("\t\t\t     \t\t<th class=\"table_w\">支付单号</th>\n");
      out.write("\t\t\t         \t<th class=\"table_w\">订单号</th>\n");
      out.write("\t\t\t         \t<th>账号</th>\n");
      out.write("\t\t\t         \t<th>时间</th>\n");
      out.write("\t\t\t         \t<th>金额</th>\n");
      out.write("\t\t\t         \t<th>状态</th>\n");
      out.write("\t\t\t         \t<th>操作</th>\n");
      out.write("\t\t\t        </tr>\n");
      out.write("\t\t        </thead>\n");
      out.write("\t\t        <tbody>\n");
      out.write("\t\t\t        ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t        </tbody>\n");
      out.write("\t\t      </table>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"box_Operation_list box_Operation_listright\">\n");
      out.write("\t\t\t\t \t<div class=\"Operationicon_right\">\n");
      out.write("\t\t\t\t       <div class=\"Operationicon_content\">\n");
      out.write("\t\t\t\t\t     <p class=\"Operationicon_box\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.cityMerchantName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("<span>ID：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.merchantCode}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span></p> \n");
      out.write("\t\t\t\t\t     <ul class=\"Operationicon_money\">\n");
      out.write("\t\t\t\t\t     \t<li class=\"Op_box\">\n");
      out.write("\t\t\t\t\t     \t\t<h3>配送利润</h3>\n");
      out.write("\t\t\t\t\t     \t\t<span class=\"Op_icon Op_icon1\"></span>\n");
      out.write("\t\t\t\t\t     \t\t<p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.areaProfit}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t\t\t\t\t     \t</li>\n");
      out.write("\t\t\t\t\t     \t<li class=\"Op_box\">\n");
      out.write("\t\t\t\t\t     \t\t<h3>固定利润</h3>\n");
      out.write("\t\t\t\t\t     \t\t<span class=\"Op_icon Op_icon2\"></span>\n");
      out.write("\t\t\t\t\t     \t\t<p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.fixateProfit}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t\t\t\t\t     \t</li>\n");
      out.write("\t\t\t\t\t     \t<li class=\"Op_box\">\n");
      out.write("\t\t\t\t\t     \t\t<h3>首批提货款</h3>\n");
      out.write("\t\t\t\t\t     \t\t<span class=\"Op_icon Op_icon3\"></span>\n");
      out.write("\t\t\t\t\t     \t\t<p>总额:");
      if (_jspx_meth_fmt_005fformatNumber_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<p style=\"color:red\">余额:");
      if (_jspx_meth_fmt_005fformatNumber_005f1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"javascript:\" class=\"add_paymen commtxtcolor\" id=\"add_paymen\">追加</a>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"javascript:\" class=\"minus_paymen commtxtcolor\" id=\"minus_paymen\">扣除</a>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"firstGoodsMoneyRecordList.html?merchantId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.id }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"commtxtcolor\">详情</a>\n");
      out.write("\t\t\t\t\t     \t</li>\n");
      out.write("\t\t\t\t\t     </ul>\n");
      out.write("\t\t\t\t\t    <div class=\"Op_name\">\n");
      out.write("\t\t\t\t\t    \t<span class=\"name_img name_img4 name_imgheigth\"></span>\n");
      out.write("\t\t\t\t\t        <div class=\"Op_name_content Op_name_content2\">\n");
      out.write("\t\t\t\t\t     \t\t<p>优惠券冻结金额：");
      if (_jspx_meth_fmt_005fformatNumber_005f2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<p style=\"color:red\">优惠券解冻金额：");
      if (_jspx_meth_fmt_005fformatNumber_005f3(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"javascript:\" class=\"add_coupon commtxtcolor\" id=\"add_coupon\">追加</a>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"javascript:\" class=\"minus_coupon commtxtcolor\" id=\"minus_coupon\">扣除</a>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"couponRecord.html?merchantId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.id }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"commtxtcolor\">记录详情</a>\n");
      out.write("\t\t\t\t\t     \t\t<i class=\"Op_sanjiao\"></i>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t     </div>\n");
      out.write("\t\t\t\t\t    <div class=\"Op_name\">\n");
      out.write("\t\t\t\t     \t\t<span class=\"name_img name_img4 name_imgheigth\"></span>\n");
      out.write("\t\t\t\t\t        <div class=\"Op_name_content Op_name_content2\">\n");
      out.write("\t\t\t\t\t     \t\t<p style=\"color:red\">总销售额：");
      if (_jspx_meth_fmt_005fformatNumber_005f4(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"saleRecordList.html?merchantId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.id }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"commtxtcolor\">记录详情</a>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t     </div>\t\n");
      out.write("\t\t\t\t\t     <div class=\"Op_name\">\n");
      out.write("\t\t\t\t     \t\t<span class=\"name_img name_img4 name_imgheigth\"></span>\n");
      out.write("\t\t\t\t\t        <div class=\"Op_name_content Op_name_content2\">\n");
      out.write("\t\t\t\t\t     \t\t<p style=\"color:red\">保证金总额：");
      if (_jspx_meth_fmt_005fformatNumber_005f5(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<p style=\"color:red\">待返还保证金：");
      if (_jspx_meth_fmt_005fformatNumber_005f6(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"bondRecord.html?merchantId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.id }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"commtxtcolor\">记录详情</a>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t     </div>\n");
      out.write("\t\t\t\t\t     <div class=\"Op_name\">\n");
      out.write("\t\t\t\t     \t\t<span class=\"name_img name_img4 name_imgheigth\"></span>\n");
      out.write("\t\t\t\t\t        <div class=\"Op_name_content Op_name_content2\">\n");
      out.write("\t\t\t\t\t     \t\t<p style=\"color:red\">返利金额：");
      if (_jspx_meth_fmt_005fformatNumber_005f7(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<a href=\"rebateRecord.html?merchantId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.id }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"commtxtcolor\">记录详情</a>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t     </div>\t\t\n");
      out.write("\t\t\t\t\t     <div class=\"Op_name\">\n");
      out.write("\t\t\t\t     \t\t<span class=\"name_user name_imgheigth\"></span>\n");
      out.write("\t\t\t\t\t        <div class=\"Op_name_content Op_name_content2\">\n");
      out.write("\t\t\t\t\t     \t\t<p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.linkMan}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t\t\t\t\t     \t\t<p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.linkTel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t\t\t\t\t     \t\t<p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.identityCard}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t\t\t\t\t     \t\t<i class=\"Op_sanjiao\"></i>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t     </div>\t\n");
      out.write("\t\t\t\t\t      <div class=\"Op_name\">\n");
      out.write("\t\t\t\t     \t\t<span class=\"name_address name_imgheigth\"></span>\n");
      out.write("\t\t\t\t\t        <div class=\"Op_name_content Op_name_content2\">\n");
      out.write("\t\t\t\t\t     \t\t<p  class=\"name-font name-margin\">\n");
      out.write("\t\t\t\t\t     \t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.provinceName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.cityName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.countyName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.cityMerchantAddress}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\n");
      out.write("\t\t\t\t\t     \t\t</p>\n");
      out.write("\t\t\t\t\t     \t\t<i class=\"Op_sanjiao\"></i>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t     </div>\t\n");
      out.write("\t\t\t\t\t      <div class=\"Op_name\">\n");
      out.write("\t\t\t\t     \t\t<span class=\"name_bank name_imgheigth\"></span>\n");
      out.write("\t\t\t\t\t        <div class=\"Op_name_content Op_name_content2\">\n");
      out.write("\t\t\t\t\t     \t\t<p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.bankNumber}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t\t\t\t\t     \t\t<p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.bankAccountName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t\t\t\t\t     \t\t<p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.bankAddress }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t\t\t\t\t     \t\t<i class=\"name_bl name_bl1\"></i>\n");
      out.write("\t\t\t\t\t     \t\t<i class=\"Op_sanjiao\"></i>\n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t     </div>\n");
      out.write("\t\t\t\t       </div>\n");
      out.write("\t\t\t\t    </div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/city_pages.jsp", out, false);
      out.write("\n");
      out.write("\t  </div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("</div>\t\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
    // /WEB-INF/jsp/crm/orderList_new.jsp(49,16) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${parentId!=null }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t              \t<a href=\"/crm/cityMerchant/downMerchant.html?parentId=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${parentId }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write('"');
        out.write('>');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${parentCity.cityMerchantName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</a>\n");
        out.write("\t\t              \t<b class=\"Operation_icon\"></b>\n");
        out.write("\t\t              ");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(77,11) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/crm/orderList_new.jsp(77,11) '${pageInfo.list}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${pageInfo.list}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/crm/orderList_new.jsp(77,11) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("item");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t         <tr class=\"table_h80\">\n");
          out.write("\t\t\t          \t<td class=\"table_w\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.payNumber}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("<i class=\"Operationicon Operationicon1\"></i></td>\n");
          out.write("\t\t\t          \t<td class=\"table_w\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.orderNumber}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t          \t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.userName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t          \t<td>");
          if (_jspx_meth_fmt_005fformatDate_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t\t          \t<td>\n");
          out.write("\t\t\t          \t\t<p>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.totalPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</p>\n");
          out.write("\t\t\t          \t</td>\n");
          out.write("\t\t\t          \t<td>\n");
          out.write("\t\t          \t\t\t");
          if (_jspx_meth_c_005fchoose_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\n");
          out.write("\t\t\t          \t</td>\n");
          out.write("\t\t\t          \t<td>\n");
          out.write("\t\t\t          \t\t<a href=\"/crm/cityMerchant/orderDetail.html?orderNumber=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.orderNumber}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\">详情</a>\n");
          out.write("\t\t\t          \t</td>\n");
          out.write("\t\t\t         </tr>\n");
          out.write("\t\t           ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatDate_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatDate
    org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jspx_th_fmt_005fformatDate_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag) _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag.class);
    _jspx_th_fmt_005fformatDate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatDate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /WEB-INF/jsp/crm/orderList_new.jsp(82,18) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setValue((java.util.Date) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.createDateTime }", java.util.Date.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(82,18) name = pattern type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setPattern("yyyy.MM.dd");
    int _jspx_eval_fmt_005fformatDate_005f0 = _jspx_th_fmt_005fformatDate_005f0.doStartTag();
    if (_jspx_th_fmt_005fformatDate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
    if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f1(_jspx_th_c_005fchoose_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f2(_jspx_th_c_005fchoose_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f3(_jspx_th_c_005fchoose_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f4(_jspx_th_c_005fchoose_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fotherwise_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jsp/crm/orderList_new.jsp(88,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.status==10 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('待');
        out.write('付');
        out.write('款');
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f1 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jsp/crm/orderList_new.jsp(89,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.status==20 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f1 = _jspx_th_c_005fwhen_005f1.doStartTag();
    if (_jspx_eval_c_005fwhen_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('已');
        out.write('付');
        out.write('款');
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f2 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jsp/crm/orderList_new.jsp(90,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.status==30 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f2 = _jspx_th_c_005fwhen_005f2.doStartTag();
    if (_jspx_eval_c_005fwhen_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('配');
        out.write('货');
        out.write('中');
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f3 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jsp/crm/orderList_new.jsp(91,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.status==40 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f3 = _jspx_th_c_005fwhen_005f3.doStartTag();
    if (_jspx_eval_c_005fwhen_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('已');
        out.write('发');
        out.write('货');
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f4 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jsp/crm/orderList_new.jsp(92,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.status==50 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f4 = _jspx_th_c_005fwhen_005f4.doStartTag();
    if (_jspx_eval_c_005fwhen_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('已');
        out.write('完');
        out.write('成');
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('已');
        out.write('关');
        out.write('闭');
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f0.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(122,18) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.firstGoodsMoney}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(122,18) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f0.setType("currency");
    // /WEB-INF/jsp/crm/orderList_new.jsp(122,18) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f0.setMaxFractionDigits(2);
    // /WEB-INF/jsp/crm/orderList_new.jsp(122,18) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f0.setMinFractionDigits(2);
    int _jspx_eval_fmt_005fformatNumber_005f0 = _jspx_th_fmt_005fformatNumber_005f0.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f1 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f1.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f1.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(124,36) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.firstGoodsMoneyBalance}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(124,36) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f1.setType("currency");
    // /WEB-INF/jsp/crm/orderList_new.jsp(124,36) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f1.setMaxFractionDigits(2);
    // /WEB-INF/jsp/crm/orderList_new.jsp(124,36) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f1.setMinFractionDigits(2);
    int _jspx_eval_fmt_005fformatNumber_005f1 = _jspx_th_fmt_005fformatNumber_005f1.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f1);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f2 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f2.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f2.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(134,23) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.freezeCoupon}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(134,23) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f2.setType("currency");
    // /WEB-INF/jsp/crm/orderList_new.jsp(134,23) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f2.setMaxFractionDigits(2);
    // /WEB-INF/jsp/crm/orderList_new.jsp(134,23) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f2.setMinFractionDigits(2);
    int _jspx_eval_fmt_005fformatNumber_005f2 = _jspx_th_fmt_005fformatNumber_005f2.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f2);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f3 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f3.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f3.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(136,41) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.unfreezeCoupon}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(136,41) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f3.setType("currency");
    // /WEB-INF/jsp/crm/orderList_new.jsp(136,41) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f3.setMaxFractionDigits(2);
    // /WEB-INF/jsp/crm/orderList_new.jsp(136,41) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f3.setMinFractionDigits(2);
    int _jspx_eval_fmt_005fformatNumber_005f3 = _jspx_th_fmt_005fformatNumber_005f3.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f3);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f4 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f4.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f4.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(147,38) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f4.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.saleTotalMoney}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(147,38) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f4.setType("currency");
    // /WEB-INF/jsp/crm/orderList_new.jsp(147,38) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f4.setMaxFractionDigits(2);
    // /WEB-INF/jsp/crm/orderList_new.jsp(147,38) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f4.setMinFractionDigits(2);
    int _jspx_eval_fmt_005fformatNumber_005f4 = _jspx_th_fmt_005fformatNumber_005f4.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f4);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f5 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f5.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f5.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(155,39) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f5.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.bondMoney}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(155,39) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f5.setType("currency");
    // /WEB-INF/jsp/crm/orderList_new.jsp(155,39) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f5.setMaxFractionDigits(2);
    // /WEB-INF/jsp/crm/orderList_new.jsp(155,39) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f5.setMinFractionDigits(2);
    int _jspx_eval_fmt_005fformatNumber_005f5 = _jspx_th_fmt_005fformatNumber_005f5.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f5);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f6 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f6.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f6.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(157,40) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f6.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.bondMoneyBalance}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(157,40) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f6.setType("currency");
    // /WEB-INF/jsp/crm/orderList_new.jsp(157,40) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f6.setMaxFractionDigits(2);
    // /WEB-INF/jsp/crm/orderList_new.jsp(157,40) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f6.setMinFractionDigits(2);
    int _jspx_eval_fmt_005fformatNumber_005f6 = _jspx_th_fmt_005fformatNumber_005f6.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f6);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatNumber_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_005fformatNumber_005f7 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_005fformatNumber_005f7.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatNumber_005f7.setParent(null);
    // /WEB-INF/jsp/crm/orderList_new.jsp(165,38) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f7.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${city.rebateMoney==null?0:city.rebateMoney}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/crm/orderList_new.jsp(165,38) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f7.setType("currency");
    // /WEB-INF/jsp/crm/orderList_new.jsp(165,38) name = maxFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f7.setMaxFractionDigits(2);
    // /WEB-INF/jsp/crm/orderList_new.jsp(165,38) name = minFractionDigits type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatNumber_005f7.setMinFractionDigits(2);
    int _jspx_eval_fmt_005fformatNumber_005f7 = _jspx_th_fmt_005fformatNumber_005f7.doStartTag();
    if (_jspx_th_fmt_005fformatNumber_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatNumber_0026_005fvalue_005ftype_005fminFractionDigits_005fmaxFractionDigits_005fnobody.reuse(_jspx_th_fmt_005fformatNumber_005f7);
    return false;
  }
}
