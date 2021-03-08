package com.cricketgame.code;

public class Player {
    private int playerId;
    private String playerName;
    private int playerAge;
    private String playerRole;
    private int playerScore;
    private int currBowlerRuns;
    private int wicketsTakenByCurrBowler;

    public Player()
    {

    }
    public Player(int id, String name, int age, String role)
    {
        this.playerId = id;
        this.playerName = name;
        this.playerAge = age;
        this.playerRole = role;
    }
    public int getPlayerId(){
        return playerId;
    }

    public String getPlayerName(){
        return playerName;
    }

    public int getPlayerAge(){
        return playerAge;
    }

    public String getPlayerRole(){
        return playerRole;
    }

    public void setPlayerId(int id){
        this.playerId = id;
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }

    public void setAge(int age){
        this.playerAge = age;
    }

    public void setPlayerRole(String role)
    {
        this.playerRole = role;
    }

    public void setPlayerScore(int score){this.playerScore = score;}

    public int getPlayerScore(){return this.playerScore;}

    public void setRunsScoredUnderCurrBowler(int runs)
    {
        this.currBowlerRuns = runs;
    }

    public int getRunsScoredUnderCurrBowler()
    {
        return this.currBowlerRuns;
    }

    public void setWicketsTakenByCurrBowler(int wickets)
    {
        this.wicketsTakenByCurrBowler = wickets;
    }
    public int getWicketsTakenByCurrBowler()
    {
        return this.wicketsTakenByCurrBowler;
    }


}
