/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.mostwantedletter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class MostWantedLetterTest {

    public MostWantedLetterTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


    @Test
    public void MostWantedLetterTest() {
        MostWantedLetter tester = new MostWantedLetter();
        assertEquals("l", tester.mostWantedLetter("Hello World!"));
        assertEquals("o", tester.mostWantedLetter("How do you do?"));
        assertEquals("e", tester.mostWantedLetter("One"));
        assertEquals("o", tester.mostWantedLetter("Oops!"));
        assertEquals("a", tester.mostWantedLetter("AAaooo!!!!"));
        assertEquals("a", tester.mostWantedLetter("abe"));
    }
}
