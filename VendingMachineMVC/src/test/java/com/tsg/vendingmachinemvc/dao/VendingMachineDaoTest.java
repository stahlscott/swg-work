/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachinemvc.dao;

import com.tsg.vendingmachinemvc.dto.Item;
import java.io.FileNotFoundException;
import java.util.List;
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
public class VendingMachineDaoTest {

    private VendingMachineDao dao;
    private Item item1;
    private Item item2;
    private Item item3;

    public VendingMachineDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);

        item1 = new Item();
        item1.setName("Rogue");
        item1.setCost(5.50);
        item1.setQuantity(10);
        item1.setPosition("A1");

        item2 = new Item();
        item2.setName("Stone");
        item2.setCost(6.50);
        item2.setQuantity(5);
        item2.setPosition("B2");

        item3 = new Item();
        item3.setName("Genny");
        item3.setCost(4.50);
        item3.setQuantity(20);
        item3.setPosition("C3");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetVendTest() {
        dao.addItem(item1);
        dao.addItem(item2);
        dao.addItem(item3);

        Item result = dao.getItemByPosition(item1.getPosition());
        assertEquals(item1, result);

        int proofQty = item2.getQuantity() - 1;
        dao.vendItem(item2.getPosition());
        int resultQty = dao.getItemByPosition(item2.getPosition()).getQuantity();
        assertEquals(proofQty, resultQty);
    }

    @Test
    public void getAllItems() {
        dao.addItem(item1);
        dao.addItem(item2);
        dao.addItem(item3);

        List<Item> iList = dao.getAllItems();

        assertEquals(3, iList.size());
    }

    @Test
    public void loadItemsTest() {
        try {
            dao.loadItemList("item_file.txt");
            assertEquals(9, dao.getAllItems().size());
        } catch (FileNotFoundException ex) {
            fail();
        }
    }
}
