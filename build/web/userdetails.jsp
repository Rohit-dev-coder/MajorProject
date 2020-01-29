

<%@page import="techquiz.dto.UserDetails"%>
<%!
    UserDetails obj = null;
 %>
<%
    String userid = (String)session.getAttribute("username");
    System.out.println("jsp "+userid);
    if(userid == null)
    {
        session.invalidate();
	response.sendRedirect("accessdenied.html");
	return;
    }
    String result = (String) request.getAttribute("result");
    if(result == null)
    {
        response.sendRedirect("accessdenied.html");
	return;
    }
    if(result.equals("studentdetails"))
    {
        
        obj = (UserDetails)request.getAttribute("data");   
    }
    else if(result.equals("error"))
    {
        response.sendRedirect("accessdenied.html");
	return;
    }
    else{
        response.sendRedirect("accessdenied.html");
	return;
    }
%>
            
    <div class="container-fluid dashboardbgimg">
        <div class="col-md-4 mx-auto">
            <div class="resultBox">
                <div class="dashboardHeading">
                    <center>Profile</center>
                </div>
                
                <br>
                <div class="dashboardContent" style="line-height: 3rem">
                    <b>Name : </b><%= obj.getFname()%> <%= obj.getSname() %><br>
                    <b>Email : </b><%= obj.getEmail() %><br>
                    <b>Gender : </b><%= obj.getGender() %><br>
                    <b>Mobile_No: </b><%= obj.getMobile() %><br>
                </div>
            </div>
            
        </div>   
    </div>

