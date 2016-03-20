/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmastery.dao;

import com.tsg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ProductDAO {

    private final String PRODUCT_FILE = "productfile.txt";
    private final String DELIMITER = ",";
    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * 
     * @param name
     * @return Product that matches given name or null if not found
     */
    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getProductType().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Loads PRODUCT_FILE containing product information (type, cost per sf, labor cost per sf)
     * @throws FileNotFoundException 
     */
    public void loadProductFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();

            String[] tokens = currentLine.split(DELIMITER);

            Product product = new Product();

            product.setProductType(tokens[0]);
            product.setCostPerSquareFoot(Double.parseDouble(tokens[1]));
            product.setLabourCostPerSquareFoot(Double.parseDouble(tokens[2]));
            products.add(product);
        }
    }

}
