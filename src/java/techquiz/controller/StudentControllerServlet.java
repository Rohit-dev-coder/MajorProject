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
import techquiz.dao.ResultDAO;
import techquiz.dao.UserDAO;
import techquiz.dto.EnrollDTO;
import techquiz.dto.ExamDTO;
import techquiz.dto.QuestionDTO;
import techquiz.dto.UserDetails;
import techquiz.dto.examsampleinfo;
import techquiz.dto.rankDTO;
import techquiz.dto.stdexamdetails;
import techquiz.dto.testdetail;


public class StudentControllerServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setDateHeader("Expires", -1);
//        System.out.println("Sttudent controller");
        
            RequestDispatcher rd=null;
            HttpSession session=request.getSession();
            try
            {
                String username=(String)session.getAttribute("username");
                String usertype=(String)session.getAttribute("usertype");
                System.out.println("userid "+ username);
                if(username == null || usertype == null)
                {
                    session.invalidate();
                    System.out.println("failed redirect");
                    response.sendRedirect("loginpage.html");
                    return;
                }
                if(usertype.equalsIgnoreCase("teacher")){
                    session.invalidate();
                    response.sendRedirect("accessdenied.html");
                    return;
                }
                else if(usertype.equalsIgnoreCase("student"))
                {
                    String queryof = (String)request.getParameter("data");
                    System.out.println("Queryfor "+ queryof);
                    if (queryof.equalsIgnoreCase("profile"))
                    {
                        UserDetails obj = UserDAO.getSingleUserDetail(username);
//                        System.out.println(obj);
                        request.setAttribute("data", obj);
                        request.setAttribute("result", "studentdetails");
                        rd=request.getRequestDispatcher("userdetails.jsp");
                    }
                    else if(queryof.equalsIgnoreCase("exams")){
                        testdetail tdo = (testdetail)session.getAttribute("examdetails");
                        if(tdo == null)
                        {
                            ArrayList<stdexamdetails> al = ExamDAO.getAllExams();
                            for(stdexamdetails obj : al){
                                String examId = obj.getExamId();
                                String email = username;
                                String result = ExamDAO.getStatus(examId, email);
                                if(result == null)
                                    obj.setStatus("NE");
                                else
                                    obj.setStatus(result);                            
                            }
                            request.setAttribute("allexams", al);
                            rd=request.getRequestDispatcher("exams.jsp");
                        }
                        else{
                            ArrayList<QuestionDTO> qal = (ArrayList<QuestionDTO>)session.getAttribute("allquestion");
                            if(qal == null){
                                rd=request.getRequestDispatcher("testconfirmation.jsp");
                            }
                            else{
                               rd = request.getRequestDispatcher("exampage.jsp"); 
                            }
                            
                        }
                    }
                    else if(queryof.equalsIgnoreCase("ResultList")){
                        ArrayList<examsampleinfo> al = ExamDAO.getAllDeclaredExamDetails();
                        request.setAttribute("al", al);
                        rd=request.getRequestDispatcher("resultlistforstudent.jsp");
                    }
                    else if(queryof.equalsIgnoreCase("News")){
                        rd=request.getRequestDispatcher("news.jsp");
                    }
                    else if(queryof.equalsIgnoreCase("Forum")){
                        rd=request.getRequestDispatcher("forum.jsp");
                    }
                    else if(queryof.equalsIgnoreCase("Settings")){
                       rd=request.getRequestDispatcher("settings.jsp");
                    }
                    else{
                        request.setAttribute("result", "error");
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
