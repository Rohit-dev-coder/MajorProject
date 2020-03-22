

<%@page import="techquiz.dto.NoticeDTO"%>
<%@page import="techquiz.dto.resultFrGDTO"%>
<%@page import="techquiz.dto.resultFrGDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="techquiz.dto.UserDetails"%>
<%!
    UserDetails obj = null;
    String utype = null;
%>
<%
    String userid = (String) session.getAttribute("username");
    System.out.println("jsp " + userid);
    if (userid == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result = (String) request.getAttribute("result");
    if (result == null) {
        response.sendRedirect("accessdenied.html");
        return;
    }
    if (result.equals("studentdetails")) {
        obj = (UserDetails) request.getAttribute("data");
        utype = (String) request.getAttribute("utype");
    } else if (result.equals("error")) {
        response.sendRedirect("accessdenied.html");
        return;
    } else {
        response.sendRedirect("accessdenied.html");
        return;
    }
%>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>     
<div class="container-fluid dashboardbgimg">
    <div class="row">
        <div class="col-lg-4 mx-auto">
            <div class="resultBox">
                <div class="dashboardHeading">
                    <center>Profile</center>
                </div>
                <br>
                <div class="dashboardContent" style="line-height: 3rem">
                    <b>Name : </b><%= obj.getFname()%> <%= obj.getSname()%><br>
                    <b>Email : </b><%= obj.getEmail()%><br>
                    <b>Gender : </b><%= obj.getGender()%><br>
                    <b>Mobile_No: </b><%= obj.getMobile()%><br>
                </div>
            </div>            
        </div>
        <%
            if (utype.equalsIgnoreCase("student")) {
                ArrayList<resultFrGDTO> ro = (ArrayList<resultFrGDTO>) request.getAttribute("resultforgraph");
        %>
        <div class="col-lg-8 mx-auto">
            <div class="resultBox">
                <div class="resultBox">
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <canvas id="myChart" width="200" height="85"></canvas>
                            </div>
                            <div class="carousel-item" >
                                <table class="table">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th scope="col">SNO</th>
                                            <th scope="col">TITLE</th>
                                            <th scope="col">MARKS</th>
                                        </tr>
                                    </thead>
                                    <tbody style="overflow: scroll">
                                        <%
                                            resultFrGDTO ob;
                                            for (int i = 0; i < 5; i++) {
                                                if (i == 5) {
                                                    break;
                                                }
                                                try {
                                                    ob = ro.get(i);
                                                } catch (Exception e) {
                                                    ob = null;
                                                }
                                        %>

                                        <tr>
                                            <th scope="row"><%=i + 1%></th>
                                                <% if (ob != null) {%>
                                            <td><%=ob.getEtitle()%></td>
                                            <td><%=ob.getPercentage()%></td>
                                            <% } else { %>
                                            <td></td>
                                            <td></td>
                                            <% } %>
                                        </tr>
                                        <% }
                                        %>


                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true" style="color: red"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>        
        </div>
        <script type="text/javascript">
            var ctx = document.getElementById('myChart').getContext('2d');
            var martitle = ["hindi", "english"];
            var marks = ["40", "20"];
            <%
                for (resultFrGDTO o : ro) {
            %>
            martitle.push("<%=o.getEtitle()%>");
            marks.push("<%=o.getPercentage()%>");
            <% }
            %>
            var chart = new Chart(ctx, {
                // The type of chart we want to create
                type: 'line',
                // The data for our dataset
                data: {
                    labels: martitle,
                    datasets: [{
                            label: 'Results',
                            backgroundColor: false,
                            borderColor: 'rgb(48, 55, 155)',
                            data: marks
                        }]
                },
                // Configuration options go here
                options: {scales: {
                        xAxes: [{
                                gridLines: {
                                    color: "rgba(0, 0, 0, 0)",
                                }
                            }],
                        yAxes: [{
                                gridLines: {
                                    color: "rgba(0, 0, 0, 0)",
                                }
                            }]
                    }}
            });
        </script>
        <% } else if (utype.equalsIgnoreCase("teacher")) {
        %>
        <div class="col-lg-8 mx-auto">
            <div class="resultBox" style="padding: 10px">
                <div class="form-row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="notice"><span style="font-size: 20px;"><b>WRITE NOTICE:</b></span></label>
                            <textarea maxlength="3999" class="form-control" rows="7" style="resize: none" id="noticemsg" placeholder="Write Notice Here.."></textarea>
                        </div>                         
                    </div>
                    <div class="col-md-2 offset-10">
                        <button class="form-control btn btn-success" onclick="postMessage()">POST</button>
                    </div>
                </div>
            </div>            
        </div>
        <script type="text/javascript">
            function Check() {
                var reg = /<(.|\n)*?>/g;
                if (reg.test($('#noticemsg').val()) === true) {
                    var ErrorText = 'do not allow HTMLTAGS';
                    alert(ErrorText);
                    return false;
                }
            }
            function postMessage() {
                var result = Check();
                if (result === false)
                    return;
                var msg = $('#noticemsg').val();
                var data = {
                    code: "post",
                    data: msg
                };
                $.post("NoticeControllerServlet", data, function (responseText) {
                    rt = responseText.trim();
                    if (rt === "success") {
                        $('#noticemsg').val("");
                        onloadmyfunction();
                        toastr["info"]("Post Successfully", "POSTED!");
                    } else {
                        toastr["error"]("ERROR", "ERROR IN POST!");
                    }
                });
            }
        </script>


        <% }%>

    </div>
    <br>
    <div class="row">
        <div class="col-md-12">
            <div class="resultBox">
                <div>                    
                    <div id="carouselExampleControls2" class="carousel slide carousel-fade" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active" style="padding: 85px">
                                <span class="dashboardHeading"><center><b>NOTICE BOARD</b></center></span>
                            </div>
                            <%
                                ArrayList<NoticeDTO> nobj = (ArrayList<NoticeDTO>) request.getAttribute("notices");
                                int ii = 0;
                                for (NoticeDTO o : nobj) {
                            %>
                            <div class="carousel-item" style="padding: 30px 100px">
                                <div class="container-fluid">
                                    <div class="row" style="color: gainsboro">
                                        <div class="col-md-6"><b>Message NO: <%=++ii%></b></div>
                                        <div class="col-md-6"><b>DATE and TIME: </b><%=o.getDate()%></div>
                                    </div>
                                    <hr style="height: 2px">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <b>MESSAGE: </b>
                                        </div>
                                        <div class="col-md-10" style="font-size: 20px;">
                                            <%=o.getMessage()%>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row" style="color: gainsboro">
                                        <div class="col-md-2">
                                            <b>BY:</b>
                                        </div>
                                        <div class="col-md-7">
                                            <%=o.getFname()%><%=o.getSname()%>
                                        </div>
                                    </div>

                                    <% if (o.getEmail().equalsIgnoreCase(userid)) {
                                    %>
                                    <br> <br>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <button class="btn btn-danger" id="<%=o.getNid()%>" onclick="deletenotice(this)">DELETE NOTICE</button>
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                        function deletenotice(x) {
                                            var id = x.getAttribute("id");
                                            var data = {
                                                code: "deletepost",
                                                data: id
                                            };
                                            $.post("NoticeControllerServlet", data, function (responseText) {
                                                rt = responseText.trim();
                                                if (rt === "success") {
                                                    onloadmyfunction();
                                                    toastr["info"]("Post Successfully deleted", "DELETED!");
                                                }
                                            });
                                        }
                                    </script>
                                    <% }
                                    %>
                                </div>
                            </div>
                            <% }
                            %>

                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls2" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true" style="background-color: lightgray"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls2" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true" style="background-color: lightgray"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>


                </div>
            </div>
        </div>
    </div>






