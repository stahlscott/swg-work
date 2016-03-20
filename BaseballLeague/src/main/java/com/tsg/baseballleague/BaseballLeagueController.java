/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.baseballleague;

import com.tsg.addressbook.ui.ConsoleIO;
import com.tsg.baseballleague.dao.BaseballLeagueDAO;
import com.tsg.baseballleague.dto.Player;
import com.tsg.baseballleague.dto.Team;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class BaseballLeagueController {

    BaseballLeagueDAO baseballLeagueDAO = new BaseballLeagueDAO();
    ConsoleIO con = new ConsoleIO();

    /**
     * Main run function; loads file, requests user input for menu option, sends to switch statement, once user quits,
     * saves & exits program.
     */
    public void run() {
        try {
            baseballLeagueDAO.loadLeagueRoster();
            con.print("League Roster loaded.");

            int userInput = 0;

            while (userInput != 8) {
                printMenu();
                userInput = con.getInteger("Please choose an option.", 1, 8);
                switch (userInput) {
                    case 1:
                        addTeam();
                        break;
                    case 2:
                        addPlayer();
                        break;
                    case 3:
                        printAllTeams();
                        break;
                    case 4:
                        printAllPlayersOnTeam();
                        break;
                    case 5:
                        tradePlayer();
                        break;
                    case 6:
                        deletePlayer();
                        break;
                    case 7:
                        deleteTeam();
                        break;
                    default:
                        break;
                }
            }

            baseballLeagueDAO.writeLeagueRoster();
            con.print("League Roster successfully saved.");
            con.print("Thank you for using League Roster Manifesto v1.0!!");

        } catch (FileNotFoundException e) {
            con.print("There was an error loading your league roster :(");
        } catch (IOException e) {
            con.print("There was an error writing your league roster :(");
        }
    }

    /**
     * Print main menu of options.
     */
    private void printMenu() {
        con.print("1.) Create A Team");
        con.print("2.) Create A Player");
        con.print("3.) List All Teams");
        con.print("4.) List All Players On Team");
        con.print("5.) Trade A Player");
        con.print("6.) Delete A Player");
        con.print("7.) Delete A Team");
        con.print("8.) Save & Exit");
    }

    /**
     * Asks user for team to add to; verify team exists; if team exists, enter information for player and add player to
     * team.
     */
    private void addPlayer() {
        Player player = new Player();
        Team team = getTeamFromUser("What team are you adding this player to? ");
        if (team != null) {
            con.print(team.getTeamName());
            player.setTeamName(team.getTeamName());
            player.setFirstName(con.getString("First name: "));
            player.setLastName(con.getString("Last name: "));
            player.setPosition(con.getString("Position: "));
            player.setJerseyNumber(con.getInteger("Jersey number(0-99): ", 0, 99));

            baseballLeagueDAO.addPlayer(player);
            con.print("\nPlayer successfully added.\n");
        } else {
            con.print("\nTeam not found.\n");
        }

    }

    /**
     * Asks for last name of player; ensures player exists; displays selected player information, confirms deletion with
     * Y/N prompt, then deletes player.
     *
     */
    private void deletePlayer() {
        Player player = getPlayerFromUser("What is the last name of the player you would like to delete?");
        if (player != null) {
            printPlayerInfo(player);
            String userSure = con.getString("Are you sure? Y/N");
            if (userSure.equalsIgnoreCase("y")) {
                baseballLeagueDAO.deletePlayer(player);
                con.print("\nPlayer deleted.\n");
            }
        } else {
            con.print("\nPlayer delete canceled.\n");
        }
    }

    /**
     * Asks for last name of player; ensures player exists; print selected player information; asks for team to trade
     * to; ensures team exists; calls DAO movePlayer function which deletes player from old team and adds to new team.
     */
    private void tradePlayer() {
        Player player = new Player();
        player = getPlayerFromUser("What is the last name of the player you would like to trade?");
        if (player != null) {
            printPlayerInfo(player);
            Team team = getTeamFromUser("What team would you like to trade this player to?");
            if (team != null) {
                baseballLeagueDAO.movePlayerToNewTeam(team, player);
                con.print("Player successfully traded to the " + team.getTeamName());
            } else {
                con.print("Team not found.");
            }
        } else {
            con.print("Player not found");
        }

    }

    /**
     * Adds new team; ensures team doesn't already exist.
     */
    private void addTeam() {
        String teamName = (con.getString("What is the name of the new team?"));

        boolean errorCheck = false;

        ArrayList<Team> teams = baseballLeagueDAO.getTeamByName(teamName);
        for (Team team1 : teams) {
            if (teamName.equals(team1.getTeamName())) {
                errorCheck = true;
            }
        }

        if (!errorCheck) {
            Team team = new Team();
            team.setTeamName(teamName);

            baseballLeagueDAO.addTeam(team);
            con.print("Team successfully added.\n");
        } else {
            con.print("Team already exists!");
        }
    }

    /**
     * Asks player for team name to delete, prints selected team name, confirms deletion, deletes team.
     */
    private void deleteTeam() {
        Team team = getTeamFromUser("What team would you like to delete?");
        if (team != null) {
            con.print("Selected team: " + team.getTeamName());
            String userSure = con.getString("Are you sure? ALL PLAYERS ON THIS TEAM WILL BE DELETED! Y/N");
            if (userSure.equalsIgnoreCase("y")) {
                baseballLeagueDAO.deleteTeam(team);
                con.print("\nTeam deleted.\n");
            }
        } else {
            con.print("\nTeam delete canceled.\n");
        }
    }

    /**
     * Prints entire list of registered teams in the league.
     */
    private void printAllTeams() {
        con.print("Teams currently registered in the league: \n");
        ArrayList<Team> teams = baseballLeagueDAO.getAllTeams();
        for (Team team : teams) {
            con.print(team.getTeamName());
        }
        con.print("");
    }

    /**
     * prints player's vital information in easy to read format
     *
     * @param player
     */
    private void printPlayerInfo(Player player) {
        con.print("#" + player.getJerseyNumber() + " " + player.getFirstName() + " " + player.getLastName() + ", " + player.getPosition()
                + ", " + player.getTeamName());
    }

    /**
     * Searches for player based on user input; returns player if 1 match found; forces user choice if multiple matches
     * found; returns null if none found.
     *
     * @param prompt
     * @return player or null
     */
    private Player getPlayerFromUser(String prompt) {
        String userPlayerName = con.getString(prompt);

        ArrayList<Player> playerResults = baseballLeagueDAO.getPlayerByLastName(userPlayerName);
        if (playerResults.size() == 1) {
            return playerResults.get(0);
        } else if (playerResults.size() > 1) {
            con.print("\nMore than one entry found!\n");
            for (int i = 0; i < playerResults.size(); i++) {
                Player player = playerResults.get(i);
                con.print((i + 1) + ": ");
                printPlayerInfo(player);
            }
            int userChoice = con.getInteger("Please select from above. ", 1, playerResults.size());
            return playerResults.get(userChoice - 1);
        }
        return null;
    }

    /**
     * Searches for team based on user input; returns team if 1 match found; forces user choice if multiple matches
     * found; returns null if none found.
     *
     * @param prompt
     * @return
     */
    private Team getTeamFromUser(String prompt) {
        String userTeamName = con.getString(prompt);

        ArrayList<Team> teamResults = baseballLeagueDAO.getTeamByName(userTeamName);
        if (teamResults.size() == 1) {
            return teamResults.get(0);
        } else if (teamResults.size() > 1) {
            con.print("\nMore than one entry found!\n");
            for (int i = 0; i < teamResults.size(); i++) {
                Team team = teamResults.get(i);
                con.print((i + 1) + ": " + team.getTeamName());
            }
            int userChoice = con.getInteger("Please select from above. ", 1, teamResults.size());
            return teamResults.get(userChoice - 1);
        }
        return null;
    }

    /**
     * Gets team from user; prints roster for entire team.
     */
    private void printAllPlayersOnTeam() {
        Team team = getTeamFromUser("What team would you like to view?");
        if (team != null) {
            con.print(team.getTeamName());
            Collection<Player> players = baseballLeagueDAO.getAllPlayersOnTeam(team);
            for (Player player : players) {
                printPlayerInfo(player);
            }
        } else {
            con.print("Team not found.");
        }
    }
}
