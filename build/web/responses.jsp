<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setDateHeader("Expires", -1 );
    String username=(String)session.getAttribute("username");
    if(username == null)
    {
        session.invalidate();
        response.sendRedirect("loginpage.html");
        return;
    }
    String status = (String)session.getAttribute("status");
    if(status != null)
    {
        if(status.equalsIgnoreCase("save"))
        {
            out.println("saveexam");
        }
    }
    else{
        out.println("error");
    }

%>