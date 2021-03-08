package com.cricketgame.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class MatchController {
    private Team team1;
    private Team team2;
    private int max_over;
    private int toss;
    private Team toss_winning_team = null;
    private Team match_winning_team = null;
    private Team batting_first_team = null;
    private Team bowling_first_team = null;
    int playerId = 0;
    Player batsman1;
    Player batsman2;
    int secondTeamBattingFlag = 0;
    int targetScore = 0;
    private List<Integer> batsmenPlayed;
    public MatchController(Team t1, Team t2, int max_over)
    {
        this.team1 = t1;
        this.team2 = t2;
        this.max_over = max_over;
    }

    public void doToss()
    {
        if(Math.random() < 0.5){
            System.out.println("HEADS");
            toss = 0;
        }
        else{
            System.out.println("TAILS");
            toss = 1;
        }

        if(toss == 0)
        {
            this.toss_winning_team = team1;
            System.out.println("Toss won by Team1");
            System.out.println("Team1 chose to do ");
            if(Math.random() < 0.5){
                System.out.println("Batting");
                this.batting_first_team = team1;
                this.bowling_first_team = team2;
            }
            else{
                System.out.println("Bowling");
                this.bowling_first_team = team1;
                this.batting_first_team = team2;
            }
        }
        else
        {
            this.toss_winning_team = team2;
            System.out.println("Toss won by Team2");
            System.out.println("Team2 chose to do ");
            if(Math.random() < 0.5){
                System.out.println("Batting");
                this.batting_first_team = team2;
                this.bowling_first_team = team1;
            }
            else{
                System.out.println("Bowling");
                this.bowling_first_team = team2;
                this.batting_first_team = team1;
            }
        }
    }

    public int getRuns()
    {
        int randomNum = ThreadLocalRandom.current().nextInt(0,8);
        return randomNum;
    }



    public void battingFirstTeam(Team tbat, Team tbowl, int over){
        Player currBowler = getBowler(tbowl);
        Player striker, non_striker;
        striker = batsman1;
        non_striker = batsman2;
        for(int noOfBalls=0;noOfBalls<6 && tbat.getWicketLost() >= 0 && tbat.getWicketLost() < 10;noOfBalls++)
        {
            int runs = getRuns();
            if(runs < 7){
                System.out.println("Team "+tbat.getName()+" scored "+runs+" runs\n");
                tbat.setScore(tbat.getScore() + runs);
                striker.setPlayerScore(striker.getPlayerScore()+runs);
                currBowler.setRunsScoredUnderCurrBowler(currBowler.getRunsScoredUnderCurrBowler() + runs);
                if(runs%2 == 1)
                {
                    BatsmanWrapper wB1 = new BatsmanWrapper(batsman1);
                    BatsmanWrapper wB2 = new BatsmanWrapper(batsman2);
                    swapBatsmen(wB1,wB2);
                    striker = batsman2;
                    non_striker = batsman1;
                }
            }
            else {
                System.out.println("Team " + tbat.getName() + " lost 1 wicket!\n");
                tbat.setWickets(tbat.getWicketLost()+1);
                currBowler.setWicketsTakenByCurrBowler(currBowler.getWicketsTakenByCurrBowler() + 1);
                batsman1 = non_striker;
                batsman2 = getNextBatsman(tbat);
                playerId++;
            }
            if(secondTeamBattingFlag == 1)
            {
                if( targetScore!= 0 && tbat.getScore() > targetScore)
                {
                    return;
                }
            }
        }
        System.out.println("Over "+(over+1)+"\tRuns "+tbat.getScore()+"\tWickets "+tbat.getWicketLost());
    }

    /*public void battingSecondTeam(Team tbat, Team tbowl, int targetScore, int over){
        Player currBowler = getBowler(tbowl);
        if(tbat.getScore() > targetScore){
            return;
        }
        for(int noOfBalls=0;noOfBalls<6 && tbat.getWicketLost() >=0 && tbat.getWicketLost() <10;noOfBalls++)
        {
            int runs = getRuns();
            if(runs < 7){
                System.out.println("Team "+tbat.getName()+" scored "+runs+" runs\n");
                tbat.setScore(tbat.getScore() + runs);
                if(runs%2 == 1)
                {

                    BatsmanWrapper wB1 = new BatsmanWrapper(batsman1);
                    BatsmanWrapper wB2 = new BatsmanWrapper(batsman2);

                    swapBatsmen(wB1,wB2);
                }
            }
            else {
                System.out.println("Team " + tbat.getName() + " lost 1 wicket!\n");
                tbat.setWickets(tbat.getWicketLost()+1);
                playerId++;
            }
        }
        System.out.println("Over "+(over+1)+"\tRuns "+tbat.getScore()+"\tWickets "+tbat.getWicketLost());

    }*/

    //TODO - Randomly select next Batsman and Bowler and not in sequential manner
    public Player getNextBatsman(Team team)
    {
        while(true)
        {
            int batsmanID = ThreadLocalRandom.current().nextInt(0,11);
            if(batsmenPlayed.isEmpty())
            {
                batsmenPlayed.add(batsmanID);
                Player player = team.getPlayerDetails().get(batsmanID);
                return player;
            }
            else if(!batsmenPlayed.contains(batsmanID))
            {
                batsmenPlayed.add(batsmanID);
                Player player = team.getPlayerDetails().get(batsmanID);
                return player;
            }
        }
    }

    public Player getBowler(Team team)
    {
        while(true)
        {
            int bowlerID = ThreadLocalRandom.current().nextInt(6,11);
            Player player = team.getPlayerDetails().get(bowlerID);
            return player;
        }
    }


    public void start_game(){
        secondTeamBattingFlag = 0;
        batsmenPlayed = new ArrayList<>();
        batsman1 = getNextBatsman(batting_first_team);
        batsman2 = getNextBatsman(batting_first_team);
        batsmenPlayed.add(batsman1.getPlayerId());
        batsmenPlayed.add(batsman2.getPlayerId());
        for(int i=0;i<this.max_over;i++) {
            battingFirstTeam(batting_first_team, bowling_first_team, i);
            BatsmanWrapper wB1 = new BatsmanWrapper(batsman1);
            BatsmanWrapper wB2 = new BatsmanWrapper(batsman2);
            swapBatsmen(wB1, wB2);
        }
        targetScore = batting_first_team.getScore();
        secondTeamBattingFlag = 1;
        playerId = 0;
        batsmenPlayed.clear();
        batsman1 = getNextBatsman(bowling_first_team);
        batsman2 = getNextBatsman(bowling_first_team);
        batsmenPlayed.add(batsman1.getPlayerId());
        batsmenPlayed.add(batsman2.getPlayerId());
        //TODO - Combine 2 functions of batting of first team and second team into 1 single function - Hence commenting out battingSecondTeam function
        for(int i=0;i<this.max_over;i++) {
            battingFirstTeam(bowling_first_team, batting_first_team, i);
            BatsmanWrapper wB1 = new BatsmanWrapper(batsman1);
            BatsmanWrapper wB2 = new BatsmanWrapper(batsman2);
            swapBatsmen(wB1, wB2);
        }
        /*int score = batting_first_team.getScore();
        secondTeamBattingFlag = 1;
        playerId=0;
        for(int i=0;i<this.max_over;i++) {
            battingSecondTeam(bowling_first_team, batting_first_team, score, i);
            BatsmanWrapper wB1 = new BatsmanWrapper(batsman1);
            BatsmanWrapper wB2 = new BatsmanWrapper(batsman2);
            swapBatsmen(wB1,wB2);
        }*/
        if(batting_first_team.getScore() > bowling_first_team.getScore()){
            System.out.println("The winner of the match is "+batting_first_team.getName());
        }
        else if(batting_first_team.getScore() < bowling_first_team.getScore()){
            System.out.println("The winner of the match is "+bowling_first_team.getName());
        }
        else
        {
            System.out.println("The match was a Draw! Better Luck Next Time! =) ");
        }
    }

    class BatsmanWrapper {
        Player p;
        BatsmanWrapper(Player p) {
            this.p = p;
        }
    }

    void swapBatsmen(BatsmanWrapper B1, BatsmanWrapper B2){
        BatsmanWrapper temp = B1;
        B1 = B2;
        B2 = temp;
    }
}

