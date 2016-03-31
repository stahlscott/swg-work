/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.jspsitelab;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
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
@WebServlet(name = "LuckySevens", urlPatterns = {"/LuckySevens"})
public class LuckySevens extends HttpServlet {

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
        RequestDispatcher rd = request.getRequestDispatcher("luckySevens.jsp");
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
        RequestDispatcher rd = request.getRequestDispatcher("luckySevens.jsp");
        response.setContentType("text/html;charset=UTF-8");
        Random generator = new Random();

        int die1, die2, sum, startingBet, rolls, maxMoney, rollsAtMax, pot;

        try {
            startingBet = Integer.parseInt(request.getParameter("dollars"));
            pot = startingBet;
            maxMoney = pot;
            rollsAtMax = 0;
            rolls = 0;

            while (pot > 0) {
                rolls++;

                die1 = generator.nextInt(6) + 1;
                die2 = generator.nextInt(6) + 1;
                sum = die1 + die2;

                if (sum == 7) {
                    pot += 4;
                } else {
                    pot -= 1;
                }

                if (pot > maxMoney) {
                    maxMoney = pot;
                    rollsAtMax = rolls;
                }
            }

            request.setAttribute("startingBet", startingBet);
            request.setAttribute("rolls", rolls);
            request.setAttribute("rollsAtMax", rollsAtMax);
            request.setAttribute("maxMoney", maxMoney);
        } catch (NumberFormatException ex) {
            request.setAttribute("errMessage", "Input error. Please try again.");
        }

        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Lucky Sevens";
    }// </editor-fold>

}
