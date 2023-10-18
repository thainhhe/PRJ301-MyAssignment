/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import entity.Course;
import entity.Department;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 *
 * @author sonnt
 */
public class EnrollmentView extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         Course c =(Course)req.getAttribute("course");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head> </head> <body>");
        
        resp.getWriter().println(c.getTitle() + "<br/>");
        for (Student student : c.getStudents()) {
            resp.getWriter().println(student.getName() + "<br/>");
        }
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");
    }
    
}
