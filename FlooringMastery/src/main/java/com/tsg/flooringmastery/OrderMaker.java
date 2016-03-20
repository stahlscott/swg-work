/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmastery;

import com.tsg.flooringmastery.dao.OrderDAO;
import com.tsg.flooringmastery.dao.ProductDAO;
import com.tsg.flooringmastery.dao.TaxDAO;
import com.tsg.flooringmastery.dto.Order;
import com.tsg.flooringmastery.dto.Product;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class OrderMaker {

    private TaxDAO taxDAO;
    private ProductDAO productDAO;

    public OrderMaker(TaxDAO taxDAO, ProductDAO productDAO) {
        this.taxDAO = taxDAO;
        this.productDAO = productDAO;
    }

    /**
     * Creates Order from given information, performing the logic to compute all other necessary 
     * order fields.
     * @param custName
     * @param state
     * @param productName
     * @param area
     * @return completed Order (except orderIDnumber which is assigned in OrderDAO)
     */
    public Order createOrder(String custName, String state, String productName, Double area) {
        Order order = new Order();
        order.setCustomerName(custName);
        order.setState(state);
        
        Double taxRate = taxDAO.getTaxRateByState(state);
        order.setTaxRate(taxRate);
        order.setArea(area);

        Product product = productDAO.getProductByName(productName);

        order.setMaterialCost((product.getCostPerSquareFoot() * area));
        order.setLabourCost(product.getLabourCostPerSquareFoot() * area);
        Double subTotal = order.getMaterialCost() + order.getLabourCost();
        order.setTax(taxRate / 100 * subTotal);
        order.setTotal(subTotal + order.getTax());
        order.setProductType(product.getProductType());
        order.setCostPerSquareFoot(product.getCostPerSquareFoot());
        order.setLabourCostPerSquareFoot(product.getLabourCostPerSquareFoot());

        return order;
    }
      public Order modifyOrder(Order order, String custName, String state, String productName, Double area) {
        
        order.setCustomerName(custName);
        order.setState(state);
        
        Double taxRate = taxDAO.getTaxRateByState(state);
        order.setTaxRate(taxRate);
        order.setArea(area);

        Product product = productDAO.getProductByName(productName);

        order.setMaterialCost((product.getCostPerSquareFoot() * area));
        order.setLabourCost(product.getLabourCostPerSquareFoot() * area);
        Double subTotal = order.getMaterialCost() + order.getLabourCost();
        order.setTax(taxRate / 100 * subTotal);
        order.setTotal(subTotal + order.getTax());
        order.setProductType(product.getProductType());
        order.setCostPerSquareFoot(product.getCostPerSquareFoot());
        order.setLabourCostPerSquareFoot(product.getLabourCostPerSquareFoot());

        return order;
    }
}
