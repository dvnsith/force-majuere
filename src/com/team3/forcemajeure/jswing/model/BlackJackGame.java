package com.team3.forcemajeure.jswing.model;


import java.util.concurrent.ThreadLocalRandom;

public class BlackJackGame {
    private GameFrame gameFrame;
    private int dealerHand = 0;
    private int playerHand = 0;
    private int card = 0;


    // ctor
    public BlackJackGame(){}
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

    public int getCard() {
        return card;
    }

    // business methods
    // This checks to see if the player already has five losses, and if so, they cannot play anymore.
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

    // Deals a player 2 random cards between one and eleven, and the dealer gets a hand between thirteen and blackjack
    public void blackjackDeal() {
        setDealerHand(ThreadLocalRandom.current().nextInt(13, 21));
        for (int playerCount = 0; playerCount < 2; playerCount++) {
            card = ThreadLocalRandom.current().nextInt(1, 11);
            setPlayerHand(playerHand += card);
        }
    }

    // Checking to see if the player has busted after each card they ask for
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

    // Deals player another card when they request to hit me
    public void hitMe() {
        card = ThreadLocalRandom.current().nextInt(1, 11);
        setPlayerHand(playerHand += card);
    }

    // End of game logic, the player will either get points with a win, lose with a loss, or if there is a tie, the house automatically wins.
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
