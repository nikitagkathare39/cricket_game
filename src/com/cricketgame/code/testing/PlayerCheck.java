package com.cricketgame.code.testing;

import com.cricketgame.code.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerCheck {

    @Test
    public void checkPlayerDetails(){
        Player player1 = new Player(1,"Sachin",34,"Batsman");
        Player player2 = new Player(2,"Virat",32,"Batsman");
        assertEquals("Sachin",player1.getPlayerName());
        assertEquals(1,player1.getPlayerId());
        assertEquals(34,player1.getPlayerAge());
        assertEquals("Batsman",player1.getPlayerRole());
        assertEquals(2,player2.getPlayerId());
        assertEquals("Virat",player2.getPlayerName());
        assertEquals(32,player2.getPlayerAge());
        assertEquals("Batsman",player2.getPlayerRole());
    }

}