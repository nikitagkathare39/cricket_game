package com.cricketgame.code.db;

import com.cricketgame.code.model.CricketGame;
import com.cricketgame.code.model.MatchController;
import com.cricketgame.code.model.Over;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamTwoCompleteScores {
    public static void addAllScoresToDB() throws SQLException {

                //JDBC_Connector jc = new JDBC_Connector();
                Connection conn = JDBC_Connector.getConnection();
                MatchController mc = new MatchController();
                int max_over = Over.getMaxOvers();
                String query;
                PreparedStatement ps;

                for (int i = 0; i < max_over; i++) {
                    for (int j = 0; j < 6; j++) {
                        query = "INSERT INTO completeScores(matchID, teamID, over_no, ball_no, batsman_ID, batsman_name, bowler_ID, bowler_name, score) VALUES(?,?,?,?,?,?,?,?,?)";
                        ps = conn.prepareStatement(query);
                        ps.setInt(1,MatchDB.matchID);
                        ps.setInt(2, CricketGame.team2ID);
                        ps.setInt(3, i + 1);
                        ps.setInt(4, j + 1);
                        ps.setInt(5, MatchController.overObj2.get(i).getStrikerIDForThisBallInThisOver(j));
                        ps.setString(6, MatchController.overObj2.get(i).getStrikerForThisBallInThisOver(j));
                        ps.setInt(7, MatchController.overObj2.get(i).getBowlerIDForThisBallInThisOver(j));
                        ps.setString(8, MatchController.overObj2.get(i).getBowlerForThisBallInThisOver(j));
                        ps.setInt(9, MatchController.overObj2.get(i).getRunsScoredInThisParticularBallInThisOver(j));
                        ps.executeUpdate();
                    }
                }
                //conn.close();
            }


    }

