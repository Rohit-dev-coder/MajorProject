<%@page import="java.util.ArrayList"%>
<%@page import="techquiz.dto.UserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList <UserDetails> resultdata = (ArrayList<UserDetails>)request.getAttribute("resultdata");   
    StringBuffer displayBlock = new StringBuffer("");
    if( resultdata != null)
    {
        int i = 1;

        displayBlock.append("<div class=\"table-responsive\">");
        displayBlock.append("<table class=\"table table-striped table-hover mytablestyle\">"
                + "<thead>"
                + "<tr>"
                + "<th scope = \"col\">S.no </th>"
                + "<th scope = \"col\">First Name</th>"
                + "<th scope = \"col\">Second Name</th>"
                + "<th scope = \"col\">Email</th>"
                + "<th scope = \"col\">Mobile_no</th>"
                + "<th scope = \"col\">Gender</th>"
                + "</thead>"
                + "<tbody>");
        for(UserDetails obj: resultdata){
            
            displayBlock.append("<tr>"
                    + "<td>"+(i++)+"</td>"
                    + "<td>"+obj.getFname()+"</td>"
                    + "<td>"+obj.getSname()+"</td>"
                    + "<td>"+obj.getEmail()+"</td>"
                    + "<td>"+obj.getMobile()+"</td>"
                    + "<td>"+obj.getGender()+"</td>"
                            + "</tr>");
        }
        displayBlock.append("</tbody></table></div>");
        out.println(displayBlock);     
    }
    else
    {
        out.println("error");
    }
%>