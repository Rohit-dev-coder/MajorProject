/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import techquiz.dao.LearningDAO;
import techquiz.dto.CorsesDTO;
import techquiz.dto.CoursesWithProgress;
import techquiz.dto.LecturesDTO;
import techquiz.dto.LecturesWithProgress;
import techquiz.dto.StudentLearingRec;

/**
 *
 * @author PC
 */
public class LearningControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setDateHeader("Expires", -1);
        PrintWriter pw = response.getWriter();
        RequestDispatcher rd = null;
        HttpSession session = request.getSession();
        try {
            String username = (String) session.getAttribute("username");
            String usertype = (String) session.getAttribute("usertype");
//            System.out.println("userid " + username);
            if (username == null || usertype == null) {
                session.invalidate();
//                System.out.println("failed redirect");
                response.sendRedirect("loginpage.html");
                return;
            }
            if (usertype.equalsIgnoreCase("teacher")) {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            } else if (usertype.equalsIgnoreCase("student")) {
                String queryof = (String) request.getParameter("code");
//                System.out.println("Queryfor " + queryof);
                if (queryof.equalsIgnoreCase("Learn")) {
                    ArrayList<CorsesDTO> al = LearningDAO.getAllCourses();
                    HashMap<String, Integer> hm = LearningDAO.getCourseProgressByCrs(username);
                    ArrayList<CoursesWithProgress> cwp = new ArrayList<>();
                    System.out.println("hmforcccc" + hm);
                    for (CorsesDTO o : al) {
                        CoursesWithProgress obj = new CoursesWithProgress();
                        obj.setC(o);
                        Integer i = hm.get(o.getCrsId());
                        if (i != null) {
                            obj.setMin(i);
                        }
                        cwp.add(obj);
                    }

                    rd = request.getRequestDispatcher("Courses.jsp");
                    request.setAttribute("cwp", cwp);
                    rd.forward(request, response);
                } else if (queryof.equalsIgnoreCase("lecturesList")) {
                    String cId = request.getParameter("data");
                    String ctitle = LearningDAO.getCrsNameById(cId);
                    ArrayList<LecturesDTO> al = LearningDAO.getAllLecturesByCrsId(cId);
                    HashMap<String, Integer> hm = LearningDAO.getlecturesProgressByEmail(username, cId);
//                    System.out.println(ctitle);

//                    System.out.println("hashmap" + hm);
                    ArrayList<LecturesWithProgress> lwp = new ArrayList<>();
                    for (LecturesDTO o : al) {
//                        System.out.println(o);
                        LecturesWithProgress obj = new LecturesWithProgress();
                        obj.setLd(o);
                        Integer i = hm.get(o.getLecId());
                        if (i != null) {
                            obj.setStdprog(i);
                        }
                        lwp.add(obj);
                    }

                    rd = request.getRequestDispatcher("CourseLectures.jsp");
                    request.setAttribute("lwp", lwp);
                    request.setAttribute("crsid", cId);
                    request.setAttribute("cname", ctitle);
                    rd.forward(request, response);
                } else if (queryof.equalsIgnoreCase("playVideo")) {
                    String cId = request.getParameter("cid");
                    String lId = request.getParameter("lid");
                    String ltitle = request.getParameter("ltitle");
                    String vref = LearningDAO.getVideoRefrence(cId, lId);
                    int lecTotalTime = LearningDAO.getLecTime(cId, lId);
                    StudentLearingRec slr = LearningDAO.getStudentLearningRecByLecAndCrs(cId, lId);
                    slr.setEmail(username);
                    rd = request.getRequestDispatcher("learing.jsp");
                    request.setAttribute("vref", vref);
                    request.setAttribute("ltitle", ltitle);
                    request.setAttribute("slr", slr);
                    request.setAttribute("lectt", lecTotalTime);
                    rd.forward(request, response);
                } else if (queryof.equalsIgnoreCase("UpdateRecord")) {
                    int newmin = Integer.parseInt(request.getParameter("data"));
                    String lecId = request.getParameter("lecid");
                    String crsId = request.getParameter("crsid");
                    System.out.println("newmin: " + newmin);
                    boolean result = LearningDAO.isStudentRecAval(username, crsId, lecId);
//                    System.out.println("Studnet aval " + result);
                    int LecTotalTime = LearningDAO.getLecTime(crsId, lecId);
                    if(newmin > LecTotalTime){
                        newmin = LecTotalTime;
                    }
                    if (result == false) {
                        boolean r = LearningDAO.insertStudentRecData(username, crsId, lecId, newmin);
//                        System.out.println("Student inserted" + r);
                        if (r == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                    } else {
                        boolean r = LearningDAO.UpdateStudentLearnRec(newmin, username, crsId, lecId);
                        if (r == false) {
                            Exception e = new Exception();
                            throw e;
                        }
                        pw.print("True");
                    }

                }

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
