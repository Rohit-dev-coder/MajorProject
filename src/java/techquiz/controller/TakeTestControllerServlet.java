/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.controller;

import java.io.IOException;
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
import techquiz.dao.ResultDAO;
import techquiz.dao.UserDAO;
import techquiz.dao.fupsDAO;
import techquiz.dao.tfDAO;
import techquiz.dto.resultDTO;
import techquiz.dto.EnrollDTO;
import techquiz.dto.ExamDTO;
import techquiz.dto.QuestionDTO;
import techquiz.dto.UserDetails;
import techquiz.dto.answersDTO;
import techquiz.dto.checkAnswer;
import techquiz.dto.mcqOptionsDTO;
import techquiz.dto.stdexamdetails;
import techquiz.dto.testdetail;

/**
 *
 * @author PC
 */
public class TakeTestControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setDateHeader("Expires", -1);
        RequestDispatcher rd = null;
        HttpSession session = request.getSession();
        try {
            String username = (String) session.getAttribute("username");
            String usertype = (String) session.getAttribute("usertype");
            System.out.println("userid " + username);
            if (username == null || usertype == null) {
                session.invalidate();
                System.out.println("failed redirect");
                response.sendRedirect("loginpage.html");
                return;
            }
            if (usertype.equalsIgnoreCase("teacher")) {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            } else if (usertype.equalsIgnoreCase("student")) {
                String code = (String) request.getParameter("code");
                System.out.println("Query for: " + code);
                if (code.equalsIgnoreCase("enroll")) {
                    String eid = (String) request.getParameter("data");
                    String status = "E";
                    EnrollDTO eobj = new EnrollDTO(eid, username, status);
                    boolean result = ExamDAO.enrollStd(eobj);
                    if (result == false) {
                        Exception e = new Exception();
                        throw e;
                    }
                    ArrayList<stdexamdetails> al = ExamDAO.getAllExams();
                    for (stdexamdetails obj : al) {
                        String examId = obj.getExamId();
                        String email = username;
                        String results = ExamDAO.getStatus(examId, email);
                        if (results == null) {
                            obj.setStatus("NE");
                        } else {
                            obj.setStatus(results);
                        }
                    }
                    request.setAttribute("allexams", al);
                    rd = request.getRequestDispatcher("exams.jsp");
                } else if (code.equalsIgnoreCase("starttest")) {
                    String examid = (String) request.getParameter("data");
                    testdetail obj = ExamDAO.getExamByID(examid);
                    String tname = UserDAO.getTeacherName(obj.getEmail());
                    obj.setTeachername(tname);
                    session.setAttribute("examdetails", obj);
                    session.setMaxInactiveInterval(60 * 60 * 4);
                    rd = request.getRequestDispatcher("testconfirmation.jsp");
                } else if (code.equalsIgnoreCase("cancelexm")) {
                    session.removeAttribute("examdetails");
                    session.setMaxInactiveInterval(60 * 2);
                    rd = request.getRequestDispatcher("StudentControllerServlet?data=exams");
                } else if (code.equalsIgnoreCase("startexam")) {
                    testdetail obj = (testdetail) session.getAttribute("examdetails");
                    ArrayList<QuestionDTO> qal = QuestionDAO.getAllQuestionByExamid(obj.getExamId());
                    ArrayList<mcqOptionsDTO> mcqal = new ArrayList<>();
                    String min = ExamDAO.getexamminutes(obj.getExamId());
                    for (QuestionDTO qq : qal) {
                        if (qq.getqType().equalsIgnoreCase("mcq")) {
                            mcqOptionsDTO ooo = McqDAO.getAllOptions(qq.getQid());
                            mcqal.add(ooo);
                        }
                    }
                    request.setAttribute("min", min);
                    session.setAttribute("allquestion", qal);
                    session.setAttribute("questionsoption", mcqal);
                    rd = request.getRequestDispatcher("exampage.jsp");
                } else if (code.equalsIgnoreCase("submitexam")) {
                    testdetail obj = (testdetail)session.getAttribute("examdetails");
                    ArrayList<QuestionDTO> allques = (ArrayList<QuestionDTO>) session.getAttribute("allquestion");
                    ArrayList<checkAnswer> cansobj = new ArrayList<>();
                    int cans = 0;
                    int wans = 0;
                    int uattemp = 0;
                    for (QuestionDTO o : allques) {
                        checkAnswer cobj = new checkAnswer();
                        String answer = null;
                        try{
                            answer = request.getParameter(o.getQid()).trim();
                        }catch(Exception e){
                              ++uattemp;
                              continue;
                        }
                        cobj.setYouranswer(answer);
//                        System.out.print(o.getQid()+"::::::"+answer);
                        String questype = QuestionDAO.getQuestionType(o.getQid());
                        if (questype.equalsIgnoreCase("mcq")) {
                            String corrans = McqDAO.getcanswer(o.getQid());
                            cobj.setCanswer(corrans);
//                            System.out.println(":::::"+corrans);
                            if (answer.equalsIgnoreCase("")) {
                                ++uattemp;
                            } else if (answer.equalsIgnoreCase(corrans)) {
                                ++cans;
                            } else {
                                ++wans;
                            }
                        } else if (questype.equalsIgnoreCase("fups")) {
                            String corrans = fupsDAO.getCorrAnswer(o.getQid());
                            cobj.setCanswer(corrans);
//                            System.out.println(":::::"+corrans);
                            if (answer.equalsIgnoreCase("")) {
                                ++uattemp;
                            } else if (answer.equalsIgnoreCase(corrans)) {
                                ++cans;
                            } else {
                                ++wans;
                            }
                        } else if (questype.equalsIgnoreCase("tf")) {                            
                            String corrans = tfDAO.getCorrAnswer(o.getQid());
                            cobj.setCanswer(corrans);
//                            System.out.println(":::::"+corrans);
                            if (answer.equalsIgnoreCase("select")) {
                                ++uattemp;
                            } else if (answer.equalsIgnoreCase(corrans)) {
                                ++cans;
                            } else {
                                ++wans;
                            }
                        }
                        cansobj.add(cobj);
                    }
                    resultDTO robj = new resultDTO();
                    robj.setEmailid(username);
                    robj.setExamid(obj.getExamId());
                    robj.setRightans(cans);
                    robj.setWrongans(wans);
                    robj.setUnattempt(uattemp);
                    robj.setTotalque(obj.getTotalQuestion());
                    robj.setTotalattempt(obj.getTotalQuestion() - uattemp);
                    robj.setTotalmarks(obj.getTotalMarks());
                    robj.calculatePercentage();
                    System.out.println(robj);
                    ExamDAO.updateEnrollTableStatus(obj.getExamId(), username);
                    session.removeAttribute("examdetails");
                    session.removeAttribute("allquestion");
                    session.removeAttribute("questionsoption");
                    session.setMaxInactiveInterval(60*2);
                    request.setAttribute("result", robj);
                    request.setAttribute("cansobj", cansobj);
//                    System.out.println(robj);
                    boolean result = ResultDAO.saveResult(robj);
                    if (result == false) {
                        Exception e = new Exception();
                        throw e;
                    }
                    rd = request.getRequestDispatcher("afterexamdetailsresult.jsp");
                }
                else if (code.equalsIgnoreCase("resulttest")) {
                   String examid = (String) request.getParameter("data");
                   resultDTO robj = ResultDAO.getResultByid(username, examid);
                   if (robj == null) {
                        Exception e = new Exception();
                        throw e;
                    }
                   request.setAttribute("result", robj);
                   rd = request.getRequestDispatcher("afterexamdetailsresult.jsp");
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", e);
            rd = request.getRequestDispatcher("showexception.jsp");
        } finally {
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
