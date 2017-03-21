package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write('\n');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("   <meta charset=\"UTF-8\">\n");
      out.write("   <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n");
      out.write("   <link href=\"plug/bootstrap/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("   <link href=\"css/login.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("   <script src=\"");
      out.print(path );
      out.write("/js/login.js\" type=\"text/javascript\"></script>\n");
      out.write("   <title>登录</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"content\">\n");
      out.write("    <form action=\"/dologin.html\" onsubmit=\"return checkInput();\" method=\"post\">\t\n");
      out.write("        <div class=\"loginbox\">\n");
      out.write("            <div class=\"leftpart\">\n");
      out.write("                <img src=\"../images/loginbg.png\" class=\"loginbg\">\n");
      out.write("                <div class=\"colorlayer\"></div>\n");
      out.write("                <img src=\"../images/indexlogo.png\" class=\"loginlogo\">\n");
      out.write("                <p class=\"firstline\">欢迎进入</p>\n");
      out.write("                <p class=\"secondline\">熊爸爸运营管理中心</p>\n");
      out.write("                <p class=\"thirdline\">熊爸爸，为温暖而生</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"rightpart\">\n");
      out.write("                <h3 class=\"logintitle\">登录</h3>\n");
      out.write("                <input type=\"text\" id=\"username\" name=\"loginName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${admin.loginName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"用户名\" class=\"username\">\n");
      out.write("                <input type=\"password\" id=\"pwd\" name=\"userPwd\" value=\"\" placeholder=\"密码\" class=\"userpass\">\n");
      out.write("                <p class=\"red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("                <input type=\"submit\" value=\"登    录\" class=\"loginbtn\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </form>\n");
      out.write("</div>\n");
      out.write("  </body>\n");
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
}
