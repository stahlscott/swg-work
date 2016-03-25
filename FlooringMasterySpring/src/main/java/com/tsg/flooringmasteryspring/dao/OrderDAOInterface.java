/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring.dao;

import com.tsg.flooringmasteryspring.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public interface OrderDAOInterface {

    public boolean isTestMode();

    public String getWorkingDate();

    public void addOrder(Order order);

    public void deleteOrder(Order order);

    public Order getOrder(Integer orderNumber);

    public void changeOrderDate(Order order, String date) throws IOException;

    public ArrayList<Order> getAllOrdersByDate(String date);

    public void changeWorkingDate(String date) throws IOException;

    public void writeOrderFile() throws IOException;

    public void appendToDeletedFile(Order order) throws IOException;

    public void loadOrderFile(String date) throws FileNotFoundException;

    public void loadConfigFile(String configFile) throws FileNotFoundException;

    public void writeConfigFile(String configFile) throws IOException;
}
