
<%@page import="techquiz.dto.QuestionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="techquiz.dto.ExamDTO"%>
<%
//   response.setHeader( "Pragma", "no-cache" );
//   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader("Expires", -1 );
    String userid = (String)session.getAttribute("username");
    if(userid == null)
    {
        session.invalidate();
	response.sendRedirect("accessdenied.html");
	return;
    }
    ExamDTO obj = (ExamDTO)session.getAttribute("paperdetails");
    if(obj == null)
    {
        response.sendRedirect("teacherdashboard.jsp");
        return;
    }
    int cquesno = (int)session.getAttribute("currentQuestionNo");
    ArrayList<QuestionDTO> allQuestion = (ArrayList<QuestionDTO>)session.getAttribute("allquestion");
    if(allQuestion.size() == obj.getTotalQuestion()){
        String url="previewquestions.jsp;jsessionid="+session.getId();
        response.sendRedirect(url);
    }
%>



<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox">
            <div class="dashboardHeading">
                <center>SET QUESTION</center>
            </div>  
            <hr>
            <div class="dashboardsubHeading">
                <div class="row mx-auto">
                    <div class="col-md-8">
                        <b>EXAM TITLE: </b><%= obj.getExamTitle() %>
                    </div>
                    <div class="col-md-4">
                        <b>TOTAL MARKS: </b><%= obj.getTotalMarks() %>
                    </div>

                </div>
                <div class="row mx-auto">
                    <div class="col-md-8">
                        <b>EXAM DATE and TIME: </b><%= obj.getExamDateTime() %>
                    </div>
                     <div class="col-md-4">
                        <b>TOTAL QUESTION: </b><%= obj.getTotalQuestion() %>
                    </div> 
                    
                </div>
                <div class="row mx-auto">
                    <div class="col-12">
                        <i>(NOTE: Each question carry equal marks i.e totalquestion/totalmarks = one question marks)</i>
                    </div>
                </div>
                
                </div>
            <hr>
            <div class="dashboardContent">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="questionno">QuestionNo:</label>
                            <%= cquesno %>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="group">
                            <label for="questiontype">Question Type :</label>
                            <select id="ddquestiontype" onchange="showanswercol()" onfocus="removeerrormsg(this)">
                                <option selected="true" disabled="true">Select</option>
                                <option value="mcq">MCQ</option>
                                <option value="fups">ONE-WORD</option>
                                <option value="tf">TRUE-FALSE</option>
                            </select>
                        </div>
                    </div> 
                </div>
                <div class="form-row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="question">Question:</label>
                            <textarea class="form-control" rows="5" id="question" onfocus="removeerrormsg(this)"></textarea>
                        </div>
                    </div>
                    
                </div>
                <div class="row mcqcorrectanscol">
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="question 1">Option 1</label>
                            <textarea class="form-control" rows="3" id="option1" onfocus="removeerrormsg(this)"></textarea>
                        </div>
                    </div>
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="question 2">Option 2</label>
                            <textarea class="form-control" rows="3" id="option2" onfocus="removeerrormsg(this)"></textarea>
                        </div>
                    </div>
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="question 3">Option 3</label>
                            <textarea class="form-control" rows="3" id="option3" onfocus="removeerrormsg(this)"></textarea>
                        </div>
                    </div>
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="question 4">Option 4</label>
                            <textarea class="form-control" rows="3" id="option4" onfocus="removeerrormsg(this)"></textarea>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="question 4">Correct Answer: </label>
                            <select id="correctansmcq" onfocus="removeerrormsg(this)">
                                <option disabled="true" selected="true">select</option>
                                <option value="option1">Option1</option>
                                <option value="option2">Option2</option>
                                <option value="option3">Option3</option>
                                <option value="option4">Option4</option>
                            </select>
                        </div>
                        
                    </div>
                </div>
                        
            <div class="row onewordanscol">
                <div class="col-md-8 ">
                    <div class="form-group">
                        <label for="correctansoneword">Answer :</label>
                        <textarea class="form-control" rows="2" id="correctanswerow" onfocus="removeerrormsg(this)"></textarea>
                    </div>
                </div>
            </div>
            <div class="row tfanscol">
                <div class="col-md-8 ">
                    <div class="form-group">
                        <label for="correctanstf">Answer :</label>
                        <select id="correctanstf" onfocus="removeerrormsg(this)">
                            <option disabled="true" selected="true">select</option>
                            <option value="true">True</option>
                            <option value="false">False</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <button class="btn btn-danger btn-block" onclick="cancleexam()">Cancel Exam</button>
                    </div>
                </div>
                <div class="col-md-6">
                    <% if(cquesno < obj.getTotalQuestion()){ %>
                     <div class="form-group">
                         <button class="btn btn-primary btn-block" onclick="nextque()">Next Question</button>
                    </div>
                     <% } %>
                    <% if(cquesno == obj.getTotalQuestion()){ %>
                    <div class="form-group">
                         <button class="btn btn-primary btn-block" onclick="nextque()">Preview</button>
                    </div>
                    <% } %>
                </div>
            </div>
            </div>
        </div>        
    </div>
    <script>
        mcq = $(".mcqcorrectanscol").hide();
        fups = $(".onewordanscol").hide();
        tf = $(".tfanscol").hide();
        
        $(window).on('beforeunload', function(){
            return false;
        });
    </script>
</div>