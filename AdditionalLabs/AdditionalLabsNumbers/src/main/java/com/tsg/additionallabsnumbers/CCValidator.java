/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.additionallabsnumbers;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class CCValidator {

    public boolean luhnCheck(String ccNum) {
        int[] luhnArray = new int[ccNum.length()];
        for (int i = 0; i < luhnArray.length; i++) {
            luhnArray[i] = ccNum.charAt(ccNum.length() - 1 - i) - '0'; // luhn checks from end of the number
        }
        int ccTotal = 0;
        for (int i = 0; i < luhnArray.length; i++) {
            if ((i + 1) % 2 == 0) {
                luhnArray[i] *= 2;
                if (luhnArray[i] > 9) {
                    luhnArray[i] -= 9;
                }
            }
            ccTotal += luhnArray[i];
        }

        return ccTotal % 10 == 0;
    }

    /* 
    amex = 34, 37 & length 15
    discover = 6011, 622126-622925, 644-649, 65 & length 16, 19
    mastercard = 51-55 & length 16
    visa = 4 & length 13, 16 or 19
     */
    public boolean ccCheck(String ccNum) {
        int iin2 = Integer.parseInt(ccNum.substring(0, 2));
        int len = ccNum.length();
        boolean valid = false;

        if ((iin2 == 34 || iin2 == 37) & len == 15) { // amex
            System.out.println("American Express");
            valid = luhnCheck(ccNum);
        } else if ((60 <= iin2 && iin2 <= 65) && (len == 16 || len == 19)) { // discover
            int iin3 = Integer.parseInt(ccNum.substring(0, 3));
            int iin4 = Integer.parseInt(ccNum.substring(0, 4));
            int iin6 = Integer.parseInt(ccNum.substring(0, 6));
            if (iin4 == 6011) {
                System.out.println("Discover");
                valid = luhnCheck(ccNum);
            } else if (644 <= iin3 && iin3 <= 659) {
                System.out.println("Discover");
                valid = luhnCheck(ccNum);
            } else if (622126 <= iin6 && iin6 <= 622925){
                System.out.println("Discover");
                valid = luhnCheck(ccNum);
            } else {
                System.out.println("Card vendor not recognized.");
            }
        } else if ((51 <= iin2 && iin2 <= 55) && len == 16) { // mastercard
            System.out.println("MasterCard");
            valid = luhnCheck(ccNum);
        } else if ((40 <= iin2 && iin2 <= 49) && (len == 13 || len == 16 || len == 19)) { // visa
            System.out.println("Visa");
            valid = luhnCheck(ccNum);
        } else {
            System.out.println("Card vendor not recognized.");
        }

        if (valid) {
            System.out.println("Card is valid.");
            return true;
        } else {
            System.out.println("Card is NOT valid.");
            return false;
        } 
        // Calendar.getInstance()
    }
}
