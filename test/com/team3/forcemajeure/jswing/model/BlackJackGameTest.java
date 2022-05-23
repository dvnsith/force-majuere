package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.util.*;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

 class BlackJackGameTest {

     SetUp setUp = new SetUp();
    BlackJackGame blackJackGame = new BlackJackGame();
    GameFrame gameFrame = new GameFrame();

    @BeforeEach
    void setUp(){
        GameFrame.getInstance();
        gameFrame.createGameScreen();
    }


    @Test
    public void assertTrueSetPlayerHandLessThanTestCardShowsTrue() throws Exception {
        int testCard = 17;
        blackJackGame.setPlayerHand(15);
        assertTrue(testCard > blackJackGame.getPlayerHand());
    }
    @Test
    public void assertTrueSetDealerHandMoreThanTestCardShowsTrue() throws Exception {
        int testCard = 17;
        blackJackGame.setDealerHand(21);
        assertTrue(testCard < blackJackGame.getDealerHand());
    }
    @Test
    public void assertTrueCardDealsARandomCardAboveOne() throws Exception {
        int testCard = 0;
        blackJackGame.hitMe();
        assertTrue(testCard < blackJackGame.getCard());
    }
    @Test
    public void assertTrueCardDealsARandomCardBelowTwelve() throws Exception {
        int testCard = 12;
        blackJackGame.hitMe();
        assertTrue(testCard > blackJackGame.getCard());
    }
}