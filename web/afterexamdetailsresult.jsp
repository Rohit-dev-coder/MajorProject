
<%@page import="techquiz.dto.checkAnswer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="techquiz.dto.resultDTO"%>
<%
    String userid = (String) session.getAttribute("username");
    String usertype = (String) session.getAttribute("usertype");
    if (userid == null || usertype == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    if (usertype.equalsIgnoreCase("student")) {
        resultDTO robj = (resultDTO) request.getAttribute("result");
%>
<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox">
            <div class="dashboardHeading">
                <center>Your Result</center>
            </div>    
            <br>
            <div class="dashboardsubHeading">
                <center>
                    <div class="row mx-auto">
                        <div class="col-lg-12">
                            <b>TOTAL QUESTION: </b><%=robj.getTotalque()%>
                        </div>
                        <div class="col-lg-12">
                            <b>Total Marks: </b><%=robj.getTotalmarks()%>
                        </div>
                        <div class="col-lg-12">
                            <b>Right Answer: </b><%=robj.getRightans()%>
                        </div>
                        <div class="col-lg-12">
                            <b>Wrong Answer: </b><%=robj.getWrongans()%>
                        </div>
                        <div class="col-lg-12">
                            <b>Unattempted: </b><%=robj.getUnattempt()%>
                        </div>
                        <div class="col-lg-12" style="color: green">
                            <b>Percentage: </b><%=robj.getPercentage()%>%
                        </div>
                        <div class="col-lg-12" style="color: green">
                            <button class="btn btn-danger" onclick="window.print()">PRINT</button>
                        </div> 
                        <div class="col-lg-12" style="color: green">
                            <button class="btn btn-toolbar" onclick="backexam()">Back</button>
                        </div>                    
                    </div>
                    <hr>
                    <div class="row">

                        <%
                            ArrayList<checkAnswer> cansobj = (ArrayList<checkAnswer>) request.getAttribute("cansobj");
                            if (cansobj != null) {

                        %>
                        <div class="col-md-4">
                            YOUR ANSWER
                        </div>
                        <div class="col-md-4">
                            RIGHT ANSWER
                        </div>
                        <div class="col-md-4">
                            Status
                        </div>
                        <%  for (checkAnswer o : cansobj) {
                        %>
                        <div class="col-md-4">
                            <%=o.getYouranswer()%>
                        </div>
                        <div class="col-md-4">
                            <%= o.getCanswer()%>
                        </div>
                        <div class="col-md-4">
                            <%
                                if (o.getCanswer().equalsIgnoreCase(o.getYouranswer())) {
                            %>
                            <span style="color: lime">CORRECT</span>
                            <% } else { %>
                            <span style="color: red">WRONG</span>
                            <% } %>
                        </div>
                        <% }
                            } %>
                    </div>
                </center>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function backexam() {
            var data = {
                data: "exams"
            };
            clearDataResult();
            $.post("StudentControllerServlet", data, processresponse);
        }
    </script>
</div>
<% }%>
