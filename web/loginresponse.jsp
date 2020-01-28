<%-- 
    Document   : loginresponse
    Created on : 26 Jan, 2020, 9:18:11 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    String username=(String)request.getAttribute("username");
    String result=(String)request.getAttribute("result");
    
    if(username!=null && result!=null)
    {
        HttpSession sess=request.getSession();
        sess.setAttribute("username",username);
        if(result.equalsIgnoreCase("student"))
        {
         String url="studentdashboard.jsp;jsessionid="+session.getId();
        out.println(url);
        
         }
    else if(result.equalsIgnoreCase("teacher"))  
    {
      String url="teacherdashboard.jsp;jsessionid="+session.getId();
      out.println(url);
        
    }
    }
    else
    {
        out.println("error");
    }
    %>
    