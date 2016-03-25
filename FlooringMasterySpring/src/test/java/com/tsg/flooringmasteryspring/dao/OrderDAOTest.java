/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring.dao;

import com.tsg.flooringmasteryspring.dao.OrderDAOImpl;
import com.tsg.flooringmasteryspring.dto.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class OrderDAOTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    public OrderDAOTest() {
    }

    final String CONFIG_FILE = "test/configtest.ini";
    final String CONFIG_FILE_WRITE = "test/configtestwrite.ini";
    // generates random string for unique test
//    final String TEST_DATE = "test" + UUID.randomUUID().toString();
//    final String TEST_DATE_2 = "test2" + UUID.randomUUID().toString();
//    final String TEST_DATE_3 = "testmode" + UUID.randomUUID().toString();
    
    final String TEST_DATE = "TEST";
    final String TEST_DATE_2 = "TEST2";
    final String TEST_DATE_3 = "TEST3";
    
    OrderDAOInterface orderDAO;
    Order order1;
    Order order2;
    Order order3;

    @Before
    public void setUp() {

        orderDAO = ctx.getBean("orderDAOtest1false", OrderDAOInterface.class);

        order1 = new Order();
        order1.setCustomerName("Aaa Aaaa");
        order1.setState("OH");
        order1.setTaxRate(6.25);
        order1.setArea(100.0);
        order1.setProductType("Tile");
        order1.setCostPerSquareFoot(3.50);
        order1.setLabourCostPerSquareFoot(4.15);
        order1.setMaterialCost(350.0);
        order1.setLabourCost(415.0);
        order1.setTax(47.8125);
        order1.setTotal(812.8125);

        order2 = new Order();
        order2.setCustomerName("Bbb Bbbb");
        order2.setState("MI");
        order2.setTaxRate(5.75);
        order2.setArea(500.0);
        order2.setProductType("Carpet");
        order2.setCostPerSquareFoot(2.25);
        order2.setLabourCostPerSquareFoot(2.10);
        order2.setMaterialCost(1125.0);
        order2.setLabourCost(1050.0);
        order2.setTax(125.0625);
        order2.setTotal(2300.0625);

        order3 = new Order();
        order3.setCustomerName("Ccc Cccc");
        order3.setState("IN");
        order3.setTaxRate(6.00);
        order3.setArea(200.0);
        order3.setProductType("Wood");
        order3.setCostPerSquareFoot(5.15);
        order3.setLabourCostPerSquareFoot(4.75);
        order3.setMaterialCost(1030.0);
        order3.setLabourCost(950.0);
        order3.setTax(118.8);
        order3.setTotal(2098.8);
    }

    @After
    public void tearDown() {
        File fileToDelete = new File("Orders_" + TEST_DATE + ".txt");
        boolean deleted = fileToDelete.delete();
        fileToDelete = new File("Orders_" + TEST_DATE_2 + ".txt");
        deleted = fileToDelete.delete();
    }

    @Test
    public void OrderDAOTestReadWriteFile() {
        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);
        try {
            orderDAO.writeOrderFile();
        } catch (IOException ex) {
            fail();
        }
        // Trick on this one was using order1.getOrderNumber() instead of 1, 2, 3
        OrderDAOInterface orderDAOLoad = ctx.getBean("orderDAOtest1false", OrderDAOInterface.class);
        try {
            orderDAOLoad.loadOrderFile(TEST_DATE);
            assertEquals(order1, orderDAOLoad.getOrder(order1.getOrderNumber()));
            assertEquals(order2, orderDAOLoad.getOrder(order2.getOrderNumber()));
            assertEquals(order3, orderDAOLoad.getOrder(order3.getOrderNumber()));
        } catch (FileNotFoundException ex) {
            fail();
        }
    }

    @Test // initialize new orderDAO with testMode on, ensure that nothing writes
    public void OrderDAOTestTestMode() {
        orderDAO = ctx.getBean("orderDAOtest3", OrderDAOInterface.class);
        assertTrue(orderDAO.isTestMode());

        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);
        try {
            orderDAO.writeOrderFile();
        } catch (IOException ex) {
            fail();
        }

        OrderDAOInterface orderDAOLoad = ctx.getBean("orderDAOtest3false", OrderDAOInterface.class);
        try {
            orderDAOLoad.loadOrderFile(TEST_DATE_3);
            fail();
        } catch (FileNotFoundException ex) {
        }
    }

    @Test // test getWorkingDate for setup date
    public void OrderDAOTestWorkingDate() {
        assertEquals(TEST_DATE, orderDAO.getWorkingDate());
    }

    @Test
    public void OrderDAOTestAddOrder() {
        //arrange
        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);

        //act
        Order result1 = orderDAO.getOrder(order1.getOrderNumber());
        Order result2 = orderDAO.getOrder(order2.getOrderNumber());
        Order result3 = orderDAO.getOrder(order3.getOrderNumber());

        //assert
        Assert.assertEquals(order1, result1);
        Assert.assertEquals(order2, result2);
        Assert.assertEquals(order3, result3);

    }

    @Test
    public void OrderDAOTestDeleteOrder() {
        //arrange
        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);

        //act
