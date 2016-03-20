/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmastery.dao;

import com.tsg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class OrderDAO {

    //TODO : how would we refactor to keep all dates/orders in memory then only write when save is called

    private final String ORDER_PREFIX = "Orders_";
    private final String DELIMITER = ",";
    private boolean testMode;
    private HashMap<Integer, Order> orders = new HashMap<>();
    private Integer currentOrderNumber;
    private String workingDate;

    /**
     * Constructor sets date equal to date sent in as parameter.
     *
     * @param date MMddYYYY
     */
    public OrderDAO(String date) {
        this.workingDate = date;
    }

    /**
     * JUNIT TESTING ONLY. Sets date equal to String sent in from JUnit, true if testMode should be true, false if
     * testMode should be false. Sets currentIDindex to 1.
     *
     * @param date MMddYYYY
     * @param junitTestActive set to true if you are doing junit tests
     */
    public OrderDAO(String date, boolean junitTestActive) {
        this.testMode = junitTestActive;
        this.workingDate = date;
        this.currentOrderNumber = 1;
    }

    /**
     *
     * @return if test mode is active or not
     */
    public boolean isTestMode() {
        return testMode;
    }

    /**
     *
     * @return current working date
     */
    public String getWorkingDate() {
        return workingDate;
    }

    /**
     * Adds given order to active hashmap.
     *
     * @param order
     */
    public void addOrder(Order order) {
        order.setOrderNumber(currentOrderNumber);
        orders.put(currentOrderNumber, order);
        currentOrderNumber++;
    }

    /**
     * Deletes given order from active hashmap.
     *
     * @param order
     */
    public void deleteOrder(Order order) {
        orders.remove(order.getOrderNumber());
    }

    /**
     * Returns order by orderNumber.
     *
     * @param orderNumber
     * @return
     */
    public Order getOrder(Integer orderNumber) {
        return orders.get(orderNumber);
    }

    public void changeOrderDate(Order order, String date) throws IOException {
        deleteOrder(order);
        changeWorkingDate(date);
        orders.put(order.getOrderNumber(), order);
    }

    /**
     * Calls changeWorkingDate to given date, returns all orders for that date
     *
     * @param date
     * @return returns all orders for given date or null if exception thrown
     */
    public ArrayList<Order> getAllOrdersByDate(String date) {

        try {
            changeWorkingDate(date);
            if (orders.size() > 0) {
                Collection<Order> orderVals = orders.values();
                ArrayList<Order> orderArrList = new ArrayList<>();
                for (Order orderVal : orderVals) {
                    orderArrList.add(orderVal);
                }
                return orderArrList;
            }
        } catch (IOException ex) {
        }
        return null;
    }

    /**
     * Changes working date. First saves to current date Order file .txt, then tries to load given date's order file. If
     * no order file exists, it creates an empty hashmap.
     *
     * @param date
     * @throws IOException
     */
    public void changeWorkingDate(String date) throws IOException {
        if (date != this.workingDate) {
            writeOrderFile();
            this.workingDate = date;
            try {
                loadOrderFile(this.workingDate);
            } catch (FileNotFoundException ex) {
                orders = new HashMap<>();
            }
        }
    }

    /**
     * Writes order map to file ORDER_PREFIX + workingDate + .txt
     *
     * @throws IOException
     */
    public void writeOrderFile() throws IOException {
        if (!this.testMode) {
            PrintWriter out = new PrintWriter(new FileWriter(ORDER_PREFIX + this.workingDate + ".txt"));
            ArrayList<Order> orderVals = getAllOrdersByDate(this.workingDate);
            if (orderVals != null) {
                for (Order orderVal : orderVals) {
                    out.println(orderVal.getOrderNumber() + DELIMITER + orderVal.getCustomerName() + DELIMITER
                            + orderVal.getState() + DELIMITER + orderVal.getTaxRate() + DELIMITER + orderVal.getProductType()
                            + DELIMITER + orderVal.getArea() + DELIMITER + orderVal.getCostPerSquareFoot() + DELIMITER
                            + orderVal.getLabourCostPerSquareFoot() + DELIMITER + orderVal.getMaterialCost() + DELIMITER
                            + orderVal.getLabourCost() + DELIMITER + orderVal.getTax() + DELIMITER + orderVal.getTotal());
                    out.flush();
                }
                out.close();
            }
        }
    }

    /**
     * Loads order file for given date
     *
     * @param date
     * @throws FileNotFoundException
     */
    public void loadOrderFile(String date) throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(ORDER_PREFIX + date + ".txt")));
        orders = new HashMap<>();
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();

            String[] tokens = currentLine.split(DELIMITER);

            Order order = new Order();

            Integer existingOrderId = Integer.parseInt(tokens[0]);

            if (existingOrderId >= this.currentOrderNumber) {
                this.currentOrderNumber = existingOrderId + 1;
            }

            order.setOrderNumber(existingOrderId);
            order.setCustomerName(tokens[1]);
            order.setState(tokens[2]);
            order.setTaxRate(Double.parseDouble(tokens[3]));
            order.setProductType(tokens[4]);
            order.setArea(Double.parseDouble(tokens[5]));
            order.setCostPerSquareFoot(Double.parseDouble(tokens[6]));
            order.setLabourCostPerSquareFoot(Double.parseDouble(tokens[7]));
            order.setMaterialCost(Double.parseDouble(tokens[8]));
            order.setLabourCost(Double.parseDouble(tokens[9]));
            order.setTax(Double.parseDouble(tokens[10]));
            order.setTotal(Double.parseDouble(tokens[11]));

            orders.put(existingOrderId, order);
        }
    }

    /**
     * Loads configFile containing test mode indicator and last used index number
     *
     * @throws FileNotFoundException
     */
    public void loadConfigFile(String configFile) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(configFile)));
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();

            String[] tokens = currentLine.split(DELIMITER);

            if (tokens[0].equals("testmode")) {
                if (tokens[1].equals("y")) {
                    this.testMode = true;
                } else {
                    this.testMode = false;
                }
            } else if (tokens[0].equals("indexnumber")) {
                this.currentOrderNumber = Integer.parseInt(tokens[1]);
            }
        }
    }

    /**
     * Writes configFile containing test mode indicator and last used index number
     *
     * @throws IOException
     */
    public void writeConfigFile(String configFile) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(configFile));
        String testString = (this.testMode) ? "y" : "n";
        out.println("testmode" + DELIMITER + testString);
        out.println("indexnumber" + DELIMITER + this.currentOrderNumber);

        out.flush();
        out.close();
    }
}
