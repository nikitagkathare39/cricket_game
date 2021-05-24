package com.cricketgame.code.model;

import com.cricketgame.code.db.JDBC_Connector;
import com.cricketgame.code.db.MatchDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Over{
    private int runsScoredInThisOver;
    private int wicketsScoredInThisOver;
    private List<Ball> l = new ArrayList<>();
    private List<Integer> balls = new ArrayList<>();
    private static int max_over;
    private int currOver;
    private List<String> strikers = new ArrayList<>();
    private List<Integer> strikerID = new ArrayList<>();
    private List<String> bowlers = new ArrayList<>();
    private List<Integer> bowlersID = new ArrayList<>();
    JDBC_Connector jc;
    Connection conn;
    PreparedStatement ps;


    public Over(int currOver, int maxOvers) throws SQLException {
        this.max_over = maxOvers;
        this.currOver = currOver;
    }

    public static int getMaxOvers(){
        return max_over;
    }

    public void setRunsScoredInThisOver(int runs) {
        this.runsScoredInThisOver = runs;
    }

    public int getRunsScoredInThisOver(){
        return this.runsScoredInThisOver;
    }

    public void setWicketsScoredInThisOver(int wicket){
        this.wicketsScoredInThisOver = wicket;
    }

    public int getWicketsScoredInThisOver(){
        return this.wicketsScoredInThisOver;
    }

    public void addRunsInOverBallList(int runs, Player p, int id, Player currBowler, int bowlerID){
        balls.add(runs);
        strikers.add(p.getPlayerName());
        strikerID.add(p.getPlayerId());
        bowlers.add(currBowler.getPlayerName());
        bowlersID.add(currBowler.getPlayerId());
        System.out.println("DUMMMMMMIIIIIIIIIIIIIIIII   "+currBowler.getPlayerId());
        //l.get(currOver).setRunsScored(runs);
    }


    public int getRunsScoredInThisParticularBallInThisOver(int index){
        try {
            //int p = l.get(currOver).getRunsScored();
            return balls.get(index);
        }catch (Exception e){
            return -1;
        }
    }

    public String getStrikerForThisBallInThisOver(int index){
        try {
            return strikers.get(index);
        }catch(Exception e){}
        return "-";
    }

    public int getStrikerIDForThisBallInThisOver(int index){
            try {
                return strikerID.get(index);
            }catch (Exception e){}
            return 0;
    }

    public int getBowlerIDForThisBallInThisOver(int index){
        try {
            return bowlersID.get(index);
        }catch (Exception e){}
        return 0;
    }

    public String getBowlerForThisBallInThisOver(int index){
        try {
            return bowlers.get(index);
        }catch(Exception e){}
        return "-";
    }

    public void setOverDBConnection(){
        //jc = new JDBC_Connector();
        try {
            conn = JDBC_Connector.getConnection();
            //MatchDB mdb = new MatchDB();

        } catch (Exception e) {
            System.out.println();
        }
        //PreparedStatement ps;
    }
    public void addTeamOneOverDataToDB(List<Over> overObj1, int i) throws SQLException{

            String query = "INSERT INTO overData(matchID, teamID, over_no, runs,wickets) VALUES(?,?,?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, MatchDB.matchID);
                ps.setInt(2, CricketGame.team1ID);
                ps.setInt(3,(i+1));
            ps.setInt(4,overObj1.get(i).getRunsScoredInThisOver());
            ps.setInt(5,overObj1.get(i).getWicketsScoredInThisOver());
            ps.executeUpdate();

        }

        public void addTeamTwoOverDataToDB(List<Over> overObj2, int i) throws SQLException{
            String query = "INSERT INTO overData(matchID, teamID, over_no,runs,wickets) VALUES(?,?,?,?,?)";

                ps = conn.prepareStatement(query);
                ps.setInt(1, MatchDB.matchID);
                ps.setInt(2, CricketGame.team2ID);
                ps.setInt(3,(i+1));
            ps.setInt(4,overObj2.get(i).getRunsScoredInThisOver());
            ps.setInt(5,overObj2.get(i).getWicketsScoredInThisOver());
            ps.executeUpdate();

        }


}
