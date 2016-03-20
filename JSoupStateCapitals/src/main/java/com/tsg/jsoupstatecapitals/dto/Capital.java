/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.jsoupstatecapitals.dto;

/**
 *
 * @author apprentice
 */
public class Capital {
    
    private String name;
    private String area;
    private String population;
    private String mayor;
    private String elevation;
    private String dateIncorporated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMayor() {
        return mayor;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getDateIncorporated() {
        return dateIncorporated;
    }

    public void setDateIncorporated(String dateIncorporated) {
        this.dateIncorporated = dateIncorporated;
    }
    
}
