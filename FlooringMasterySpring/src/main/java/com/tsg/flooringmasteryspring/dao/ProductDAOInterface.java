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
public interface ProductDAOInterface {

    public ArrayList<Product> getProducts();

    public Product getProductByName(String name);

    public void loadProductFile() throws FileNotFoundException;

}
