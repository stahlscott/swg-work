/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.jsoupstatecapitals;

import com.tsg.jsoupstatecapitals.dao.CapitalsDAO;
import com.tsg.jsoupstatecapitals.dao.StatesCapitalsDAO;
import com.tsg.jsoupstatecapitals.dto.Capital;
import com.tsg.jsoupstatecapitals.dto.State;
import java.util.Map;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class StatesCapitalsController {

    StatesCapitalsDAO dao = new StatesCapitalsDAO();

    public void run() {

        //checking each state one at a time, because every page is different!
        printAllStateCapitalInfo();
        
        dao.getStates().values().stream().forEach(state -> {
            printCapitalInfo(state);
        });
        
//        State state = dao.getState("Alabama");
//        printCapitalInfo(state);
//        state = dao.getState("Alaska");
//        printCapitalInfo(state);
//        state = dao.getState("Arizona");
//        printCapitalInfo(state);
//        state = dao.getState("Arkansas");
//        printCapitalInfo(state);
//        state = dao.getState("California");
//        printCapitalInfo(state);
//        state = dao.getState("Colorado");
//        printCapitalInfo(state);
    }

    public void printAllStateCapitalInfo() {

        Map<String, State> states = dao.getStates();

        System.out.println(states.size());
        states.values().stream()
                .forEach(state
                        -> System.out.println(
                                state.getName() + " "
                                + state.getAbbreviation() + " "
                                + state.getCapital() + " "
                                + state.getArea() + " "
                                + state.getPopulation())
                );
    }

    public void printCapitalInfo(State state) {

        CapitalsDAO capitalsDAO = new CapitalsDAO();
        capitalsDAO.loadCapitalInfo(state);
        Capital capital = capitalsDAO.getCapital(state.getCapital());

        System.out.println("");
        System.out.println(state.getCapital());
//        System.out.println("Date incorporated: " + capital.getDateIncorporated());
//        System.out.println("Elevation: " + capital.getElevation());
        System.out.println("Mayor: " + capital.getMayor());
//        System.out.println("Population: " + capital.getPopulation());

        // System.out.println(capital.getName());
    }
}
