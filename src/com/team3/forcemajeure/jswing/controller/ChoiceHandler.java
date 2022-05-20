package com.team3.forcemajeure.jswing.controller;

import com.team3.forcemajeure.jswing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceHandler implements ActionListener {

    private GameFrame gameFrame;
    private MagicGame magicGame;
    private BlackJackGame blackJackGame;
    private JavaScriptGame javaScriptGame;
    private StackOverflowGame soGame;
    private SetUp setUp;
    private int skips;


    // Ctor
    public ChoiceHandler(){

    }
    public ChoiceHandler(GameFrame view) {
        gameFrame = view;
        magicGame = new MagicGame(view);
        blackJackGame = new BlackJackGame(view);
        setUp = new SetUp(view);
        javaScriptGame = new JavaScriptGame(view);
        soGame = new StackOverflowGame(view);
    }

    // Business methods
    public void actionPerformed(ActionEvent event) {

        String yourChoice = event.getActionCommand();

        switch (gameFrame.position) {
            case "prelude":
            case "sign":
                switch (yourChoice) {
                    case "c1":
                        setUp.dock();
                        break;
                }
                break;
            case "dock":
                switch (yourChoice) {
                    case "c1":
                        setUp.beach();
                        break;
                    case "c2":
                        setUp.sign();
                        break;
                    case "c4":
                        gameFrame.showMap("dock");
                        break;
                }
                break;
            case "map":
                switch (yourChoice) {
                    case "c4": gameFrame.setTexts(gameFrame.getCurrentRoom(), gameFrame.getMainText(), gameFrame.getFirstChoice(),gameFrame.getSecondChoice(), gameFrame.getThirdChoice(), gameFrame.getFourthChoice());// get previous method of scene
                        break;
                }
                break;
            case "rennie":
                switch (yourChoice) {
                    case "c1":
                        setUp.beach();
                        break;
                    case "c2":
                        setUp.miniGame();
                        break;
                    case "c3":
                        if(gameFrame.inventory.contains("Key")){
                            setUp.ending();
                        }
                        break;
                    case "c4":
                        gameFrame.showMap("beach");
                        break;
                }
                break;
            case "beach":
                switch (yourChoice) {
                    case "c1":
                        setUp.lobby();
                        break;
                    case "c2":
                        setUp.dock();
                        break;
                    case "c3":
                        setUp.talkInstructor("rennie");
                        break;
                    case "c4":
                        gameFrame.showMap("beach");
                        break;
                }
                break;
            case "lobby":
                switch (yourChoice) {
                    case "c1":
                        setUp.hall();
                        break;
                    case "c2":
                        setUp.beach();
                        break;
                    case "c3":
                        if(gameFrame.getBlackjackPlayed().equals(true)){
                            setUp.talkInstructor("nelly");
                        }
                        break;
                    case "c4":
                        gameFrame.showMap("lobby");
                        break;
                }
                break;
            case "nelly":
                switch (yourChoice){
                    case "c1":
                        setUp.lobby();
                        break;
                    case "c2":
                        javaScriptGame.jsGameStart();
                        break;
                    case "c4":
                        gameFrame.showMap("lobby");
                        break;
                }
                break;
            case "jsStart":
                switch (yourChoice) {
                    case "c1":
                        javaScriptGame.jsQuestionOne();
                        break;
                    case "c2":
                        setUp.lobby();
                        break;
                }
                break;
            case "jsQuestionOne":
                switch (yourChoice){
                    case "c1":
                        javaScriptGame.correctImagesAnswer();
                        javaScriptGame.jsQuestionTwo();
                        break;
                    case "c2":
                        javaScriptGame.wrongAnswer();
                        javaScriptGame.jsQuestionTwo();
                        break;
                    case "c4":
                        javaScriptGame.skipQuestion();
                        javaScriptGame.jsQuestionTwo();
                        break;
                }
                break;
            case "jsQuestionTwo":
                switch (yourChoice){
                    case "c1":
                        javaScriptGame.wrongAnswer();
                        javaScriptGame.jsQuestionThree();
                        break;
                    case "c2":
                        javaScriptGame.correctAnswer();
                        javaScriptGame.jsQuestionThree();
                        break;
                    case "c4":
                        javaScriptGame.skipQuestion();
                        javaScriptGame.jsQuestionThree();
                        break;
                }
                break;
            case "jsQuestionThree":
                switch (yourChoice){
                    case "c1":
                        javaScriptGame.wrongAnswer();
                        javaScriptGame.jsQuestionFour();
                        break;
                    case "c2":
                        javaScriptGame.correctAnswer();
                        javaScriptGame.jsQuestionFour();
                        break;
                    case "c4":
                        javaScriptGame.skipQuestion();
                        javaScriptGame.jsQuestionFour();
                        break;
                }
                break;
            case "jsQuestionFour":
                switch (yourChoice){
                    case "c1":
                        javaScriptGame.correctAnswer();
                        javaScriptGame.jsQuestionFive();
                        break;
                    case "c2":
                        javaScriptGame.wrongAnswer();
                        javaScriptGame.jsQuestionFive();
                        break;
                    case "c4":
                        javaScriptGame.skipQuestion();
                        javaScriptGame.jsQuestionFive();
                        break;
                }
                break;
            case "jsQuestionFive":
                switch (yourChoice){
                    case "c1":
                        javaScriptGame.wrongAnswer();
                        javaScriptGame.jsEnd();
                        break;
                    case "c2":
                        javaScriptGame.correctImagesAnswer();
                        javaScriptGame.jsEnd();
                        break;
                    case "c4":
                        javaScriptGame.skipQuestion();
                        javaScriptGame.jsEnd();
                        break;
                }
                break;
            case "jsEnd":
                switch (yourChoice){
                    case "c1":
                        setUp.lobby();
                        break;
                }
                break;
            case "jsEnd2":
                switch (yourChoice){
                    case "c1":
                        setUp.lobby();
                        break;
                    case "c2":
                        javaScriptGame.jsGameStart();
                        break;
                }
                break;
            case "jsNoStart":
                switch (yourChoice){
                    case "c1":
                        setUp.gameFloor();
                        break;
                    case "c2":
                        setUp.hall();
                        break;
                }
                break;
            case "hall":
                switch (yourChoice) {
                    case "c1":
                        setUp.restaurant();
                        break;
                    case "c2":
                        setUp.gameFloor();
                        break;
                    case "c3":
                        setUp.lobby();
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
                    case "c3":
                        if (gameFrame.getBlackjackPlayed().equals(true)) {
                            setUp.talkInstructor("karl");
                            break;
                        } else if (gameFrame.getThirdChoice().equals("Order Spaghetti & Pepsi")) {
                            setUp.restaurant();
                            break;
                        }
                    case "c4":
                        gameFrame.showMap("restaurant");
                        break;
                }
                break;
            case "karl":
                switch (yourChoice){
                    case "c1":
                        setUp.restaurant();
                        break;
                    case "c2":
                        soGame.soGameStart();
                        break;
                    case "c4":
                        gameFrame.showMap("restaurant");
                        break;
                }
                break;
            case "soNoStart":
                switch (yourChoice) {
                    case "c1":
                        setUp.gameFloor();
                        break;
                    case "c2":
                        setUp.hall();
                        break;
                    case "c3":
                        setUp.restaurant();
                        break;
                }
                break;
            case "soStart":
                switch (yourChoice) {
                    case "c1":
                        soGame.soQuestionOne();
                        break;
                    case "c2":
                        soGame.ideSet();
                        break;
                    case "c3":
                        setUp.restaurant();
                        break;
                }
                break;
            case "soQuestionOne":
                switch (yourChoice){
                    case "c1": case "c3":
                        soGame.soQuestionTwo();
                        break;
                    case "c2":
                        soGame.correctAnswer();
                        soGame.soQuestionTwo();
                        break;
                    case "c4":
                        soGame.skipQuestion();
                        soGame.soQuestionTwo();
                        break;
                }
                break;
            case "soQuestionTwo":
                switch (yourChoice){
                    case "c1": case "c2":
                        soGame.soQuestionThree();
                        break;
                    case "c3":
                        soGame.correctAnswer();
                        soGame.soQuestionThree();
                        break;
                    case "c4":
                        soGame.skipQuestion();
                        soGame.soQuestionThree();
                        break;
                }
                break;
            case "soQuestionThree":
                switch (yourChoice){
                    case "c1":
                        soGame.correctAnswer();
                        soGame.soQuestionFour();
                        break;
                    case "c2": case "c3":
                        soGame.soQuestionFour();
                        break;
                    case "c4":
                        soGame.skipQuestion();
                        soGame.soQuestionFour();
                        break;
                }
                break;
            case "soQuestionFour":
                switch (yourChoice){
                    case "c1":
                        soGame.correctAnswer();
                        soGame.soQuestionFive();
                        break;
                    case "c2": case "c3":
                        soGame.soQuestionFive();
                        break;
                    case "c4":
                        soGame.skipQuestion();
                        soGame.soQuestionFive();
                        break;
                }
                break;
            case "soQuestionFive":
                switch (yourChoice){
                    case "c2": case "c3":
                        soGame.soEnd();
                        break;
                    case "c1":
                        soGame.correctAnswer();
                        soGame.soEnd();
                        break;
                    case "c4":
                        soGame.skipQuestion();
                        soGame.soEnd();
                        break;
                }
                break;
            case "soEnd":
                switch (yourChoice){
                    case "c1":
                        soGame.joke();
                        break;
                    case "c2":
                        soGame.pepsi();
                        setUp.restaurant();
                        break;
                    case "c3":
                        setUp.restaurant();
                        break;
                }
                break;
            case "joke":
                switch (yourChoice){
                    case "c1":
                        soGame.joke();
                        break;
                    case "c2":
                        setUp.restaurant();
                        break;
                }
                break;
            case "ideSet":
                switch (yourChoice){
                    case "c1":
                        soGame.ideSet();
                        break;
                    case "c2":
                        soGame.soQuestionOne();
                        break;
                    case"c3":
                        setUp.restaurant();
                        break;
                }
                break;
            case "gameFloor":
                switch (yourChoice) {
                    case "c1":
                        setUp.talkInstructor("jay");
                        break;
                    case "c2": //set pre theater method
                        if(gameFrame.getJsGameDone()){
                            setUp.preTheater();
                        } else {
                            setUp.theater();}
                        break;
                    case "c3":
                        setUp.restaurant();
                        break;
                    case "c4":
                        gameFrame.showMap("gameFloor");
                        break;
                }
                break;
            case "jay":
                switch (yourChoice){
                    case "c1":
                        setUp.gameFloor();
                        break;
                    case "c2":
                        blackJackGame.blackJackStart();
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
            case "preTheater":
                switch (yourChoice) {
                    case "c1":
                        setUp.gameFloor();
                        break;
                    case "c2":
                        if(gameFrame.getMagicWordCorrect()){
                            setUp.theater();
                        }
                        break;
                }
                break;
            case "theater":
                switch (yourChoice) {
                    case "c1":
                        setUp.gameFloor();
                        break;
                    case "c2":
                        if (gameFrame.getJsGameDone().equals(true) && gameFrame.getMagicQuizDone().equals(false)){
                            setUp.talkInstructor("chad");
                        }
                        break;
                    case "c4": gameFrame.showMap("theater");
                        break;
                }
                break;
            case "chad":
                switch (yourChoice){
                    case "c1":
                        setUp.theater();
                        break;
                    case "c2":
                        //if beaten 21
                        magicGame.magicQuizAsk();
                        break;
                    case "c4":
                        gameFrame.showMap("theater");
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
                    case "c1": case "c3":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionTwo();
                        break;
                    case "c2":
                        magicGame.correctAnswerEasy();
                        magicGame.magicQuestionTwo();
                        break;
                    case "c4":
                        magicGame.skipQuestion();
                        magicGame.magicQuestionTwo();
                        break;
                }
                break;
            case "magicQuestionTwo":
                switch (yourChoice) {
                    case "c1": case "c3":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionThree();
                        break;
                    case "c2":
                        magicGame.correctAnswerEasy();
                        magicGame.magicQuestionThree();
                        break;
                    case "c4":
                        magicGame.skipQuestion();
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
                    case "c2":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionFour();
                        break;
                    case "c4":
                        magicGame.skipQuestion();
                        magicGame.magicQuestionFour();
                        break;
                }
                break;
            case "magicQuestionFour":
                switch (yourChoice) {
                    case "c1": case "c3":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionFive();
                        break;
                    case "c2":
                        magicGame.correctAnswerHard();
                        magicGame.magicQuestionFive();
                        break;
                    case "c4":
                        magicGame.skipQuestion();
                        magicGame.magicQuestionFive();
                        break;
                }
                break;
            case "magicQuestionFive":
                switch (yourChoice) {
                    case "c1": case "c2":
                        magicGame.wrongAnswer();
                        magicGame.magicQuestionEnd();
                        break;
                    case "c3":
                        magicGame.correctAnswerHard();
                        magicGame.magicQuestionEnd();
                        break;
                    case "c4":
                        magicGame.skipQuestion();
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
                break;
        }
    }
}
