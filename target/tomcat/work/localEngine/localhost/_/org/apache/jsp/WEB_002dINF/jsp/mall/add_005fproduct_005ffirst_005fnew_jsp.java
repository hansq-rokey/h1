package org.apache.jsp.WEB_002dINF.jsp.mall;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_005fproduct_005ffirst_005fnew_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/plug_new/bootstrap/bootstrap.min.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/plug_new/bootstrap/daterangepicker-bs3.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/css_new/common.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/css_new/Process.css\" />\r\n");
      out.write("    <title>商品发布流程第一步</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/left_new.jsp", out, false);
      out.write("\r\n");
      out.write("<div class=\"right-part\">\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/top_new.jsp", out, false);
      out.write("\r\n");
      out.write(" \t<form action=\"/mall/product/save.html\" method=\"post\" id=\"form1\">\r\n");
      out.write("    <div class=\"wrap calcHeight\">\r\n");
      out.write("       <div class=\"m_content\" style=\"padding-bottom: 100px;\">\r\n");
      out.write("          <div class=\"p_title\">\r\n");
      out.write("            <ul class=\"p_list\">\r\n");
      out.write("              <li class=\"p_on\"><span>1</span></li>\r\n");
      out.write("              <li><span>2</span></li>\r\n");
      out.write("              <li><span>3</span></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </div>\r\n");
      out.write("        <div class=\"p_content\" >\r\n");
      out.write("          <div class=\"content_list\">\r\n");
      out.write("            <div class=\"content_mian\">\r\n");
      out.write("              <div class=\"selectskin selectModel\">\r\n");
      out.write("                <i class=\"selectpoint\"></i>\r\n");
      out.write("                <span class=\"selectcontent data_id\" data-id=\"\">产品型号</span>\r\n");
      out.write("                <ul class=\"selectopation\" style=\"display: none;\">\r\n");
      out.write("                \t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                </ul>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"selectskin selecttemplate\">\r\n");
      out.write("                <i class=\"selectpoint\"></i>\r\n");
      out.write("                <span class=\"selectcontent template_id\" data-id=\"\">模板名称</span>\r\n");
      out.write("                <ul class=\"selectopation\" style=\"display: none;\">\r\n");
      out.write("                \t");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                </ul>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"content_mian\">\r\n");
      out.write("              <div class=\"p_check\">\r\n");
      out.write("                   <label>是否上架</label>\r\n");
      out.write("                   <p><input type=\"radio\" id=\"checkbox1\" name=\"status\" value=\"1\" class=\"regular-checkbox\" /><label for=\"checkbox1\"></label>是</p>\r\n");
      out.write("                   <p><input type=\"radio\" id=\"checkbox2\" name=\"status\" checked=\"checked\" value=\"-1\" class=\"regular-checkbox\" /><label for=\"checkbox2\"></label>否</p> \r\n");
      out.write("                </div>   \r\n");
      out.write("                <div class=\"p_check\">\r\n");
      out.write("                   <label>是否是白熊产品</label>\r\n");
      out.write("                   <p><input type=\"radio\" id=\"checkbox3\" name=\"isCompanyProduct\" value=\"1\" class=\"regular-checkbox\" /><label for=\"checkbox3\"></label>是</p>\r\n");
      out.write("                   <p><input type=\"radio\" id=\"checkbox4\" name=\"isCompanyProduct\" checked=\"checked\" value=\"0\" class=\"regular-checkbox\" /><label for=\"checkbox4\"></label>否</p> \r\n");
      out.write("               </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"content_mian\">\r\n");
      out.write("             <p class=\"ProfitInput\">\r\n");
      out.write("             <!--  <span>总利润</span>\r\n");
      out.write("              <span><input type=\"text\" class=\"p_Profit\" id=\"\" name=\"totalProfit\"></span>\r\n");
      out.write("              <span>%</span> -->\r\n");
      out.write("              <span class=\"Profit_input_list Profit_input_list3\"><label>市场价</label><input type=\"text\" value=\"0\" class=\"Price\" name=\"price\" id=\"_price\"></span>\r\n");
      out.write("              <span class=\"Profit_input_list Profit_input_list2\"><label>代理商提货价</label><input type=\"text\" value=\"0\" name=\"productPurchasePrice\" class=\"Price\" id=\"_pick\"></span>\r\n");
      out.write("            </p>\r\n");
      out.write("            </div>\r\n");
      out.write("             <div class=\"content_mian\">\r\n");
      out.write("              <div class=\"p_check\">\r\n");
      out.write("                   <label>物料计算</label>\r\n");
      out.write("                    <p><input type=\"radio\" id=\"checkbox13\" name=\"isMaterialCalculate\" value=\"1\" class=\"regular-checkbox\" /><label for=\"checkbox13\"></label>是</p>\r\n");
      out.write("               \t\t<p><input type=\"radio\" id=\"checkbox14\" name=\"isMaterialCalculate\" checked=\"checked\" value=\"0\" class=\"regular-checkbox\" /><label for=\"checkbox14\"></label>否</p>\r\n");
      out.write("               </div>\r\n");
      out.write("               <div class=\"p_check\">\r\n");
      out.write("                   <label>是否允许使用优惠券</label>\r\n");
      out.write("                   <p><input type=\"radio\" id=\"checkbox11\" name=\"specialType\" value=\"1\" class=\"regular-checkbox\" /><label for=\"checkbox11\"></label>是</p>\r\n");
      out.write("                   <p><input type=\"radio\" id=\"checkbox12\" name=\"specialType\" checked=\"checked\" value=\"0\" class=\"regular-checkbox\" /><label for=\"checkbox12\"></label>否</p> \r\n");
      out.write("               </div>\r\n");
      out.write("            </div>\r\n");
      out.write("             <div class=\"p_check\">\r\n");
      out.write("                   <label style=\"font-weight: 400;\">产品标签 </label>\r\n");
      out.write("                   <ul class=\"goodsbox\">\r\n");
      out.write("                    ");
      if (_jspx_meth_c_005fforEach_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("               </div>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"content_list\">\r\n");
      out.write("              <div class=\"content_input\">\r\n");
      out.write("                <label>产品特点</label>\r\n");
      out.write("                <input type=\"text\" class=\"Feature\" id=\"Feature\" name=\"feature\">\r\n");
      out.write("              </div>\r\n");
      out.write("            <div class=\"content_mian\">\r\n");
      out.write("\t              <div class=\"p_check\">\r\n");
      out.write("\t                  <label>是否允许下定金</label>\r\n");
      out.write("\t                  <p><input type=\"radio\" id=\"checkbox5\" name=\"isUseDeposit\" value=\"1\" class=\"regular-checkbox chbox chfade\" /><label for=\"checkbox5\"></label>是</p>\r\n");
      out.write("\t                  <p><input type=\"radio\" id=\"checkbox6\" checked=\"checked\" name=\"isUseDeposit\" value=\"0\" class=\"regular-checkbox chbox\" /><label for=\"checkbox6\"></label>否</p> \r\n");
      out.write("\t              </div>\r\n");
      out.write("               <div class=\"p_check p_fadeout\" style=\"display:none\">\r\n");
      out.write("                 <label class=\"p_Unit\">定金</label>\r\n");
      out.write("                 <input type=\"text\" class=\"p_money2\" name=\"depositMoney\" id=\"\">\r\n");
      out.write("                 <span >元</span>\r\n");
      out.write("               </div>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"content_mian\">\r\n");
      out.write("\t              <div class=\"isSee_total\" style=\"line-height: 4;\">\r\n");
      out.write("\t                  <label class=\"issee_label\">可见性</label>\r\n");
      out.write("\t                  <p><input type=\"checkbox\" id=\"checkbox7\" name=\"cDisplay\" value=\"1\" class=\"regular-checkbox\" /><label for=\"checkbox7\"></label><span class=\"Client\">C端用户</span></p>\r\n");
      out.write("\t                  <p><input type=\"checkbox\" id=\"checkbox8\" name=\"bDisplay\" value=\"1\" class=\"regular-checkbox\" /><label for=\"checkbox8\"></label><span class=\"Client\">B端用户</span></p> \r\n");
      out.write("\t              </div>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"content_mian\">\r\n");
      out.write("\t              <div class=\"p_check\">\r\n");
      out.write("\t                  <label>是否有区域利润</label>\r\n");
      out.write("\t                  <p><input type=\"radio\" id=\"checkbox9\" name=\"isSetAreaProfit\" value=\"1\" class=\"regular-checkbox isprofit isprofiton\" /><label for=\"checkbox9\"></label>是</p>\r\n");
      out.write("\t                  <p><input type=\"radio\" id=\"checkbox10\" name=\"isSetAreaProfit\" checked=\"checked\" value=\"0\" class=\"regular-checkbox isprofit\" /><label  for=\"checkbox10\"></label>否</p> \r\n");
      out.write("\t              </div>\r\n");
      out.write("\t             <div class=\"PriceBox\">\r\n");
      out.write("                  <input type=\"text\" class=\"Price Price1\" name=\"areaMoney\" id=\"\">\r\n");
      out.write("                 </div>\r\n");
      out.write("              </div>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>  \r\n");
      out.write("        <p class=\"p_btn\">\r\n");
      out.write("          <a class=\"p_btna\" href=\"javascript:void(0);\">发布</a>\r\n");
      out.write("          <a class=\"p_btnb\" href=\"###\">取消</a>\r\n");
      out.write("        </p>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("  <input type=\"hidden\" name=\"level\" value=\"1\" />\r\n");
      out.write("  <input type=\"hidden\" name=\"categoryModelId\" id=\"modelId\" value=\"\" />\r\n");
      out.write("  <input type=\"hidden\" name=\"freightTemplateId\" id=\"templateId\" value=\"\" />\r\n");
      out.write("  </form>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/plug_new/jQuery/jquery-1.9.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/plug_new/bootstrap/bootstrap.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/plug_new/bootstrap/daterangepicker.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js_new/common.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t //输入框焦点事件\r\n");
      out.write("\t $(document).on('focus', '#Feature', function() {\r\n");
      out.write("\t    $(this).prev(\"label\").css({top: '-17px',color: '#2CA3E5'});\r\n");
      out.write("\t    $(this).addClass('ab');\r\n");
      out.write("\t });\r\n");
      out.write("\t $(document).on('blur', '#Feature', function() {\r\n");
      out.write("\t  var _htmlc=$(this).val();\r\n");
      out.write("\t    if (_htmlc==\"\") {\r\n");
      out.write("\t          $(this).prev(\"label\").css({top: '-2px',color: '#5a6677'});\r\n");
      out.write("\t          $(this).removeClass('ab');\r\n");
      out.write("\t    }else{\r\n");
      out.write("\t          $(this).prev(\"label\").css({top: '-17px',color: '#2CA3E5'});\r\n");
      out.write("\t          $(this).addClass('ab');\r\n");
      out.write("\t    }\r\n");
      out.write("\t });\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\".p_btna\").click(function(){\r\n");
      out.write("\t\t\tvar p_m=$(\".data_id\").attr(\"data-id\");//产品型号\r\n");
      out.write("\t\t\tvar _price=$(\"#_price\").val();//市场价\r\n");
      out.write("\t\t\tvar _pick=$(\"#_pick\").val();//代理商提货价\r\n");
      out.write("\t\t\tif(p_m == \"\" || _price == \"\" || _pick == \"\"){\r\n");
      out.write("\t\t\t\t$(\".data_id\").css({\"color\":\"#f00\"});\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(\"#form1\").submit();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\".chbox\").on('click', function() {\r\n");
      out.write("\t\t    if ($(this).hasClass('chfade')) {\r\n");
      out.write("\t\t      $(\".p_fadeout\").fadeIn(200);\r\n");
      out.write("\t\t    }else{\r\n");
      out.write("\t\t      $(\".p_fadeout\").fadeOut(200);\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t  });\r\n");
      out.write("\t\t$(\".isprofit\").on('click', function() {\r\n");
      out.write("\t\t    if ($(this).hasClass('isprofiton')) {\r\n");
      out.write("\t\t      $(\".PriceBox\").fadeIn(200);\r\n");
      out.write("\t\t    }else{\r\n");
      out.write("\t\t      $(\".PriceBox\").fadeOut(200);\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t  });\r\n");
      out.write("\t})\r\n");
      out.write("\t\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/jsp/mall/add_product_first_new.jsp(34,17) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/mall/add_product_first_new.jsp(34,17) '${modelList}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${modelList}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/mall/add_product_first_new.jsp(34,17) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("model");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                        <li data-id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('+');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.code}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</li>\r\n");
          out.write("                   \t");
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

  private boolean _jspx_meth_c_005fforEach_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent(null);
    // /WEB-INF/jsp/mall/add_product_first_new.jsp(43,17) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/mall/add_product_first_new.jsp(43,17) '${templates}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${templates}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/mall/add_product_first_new.jsp(43,17) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("template");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                        <li data-id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${template.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${template.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</li>\r\n");
          out.write("                   \t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f2.setParent(null);
    // /WEB-INF/jsp/mall/add_product_first_new.jsp(85,20) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/mall/add_product_first_new.jsp(85,20) '${tags }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${tags }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/mall/add_product_first_new.jsp(85,20) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setVar("tag");
    // /WEB-INF/jsp/mall/add_product_first_new.jsp(85,20) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setVarStatus("status");
    int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
      if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                    \t<li><input type=\"checkbox\" id=\"check");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status.count}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" name=\"tagId\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tag.id }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" class=\"regular-checkbox\" /><label for=\"check");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status.count}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"></label>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tag.tagName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</li>\r\n");
          out.write("                    ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f2.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f2);
    }
    return false;
  }
}
