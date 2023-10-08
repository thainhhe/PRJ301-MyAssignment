/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Course;
import entity.Department;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class EnrollmentController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EnrollmentController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EnrollmentController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        req.getRequestDispatcher("create.html").forward(req, resp);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        int courseid = Integer.parseInt(req.getParameter("classId"));
        String coursename = req.getParameter("className");
        Date startdate = Date.valueOf(req.getParameter("startDate"));
        boolean allowRetake = req.getParameter("allowRetake")!=null;
        Course c = new Course();
        c.setId(courseid);
        c.setTitle(coursename);
        c.setStartdate(startdate);
        c.setAllowretake(allowRetake);
        
        int row_count = Integer.parseInt(req.getParameter("row_count"));
        for (int i = 1; i <= row_count; i++) {
            int id = Integer.parseInt(req.getParameter("id"+i));
            String name = req.getParameter("name"+i);
            boolean gender = req.getParameter("gender"+i).equals("male");
            Date dob = Date.valueOf(req.getParameter("dob"+i));
            Department d = new Department();
            d.setId(req.getParameter("department"+i));
            d.setName(req.getParameter("department"+i));
            Student s = new Student();
            s.setDept(d);
            s.setCourse(c);
            s.setId(id);
            s.setName(name);
            s.setGender(gender);
            s.setDob(dob);
            c.getStudents().add(s);
        }
        req.setAttribute("course", c);
        req.getRequestDispatcher("view/enroll").forward(req, resp);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
