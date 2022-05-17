package com.team3.forcemajeure.jswing.view;

import com.team3.forcemajeure.jswing.view.GameFrame;

public class Magic {
    public GameFrame game;
    private int magicQuizCorrect = 0;
    private Boolean magicQuizDone = false;


    public Boolean getMagicQuizDone() {
        return magicQuizDone;
    }

    public void setMagicQuizDone(Boolean magicQuizDone) {
        this.magicQuizDone = magicQuizDone;
    }

    public int getMagicQuizCorrect() {
        return magicQuizCorrect;
    }

    public void setMagicQuizCorrect(int magicQuizCorrect) {
        this.magicQuizCorrect = magicQuizCorrect;
    }


    public void magicQuizAsk() {
        if (!getMagicQuizDone()) {
            game.setTexts("magicQuizAsk", "Hello " + game.getPlayer() + ", would you like to answer some questions? ", "Sure!", "No Thanks", "", "");
        }
        else if (getMagicQuizDone()) {
            game.setTexts("magicQuizAsk", "It seems like you've already answered my questions. Head to another person to chat", "", "Return to Theater", "", "");
        }

    }

    public void magicQuestionOne() {
        game.setTexts("magicQuestionOne","Question 1: Which is the correct answer?","Incorrect","Correct","Incorrect","Incorrect");
    }
    public void magicQuestionTwo() {
        game.setTexts("magicQuestionTwo","Question 2: Which is the correct answer?","Incorrect","Incorrect","Incorrect","Correct");
    }
    public void magicQuestionThree() {
        game.setTexts("magicQuestionThree","Question 3: Which is the correct answer?","Correct","Incorrect","Incorrect","Incorrect");
    }
    public void magicQuestionFour() {
        game.setTexts("magicQuestionFour","Question 4: Which is the correct answer?","Incorrect","Correct","Incorrect","Incorrect");
    }
    public void magicQuestionFive() {
        game.setTexts("magicQuestionFive","Question 5: Which is the correct answer?","Incorrect","Incorrect","Correct","Incorrect");
    }
    public void magicQuestionEnd() {
        if(getMagicQuizCorrect() >= 3) {

            game.inventory.add("Key");
            game.inventoryLabelName.setText(game.inventory.get(0) + ", " + game.inventory.get(1));

            game.setTexts("magicQuestionEnd","Your total correct: " + getMagicQuizCorrect() + " out of 5, you did well enough to succeed here!","Return to Theater","","","");
            setMagicQuizDone(true);
        }
        else if(getMagicQuizCorrect() < 3){
            game.setTexts("magicQuestionEnd","Your total correct: " + getMagicQuizCorrect() + " out of 5. You probably should study up and give this another go.","Return to Theater","","","");
        }

    }
    public void correctAnswer() {
        setMagicQuizCorrect(getMagicQuizCorrect()+1);
        game.setPlayerPT(game.getPlayerPT()+4);
        game.ptLabelNumber.setText("" + game.getPlayerPT());
    }
    public void wrongAnswer() {
        game.setPlayerPT(game.getPlayerPT() - 2);
        game.ptLabelNumber.setText("" + game.getPlayerPT());
    }
}
