/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring.dto;

import java.util.Objects;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Tax {

    private String state;
    private Double rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.state);
        hash = 23 * hash + Objects.hashCode(this.rate);
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
        final Tax other = (Tax) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.rate, other.rate)) {
            return false;
        }
        return true;
    }

}
