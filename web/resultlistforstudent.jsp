<%@page import="techquiz.dto.examsampleinfo"%>
<%--<%@page import="techquiz.dto.ExamDTO"%>--%>
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
    ArrayList<examsampleinfo> al = (ArrayList<examsampleinfo>) request.getAttribute("al");
%>

<div class="container-fluid dashboardbgimg" style="padding-left:unset;padding-right: unset;">
    <div class="col-md-12 mx-auto">
        <div>
            <div class="dashboardHeading">
                <center>EXAM SELECT</center>
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
                            for (examsampleinfo o : al) {
                        %>
                        <tr>

                            <td><%= o.getExamid() %></td>
                            <td>
                                
                                 <button class="btn btn-secondary btn-sm" onclick="showrank(this)" id="<%=o.getExamid()%>">Show Ranks</button>
                                 
                            </td>

                            <td><%= o.getExamtitle() %></td>
                            
                            <% }%>
                        </tr>   
                    </tbody>
                </table>

            </div>
        </div>
        <script type="text/javascript">
            function showrank(x){
                var id = x.getAttribute("id");
                clearDataResult();
                data = {
                    data: id,
                    code: "showranks"
                }
                $.post("ShowResultController", data, processresponse);
            }
        </script>
    </div>   
</div>