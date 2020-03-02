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
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int i = 1;
                            for (ExamDTO o : al) {
                        %>
                        <tr>

                            <td><%= o.getExamId()%></td>
                            <td>
                                <% String s = o.getStatus();
                                %>
                                <% if(s.equalsIgnoreCase("S")){
                                %>
                                <p style="color:red">Please End the exam</p>
                                <% }else if(s.equalsIgnoreCase("NS")){
                                %>
                                <button class="btn btn-primary btn-sm" onclick="declaredrank(this)" id="<%=o.getExamId()%>">Declared Rank</button>
                                <% }else if(s.equalsIgnoreCase("R")){
                                 %>
                                 <button class="btn btn-secondary btn-sm" onclick="showrank(this)" id="<%=o.getExamId()%>">Show Ranks</button>
                                <% } %>
                                 
                            </td>

                            <td><%= o.getExamTitle()%></td>

                            <% }%>
                        </tr>   
                    </tbody>
                </table>

            </div>
        </div>
        <script type="text/javascript">
            function declaredrank(x) {
                var id = x.getAttribute("id");
                clearDataResult();
                data = {
                    data: id,
                    code: "declaredrank"
                }
                $.post("EditQuestionsControllerServlet", data, processresponseforsetexam);
            }
            function showrank(x){
                var id = x.getAttribute("id");
                clearDataResult();
                data = {
                    data: id,
                    code: "showranks"
                }
                $.post("EditQuestionsControllerServlet", data, processresponseforsetexam);
            }
        </script>
    </div>   
</div>