/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.dvdlibrarymvc.dao;

import com.tsg.dvdlibrarymvc.dto.Dvd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

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

        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        cleaner.execute("delete from dvd");

        dvd1 = new Dvd();
        dvd1.setTitle("The Godfather");
        dvd1.setReleaseDate("1972-01-01 00:00:00.0");
        dvd1.setMpaaRating("R");
        dvd1.setStudio("Paramount");
        dvd1.setDirector("Francis Ford Coppola");
        dvd1.setReviewerRating("5");
        dvd1.setNotes("Great film");

        dvd2 = new Dvd();
        dvd2.setTitle("The Godfather Part II");
        dvd2.setReleaseDate("1974-01-01 00:00:00.0");
        dvd2.setMpaaRating("R");
        dvd2.setStudio("Paramount");
        dvd2.setDirector("Francis Ford Coppola");
        dvd2.setReviewerRating("5");
        dvd2.setNotes("Greater film");

        dvd3 = new Dvd();
        dvd3.setTitle("Taxi Driver");
        dvd3.setReleaseDate("1976-01-01 00:00:00.0");
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
        dao.addDvd(dvd2);
        dao.addDvd(dvd3);

        Dvd fromDb = dao.getDvdById(dvd1.getDvdId());
        assertEquals(dvd1, fromDb);

        dao.removeDvd(dvd1.getDvdId());
        assertNull(dao.getDvdById(dvd1.getDvdId()));

        dao.addDvd(dvd1);
        
        fromDb = dao.getDvdById(dvd1.getDvdId());
        assertEquals(dvd1, fromDb);

        fromDb = dao.getDvdById(dvd2.getDvdId());
        assertEquals(dvd2, fromDb);

        fromDb = dao.getDvdById(dvd3.getDvdId());
        assertEquals(dvd3, fromDb);

        dao.removeDvd(dvd1.getDvdId());
        dao.removeDvd(dvd2.getDvdId());
        dao.removeDvd(dvd3.getDvdId());

        assertNull(dao.getDvdById(dvd1.getDvdId()));
        assertNull(dao.getDvdById(dvd2.getDvdId()));
        assertNull(dao.getDvdById(dvd3.getDvdId()));
    }

    @Test
    public void addUpdateDvd() {
        dao.addDvd(dvd1);
        dao.addDvd(dvd2);
        dao.addDvd(dvd3);
        
        dvd2.setDirector("F.F.C.");
        dvd2.setNotes("Actually I like this better than Part I. Hard to decide!");
        dao.updateDvd(dvd2);
        Dvd fromDb = dao.getDvdById(dvd2.getDvdId());
        assertEquals(dvd2, fromDb);
    }

    @Test
    public void getAllDvds() {
        dao.addDvd(dvd1);
        dao.addDvd(dvd2);
        dao.addDvd(dvd3);
        
        List<Dvd> dList = dao.getAllDvds();
        assertEquals(3, dList.size());
        assertTrue(dList.contains(dvd1));
        assertTrue(dList.contains(dvd2));
        assertTrue(dList.contains(dvd3));
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
        
        criteria.clear();
        
        criteria.put(SearchTerm.MPAA_RATING, "R");
        dList = dao.searchDvds(criteria);
        assertEquals(3, dList.size());
        
        criteria.put(SearchTerm.REVIEWER_RATING, "5");
        dList = dao.searchDvds(criteria);
        assertEquals(3, dList.size());
        
        criteria.put(SearchTerm.DIRECTOR, "Martin Scorcese");
        dList = dao.searchDvds(criteria);
        assertEquals(1, dList.size());
        assertEquals(dvd3, dList.get(0));
        
        criteria.clear();
        
        criteria.put(SearchTerm.NOTES, "Great film");
        criteria.put(SearchTerm.TITLE, "The Godfather");
        dList = dao.searchDvds(criteria);
        assertEquals(1, dList.size());
        assertEquals(dvd1, dList.get(0));
        
        criteria.clear();
        
        criteria.put(SearchTerm.TITLE, "The Godfather");
        dList = dao.searchDvds(criteria);
        assertEquals(1, dList.size());
        assertEquals(dvd1, dList.get(0));

        criteria.clear();
        
        criteria.put(SearchTerm.RELEASE_DATE, "1974-01-01 00:00:00.0");
        dList = dao.searchDvds(criteria);
        assertEquals(1, dList.size());
        assertEquals(dvd2, dList.get(0));

        criteria.put(SearchTerm.RELEASE_DATE, "xxxx");
        dList = dao.searchDvds(criteria);
        assertEquals(0, dList.size());
    }
}
