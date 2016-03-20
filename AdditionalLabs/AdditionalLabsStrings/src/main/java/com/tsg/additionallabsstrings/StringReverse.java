/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.additionallabsstrings;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class StringReverse {

    public String stringReverse(String str) { // I did this in JUnit rather than user input

        String newStr = "";
        for (int i = 1; i < str.length() + 1; i++) {
            newStr = newStr + str.substring(str.length() - i, str.length() - i + 1);
        }
        return newStr;

    }

    public String stringReverseAlt(String str) { // alt solution using char array

        char[] inputString = str.toCharArray();
        int len = inputString.length;

        for (int i = 0; i < len / 2; i++) {
            char c = inputString[i];
            inputString[i] = inputString[len - i - 1];
            inputString[len - i - 1] = c;
        }

        return String.valueOf(inputString);
    }
}
