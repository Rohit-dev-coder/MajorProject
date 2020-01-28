

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
            
<div class="container">
        <div class="col-md-6 mx-auto" id="register-container">
            <div id="register-container-heading" style="font-size: 40px">
                Your Details
            </div>
            <br>
            <div id="register-container-body">
                <div class="form-row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="firstname">First Name :</label>
                            <input type="text" class="form-control" value="<%= obj.getFname() %>" disabled="true">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="firstname">Last Name :</label>
                            <input type="text" class="form-control" value="<%= obj.getSname() %>" disabled="true">
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-lg-12">
                        <label for="email">Email :</label>
                        <input type="email" class="form-control" value = "<%= obj.getEmail() %>" disabled="true">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="mobileno">Mobile No :</label>
                        <input type="text" class="form-control" maxlength="10" value="<%= obj.getMobile() %>" disabled="true">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="gender">Gender :</label>
                        <input type="text" class="form-control" value = "<%= obj.getGender() %>" disabled="true">
                    </div>
                </div>
                <br>
            </div>
        </div>
    </div>

