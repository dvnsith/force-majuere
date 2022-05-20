package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.util.*;

import java.util.*;

public class StackOverflowGame {
    private GameFrame gameFrame;
    private SetUp setup;
    private Player player = new Player();
    private Boolean correctAnswer;
    private Boolean soGameDone = false;
    private int correctTotal;


    // Ctors
    public StackOverflowGame() {
    }

    public StackOverflowGame(GameFrame view) {
        gameFrame = view;
    }

    // Accessors
    public Boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Boolean getSoGameDone() {
        return soGameDone;
    }

    public void setSoGameDone(Boolean soGameDone) {
        this.soGameDone = soGameDone;
    }

    public int getCorrectTotal() {
        return correctTotal;
    }

    public void setCorrectTotal(int correctTotal) {
        this.correctTotal = correctTotal;
    }

    public void soGameStart() {
            gameFrame.setTexts("soStart", "'Oh Boy.' You can hear from the kitchen as you walk back. The mustached Chef was standing at the out-of-control printer.\n" +
                            "Spools of printer tape shooting out of the machine.\n'I can't get any orders because this table's single order just keeps printing.'.\n" +
                            "I sent a call out to IT Tom for help but folks are getting HANGRY out there!'",
                    "I Guess?", "Is Your IDE Set?", "No.", "");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
    }

    public void ideSet() {
        gameFrame.setTexts("ideSet", "Now you bet. That it's nothing but regret.\n" +
                        "If your IDE Ain't Set.\n" +
                        "The line cooks scramble to add JUnit to their projects\n",
                "Still Not?!", "I Guess?", "I'm Done Here", "");
        gameFrame.choice1.setVisible(true);
        gameFrame.choice2.setVisible(true);
        gameFrame.choice3.setVisible(true);
    }

