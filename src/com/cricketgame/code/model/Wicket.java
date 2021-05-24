package com.cricketgame.code.model;

import java.util.ArrayList;
import java.util.List;

public class Wicket {
    List<Player> batsmen = new ArrayList<>();
    List<Player> bowler = new ArrayList<>();

    public void addBatsmenLostWicket(Player p){
        batsmen.add(p);
    }

    public void addBowlerWhoTookWickets(Player p){
        bowler.add(p);
    }
}
