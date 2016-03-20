/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmastery;

import com.tsg.flooringmastery.dao.*;
import com.tsg.flooringmastery.dto.*;
import com.tsg.flooringmastery.ui.ConsoleIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class FlooringController {

    private ConsoleIO con;
    private OrderDAO orderDAO;
    private TaxDAO taxDAO;
    private ProductDAO productDAO;
    private OrderMaker orderMaker;
    private final String CONFIG_FILE = "config.ini";
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("MMddyyyy");
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    String todayDate;

    //TODO delete file if empty
    //TODO ability to parse commas inline
    //TODO soft delete (Orders_deleted.txt) w/ date storage
    
    /**
     * Default constructor; when called, initializes DAOs and OrderMaker, sets default date to today.
     *
     */
    public FlooringController() {
        this.con = new ConsoleIO();
        this.productDAO = new ProductDAO();
        this.taxDAO = new TaxDAO();
        Calendar cal = Calendar.getInstance();
        this.todayDate = DATE_FORMAT.format(cal.getTime());
        try {
            this.orderDAO = new OrderDAO(this.todayDate, false);
            orderDAO.loadConfigFile(CONFIG_FILE);
            con.print("config.ini successfully loaded");
            orderMaker = new OrderMaker(taxDAO, productDAO);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringController.class.getName()).log(Level.SEVERE, null, ex);
            con.print("CRITICAL ERROR: config.ini not found. Please exit the program.");
        }
    }

    /**
     * Main run function switching user menu choice to various options.
     */
    public void run() {
        try {
            orderDAO.loadOrderFile(todayDate);
            con.print("Orders_" + todayDate + ".txt successfully loaded.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringController.class.getName()).log(Level.SEVERE, null, ex);
            con.print("Orders_" + todayDate + ".txt not found; empty order file started.");
        }

        try {
            taxDAO.loadTaxFile();
            productDAO.loadProductFile();

            int userChoice = 0;
            while (userChoice != 6) {
                printMenu();
                userChoice = con.getInteger("Please choose an option: ", 1, 6);

                switch (userChoice) {
                    case 1:
                        displayAllOrdersForDate();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        writeFiles();
                        break;
                    default:
                        break;
                }
            }
            writeFiles();
            con.print("Thank you for running SWC Corp's Flooring Program!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringController.class.getName()).log(Level.SEVERE, null, ex);
            con.print("CRITICAL ERROR: Files not loaded correctly. Contact IT.");
        }
    }

    /**
     * Prints main menu.
     */
    public void printMenu() {
        String testProd = (orderDAO.isTestMode()) ? "[TEST]" : "[PROD]";
        con.print("\n*************************************************");
        con.print("*\tSWC Corp. Flooring Retail Program\t*");
        con.print("*\t\t     " + testProd + "\t\t\t*");
        con.print("*\t\tWorking in date: \t\t*");
        con.print("*\t\t    " + orderDAO.getWorkingDate() + "\t\t\t*");
        con.print("*\t\t\t\t\t\t*");
        con.print("* 1. Display Orders\t\t\t\t*");
        con.print("* 2. Add an Order\t\t\t\t*");
        con.print("* 3. Edit an Order\t\t\t\t*");
        con.print("* 4. Remove an Order\t\t\t\t*");
        con.print("* 5. Save Current Work\t\t\t\t*");
        con.print("* 6. Quit\t\t\t\t\t*");
        con.print("*\t\t\t\t\t\t*");
        con.print("*************************************************\n");
    }

    /**
     * Gets date from user, displays a short list of orders on that date and asks for a selection to display in full.
     */
    public void displayAllOrdersForDate() {
        fancyPrint("Display All Orders");
        String date = con.getString("Please enter date to search (MMDDYYYY) \n\tor Enter for current (Current: " + orderDAO.getWorkingDate() + "): ");
        if (date.equals("")) {
            date = orderDAO.getWorkingDate();
        }
        if (isValidDate(date)) {
            ArrayList<Order> orders = orderDAO.getAllOrdersByDate(date);
            if (orders != null) {
                Order order = getOrderFromUser(date);
                displayOrder(order);
            } else {
                con.print("No orders found for that date.");
            }
        } else {
            con.print("That date is invalid.");
        }
    }

    /**
     * Gets customer name, area, taxable state, product type from user, calls OrderMaker to generate order, then adds it
     * to order file.
     */
    public void addOrder() {
        fancyPrint("    Add an Order");
        Double taxRate;
        Product product;
        String state;
        String productType;
        String date = con.getString("Please enter date of order to add (MMDDYYYY) \n\tor Enter for current (Current: " + orderDAO.getWorkingDate() + "): ");
        if (date.equals("")) {
            date = orderDAO.getWorkingDate();
        }
        if (isValidDate(date)) {
            try {
                orderDAO.changeWorkingDate(date);

                String custName = con.getString("Please enter customer name: ");
                Double area = con.getDouble("Please enter Square foot area: ", 0, Double.MAX_VALUE);
                state = getStateNameFromUser("Please enter State abbreviation (ie. OH for Ohio): ", false);

                productType = getProductTypeFromUser("Please enter product type: ", false);

                Order order = orderMaker.createOrder(custName, state, productType, area);

                displayOrder(order);
                String confirm = con.getString("Are you sure you want to add order?(Y/N) ");
                if (confirm.equalsIgnoreCase("Y")) {
                    orderDAO.addOrder(order);
                    con.print("Order # " + order.getOrderNumber() + " successfully added.");

                } else {
                    con.print("Order cancelled. ");
                }

            } catch (IOException ex) {
                Logger.getLogger(FlooringController.class.getName()).log(Level.SEVERE, null, ex);
                con.print("File error. Please exit program.");
            }

        } else {
            con.print("Date not valid.");
        }
    }

    /**
     * Edit an existing order. Allows user to pull up a previous order and edit the following information: order date,
     * name, area, product type.
     */
    public void editOrder() {
        fancyPrint("    Edit an Order");
        String date = con.getString("Please enter date of order to edit (MMDDYYYY) \n\tor Enter for current (Current: " + orderDAO.getWorkingDate() + "): ");
        if (date.equals("")) {
            date = orderDAO.getWorkingDate();
        }
        if (isValidDate(date)) {
            try {
                orderDAO.changeWorkingDate(date);
                Order order = getOrderFromUser(date);
                if (order != null) {
                    displayOrder(order);

                    //date check
                    String newDate = con.getString("\nDate (Current: " + orderDAO.getWorkingDate() + "): ");

                    while (!isValidDate(newDate) && !newDate.equals("")) {
                        con.print("Date not valid.");
                        newDate = con.getString("Date (MMDDYYYY) (Current: " + orderDAO.getWorkingDate() + "): ");
                        if (date.equals("")) {
                            date = orderDAO.getWorkingDate();
                            break;
                        }
                    }

                    if (!newDate.equals("")) {
                        orderDAO.changeOrderDate(order, newDate);
                    }

                    String custName = con.getString("Please enter customer name (Current: " + order.getCustomerName() + "): ");
                    if (custName.equals("")) {
                        custName = order.getCustomerName();
                    }
                    String areaString = con.getString("Please enter square foot area (Current: " + order.getArea() + "): ");
                    Double area;

                    try {
                        area = Double.parseDouble(areaString);
                    } catch (NumberFormatException ex) {
                        area = order.getArea();
                    }

                    if (areaString.equals("")) {
                        area = order.getArea();

                    }
                    String state = getStateNameFromUser("Please enter State abbreviation (Current: " + order.getState() + "): ", true);
                    if (state.equals("")) {
                        state = order.getState();

                    }
                    String productType = getProductTypeFromUser("Please enter product type (Current: " + order.getProductType() + "): ", true);
                    if (productType.equals("")) {
                        productType = order.getProductType();
                    }
                    order = orderMaker.modifyOrder(order, custName, state, productType, area);

                    displayOrder(order);
                    con.print("Order Successfully changed. ");

                } else {
                    con.print("Order not found.");
                }
            } catch (IOException ex) {
                Logger.getLogger(FlooringController.class.getName()).log(Level.SEVERE, null, ex);
                con.print("File error. Please exit program.");
            }

        } else {
            con.print("Date not valid.");
        }

    }

    /**
     * Allows user to remove an order by first asking for date, showing a list of all available options, and then
     * confirming remove.
     */
    public void removeOrder() {
        fancyPrint("    Remove an Order");
        String date = con.getString("Please enter date of order to remove (MMDDYYYY) \n\tor Enter for current (Current: " + orderDAO.getWorkingDate() + "): ");
        if (date.equals("")) {
            date = orderDAO.getWorkingDate();
        }
        if (isValidDate(date)) {

            Order order = getOrderFromUser(date);

            displayOrder(order);
            String confirm = con.getString("Are you sure you want to delete order?(Y/N) ");
            if (confirm.equalsIgnoreCase("Y")) {
                orderDAO.deleteOrder(order);
                con.print("Order # " + order.getOrderNumber() + " successfully deleted.");

            } else {
                con.print("Order delete cancelled. ");
            }
        } else {
            con.print("Date not valid.");
        }
    }

    /**
     * Print function, showing all information for given Order.
     *
     * @param order
     */
    public void displayOrder(Order order) {
        con.print("---------------------------------------");
        if (order.getOrderNumber() == null) {
            con.print("Quote:\t\t\tDate: " + orderDAO.getWorkingDate());
        } else {
            con.print("Order Number: " + order.getOrderNumber() + "\t\tDate: " + orderDAO.getWorkingDate());
        }
        con.print("\n\tCustomer Name: " + order.getCustomerName());
        con.print("\n\tState: " + order.getState() + "\tTax Rate: " + order.getTaxRate() + "%");
        con.print("\tProduct Type: " + order.getProductType());
        con.print("\tCost per sq. ft.:\t$" + DECIMAL_FORMAT.format(order.getCostPerSquareFoot()));
        con.print("\tLabor cost per sq. ft:\t$" + DECIMAL_FORMAT.format(order.getLabourCostPerSquareFoot()));
        con.print("\n\tArea (sq. ft.): " + order.getArea());
        con.print("\n\tTotal Material Cost: \t$" + DECIMAL_FORMAT.format(order.getMaterialCost()));
        con.print("\tTotal Labor Cost: \t$" + DECIMAL_FORMAT.format(order.getLabourCost()));
        con.print("\tSales Tax: \t\t$" + DECIMAL_FORMAT.format(order.getTax()));
        con.print("\n\t\tTotal: \t\t$" + DECIMAL_FORMAT.format(order.getTotal()));
        con.print("---------------------------------------");
    }

    public void fancyPrint(String str) {
        con.print("");
        con.print("*************************************************");
        con.print("*\t\t\t\t\t\t*");
        con.print("*\t\t" + str + "\t\t*");
        con.print("*\t\t\t\t\t\t*");
        con.print("*************************************************");
        con.print("");
    }

    /**
     * Prints a list of all ProductTypes loaded in productDAO.
     */
    public void displayAllItemTypes() {
        ArrayList<Product> prodTypes = productDAO.getProducts();
        for (Product prodType : prodTypes) {
            con.print(prodType.getProductType());
        }
    }

    /**
     * Prints a list of all States loaded in taxDAO.
     */
    public void displayAllTaxStates() {
        ArrayList<Tax> taxStates = taxDAO.getTaxes();
        for (Tax taxState : taxStates) {
            con.print(taxState.getState());
        }
    }

    /**
     * Presents a list of orders to the user on given date, asking them to choose one. If only one exists, return that
     * one. If none exist, return null.
     *
     * @param date
     * @return Order selected by user or null if no order found
     */
    public Order getOrderFromUser(String date) {
        ArrayList<Order> orders = orderDAO.getAllOrdersByDate(date);
        if (orders.size() == 1) {
            return orders.get(0);
        } else if (orders.size() > 1) {
            con.print("More than one entry found. ");
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                con.print((i + 1) + " : " + "Order #: " + order.getOrderNumber() + "\t Customer Name: " + order.getCustomerName() + " \t Order Total: $" + DECIMAL_FORMAT.format(order.getTotal()));
            }
            int userChoice = con.getInteger("\nPlease select menu option from above (NOT order number): ", 1, orders.size());
            return orders.get(userChoice - 1);
        }
        return null;
    }

    /**
     * Gets product type from user, forces a valid answer matching product types loaded into ProductDAO
     *
     * @param prompt String request to display to user
     * @param allowBlank if a blank response is allowed
     * @return productType as a String
     */
    public String getProductTypeFromUser(String prompt, boolean allowBlank) {
        String productType = con.getString(prompt);
        if (allowBlank) {
            if (productType.equals("")) {
                return productType;
            }
        }
        Product product = productDAO.getProductByName(productType);
        while (product == null) {
            con.print("Product not found. Available items: ");
            displayAllItemTypes();
            productType = con.getString(prompt);
            if (allowBlank) {
                if (productType.equals("")) {
                    return productType;
                }
            }
            product = productDAO.getProductByName(productType);
        }
        productType = product.getProductType();
        return productType;
    }

    /**
     * Gets state name from user, forces a valid answer matching States loaded into TaxDAO
     *
     * @param prompt String request to display to user
     * @param allowBlank if a blank response is allowed
     * @return stateName as a String
     */
    public String getStateNameFromUser(String prompt, boolean allowBlank) {
        String state = con.getString(prompt).toUpperCase();
        if (allowBlank) {
            if (state.equals("")) {
                return state;
            }
        }
        Double taxRate = taxDAO.getTaxRateByState(state);

        while (taxRate == null) {
            con.print("State not found. Available states: ");
            displayAllTaxStates();
            state = con.getString(prompt).toUpperCase();
            if (allowBlank) {
                if (state.equals("")) {
                    return state;
                }
            }
            taxRate = taxDAO.getTaxRateByState(state);
        }
        return state;
    }

    /**
     * Checks that String date matches MMddYYYY format
     *
     * @param date
     * @return true if valid, false if not
     */
    public boolean isValidDate(String date) {
        try {
            DATE_FORMAT.setLenient(false);
            DATE_FORMAT.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * Writes orderDAO and config files.
     */
    public void writeFiles() {
        try {
            orderDAO.writeOrderFile();
            orderDAO.writeConfigFile(CONFIG_FILE);
            con.print("Files saved successfully.");
        } catch (IOException ex) {
            Logger.getLogger(FlooringController.class
                    .getName()).log(Level.SEVERE, null, ex);
            con.print("ERROR: Unable to write files.");
        }
    }

}
