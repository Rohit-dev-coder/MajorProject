<%@page import="techquiz.dto.UserDetails"%>
<%
    response.setDateHeader("Expires", 0);
    String userid = (String) session.getAttribute("username");
    String utype = (String) session.getAttribute("usertype");
    if (userid == null || utype == null) {

        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    UserDetails uobj = (UserDetails) request.getAttribute("uobj");
    
    
%>
<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox" style="padding: 10px">
            <div class="dashboardHeading">
                <center>Settings</center>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-2">
                    <b>First Name: </b>
                </div>
                <div class="col-sm-4">
                    <input type="text" value="<%= uobj.getFname()%>" disabled="true" id="fname">
                </div>
                <div class="col-sm-3">
                    <button class="btn-sm btn-outline-danger" onclick="enableedit(this)" value="fname">EDIT</button>
                </div>
                <div class="col-sm-3">
                    <div class="actionresult fname"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <b>Second Name: </b>
                </div>
                <div class="col-sm-4">
                    <input type="text" value="<%= uobj.getSname()%>" disabled="true" id="sname">
                </div>
                <div class="col-sm-3">
                    <button class="btn-sm btn-outline-danger" onclick="enableedit(this)" value="sname">EDIT</button>
                </div>
                <div class="col-sm-3">
                    <div class="actionresult sname"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <b>Email :</b>
                </div>
                <div class="col-sm-4">
                    <input type="text" value="<%= uobj.getEmail()%>" disabled="true" id="email">
                </div>
                <div class="col-sm-3">
                    <button class="btn-sm btn-outline-danger" disabled="true" onclick="enableedit(this)" value="email">EDIT</button>
                </div>
                <div class="col-sm-3">
                    <div class="actionresult email"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <b>Gender: </b>
                </div>
                <div class="col-sm-4">
                    <input type="text" value="<%= uobj.getGender()%>" disabled="true" id="gender">
                </div>
                <div class="col-sm-3">
                    <button class="btn-sm btn-outline-danger" onclick="enableedit(this)" value="gender">EDIT</button>
                </div>
                <div class="col-sm-3">
                    <div class="actionresult gender"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <b>Mobile No: </b>
                </div>
                <div class="col-sm-4">
                    <input type="number" value="<%= uobj.getMobile()%>" disabled="true" id="Mobile_no">
                </div>
                <div class="col-sm-3">
                    <button class="btn-sm btn-outline-danger" onclick="enableedit(this)" value="Mobile_no">EDIT</button>
                </div>
                <div class="col-sm-3">
                    <div class="actionresult Mobile_no"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <b>Password: </b>
                </div>
                <div class="col-sm-4">
                    <input type="password" value="<%= uobj.getPwd()%>" disabled="true" id="pwd">
                </div>
                <div class="col-sm-3">
                    <button class="btn-sm btn-outline-danger" onclick="enableedit(this)" value="pwd">EDIT</button>
                </div>
                <div class="col-sm-3">
                    <div class="actionresult pwd"></div>
                </div>
            </div>
            <script type="text/javascript">
                var id;
                var value;
               
                function enableedit(x){
                    var id = x.getAttribute("value");
                    $("#"+id).attr('disabled',false);      
                    $("div.actionresult." + id).html("");
                    $("div.actionresult." + id).append('<button class="btn btn-primary" onclick="update(this)" value="'+id+'">Save</button>');                    
                }
               
                function update(x) {
                    id = x.getAttribute("value");
                    value = document.getElementById(id).value;
                    
                    var data = {
                        id: id,
                        value: value,
                        code: 'edit'
                    };
                    $.post("SettingsController", data, settingprocessresponse);
                }
                function settingprocessresponse(responsText) {
                    var r = responsText.trim();
                    if (r === "Saved") {
                        $("div.actionresult." + id).html("");
                        $("#"+id).attr('disabled',true); 
                        $("div.actionresult." + id).append("<p>Saved Change</p>");
                        toastr["success"](" SAVED SUCCESSFULLY", "success!");
                    }else{
                        $("div.actionresult." + id).html("");
                        $("div.actionresult." + id).append("<p>Error</p>");
                    }
                }
            </script>
        </div>

    </div>
</div>

