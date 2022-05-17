package com.team3.forcemajeure.jswing.model;

public class MagicGame {
    public final GameFrame game;
    private Boolean magicQuizDone = false;
    private int skips = 3;
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

    public int getSkips() {
        return skips;
    }

    public void setSkips(int skips) {
        this.skips = skips;
    }


    // Business methods
    public void magicQuizAsk() {
        if (!getMagicQuizDone()) {
            game.setTexts("magicQuizAsk", "Hello " + game.getPlayer() + ", would you like to answer some questions? ", "Sure!", "No Thanks", "", "");
        }

        else if (getMagicQuizDone()) {
            game.setTexts("magicQuizAsk", "It seems like you've already answered my questions. Head to another person to chat", "", "Return to Theater", "", "");
        }
    }
    // For each question we check to make sure that the player still has some skips left.
    public void magicQuestionOne() {
        if (getSkips() > 0) {
            game.setTexts("magicQuestionOne", "Question 1: Which is the correct answer?", "Incorrect", "Correct", "Incorrect", "Skip");
        }
        else if (getSkips() >= 3) {
            game.setTexts("magicQuestionOne", "Question 1: Which is the correct answer?", "Incorrect", "Correct", "Incorrect", "");
        }
    }
    public void magicQuestionTwo() {
        if (getSkips() > 0) {
            game.setTexts("magicQuestionTwo", "Question 2: Which is the correct answer?", "Correct", "Incorrect", "Incorrect", "Skip");
        }
        else if (getSkips() <= 0) {
            game.setTexts("magicQuestionTwo", "Question 2: Which is the correct answer?", "Correct", "Incorrect", "Incorrect", "");
        }
    }
    public void magicQuestionThree() {
        if (getSkips() > 0) {
            game.setTexts("magicQuestionThree", "Question 3: Which is the correct answer?", "Correct", "Incorrect", "Incorrect", "Skip");
        }
        else if (getSkips() <= 0) {
            game.setTexts("magicQuestionThree", "Question 3: Which is the correct answer?", "Correct", "Incorrect", "Incorrect", "");
            game.choice4.setVisible(false);
        }
    }
    public void magicQuestionFour() {
        if (getSkips() > 0) {
            game.setTexts("magicQuestionFour", "Question 4: Which is the correct answer?", "Incorrect", "Correct", "Incorrect", "Skip");
        }
        else if (getSkips() <= 0) {
            game.setTexts("magicQuestionFour", "Question 4: Which is the correct answer?", "Incorrect", "Correct", "Incorrect", "");
        }
    }
    public void magicQuestionFive() {
        if (getSkips() > 0) {
            game.setTexts("magicQuestionFive", "Question 5: Which is the correct answer?", "Incorrect", "Incorrect", "Correct", "Skip");
        }
        else if (getSkips() <= 0) {
            game.setTexts("magicQuestionFive", "Question 5: Which is the correct answer?", "Incorrect", "Incorrect", "Correct", "");
        }
    }
        // Evaluating the players score, they need to get past the threshold in order to consider this a win
    public void magicQuestionEnd() {
        if(game.getPlayerPT() >= 12 ) {
            game.inventory.add("Key");
            game.inventoryLabelName.setText(game.inventory.get(0) + ", " + game.inventory.get(1));
            game.setTexts("magicQuestionEnd","Your total points: " + game.getPlayerPT() + " out of 24 total points, you did well enough to succeed here!","Return to Theater","","","");
            setMagicQuizDone(true);
        }
        else if(game.getPlayerPT() < 12){
            game.setTexts("magicQuestionEnd","You got " + game.getPlayerPT() + " points. You probably should study up and give this another go, you need to get at least 8 points at the finish.","Return to Theater","","","");
        }
    }

    // Skips give the player the ability to skip the question without hurting the players score.
    public void skipQuestion() {
        setSkips(getSkips()-1);
        game.skipLabel.setText("Skips: " + getSkips());
    }
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
