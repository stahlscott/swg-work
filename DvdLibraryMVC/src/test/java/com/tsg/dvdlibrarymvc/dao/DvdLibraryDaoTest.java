/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.dvdlibrarymvc.dao;

import com.tsg.dvdlibrarymvc.dto.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class DvdLibraryDaoTest {

    private DvdLibraryDao dao;
    private Dvd dvd1;
    private Dvd dvd2;
    private Dvd dvd3;
    
    public DvdLibraryDaoTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);
        
        dvd1 = new Dvd();
        dvd1.setTitle("The Godfather");
        dvd1.setReleaseDate("1972");
        dvd1.setMpaaRating("R");
        dvd1.setStudio("Paramount");
        dvd1.setDirector("Francis Ford Coppola");
        dvd1.setReviewerRating("5");
        dvd1.setNotes("Great film");
        
        dvd2 = new Dvd();
        dvd2.setTitle("The Godfather Part II");
        dvd2.setReleaseDate("1974");
        dvd2.setMpaaRating("R");
        dvd2.setStudio("Paramount");
        dvd2.setDirector("Francis Ford Coppola");
        dvd2.setReviewerRating("5");
        dvd2.setNotes("Greater film");
        
        dvd3 = new Dvd();
        dvd3.setTitle("Taxi Driver");
        dvd3.setReleaseDate("1976");
        dvd3.setMpaaRating("R");
        dvd3.setStudio("Columbia");
        dvd3.setDirector("Martin Scorcese");
        dvd3.setReviewerRating("5");
        dvd3.setNotes("Violent! Yikes!");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteDvd() {
        dao.addDvd(dvd1);
        Dvd fromDb = dao.getDvdById(dvd1.getDvdId());
        assertEquals(dvd1, fromDb);
        dao.removeDvd(dvd1.getDvdId());
        assertNull(dao.getDvdById(dvd1.getDvdId()));
    }
    
    @Test
    public void addUpdateDvd() {
        dao.addDvd(dvd1);
        dvd1.setNotes("Actually I like this better than Part II. Hard to decide!");
        dao.updateDvd(dvd1);
        Dvd fromDb = dao.getDvdById(dvd1.getDvdId());
        assertEquals(dvd1, fromDb);
    }
    
    @Test
    public void getAllDvds() {
        dao.addDvd(dvd1);
        dao.addDvd(dvd2);
        dao.addDvd(dvd3);
        
        List<Dvd> dList = dao.getAllDvds();
        assertEquals(3, dList.size());
    }
    
    @Test
    public void searchDvds() {
        dao.addDvd(dvd1);
        dao.addDvd(dvd2);
        dao.addDvd(dvd3);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        
        criteria.put(SearchTerm.STUDIO, "Columbia");
        List<Dvd> dList = dao.searchDvds(criteria);
        assertEquals(1, dList.size());
        assertEquals(dvd3, dList.get(0));
        
        criteria.put(SearchTerm.STUDIO, "Paramount");
        dList = dao.searchDvds(criteria);
        assertEquals(2, dList.size());
        
        criteria.put(SearchTerm.RELEASE_DATE, "1972");
        dList = dao.searchDvds(criteria);
        assertEquals(1, dList.size());
        assertEquals(dvd1, dList.get(0));
        
        criteria.put(SearchTerm.RELEASE_DATE, "1974");
        dList = dao.searchDvds(criteria);
        assertEquals(1, dList.size());
        assertEquals(dvd2, dList.get(0));
        
        criteria.put(SearchTerm.RELEASE_DATE, "1976");
        dList = dao.searchDvds(criteria);
        assertEquals(0, dList.size());
    }
}
