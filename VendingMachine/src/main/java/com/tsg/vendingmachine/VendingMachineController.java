/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachine;

import com.tsg.addressbook.ui.ConsoleIO;
import com.tsg.vendingmachine.dao.VendingMachineDAO;
import com.tsg.vendingmachine.dto.Beer;
import com.tsg.vendingmachine.dto.Change;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class VendingMachineController {

    private VendingMachineDAO vendingMachineDAO = new VendingMachineDAO();
    private ConsoleIO con = new ConsoleIO();
    private Change change = new Change();
    private final String ADMIN_PW = "beermejesus";

    /**
     * Main run method. Displays list of beers, presents menu of options, quits when user selects quit.
     */
    public void run() {
        try {
            vendingMachineDAO.loadBeerList();

            int userChoice = 0;

            while (userChoice != 3) {
                listBeer();
                printMenu();
                userChoice = con.getInteger("Please choose a menu option:", 1, 4);

                switch (userChoice) {
                    case 1:
                        vendBeer();
                        break;
                    case 2:
                        addMoney();
                        break;
                    case 4:
                        addBeerQuantity();
                        break;
                    default:
                        break;
                }
            }

            vendingMachineDAO.writeBeerList();
            con.print("Thank you for your patronage at BeerVendBot 1.0!!! :-D");
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
            con.print("ERROR. BEERVENDBOT IS BORKEN. PLEAE CONTACT YOUR LOCAL REPRESENTATIVE. BLEEP BLOOP.");
        }

    }

    /**
     * Prints menu of options for user to choose from.
     */
    private void printMenu() {
        con.print("\nYou currently have: $" + change.getTotal() + "\n");
        con.print("1. Vend Beer");
        con.print("2. Add Money");
        con.print("3. Quit");
        con.print("");
    }

    /**
     * Prints list of beers in machine with name, price, qty, and item position.
     */
    private void listBeer() {
        ArrayList<Beer> beers = vendingMachineDAO.getAllBeers();

        for (int i = 0; i < beers.size(); i += 3) {
            Beer beer1 = beers.get(i);
            Beer beer2 = beers.get(i + 1);
            Beer beer3 = beers.get(i + 2);

            con.print(beer1.getName() + "\t\t" + beer2.getName() + "\t\t" + beer3.getName());
            con.print("$" + beer1.getCost() + "\t Qty " + beer1.getQuantity()
                    + "\t\t$" + beer2.getCost() + "\t Qty " + beer2.getQuantity()
                    + "\t\t$" + beer3.getCost() + "\t Qty " + beer3.getQuantity());
            con.print("\t[" + beer1.getItemPosition() + "]\t\t\t[" + beer2.getItemPosition() + "]\t\t\t[" + beer3.getItemPosition() + "]");
            con.print("");
            con.print("");
        }

    }

    /**
     * Determines user's desired Beer object by querying for item position.
     *
     * @return Beer object selected by user or null if incorrect position selected
     */
    private Beer getBeerFromUser() {
        ArrayList<Beer> beers = vendingMachineDAO.getAllBeers();

        String userPosition = con.getString("Please enter item position of desired beer:");

        Beer userBeer = null;

        for (Beer beer : beers) {
            if (beer.getItemPosition().equalsIgnoreCase(userPosition)) {
                userBeer = beer;
            }
        }

        return userBeer;
    }

    /**
     * Calls getBeerFromUser, then checks if position is correct, if enough stock exists, if there is enough money in
     * the machine. If it meets these criteria, calls sellBeer in DAO and congratulates user on their purchase.
     *
     */
    private void vendBeer() {
        Beer userBeer = getBeerFromUser();

        if (userBeer != null) {
            if (userBeer.getQuantity() > 0) {
                if (change.getTotal() >= userBeer.getCost()) {
                    con.print("Enjoy your nice, cold " + userBeer.getName() + "!!\n");
                    vendingMachineDAO.sellBeer(userBeer.getItemPosition());
                    dispenseChange(userBeer.getCost());
                } else {
                    con.print("Sorry, you don't have enough money in the machine.\n");
                }
            } else {
                con.print("Sorry, that is temporarily out of stock.\n");
            }
        } else {
            con.print("Invalid selection.\n");
        }
    }

    /**
     * Allows user to add desired amount of money to the machine, between $0 and $20 at a time.
     */
    private void addMoney() {
        con.print("You currently have: $" + change.getTotal());
        double userMoney = con.getDouble("How much money to insert?", 0, 20);
        change.addToTotal(userMoney);
    }

    /**
     * Allows user with admin password to add quantity to a beer in a given slot.
     */
    private void addBeerQuantity() {
        if (con.getString("Please enter admin password: ").equals(ADMIN_PW)) {
            Beer userBeer = getBeerFromUser();
            if (userBeer != null) {
                int addQty = con.getInteger("How many would you like to add?", 0, 20);
                vendingMachineDAO.addBeerQty(userBeer, addQty);
            } else {
                con.print("Invalid selection.\n");
            }
        } else {
            con.print("Go away.\n");
        }
    }

    /**
     * Given the cost of a given beer, returns the change due to the user by calling helper functions in Change class
     * and sets change total to zero.
     *
     * @param beerCost double
     */
    private void dispenseChange(double beerCost) {
        change.removeFromTotal(beerCost);
        if (change.getTotal() != 0) {
            con.print("Your change is " + change.getTotal() + ". You receive : ");
            if (change.getQuarters() > 0) {
                con.print(change.getQuarters() + " quarter(s)");
            }
            if (change.getDimes() > 0) {
                con.print(change.getDimes() + " dime(s)");
            }
            if (change.getNickels() > 0) {
                con.print(change.getNickels() + " nickel(s)");
            }
            if (change.getPennies() > 0) {
                con.print(change.getPennies() + " pennie(s)");
            }
            con.print("");
            change.removeFromTotal(change.getTotal());
        }
    }
}
