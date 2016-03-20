/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.baseballleague.dto;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Team {

//    private Integer teamId;
    private String teamName;
    private HashMap<Integer, Player> teamRoster = new HashMap<>();

    /**
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the teamRoster
     */
    public HashMap<Integer, Player> getTeamRoster() {
        return teamRoster;
    }

    /**
     * @param teamRoster the teamRoster to set
     */
    public void setTeamRoster(HashMap<Integer, Player> teamRoster) {
        this.teamRoster = teamRoster;
    }
}
