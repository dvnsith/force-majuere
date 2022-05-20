package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.util.*;

public class MagicGame {
    private GameFrame game;
    private Boolean magicQuizDone = false;
    private Player player = new Player();


    public MagicGame(){}
    // Ctor
    public MagicGame(GameFrame view){
        game = view;
    }

    // Accessor Methods
    public Boolean getMagicQuizDone() {
        return magicQuizDone;
    }

    public void setMagicQuizDone(Boolean magicQuizDone) {
        this.magicQuizDone = magicQuizDone;
    }



    // Business methods
    public void magicQuizAsk() {
        if (!game.getMagicQuizDone()) {
            game.setTexts("magicQuizAsk", "Hello " + game.getPlayer() + ", would you like to answer some questions? ", "Sure!", "No Thanks", "", "");
            game.choice3.setVisible(false);
            game.choice4.setVisible(false);
        }
        else if (game.getMagicQuizDone()) {
            game.setTexts("magicQuizAsk", "It seems like you've already answered my questions. Head to another person to chat", "", "Return to Theater", "", "");
            game.choice1.setVisible(false);
            game.choice3.setVisible(false);
            game.choice4.setVisible(false);
             }
    }
    // For each question we check to make sure that the player still has some skips left.
    public void magicQuestionOne() {
        game.choice4.setVisible(true);
        if (player.getSkips() > 0) {
            game.setTexts("magicQuestionOne", "Question 1: Which of the following is the correct extension of the Python file?", ".python", ".py", ".p", "Skip");
        }
        else if (player.getSkips() >= 3) {
            game.setTexts("magicQuestionOne", "Question 1: Which of the following is the correct extension of the Python file?", ".python", ".py", ".p", "");

        }
    }
    public void magicQuestionTwo() {
        if (player.getSkips() > 0) {
            game.setTexts("magicQuestionTwo", "Question 2: Which character is used in Python to make a single line comment?", "//", "#", "!", "Skip");
        }
        else if (player.getSkips() <= 0) {
            game.setTexts("magicQuestionTwo", "Question 2: Which character is used in Python to make a single line comment?", "//", "#", "!", "");

        }
    }
    public void magicQuestionThree() {
        if (player.getSkips() > 0) {
            game.setTexts("magicQuestionThree", "Question 3: Which of the following functions is a built-in function in python language?", "print()", "System.out.println", "val()", "Skip");
            game.choice4.setVisible(true);
        }
        else if (player.getSkips() <= 0) {
            game.setTexts("magicQuestionThree", "Question 3: Which of the following functions is a built-in function in python language?", "print()", "System.out.println", "Val()", "");
            game.choice4.setVisible(false);
        }
    }
    public void magicQuestionFour() {
        if (player.getSkips() > 0) {
            game.setTexts("magicQuestionFour", "Question 4: What would be the output of the following function? \nlen([\"hello\",2, 4, 6])", "Error", "4", "3", "Skip");
            game.choice4.setVisible(true);
        }
        else if (player.getSkips() <= 0) {
            game.setTexts("magicQuestionFour", "Question 4: What would be the output of the following function? \nlen([\"hello\",2, 4, 6])", "Error", "4", "3", "");
            game.choice4.setVisible(false);
        }
    }
    public void magicQuestionFive() {
        if (player.getSkips() > 0) {
            game.setTexts("magicQuestionFive", "Question 5: print(\"abc. DEF\".capitalize())", "Abc. Def", "ABC. DEF", "Abc. def", "Skip");
            game.choice4.setVisible(true);
        }
        else if (player.getSkips() <= 0) {
            game.setTexts("magicQuestionFive", "Question 5:  print(\"abc. DEF\".capitalize())", "Abc. Def", "ABC. DEF", "Abc. def", "");
            game.choice4.setVisible(false);
        }
    }
    // Evaluating the players score, they need to get past the threshold in order to consider this a win
    public void magicQuestionEnd() {
        if(game.getPlayerPT() >= 10 ) {
            game.inventory.add("Blueprint");
            game.inventoryLabelName.setText(game.inventory.get(0) + ", " + game.inventory.get(1));
            game.setTexts("magicQuestionEnd","Your total points: " + game.getPlayerPT() + " , you did well enough to succeed here! You've received a key off the island.","Return to Theater","","","");
            game.setMagicQuizDone(true);
        }
        else if(game.getPlayerPT() < 10){
            game.setTexts("magicQuestionEnd","You got " + game.getPlayerPT() + " points. You probably should study up and give this another go, you need to get at least 10 points at the finish.","Return to Theater","","","");
        }
    }

    // Skips give the player the ability to skip the question without hurting the players score.
    public void skipQuestion() {
        player.setSkips(player.getSkips()-1);
        game.skipLabel.setText("Skips: " + player.getSkips());
    }
    /*public void skipQuestion() {
        setSkips(getSkips()-1);
        game.skipLabel.setText("Skips: " + getSkips());
    }*/
    // The first 3 questions will be easy and the last two will be hard. Easy questions give less points when correct.
    public void correctAnswerEasy() {
        game.setPlayerPT(game.getPlayerPT()+4);
        game.ptLabelNumber.setText("" + game.getPlayerPT());
    }
    public void correctAnswerHard() {
        game.setPlayerPT(game.getPlayerPT()+6);
        game.ptLabelNumber.setText("" + game.getPlayerPT());
    }
    public void wrongAnswer() {
        game.setPlayerPT(game.getPlayerPT() - 2);
        game.ptLabelNumber.setText("" + game.getPlayerPT());
    }
}
