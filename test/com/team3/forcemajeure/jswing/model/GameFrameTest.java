package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.jswing.controller.*;
import com.team3.forcemajeure.util.*;
import org.json.simple.*;
import org.junit.jupiter.api.*;
import java.awt.*;
import java.awt.event.*;

import static org.junit.jupiter.api.Assertions.*;

class GameFrameTest {

    SetUp setUp = new SetUp();
    GameFrame gameFrame = new GameFrame();
    BlackJackGame blackJackGame = new BlackJackGame();
    Player player = new Player();


    @BeforeEach
    void setUp(){
        GameFrame.getInstance();
        gameFrame.createGameScreen();
    }

    @Test
    void assertEqualMenuBarFontSetToImpact_Plain_Size20(){
        //set testing variables
        String response = gameFrame.inventoryLabelName.getFont().toString();
        String expected = String.valueOf("java.awt.Font[family=Impact,name=Impact,style=plain,size=20]");

        //check results vs. expected
        assertEquals(expected, response);
    }

    @Test
    void assertEqualInventoryLabelBackgroundColorSetToR15_G56_B67(){
        //set testing variables
        String response = gameFrame.inventoryLabel.getBackground().toString();
        String expected = "java.awt.Color[r=15,g=56,b=67]";

        //check results vs. expected
        assertEquals(expected, response);
        System.out.println("Expected: " + expected+ "\nActual: " + response);
    }

    @Test
    void assertEqualsTrueCurrentRoomSetToPreludeAtGameBeginning(){
        //set testing variables
        String expected = "prelude";
        String result = gameFrame.getCurrentRoom();

        //check results vs. expected
        assertEquals(result, expected);
        System.out.println("Expected: " + expected+ "\nActual: " + result);
    }

    @Test
    void assertEqualsTruePreviousRoomSetToRennieOnceCurrentRoomUpdatedToLobby(){
        /*  current room prelude -> set previous room to prelude -> set current room to beach
            set previous room to beach -> set current room to rennie -> set previous room to rennie ->
            set current room to lobby   */
        gameFrame.setPreviousRoom(gameFrame.getCurrentRoom());
        gameFrame.setCurrentRoom("beach");
        gameFrame.setPreviousRoom(gameFrame.getCurrentRoom());
        gameFrame.setCurrentRoom("rennie");
        gameFrame.setPreviousRoom(gameFrame.getCurrentRoom());
        gameFrame.setCurrentRoom("lobby");

        //set testing variables
        String expected = "rennie";
        String result = gameFrame.getPreviousRoom();

        //check results vs. expected
        assertEquals(result, expected);
        System.out.println("Current: " + "\nExpected: " + expected+ "\nActual: " + result);
    }

    @Test
    void assertNotEqualsPreviousRoomSetToNullAtGameBeginning(){
        //set testing variables
        String expected = null;
        String result = gameFrame.getPreviousRoom();

        //check results vs. expected
        assertNotEquals(null, result);
        System.out.println("Expected: " + null + "\nActual: " + result);
    }

    @Test
    void assertEqualsPlayerAddsItemToInventoryAfterGameScreenCreated() {
        //additional setup + add inventory item
        gameFrame.inventory.add("About $3.50");

        //set testing variables
        String expected = "About $3.50";
        String result = gameFrame.inventory.get(1);

        //check results vs. expected
        System.out.println("Expected: " + expected+ "\nActual: " + result);
        assertEquals(result, expected);

    }

    @Test
    void assertEqualsTrueMainTextSetToPreludeText(){
        //set testing variables
        String expected = "So you've arrived. At your final test. " +
                "You cannot believe it, a year ago you were working multiple jobs from dishwasher to line cook... and now you're here. " +
                "The culmination of your work thus far. " +
                "You take a deep breath and put on the headset.";
        String result = gameFrame.getMainText();

        //check results vs. expected
        assertEquals(expected, result);
        System.out.println("Expected: " + expected + "\nActual: " + result);

    }

    @Test
    void assertNotEqualsPreludeChoiceOneEqualsPreludeChoiceOneValue(){
        //set testing variables
        String unexpected = "Start Simulation";
        String result = gameFrame.getFirstChoice();

        //check results vs. expected
        assertNotEquals(unexpected, result);
        System.out.println("Expected: " + unexpected + "\nActual: " + result);

    }

    @Test
    void assertEqualsPreludeChoiceTwoEqualsPreludeChoiceTwoValue(){
        //set testing variables
        String expected = "Start Simulation";
        String result = gameFrame.getSecondChoice();

        //check results vs. expected
        assertEquals(expected, result);
        System.out.println("Expected: " + expected + "\nActual: " + result);

    }

    @Test
    void assertNotEqualsPreludeChoiceThreeEqualsPreludeChoiceThreeValue(){
        //set testing variables
        String unexpected = "Start Simulation";
        String result = gameFrame.getThirdChoice();

        //check results vs. expected
        assertNotEquals(unexpected, result);
        System.out.println("Expected: " + unexpected + "\nActual: " + result);

    }

    @Test
    void assertEqualsPreludeChoiceFourEqualsPreludeChoiceFourValue(){
        //set testing variables
        String expected = "";
        String result = gameFrame.getFourthChoice();

        //check results vs. expected
        assertEquals(expected, result);
        System.out.println("Expected: " + expected + "\nActual: " + result);

    }
    @Test
    void AssertEqualsPlayerPointValueIncreasesWhenPointsAreAddedInGamePlay(){
        String initial = gameFrame.ptLabelNumber.getText();
        gameFrame.setPlayerPT(12);
        gameFrame.ptLabelNumber.setText("" + gameFrame.getPlayerPT());
        String response = gameFrame.ptLabelNumber.getText();
        String expected = String.valueOf("" + "12");
        assertEquals(expected, response);
        System.out.println("Expected: " + expected + "\nActual: " + response);

    }



}