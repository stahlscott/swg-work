/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.jsoupstatecapitals.dao;

import com.tsg.jsoupstatecapitals.dto.Capital;
import com.tsg.jsoupstatecapitals.dto.State;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author apprentice
 */
public class CapitalsDAO {

    Map<String, Capital> capitalList = new HashMap<String, Capital>();
    Document doc;

    public CapitalsDAO() {

    }

    public void loadCapitalInfo(State state) {

        try {

            Capital capital = new Capital();
            doc = Jsoup.connect(state.getCapitalUrl()).get();

//            Elements table = doc.getElementsByClass("vcard");

            ArrayList<Element> trs = doc.select("tr");
            ArrayList<String> titles = new ArrayList<>();
            
            //even though we have different datafields that correspond to the same type of thing,
            //like "State Capital" and "City and Borough", if it doesn't match the first one
            //but does match the second one, our result list should still only contain the
            //necessary items.
            
            
            //okay, so.... Juneau has City and Borough under Area AND population. Blargh.
//            titles.add("State Capital");  //text for "Population"
//            titles.add("City and Borough");
//            titles.add("Estimate");
            titles.add("Mayor");
//            titles.add("Elevation");
//            titles.add("Incorporated");

            ArrayList<String> capitalDataList = new ArrayList<>();

            trs.stream().filter(ele -> ele.select("th").size() > 0)
                    .forEach(e -> {
                        ArrayList<Element> th;
                        th = e.select("th");

                        th.stream().forEach(f -> {
                            titles.stream().forEach(t -> {

                                if (f.text().contains(t)) {

                                    if (f.siblingElements().size() > 0) {
                                        capitalDataList.add(f.nextElementSibling().text());
                                    }

                                }
                            });
                        });
                    });
            
            //right now only works if the dataList has all of these objects
            
//            capital.setPopulation(capitalDataList.get(3));
            capital.setMayor(capitalDataList.get(0));
//            capital.setElevation(capitalDataList.get(2));
//            capital.setDateIncorporated(capitalDataList.get(0));
            capitalList.put(state.getCapital(),capital);
            

        } catch (IOException ex) {
            Logger.getLogger(CapitalsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Capital getCapital(String capitalName) {
        return capitalList.get(capitalName);
    }
}
