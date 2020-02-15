<%@page import="techquiz.dto.mcqOptionsDTO"%>
<%@page import="techquiz.dto.QuestionDTO"%>
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
    ArrayList<QuestionDTO> allques = (ArrayList<QuestionDTO>) session.getAttribute("allquestion");
    ArrayList<mcqOptionsDTO> mcqoptions = (ArrayList<mcqOptionsDTO>) session.getAttribute("questionsoption");

%>

<div class="container-fluid dashboardbgimg">
    <div class="col-md-10 mx-auto">
        <div class="resultBox">
            <div class="dashboardHeading">
                <center>PAPER</center>
            </div>    
            <br>
            <form id="questionpaperform">
                <div class="dashboardContent">
                    <%if (allques != null) {
                            int i = 0;
                            for (QuestionDTO obj : allques) {
                                i++;
                                String type = obj.getqType();
                    %>
                    <hr>

                    <div class="form-row">
                        <div class="col-md-12 mx-auto">
                            <div class="form-group">
                                <label for="question"><b>Question NO:<%=i%></b> <%=obj.getQuestion()%></label>
                            </div>
                        </div>
                    </div>
                    <% if (type.equalsIgnoreCase("mcq")) {
                            for (mcqOptionsDTO mcqobj : mcqoptions) {
                                if (mcqobj.getQid().equalsIgnoreCase(obj.getQid())) {
                    %>
                    <div class="form-row">
                        <div class="col-md-12 mx-auto">
                            <div class="form-group">
                                <div class="radio">
                                    <label><input name="<%=obj.getQid()%>" type="radio" id="<%=obj.getQid()%>" value="<%=mcqobj.getOption1()%>"><%=mcqobj.getOption1()%> </label>
                                </div>
                                <div class="radio">
                                    <label><input name="<%=obj.getQid()%>" type="radio" id="<%=obj.getQid()%>" value="<%=mcqobj.getOption2()%>"><%=mcqobj.getOption2()%> </label>
                                </div>
                                <div class="radio">
                                    <label><input name="<%=obj.getQid()%>" type="radio" id="<%=obj.getQid()%>" value="<%=mcqobj.getOption3()%>"><%=mcqobj.getOption3()%> </label>
                                </div>
                                <div class="radio">
                                    <label><input name="<%=obj.getQid()%>" type="radio" id="<%=obj.getQid()%>" value="<%=mcqobj.getOption4()%>"><%=mcqobj.getOption4()%> </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <% }
                        } %>
                    <% } else if (type.equalsIgnoreCase("fups")) {
                    %>
                    <div class="form-row">
                        <div class="col-md-12 mx-auto">
                            <div class="form-group">
                                <label for="Answer">Answer</label>
                                <input type="text" class="form-control" id="<%=obj.getQid()%>">
                            </div>
                        </div>
                    </div>            

                    <%
                    } else if (type.equalsIgnoreCase("tf")) {
                    %>
                    <div class="form-group">
                        <div class="col-md-12 mx-auto">
                            <label for="correctanstf">Answer</label>
                            <select id="<%=obj.getQid()%>" name="tf">
                                <option disabled="true" selected="true">select</option>
                                <option value="true">True</option>
                                <option value="false">False</option>
                            </select>
                        </div>
                    </div>

                    <% }
                            }
                        }%>

                    <div class="row">
                        
                        <div class="col-md-4 mx-auto">
                            <button class="btn btn-success btn-block" onclick="saveexam()">SUBMIT</button>
                        </div>
                    </div>
<!--                    <div class="row">
                        <div class="col-md-4 mx-auto examstatus">

                        </div>
                        <div class="col-md-4 mx-auto">

                        </div>
                    </div>        -->
                </div>
            </form>
        </div>        
    </div>   
    <script type="text/javascript">
        function saveexam() {
            var myform = document.getElementById("questionpaperform");
            var arr = myform.elements;
            var data = {
                code: "submitexam"
            };
            for (var x of arr)
            {
                var y = x.getAttribute("id");
                var z = x.getAttribute("value");
                var qtype = x.getAttribute("type");
                if (qtype === "radio") {
                    if (x.checked) {
                        data[y] = z;
                        continue;
                    }
                } else if (qtype === "text") {
                    if (y !== null)
                    {
                        var txtval = x.value;
                        if (y.startsWith("Q"))
                            data[y] = txtval;
                    }
                } else {
                    qtype = x.getAttribute("name");
                    var txtval = x.value;
                    if (qtype === "tf") {
                        data[y] = txtval;
                    }
                }
            }
            $.post("TakeTestControllerServlet", data, processresponse);
        }

    </script>


</div>








