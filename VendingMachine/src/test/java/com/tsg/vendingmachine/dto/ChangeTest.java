/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachine.dto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ChangeTest {

    public ChangeTest() {
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
    public void ChangeTestTotal1() {
        Change change = new Change();
        change.addToTotal(.99);
        double proof = .99;
        double result = change.getTotal();
        assertEquals(proof, result, 0);
    }

    @Test // .99 -> 3 quarters returned
    public void ChangeTestQuarters1() {
        Change change = new Change();
        change.addToTotal(.99);
        int proof = 3;
        int result = change.getQuarters();
        assertEquals(proof, result);
    }
    
    @Test // .00 -> 0 quarters returned
    public void ChangeTestQuarters2() {
        Change change = new Change();
        change.addToTotal(.00);
        int proof = 0;
        int result = change.getQuarters();
        assertEquals(proof, result);
    }

    @Test // .99 -> 2 dimes returned
    public void ChangeTestDimes1() {
        Change change = new Change();
        change.addToTotal(.99);
        int proof = 2;
        int result = change.getDimes();
        assertEquals(proof, result);
    }

    @Test // .68 -> 2 dimes returned
    public void ChangeTestDimes2() {
        Change change = new Change();
        change.addToTotal(.68);
        int proof = 1;
        int result = change.getDimes();
        assertEquals(proof, result);
    }

    @Test // .00 -> 0 dimes returned
    public void ChangeTestDimes3() {
        Change change = new Change();
        change.addToTotal(.00);
        int proof = 0;
        int result = change.getDimes();
        assertEquals(proof, result);
    }

    @Test // .94 -> 1 nickel returned
    public void ChangeTestNickels1() {
        Change change = new Change();
        change.addToTotal(.94);
        int proof = 1;
        int result = change.getNickels();
        assertEquals(proof, result);
    }
    
    

    @Test // .00 -> 0 nickel returned
    public void ChangeTestNickels2() {
        Change change = new Change();
        change.addToTotal(.00);
        int proof = 0;
        int result = change.getNickels();
        assertEquals(proof, result);
    }

    @Test // .99 -> 4 pennies returned
    public void ChangeTestPennies1() {
        Change change = new Change();
        change.addToTotal(.99);
        int proof = 4;
        int result = change.getPennies();
        assertEquals(proof, result);
    }

    @Test // .00 -> 0 pennies returned
    public void ChangeTestPennies2() {
        Change change = new Change();
        change.addToTotal(.00);
        int proof = 0;
        int result = change.getPennies();
        assertEquals(proof, result);
    }
}
