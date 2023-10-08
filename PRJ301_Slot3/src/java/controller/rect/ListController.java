/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.rect;

import entity.Rect;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sonnt
 */
public class ListController extends HttpServlet {
   
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int num = Integer.parseInt(request.getParameter("num"));
        ArrayList<Rect> rects = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            int w = random.nextInt(101) + 50;
            int h = random.nextInt(101) + 50;
            int x = random.nextInt(501 - w);
            int y = random.nextInt(501 - h);
            int r = random.nextInt(256);
            int g = random.nextInt(256); 
            int b = random.nextInt(256); 
            Rect rect = new Rect();
            rect.setX(x);
            rect.setY(y);
            rect.setW(w);
            rect.setH(h);
            rect.setR(r);
            rect.setG(g);
            rect.setB(b);
            rects.add(rect);
        }
        request.setAttribute("rects", rects);
        request.getRequestDispatcher("../view/rect/list.jsp").forward(request, response);
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
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
