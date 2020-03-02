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

    if (usertype.equalsIgnoreCase("student")) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    ArrayList<ExamDTO> al = (ArrayList<ExamDTO>) request.getAttribute("allexams");
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
                            int i = 1;
                            for (ExamDTO o : al) {
                        %>
                        <tr>

                            <td><%= o.getExamId()%></td>
                            <td><button class="btn btn-dark btn-sm" onclick="editpaper(this)" id="<%=o.getExamId()%>">EDIT</button>
                                <button class="btn btn-dark btn-sm" onclick="showallenrollstd(this)" id="<%=o.getExamId()%>">Enrolled Student</button>
                                <%
                                    String status = o.getStatus();
                                    if (status.equalsIgnoreCase("NS")) {
                                %>
                                <button class="btn btn-dark btn-sm" onclick="startpaper(this)" id="<%=o.getExamId()%>">START EXAM</button>
                                <% } else if (status.equalsIgnoreCase("S")) {
                                %>
                                <button class="btn btn-danger btn-sm" onclick="endpaper(this)" id="<%=o.getExamId()%>">END</button>
                                <%} 
                                %>

                            </td>

                            <td><%= o.getExamTitle()%></td>
                            <td><%= o.getExamDateTime()%></td>
                            <td><%= o.getTotalQuestion()%></td>
                            <td><%= o.getTotalMarks()%></td>

                            <% }%>
                        </tr>   
                    </tbody>
                </table>

            </div>
        </div>
        <script type="text/javascript">
            function showallenrollstd(x) {
                var id = x.getAttribute("id");
                clearDataResult();
                data = {
                    data: id,
                    code: "showstd"
                }
                $.post("SetQuestionsControllerServlet", data, processresponseforsetexam);
            }
            function editpaper(x) {
                var id = x.getAttribute("id");
                clearDataResult();
                data = {
                    data: id,
                    code: "editexam"
                }
                $.post("EditQuestionsControllerServlet", data, processresponseforsetexam);
            }
            function startpaper(x) {
                var id = x.getAttribute("id");
                var minutes = prompt("Enter Minutes: ");
                if (minutes === "") {
                    alert("Please Enter Minutes");
                } else if (isNaN(minutes)) {
                    alert("Please Enter Numbers Only");
                } else {
                    clearDataResult();
                    data = {
                        data: id,
                        code: "startexam",
                        min: minutes
                    }
                    $.post("EditQuestionsControllerServlet", data, processresponseforsetexam);
                }

            }
            function endpaper(x) {
                var id = x.getAttribute("id");
                clearDataResult();
                data = {
                    data: id,
                    code: "endexam"
                }
                $.post("EditQuestionsControllerServlet", data, processresponseforsetexam);
            }
        </script>
    </div>   
</div>