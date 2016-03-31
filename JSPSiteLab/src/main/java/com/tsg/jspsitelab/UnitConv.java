/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.jspsitelab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
@WebServlet(name = "UnitConv", urlPatterns = {"/UnitConv"})
public class UnitConv extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("unitConv.jsp");
        rd.forward(request, response);
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
        try {
            double amount = Double.parseDouble(request.getParameter("amount"));
            String type = request.getParameter("dd1");
            String startAs = request.getParameter("dd2");
            String convTo = request.getParameter("dd3");

            double convertedAmount = 0;

            switch (type) {
                case "Temperature":
                    switch (startAs) {
                        case "Celsius":
                            switch (convTo) {
                                case "Fahrenheit":
                                    convertedAmount = amount * 1.8 + 32;
                                    break;
                                case "Kelvin":
                                    convertedAmount = amount + 273.15;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                        case "Fahrenheit":
                            switch (convTo) {
                                case "Celsius":
                                    convertedAmount = (amount - 32) * 5 / 9;
                                    break;
                                case "Kelvin":
                                    convertedAmount = ((amount - 32) * 5 / 9) + 273.15;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                        case "Kelvin":
                            switch (convTo) {
                                case "Celsius":
                                    convertedAmount = amount - 273.15;
                                    break;
                                case "Fahrenheit":
                                    convertedAmount = (amount - 273.15) * 1.8 + 32;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                    }
                    break;
                case "Length":
                    switch (startAs) {
                        case "Inch":
                            switch (convTo) {
                                case "Centimeter":
                                    convertedAmount = amount * 2.54;
                                    break;
                                case "Foot":
                                    convertedAmount = amount * .08333333;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                        case "Centimeter":
                            switch (convTo) {
                                case "Inch":
                                    convertedAmount = amount * 0.3937008;
                                    break;
                                case "Foot":
                                    convertedAmount = amount * 0.0328084;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                        case "Foot":
                            switch (convTo) {
                                case "Inch":
                                    convertedAmount = amount * 12;
                                    break;
                                case "Centimeter":
                                    convertedAmount = amount * 30.48;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                    }
                    break;
                case "Mass":
                    switch (startAs) {
                        case "Pound":
                            switch (convTo) {
                                case "Kilogram":
                                    convertedAmount = amount * 0.453592;
                                    break;
                                case "Ounce":
                                    convertedAmount = amount * 16;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                        case "Kilogram":
                            switch (convTo) {
                                case "Pound":
                                    convertedAmount = amount * 2.20462;
                                    break;
                                case "Ounce":
                                    convertedAmount = amount * 35.2739;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                        case "Ounce":
                            switch (convTo) {
                                case "Pound":
                                    convertedAmount = amount * 0.0625;
                                    break;
                                case "Kilogram":
                                    convertedAmount = amount * 0.0283495;
                                    break;
                                default:
                                    convertedAmount = amount;
                                    break;
                            }
                            break;
                    }
                    break;
                default:
                    convertedAmount = 0;
                    break;
            }

            request.setAttribute("amount", String.format("%.2f", amount));
            request.setAttribute("convertedAmount", String.format("%.2f", convertedAmount));
            request.setAttribute("type", type);
            request.setAttribute("unit1type", startAs);
            request.setAttribute("unit2type", convTo);
        } catch (NumberFormatException ex) {
            request.setAttribute("errMessage", "Input error. Please try again.");
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Unit Convertor";
    }// </editor-fold>

}
