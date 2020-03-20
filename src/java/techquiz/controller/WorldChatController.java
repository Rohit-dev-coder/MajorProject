package techquiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import techquiz.dao.ChatFileHandlingDAO;
import techquiz.dto.chatmessageDTO;

public class WorldChatController extends HttpServlet {

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
            String queryof = (String) request.getParameter("code");
            if (queryof.equalsIgnoreCase("sendmsg")) {
                chatmessageDTO cmsgobj = new chatmessageDTO();
                cmsgobj.setUtype(usertype);
                cmsgobj.setUemail(username);
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm");
                String dt = sdf.format(d);
                cmsgobj.setDatetime(dt);
                String msg = request.getParameter("data");
                cmsgobj.setMessage(msg);
                boolean result = ChatFileHandlingDAO.insertonemsg(cmsgobj);
                System.out.println(result);
                if (result == false) {
                    Exception e = new Exception();
                    throw e;
                }
                pw.print("sendsucc");
            } else if (queryof.equalsIgnoreCase("wchat")) {
                ArrayList<chatmessageDTO> al = ChatFileHandlingDAO.readmsg();
                request.setAttribute("msges", al);
                rd = request.getRequestDispatcher("wchat.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", e);
            rd = request.getRequestDispatcher("showexception.jsp");
            rd.forward(request, response);
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
