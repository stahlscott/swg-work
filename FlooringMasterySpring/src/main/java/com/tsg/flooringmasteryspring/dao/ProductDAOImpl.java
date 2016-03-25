/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring.dao;

import com.tsg.flooringmasteryspring.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ProductDAOImpl implements ProductDAOInterface {

    private final String DELIMITER = ",";
    private String productFile = "productfile.txt";
    private ArrayList<Product> products = new ArrayList<>();

    public ProductDAOImpl() {

    }

    public ProductDAOImpl(String productFile) {
        this.productFile = productFile;
    }

    @Override
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     *
     * @param name
     * @return Product that matches given name or null if not found
     */
    @Override
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
     *
     * @throws FileNotFoundException
     */
    @Override
    public void loadProductFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(productFile)));

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
