/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Models.Invoice;
import dal.InvoiceDBContext;
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
 * @author Administrator
 */
public class IncomeController extends HttpServlet {
   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        Date d1 = null;
        Date d2 = null;
        int pageIndex;
        try {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            pageIndex = 1;
        }
        try {
            d1 = Date.valueOf(request.getParameter("startdate"));
            d2 = Date.valueOf(request.getParameter("enddate"));
        } catch (IllegalArgumentException ie) {
            String errormessage = "Invalid range of date";
            request.setAttribute("error", errormessage);
            request.getRequestDispatcher("view/incometime.jsp").forward(request, response);
        }
        if (d1.after(d2)) {
            String errormessage = "Invalid range of date";
            request.setAttribute("error", errormessage);
            request.getRequestDispatcher("view/incometime.jsp").forward(request, response);
        }
        InvoiceDBContext idb = new InvoiceDBContext();
        ArrayList<Invoice> invoices = idb.getInvoicesWithDate(pageIndex, d1, d2);
        int total = idb.getTotal(d1, d2);
        request.setAttribute("invoices", invoices);
        int count = idb.count("WHERE datecreated BETWEEN '" + d1 + "' AND '" + d2 + "'");
        int totalpage = (count % 5 == 0) ? (count / 5) : (count / 5) + 1;
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageIndex);
        request.setAttribute("total", total);
        request.setAttribute("startdate", d1);
        request.setAttribute("enddate", d2);
        request.getRequestDispatcher("view/income.jsp").forward(request, response);
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