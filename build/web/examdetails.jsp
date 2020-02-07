<%@page import="techquiz.dto.ExamDTO"%>
<%@page import="java.util.ArrayList"%>
<%
    String userid = (String)session.getAttribute("username");
    String usertype=(String)session.getAttribute("usertype");
    if(userid == null || usertype == null)
    {
        session.invalidate();
	response.sendRedirect("accessdenied.html");
	return;
    }
    
    if(usertype.equalsIgnoreCase("student")){
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    ArrayList<ExamDTO> al = (ArrayList<ExamDTO>)request.getAttribute("allexams");
%>

<div class="container-fluid dashboardbgimg" style="padding-left:unset;padding-right: unset;">
    <div class="col-md-12 mx-auto">
        <div>
            <div class="dashboardHeading">
                <center>EXAM DETAIL</center>
            </div>    
            <br>
            <div class="dashboardContent table-responsive-sm" style="line-height: 3rem; font-size: 12px;">
                <table class="table table-hover table-striped" style="">
                    <thead>
                        <tr>
                            <th scope="col">EXAM ID</th>
                            <th scope="col">ACTION</th>
                            <th scope="col">TITLE</th>
                            <th scope="col">DATE</th>
                            <th scope="col">TOTAL QUE</th>
                            <th scope="col">TOTAL MARKS</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int i=1;
                            for(ExamDTO o: al){
                         %>
                    <tr>
                        
                        <td><%= o.getExamId() %></td>
                        <td><button class="btn btn-dark btn-sm">EDIT</button>
                            <button class="btn btn-dark btn-sm">Enrolled Student</button>
                        </td>

                        <td><%= o.getExamTitle() %></td>
                        <td><%= o.getExamDateTime() %></td>
                        <td><%= o.getTotalQuestion() %></td>
                        <td><%= o.getTotalMarks() %></td>
                        
                        <% } %>
                    </tr>   
                    </tbody>
                </table>
                
            </div>
        </div>        
    </div>   
</div>