/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Models.Invoice;
import dal.InvoiceDBContext;
import dal.RoomDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class InvoiceController extends HttpServlet {
   
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
        int pageIndex;
        try{
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }catch(NumberFormatException e){
            pageIndex = 1;
        }
        InvoiceDBContext idb = new InvoiceDBContext();
        RoomDBContext rdb = new RoomDBContext();
        ArrayList<Invoice> invoices = idb.getInvoices(pageIndex);
        ArrayList<String> roomname = new ArrayList<>();
        for(Invoice i: invoices){
            try{
            roomname.add(rdb.getRoom(i.getRid()).getName());
            }catch(NullPointerException e){
                roomname.add("N/A");
            }
        }
        int count = idb.count("");
        int totalpage = (count%10==0)?(count/10):(count/10)+1;
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageIndex);
        request.setAttribute("roomnames", roomname);
        request.setAttribute("invoices", invoices);
        request.getRequestDispatcher("view/invoice.jsp").forward(request, response);
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
