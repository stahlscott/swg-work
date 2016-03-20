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
public class CharCount {

    public int vowelCount(String str) {
        char[] wordArr = str.toCharArray();
        char[] vowelArr = {'a', 'e', 'i', 'o', 'u'};
        int vowelCount = 0;

        for (char c : wordArr) {
            for (char d : vowelArr) {
                if (d == c) {
                    vowelCount++;
                }
            }
        }

        return vowelCount;
    }

    public int vowelAndSpaceCount(String str) {
        char[] wordArr = str.toCharArray();
        char[] vowelArr = {'a', 'e', 'i', 'o', 'u', ' '};
        int vowelAndSpaceCount = 0;

        for (char c : wordArr) {
            for (char d : vowelArr) {
                if (d == c) {
                    vowelAndSpaceCount++;
                }
            }
        }

        return vowelAndSpaceCount;
    }
}
