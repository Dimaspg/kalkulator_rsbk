/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kalkulator.ejb.KalkulatorBeanLocal;

/**
 *
 * @author rafli
 */
@WebServlet(name = "kalkulator", urlPatterns = {"/kalkulator"})
public class kalkulator extends HttpServlet {

    KalkulatorBeanLocal kalkulatorBean = lookupKalkulatorBeanLocal();
    
    String [] param         = {"satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan", "nol", "ratus", "koma"};
    String [] paramVal      = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "00", "."};
    String valInput         = null;
    String lastOperation    = null;
    String operation        = null;
    String history          = null;

    double results          = 0;
    double value            = 0;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        response.setContentType("text/html;charset=UTF-8");
        try {
         boolean Calculate = true;

        for (int i = 0; i < this.param.length; i++) {
            if(request.getParameter(this.param[i]) != null){
                this.valInput = kalkulatorBean.isNumber(this.valInput, this.paramVal[i]);
                Calculate = false;
            }
        }

        if(request.getParameter("delete") != null){
            valInput = kalkulatorBean.delete(valInput);
            Calculate = false;
        }
        if(request.getParameter("reset") != null){
            results = kalkulatorBean.reset();
            history = kalkulatorBean.History();
            request.setAttribute("operation", "");
            lastOperation = null;
            operation = "";
            Calculate = false;
            valInput = null;
        }
        if(request.getParameter("equ") != null){
            value = Double.parseDouble(valInput);
            results =  kalkulatorBean.equal(results, value, lastOperation);
            history = kalkulatorBean.History(history, lastOperation, valInput, Double.toString(results));
            operation = "=";
            lastOperation = null;
            Calculate = false; 
            valInput = null;
        }

        if(Calculate){
            if (valInput != null) {
                value = Double.parseDouble(valInput);

                if(null == lastOperation) {
                    results = kalkulatorBean.add(0, value);
                    history = kalkulatorBean.History(valInput); 
                } else {
                    switch (lastOperation) {
                        case "add":
                            results = kalkulatorBean.add(results, value);
                            break;
                        case "sub":
                            results = kalkulatorBean.subtract(results, value);
                            break;
                        case "div":
                            results = kalkulatorBean.divide(results, value);
                            break;
                        case "mul":
                            results = kalkulatorBean.multiply(results, value);
                            break;
                    }
                    history = kalkulatorBean.History(history, lastOperation, valInput);
                }
                valInput = null;
            }
        }
        if(request.getParameter("add") != null){
            lastOperation = "add";
            operation = "+";
        }
        if(request.getParameter("sub") != null){
            lastOperation = "sub";
            operation = "-";
        }
        if(request.getParameter("div") != null){
            lastOperation = "div";
            operation = "/";
        }
        if(request.getParameter("mul") != null){
            lastOperation = "mul";
            operation = "x";
        }

        request.setAttribute("history", history);
        request.setAttribute("operation", operation);
        request.setAttribute("results", results);
        request.setAttribute("value", valInput);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/kalkulator.jsp");
            rd.forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/kalkulator.jsp");
            rd.forward(request, response);
        } finally {
            PrintWriter out = response.getWriter();
            System.out.println("out");
            out.close();
        }
 
//        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Calculator</title>");
//            out.println("<link rel=\"stylesheet\" href=\"style.css\">");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<div class=\"container\">");
//            out.println("<center><h1 style=\"color: white;\">Calculator</h3></center> <br>");
//            out.println("<br>");
//            out.println("<p style=\"color:white;\">${history}</p>");
//            out.println("<h1 style=\"color:white;\">${results}</h1>");
//            out.println("<form class=\"calculator\" name=\"kalkulator\">");
//            out.println("<input class=\"value hasil\" value=\"${value}\" type=\"text\" name=\"nilai\" readonly=\"\"/>");
//            out.println("<span name=\"reset\" class=\"num clear operation\" onclick=\"kalkulator.nilai.value = ''\">C</span>");
//            out.println("<span name=\"div\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '/'\">/</span>");
//            out.println("<span name=\"mul\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '*'\">*</span>");
//            out.println("<span name=\"tujuh\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '7'\">7</span>");
//            out.println("<span name=\"delapan\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '8'\">8</span>");
//            out.println("<span name=\"sembilan\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '9'\">9</span>");
//            out.println("<span name=\"sub\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '-'\">-</span>");
//            out.println("<span name=\"empat\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '4'\">4</span>");
//            out.println("<span name=\"lima\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '5'\">5</span>");
//            out.println("<span name=\"enam\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '6'\">6</span>");
//            out.println("<span name=\"add\" class=\"num plus operation\" onclick=\"kalkulator.nilai.value += '+'\">+</span>");
//            out.println("<span name=\"tiga\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '3'\">3</span>");
//            out.println("<span name=\"dua\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '2'\">2</span>");
//            out.println("<span name=\"satu\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '1'\">1</span>");
//            out.println("<span name=\"nol\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '0'\">0</span>");
//            out.println("<span name=\"ratus\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '00'\">00</span>");
//            out.println("<span name=\"koma\" class=\"num operation\" onclick=\"kalkulator.nilai.value += '.'\">.</span>");
//            out.println("<span name=\"equ\" class=\"num equal operation\" onclick=\"kalkulator.nilai.value = eval(kalkulator.nilai.value)\">=</span>");
//            out.println("</div>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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

    private KalkulatorBeanLocal lookupKalkulatorBeanLocal() {
        try {
            Context c = new InitialContext();
            return (KalkulatorBeanLocal) c.lookup("java:global/kalkulator/kalkulator-ejb/KalkulatorBean!kalkulator.ejb.KalkulatorBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
