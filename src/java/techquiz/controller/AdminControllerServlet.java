
package techquiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import techquiz.dao.UserDAO;
import techquiz.dto.UserDetails;


public class AdminControllerServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String queryfor = request.getParameter("data");
        RequestDispatcher rd=null;
//        HttpSession session=request.getSession();
        try{
//            String userid=(String)session.getAttribute("userid");
//            if(userid==null)
//            {
//                session.invalidate();
//                response.sendRedirect("accessdenied.html");
//                return;
//            }
            if(queryfor.equalsIgnoreCase("View-Students"))
            {
                ArrayList<UserDetails> resultdata = UserDAO.getAllUsers("student");
                request.setAttribute("resultdata", resultdata);
            }
            else if(queryfor.equalsIgnoreCase("View-teachers"))
            {
                ArrayList<UserDetails> resultdata = UserDAO.getAllUsers("teacher");
                request.setAttribute("resultdata", resultdata);
            }
            
            rd=request.getRequestDispatcher("showuserstable.jsp");
            }
            catch(Exception e)
            {
                e.printStackTrace();
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
