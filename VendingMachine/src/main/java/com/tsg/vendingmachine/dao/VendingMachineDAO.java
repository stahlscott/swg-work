/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachine.dao;

import com.tsg.vendingmachine.dto.Beer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class VendingMachineDAO {

    private Map<String, Beer> vendingMachine = new TreeMap<>();
    private static final String BEER_FILE = "thefridge.txt";
    private static final String DELIMITER = "::";

    /**
     * Reduces quantity of beer by one.
     *
     * @param itemPosition String
     */
    public void sellBeer(String itemPosition) {
        Beer beer = vendingMachine.get(itemPosition);
        int currentQuantity = beer.getQuantity();
        beer.setQuantity(currentQuantity - 1);
    }

    /**
     *
     * @param itemPosition String
     * @return Beer object at itemPosition
     */
    public Beer getBeerByItemPosition(String itemPosition) {
        return vendingMachine.get(itemPosition);
    }

    /**
     *
     * @return list of all Beers in vending machine
     */
    public ArrayList<Beer> getAllBeers() {
        Collection<Beer> beers = vendingMachine.values();
        ArrayList<Beer> beerArrList = new ArrayList<>();
        for (Beer beer : beers) {
            beerArrList.add(beer);
        }

        return beerArrList;
    }

    /**
     * Adds addQty to beer quantity
     *
     * @param beer Beer
     * @param addQty int
     */
    public void addBeerQty(Beer beer, int addQty) {
        int currentQuantity = beer.getQuantity();
        beer.setQuantity(currentQuantity + addQty);
    }

    /**
     * Writes vending machine file to BEER_FILE String in following order. itemPosition :: name :: cost :: quantity
     *
     * @throws IOException
     */
    public void writeBeerList() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(BEER_FILE));
        Collection<Beer> beers = vendingMachine.values();

        for (Beer beer : beers) {
            out.println(beer.getItemPosition() + DELIMITER + beer.getName() + DELIMITER + beer.getCost() + DELIMITER + beer.getQuantity());
        }

        out.flush();
        out.close();

    }

    /**
     * Reads vending machine file from BEER_FILE String in following order. itemPosition :: name :: cost :: quantity
     *
     * @throws FileNotFoundException
     */
    public void loadBeerList() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(BEER_FILE)));

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] tokens = currentLine.split(DELIMITER);

            Beer beer = new Beer();

            beer.setItemPosition(tokens[0]);
            beer.setName(tokens[1]);
            beer.setCost(Double.parseDouble(tokens[2]));
            beer.setQuantity(Integer.parseInt(tokens[3]));

            this.vendingMachine.put(tokens[0], beer);
        }

    }

}
