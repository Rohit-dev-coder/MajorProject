

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    String username=(String)request.getAttribute("username");
    boolean result=(boolean)request.getAttribute("result");
    String utype = (String)request.getAttribute("utype");
    System.out.println("lr "+utype+result);
    if(username!=null && utype!=null && result == true)
    {
        HttpSession sess=request.getSession();
        sess.setAttribute("username",username);
        sess.setAttribute("usertype",utype);
        if(utype.equalsIgnoreCase("student"))
        {
            String url="studentdashboard.jsp;jsessionid="+session.getId();
            out.println(url);
        }
        else if(utype.equalsIgnoreCase("teacher"))  
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
    