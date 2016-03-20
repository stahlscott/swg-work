/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.baseballleague.dto;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Player {

    private Integer playerId;
    private String firstName;
    private String lastName;
    private String teamName;
    private Integer jerseyNumber;
    private String position;

    /**
     * @return the playerIdNumber
     */
    public Integer getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerIdNumber to set
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param playerName the firstName to set
     */
    public void setFirstName(String playerName) {
        this.firstName = playerName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the jerseyNumber
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the jerseyNumber to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the jerseyNumber
     */
    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    /**
     * @param jerseyNumber the jerseyNumber to set
     */
    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

}
