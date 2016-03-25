/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring;

import com.tsg.flooringmasteryspring.dao.ProductDAOImpl;
import com.tsg.flooringmasteryspring.dao.ProductDAOInterface;
import com.tsg.flooringmasteryspring.dao.TaxDAOImpl;
import com.tsg.flooringmasteryspring.dao.TaxDAOInterface;
import com.tsg.flooringmasteryspring.dto.Order;
import com.tsg.flooringmasteryspring.dto.Product;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class OrderMaker {

    private TaxDAOInterface taxDAO;
    private ProductDAOInterface productDAO;

    public OrderMaker(TaxDAOInterface taxDAO, ProductDAOInterface productDAO) {
        this.taxDAO = taxDAO;
        this.productDAO = productDAO;
    }

    /**
     * Creates Order from given information, performing the logic to compute all other necessary order fields.
     *
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
        // checks if state is null; set rate to 0 if not found
        if (taxRate == null) {
            taxRate = 0.0;
        }
        order.setTaxRate(taxRate);
        order.setArea(area);

        Product product = productDAO.getProductByName(productName);

        // checks if product is null; set fields to 0 if not found
        if (product == null) {
            product = new Product();
            product.setProductType(productName);
            product.setCostPerSquareFoot(0.0);
            product.setLabourCostPerSquareFoot(0.0);
        }

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
        if (taxRate == null) {
            taxRate = 0.0;
        }
        order.setTaxRate(taxRate);
        order.setArea(area);

        Product product = productDAO.getProductByName(productName);

        if (product == null) {
            product = new Product();
            product.setProductType(productName);
            product.setCostPerSquareFoot(0.0);
            product.setLabourCostPerSquareFoot(0.0);
        }

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
