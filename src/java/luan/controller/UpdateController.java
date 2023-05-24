/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luan.controller;

import luan.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateController extends HttpServlet {

    private final String updateERR = "updateErr.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = updateERR;
        try (PrintWriter out = response.getWriter()) {         
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String lastname = request.getParameter("txtLastname");
            String searchValue = request.getParameter("lastSearchValue");
            boolean role = false;
            String admin = request.getParameter("ADMIN");
            if(admin != null){
                role = true;
            }
            
            try {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.update(username, password, lastname, role);
                if(result){
                    url = "MainController?btAction=Search&txtSearchValue="+ searchValue;
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                response.sendRedirect(url);
            }
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
