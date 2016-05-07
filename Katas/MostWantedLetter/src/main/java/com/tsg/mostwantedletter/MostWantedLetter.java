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
        // check for null/empty
        if (str == null) {
            return null;
        } else if (str.equals("")) {
            return "";
        }

        // set string to lower case
        str = str.toLowerCase();
        // create map of letters to their number of occurences
        Map<String, Integer> letterMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            // get string of letter at current index
            String currentChar = str.substring(i, i + 1);
            // check if it's between a -> z
            if (currentChar.compareTo("a") >= 0 && currentChar.compareTo("z") <= 0) {
                // add to map; if exists, set number of occurrences +1, otherwise set it to 1
                letterMap.put(currentChar, letterMap.getOrDefault(currentChar, 0) + 1);
            }
        }

        // get collection of letters
        Collection<String> letters = letterMap.keySet();
        // set this to "minimum" (if above filter works we should never have any letter
        // lower than zz - probably don't need to initialize this at all though
        String maxLetter = "zz";
        // set counter to find the peak number of occurrences
        int maxNumOfOccurrences = 0;

        // for each letter in the keySet
        for (String letter : letters) {
            // "current occurrences" = value associated with letter
            int occurrences = letterMap.get(letter);
            // if current occurrences is greater than our peak, set the maxLetter equal to the
            // current letter, set max occurrences equal to this letter's occurrences
            if (occurrences > maxNumOfOccurrences) {
                maxLetter = letter;
                maxNumOfOccurrences = occurrences;
                // otherwise, if number of occurrences is equal but the letter is alphabetically
                // higher, set the letter to that
            } else if (occurrences == maxNumOfOccurrences && maxLetter.compareTo(letter) > 0) {
                maxLetter = letter;
            }
        }

        return maxLetter;
    }
}
