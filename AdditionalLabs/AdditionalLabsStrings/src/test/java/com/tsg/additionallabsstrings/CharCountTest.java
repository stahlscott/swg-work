/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.additionallabsstrings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class CharCountTest {

    public CharCountTest() {
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
    
    @Test // Hi everybody -> 4
    public void CharCountTest1() {
        CharCount test = new CharCount();
        int proof = 4;
        int result = test.vowelCount("Hi everybody");
        assertEquals(proof, result);
    }

    @Test // How are you doing? -> 7
    public void CharCountTest2() {
        CharCount test = new CharCount();
        int proof = 7;
        int result = test.vowelCount("How are you doing?");
        assertEquals(proof, result);
    }

    @Test // Syzygy -> 0
    public void CharCountTest3() {
        CharCount test = new CharCount();
        int proof = 0;
        int result = test.vowelCount("syzygy");
        assertEquals(proof, result);
    }
    
    @Test // Hi everybody -> 5
    public void CharSpaceCountTest1() {
        CharCount test = new CharCount();
        int proof = 5;
        int result = test.vowelAndSpaceCount("Hi everybody");
        assertEquals(proof, result);
    }

    @Test // How are you doing? -> 10
    public void CharSpaceCountTest2() {
        CharCount test = new CharCount();
        int proof = 10;
        int result = test.vowelAndSpaceCount("How are you doing?");
        assertEquals(proof, result);
    }

    @Test // Syzygy -> 0
    public void CharSpaceCountTest3() {
        CharCount test = new CharCount();
        int proof = 0;
        int result = test.vowelAndSpaceCount("syzygy");
        assertEquals(proof, result);
    }
}
