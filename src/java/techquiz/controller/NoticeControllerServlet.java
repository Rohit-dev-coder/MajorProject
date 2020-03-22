/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import techquiz.dao.NoticeDAO;

/**
 *
 * @author PC
 */
public class NoticeControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setDateHeader("Expires", -1);
        RequestDispatcher rd = null;
        PrintWriter pw = response.getWriter();
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
                String code = (String) request.getParameter("code");
                if (code.equalsIgnoreCase("post")) {
                    String msg = (String) request.getParameter("data");
                    Pattern p = Pattern.compile("<([A-Z][A-Z0-9]*)\\b[^>]*>(.*?)</\\1>");//. represents single character  
                    Matcher m = p.matcher(msg);
                    boolean b = m.matches();
                    msg = msg.replace("<", " ");
                    msg = msg.replace(">", " ");
                    msg = msg.replace("/", " ");
                    if (b == false) {                       
                        boolean result = NoticeDAO.insertNotice(msg, username);
                        if (result == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        pw.print("success");
                    }
                    else{
                        pw.print("error");
                    }
                } else if (code.equalsIgnoreCase("deletepost")) {
                    String id = (String) request.getParameter("data");
                    boolean result = NoticeDAO.deleteSingleNotice(id);
                    if (result == false) {
                        Exception e = new Exception();
                        throw e;
                    }
                    pw.print("success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", e);
            rd = request.getRequestDispatcher("showexception.jsp");
        } finally {

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
