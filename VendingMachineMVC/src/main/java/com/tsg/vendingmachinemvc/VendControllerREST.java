/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachinemvc;

import com.tsg.vendingmachinemvc.dao.VendingMachineDao;
import com.tsg.vendingmachinemvc.dto.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
@Controller
public class VendControllerREST {

    private final VendingMachineDao dao;

    @Inject
    public VendControllerREST(VendingMachineDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/vend-ajax", method = RequestMethod.GET)
    public String displayHomePage() {
        return "vend-ajax";
    }

    @RequestMapping(value = "/item/{itemPos}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItem(@PathVariable String itemPos) {
        return dao.getItemByPosition(itemPos);
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @RequestMapping(value = "/add/{itemPos}/{qty}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable String itemPos, @PathVariable int qty) {
        Item item = dao.getItemByPosition(itemPos);
        item.setQuantity(item.getQuantity() + qty);
        dao.addItem(item);
    }

    @RequestMapping(value = "/coinReturn/{money}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> coinReturn(@PathVariable String money) {
        ChangeFactory change = new ChangeFactory();
        money = (!(money).equals("")) ? money : "0.00";
        double deposit = Double.parseDouble(money);
        change.addToTotal(deposit);

        Map<String, String> result = new HashMap<>();

        result.put("quarters", String.valueOf(change.getQuarters()));
        result.put("dimes", String.valueOf(change.getDimes()));
        result.put("nickels", String.valueOf(change.getNickels()));

        return result;
    }

    @RequestMapping(value = "/item/{itemPos}/{money}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, String> vendItem(@PathVariable String itemPos, @PathVariable String money) {
        ChangeFactory change = new ChangeFactory();
        money = (!(money).equals("")) ? money : "0.00";
        double deposit = Double.parseDouble(money);
        change.addToTotal(deposit);
        Item item = dao.getItemByPosition(itemPos);
        Map<String, String> result = new HashMap<>();
        
        if (item.getQuantity() > 0) {
            if (deposit >= item.getCost()) {
                dao.vendItem(itemPos);
                change.removeFromTotal(item.getCost());

                result.put("name", item.getName());
                result.put("quarters", String.valueOf(change.getQuarters()));
                result.put("dimes", String.valueOf(change.getDimes()));
                result.put("nickels", String.valueOf(change.getNickels()));

                return result;
            }
        }
        
        result.put("error", "true");
        return result;

    }
}
