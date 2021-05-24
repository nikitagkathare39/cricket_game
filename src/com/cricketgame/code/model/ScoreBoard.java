package com.cricketgame.code.model;

import java.sql.*;

import com.cricketgame.code.db.MatchDB;

public class ScoreBoard {

    private Team TeamOne;
    private Team TeamTwo;
    private String winning_team;

    public ScoreBoard(Team TeamOne, Team TeamTwo) {
        this.TeamOne = TeamOne;
        this.TeamTwo = TeamTwo;
    }

    public void score() {
        if (TeamOne.getScore() > TeamTwo.getScore()) {
            winning_team = TeamOne.getName();
        } else if(TeamOne.getScore() < TeamTwo.getScore()){
            winning_team = TeamTwo.getName();
        }
        else{
            winning_team = "DRAW!";
        }
    }

    public String getWinning_team(){
        return this.winning_team;
    }

    public int getRunsScoredByTeamOne() {
        return TeamOne.getScore();
    }

    public int getRunsScoredByTeamTwo() {
        return TeamTwo.getScore();
    }

    public int wicketsDownFirstTeam() {
        return TeamOne.getWicketLost();

    }

    public int wicketDownsSecondTeam() {
        return TeamTwo.getWicketLost();
    }

    public void getMatchDetails() {
        System.out.println("Team 1 - "+TeamOne.getName());
        System.out.println("Team 2 - "+TeamTwo.getName());
    }


    public void addScoreBoardToDB(Connection conn) throws Exception {
        int winnerID;
        String query = "INSERT INTO scoreboard(id, team1,team2,runsScoredByTeam1,runsScoredByTeam2,wicketsLostByTeam1,wicketsLostByTeam2,winner) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement p = conn.prepareStatement(query);
        p.setInt(1,MatchDB.matchID);
        p.setString(2,TeamOne.getName());
        p.setString(3, TeamTwo.getName());
        p.setInt(4,TeamOne.getScore());
        p.setInt(5,TeamTwo.getScore());
        p.setInt(6,TeamOne.getWicketLost());
        p.setInt(7, TeamTwo.getWicketLost());
        p.setString(8,winning_team);
        p.executeUpdate();
        p.close();
        /*query = "SELECT winnerTeamID from matchDetails WHERE matchID='"+ MatchDB.matchID+"'";
        Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet r = s.executeQuery(query);
        if(winning_team.equals(TeamOne.getName())){
            winnerID = CricketGame.team1ID;
        }
        else if(winning_team.equals(TeamTwo.getName())){
            winnerID = CricketGame.team2ID;
        }
        else{
            winnerID = -1;
        }

        //r.next();
        r.updateInt(4,winnerID);
        r.updateRow();
        r.close();
        //conn.close();*/
    }

}