/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.mostwantedletter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class MostWantedLetter {

    public String mostWantedLetter(String str) {
        str = str.toLowerCase();
        Map<String, Integer> letterMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            String currentChar = str.substring(i, i + 1);
            if (currentChar.compareTo("a") >= 0 && currentChar.compareTo("z") <= 0) {
                letterMap.put(currentChar, letterMap.getOrDefault(currentChar, 0) + 1);
            }
        }

        Collection<String> letters = letterMap.keySet();
        
        String topChar = "zz";
        int maxNumOfOccurences = 0;

        for (String letter : letters) {
            int occurences = letterMap.get(letter);
            if (occurences > maxNumOfOccurences) {
                topChar = letter;
                maxNumOfOccurences = occurences;
            } else if (occurences == maxNumOfOccurences && topChar.compareTo(letter) > 0) {
                topChar = letter;
                maxNumOfOccurences = occurences;
            }
        }

        return topChar;
    }
}