    //Karl's printers will not stop printing - the same order continues to print out - unable to get patron orders, it's caused a backlog of issues
    //Answer 3 of 5 correctly and get a bad joke
    public void soQuestionOne() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("soQuestionOne", "First Question! BIG - O", "I agree, it is 2",
                    "CORRECT", "", "Skip Question");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("soQuestionOne", "", "", "", "", "");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(false);
        }
    }

    public void soQuestionTwo() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("soQuestionTwo", "Alright, " + player.getName() + " . You are " +
                    String.valueOf(getCorrectTotal()) + " for 1 so far.\n" +
                    "Here's an easy one\nRECURSION - WHAT'S WRONG", "No!", "Boom. There it is.",
                    "CORRECT", "Skip");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("soQuestionTwo", "Alright, " + player.getName() + " . You are " +
                            String.valueOf(getCorrectTotal()) + " for 1 so far.\n" +
                            "Here's an easy one\nRECURSION - WHAT'S WRONG", "No!", "Boom. There it is.",
                    "CORRECT",  "");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(false);
        }
    }

    public void soQuestionThree() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("soQuestionThree", player.getName() + ", the paper JUST WON'T STOP! Let's see if you can add another answer to your pool:\n" +
                            "BIG O IMAGE\n" + "Answered Correct: " + String.valueOf(getCorrectTotal()) + "\nTotal Answered: 2",
                    "CORRECT", "Boom. There it is.", " ", "Skip");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("soQuestionThree", player.getName() + ", the paper JUST WON'T STOP! Let's see if you can add another answer to your pool:\n" +
                            "BIG O IMAGE\n" + "Answered Correct: " + String.valueOf(getCorrectTotal()) + "\nTotal Answered: 2 n",
                    "CORRECT", "Boom. There it is.", " ", " ");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(false);
        }
    }

    public void soQuestionFour() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("soQuestionFour", "'GOOD NEWS! Tom said he'd be here in 5. I heard some glasses break out there.\nIt's getting ugly.Good news is,\n" +
                    "you are currently " + getCorrectTotal() + " for 3! BIG-O IMAGE", "CORRECT",
                    " ", "", "Skip Question");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("soQuestionFour", "'GOOD NEWS! Tom said he'd be here in 5. I heard some glasses break out there.\nIt's getting ugly.Good news is,\n" +
                            "you are currently " + getCorrectTotal() + " for 3! BIG-O IMAGE", "CORRECT",
                    " ", "Skip Question", " ");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(false);
        }
    }

    public void soQuestionFive() {
        if (player.getSkips() > 0) {
            gameFrame.setTexts("soQuestionFive", "THEY HAVE MOVED TO BREAKING FURNITURE. We GOTTA get these orders out! Alright one final question\nDS QUESTION\n" +
                            "DS QUESTION" + "Answered Correct:  " + String.valueOf(getCorrectTotal()),
                    "CORRECT",
                    "I am gonna have to say no", "", "Skip Question");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(true);
        } else if (player.getSkips() <= 0) {
            gameFrame.setTexts("soQuestionFive", "THEY HAVE MOVED TO BREAKING FURNITURE. We GOTTA get these orders out! Alright one final question\nDS QUESTION\n" +
                            "DS QUESTION" + "Answered Correct:  " + String.valueOf(getCorrectTotal()),
                    "CORRECT",
                    "I am gonna have to say no", "", "");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
            gameFrame.choice4.setVisible(false);
        }

        gameFrame.setSoGameDone(true);
    }

    public void soEnd() {
        if (getCorrectTotal() >= 3) {
            gameFrame.setTexts("soEnd", "The ribbons of printer tape start to subside. Karl climbs out from the mess. Well that was a doozy! " +
                            "Luckily, Tom has arrived and not a minute too soon!\n" +
                            "I thought they were about to wheel in a guillotine over pasta. " + player.getName() + " , I appreciate the help! And for your assistance, " +
                            "I offer you a joke. Are you ready?",
                    "... Sure", "Can I get a Pepsi Instead?", "I'm Good", " ");
            gameFrame.choice1.setVisible(true);
            gameFrame.choice2.setVisible(true);
            gameFrame.choice3.setVisible(true);
        } else if (getCorrectTotal() < 3) {
            gameFrame.setTexts("soEnd", "The ribbons of printer tape start to subside. Karl climbs out from the mess. Well that was a doozy! " +
                            "Luckily, Tom has arrived and not a minute too soon!\n" +
                            "I thought they were about to wheel in a guillotine over pasta. " + player.getName() + " , you didn't exactly land the plane - so I am going to take care of these printers. " +
                            "And maybe close early.",
                    "... Thanks?", "One More Time", " ", " ");
            gameFrame.choice1.setVisible(true);

        }
        gameFrame.setSoGameDone(true);
        setSoGameDone(true);
    }


            /*TODO: MOVE TO CHOICE HANDLER CLASS
            if(gameFrame.getFirstChoice().matches("... Sure") && gameFrame.getFirstChoice() != null) {
                joke();
                gameFrame.setSoGameDone(true);
            }
        }
*/

    //JS - sets skips -1 and updates player menu bar
    public void skipQuestion() {
        player.setSkips(player.getSkips() - 1);
        gameFrame.skipLabel.setText("Skips: " + player.getSkips());
    }

    //JS - set points + 1 updates player menu bar
    public int correctAnswer() {
        setCorrectTotal(getCorrectTotal() + 1);
        return getCorrectTotal();
    }

    public void joke() {
        ArrayList<String> jokeArray = new ArrayList<>();
        jokeArray.add("How many programmers does it take to change a light bulb?\nNone, that's a hardware issue.\n");
        jokeArray.add("I wanted to be a better computer programmer so I decided to slowly improve my binary skills\nYou could say I improved bit by bit\n");
        jokeArray.add("How do you know that a computer programmer is an extrovert instead of an introvert?\nWhen they talk to you, he stares at your shoes instead of his own.\n");
        jokeArray.add("Why did the computer programmer get stuck in the shower forever?\nBecause the instructions on the shampoo bottle said to\n1. Lather\n2. Rinse\n3. Repeat\n");
        jokeArray.add("A guy is standing on the corner of the street smoking one cigarette after another.\nA lady walking by notices him and says,\n'Hey, don't you know that those things can kill you? I mean, didn't you see the giant warning on the box?!'\n'That's OK,' says the guy, puffing casually. 'I'm a computer programmer'\n'So? What's that got to do with anything?'\n'We don't care about warnings. We only care about errors.'\n");
        int random = (int) Math.round((Math.random() * 4));
        //TODO: CHOICE HANDLER FOR JOKE -> C1 CALL JOKE C2 CALL HALL AND SET TO COMPLETE
        gameFrame.setTexts("joke", String.valueOf(jokeArray.get(random)), "Okay, that was good - one more", "Good Luck Chef", "", "");
        gameFrame.choice1.setVisible(true);
        gameFrame.choice2.setVisible(true);
    }

    public void pepsi(){
        gameFrame.inventory.add("Pepsi");
        gameFrame.inventoryLabelName.setFont(gameFrame.getSmallFont());


    }
}


