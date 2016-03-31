/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachinemvc.dao;

import com.tsg.vendingmachinemvc.dto.Item;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public interface VendingMachineDao {
    
    public void addItem(Item item);

    public void vendItem(String position);

    public Item getItemByPosition(String position);

    public List<Item> getAllItems();

    public void loadItemList(String itemFile) throws FileNotFoundException;

}
