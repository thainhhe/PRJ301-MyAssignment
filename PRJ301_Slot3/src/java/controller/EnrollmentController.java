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
   


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        req.getRequestDispatcher("create.html").forward(req, resp);
    } 


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
            //d.setId(req.getParameter("department"+i));
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
