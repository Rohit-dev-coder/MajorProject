<%@page import="techquiz.dto.testdetail"%>
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
    ArrayList<String> al = (ArrayList<String>) request.getAttribute("al");
    testdetail td = (testdetail) request.getAttribute("td");
%>
<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox">
            <div class="dashboardHeading">
                <center>EDIT <%= td.getExamTitle()%> Paper</center>
            </div>  
            <hr>
            <div class="col-md-12 group">
                <p><b>Select Question No:</b> <select onchange="getquestionbyid()" id="questionids">
                        <option selected="true" disabled="true">Select</option>
                        <%
                            for (String s : al) {
                        %>        
                        <option class="form-group"><%=s%></option> 
                        <% }
                        %>
                    </select></p>
                
            </div>
                    <div class="col-md-12 questionsbyids">
                        
                    </div>
                    
        </div>
    </div>
</div>
                    <script type="text/javascript">
                    function getquestionbyid() {
                        var x = document.getElementById("questionids").selectedIndex;
                        var y = document.getElementById("questionids").options;
                        var selectedValue = y[x].value;
//                        alert(selectedValue);
                        data = {
                            code: "getquestion",
                            qid: selectedValue          
                        }
                        $.post("EditQuestionsControllerServlet", data, processresponseforeditques);
                    }
                    function processresponseforeditques(response){
                        var response = response.trim();
                        if(response !== ""){
                            $('.questionsbyids').html("");
                            $('.questionsbyids').append(response);
                        }
                    }
                </script>
