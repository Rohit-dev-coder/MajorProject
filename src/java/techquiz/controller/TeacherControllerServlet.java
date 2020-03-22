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
import techquiz.dao.ChatFileHandlingDAO;
import techquiz.dao.ExamDAO;
import techquiz.dao.NoticeDAO;
import techquiz.dao.UserDAO;
import techquiz.dto.ExamDTO;
import techquiz.dto.NoticeDTO;
import techquiz.dto.UserDetails;
import techquiz.dto.chatmessageDTO;

public class TeacherControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setDateHeader("Expires", -1);
//        System.out.println("Teacher controller");
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
                String queryof = (String) request.getParameter("data");
                System.out.println("Queryfor " + queryof);
                if (queryof.equalsIgnoreCase("profile")) {
                    UserDetails obj = UserDAO.getSingleUserDetail(username);
                    ArrayList<NoticeDTO> nobj = NoticeDAO.getAllNotice();
                    request.setAttribute("data", obj);
                    request.setAttribute("result", "studentdetails");
                    request.setAttribute("utype","teacher");
                    request.setAttribute("notices", nobj);
                    rd = request.getRequestDispatcher("userdetails.jsp");
                } else if (queryof.equalsIgnoreCase("Set-exams")) {
                    ExamDTO o = (ExamDTO) session.getAttribute("paperdetails");
                    if (o == null) {
                        rd = request.getRequestDispatcher("setexam.jsp");
                    } else {
                        int i = (int) session.getAttribute("currentQuestionNo");
                        if (i == o.getTotalQuestion()) {
                            rd = request.getRequestDispatcher("previewquestions.jsp");
                        }
                        rd = request.getRequestDispatcher("setquestions.jsp");
                    }
                } else if (queryof.equalsIgnoreCase("Exams-details")) {
                    ArrayList<ExamDTO> al = ExamDAO.getAllExamByEmail(username);
                    request.setAttribute("allexams", al);
                    rd = request.getRequestDispatcher("examdetails.jsp");
                } else if (queryof.equalsIgnoreCase("Declared-Rank")) {
                    ArrayList<ExamDTO> al = ExamDAO.getAllExamByEmail(username);
                    request.setAttribute("allexams", al);
                    rd = request.getRequestDispatcher("declaredrank.jsp");
                } else if (queryof.equalsIgnoreCase("wchat")) {
                    ArrayList<chatmessageDTO> al = ChatFileHandlingDAO.readmsg();
                    request.setAttribute("msges", al);
                    rd = request.getRequestDispatcher("wchat.jsp");
                } else if (queryof.equalsIgnoreCase("News")) {
                    rd = request.getRequestDispatcher("news.jsp");
                } else if (queryof.equalsIgnoreCase("Settings")){ 
                    UserDetails uobj = UserDAO.getSingleUserDetail(username);
                    request.setAttribute("uobj", uobj);
                    rd = request.getRequestDispatcher("settings.jsp");
                } else {
                    request.setAttribute("result", "error");
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
