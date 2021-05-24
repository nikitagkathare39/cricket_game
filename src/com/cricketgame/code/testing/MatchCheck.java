package com.cricketgame.code.testing;

import com.cricketgame.code.model.MatchController;
import com.cricketgame.code.model.Team;
import com.cricketgame.code.model.Over;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchCheck {
    public int getScoresForAllOvers(List<Over> overs) {
        int sum = 0;
        for (Over over : overs) {
            sum += over.getRunsScoredInThisOver();
        }
        return sum;
    }

    public int getWicketsForAllOvers(List<Over> overs) {
        int sum = 0;
        for (Over over : overs) {
            sum += over.getWicketsScoredInThisOver();
        }
        return sum;
    }



    @Test
    public void checkScores(MatchController match, Team t1, Team t2) {
        //Team t1 = new Team("CSK");
        //Team t2 = new Team("MI");
        //MatchController match = new MatchController(t1,t2,6);
        //match.start_game();

        int scoreByFirstTeam;
        int scoreBySecondTeam;

        scoreByFirstTeam = getScoresForAllOvers(match.overObj1);
        scoreBySecondTeam = getScoresForAllOvers(match.overObj2);

        assertEquals(scoreByFirstTeam, match.batting_first_team.getScore());
        assertEquals(scoreBySecondTeam, match.bowling_first_team.getScore());
        assertEquals(match.batting_first_team.getWicketLost(),getWicketsForAllOvers(match.overObj1));
        assertEquals(match.bowling_first_team.getWicketLost(),getWicketsForAllOvers(match.overObj2));


    }
}