/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.authentication.BasedAuthorizationController;
import controller.authentication.BasedRequiredAuthenticationController;
import dal.DeptDBContext;
import dal.StudentDBContext;
import entity.Account;
import entity.Department;
import entity.Role;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class EditController extends BasedAuthorizationController {
   
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles )
    throws ServletException, IOException {
        //check login
        DeptDBContext deptDB = new DeptDBContext();
        ArrayList<Department> depts = deptDB.list();
        
        int sid = Integer.parseInt(request.getParameter("id"));
        StudentDBContext studentDB = new StudentDBContext();
        Student student = new Student();
        student.setId(sid);
        studentDB.get(student);
        
        request.setAttribute("depts", depts);
        request.setAttribute("student", student);
        
        request.getRequestDispatcher("../view/student/edit.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response,Account account,ArrayList<Role> roles)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        boolean gender = request.getParameter("gender").equals("male");
        Date dob = Date.valueOf(request.getParameter("dob"));// yyyy-MM-dd
        int did = Integer.parseInt(request.getParameter("did"));
        Department d = new Department();
        d.setId(did);
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setGender(gender);
        s.setDob(dob);
        s.setDept(d);
        StudentDBContext db = new StudentDBContext();
        db.update(s);
        response.sendRedirect("list");
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