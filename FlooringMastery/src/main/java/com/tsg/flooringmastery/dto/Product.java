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
public class Product {

    private String productType;
    private Double costPerSquareFoot;
    private Double labourCostPerSquareFoot;

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
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.productType);
        hash = 59 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 59 * hash + Objects.hashCode(this.labourCostPerSquareFoot);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.productType, other.productType)) {
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
