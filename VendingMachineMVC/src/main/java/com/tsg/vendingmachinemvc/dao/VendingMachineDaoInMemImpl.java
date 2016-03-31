/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachinemvc.dao;

import com.tsg.vendingmachinemvc.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {

    private Map<String, Item> vendingMachine = new TreeMap<>();
    private static final String DELIMITER = "::";

    @Override
    public void addItem(Item item) {
        vendingMachine.put(item.getPosition(), item);
    }

    @Override
    public void vendItem(String position) {
        Item item = vendingMachine.get(position);
        item.setQuantity(item.getQuantity() - 1);
    }

    @Override
    public Item getItemByPosition(String position) {
        return vendingMachine.get(position);
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList(vendingMachine.values());
    }

    @Override
    public void loadItemList(String itemFile) throws FileNotFoundException {

        Item item = new Item();
        item.setPosition("A1");
        item.setName("Rogue Dead Guy");
        item.setCost(4.50);
        item.setQuantity(10);
        item.setImgUrl("/img/Rogue-Dead-Guy-Ale.jpg");
        addItem(item);

        item = new Item();
        item.setPosition("A2");
        item.setName("Stone Ruination");
        item.setCost(6.45);
        item.setQuantity(5);
        item.setImgUrl("/img/ruination.png");
        addItem(item);

        item = new Item();
        item.setPosition("A3");
        item.setName("Deschutes Pinedrops");
        item.setCost(5.65);
        item.setQuantity(20);
        item.setImgUrl("/img/pinedrops.png");
        addItem(item);

        item = new Item();
        item.setPosition("B1");
        item.setName("Ithaca Flower Power");
        item.setCost(6.00);
        item.setQuantity(14);
        item.setImgUrl("/img/flowerpower.jpg");
        addItem(item);

        item = new Item();
        item.setPosition("B2");
        item.setName("Full Pint Chinookie");
        item.setCost(6.85);
        item.setQuantity(12);
        item.setImgUrl("/img/chinookie.png");
        addItem(item);

        item = new Item();
        item.setPosition("B3");
        item.setName("Southern Tier Live");
        item.setCost(4.35);
        item.setQuantity(14);
        item.setImgUrl("/img/live.jpg");
        addItem(item);

        item = new Item();
        item.setPosition("C1");
        item.setName("Left Hand Nitro Milk Stout");
        item.setCost(4.95);
        item.setQuantity(10);
        item.setImgUrl("/img/nitromilkstout.png");
        addItem(item);

        item = new Item();
        item.setPosition("C2");
        item.setName("Three Heads The Kind");
        item.setCost(5.25);
        item.setQuantity(8);
        item.setImgUrl("/img/thekind.jpg");
        addItem(item);

        item = new Item();
        item.setPosition("C3");
        item.setName("Thirsty Dog Citra");
        item.setCost(5.90);
        item.setQuantity(20);
        item.setImgUrl("/img/citra.png");
        addItem(item);

//        Scanner sc = new Scanner(new BufferedReader(new FileReader(itemFile)));
//
//        while (sc.hasNextLine()) {
//            String currentLine = sc.nextLine();
//            String[] tokens = currentLine.split(DELIMITER);
//
//            Item item = new Item();
//
//            item.setPosition(tokens[0]);
//            item.setName(tokens[1]);
//            item.setCost(Double.parseDouble(tokens[2]));
//            item.setQuantity(Integer.parseInt(tokens[3]));
//            item.setImgUrl(Integer.parseInt(tokens[4]));
//
//            vendingMachine.put(tokens[0], item);
//        }
    }

}
