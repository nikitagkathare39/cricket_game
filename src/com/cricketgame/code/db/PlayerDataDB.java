package com.cricketgame.code.db;

import com.cricketgame.code.model.Player;
import com.cricketgame.code.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDataDB {
    public void addBowlerData(char n, int age, int teamID) throws SQLException {
        String query = "INSERT INTO Players(playerName, playerAge, playerRole, matchesPlayed, runsScored, ballsPlayed, wicketsTaken, ballsBowled) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = JDBC_Connector.getConnection().prepareStatement(query);
        //pstmt.setInt(1,i+6);
        pstmt.setString(1, Character.toString(n));
        pstmt.setInt(2, age);
        pstmt.setString(3, "BOWLER");
        pstmt.setInt(4,0);
        pstmt.setInt(5,0);
        pstmt.setInt(6,0);
        pstmt.setInt(7,0);
        pstmt.setInt(8,0);
        pstmt.executeUpdate();

        Statement st = JDBC_Connector.getConnection().createStatement();
        query = "SELECT playerID FROM Players ORDER BY playerID DESC LIMIT 1";
        ResultSet r = st.executeQuery(query);
        r.next();
        int ID = r.getInt("playerID");
        System.out.println("Lolzzzzzzzziiieee "+ID);
        query = "INSERT INTO PlayerTeamMapping VALUES(?,?)";
        PreparedStatement pst = JDBC_Connector.getConnection().prepareStatement(query);
        pst.setInt(1, ID);
        pst.setInt(2, teamID);
        pst.executeUpdate();
    }

    public void addBatsmanData(char n, int age, int teamid) throws SQLException {
        String query = "INSERT INTO Players(playerName, playerAge, playerRole, matchesPlayed, runsScored, ballsPlayed, wicketsTaken, ballsBowled) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = JDBC_Connector.getConnection().prepareStatement(query);
        //pstmt.setInt(1,i+6);
        pstmt.setString(1, Character.toString(n));
        pstmt.setInt(2, age);
        pstmt.setString(3, "BATSMAN");
        pstmt.setInt(4,0);
        pstmt.setInt(5,0);
        pstmt.setInt(6,0);
        pstmt.setInt(7,0);
        pstmt.setInt(8,0);
        pstmt.executeUpdate();

        Statement st = JDBC_Connector.getConnection().createStatement();
        query = "SELECT playerID FROM Players ORDER BY playerID DESC LIMIT 1";
        ResultSet r = st.executeQuery(query);
        r.next();
        int ID = r.getInt("playerID");
        System.out.println("HUHULOLOLOLOLOLOLOLOLOLOLO  "+ID);
        query = "INSERT INTO PlayerTeamMapping VALUES(?,?)";
        PreparedStatement pst = JDBC_Connector.getConnection().prepareStatement(query);
        pst.setInt(1, ID);
        pst.setInt(2, teamid);
        pst.executeUpdate();
    }

    public void updatePlayerDataTeamOne(Team t, int team1ID, int playerID) throws SQLException{
        for(int i=playerID;i<playerID+11;i++) {
            Player p = t.getPlayerDetails().get(i-playerID);
            String query = "UPDATE Players SET matchesPlayed=?,runsScored=?, ballsPlayed = ?, wicketsTaken = ?, ballsBowled = ? WHERE playerID = ?";
            PreparedStatement ps = JDBC_Connector.getConnection().prepareStatement(query);
            ps.setInt(1, MatchDB.getNoOfMatchesPlayed(team1ID) + 1);
            ps.setInt(2, Player.getPlayerDBScores(i)+p.getPlayerScore());
            ps.setInt(3, 0);
            ps.setInt(4,Player.getPlayerDBWicketsLost(i)+p.getWicketsTakenByCurrBowler());
            ps.setInt(5,0);
            ps.setInt(6,i);
            ps.executeUpdate();
        }
        for(int i=playerID;i<playerID+11;i++) {
            Player p = t.getPlayerDetails().get(i-playerID);
            String query = "UPDATE Players SET ballsPlayed = ?, ballsBowled = ? WHERE playerID = ?";
            PreparedStatement ps = JDBC_Connector.getConnection().prepareStatement(query);
            ps.setInt(1, Player.getPlayerDBBallsPlayed(i));
            ps.setInt(2,Player.getPlayerDBBallsBowled(i));
            ps.setInt(3,i);
            ps.executeUpdate();
        }
    }

    public void updatePlayerDataTeamTwo(Team t, int team2ID, int playerID) throws SQLException{
        for(int i=playerID;i<playerID+11;i++) {
            Player p = t.getPlayerDetails().get(i-playerID);
            String query = "UPDATE Players SET matchesPlayed=?,runsScored=?, ballsPlayed = ?, wicketsTaken = ?, ballsBowled = ? WHERE playerID = ?";
            PreparedStatement ps = JDBC_Connector.getConnection().prepareStatement(query);
            ps.setInt(1, MatchDB.getNoOfMatchesPlayed(team2ID) + 1);
            ps.setInt(2, Player.getPlayerDBScores(i)+p.getPlayerScore());
            ps.setInt(3, 0);
            ps.setInt(4,Player.getPlayerDBWicketsLost(i)+p.getWicketsTakenByCurrBowler());
            ps.setInt(5,0);
            ps.setInt(6,i);
            ps.executeUpdate();
        }
        for(int i=playerID;i<playerID+11;i++) {
            Player p = t.getPlayerDetails().get(i-playerID);
            String query = "UPDATE Players SET ballsPlayed = ?, ballsBowled = ? WHERE playerID = ?";
            PreparedStatement ps = JDBC_Connector.getConnection().prepareStatement(query);
            ps.setInt(1, Player.getPlayerDBBallsPlayed(i));
            ps.setInt(2,Player.getPlayerDBBallsBowled(i));
            ps.setInt(3,i);
            ps.executeUpdate();
        }
    }
}
