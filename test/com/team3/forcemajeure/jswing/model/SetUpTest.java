package com.team3.forcemajeure.jswing.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SetUpTest {

    GameFrame gameFrame = new GameFrame();
    SetUp setUp = new SetUp(gameFrame);
    BlackJackGame blackJackGame = new BlackJackGame();
    JavaScriptGame jsGame = new JavaScriptGame();

    @BeforeEach
    void setUp(){
        GameFrame.getInstance();
        gameFrame.createGameScreen();
        setUp.createPanelScene("hall");
    }

    @Test
    void assertEqualCanEnterTheaterWithMagicWord(){
        gameFrame.setBlackjackPlayed(true);
        jsGame.setJsGameDone(true);

    }
    @Test
    void assertEqualCanSetTextForHallWhenCreatePanelIsCalled(){
        String response = gameFrame.getCurrentRoom();

        assertEquals("hall", response);
    }


}