<%
    String userid = (String)session.getAttribute("username");
    String usertype=(String)session.getAttribute("usertype");
    if(userid == null || usertype == null)
    {
        session.invalidate();
	response.sendRedirect("accessdenied.html");
	return;
    }
    
    if(usertype.equalsIgnoreCase("student")){
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
%>

<div class="container-fluid dashboardbgimg">
    <div class="col-md-6 mx-auto">
        <div class="resultBox">
            <div class="dashboardHeading">
                <center>SET EXAM</center>
            </div>    
            <br>
            <div class="dashboardContent" style="line-height: 3rem">
                
                <!--content will changed acc to user click;-->   
                <div class="form-row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="settitle">Set Exam Title :</label>
                            <input type="text" class="form-control" placeholder="" id="examtitle" onfocus="removeerrormsg(this)">
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="examdate">Exam Date :</label>
                            <input type="date" class="form-control" placeholder="" id="examdate" onfocus="removeerrormsg(this)">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="examtime">Exam Time :</label>
                            <input type="time" class="form-control" placeholder="" id="examtime" onfocus="removeerrormsg(this)">
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mx-auto">
                        <div class="form-group">
                            <label for="totalquestion">Total Question :</label>
                            <input type="number" class="form-control" placeholder="" id="totalquestion" min="1" onfocus="removeerrormsg(this)">
                        </div>
                    </div>
                    <div class="col-md-6 mx-auto">
                        <div class="form-group">
                            <label for="totalquestion">Total Marks :</label>
                            <input type="number" class="form-control" placeholder="" id="totalmarks" min="20" max="1000" onfocus="removeerrormsg(this)">
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6">
                        <button class="btn btn-lg btn-info btn-block mt-1" onclick="setExam()">Next</button>
                    </div>
                    <div class="col-md-6">
                        <button class="btn btn-lg btn-danger btn-block mt-1" onclick="clearSetExamForm()">Clear</button>
                    </div>
                    
                </div>
            </div>
        </div>        
    </div>   
</div>