/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachine.dto;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Beer {
    private String name;
    private Double cost;
    private Integer quantity;
    private String itemPosition;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the itemPosition
     */
    public String getItemPosition() {
        return itemPosition;
    }

    /**
     * @param itemPosition the itemPosition to set
     */
    public void setItemPosition(String itemPosition) {
        this.itemPosition = itemPosition;
    }
    
}
