<%@page import="techquiz.dto.fupsDTO"%>
<%@page import="techquiz.dto.tfDTO"%>
<%@page import="techquiz.dto.mcqDTO"%>
<%@page import="techquiz.dto.QuestionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="techquiz.dto.ExamDTO"%>
<%
//   response.setHeader( "Pragma", "no-cache" );
//   response.setHeader( "Cache-Control", "no-cache" );
    response.setDateHeader("Expires", -1);

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
    ExamDTO obj = (ExamDTO) session.getAttribute("paperdetails");
    if (obj == null) {
        response.sendRedirect("teacherdashboard.jsp");
        return;
    }
    ArrayList<QuestionDTO> allQuestion = (ArrayList<QuestionDTO>) session.getAttribute("allquestion");
    ArrayList<mcqDTO> allmcqAnswer = (ArrayList<mcqDTO>) session.getAttribute("allmcqanswer");
    ArrayList<tfDTO> alltfAnswer = (ArrayList<tfDTO>) session.getAttribute("alltfanswer");
    ArrayList<fupsDTO> allfupsAnswer = (ArrayList<fupsDTO>) session.getAttribute("allfupsanswer");
%>


<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox">
            <div class="dashboardHeading">
                <center>Preview</center>
            </div>  
            <hr>
            <div class="dashboardsubHeading">
                <div class="row mx-auto">
                    <div class="col-md-8">
                        <b>EXAM TITLE: </b><%= obj.getExamTitle()%>
                    </div>
                    <div class="col-md-4">
                        <b>TOTAL MARKS: </b><%= obj.getTotalMarks()%>
                    </div>

                </div>
                <div class="row mx-auto">
                    <div class="col-md-8">
                        <b>EXAM DATE and TIME: </b><%= obj.getExamDateTime()%>
                    </div>
                    <div class="col-md-4">
                        <b>TOTAL QUESTION: </b><%= obj.getTotalQuestion()%>
                    </div> 

                </div>
                <div class="row mx-auto">
                    <div class="col-12">
                        <p><i>NOTE:<br> 1. Each question carry equal marks i.e total_marks / total_question = one question marks</i><br>
                            <i>2. If you want to change anything in this paper please go to edit paper section and select which paper you want to edit.</i></p>
                    </div>
                </div>                
            </div>
            <hr>
            <div class="dashboardContent">
                <div class="col-md-12">
                    <%
                        int i = 1;
                        String qtype;
                        String o1, o2, o3, o4, cans;
                        for (QuestionDTO qobj : allQuestion) {
                            qtype = qobj.getqType();
                            out.println("<p><b>QNO " + i + ":    " + "<textarea disabled=\"true\">" + qobj.getQuestion() + "</textarea></b></p>");
                            if (qtype.equalsIgnoreCase("mcq")) {
                                for (mcqDTO mcqobj : allmcqAnswer) {
                                    if (Integer.parseInt(mcqobj.getQid()) == i) {
                    %>
                    <p>Option1: <textarea disabled="true"><%=mcqobj.getOption1()%></textarea></p><p>Option2:<textarea disabled="true"><%=mcqobj.getOption2()%></textarea></p>
                    <p>Option3: <textarea disabled="true"><%=mcqobj.getOption3()%></textarea></p><p>Option4:<textarea disabled="true"><%=mcqobj.getOption4()%></textarea></p>
                    <p>Correct Answer: <b><%= mcqobj.getCanswer()%></b></p>                                    
                    <%
                                    }
                                }
                            } else if (qtype.equalsIgnoreCase("fups")) {
                                for (fupsDTO fupsobj : allfupsAnswer) {
                                    if (Integer.parseInt(fupsobj.getQid()) == i) { %>
                                    <p>Correct Answer:<textarea> <%=fupsobj.getCanswer() %></textarea></p>
                                    <%}
                                }
                            } else if (qtype.equalsIgnoreCase("tf")) {
                                for (tfDTO tfobj : alltfAnswer) {
                                    if (Integer.parseInt(tfobj.getQid()) == i) {
                                        out.println("<p>Corrent Answer: <b>" + tfobj.getCanswer() + "</b></p>");
                                    }
                                }
                            }
                            out.println("<hr>");
                            i += 1;
                        }

                    %>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <button class="btn btn-danger btn-block" onclick="cancleexam()">Cancel Exam</button>
                        </div>
                    </div>
                    <div class="col-md-6"> 
                        <div class="form-group">
                            <button class="btn btn-primary btn-block" onclick="saveexam()">Save Paper</button>
                        </div>                    
                    </div>
                </div>
            </div>
        </div>        
    </div>
</div>