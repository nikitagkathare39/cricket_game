package com.cricketgame.code.db;

import com.cricketgame.code.model.CricketGame;
import com.cricketgame.code.model.MatchController;
import com.cricketgame.code.model.Over;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TeamOneCompleteScores {
    public static void addAllScoresToDB() throws Exception{
            //JDBC_Connector jc = new JDBC_Connector();

            Connection conn = JDBC_Connector.getConnection();
            int max_over = Over.getMaxOvers();

            PreparedStatement ps;

            int i, j;
            for (i = 0; i < max_over; i++) {
                for (j = 0; j < 6; j++) {

                    String query = "INSERT INTO completeScores(matchID, teamID, over_no, ball_no, batsman_ID, batsman_name, bowler_ID, bowler_name, score) VALUES(?,?,?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, MatchDB.matchID);
                    ps.setInt(2, CricketGame.team1ID);
                    ps.setInt(3, i + 1);
                    ps.setInt(4, j + 1);
                    ps.setInt(5, MatchController.overObj1.get(i).getStrikerIDForThisBallInThisOver(j));
                    ps.setString(6, MatchController.overObj1.get(i).getStrikerForThisBallInThisOver(j));
                    ps.setInt(7, MatchController.overObj1.get(i).getBowlerIDForThisBallInThisOver(j));
                    ps.setString(8, MatchController.overObj1.get(i).getBowlerForThisBallInThisOver(j));
                    ps.setInt(9, MatchController.overObj1.get(i).getRunsScoredInThisParticularBallInThisOver(j));
                    ps.executeUpdate();
                }
            }

            //conn.close();
    }
}
