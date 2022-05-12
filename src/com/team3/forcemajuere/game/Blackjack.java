package com.team3.forcemajuere.game;

import com.team3.forcemajeure.util.Player;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Blackjack {

    private Player player = new Player();
    private String answer;
    private int dealerHand = 0;
    private int playerHand;
    private int card;
    private int sleepTimer = 1000;

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

    public void promptToPlay() {

        try {
            while (!(player.getLosses() >= 5)) {
                System.out.print("Do you want to play a game of 21? \n");
                Scanner scanner = new Scanner(System.in);
                answer = scanner.nextLine();
                // While you remain under 5 losses and want to play, you still have the option to.
                if (answer.matches("yes|y|YES|Y}")) {
                    System.out.println("Lets play then! \n");
                    gameStart();
                }
                else if (answer.matches("no|n|NO|N}")){
                    break;
                }
            }
                System.out.println("I think it's best you lay off the tables for today.");
                return;
        }
        catch (Exception e) {
                e.printStackTrace();
            }
    }

    // This sets the initial 2 cards for both the player and the dealer
    // Player has the option to ask to hit me, which adds a card to their hand
    public void gameStart(){
        setDealerHand(0);
        setPlayerHand(0);
        setDealerHand(ThreadLocalRandom.current().nextInt(13,21));
        for (int playerCount = 0; playerCount < 2; playerCount++)  {
            card = ThreadLocalRandom.current().nextInt(1,11);
            setPlayerHand(playerHand += card);
        }
        System.out.println("your hand: " + playerHand);
        askForHitMe();
    }

    // Checks to see if the hand is busted or not, if it is, the game is ended with loss
    // If not, asking them if they want another card or not.
    public void askForHitMe() {
        if (getPlayerHand() < 22){
            System.out.print("Do you want another card? \n");
            Scanner scanner = new Scanner(System.in);
            answer = scanner.nextLine();
            if (answer.matches("yes|y|YES|Y}")) {
                hitMe();
            }
            else if (answer.matches("no|n|NO|N}")){
                checkCards();
            }
        }
        else if (getPlayerHand() > 21) {
            player.setPointTotal(player.getPointTotal()-2);
            player.setLosses(player.getLosses()+1);
            System.out.println("You busted! Maybe you'll win another time. \n");
            System.out.println("Your point total: " + player.getPointTotal());
            System.out.println("Your loss total: " + player.getLosses());
        }
    }

    // Adds a card to the player hand
    public void hitMe() {
        card = ThreadLocalRandom.current().nextInt(1,11);
        setPlayerHand(playerHand += card);
        System.out.println("your hand: " + getPlayerHand());
        askForHitMe();
    }

    // This final check will determine if the player has beat the dealer and has remained at 21 max
    // Will add or subtract points accordingly
    public void checkCards() {
        System.out.println("Dealer hand: " + getDealerHand());
        if (getDealerHand() > getPlayerHand()) {
            player.setPointTotal(player.getPointTotal()-2);
            player.setLosses(player.getLosses()+1);
            System.out.println("Better luck next time. \n");
            System.out.println("Your point total: " + player.getPointTotal());
            System.out.println("Your loss total: " + player.getLosses());
        }
        else if (getPlayerHand() > getDealerHand()) {
            System.out.println("Today must be your lucky day. You win this round \n");
            player.setPointTotal(player.getPointTotal()+3);
            System.out.println("Your point total: " + player.getPointTotal());
        }
        else if (getPlayerHand() == getDealerHand()) {
            System.out.println("Hmm... that's not right, we will deal again.");
        }
        promptToPlay();
    }
}
