
<%@page import="techquiz.dto.LecturesWithProgress"%>
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
        ArrayList<LecturesWithProgress> lwp = (ArrayList<LecturesWithProgress>) request.getAttribute("lwp");
        String crsname = (String) request.getAttribute("cname");
        String crsid = (String) request.getAttribute("crsid");
%>
<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox" style="padding: 10px">
            <div class="dashboardHeading" style="text-align: center">
                <p><%=crsname%></p>
                
                <hr>
            </div>
                <div class="row">
                    <div class="col-md-3">
                        <button class="btn btn-info" style="margin-bottom: 5px" onclick="back()">Back</button>
                    </div>
                    <div class="col-md-6 mx-auto">
                        <p style="color: red">Click Lectures to Watch Video</p>
                    </div>
                </div>
                
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Lecture No.</th>
                                <th scope="col">Title</th>
                                <th scope="col">Progress</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int i = 1;
                                for (LecturesWithProgress o : lwp) {
                                    int max = o.getLd().getLecTime();
                                    double now = ((double) o.getStdprog() / max) * 100;
                                    System.out.println("Max Time"+max+"Std Prog"+o.getStdprog());
                                    System.out.println("Percentage now:"+now);                                  
                            %>
                            <tr id="<%=o.getLd().getLecId()%>" onclick="viewlec(this)" style="cursor: pointer">
                                <td><%= i++ %></td>
                                <td><%=o.getLd().getLecTitle()%></td>
                                <td>                                    
                                    <div class="progress">
                                        <% if(now > 95){ %>
                                        <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now %>%</div>
                                        <% }else if(now > 75){ %>
                                        <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now %>%</div>
                                        <% }else if(now > 50){ %>
                                        <div class="progress-bar progress-bar-striped bg-dark" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now %>%</div>
                                        <% }else if(now > 30){ %>
                                        <div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now %>%</div>
                                        <% }else if(now > 0){ %>
                                        <div class="progress-bar progress-bar-striped bg-danger" role="progressbar" style="width: <%=now%>%" aria-valuenow="<%=now%>" aria-valuemin="0" aria-valuemax="100"><%= (int) now %>%</div>
                                        <% } %>
                                    </div>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <script type="text/javascript">
                        function back(){
                            var data = {
                                code: 'Learn'
                            };
                            $.post("LearningControllerServlet", data, processresponse);
                        }
                        function viewlec(x) {
                            var lid = x.getAttribute("id");
                            var cid = "<%=crsid %>"
                            var ltitle = "<%= crsname %>"
                            var data = {
                                lid: lid,
                                cid: cid,
                                ltitle: ltitle,
                                code: 'playVideo'
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