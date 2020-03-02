/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import techquiz.dao.UserDAO;
import techquiz.dto.UserDTO;

public class LoginControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String utype = request.getParameter("type");

        UserDTO user = new UserDTO(username, password, utype);
//        System.out.println(user);
        try {
            Enumeration en = request.getHeaderNames();
            String headervalue = request.getHeader("user-agent");
            
            if (headervalue.contains("Chrome")==false || headervalue.contains("Edge")==true) {
//                System.out.println("false");
                request.setAttribute("status","false");
                rd = request.getRequestDispatcher("loginresponse.jsp");
            } else {
                boolean result = UserDAO.validateUser(user);
//            System.out.println(result);
                request.setAttribute("result", result);
                request.setAttribute("utype", utype);
                request.setAttribute("username", username);
                request.setAttribute("status","true");
                rd = request.getRequestDispatcher("loginresponse.jsp");
            }

        } catch (SQLException e) {
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
