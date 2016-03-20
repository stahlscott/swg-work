/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.jsoupstatecapitals.dao;

import com.tsg.jsoupstatecapitals.dto.NameIndexPair;
import com.tsg.jsoupstatecapitals.dto.State;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author apprentice
 */
public class StatesCapitalsDAO {

    private final String PREFIX = "https://en.wikipedia.org";
    private final String URL = PREFIX + "/wiki/List_of_capitals_in_the_United_States";

    private Map<String, State> states = new TreeMap<>();

    public StatesCapitalsDAO() {
        Document doc;

        try {
            doc = Jsoup.connect(URL).get();
            String title = doc.title();
            System.out.println("Title: " + title);

            Elements table = doc.getElementsByClass("sortable");

            ArrayList<Element> trs = table.select("tr");

            int indexOfCapital = 0;
            Map<String, NameIndexPair> indexes = new HashMap<>();
            ArrayList<String> titles = new ArrayList<>();
            titles.add("State");
            titles.add("Abbr.");
            titles.add("Capital");
            titles.add("Municipal population (2010 census)");
            titles.add("Land area (mi²)");

            trs.stream().filter(ele -> ele.select("th").size() > 0)
                    .forEach(e -> {
                        ArrayList<Element> th;
                        th = e.select("th");
                        th.stream().forEach(f -> {
                            titles.stream().forEach(t -> {
                                if (f.text().equals(t)) {
                                    NameIndexPair nameIndexPair = new NameIndexPair();
                                    nameIndexPair.setName(f.text());
                                    nameIndexPair.setIndex(f.elementSiblingIndex());
                                    indexes.put(t, nameIndexPair);
                                }
                            });
                        });
                    });

            trs.stream().filter(ele -> ele.select("td").size() > 0)
                    .forEach(e -> {

                        State state = new State();

                        Element td;
                        String tdData;
                        
                        td = e.select("td").get(indexes.get("State").getIndex());
                        tdData = td.text();
                        state.setName(tdData);

                        td = e.select("td").get(indexes.get("State").getIndex()).select("a").get(0);
                        tdData = td.attr("href");
                        state.setStateUrl(PREFIX + tdData);

                        td = e.select("td").get(indexes.get("Abbr.").getIndex());
                        tdData = td.text();
                        state.setAbbreviation(tdData);

                        td = e.select("td").get(indexes.get("Capital").getIndex());
                        tdData = td.text();
                        state.setCapital(tdData);

                        td = e.select("td").get(indexes.get("Capital").getIndex()).select("a").get(0);
                        tdData = td.attr("href");
                        state.setCapitalUrl(PREFIX + tdData);

                        td = e.select("td").get(indexes.get("Land area (mi²)").getIndex());
                        tdData = td.text();
                        state.setArea(tdData);

                        td = e.select("td").get(indexes.get("Municipal population (2010 census)").getIndex());
                        tdData = td.text();
                        state.setPopulation(tdData);

                        states.put(state.getName(), state);

                    });

        } catch (IOException ex) {
            Logger.getLogger(StatesCapitalsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public Map<String, State> getStates(){
        return states;
    }

    public State getState(String stateName) {

        return states.get(stateName);
    }
}
