
<%@page import="techquiz.dto.CorsesDTO"%>
<%@page import="techquiz.dto.CoursesWithProgress"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%
    response.setDateHeader("Expires", 0);
    String userid = (String) session.getAttribute("username");
    String utype = (String) session.getAttribute("usertype");
    if (userid == null || utype == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    if (utype.equalsIgnoreCase("student")) {
        ArrayList<CoursesWithProgress> cwp = (ArrayList<CoursesWithProgress>) request.getAttribute("cwp");
%>
<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox" style="padding: 10px">
            <div class="dashboardHeading" style="text-align: center">
                <p>Courses</p>
                <hr>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Course Name</th>
                                <th scope="col">Progress</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (CoursesWithProgress o : cwp) {
                                    int max = o.getC().getCrsDuration();
                                    double now = ((double) o.getMin() / max) * 100;

//                                  
                            %>
                            <tr id="<%=o.getC().getCrsId()%>" onclick="viewlec(this)" style="cursor: pointer">

                                <td><%=o.getC().getCrsTitle()%></td>
                                <td>
                                    <div class="progress">
                                        <% if (now > 95) {%>
                                        <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now%>%</div>
                                        <% } else if (now > 75) {%>
                                        <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now%>%</div>
                                        <% } else if (now > 50) {%>
                                        <div class="progress-bar progress-bar-striped bg-dark" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now%>%</div>
                                        <% } else if (now > 30) {%>
                                        <div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now%>%</div>
                                        <% } else if (now > 0) {%>
                                        <div class="progress-bar progress-bar-striped bg-danger" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now%>%</div>
                                        <% } %>
                                    </div>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <script type="text/javascript">
                        function viewlec(x) {
                            var id = x.getAttribute("id");
                            var data = {
                                data: id,
                                code: 'lecturesList'
                            };
                            $.post("LearningControllerServlet", data, processresponse);
                        }
                    </script>

                </div>
            </div>
        </div>
    </div>
</div>
</div>


<% }%>