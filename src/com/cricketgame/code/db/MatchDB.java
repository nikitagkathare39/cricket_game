package com.cricketgame.code.db;

import java.sql.*;

public class MatchDB {
    public static int matchID;
        static Connection conn = JDBC_Connector.getConnection();

    /*public MatchDB() throws SQLException {
            Statement s = conn.createStatement();
            String query = "SELECT matchID FROM matchDetails ORDER BY matchID DESC LIMIT 1";
            ResultSet r = s.executeQuery(query);
            r.next();
            matchID = r.getInt("matchID")+1;
            System.out.println("TUTUTUTUTUTUTUTUTUTUTUTUTUTUTUTU  "+matchID);
    }*/


    public static void addMatch(int team1, int team2) throws SQLException{

            //JDBC_Connector jc = new JDBC_Connector();
            String venue = "AZ Stadium, Pune";
            //Date date = new Date();
            String query = "INSERT INTO matchDetails(team1ID,team2ID,venue, dateofmatch) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,team1);
            pstmt.setInt(2,team2);
            pstmt.setString(3,venue);
            pstmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            //pstmt.setInt(3,win);
            pstmt.executeUpdate();
            Statement s = conn.createStatement();
            query = "SELECT matchID FROM matchDetails ORDER BY matchID DESC LIMIT 1";
            ResultSet r = s.executeQuery(query);
            r.next();
            matchID = r.getInt("matchID");
            //conn.close();

    }

    public static void updateMatchWinner(int team1, int team2, int winner) throws SQLException{
        String query = "UPDATE matchDetails SET winnerTeamID=? WHERE matchID=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1,winner);
        pstmt.setInt(2,matchID);
        //pstmt.setInt(3,win);
        pstmt.executeUpdate();
    }

    public static int getNoOfMatchesPlayed(int teamID) throws SQLException {
        String q = "SELECT count(*) from matchDetails where team1ID = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1,teamID);
        ResultSet rs = ps.executeQuery();
        int c1=0,c2=0;
        if(rs.next()){
            c1 = rs.getInt(1);
        }
        q = "SELECT count(*) from matchDetails where team2ID = ?";
        ps = conn.prepareStatement(q);
        ps.setInt(1,teamID);
        rs = ps.executeQuery();
        if(rs.next()){
            c2 = rs.getInt(1);
        }
        return c1+c2;
    }
}