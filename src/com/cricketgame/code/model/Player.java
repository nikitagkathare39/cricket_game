package com.cricketgame.code.model;

import com.cricketgame.code.db.JDBC_Connector;
import com.cricketgame.db.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Player {
    private int playerId;
    private String playerName;
    private int playerAge;
    private String playerRole;
    private int playerScore;
    private int currBowlerRuns;
    private int wicketsTakenByCurrBowler;
    private int playerDBID;

    public Player() {

    }

    public Player(int id, String name, int age, String role) {
        this.playerId = id;
        this.playerName = name;
        this.playerAge = age;
        this.playerRole = role;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public void setAge(int age) {
        this.playerAge = age;
    }

    public void setPlayerRole(String role) {
        this.playerRole = role;
    }

    public void setPlayerScore(int score) {
        this.playerScore = score;
    }

    public int getPlayerScore() {
        return this.playerScore;
    }

    public void setRunsScoredUnderCurrBowler(int runs) {
        this.currBowlerRuns = runs;
    }

    public int getRunsScoredUnderCurrBowler() {
        return this.currBowlerRuns;
    }

    public void setWicketsTakenByCurrBowler(int wickets) {
        this.wicketsTakenByCurrBowler = wickets;
    }

    public int getWicketsTakenByCurrBowler() {
        return this.wicketsTakenByCurrBowler;
    }

    public static int getPlayerDBID(int teamID, int i) throws SQLException {
        String query = "SELECT playerID from PlayerTeamMapping WHERE teamID=? ORDER BY playerID LIMIT 1";
        PreparedStatement p = JDBC_Connector.getConnection().prepareStatement(query);
        p.setInt(1, teamID);
        //p.setInt(2, i+1);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + i;
        }
        return 0;
    }


    public static String getPlayerDBName(int id) throws SQLException {
        String query = "SELECT playerName from Players WHERE playerID=? LIMIT 1";
        PreparedStatement p = JDBC_Connector.getConnection().prepareStatement(query);
        p.setInt(1, id);
        //p.setInt(2, i+1);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return "";
    }

    public static int getPlayerDBAge(int id) throws SQLException {
        String query = "SELECT playerAge from Players WHERE playerID=? LIMIT 1";
        PreparedStatement p = JDBC_Connector.getConnection().prepareStatement(query);
        p.setInt(1, id);
        //p.setInt(2, i+1);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public static String getPlayerDBRole(int id) throws SQLException {
        String query = "SELECT playerRole from Players WHERE playerID=? LIMIT 1";
        PreparedStatement p = JDBC_Connector.getConnection().prepareStatement(query);
        p.setInt(1, id);
        //p.setInt(2, i+1);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return "";
    }

    public static int getPlayerDBScores(int id) throws SQLException {
        String query = "SELECT runsScored from Players WHERE playerID=? LIMIT 1";
        PreparedStatement p = JDBC_Connector.getConnection().prepareStatement(query);
        p.setInt(1, id);
        //p.setInt(2, i+1);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public static int getPlayerDBBallsPlayed(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM completeScores WHERE batsman_ID = ?";
        PreparedStatement ps = JDBC_Connector.getConnection().prepareStatement(query);
        ps.setInt(1,id);
        ResultSet r = ps.executeQuery();
        r.next();
        return r.getInt(1);
    }

    public static int getPlayerDBWicketsLost(int id) throws SQLException {
        String query = "SELECT wicketsTaken from Players WHERE playerID=? LIMIT 1";
        PreparedStatement p = JDBC_Connector.getConnection().prepareStatement(query);
        p.setInt(1, id);
        //p.setInt(2, i+1);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public static int getPlayerDBBallsBowled(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM completeScores WHERE bowler_ID = ?";
        PreparedStatement ps = JDBC_Connector.getConnection().prepareStatement(query);
        ps.setInt(1,id);
        ResultSet r = ps.executeQuery();
        r.next();
        return r.getInt(1);
    }

    public void setPlayerDBID(int playerDBID) {
        this.playerDBID = playerDBID;
    }

    public void getTotalRunsScoredByPlayer() throws SQLException {
        String query = "SELECT runsScored FROM Players WHERE playerID = ?";
        PreparedStatement ps = JDBC_Connector.getConnection().prepareStatement(query);
        ps.setInt(1, this.playerDBID);
        ResultSet rs = ps.executeQuery(query);
        int r = rs.getInt(1);
        rs.close();
        query = "UPDATE Players SET runsScored=? WHERE playerID = ?";
        ps.setInt(1, r + getPlayerScore());
        ps.setInt(2, this.playerDBID);
        ps.executeUpdate(query);
    }
}


