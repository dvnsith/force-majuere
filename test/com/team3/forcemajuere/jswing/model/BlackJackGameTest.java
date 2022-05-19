package com.team3.forcemajuere.jswing.model;

import com.team3.forcemajeure.jswing.model.BlackJackGame;
import com.team3.forcemajeure.jswing.model.GameFrame;
import org.junit.Test;


import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class BlackJackGameTest {

    BlackJackGame blackJackGame = new BlackJackGame();
//    GameFrame gameFrame = new GameFrame();
    public BlackJackGameTest() {}
//    GameFrame gameFrame = new GameFrame();


//    @BeforeEach
//    void setUp(){
//        GameFrame.getInstance();
//        gameFrame.createGameScreen();
//    }


    @Test
    public void setPlayerHandLessThanTestCardShowsTrue() throws Exception {
        int testCard = 17;
        blackJackGame.setPlayerHand(15);
        assertTrue(testCard > blackJackGame.getPlayerHand());
    }
    @Test
    public void setDealerHandMoreThanTestCardShowsTrue() throws Exception {
        int testCard = 17;
        blackJackGame.setDealerHand(21);
        assertTrue(testCard < blackJackGame.getDealerHand());
    }
    @Test
    public void cardDealsARandomCardAboveOne() throws Exception {
        int testCard = 0;
        blackJackGame.hitMe();
        assertTrue(testCard < blackJackGame.getCard());
    }
    @Test
    public void cardDealsARandomCardBelowTwelve() throws Exception {
        int testCard = 12;
        blackJackGame.hitMe();
        assertTrue(testCard > blackJackGame.getCard());
    }
//    @Test
//    public void playerCardLoseShouldAddLoss() throws Exception {
//        blackJackGame.setDealerHand(21);
//        blackJackGame.setPlayerHand(15);
//        blackJackGame.checkCards();
//        assertEquals(1,blackJackGame.getLosses());
//    }
//    @Test
//    public void lossesEqualFiveShouldDenyGame() throws Exception{
//        String lossString = "I think it's best you lay off the tables for today. You have too many losses";
//        blackJackGame.setLosses(5);
//        blackJackGame.blackJackStart();
//    }
//
//    @Test
//    public void lossesUnderFiveShouldAllowGame()throws Exception{
//        String startString = "Do you want to play blackjack?";
//        blackJackGame.setLosses(0);
//        blackJackGame.blackJackStart();
//        assertEquals(startString,gameFrame.getMainText());
//    }

}