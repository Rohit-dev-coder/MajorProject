<%@page import="techquiz.dto.stdexamdetails"%>
<%@page import="techquiz.dto.ExamDTO"%>
<%@page import="java.util.ArrayList"%>
<%
    String userid = (String) session.getAttribute("username");
    String usertype = (String) session.getAttribute("usertype");
    if (userid == null || usertype == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }

    if (usertype.equalsIgnoreCase("teacher")) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    ArrayList<stdexamdetails> al = (ArrayList<stdexamdetails>) request.getAttribute("allexams");
    if (usertype.equalsIgnoreCase("student")) {
%>

<div class="container-fluid dashboardbgimg" style="padding-left:unset;padding-right: unset;">
    <div class="col-md-12 mx-auto">
        <div>
            <div class="dashboardHeading">
                <center>EXAM DETAIL</center>
            </div>    
            <br>
            <script type="text/javascript">
                function enrollstd(x) {
                    var id = x.getAttribute("id");
                    var data = {
                        code: 'enroll',
                        data: id
                    };
                    clearDataResult();
                    $.post("TakeTestControllerServlet", data, processresponse);
                }
                function startteststd(x) {
                    var id = x.getAttribute("id");
                    var data = {
                        data: id,
                        code: 'starttest'
                    };
                    $.post("TakeTestControllerServlet", data, processresponse);
                }
                function resultteststd(x){
                    var id = x.getAttribute("id");
                    var data = {
                        data: id,
                        code: 'resulttest'
                    };
                    $.post("TakeTestControllerServlet", data, processresponse);
                }
            </script>
            <div class="dashboardContent table-responsive-sm" style="line-height: 3rem; font-size: 13px;">
                <table class="table table-hover" style="">
                    <thead>
                        <tr>
                            <th scope="col">EXAM ID</th>
                            <th scope="col">ACTION</th>
                            <th scope="col">TITLE</th>
                            <th scope="col">DATE</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (stdexamdetails o : al) {
                        %>
                        <tr>

                            <td><%= o.getExamId()%></td>
                            <td>
                                <%
                                    if (o.getStatus().equals("NE")) {
                                %>
                                <button class="btn btn-dark" onclick="enrollstd(this)" id="<%= o.getExamId()%>">ENROLL</button>
                                <% } else if (o.getStatus().equals("E")) {
                                %>
                                <button class="btn btn-primary" onclick="startteststd(this)" id="<%= o.getExamId()%>">TAKE TEST</button>
                                <%}else if(o.getStatus().equals("R")){
                                %>
                                <button class="btn btn-success" onclick="resultteststd(this)" id="<%= o.getExamId()%>">VIEW RESULT</button>
                                <%}
                                %>
                            </td>

                            <td><%= o.getExamTitle()%></td>
                            <td><%= o.getExamDateTime()%></td>


                        </tr>   
                        <% } %>
                    </tbody>

                </table>

            </div>
        </div>        
    </div>   
</div>
<% } else {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }%>