package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.util.*;
import java.util.*;

public class JavaScriptGame {
    private GameFrame gameFrame;
    private SetUp setup;
    private Player player = new Player();
    private boolean correctAnswer;
    private Boolean jsGameDone = false;
    private String password;


    // Ctors
    public JavaScriptGame(){}

    public JavaScriptGame(GameFrame view) {
        gameFrame = view;
    }

    // Accessors
    public Boolean getJsGameDone() {
        return jsGameDone;
    }

    public void setJsGameDone(Boolean jsGameDone) {
        this.jsGameDone = jsGameDone;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void jsGameStart() {
            gameFrame.setTexts("jsStart", player.getName() + " !You should know that as a developer that you are constantly using blueprints when designing web applications and pages." +
                    "\nYou see, I have this new idea but need help finding the blueprints for the site. " +
                    "I'm on a time crunch and need some quick help.\nLet me run some questions by you, let me know if you think I am on the right path. " +
                    "\nReady for a challenge?", "Let build!", "Let me check W3Schools first", "", "");
            // gameFrame.mainTextArea.setFont(gameFrame.getChoiceFont());
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
    }

    public void jsQuestionOne() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("jsQuestionOne", "Am I seeing this right?\nIt looks like there are 2 elements named 'trail'. " +
                    "What do you think?", "I agree, it is 2", "No, that's not right", "", "Skip Question");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("jsQuestionOne", "Am I seeing this right?\nIt looks like there are 2 elements named 'trail'. " +
                    "What do you think?", "I agree, it is 2", "No, that's not right", "", "");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);
        }

    }
//    public void jsQuestionResult() {
//        gameFrame.setTexts("jsQuestionTwo", "Am I seeing this right?\nIt looks like there are 2 elements named 'trail'. " +
//                "What do you think?", "I agree, it is 2", "No, that's not right", "", "Skip Question");
//        gameFrame.choice1.setVisible(true);
//        gameFrame.choice2.setVisible(true);
//        gameFrame.choice3.setVisible(false);
//        gameFrame.choice4.setVisible(true);
//    }

    public void jsQuestionTwo() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("jsQuestionTwo", "Now we are warming up!\nTo get some JS functionality in my HTML page, " +
                    "do I just add the JS script in script tags?", "No!", "Boom. There it is.", "", "Skip");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("jsQuestionTwo", "Now we are warming up!\nTo get some JS functionality in my HTML page, " +
                    "do I just add the JS script in script tags?", "No!", "Boom. There it is.", "", "Skip");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);
        }
    }

    public void jsQuestionThree() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("jsQuestionThree", "I am drawing a blank on this one. " +
                    "You can or cannot write CSS inline?", "You can't do that!", "Absolutely!", "", "Skip Question");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("jsQuestionThree", "I am drawing a blank on this one. " +
                    "You can or cannot write CSS inline?", "You can't do that!", "Absolutely!", "", "");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);
        }
    }

    public void jsQuestionFour() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("jsQuestionFour", "Okay, so if I want to create a new HTML file for this page, " +
                    "and I want it to be styled with a linked CSS sheet, I just add the link " +
                    "to the style sheet in the <head></head> tags, right?", "You got it!", "Nope", "", "Skip Question");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("jsQuestionFour", "Okay, so if I want to create a new HTML file for this page, " +
                    "and I want it to be styled with a linked CSS sheet, I just add the link " +
                    "to the style sheet in the <head></head> tags, right?", "You got it!", "Nope", "", "");
            gameFrame.choice4.setVisible(false);
        }
    }

    public void jsQuestionFive() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("jsQuestionFive", "Now here's a challenge, I am trying to figure out " +
                    "just what anchor tag is about to be pulled. I think it's going to be Pisgah Waterfalls. " +
                    "Do you agree?", "Yea, definitely Pisgah Waterfalls", "I am gonna have to say no", "", "Skip Question");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("jsQuestionFive", "Now here's a challenge, I am trying to figure out " +
                    "just what anchor tag is about to be pulled. I think it's going to be Pisgah Waterfalls. " +
                    "Do you agree?", "Yea, definitely Pisgah Waterfalls", "I am gonna have to say no", "", "");
            gameFrame.choice3.setVisible(false);
            gameFrame.choice4.setVisible(false);
        }

    }

    public void jsEnd() {
        if (gameFrame.getPlayerPT() >= 10) {
            password();
            gameFrame.setTexts("jsEnd", "Well, I think that's it! Thanks for the help!\nAnd for looking out, like always.\nI have this " +
                            "'magic word' that can get you into\nThe Fantastical Fesser's Show. The password is:\n" + gameFrame.getMagicWord() + "\nNow go out there and kill it\nOh, and" + player.getName() +
                            ", keep an eye out for my blueprints. I want you to see them.",
                    "Return to Lobby", "", "", "");
            gameFrame.choice1.setVisible(true);

        } else if (gameFrame.getPlayerPT() < 10) {
            gameFrame.setTexts("jsEnd2", "I think you may need to go ahead and hit the docs, " + player.getName() + "\nI'm going to need you to come back and try again!",
                    "Return to Lobby", "Try Again", "", "");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.setJsGameDone(false);

        }
    }


    //JS - sets skips -1 and updates player menu bar
    public void skipQuestion() {
        player.setSkips(player.getSkips() - 1);
        gameFrame.skipLabel.setText("Skips: " + player.getSkips());
    }

    //JS - set points + 1 updates player menu bar
    public void correctImagesAnswer() {
        gameFrame.setPlayerPT(gameFrame.getPlayerPT() + 4);
        gameFrame.ptLabelNumber.setText("" + gameFrame.getPlayerPT());
    }

    public void correctAnswer() {
        gameFrame.setPlayerPT(gameFrame.getPlayerPT() + 3);
        gameFrame.ptLabelNumber.setText("" + gameFrame.getPlayerPT());
    }

    //JS - set points -1 updates player menu bar
    public void wrongAnswer() {
        gameFrame.setPlayerPT(gameFrame.getPlayerPT() - 3);
        gameFrame.ptLabelNumber.setText("" + gameFrame.getPlayerPT());
    }

    public void password() {
        ArrayList<String> passwordArray = new ArrayList<>();
        passwordArray.add("duck race");
        passwordArray.add("no cap");
        passwordArray.add("its in the api docs");
        passwordArray.add("cherry pepsi");
        passwordArray.add("vim froze again");
        int random = (int) Math.round((Math.random() * 4));
        String rand = passwordArray.get(random);
        gameFrame.setMagicWord(rand);
        gameFrame.inventory.add(0, gameFrame.getMagicWord());
        gameFrame.inventoryLabelName.setFont(gameFrame.getSmallFont());

        gameFrame.setJsGameDone(true);
        setJsGameDone(true);
        gameFrame.inventoryLabelName.setText(gameFrame.inventory.toString());
        System.out.println("Inventory: " + gameFrame.inventory + "\nPassword: " + password + "\nJS Game Done: ");
    }


}


