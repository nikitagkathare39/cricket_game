package com.cricketgame.code.model;

import com.cricketgame.code.testing.PlayerCheck;
import com.cricketgame.code.testing.MatchCheck;
import com.cricketgame.code.testing.ScoreBoardCheck;

import java.sql.SQLException;

public class Test{
    public void startTesting() throws SQLException {

        Team t3 = new Team("MI");
        int noOfBatsmen = 6, noOfBowlers = 5;
        for (int i = 0; i < noOfBatsmen; i++) {
            Player p = new Player();
            p.setPlayerId(i);
            p.setPlayerRole("BATSMAN");
            t3.addPlayers(p);
        }
        System.out.println("Enter details of bowlers");
        for (int i = 0; i < noOfBowlers; i++) {
            Player p = new Player();
            p.setPlayerId(i + 6);
            p.setPlayerRole("BOWLER");
            t3.addPlayers(p);
        }
        Team t4 = new Team("CSK");
        for (int i = 0; i < noOfBatsmen; i++) {
            Player p = new Player();
            p.setPlayerId(i);
            p.setPlayerRole("BATSMAN");
            t4.addPlayers(p);
        }
        for (int i = 0; i < noOfBowlers; i++) {
            Player p = new Player();
            p.setPlayerId(i + 6);
            p.setPlayerRole("BOWLER");
            t4.addPlayers(p);
        }
        MatchController m = new MatchController(t3, t4, 5);
        m.doToss();
        m.start_game();
        PlayerCheck pc = new PlayerCheck();
        pc.checkPlayerDetails();
        MatchCheck mc = new MatchCheck();
        mc.checkScores(m,t3,t4);
        ScoreBoardCheck sc = new ScoreBoardCheck();
        sc.scoreBoardCheck();
    }
}