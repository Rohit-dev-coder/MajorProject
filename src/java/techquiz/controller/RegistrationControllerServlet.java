/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import techquiz.dao.RegistrationDao;
import techquiz.dto.UserDetails;

/**
 *
 * @author PC
 */
public class RegistrationControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=null;
//        System.out.println("RC");
        UserDetails user=new UserDetails();
        user.setFname(request.getParameter("fname").trim());
        user.setSname(request.getParameter("sname").trim());
        user.setEmail(request.getParameter("email").trim());
        user.setPwd(request.getParameter("pwd"));
        user.setGender(request.getParameter("gender"));
        user.setMobile(Long.parseLong(request.getParameter("mobile")));
        
        try{
            boolean result=false,userfound=false;
            if(!RegistrationDao.searchUser(user.getEmail()))
            {
                result=RegistrationDao.registerUser(user);
            }
            else
            {
                userfound=true;
            }
            request.setAttribute("result", result);
            request.setAttribute("userfound", userfound);
            //request.setAttribute("username", user.getFname());
            rd=request.getRequestDispatcher("registrationresponse.jsp");
        }
        catch(SQLException e){
            request.setAttribute("exception",e);
            rd=request.getRequestDispatcher("showexception.jsp");
            e.printStackTrace();
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
