
<%@page import="techquiz.dto.testdetail"%>
<%
    String userid = (String) session.getAttribute("username");
    String usertype = (String) session.getAttribute("usertype");
    if (userid == null || usertype == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    if (usertype.equalsIgnoreCase("student")) {
        testdetail obj = (testdetail) session.getAttribute("examdetails");
        String status = obj.getStatus();
        System.out.println("status :"+status);
%>


<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox">
            <div class="dashboardHeading">
                <center>Confirmation Test</center>
            </div>    
            <br>
            <div class="dashboardsubHeading">
                <div class="row mx-auto">
                    <div class="col-md-4">
                        <b>EXAM TITLE: </b><%=obj.getExamTitle()%>
                    </div>
                    <div class="col-md-4">
                        <b>TOTAL MARKS: </b><%= obj.getTotalMarks()%>
                    </div>
                    <div class="col-md-4">
                        <b>TEACHER: </b><%= obj.getTeachername()%>
                    </div>
                </div>
                <div class="row mx-auto">
                    <div class="col-md-4">
                        <b>EXAM ID: </b><%=obj.getExamId()%>
                    </div>
                    <div class="col-md-4">
                        <b>DATE TIME: </b><%=obj.getExamDateTime()%>
                    </div>
                    <div class="col-md-4">
                        <b>QUESTION: </b><%=obj.getTotalQuestion()%>
                    </div>                             
                </div>
                <hr>
                <div class="row mx-auto">
                    <div class="col">
                        <b>INSTRUCTION:</b>
                        <p> 1. Every Question carry equal marks (i.e. TotalQuestion/TotalMarks = per question mark)<br>
                            2. Do not use any kind of electronic devices.<br>
                            3. Do not open another tab or browser in between your test otherwise your test will rejected automatically.<br>
                            4. If you finish your paper with in the time period then click submit otherwise it automatically submit.</br>
                            5. When you start test you are redirect towards your test page. So do not close your browser just Wait for the response from the server</br>
                            6. DO NOT RELOAD OR REFRESH THE PAGE.</p>                        
                    </div>
                </div>
                <div class="form-row mx-auto">
                    <div class="form-check col-md-4 mx-auto">
                        <input class="form-check-input" type="checkbox" value="ok" id="CheckCon" onclick="agreeins()">
                        <label class="form-check-label" for="defaultCheck1">
                            * I read all the Instruction carefully.
                        </label>
                        <script type="text/javascript">
                            var checkboxresult = false;
                            function agreeins(){
                                var x = document.getElementById("CheckCon");
                                checkboxresult = x.checked;
                               
                            }
                            function cancleexambtn(){
                                var data = {
                                    code: 'cancelexm'
                                };
                                $.post("TakeTestControllerServlet", data, processresponse);
                            }
                            
                            
                        </script>
                    </div>
                </div>
                <br>
                <div class="row mx-auto">
                    <div class="col-md-6 mx-auto">
                        <button class="btn btn-outline-danger btn-block" onclick="cancleexambtn()">Cancel</button>
                    </div>
                    <% 
                        if(status.equalsIgnoreCase("NS")){
                    %>
                    <div class="col-md-6 mx-auto">
                        <p style="color: blue"> Test Not Started Yet. Reload page.</p>
                    </div>
                    <% }else if(status.equalsIgnoreCase("S")){ 
                    %>
                    <script type="text/javascript">
                        function startexambtn(){
                                if(checkboxresult === true){
                                    var data = {
                                        code: 'startexam'
                                    };
                                    $.post("TakeTestControllerServlet", data, processresponse);
                                }
                                else{
                                    alert("Please check the checkbox");
                                }                                                                
                            }
                     </script>
                    <div class="col-md-6 mx-auto">
                        <button class="btn btn-primary btn-block" id="startexambtn" onclick="startexambtn()">Start Exam</button>
                    </div>
                    <% }else if(status.equalsIgnoreCase("R")){
                     %>
                     <div class="col-md-6 mx-auto">
                        <p style="color: red">EXAM END</p>
                    </div>
                    <% }else{ %>
                    <div class="col-md-6 mx-auto">
                        <p style="color: blue"> Error to start paper. Reload page.</p>
                    </div>
                    <% } 
                    %>
                </div>
                <br>
            </div>
        </div>        
    </div>   
</div>
<% }%>