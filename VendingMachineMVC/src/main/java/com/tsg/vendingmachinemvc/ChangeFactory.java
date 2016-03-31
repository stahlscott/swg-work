/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachinemvc;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ChangeFactory {

    private Integer totalInPennies = 0;

    public double getTotal() {
        double total = totalInPennies;
        total /= 100;
        return total;
    }

    /**
     *
     * @return number of quarters that would be due to the customer as change in current dollar amount held
     */
    public int getQuarters() {
        return totalInPennies / 25;
    }

    /**
     *
     * @return number of dimes that would be due to the customer after quarters are dispense
     */
    public int getDimes() {
        return (totalInPennies % 25) / 10;
    }

    /**
     *
     * @return number of nickels that would be due to the customer after quarters & dimes are dispensed
     */
    public int getNickels() {
        return ((totalInPennies % 25) % 10) / 5;
    }

    /**
     *
     * @return number of pennies due to the customer after quarters, dimes, nickels are dispensed
     */
    public int getPennies() {
        return totalInPennies % 5;
    }

    /**
     * adds addedMoney to total held in class; takes in double, converts to integer (pennies)
     *
     * @param addedMoney double
     */
    public void addToTotal(double addedMoney) {
        int addedPennies = (int) (addedMoney * 100);
        this.totalInPennies += addedPennies;

    }

    /**
     * reduces total by removedMoney
     *
     * @param removedMoney double
     */
    public void removeFromTotal(double removedMoney) {
        int removedPennies = (int) (removedMoney * 100);
        this.totalInPennies -= removedPennies;
    }
}
