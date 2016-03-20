/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmastery.dto;

import java.util.Objects;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Order {

    private Integer orderNumber;
    private String customerName;
    private String state;
    private Double taxRate;
    private Double area;
    private Double materialCost;
    private Double labourCost;
    private Double tax;
    private Double total;
    private String productType;
    private Double costPerSquareFoot;
    private Double labourCostPerSquareFoot;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(Double materialCost) {
        this.materialCost = materialCost;
    }

    public Double getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(Double labourCost) {
        this.labourCost = labourCost;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(Double costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public Double getLabourCostPerSquareFoot() {
        return labourCostPerSquareFoot;
    }

    public void setLabourCostPerSquareFoot(Double labourCostPerSquareFoot) {
        this.labourCostPerSquareFoot = labourCostPerSquareFoot;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.orderNumber);
        hash = 97 * hash + Objects.hashCode(this.customerName);
        hash = 97 * hash + Objects.hashCode(this.state);
        hash = 97 * hash + Objects.hashCode(this.taxRate);
        hash = 97 * hash + Objects.hashCode(this.area);
        hash = 97 * hash + Objects.hashCode(this.materialCost);
        hash = 97 * hash + Objects.hashCode(this.labourCost);
        hash = 97 * hash + Objects.hashCode(this.tax);
        hash = 97 * hash + Objects.hashCode(this.total);
        hash = 97 * hash + Objects.hashCode(this.productType);
        hash = 97 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 97 * hash + Objects.hashCode(this.labourCostPerSquareFoot);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.labourCost, other.labourCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.labourCostPerSquareFoot, other.labourCostPerSquareFoot)) {
            return false;
        }
        return true;
    }

}
