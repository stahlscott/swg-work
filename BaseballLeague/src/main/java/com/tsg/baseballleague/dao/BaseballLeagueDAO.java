/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.baseballleague.dao;

import com.tsg.baseballleague.dto.Player;
import com.tsg.baseballleague.dto.Team;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class BaseballLeagueDAO {

    private ArrayList<Team> leagueRoster = new ArrayList<>();
    private static final String LEAGUE_FILE = "league_file.txt";
    private static final String TEAM_DELIMITER = "::";
    private static final String PLAYER_DELIMITER = ",,";
    private Integer playerIdIndex;

    /**
     * Adds a new Player to the Team's roster
     *
     * @param player
     */
    public void addPlayer(Player player) {
        player.setPlayerId(playerIdIndex);
        playerIdIndex++;
        for (Team team : leagueRoster) {
            if (team.getTeamName().equals(player.getTeamName())) {
                addPlayerToRoster(team, player);
                break;
            }
        }
    }

    /**
     * Moves a player from one team to another. Copies all player information to a new Player object, deletes the old
     * player, adds the new player to the new team's roster
     *
     * @param newTeam
     * @param player
     */
    public void movePlayerToNewTeam(Team newTeam, Player player) {
        Player newPlayer = new Player();

        newPlayer.setTeamName(newTeam.getTeamName());
        newPlayer.setPlayerId(player.getPlayerId());
        newPlayer.setFirstName(player.getFirstName());
        newPlayer.setLastName(player.getLastName());
        newPlayer.setPosition(player.getPosition());
        newPlayer.setJerseyNumber(player.getJerseyNumber());

        deletePlayer(player);
        addPlayerToRoster(newTeam, newPlayer);
    }

    /**
     * Deletes a player from his/her team.
     *
     * @param player
     */
    public void deletePlayer(Player player) {
        for (Team team : leagueRoster) {
            if (team.getTeamName().equals(player.getTeamName())) {
                removePlayerFromRoster(team, player);
                break;
            }
        }

    }

    /**
     *
     * @param playerId
     * @return Player object
     */
    public Player getPlayerById(Integer playerId) {
        for (Team team : leagueRoster) {
            Collection<Player> players = getAllPlayersOnTeam(team);
            for (Player player : players) {
                if (player.getPlayerId() == playerId) {
                    return player;
                }
            }
        }
        return null;
    }

    /**
     * Returns an ArrayList of Player objects that match a given last name
     *
     * @param playerName
     * @return
     */
    public ArrayList<Player> getPlayerByLastName(String playerName) {
        ArrayList<Player> matchingPlayers = new ArrayList<>();

        for (Team team : leagueRoster) {
            Collection<Player> players = getAllPlayersOnTeam(team);
            for (Player player : players) {
                String playerResultName = player.getLastName().toLowerCase();
                if (playerResultName.length() >= playerName.length()) {
                    if (playerResultName.contains(playerName.toLowerCase())) {
                        matchingPlayers.add(player);
                    }
                }
            }
        }
        return matchingPlayers;
    }

    /**
     *
     * @return ArrayList<Team> of all teams in the league.
     */
    public ArrayList<Team> getAllTeams() {
        return leagueRoster;
    }

    /**
     * Add Player object to Team object's roster
     *
     * @param team
     * @param player
     */
    private void addPlayerToRoster(Team team, Player player) {
        HashMap<Integer, Player> teamRoster = team.getTeamRoster();

        teamRoster.put(player.getPlayerId(), player);
    }

    /**
     * Remove Player object from Team object's roster
     *
     * @param team
     * @param player
     */
    private void removePlayerFromRoster(Team team, Player player) {
        HashMap<Integer, Player> teamRoster = team.getTeamRoster();

        teamRoster.remove(player.getPlayerId());
    }

    /**
     * Returns a Collection<Player> of all players on a given Team's roster
     *
     * @param team
     * @return
     */
    public Collection<Player> getAllPlayersOnTeam(Team team) {
        HashMap<Integer, Player> teamRoster = team.getTeamRoster();

        return teamRoster.values();
    }

    /**
     * Adds a given Team to the league roster
     *
     * @param team
     */
    public void addTeam(Team team) {
        leagueRoster.add(team);
    }

    /**
     * Deletes a given Team from the league roster along with all of its players
     *
     * @param team
     */
    public void deleteTeam(Team team) {
        leagueRoster.remove(team);//Not sure if this will work, may need to remove by [i]
    }

    /**
     * Returns an ArrayList<Team> of all team name keyword matches
     *
     * @param teamName
     * @return
     */
    public ArrayList<Team> getTeamByName(String teamName) {
        ArrayList<Team> matchingTeams = new ArrayList<>();

        for (Team team : leagueRoster) {
            String teamResultName = team.getTeamName().toLowerCase();
            if (teamResultName.length() >= teamName.length()) {
                if (teamResultName.contains(teamName.toLowerCase())) {
                    matchingTeams.add(team);

                }
            }
        }
        return matchingTeams;
    }

    /**
     * Saves file in the format
     * TEAM_NAME::player1Id,,playerFirstName,,playerLastName,,playerPosition,,playerTeamName,,playerJerseyNumber::player2id,,
     * etc
     *
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeLeagueRoster() throws FileNotFoundException, IOException {

        PrintWriter out = new PrintWriter(new FileWriter(LEAGUE_FILE));

        for (Team team : leagueRoster) {
            Collection<Player> teamRoster = getAllPlayersOnTeam(team);
            out.print(team.getTeamName());
            for (Player player : teamRoster) {
                out.print(TEAM_DELIMITER);
                out.print(player.getPlayerId() + PLAYER_DELIMITER
                        + player.getFirstName() + PLAYER_DELIMITER + player.getLastName()
                        + PLAYER_DELIMITER + player.getPosition() + PLAYER_DELIMITER
                        + player.getTeamName() + PLAYER_DELIMITER + player.getJerseyNumber());

            }
            out.println();

        }
        out.flush();
        out.close();
    }

    /**
     * Loads saved league roster
     *
     * @throws FileNotFoundException
     */
    public void loadLeagueRoster() throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(LEAGUE_FILE)));
        this.playerIdIndex = 1;

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();

            String[] teamTokens = currentLine.split(TEAM_DELIMITER);

            Team team = new Team();

            HashMap<Integer, Player> teamRoster = new HashMap<>();

            team.setTeamName(teamTokens[0]);
            for (int i = 1; i < teamTokens.length; i++) {
                String[] playerTokens = teamTokens[i].split(PLAYER_DELIMITER);

                Player player = new Player();
                Integer playerCurrentId = Integer.parseInt(playerTokens[0]);

                player.setPlayerId(playerCurrentId);
                player.setFirstName(playerTokens[1]);
                player.setLastName(playerTokens[2]);
                player.setPosition(playerTokens[3]);
                player.setTeamName(playerTokens[4]);
                player.setJerseyNumber(Integer.parseInt(playerTokens[5]));

                teamRoster.put(playerCurrentId, player);

                if (this.playerIdIndex <= playerCurrentId) {
                    this.playerIdIndex = playerCurrentId + 1;
                }

                team.setTeamRoster(teamRoster);
            }
            this.leagueRoster.add(team);
        }
    }

}
