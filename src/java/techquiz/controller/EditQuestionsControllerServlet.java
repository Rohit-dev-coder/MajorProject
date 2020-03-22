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
import techquiz.dao.ResultDAO;
import techquiz.dao.UserDAO;
import techquiz.dao.fupsDAO;
import techquiz.dao.tfDAO;
import techquiz.dto.QuestionDTO;
import techquiz.dto.UserDetails;
import techquiz.dto.fupsDTO;
import techquiz.dto.mcqDTO;
import techquiz.dto.rankDTO;
import techquiz.dto.testdetail;
import techquiz.dto.tfDTO;

public class EditQuestionsControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setDateHeader("Expires", -1);
        try (PrintWriter out = response.getWriter()) {
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

                if (usertype.equalsIgnoreCase("student")) {
                    session.invalidate();
                    response.sendRedirect("accessdenied.html");
                    return;
                } else if (usertype.equalsIgnoreCase("teacher")) {
                    String queryof = (String) request.getParameter("code");
                    System.out.println("query for" + queryof);
                    if (queryof.equalsIgnoreCase("editexam")) {
                        String examid = (String) request.getParameter("data");
                        ArrayList<String> al = QuestionDAO.getAllQidbyEid(examid);
                        testdetail td = ExamDAO.getExamByID(examid);
                        request.setAttribute("al", al);
                        request.setAttribute("td", td);
                        rd = request.getRequestDispatcher("editpaper.jsp");
                    } else if (queryof.equalsIgnoreCase("startexam")) {
                        String examid = (String) request.getParameter("data");
                        String min = (String) request.getParameter("min");

                        boolean result = ExamDAO.startexam(examid, min);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        session.setAttribute("status", "Paper Start");
                        rd = request.getRequestDispatcher("responses.jsp");
                    } else if (queryof.equalsIgnoreCase("endexam")) {
                        String examid = (String) request.getParameter("data");
                        boolean result = ExamDAO.endexam(examid);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        session.setAttribute("status", "Paper End");
                        rd = request.getRequestDispatcher("responses.jsp");
                    } else if (queryof.equalsIgnoreCase("declaredrank")) {
                        String examid = (String) request.getParameter("data");
                        boolean result = ExamDAO.declaredRank(examid);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        rd = request.getRequestDispatcher("TeacherControllerServlet?data=Declared-Rank");
                    } else if (queryof.equalsIgnoreCase("showranks")) {
                        String examid = (String) request.getParameter("data");

                        ArrayList<rankDTO> al = ResultDAO.getAllResultbyexamid(examid);
                        request.setAttribute("examid", examid);
                        request.setAttribute("examtitle", ExamDAO.getExamByID(examid).getExamTitle());
                        request.setAttribute("result", al);
                        rd = request.getRequestDispatcher("resultlist.jsp");
                    } else if (queryof.equalsIgnoreCase("back")) {
                        rd = request.getRequestDispatcher("TeacherControllerServlet?data=Declared-Rank");
                    } else if (queryof.equalsIgnoreCase("deleteresult")) {
                        String examid = (String) request.getParameter("data");
                        boolean result = ExamDAO.endexam(examid);

                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        ExamDAO.deletestdfromenroll(examid);
                        boolean r = ResultDAO.deleteAllResultbyexamid(examid);
                        rd = request.getRequestDispatcher("TeacherControllerServlet?data=Declared-Rank");
                    } else if (queryof.equalsIgnoreCase("getquestion")) {
                        String qid = request.getParameter("qid");
                        QuestionDTO obj = QuestionDAO.getQuestionById(qid);
                        request.setAttribute("obj", obj);
                        if (obj.getqType().equalsIgnoreCase("mcq")) {
                            mcqDTO answer = McqDAO.getMCQDTOById(qid);
                            request.setAttribute("answer", answer);
                            rd = request.getRequestDispatcher("editpaperresponse.jsp");
                        } else if (obj.getqType().equalsIgnoreCase("fups")) {
                            String canswer = fupsDAO.getCorrAnswer(qid);
                            fupsDTO answer = new fupsDTO(qid, canswer);
                            request.setAttribute("answer", answer);
                            rd = request.getRequestDispatcher("editpaperresponse.jsp");
                        } else if (obj.getqType().equalsIgnoreCase("tf")) {
                            String canswer = tfDAO.getCorrAnswer(qid);
                            tfDTO answer = new tfDTO(qid, canswer);
                            request.setAttribute("answer", answer);
                            rd = request.getRequestDispatcher("editpaperresponse.jsp");
                        }
                    } else if (queryof.equalsIgnoreCase("updateques")) {
                        String ques = request.getParameter("data");
                        String qid = request.getParameter("qid");
                        boolean result = QuestionDAO.updateQuestion(qid, ques);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        out.print("success");
                    }else if (queryof.equalsIgnoreCase("mcqoptionupdate")) {
                        String data = request.getParameter("data");
                        String qid = request.getParameter("qid");
                        String option = request.getParameter("option");
                        boolean result = McqDAO.updateoptions(option, data, qid);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        out.print("success");
                    }
                    else if (queryof.equalsIgnoreCase("mcqcansupdate")) {
                        String data = request.getParameter("data");
                        String qid = request.getParameter("qid");
                        boolean result = McqDAO.updateCans(data, qid);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        out.print("success");
                    }
                    else if (queryof.equalsIgnoreCase("tfcansupdate")) {
                        String data = request.getParameter("data");
                        String qid = request.getParameter("qid");
                        boolean result = tfDAO.updateCans(data, qid);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        out.print("success");
                    }
                    else if (queryof.equalsIgnoreCase("updatecansfups")) {
                        String data = request.getParameter("data");
                        String qid = request.getParameter("qid");
                        boolean result = fupsDAO.updateCans(data, qid);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        out.print("success");
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
