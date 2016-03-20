package com.tsg.additionallabsstrings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class StringReverseTest {

    public StringReverseTest() {
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
    @Test // Hello -> olleH
    public void StringReverseTest1() {
        StringReverse test = new StringReverse();
        String proof = "olleH";
        String result = test.stringReverse("Hello");
        assertEquals(proof, result);
    }

    @Test // a -> a
    public void StringReverseTest2() {
        StringReverse test = new StringReverse();
        String proof = "a";
        String result = test.stringReverse("a");
        assertEquals(proof, result);
    }

    @Test // rettelneve -> evenletter
    public void StringReverseTest3() {
        StringReverse test = new StringReverse();
        String proof = "evenletter";
        String result = test.stringReverse("rettelneve");
        assertEquals(proof, result);
    }

    @Test // Hello -> olleH
    public void StringReverseTestAlt1() {
        StringReverse test = new StringReverse();
        String proof = "olleH";
        String result = test.stringReverseAlt("Hello");
        assertEquals(proof, result);
    }

    @Test // a -> a
    public void StringReverseTestAlt2() {
        StringReverse test = new StringReverse();
        String proof = "a";
        String result = test.stringReverseAlt("a");
        assertEquals(proof, result);
    }

    @Test // rettelneve -> evenletter
    public void StringReverseTestAlt3() {
        StringReverse test = new StringReverse();
        String proof = "evenletter";
        String result = test.stringReverseAlt("rettelneve");
        assertEquals(proof, result);
    }
}
