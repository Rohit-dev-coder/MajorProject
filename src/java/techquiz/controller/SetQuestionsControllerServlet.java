/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import techquiz.dao.ExamDAO;
import techquiz.dao.McqDAO;
import techquiz.dao.QuestionDAO;
import techquiz.dao.fupsDAO;
import techquiz.dao.tfDAO;
import techquiz.dto.ExamDTO;
import techquiz.dto.QuestionDTO;
import techquiz.dto.fupsDTO;
import techquiz.dto.mcqDTO;
import techquiz.dto.tfDTO;

/**
 *
 * @author PC
 */
public class SetQuestionsControllerServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        response.setHeader( "Pragma", "no-cache" );
//        response.setHeader( "Cache-Control", "no-cache" );
        response.setDateHeader("Expires", -1 );
//        System.out.println("set question controller");
        RequestDispatcher rd=null;
        HttpSession session=request.getSession();
//        System.out.println(session.getAttribute("username"));
        ExamDTO obj;
        ArrayList<QuestionDTO> allQuestion = new ArrayList<>();
        ArrayList<mcqDTO> allmcqAnswer= new ArrayList<>();
        ArrayList<tfDTO> alltfAnswer = new ArrayList<>();
        ArrayList<fupsDTO> allfupsAnswer = new ArrayList<>();
        
        
        try{
            
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
            else if(usertype.equalsIgnoreCase("teacher"))
            {
                String code = (String)request.getParameter("code");
                if(code.equalsIgnoreCase("paperdetails")){   
                    String examtitle =  (String)request.getParameter("examtitle");
                    String examdate = (String)request.getParameter("examdate");                
                    String examtime = (String)request.getParameter("examtime");
                    String fulldatetime = examdate+" "+examtime+":00";
//                System.out.println(fulldatetime);
                    int tquestion = Integer.parseInt(request.getParameter("tquestion"));
                    int tmarks = Integer.parseInt(request.getParameter("tmarks"));
//                String examid = ExamDAO.getExamID();
                    obj = new ExamDTO();
                    obj.setEmail(userid);
//                obj.setExamId(examid);
                    obj.setExamTitle(examtitle);
                    obj.setTotalMarks(tmarks);
                    obj.setTotalQuestion(tquestion);
                    obj.setExamDateTime(fulldatetime);  
                session.setAttribute("paperdetails", obj);
                session.setAttribute("currentQuestionNo", 1);
                session.setAttribute("allquestion", allQuestion);
                session.setAttribute("allfupsanswer", allfupsAnswer);
                session.setAttribute("allmcqanswer", allmcqAnswer);
                session.setAttribute("alltfanswer", alltfAnswer);
                session.setMaxInactiveInterval(60*15);
                rd = request.getRequestDispatcher("setquestions.jsp");
            }
            else if(code.equalsIgnoreCase("cancleexam"))
            {
                session.removeAttribute("paperdetails");
                session.removeAttribute("currentQuestionNo");
                session.setMaxInactiveInterval(60*5);
                rd=request.getRequestDispatcher("setexam.jsp");
            }
            else if(code.equalsIgnoreCase("nextque"))
            {
                allQuestion = (ArrayList<QuestionDTO>)session.getAttribute("allquestion");
                allmcqAnswer= (ArrayList<mcqDTO>)session.getAttribute("allmcqanswer");
                alltfAnswer = (ArrayList<tfDTO>)session.getAttribute("alltfanswer");
                allfupsAnswer = (ArrayList<fupsDTO>)session.getAttribute("allfupsanswer");
                
                String qtype = (String)request.getParameter("qtype");
                String question = (String)request.getParameter("question");
                int cque = (int)session.getAttribute("currentQuestionNo");
                QuestionDTO qo = new QuestionDTO();
                qo.setQuestion(question);
                qo.setqType(qtype);
                String tquid = String.valueOf(cque);
                qo.setQid(tquid);
                System.out.println(tquid);
                allQuestion.add(qo);
                
                if(qtype.equalsIgnoreCase("mcq")){
                    String option1 = (String)request.getParameter("option1");
                    String option2 = (String)request.getParameter("option2");
                    String option3 = (String)request.getParameter("option3");
                    String option4 = (String)request.getParameter("option4");
                    String cans = (String)request.getParameter("cans");
                    mcqDTO o = new mcqDTO();
                    o.setOption1(option1);
                    o.setOption2(option2);
                    o.setOption3(option3);
                    o.setOption4(option4);
                    o.setCanswer(cans);
                    o.setQid(tquid);
                    allmcqAnswer.add(o);                    
                }
                else if(qtype.equalsIgnoreCase("fups")){
                    String cans = (String)request.getParameter("cans");
                    fupsDTO o = new fupsDTO();
                    o.setCanswer(cans);
                    o.setQid(tquid);
                    allfupsAnswer.add(o);
                }
                else if(qtype.equalsIgnoreCase("tf")){
                    String cans = (String)request.getParameter("cans");
                    tfDTO o = new tfDTO();
                    o.setCanswer(cans);
                    o.setQid(tquid);
                    alltfAnswer.add(o);
                }                
                cque+=1;
                obj = (ExamDTO)session.getAttribute("paperdetails");
                if(cque > obj.getTotalQuestion())
                {
                    System.out.println("True redirencting to preview");
                    rd = request.getRequestDispatcher("previewquestions.jsp");
                }
                else{
                    rd = request.getRequestDispatcher("setquestions.jsp");
                    session.setAttribute("currentQuestionNo", cque);
                }
                
            }
            else if(code.equalsIgnoreCase("saveexam"))
            {
                allQuestion = (ArrayList<QuestionDTO>)session.getAttribute("allquestion");
                allmcqAnswer= (ArrayList<mcqDTO>)session.getAttribute("allmcqanswer");
                alltfAnswer = (ArrayList<tfDTO>)session.getAttribute("alltfanswer");
                allfupsAnswer = (ArrayList<fupsDTO>)session.getAttribute("allfupsanswer");
                obj = (ExamDTO)session.getAttribute("paperdetails");
                obj.setExamId(ExamDAO.getExamID());
                boolean result = ExamDAO.saveExam(obj);
                if (result == false){
                    Exception e = new Exception();
                    throw e;
                }
                System.out.println("EXAM SAVE: ID-->"+obj.getExamId());
                for(QuestionDTO qobj: allQuestion){
                    String tempqid = qobj.getQid();
                    String qtype = qobj.getqType();
                    
                    qobj.setExamId(obj.getExamId());
                    
                    String newqid = QuestionDAO.getNewQuestionID();
                    qobj.setQid(newqid);
                    
                    result = QuestionDAO.saveQuestion(qobj);
                    
                    if (result == false){
                        Exception e = new Exception();
                        throw e;
                    }
                    System.out.println("QuestionSaved");
                    
                    if(qtype.equalsIgnoreCase("mcq")){
                        for(mcqDTO mcqobj: allmcqAnswer){
                            if(mcqobj.getQid().equalsIgnoreCase(tempqid)){
                                mcqobj.setQid(newqid);
                                result = McqDAO.saveMcqQuestionAnswer(mcqobj);
                                if (result == false){
                                    Exception e = new Exception();
                                    throw e;
                                }
                                System.out.println("AnswerSaved");
                                break;
                            }
                        }                        
                    }
                    else if(qtype.equalsIgnoreCase("fups")){
                        for(fupsDTO fobj : allfupsAnswer){
                            if(fobj.getQid().equalsIgnoreCase(tempqid)){
                                fobj.setQid(newqid);
                                result = fupsDAO.savefupsQuestionAnswer(fobj);
                                if (result == false){
                                    Exception e = new Exception();
                                    throw e;
                                }
                                System.out.println("AnswerSaved");
                                break;
                            }
                        }
                    }
                    else if(qtype.equalsIgnoreCase("tf")){
                        for(tfDTO tfobj: alltfAnswer){
                            if(tfobj.getQid().equalsIgnoreCase(tempqid)){
                                tfobj.setQid(newqid);
                                result = tfDAO.savetfQuestionAnswer(tfobj);
                                if (result == false){
                                    Exception e = new Exception();
                                    throw e;
                                }
                                System.out.println("AnswerSaved");
                                break;
                            }
                        }
                    }
                    
                }
                session.removeAttribute("paperdetails");
                session.removeAttribute("currentQuestionNo");
                session.removeAttribute("allquestion");
                session.removeAttribute("allmcqanswer");
                session.removeAttribute("alltfanswer");
                session.removeAttribute("allfupsanswer");
                session.setMaxInactiveInterval(60*5);
                
                session.setAttribute("status", "save");
                rd=request.getRequestDispatcher("responses.jsp");
                               
            }
                
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            request.setAttribute("exception", e);
            rd=request.getRequestDispatcher("showexception.jsp");
        }
        finally
        {
            rd.forward(request, response);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
