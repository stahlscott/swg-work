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
public class MorseCodeMakerTest {

    public MorseCodeMakerTest() {
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
    @Test // zap -> "--.. .- .--. "
    public void MorseMakerTest1() {
        MorseCodeMaker test = new MorseCodeMaker();
        String proof = "--.. .- .--. ";
        String result = test.morseCodeConvString("zap");
        assertEquals(proof, result);
    }

    @Test // wilco -> ".-- .. .-.. -.-. --- "
    public void MorseMakerTest2() {
        MorseCodeMaker test = new MorseCodeMaker();
        String proof = ".-- .. .-.. -.-. --- ";
        String result = test.morseCodeConvString("wilco");
        assertEquals(proof, result);
    }

    @Test // javascript -> ".--- .- ...- .- ... -.-. .-. .. .--. - "
    public void MorseMakerTest3() {
        MorseCodeMaker test = new MorseCodeMaker();
        String proof = ".--- .- ...- .- ... -.-. .-. .. .--. - ";
        String result = test.morseCodeConvString("javascript");
        assertEquals(proof, result);
    }
}
