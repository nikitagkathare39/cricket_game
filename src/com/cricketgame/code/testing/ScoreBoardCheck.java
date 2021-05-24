package com.cricketgame.code.testing;

import com.cricketgame.code.model.CricketGame;
import com.cricketgame.code.model.MatchController;
import com.cricketgame.code.model.ScoreBoard;
import com.cricketgame.code.model.*;

import static org.junit.Assert.assertEquals;

public class ScoreBoardCheck {
    public void scoreBoardCheck() {
        CricketGame cg = new CricketGame();
        MatchController mc = new MatchController();
        ScoreBoard sc = new ScoreBoard(cg.t1, cg.t2);
        assertEquals(sc.getWinning_team(),mc.winner);
        assertEquals(sc.getRunsScoredByTeamOne(),cg.t1.getScore());
        assertEquals(sc.getRunsScoredByTeamTwo(),cg.t2.getScore());
        assertEquals(sc.wicketsDownFirstTeam(),cg.t1.getWicketLost());
        assertEquals(sc.wicketDownsSecondTeam(),cg.t2.getWicketLost());
    }
}
