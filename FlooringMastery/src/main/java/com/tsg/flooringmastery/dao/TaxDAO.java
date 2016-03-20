/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmastery.dao;

import com.tsg.flooringmastery.dto.Product;
import com.tsg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class TaxDAO {

    private final String TAX_FILE = "taxfile.txt";
    private final String DELIMITER = ",";
    private ArrayList<Tax> taxes = new ArrayList<>();

    public ArrayList<Tax> getTaxes() {
        return taxes;
    }

    /**
     * 
     * @param state
     * @return tax rate as a Double from given state name, or null if not found
     */
    public Double getTaxRateByState(String state) {
        for (Tax tax : taxes) {
            if (tax.getState().equals(state)) {
                return tax.getRate();
            }

        }
        return null;
    }

    /**
     * Loads tax information file including state abbrev and tax rate
     * @throws FileNotFoundException 
     */
    public void loadTaxFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();

            String[] tokens = currentLine.split(DELIMITER);

            Tax tax = new Tax();

            tax.setState(tokens[0]);
            tax.setRate(Double.parseDouble(tokens[1]));
            taxes.add(tax);
        }
    }

}