//        Order deleteResult = orderDAO.deleteOrder(order1);
        orderDAO.deleteOrder(order1);
        Order getResult = orderDAO.getOrder(order1.getOrderNumber());

        //assert
        Assert.assertNull(getResult);
        //Assert.assertEquals(order1, deleteResult);

        ArrayList<Order> currentOrders = orderDAO.getAllOrdersByDate(TEST_DATE);
        Assert.assertEquals(2, currentOrders.size());

    }

    @Test // change to new date, expect order 3 to exist, expect order1 && order2 not to exist
    public void OrderDAOTestChangeOrderDate1() {
        // adds orders to current hashmap
        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);

        // attempt to change date -> save file, open new blank hashmap
        try {
            orderDAO.changeOrderDate(order3, TEST_DATE_2);
        } catch (IOException ex) {
            fail(); // if unable to write file
        }

        // check that current date is TEST_DATE_2
        assertEquals(TEST_DATE_2, orderDAO.getWorkingDate());
        // check that order1 and order2 are not on the current hashmap
        assertNull(orderDAO.getOrder(order1.getOrderNumber()));
        assertNull(orderDAO.getOrder(order2.getOrderNumber()));
        // check that order3 is present
        assertEquals(order3, orderDAO.getOrder(3));

        orderDAO = ctx.getBean("orderDAOtest1", OrderDAOInterface.class);
        try { // set up new DAO with previous TEST_DATE, load file saved in previous
            orderDAO.loadOrderFile(TEST_DATE);
        } catch (FileNotFoundException ex) {
            fail();
        }

        // check order1 and order 2 appear and order3 is gone
        assertEquals(order1, orderDAO.getOrder(order1.getOrderNumber()));
        assertEquals(order2, orderDAO.getOrder(order2.getOrderNumber()));
        assertNull(orderDAO.getOrder(order3.getOrderNumber()));
    }

    @Test // change date to current date (no change), all 3 orders should still exist
    public void OrderDAOTestChangeOrderDate2() {
        // adds orders to current hashmap
        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);

        // attempt to change date to current date; should do nothing
        try {
            orderDAO.changeOrderDate(order3, TEST_DATE);
        } catch (IOException ex) {
            fail();
        }

        // check date equals TEST_DATE
        assertEquals(TEST_DATE, orderDAO.getWorkingDate());
        // check all orders still exist, none were deleted
        assertEquals(order1, orderDAO.getOrder(1));
        assertEquals(order2, orderDAO.getOrder(2));
        assertEquals(order3, orderDAO.getOrder(3));
    }

    @Test // test all orders added should be returned in ArrayList
    public void OrderDAOTestGetAllOrdersByDate() {
        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);

        ArrayList<Order> orders = orderDAO.getAllOrdersByDate(TEST_DATE);
        ArrayList<Order> ordersProof = new ArrayList<>();
        ordersProof.add(order1);
        ordersProof.add(order2);
        ordersProof.add(order3);

        // test proof
        for (Order order : orders) {
            assertTrue(ordersProof.contains(order));
            ordersProof.remove(order);
        }

    }

    @Test // test change date to new date
    public void OrderDAOTestChangeWorkingDate1() {
        try {
            orderDAO.changeWorkingDate(TEST_DATE_2);
        } catch (IOException ex) {
            fail();
        }
        assertEquals(TEST_DATE_2, orderDAO.getWorkingDate());
    }

    @Test // test change date to current date, should be no change
    public void OrderDAOTestChangeWorkingDate2() {
        try {
            orderDAO.changeWorkingDate(TEST_DATE);
        } catch (IOException ex) {
            fail();
        }
        assertEquals(TEST_DATE, orderDAO.getWorkingDate());
    }

    @Test // test load config file -- now unnecessary as next test checks both
    public void OrderDAOTestLoadConfigFile() {
        orderDAO = new OrderDAOImpl(TEST_DATE);
        try {
            orderDAO.loadConfigFile(CONFIG_FILE);
        } catch (FileNotFoundException ex) {
            fail();
        }
        assertEquals(TEST_DATE, orderDAO.getWorkingDate());

    }

    // had to rewrite read/write config file to accept incoming String parameter
    // rather than CONFIG_FILE held as OrderDAOImpl final
    @Test
    public void OrderDAOTestReadWriteConfigFile() {
        // new DAO, "false" so currentOrderNumber = 1
        orderDAO = ctx.getBean("orderDAOtest1false", OrderDAOInterface.class);
        // add 3 orders
        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);

        // write config file which should set idIndex to 4
        try {
            orderDAO.writeConfigFile(CONFIG_FILE);
        } catch (IOException ex) {
            fail();
        }

        // new blank orderDAO
        orderDAO = new OrderDAOImpl(TEST_DATE);
        // load config file which should set idIndex to 4
        try {
            orderDAO.loadConfigFile(CONFIG_FILE);
        } catch (FileNotFoundException ex) {
            fail();
        }
        // add another order to hashmap, should be assigned 4
        orderDAO.addOrder(order1);

        // check that "3+1" is equal to "4"
        assertEquals(orderDAO.getOrder(order3.getOrderNumber() + 1), orderDAO.getOrder(order1.getOrderNumber()));
    }
}
