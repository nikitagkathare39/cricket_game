package com.cricketgame.code.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players = new ArrayList<Player>();
    private int score;
    private int wicket;

    public Team(String n){
        this.name = n;
    }

    public void setScore(int score){
        this.score = score;
    }

    public String getName()
    {
        return name;
    }

    public List<Player> getPlayerDetails(){
        return players;
    }

    void addPlayers(Player player){
        if(players.size()>11){
            System.out.println("Number of cricket players can't exceed 11. Maximum 11 players can be in a cricket team!");
        }
        else
        {
            players.add(player);
        }
    }

    public int getScore()
    {
        return this.score;
    }

    void setWickets(int noOfWicket)
    {
        this.wicket = noOfWicket;
    }

    public int getWicketLost()
    {
        return this.wicket;
    }
}
