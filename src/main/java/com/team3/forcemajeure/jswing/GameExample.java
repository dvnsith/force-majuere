package com.team3.forcemajeure.jswing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameExample {

    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel, ptLabel, ptLabelNumber, inventoryLabel, inventoryLabelName;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerPT, monsterHP, silverRing;
    String inventory, position;
    JLabel imageLabel = new JLabel();
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();

    ImageIcon logo = new ImageIcon("resources/images/island.png");

    public static void main(String[] args) {

        new GameExample();
    }

    public GameExample() {

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setIconImage(logo.getImage());
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Force Majeure");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        window.setVisible(true);
    }

    public void createGameScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(imageLabel);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea(
                "This is the main text are. This game is going to be great. I'm sure of it!!!!!!!");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(choiceButtonPanel);
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        // choice4.setContentAreaFilled(false); // Disable highlighting on press!!!

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        con.add(playerPanel);
        ptLabel = new JLabel("Points:");
        ptLabel.setFont(normalFont);
        ptLabel.setForeground(Color.white);
        playerPanel.add(ptLabel);
        ptLabelNumber = new JLabel();
        ptLabelNumber.setFont(normalFont);
        ptLabelNumber.setForeground(Color.white);
        playerPanel.add(ptLabelNumber);
        inventoryLabel = new JLabel("Inventory:");
        inventoryLabel.setFont(normalFont);
        inventoryLabel.setForeground(Color.white);
        inventoryLabel.setBackground(Color.red);
        playerPanel.add(inventoryLabel);
        inventoryLabelName = new JLabel();
        inventoryLabelName.setFont(normalFont);
        inventoryLabelName.setForeground(Color.white);
        playerPanel.add(inventoryLabelName);

        playerSetup();

    }

    public void playerSetup() {

        playerPT = 0;
        monsterHP = 20;
        inventory = "Map";
        inventoryLabelName.setText(inventory);
        ptLabelNumber.setText("" + playerPT);

        dock();
    }

    public void setTexts(String pos, String mainText, String choiceOne, String choiceTwo, String choiceThree,
                         String choiceFour) {

        position = pos;
        // if pos = blackjack or contains the word blackjack
        // add text area to show card output
        mainTextArea.setText(mainText);
        choice1.setText(choiceOne);
        choice2.setText(choiceTwo);
        choice3.setText(choiceThree);
        choice4.setText(choiceFour);
    }

    // blackjack logic => int

    public void talkInstructor() {
        setTexts("talkInstructor", "Instructor: Hello [username], Please go to the casino and exlore", "Go to dock",
                "Go to lobby", "", "");
    }

    public void blackJackRound1() {
        // pos = blackjack
        // int card = blackjackGame();

        // setText("blackjack", "dealer msg", "hit me", "hold", "","",)
    }

    public void blackJackRound2() {

    }

    public void blackJackRound3() {

    }

    public void blackJackRound4() {

    }

    public void blackJackRound5() {

    }

    public void beach() {
        setTexts("beach", "This is the beach", "go to lobby", "go to restaurant", "", "");
    }

    public void dock() {
        setTexts("dock", "This is the dock", "go to beach", "", "", "");
    }
    public void lobby() {
        setTexts("lobby", "This is the dock", "go to hall", "go to beach", "talkInstructor", "");
    }

    public void hall() {
        setTexts("hall", "This is the hall", "go to lobby", "go to restaurant", "", "");
    }

    public void restaurant() {
        setTexts("restaurant", "This is the restaurant", "go to game floor", "go to hall", "", "");
    }

    public void gameFloor() {
        setTexts("gameFloor", "This is the game floor", "go to theater", "go to restaurant", "", "");
    }

    public void theater() {
        setTexts("theater", "This is the theater", "go to game floor", "", "", "");
    }

    public void ending() {

    }
    // public void attackGuard(){
    // position = "attackGuard";
    // mainTextArea.setText("Guard: Hey don't be stupid!\n\nThe guard fought back
    // and hit you hard.\n(You receive 3 damage)");
    // //playerHP = playerHP -3;
    // playerPT -=3;
    // ptLabelNumber.setText(""+playerPT);
    // choice1.setText(">hit me");
    // choice2.setText(">skip");
    // choice3.setText("");
    // choice4.setText("");
    // }
    // public void crossRoad(){
    // position = "crossRoad";
    // mainTextArea.setText("You are at a crossroad.\nIf you go south, you will go
    // back to the town.");
    // choice1.setText("Go north");
    // choice2.setText("Go east");
    // choice3.setText("Go south");
    // choice4.setText("Go west");
    // }
    // public void north(){
    // position = "north";
    // mainTextArea.setText("There is a river. \nYou drink the water and rest at the
    // riverside. \n\n(Your HP is recovered by 2)");
    // playerPT = playerPT + 2;
    // ptLabelNumber.setText(""+playerPT);
    // choice1.setText("Go south");
    // choice2.setText("");
    // choice3.setText("");
    // choice4.setText("");
    // }
    // public void east(){
    // position = "east";
    // mainTextArea.setText("You walked into a forest and found a Long
    // Sword!\n\n(You obtained a Long Sword)");
    // inventory = "Long Sword";
    // inventoryLabelName.setText(inventory);
    // choice1.setText("Go west");
    // choice2.setText("");
    // choice3.setText("");
    // choice4.setText("");

    // }
    // public void west(){
    // position = "west";
    // mainTextArea.setText("You encounter a goblin!");
    // choice1.setText("Fight");
    // choice2.setText("Run");
    // choice3.setText("");
    // choice4.setText("");
    // }
    // public void fight(){
    // position = "fight";
    // mainTextArea.setText("Monter HP: " + monsterHP + "\n\nWhat do you do?");
    // choice1.setText("Attack");
    // choice2.setText("Run");
    // choice3.setText("");
    // choice4.setText("");
    // }
    // public void playerAttack(){
    // position = "playerAttack";

    // int playerDamage = 0;

    // if(inventory.equals("Knife")){
    // playerDamage = new java.util.Random().nextInt(3);
    // }
    // else if(inventory.equals("Long Sword")){
    // playerDamage = new java.util.Random().nextInt(12);
    // }

    // mainTextArea.setText("You attacked the monster and gave " + playerDamage + "
    // damage!");

    // monsterHP = monsterHP - playerDamage;

    // choice1.setText(">");
    // choice2.setText("");
    // choice3.setText("");
    // choice4.setText("");
    // }
    // public void monsterAttack(){
    // position = "monsterAttack";

    // int monsterDamage = 0;

    // monsterDamage = new java.util.Random().nextInt(6);

    // mainTextArea.setText("The monster attacked you and gave " + monsterDamage + "
    // damage!");

    // playerPT = playerPT - monsterDamage;
    // ptLabelNumber.setText(""+playerPT);

    // choice1.setText(">");
    // choice2.setText("");
    // choice3.setText("");
    // choice4.setText("");
    // }
    // public void win(){
    // position = "win";

    // mainTextArea.setText("You defeated the monster!\nThe monster dropped a
    // ring!\n\n(You obtained a Silver Ring)");

    // silverRing = 1;

    // choice1.setText("Go east");
    // choice2.setText("");
    // choice3.setText("");
    // choice4.setText("");

    // }
    // public void lose(){
    // position = "lose";

    // mainTextArea.setText("You are dead!\n\nGAME OVER");

    // choice1.setText("");
    // choice2.setText("");
    // choice3.setText("");
    // choice4.setText("");
    // choice1.setVisible(false);
    // choice2.setVisible(false);
    // choice3.setVisible(false);
    // choice4.setVisible(false);
    // }
    // public void ending(){
    // position = "ending";

    // mainTextArea.setText("Guard: Oh you killed that goblin!?\nThank you so much.
    // You are true hero!\nWelcome to our town!\n\nTHE END");

    // choice1.setText("");
    // choice2.setText("");
    // choice3.setText("");
    // choice4.setText("");
    // choice1.setVisible(false);
    // choice2.setVisible(false);
    // choice3.setVisible(false);
    // choice4.setVisible(false);
    // }

    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            createGameScreen();
        }
    }

    public class ChoiceHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            String yourChoice = event.getActionCommand();

            switch(position){
                case "dock":
                    switch(yourChoice){
                        case "c1":
                            if(silverRing==1){
                                ending();
                            }
                            else{
                                talkInstructor();
                            }
                            break;
//                        case "c2": talkInstructor();break;
//                        case "c3": lobby();break;
                    }
                    break;
                case "talkInstructor":
                    switch(yourChoice) {
                        case "c1":
                            dock();
                            break;
                        case "c2":
                            lobby();
                            break;
                    }
                    break;
                case "lobby":
                    switch(yourChoice){
                        case "c1": hall(); break;
                        case "c2": beach(); break;
                        case "c3": talkInstructor(); break;
                    }
                    break;
                case "hall":
                    switch(yourChoice){
                        case "c1": lobby(); break;
                        case "c2": restaurant(); break;
                    }
                    break;
                case "restaurant":
                    switch(yourChoice){
                        case "c1": hall(); break;
                        case "c2": gameFloor(); break;
                    }
                    break;
                case "gameFloor":
                    switch(yourChoice){
                        case "c1": theater(); break;
                        case "c2": restaurant(); break;
                    }
                    break;
                case "theater":
                    switch(yourChoice){
                        case "c1": gameFloor(); break;
                    }
                    break;

                // case "crossRoad":
                //     switch(yourChoice){
                //         case "c1": north(); break;
                //         case "c2": east();break;
                //         case "c3": dock(); break;
                //         case "c4": west();break;
                //     }
                //     break;
                // case "north":
                //     switch(yourChoice){
                //         case "c1": crossRoad(); break;
                //     }
                //     break;
                // case "east":
                //     switch(yourChoice){
                //         case "c1": crossRoad(); break;
                //     }
                //     break;
                // case "west":
                //     switch(yourChoice){
                //         case "c1": fight(); break;
                //         case "c2": crossRoad(); break;
                //     }
                //     break;
                // case "fight":
                //     switch(yourChoice){
                //         case "c1": playerAttack();break;
                //         case "c2": crossRoad(); break;
                //     }
                //     break;
                // case "playerAttack":
                //     switch(yourChoice){
                //         case "c1":
                //             if(monsterHP <1 ){
                //                 win();
                //             }
                //             else{
                //                 monsterAttack();
                //             }
                //             break;
                //     }
                //     break;
                // case "monsterAttack":
                //     switch(yourChoice){
                //         case "c1":
                //             if(playerPT <1 ){
                //                 lose();
                //             }
                //             else{
                //                 fight();
                //             }
                //             break;
                //     }
                //     break;
                // case "win":
                //     switch(yourChoice){
                //         case "c1": crossRoad();
                //     }
                //     break;

            }


        }
    }

}