package com.team3.forcemajeure.jswing.controller;

import com.team3.forcemajeure.jswing.view.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameController implements ActionListener {

    private final GameFrame gameFrame;

    public FrameController(GameFrame pView) {
        gameFrame = pView;
    }
    public void actionPerformed(ActionEvent event) {

        String yourChoice = event.getActionCommand();

        switch (gameFrame.position) {
            case "dock":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.talkInstructor();
                        break;
                    case "c4":
                        gameFrame.showMap("dock");
                        break;
                }
                break;
            case "map":
                switch (yourChoice){
                    case "c4": gameFrame.setTexts(gameFrame.getCurrentRoom(), gameFrame.getMainText(), gameFrame.getFirstChoice(),gameFrame.getSecondChoice(), gameFrame.getThirdChoice(), gameFrame.getFourthChoice());// get previous method of scene
                        break;
                }
                break;
            case "talkInstructor":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.dock();
                        break;
                    case "c2":
                        gameFrame.lobby();
                        break;
                    case "c3":
                        if(gameFrame.inventory.contains("Key")){
                            gameFrame.ending();
                        } else {
                            gameFrame.choice3.setContentAreaFilled(false);
                        }
                        break;
                    case "c4":
                        gameFrame.showMap("talkInstructor");
                        break;
                }
                break;
            case "lobby":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.hall();
                        break;
                    case "c2":
                        gameFrame.talkInstructor();
                        break;
                    case "c4":
                        gameFrame.showMap("lobby");
                        break;
                }
                break;
            case "hall":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.lobby();
                        break;
                    case "c2":
                        gameFrame.restaurant();
                        break;
                    case "c4":
                        gameFrame.showMap("hall");
                        break;
                }
                break;
            case "restaurant":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.hall();
                        break;
                    case "c2":
                        gameFrame.gameFloor();
                        break;

                    case "c4":
                        gameFrame.showMap("restaurant");
                        break;
                }
                break;
            case "gameFloor":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.theater();
                        break;
                    case "c2":
                        gameFrame.restaurant();
                        break;
                    case "c3":
                        gameFrame.blackJackStart();
                        break;
                    case "c4":
                        gameFrame.showMap("gameFloor");
                        break;
                }
                break;
            case "blackjackstart":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.blackjackDeal();
                        gameFrame.blackJackRound();
                        break;
                    case "c2":
                        gameFrame.gameFloor();
                        break;
                }
                break;
            case "blackjackfirsthand":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.hitMe();
                        gameFrame.blackJackRound();
                        break;
                    case "c2":
                        gameFrame.checkCards();
                        break;
                }
                break;
            case "checkcards":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.gameFloor();
                        break;
                }
                break;
            case "theater":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.gameFloor();
                        break;
                    case "c2": //if beaten 21
                        gameFrame.magicQuizAsk();
                        break;
                    case "c4": gameFrame.showMap("theater");
                        break;
                }
                break;
            case "magicQuizAsk":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.magicQuestionOne();
                        break;
                    case "c2":
                        gameFrame.theater();
                        break;
                }
                break;
            case "magicQuestionOne":
                switch (yourChoice) {
                    case "c1": case "c3": case "c4":
                        gameFrame.wrongAnswer();
                        gameFrame.magicQuestionTwo();
                        break;
                    case "c2":
                        gameFrame.correctAnswer();
                        gameFrame.magicQuestionTwo();
                        break;

                }
                break;
            case "magicQuestionTwo":
                switch (yourChoice) {
                    case "c1": case "c2": case "c3":
                        gameFrame.wrongAnswer();
                        gameFrame.magicQuestionThree();
                        break;
                    case "c4":
                        gameFrame.correctAnswer();
                        gameFrame.magicQuestionThree();
                        break;
                }
                break;
            case "magicQuestionThree":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.correctAnswer();
                        gameFrame.magicQuestionFour();
                        break;
                    case "c2": case "c3": case "c4":
                        gameFrame.wrongAnswer();
                        gameFrame.magicQuestionFour();
                        break;
                }
                break;
            case "magicQuestionFour":
                switch (yourChoice) {
                    case "c1": case "c3": case "c4":
                        gameFrame.wrongAnswer();
                        gameFrame.magicQuestionFive();
                        break;
                    case "c2":
                        gameFrame.correctAnswer();
                        gameFrame.magicQuestionFive();
                        break;
                }
                break;
            case "magicQuestionFive":
                switch (yourChoice) {
                    case "c1": case "c2": case "c4":
                        gameFrame.wrongAnswer();
                        gameFrame.magicQuestionEnd();
                        break;
                    case "c3":
                        gameFrame.correctAnswer();
                        gameFrame.magicQuestionEnd();
                        break;

                }
                break;
            case "magicQuestionEnd":
                switch (yourChoice) {
                    case "c1":
                        gameFrame.theater();
                        break;
                }
                break;
            case "ending":
                switch(yourChoice){
                    case "c1":
                        gameFrame.scoreBoard();
                        break;
                }
        }
    }
}
