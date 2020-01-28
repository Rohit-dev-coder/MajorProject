package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class teacherdashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!doctype html>\r\n");
      out.write("<html lang=\"en\">\r\n");

        response.setDateHeader("Expires", 0);
        String userid = (String)session.getAttribute("username");
        if(userid == null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
    
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("    <meta name=\"description\" content=\"\">\r\n");
      out.write("    <meta name=\"author\" content=\"\">\r\n");
      out.write("    <link rel=\"icon\" href=\"/docs/4.0/assets/img/favicons/favicon.ico\">\r\n");
      out.write("\r\n");
      out.write("    <title>Dashboard Template for Bootstrap</title>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"bootstrapfiles/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"bootstrapfiles/jquery-3.4.1.js\"></script>\r\n");
      out.write("    <script src=\"bootstrapfiles/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- Custom styles for this template -->\r\n");
      out.write("    <link href=\"css/dashboard.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"#\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <script defer src=\"https://use.fontawesome.com/releases/v5.0.7/js/all.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- myscript -->\r\n");
      out.write("    <script src=\"jsScripts/dashboard.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    <nav class=\"navbar navbar-expand-md navbar-dark bg-dark sticky-top\">\r\n");
      out.write("        <a class=\"navbar-brand\" href=\"#\">TechQuiz</a>\r\n");
      out.write("        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarText\"\r\n");
      out.write("            aria-controls=\"navbarText\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("            <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("        </button>\r\n");
      out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarText\">\r\n");
      out.write("            <ul class=\"navbar-nav mr-auto ml-auto\">\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">HOME</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">ABOUT US</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">FORUM</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">CONTACT US</a>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            <div class=\"btn-group mt-2\">\r\n");
      out.write("                <button type=\"button\" class=\"btn btn-info\">LOGIN</button>\r\n");
      out.write("                <button type=\"button\" class=\"btn btn-outline-info\">REGISTER</button>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container-fluid\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <nav class=\"col-md-2 d-none d-md-block bg-light sidebar\">\r\n");
      out.write("                <div class=\"sidebar-sticky\">\r\n");
      out.write("                    <ul class=\"nav flex-column\">\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <span class=\"nav-link active\" id=\"Profile\" onclick=\"showContent(this)\" >\r\n");
      out.write("                                <span data-feather=\"home\"></span>\r\n");
      out.write("                                Profile\r\n");
      out.write("                            </span>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <span class=\"nav-link\" id=\"Set-Exams\" onclick=\"showContent(this)\">\r\n");
      out.write("                                <span data-feather=\"file\"></span>\r\n");
      out.write("                                Set Exams\r\n");
      out.write("                            </span>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <span class=\"nav-link\" id=\"Exams-Request\" onclick=\"showContent(this)\">\r\n");
      out.write("                                <span data-feather=\"check-square\"></span>\r\n");
      out.write("                                Exams Request\r\n");
      out.write("                            </span>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <span class=\"nav-link\" id=\"Declared-Result\" onclick=\"showContent(this)\">\r\n");
      out.write("                                <span data-feather=\"users\"></span>\r\n");
      out.write("                                Declared Result\r\n");
      out.write("                            </span>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <span class=\"nav-link\" id=\"Forum\" onclick=\"showContent(this)\">\r\n");
      out.write("                                <span data-feather=\"users\"></span>\r\n");
      out.write("                                Forum\r\n");
      out.write("                            </span>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <span class=\"nav-link\" id=\"Settings\" onclick=\"showContent(this)\">\r\n");
      out.write("                                <span data-feather=\"settings\"></span>\r\n");
      out.write("                                Settings\r\n");
      out.write("                            </span>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </nav>\r\n");
      out.write("\r\n");
      out.write("            <main role=\"main\" class=\"col-md-9 ml-sm-auto col-lg-10 pt-3 px-4\">\r\n");
      out.write("                <div id = \"user-dashboard\"\r\n");
      out.write("                    class=\"d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom\">\r\n");
      out.write("                    <!-- dynamically changed title -->\r\n");
      out.write("                    <!--<h1 class=\"h1\">Profile</h1>-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"data-result\">\r\n");
      out.write("                        <!-- body of the title goes here  -->\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("            </main>\r\n");
      out.write("\r\n");
      out.write("           \r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- Icons -->\r\n");
      out.write("    <script src=\"https://unpkg.com/feather-icons/dist/feather.min.js\"></script>\r\n");
      out.write("    <script>\r\n");
      out.write("        feather.replace()\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("    <!-- Graphs -->\r\n");
      out.write("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js\"></script>\r\n");
      out.write("    \r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
