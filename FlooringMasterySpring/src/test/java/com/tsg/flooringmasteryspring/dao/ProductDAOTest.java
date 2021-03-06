/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring.dao;

import com.tsg.flooringmasteryspring.dao.ProductDAOImpl;
import com.tsg.flooringmasteryspring.dto.Product;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ProductDAOTest {

    public ProductDAOTest() {
    }
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    ProductDAOInterface productDAO;
    Product tile;
    Product laminate;
    Product carpet;
    Product wood;

    @Before
    public void setUp() {
        productDAO = ctx.getBean("productDAO", ProductDAOInterface.class);

        carpet = new Product();
        carpet.setProductType("Carpet");
        carpet.setCostPerSquareFoot(2.25);
        carpet.setLabourCostPerSquareFoot(2.10);

        laminate = new Product();
        laminate.setProductType("Laminate");
        laminate.setCostPerSquareFoot(1.75);
        laminate.setLabourCostPerSquareFoot(2.10);

        tile = new Product();
        tile.setProductType("Tile");
        tile.setCostPerSquareFoot(3.50);
        tile.setLabourCostPerSquareFoot(4.15);

        wood = new Product();
        wood.setProductType("Wood");
        wood.setCostPerSquareFoot(5.15);
        wood.setLabourCostPerSquareFoot(4.75);

    }

    @After
    public void tearDown() {
    }

    @Test // check load
    public void ProductDAOLoadTest() {
        try {
            productDAO.loadProductFile();
        } catch (FileNotFoundException ex) {
            fail();
        }
    }

    @Test
    public void ProductDAOGetProductTest() {
        try {
            productDAO.loadProductFile();
        } catch (FileNotFoundException ex) {
            fail();
        }

        Product result = productDAO.getProductByName("Tile");
        assertEquals(tile, result);
    }

    @Test
    public void ProductDAOGetAllProductsTest() {
        try {
            productDAO.loadProductFile();
        } catch (FileNotFoundException ex) {
            fail();
        }

        ArrayList<Product> products = productDAO.getProducts();
        ArrayList<Product> proof = new ArrayList<>();
        proof.add(carpet);
        proof.add(laminate);
        proof.add(tile);
        proof.add(wood);

        for (Product product : products) {
            assertTrue(proof.contains(product));
            proof.remove(product);
        }
    }
}
