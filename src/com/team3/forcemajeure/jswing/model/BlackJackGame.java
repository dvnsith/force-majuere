package com.team3.forcemajeure.jswing.model;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;

public class BlackJackGame {
    private SetUp setup;
    private GameFrame gameFrame;
    private int dealerHand = 0;
    private int playerHand = 0;
    private int card = 0;
//    private int losses = 0;

    // ctor
    public BlackJackGame(){

    }

    public BlackJackGame(GameFrame view){
        gameFrame = view;
    }

    // accessor methods
    public int getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(int dealerHand) {
        this.dealerHand = dealerHand;
    }

    public int getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(int playerHand) {
        this.playerHand = playerHand;
    }


    // business methods
    public void blackJackStart() {
        setPlayerHand(0);
        setDealerHand(0);
        if (gameFrame.getLosses() < 5) {
            gameFrame.setTexts("blackjackstart", "Do you want to play blackjack?", "Yes", "No", "", "");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);
        } else if (gameFrame.getLosses() >= 5) {
            gameFrame.setTexts("blackjackstart", "I think it's best you lay off the tables for today. You have too many losses", "", "Return to Game Floor", "", "");
            gameFrame.choice1.setVisible(false);
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);


        }
    }

    public void blackjackDeal() {
        setDealerHand(ThreadLocalRandom.current().nextInt(13, 21));
        for (int playerCount = 0; playerCount < 2; playerCount++) {
            card = ThreadLocalRandom.current().nextInt(1, 11);
            setPlayerHand(playerHand += card);
        }
    }

    public void blackJackRound() {
        if (getPlayerHand() < 22) {
            gameFrame.setTexts("blackjackfirsthand", "Here is your hand: " + getPlayerHand(), "Hit me", "Stay", "", "");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);
        } else if (getPlayerHand() > 21) {
            gameFrame.setPlayerPT(gameFrame.getPlayerPT() - 2);
            gameFrame.ptLabelNumber.setText("" + gameFrame.getPlayerPT());
            gameFrame.setLosses(gameFrame.getLosses() + 1);
            gameFrame.setTexts("checkcards", "Your hand: " + getPlayerHand() + "\n You busted! Better luck next time", "Return to Game Floor", "", "", "");
            gameFrame.choice2.setVisible(false);
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);
        }
    }

    public void hitMe() {
        card = ThreadLocalRandom.current().nextInt(1, 11);
        setPlayerHand(playerHand += card);
    }

    public void checkCards() {
        if (getPlayerHand() > getDealerHand()) {
            gameFrame.setBlackjackPlayed(true);
            gameFrame.setPlayerPT(gameFrame.getPlayerPT() + 3);
            gameFrame.ptLabelNumber.setText("" + gameFrame.getPlayerPT());
            gameFrame.setTexts("checkcards", "Dealers Hand : " + getDealerHand() +" \n It's your lucky day! You get 3 points", "Return to Game Floor", "", "", "");
            gameFrame.choice2.setVisible(false);
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);

        } else if (getPlayerHand() < getDealerHand()) {
            gameFrame.setPlayerPT(gameFrame.getPlayerPT() - 2);
            gameFrame.ptLabelNumber.setText("" + gameFrame.getPlayerPT());
            gameFrame.setLosses(gameFrame.getLosses() + 1);
            gameFrame.setTexts("checkcards", "Dealers Hand : " + getDealerHand() + "\n Better luck next time.", "Return to Game Floor", "", "", "");
            gameFrame.choice2.setVisible(false);
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);
        } else {
            gameFrame.setTexts("checkcards", "Dealers Hand : " + getDealerHand() + "\n Didn't they tell you? The House always wins. We won't take any points off though.", "Return to Game Floor", "", "", "");
        }
    }
}
