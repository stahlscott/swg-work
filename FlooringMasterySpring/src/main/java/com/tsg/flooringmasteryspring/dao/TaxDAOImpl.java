/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring.dao;

import com.tsg.flooringmasteryspring.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class TaxDAOImpl implements TaxDAOInterface {

    private final String DELIMITER = ",";
    private String taxFile = "taxfile.txt";
    private ArrayList<Tax> taxes = new ArrayList<>();

    public TaxDAOImpl() {

    }

    public TaxDAOImpl(String taxFile) {
        this.taxFile = taxFile;
    }

    @Override
    public ArrayList<Tax> getTaxes() {
        return taxes;
    }

    /**
     *
     * @param state
     * @return tax rate as a Double from given state name, or null if not found
     */
    @Override
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
     *
     * @throws FileNotFoundException
     */
    @Override
    public void loadTaxFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(taxFile)));

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
