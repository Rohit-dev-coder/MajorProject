<%@page import="techquiz.dto.chatmessageDTO"%>
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

    try {
        ArrayList<chatmessageDTO> al = (ArrayList<chatmessageDTO>) request.getAttribute("msges");
    } catch (Exception e) {

    }
%>
<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox" style="overflow: scroll; overflow-x: hidden; height: 500px;  padding: 10px; background-color: #Ece5dd; box-shadow:3px 3px 5px 6px #ccc;">
            <%
                ArrayList<chatmessageDTO> al = (ArrayList<chatmessageDTO>) request.getAttribute("msges");
                for (chatmessageDTO o : al) {
                    if (o.getUemail().equalsIgnoreCase(userid)) {
            %>
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="reciever">
                        <% if(o.getUtype().equalsIgnoreCase("teacher")) {%>
                        <span style="color: gold; font-size: 15px;">Teacher</span>
                        <% }else{ %>
                        <span style="color: #Ece5dd; font-size: 15px;">Student</span>
                        <% } %>
                        <p><%=o.getMessage()%></p>
                        <div class="reciever-info">
                            Send by You at <%=o.getDatetime()%>
                        </div>
                    </div>                    
                </div>
            </div>
            <% } else {
            %>            
            <div class="row">
                <div class="col-lg-8">
                    <div class="sender">
                        <% if(o.getUtype().equalsIgnoreCase("teacher")) {%>
                        <span style="color: gold; font-size: 15px;">Teacher</span>
                        <% }else{ %>
                        <span style="color: #Ece5dd; font-size: 15px;">Student</span>
                        <% } %>
                        <p><%=o.getMessage()%></p>
                        <div class="sender-info">
                            Send by <%=o.getUemail()%> at <%=o.getDatetime()%>
                        </div>
                    </div>                    
                </div>
            </div>
            <% }
                }%>
        </div>
        <div class="resultBox" style=" background-color: aliceblue; box-shadow:3px 3px 5px 6px #ccc;">
            <div class="row">
                <div class="col-sm-10" style="padding-right: 0px">
                    <!--<input type="text" id="usrmessage" class="form-control">-->
                    <textarea class="form-control" rows="1" style="resize: none" placeholder="Type Message" id="usrmessage"></textarea>
                </div>
                <div class="col-sm-2" style="padding-left: 0px">
                    <input type="button"  value="send" class="btn btn-info form-control" onclick="msgsend()">
                    <script type="text/javascript">
                        
                        
                        
                        function msgsend() {
                            var msg = document.getElementById("usrmessage").value;
                            $('#usrmessage').val("");
                            var data = {
                                data: msg,
                                code: 'sendmsg'
                            };
                            $.post("WorldChatController", data, wchatprocessresponse);
                        }
                        function wchatprocessresponse(responseText) {
                            var result = responseText.trim();
                            if (result === "sendsucc") {
                                var data = {
                                    code: 'wchat'
                                };
                                $.post("WorldChatController", data, processresponse);
                            } else {
                                toastr["warning"]("Would not send message", "Error!");
                            }
                        }


                    </script>                    
                </div>
            </div>
        </div>
    </div>
        <script type="text/javascript">
                var messageBody = document.querySelector('.resultBox');
                messageBody.scrollTop = messageBody.scrollHeight - messageBody.clientHeight;
            </script>
</div>
