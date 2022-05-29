/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Naser
 */
public class WelcomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        JSONObject userDetails = (JSONObject) request.getAttribute("jsonobject");
        if (userDetails != null) {
            try (PrintWriter out = response.getWriter()) {

                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet welcomeServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>user details {</h2>");
                out.println("<h2><span><span> Name :" + userDetails.get("name") + "</h2>");
                out.println("<h2><span><span> Name :" + userDetails.get("kurzname") + "</h2>");
                out.println("<h2><span><span> Name :" + userDetails.get("typ") + "</h2>");
                out.println("<h2> } </h2>");
                out.println("</body>");
                out.println("</html>");
            }

        } else {
            response.sendRedirect("index.html");
            destroy();
        }
        request.removeAttribute("jsonobject");
        resetCookies(request);
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

    public void resetCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cook : cookies) {
            cook.setMaxAge(0);
        }
    }
}
