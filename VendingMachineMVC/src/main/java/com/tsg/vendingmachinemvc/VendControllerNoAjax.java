/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachinemvc;

import com.tsg.vendingmachinemvc.dao.VendingMachineDao;
import com.tsg.vendingmachinemvc.dto.Item;
import java.io.FileNotFoundException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
@Controller
public class VendControllerNoAjax {

    private final VendingMachineDao dao;
    private ChangeFactory change;

    @Inject
    public VendControllerNoAjax(VendingMachineDao dao) {
        this.dao = dao;
        change = new ChangeFactory();
        try {
            dao.loadItemList("item_file.txt");
        } catch (FileNotFoundException ex) {
        }
    }

    @RequestMapping(value = {"/", "/vend"}, method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<Item> iList = dao.getAllItems();
        model.addAttribute("iList", iList);
        return "vend";
    }

    @RequestMapping(value = "/vendItem", method = RequestMethod.POST)
    public String vendItem(HttpServletRequest req) {
        String selection = req.getParameter("selection");
        String totalDeposit = req.getParameter("totalDeposit");
        if (!selection.equals("")) {
            totalDeposit = (!(totalDeposit).equals("")) ? totalDeposit.substring(2) : "0.00";
            double deposit = Double.parseDouble(totalDeposit);
            change.addToTotal(deposit);

            Item item = dao.getItemByPosition(selection);

            if (item.getQuantity() > 0) {
                if (deposit >= item.getCost()) {
                    req.setAttribute("successMsg", "Enjoy your nice, cold \"" + item.getName() + "\"!");
                    req.setAttribute("imgUrl", item.getImgUrl());
                    dao.vendItem(item.getPosition());
                    change.removeFromTotal(item.getCost());
                } else {
                    req.setAttribute("errMsg", "Not enough money deposited. Please try again.");
                }
            } else {
                req.setAttribute("errMsg", "Sorry, that item is out of stock.");
            }
        } else {
            req.setAttribute("errMsg", "You did not make a selection.");
        }

        // can you setAttributes and still redirect: ?
        req.setAttribute("quarters", change.getQuarters());
        req.setAttribute("dimes", change.getDimes());
        req.setAttribute("nickels", change.getNickels());
        change.removeFromTotal(change.getTotal());
        return "receiveItem";
    }
}
