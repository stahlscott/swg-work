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
public class MorseCodeMaker {

    /**
     * This only works with lowercase alpha letters. Could add several String[]s with if blocks to return
     * numbers, spaces, periods, etc.
     *
     * @param c
     * @return morse translation as String
     */
    private String morseCodeConvChar(char c) {
        String[] morseAlphaDict = {".-", // a = 97-97 = [0]
            "-...", // b = 98-97 = [1]
            "-.-.", // etc
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--.."};
        int i = (int) c;

        return morseAlphaDict[i - 97] + " "; // ascii code a = 97

    }

    public String morseCodeConvString(String str) {
        String convertedString = "";
        char[] strArray = str.toCharArray();
        for (char c : strArray) {
            convertedString += morseCodeConvChar(c);
        }
        return convertedString;
    }

}
