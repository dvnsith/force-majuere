package com.team3.forcemajeure.jswing;

import com.team3.forcemajeure.util.Audio;
import com.team3.forcemajeure.util.Player;
import com.team3.forcemajuere.game.Blackjack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class GameExample {

    JFrame window;
    Container con;
    JPanel menuPanel, userNamePanel, titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel userNameLabel, titleNameLabel, ptLabel, ptLabelNumber, inventoryLabel, inventoryLabelName;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    JButton soundButton, startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerPT, monsterHP, silverRing;
    String inventory, position;
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    String player, previousRoom, currentRoom, mainText, firstChoice, secondChoice, thirdChoice, fourthChoice;
    Boolean soundOn = true;
    ImageIcon logo = new ImageIcon("resources/images/island.png");
    ImageIcon mapImage;
    JLabel mapLabel = new JLabel();
    Audio audio = Audio.getInstance();
    private int dealerHand = 0;
    private int playerHand = 0;
    private int card = 0;
    private int losses = 0;



    //Accessor
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(String previousRoom) {
        this.previousRoom = previousRoom;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice;
    }

    public String getThirdChoice() {
        return thirdChoice;
    }

    public void setThirdChoice(String thirdChoice) {
        this.thirdChoice = thirdChoice;
    }

    public String getFourthChoice() {
        return fourthChoice;
    }

    public void setFourthChoice(String fourthChoice) {
        this.fourthChoice = fourthChoice;
    }

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

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getPlayerPT() {
        return playerPT;
    }

    public void setPlayerPT(int playerPT) {
        this.playerPT = playerPT;
    }

    // Ctor
    public GameExample() {

        Color bg = Color.black;

        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(bg);
        window.setLayout(null);
        window.setIconImage(logo.getImage());
        con = window.getContentPane();


        soundButton = new JButton("🔈 on/off");
        soundButton.setBounds(15,7,50,50);
        soundButton.addActionListener(e -> {
            if(isSoundOn()) {
                System.out.println("Sound Off");
                setSoundOn(false);
            } else {
                System.out.println("Sound on");
                setSoundOn(true);
            }
        });


        menuPanel = new JPanel();
        menuPanel.setBounds(15,7,200,50);
        menuPanel.setBackground(bg);

        userNamePanel = new JPanel();
        userNamePanel.setBounds(350,250,250,125);
        userNamePanel.setBackground(bg);
        userNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(175, 100, 600, 150);
        titleNamePanel.setBackground(bg);
        titleNameLabel = new JLabel("Force Majeure");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(370, 400, 200, 100);
        startButtonPanel.setBackground(bg);

        startButton = new JButton("START");
        startButton.setBackground(bg);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        userNameLabel = new JLabel("Enter username");
        userNameLabel.setForeground(Color.white);
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200,40));
        startButton.addActionListener(e -> {
            if(e.getSource() == startButton){
                setPlayer(textField.getText());

            }
        });

        menuPanel.add(soundButton);
        userNamePanel.add(userNameLabel);
        userNamePanel.add(textField);
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(menuPanel);
        con.add(userNamePanel);
        con.add(titleNamePanel);
        con.add(startButtonPanel);

        window.setVisible(true);
    }

    //Business Methods
    public void createGameScreen() {
        userNamePanel.setVisible(false);
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(220, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
//        con.add(imageLabel);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea(
                "This is the main text are. This game is going to be great. I'm sure of it!!!!!!!");
        mainTextArea.setBounds(225, 200, 500, 300);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(350, 475, 300, 150);
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
        playerPanel.setBounds(250, 0, 600, 50);
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
        audio.play("start");
        // playerPT = player.getTotalPoints();
        monsterHP = 20;
        inventory = "Map";
        inventoryLabelName.setText(inventory);
        ptLabelNumber.setText("" + getPlayerPT());

        dock();
    }

    public void setTexts(String pos, String mainText, String choiceOne, String choiceTwo, String choiceThree,
                         String choiceFour) {

        choice1.setVisible(true);
        choice2.setVisible(true);
        choice3.setVisible(true);
        mapLabel.setVisible(false);
        position = pos;
        // if pos = blackjack or contains the word blackjack
        // add text area to show card output
        mainTextArea.setText(mainText);
        choice1.setText(choiceOne);
        choice2.setText(choiceTwo);
        choice3.setText(choiceThree);
        choice4.setText(choiceFour);
        setMainText(mainText);
        setFirstChoice(choiceOne);
        setSecondChoice(choiceTwo);
        setThirdChoice(choiceThree);
        setFourthChoice(choiceFour);

        // prev. room = current room
        // current room = pos
        setPreviousRoom(getCurrentRoom());
        setCurrentRoom(pos);

        System.out.println("Prev room: "+ getPreviousRoom() + "\nCurrent room: " + getCurrentRoom());
    }


    // blackjack logic => int



    public void showMap(String previousRoomName){
        // when clicked => shows map of current room then on choice, return to previous room.
        setCurrentRoom(previousRoomName);
        mapLabel.setVisible(true);

        switch (previousRoomName){
            case "dock":
                // show dock image
                mapImage = new ImageIcon("resources/images/dock.jpg");
                break;
            case "talkInstructor":
                // show talkInstructor image
                mapImage = new ImageIcon("resources/images/beach.jpeg");
                break;
            case "lobby":
                // show lobby image
                mapImage = new ImageIcon("resources/images/lobby.jpg");
                break;
            case "hall":
                // show hall image
                mapImage = new ImageIcon("resources/images/hall.jpg");
                break;
            case "restaurant":
                // show restaurant image
                mapImage = new ImageIcon("resources/images/restaurant.jpg");
                break;
            case "gameFloor":
                // show theater image
                mapImage = new ImageIcon("resources/images/casinofloor.jpg");
                break;
            case "theater":
                // show theater image
                mapImage = new ImageIcon("resources/images/theater.jpg");
                break;
        }

        mapLabel.setIcon(mapImage);
        mainTextPanel.add(mapLabel);
        position = "map";
        mainTextArea.setText("this is the map");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setText("exit map");
    }

    public void talkInstructor() {
        setTexts("talkInstructor", "Instructor: Hello " + getPlayer() + ", Please go to the casino and explore", "Go to dock",
                "Go to lobby", "", "see map");
    }

    public void beach() {
        setTexts("beach", "This is the beach", "go to lobby", "go to dock", "", "see map");
    }
    public void dock() {
        setTexts("dock", "This is the dock", "go to beach", "", "", "see map");
    }
    public void lobby() {
        setTexts("lobby", "This is the lobby", "go to hall", "go to beach", "", "see map");
    }
    public void hall() {
        setTexts("hall", "This is the hall", "go to lobby", "go to restaurant", "", "see map");
    }
    public void restaurant() {
        setTexts("restaurant", "This is the restaurant", "go to hall", "go game floor", "", "see map");
    }
    public void gameFloor() {
        setTexts("gameFloor", "This is the game floor", "go to theater", "go to restaurant", "speak to casino jay", "see map");
    }
    public void theater() {
        setTexts("theater", "This is the theater", "go to game floor", "", "", "see map");
    }

    public void blackJackStart() {
        setPlayerHand(0);
        setDealerHand(0);
        if (getLosses() < 5){
            setTexts("blackjackstart", "Do you want to play blackjack?", "Yes","No","","");
        }
        else if(getLosses() >= 5) {
            setTexts("blackjackstart", "I think it's best you lay off the tables for today. You have too many losses", "","Return to Game Floor","","");
        }
    }

    public void blackjackDeal() {
        setDealerHand(ThreadLocalRandom.current().nextInt(13,21));
        for (int playerCount = 0; playerCount < 2; playerCount++)  {
            card = ThreadLocalRandom.current().nextInt(1,11);
            setPlayerHand(playerHand += card);
        }
    }

    public void blackJackRound() {
        if (getPlayerHand() < 22){
            setTexts("blackjackfirsthand","Here is your hand: " + getPlayerHand(),"Hit me","Stay","","");
        }
        else if (getPlayerHand() > 21){
            setPlayerPT(getPlayerPT()-2);
            ptLabelNumber.setText("" + getPlayerPT());
            setLosses(getLosses()+1);
            setTexts("checkcards","You busted! Better luck next time","Return to Game Floor","","","");
        }
    }

    public void hitMe() {
            card = ThreadLocalRandom.current().nextInt(1,11);
            setPlayerHand(playerHand += card);
    }

    public void checkCards() {
        if (getPlayerHand() > getDealerHand()) {
            setPlayerPT(getPlayerPT()+3);
            ptLabelNumber.setText("" + getPlayerPT());
            setTexts("checkcards","It's your lucky day! You get 3 points","Return to Game Floor","","","");
        }
        else if (getPlayerHand() < getDealerHand()) {
            setPlayerPT(getPlayerPT()-2);
            ptLabelNumber.setText("" + getPlayerPT());
            setLosses(getLosses()+1);
            setTexts("checkcards","Better luck next time.","Return to Game Floor","","","");
        }
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

    public class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            switch (position) {
                case "dock":
                    switch (yourChoice) {
                        case "c1":
                            if (silverRing == 1) {
                                ending();
                            } else {
                                talkInstructor();
                            }
                            break;
                        case "c4":
                            showMap("dock");
                            break;
                    }
                    break;
                case "map":
                    switch (yourChoice){
                        case "c4": setTexts(getCurrentRoom(), getMainText(), getFirstChoice(),getSecondChoice(), getThirdChoice(), getFourthChoice());// get previous method of scene
                            break;
                    }
                    break;
                case "talkInstructor":
                    switch (yourChoice) {
                        case "c1":
                            dock();
                            break;
                        case "c2":
                            lobby();
                            break;
                        case "c4":
                            showMap("talkInstructor");
                            break;
                    }
                    break;
                case "lobby":
                    switch (yourChoice) {
                        case "c1":
                            hall();
                            break;
                        case "c2":
                            talkInstructor();
                            break;
                        case "c4":
                            showMap("lobby");
                            break;
                    }
                    break;
                case "hall":
                    switch (yourChoice) {
                        case "c1":
                            lobby();
                            break;
                        case "c2":
                            restaurant();
                            break;
                        case "c4":
                            showMap("hall");
                            break;
                    }
                    break;
                case "restaurant":
                    switch (yourChoice) {
                        case "c1":
                            hall();
                            break;
                        case "c2":
                            gameFloor();
                            break;
                        case "c4":
                            showMap("restaurant");
                            break;
                    }
                    break;
                case "gameFloor":
                    switch (yourChoice) {
                        case "c1":
                            theater();
                            break;
                        case "c2":
                            restaurant();
                            break;
                        case "c3":
                            blackJackStart();
                            break;
                        case "c4":
                            showMap("gameFloor");
                            break;
                    }
                    break;
                case "theater":
                    switch (yourChoice) {
                        case "c1":
                            gameFloor();
                            break;
                        case "c4": showMap("theater");break;
                    }
                    break;
                case "blackjackstart":
                    switch (yourChoice) {
                        case "c1":
                            blackjackDeal();
                            blackJackRound();
                            break;
                        case "c2":
                            gameFloor();
                            break;
                    }
                    break;
                case "blackjackfirsthand":
                    switch (yourChoice) {
                        case "c1":
                            hitMe();
                            blackJackRound();
                            break;
                        case "c2":
                            checkCards();
                            break;
                    }
                    break;
                case "checkcards":
                    switch (yourChoice) {
                        case "c1":
                            gameFloor();
                            break;
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

