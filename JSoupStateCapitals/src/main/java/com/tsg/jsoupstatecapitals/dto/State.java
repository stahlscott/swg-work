/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.jsoupstatecapitals.dto;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class State {

    private String name;
    private String abbreviation;
    private String capital;
    private String area;
    private String population;
    private String stateUrl;
    private String capitalUrl;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.abbreviation);
        hash = 61 * hash + Objects.hashCode(this.capital);
        hash = 61 * hash + Objects.hashCode(this.area);
        hash = 61 * hash + Objects.hashCode(this.population);
        hash = 61 * hash + Objects.hashCode(this.stateUrl);
        hash = 61 * hash + Objects.hashCode(this.capitalUrl);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.abbreviation, other.abbreviation)) {
            return false;
        }
        if (!Objects.equals(this.capital, other.capital)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.population, other.population)) {
            return false;
        }
        if (!Objects.equals(this.stateUrl, other.stateUrl)) {
            return false;
        }
        if (!Objects.equals(this.capitalUrl, other.capitalUrl)) {
            return false;
        }
        return true;
    }

    

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getStateUrl() {
        return stateUrl;
    }

    public void setStateUrl(String stateUrl) {
        this.stateUrl = stateUrl;
    }

    public String getCapitalUrl() {
        return capitalUrl;
    }

    public void setCapitalUrl(String capitalUrl) {
        this.capitalUrl = capitalUrl;
    }

}
