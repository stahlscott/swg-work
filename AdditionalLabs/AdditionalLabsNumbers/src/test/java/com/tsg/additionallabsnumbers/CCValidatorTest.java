/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.additionallabsnumbers;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class CCValidatorTest {
    
    public CCValidatorTest() {
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
    
    @Test // 79927398713 -> true (from wiki page on Luhn)
    public void LuhnCheckTest1() {
        CCValidator test = new CCValidator();
        boolean result = test.luhnCheck("79927398713");
        assertTrue(result);
    }
    
    // tests also passed successfully on my own CC numbers - I did not save those tests though :)
    // i used http://www.freeformatter.com/credit-card-number-generator-validator.html for following tests
    
    @Test // 341075657622205 -> true 
    public void CCValidatorTest1() {
        CCValidator test = new CCValidator();
        boolean result = test.ccCheck("341075657622205");
        assertTrue(result);
    }
    
    @Test // 5360640519562181 -> true 
    public void CCValidatorTest2() {
        CCValidator test = new CCValidator();
        boolean result = test.ccCheck("5360640519562181");
        assertTrue(result);
    }
    
    @Test // 5360640519562182 -> false 
    public void CCValidatorTest3() {
        CCValidator test = new CCValidator();
        boolean result = test.ccCheck("5360640519562182");
        assertFalse(result);
    }
    
    @Test // 1360640519562182 -> false 
    public void CCValidatorTest4() {
        CCValidator test = new CCValidator();
        boolean result = test.ccCheck("1360640519562182");
        assertFalse(result);
    }
    
    @Test // 4556959325765110 -> true 
    public void CCValidatorTest5() {
        CCValidator test = new CCValidator();
        boolean result = test.ccCheck("6011455521528729");
        assertTrue(result);
    }
}
