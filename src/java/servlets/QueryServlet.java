/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Naser
 */
public class QueryServlet extends HttpServlet {

    JSONObject json1 = new JSONObject();
    URL url;
    Scanner scan;
    String line;
    String[] userDe;
    boolean isFound = false;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String abk = request.getParameter("name");
        userDe = querySearch(abk);
        if (isFound) {
            json1.put("name", userDe[1]);
            json1.put("kurzname", userDe[2]);
            json1.put("typ", userDe[3]);
            isFound = false;
        } else {
            response.sendRedirect("index.html");
        }
        request.getSession().setAttribute("jsonobject", json1);
        RequestDispatcher rd = request.getRequestDispatcher("welcomeServlet");
        rd.forward(request, response);
        destroy();

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

    public String[] querySearch(String Pabk) {

        String[] details = null;
        try {

            url = new URL("https://download-data.deutschebahn.com/static/datasets/betriebsstellen/DBNetz-Betriebsstellenverzeichnis-Stand2018-04.csv");
            scan = new Scanner(url.openStream());
            while ((line = scan.nextLine()) != null && !isFound) {
                details = line.split(";");
                if (details[0].equalsIgnoreCase(Pabk)) {
                    isFound = true;
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return details;
    }
}
