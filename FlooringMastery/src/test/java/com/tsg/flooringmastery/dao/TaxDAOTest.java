/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmastery.dao;

import com.tsg.flooringmastery.dto.Tax;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class TaxDAOTest {
    
    public TaxDAOTest() {      
    }
    
        TaxDAO taxDAO;
        Tax OH;
        Tax PA;
        Tax MI;
        Tax IN;
    
    
    @Before
    public void setUp() {
        taxDAO = new TaxDAO();
        
        OH = new Tax();
        OH.setState("OH");
        OH.setRate(6.25);
        
        PA =  new Tax();
        PA.setState("PA");
        PA.setRate(6.75);
        
        MI = new Tax();
        MI.setState("MI");
        MI.setRate(5.75);
        
        IN = new Tax();
        IN.setState("IN");
        IN.setRate(6.00);
            
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void TaxDAOLoadTest(){
        try {
            taxDAO.loadTaxFile();
        } catch (FileNotFoundException ex) {
            fail();
        }
    }
    
    // TODO: allow overloaded constructor to set taxfile.txt
    @Test
    public void TaxDAOGetTaxRateByStateTest(){
        try {
            taxDAO.loadTaxFile();
        } catch (FileNotFoundException ex) {
            fail();
        }
        
        Double result = taxDAO.getTaxRateByState("OH");
        assertEquals(OH.getRate(), result,0);   
        
        // this works as well.
        // Double result = taxDAO.getTaxRateByState("OH");
        // assertEquals(6.25, result,0);
    }
    
    @Test
    public void TaxDAOGetTaxesTest(){
          try {
            taxDAO.loadTaxFile();
        } catch (FileNotFoundException ex) {
            fail();
        }
        
      ArrayList<Tax> taxes = taxDAO.getTaxes();
      ArrayList<Tax> check = new ArrayList<>();
      
      check.add(OH);
      check.add(PA);
      check.add(MI);
      check.add(IN);
      
        for (Tax tax : taxes) {
            assertTrue(check.contains(tax));
            check.remove(tax);
        }
          
    }
}
