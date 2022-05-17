package com.team3.forcemajeure.jswing.controller;

import com.team3.forcemajeure.jswing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceHandler implements ActionListener {

    private final GameFrame gameFrame;
    private final MagicGame magicGame;
    private final BlackJackGame blackJackGame;
    private final SetUp setUp;

    //Ctor
    public ChoiceHandler(GameFrame view) {
        gameFrame = view;
        magicGame = new MagicGame(view);
        blackJackGame = new BlackJackGame(view);
        setUp = new SetUp(view);
    }

    //Business methods
    public void actionPerformed(ActionEvent event) {

        String yourChoice = event.getActionCommand();

        switch (gameFrame.getCurrentRoom()) {
            case "dock":
                switch (yourChoice) {
                    case "c1":
                        setUp.talkInstructor();
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
                        setUp.dock();
                        break;
                    case "c2":
                        setUp.lobby();
                        break;
                    case "c3":
                        if(gameFrame.inventory.contains("Key")){
                            setUp.ending();
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
                        setUp.hall();
                        break;
                    case "c2":
                        setUp.talkInstructor();
                        break;
                    case "c4":
                        gameFrame.showMap("lobby");
                        break;
                }
                break;
            case "hall":
                switch (yourChoice) {
                    case "c1":
                        setUp.lobby();
                        break;
                    case "c2":
                        setUp.restaurant();
                        break;
                    case "c4":
                        gameFrame.showMap("hall");
                        break;
                }
                break;
            case "restaurant":
                switch (yourChoice) {
                    case "c1":
                        setUp.hall();
                        break;
                    case "c2":
                        setUp.gameFloor();
                        break;

                    case "c4":
                        gameFrame.showMap("restaurant");
                        break;
                }
                break;
            case "gameFloor":
                switch (yourChoice) {
                    case "c1":
                        setUp.theater();
                        break;
                    case "c2":
                        setUp.restaurant();
                        break;
                    case "c3":
                        blackJackGame.blackJackStart();
                        break;
                    case "c4":
                        gameFrame.showMap("gameFloor");
                        break;
                }
                break;
            case "blackjackstart":
                switch (yourChoice) {
                    case "c1":
                        blackJackGame.blackjackDeal();
                        blackJackGame.blackJackRound();
                        break;
                    case "c2":
                        setUp.gameFloor();
                        break;
                }
                break;
            case "blackjackfirsthand":
                switch (yourChoice) {
                    case "c1":
                        blackJackGame.hitMe();
                        blackJackGame.blackJackRound();
                        break;
                    case "c2":
                        blackJackGame.checkCards();
                        break;
                }
                break;
            case "checkcards":
                switch (yourChoice) {
                    case "c1":
                        setUp.gameFloor();
                        break;
                }
                break;
            case "theater":
                switch (yourChoice) {
                    case "c1":
                        setUp.gameFloor();
                        break;
                    case "c2": //if beaten 21
                        magicGame.magicQuizAsk();
                        break;
                    case "c4": gameFrame.showMap("theater");
                        break;
                }
                break;
            case "magicQuizAsk":
                switch (yourChoice) {
                    case "c1":
                        magicGame.magicQuestionOne();
                        break;
                    case "c2":
                        setUp.theater();
                        break;
                }
                break;
            case "magicQuestionOne":
                switch (yourChoice) {
                    case "c1": case "c3": case "c4":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionTwo();
                        break;
                    case "c2":
                        magicGame.correctAnswerEasy();
                        magicGame.magicQuestionTwo();
                        break;

                }
                break;
            case "magicQuestionTwo":
                switch (yourChoice) {
                    case "c1": case "c2": case "c3":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionThree();
                        break;
                    case "c4":
                        magicGame.correctAnswerEasy();
                        magicGame.magicQuestionThree();
                        break;
                }
                break;
            case "magicQuestionThree":
                switch (yourChoice) {
                    case "c1":
                        magicGame.correctAnswerEasy();
                        magicGame.magicQuestionFour();
                        break;
                    case "c2": case "c3": case "c4":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionFour();
                        break;
                }
                break;
            case "magicQuestionFour":
                switch (yourChoice) {
                    case "c1": case "c3": case "c4":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionFive();
                        break;
                    case "c2":
                        magicGame.correctAnswerEasy();
                        magicGame.magicQuestionFive();
                        break;
                }
                break;
            case "magicQuestionFive":
                switch (yourChoice) {
                    case "c1": case "c2": case "c4":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionEnd();
                        break;
                    case "c3":
                        magicGame.correctAnswerEasy();
                        magicGame.magicQuestionEnd();
                        break;

                }
                break;
            case "magicQuestionEnd":
                switch (yourChoice) {
                    case "c1":
                        setUp.theater();
                        break;
                }
                break;
            case "ending":
                switch(yourChoice){
                    case "c1":
                        setUp.scoreBoard();
                        break;
                }
        }
    }
}
