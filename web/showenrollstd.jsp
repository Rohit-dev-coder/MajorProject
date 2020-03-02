<%@page import="techquiz.dto.EnrollDTO"%>
<%@page import="java.util.ArrayList"%>
<%
    String userid = (String) session.getAttribute("username");
    String usertype = (String) session.getAttribute("usertype");
    if (userid == null || usertype == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }

    if (usertype.equalsIgnoreCase("student")) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    ArrayList<EnrollDTO> al = (ArrayList<EnrollDTO>) request.getAttribute("al");

%>
<div class="container-fluid dashboardbgimg" style="padding-left:unset;padding-right: unset;">
    <div class="col-md-12 mx-auto">
        <div>
            <div class="dashboardHeading">
                <center>ENROLL STUDENT</center>
            </div>    
            <br>
            <script type="text/javascript">
                function backexamdetail() {
                    clearDataResult();
                    var data = {
                        data: "Exams-details"
                    };
                    $.post("TeacherControllerServlet", data, processresponse);
                }
            </script>
            <button class="btn btn-toolbar" onclick="backexamdetail()">BACK</button>
            <div class="dashboardContent table-responsive-sm" style="line-height: 3rem; font-size: 12px;">
                <table class="table table-hover table-striped" style="">
                    <thead>
                        <tr>
                            <th scope="col">SNO</th>
                            <th scope="col">STUDENT</th>    
                            <th scope="col">STATUS</th>    
                        </tr>
                    </thead>
                    <tbody>
                        <%                            int i = 1;
                            for (EnrollDTO o : al) {
                        %>
                        <tr>
                            <td><%= i%></td>
                            <td><%= o.getEmail() %></td>
                            <%
                                String sss = o.getStatus();
                                if(sss.equalsIgnoreCase("E")){
                            %>
                            <td><p style="color: red">Just Enroll</p></td>
                            <%}else if(sss.equalsIgnoreCase("R")){
                             %>
                            <td><p style="color: green">Test Done</p></td>
                            <% }
                            %>
                            <% ++i;
                                }%>
                        </tr>   
                    </tbody>
                </table>

            </div>
        </div>        
    </div>   
</div>