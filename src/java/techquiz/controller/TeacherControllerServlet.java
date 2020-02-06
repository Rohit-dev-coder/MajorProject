/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import techquiz.dao.UserDAO;
import techquiz.dto.ExamDTO;
import techquiz.dto.UserDetails;

public class TeacherControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setDateHeader("Expires", 0);
//        System.out.println("Teacher controller");
        
            RequestDispatcher rd=null;
            HttpSession session=request.getSession();
            try
            {
                String username=(String)session.getAttribute("username");
                System.out.println("userid "+ username);
                if(username == null)
                {
                    session.invalidate();
                    System.out.println("failed redirect");
                    response.sendRedirect("loginpage.html");
                    return;
                }
                
                    
                String queryof = (String)request.getParameter("data");
                System.out.println("Queryfor "+ queryof);
                if (queryof.equalsIgnoreCase("profile"))
                {
                    UserDetails obj = UserDAO.getSingleUserDetail(username);
                    System.out.println(obj);
                    request.setAttribute("data", obj);
                    request.setAttribute("result", "studentdetails");
                    rd = request.getRequestDispatcher("userdetails.jsp");
                }
                else if(queryof.equalsIgnoreCase("Set-exams")){
                    ExamDTO o = (ExamDTO)session.getAttribute("paperdetails");
                    if(o==null)
                    {
                        rd=request.getRequestDispatcher("setexam.jsp");
                    }
                    else{
                        int i = (int)session.getAttribute("currentQuestionNo");
                        if(i == o.getTotalQuestion()){
                            rd = request.getRequestDispatcher("previewquestions.jsp");
                        }
                        rd=request.getRequestDispatcher("setquestions.jsp");
                    }
                }
                else if(queryof.equalsIgnoreCase("Exams-request")){
                    rd=request.getRequestDispatcher("examrequest.jsp");
                }
                else if(queryof.equalsIgnoreCase("declared-result")){
                    rd=request.getRequestDispatcher("declaredresult.jsp");
                }
                else if(queryof.equalsIgnoreCase("Forum")){
                    rd=request.getRequestDispatcher("Forum.jsp");
                }
                else if(queryof.equalsIgnoreCase("Settings")){
                    rd=request.getRequestDispatcher("settings.jsp");
                }
                else{
                    request.setAttribute("result", "error");
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
