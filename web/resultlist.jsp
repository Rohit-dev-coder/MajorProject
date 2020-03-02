<%@page import="techquiz.dto.rankDTO"%>
<%@page import="java.util.ArrayList"%>
<%
    String userid = (String) session.getAttribute("username");
    String usertype = (String) session.getAttribute("usertype");
    if (userid == null || usertype == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }

    ArrayList<rankDTO> al = (ArrayList<rankDTO>) request.getAttribute("result");
    String examtitle = (String) request.getAttribute("examtitle");
    String examid = (String) request.getAttribute("examid");
%>

<style>
    .blinking{
        animation:blinkingText 1.2s 2;
        color: lightcoral;
    }
    @keyframes blinkingText{
        0%{     color: #000;    }
        49%{    color: #000; }
        60%{    color: transparent; }
        99%{    color:transparent;  }
        100%{   color: #000;    }
    }
</style>
<div class="container-fluid dashboardbgimg" style="padding-left:unset;padding-right: unset;">
    <div class="col-md-12 mx-auto">
        <div>
            <div class="dashboardHeading">
                <center><%=examtitle%> RESULT LIST</center>
            </div>
            <% if (usertype.equalsIgnoreCase("teacher")) { %>
            <button class="btn btn-info" onclick="backfromrankresult()">BACK</button>
            <% } else if (usertype.equalsIgnoreCase("student")) { %>
            <button class="btn btn-info" onclick="backfromrankresultstd()">BACK</button>
            <% }
            %>
            <button class="btn btn-danger" style="float:right" onclick="window.print()">PRINT</button>
            <br>
            <div class="dashboardContent table-responsive-sm" style="line-height: 3rem; font-size: 12px;">
                <table class="table" style="">
                    <thead>
                        <tr>
                            <th scope="col">RANK</th>
                            <th scope="col">EMAIL</th>
                            <th scope="col">PERCENTAGE</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int i = 1;
                            for (rankDTO o : al) {
                        %>

                    
                    <% if (o.getEmail().equalsIgnoreCase(userid)) {%>
                    <tr class="blinking"><td><%= i%></td><td class="blinking" style="font-size: 16px;
                            color: lightcoral;"><%= o.getEmail()%></td><td><%= o.getPer()%></td></tr>
                        <% } else {%>
                    <tr><td><%= i%></td><td><%= o.getEmail()%></td><td><%= o.getPer()%></td></tr>
                        <% }%>
                        

                    
                    <% ++i;
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <% if (usertype.equalsIgnoreCase("teacher")) {%>
            <button class="btn btn-info" onclick="deleteresult()">RESET</button>
            <script type="text/javascript">
                function deleteresult() {
                    var id = "<%=examid%>";
                    clearDataResult();
                    var data = {
                        data: id,
                        code: "deleteresult"
                    }
                    $.post("EditQuestionsControllerServlet", data, processresponseforsetexam);
                }
            </script>
            <% }
            %>
        </div>
        <script type="text/javascript">

            function backfromrankresult() {
                clearDataResult();
                var data = {
                    code: "back"
                }
                $.post("EditQuestionsControllerServlet", data, processresponseforsetexam);
            }

            function backfromrankresultstd() {
                clearDataResult();
                var data = {
                    code: "back"
                }
                $.post("ShowResultController", data, processresponse);
            }
        </script>
    </div>   
</div>