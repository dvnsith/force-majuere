package com.team3.forcemajeure.jswing;

import com.team3.forcemajeure.util.Audio;
import com.team3.forcemajeure.util.SoundPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameFrame {

    JFrame window;
    Container con;
    JPanel menuPanel, userNamePanel, titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel userNameLabel, titleNameLabel, ptLabel, ptLabelNumber, inventoryLabel, inventoryLabelName;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    JButton soundButton, startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerPT, monsterHP, silverRing;
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    String inventory, position, player, previousRoom, currentRoom, mainText, firstChoice, secondChoice, thirdChoice, fourthChoice;
    Boolean soundOn = true;
    ImageIcon logo = new ImageIcon("resources/images/island.png");
    ImageIcon gameMapImage,gameBgImage;
    JLabel mapLabel = new JLabel();
    JLabel imageBgLabel = new JLabel();
    Audio audio = Audio.getInstance();
    SoundPlayer sound = new SoundPlayer();
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
    public GameFrame() {

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

//        menuPanel.add(soundButton);
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
        mainTextPanel.setBounds(220, 75, 600, 425);
        mainTextPanel.setBackground(Color.black);
//        con.add(imageLabel);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea(
                "Oops...the text is not showing.");
        mainTextArea.setBounds(225, 500, 500, 300);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(350, 515, 300, 125);
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

        Clip themeSong = sound.play("start",true,0,GameFrame.class);
        // playerPT = player.getTotalPoints();
        monsterHP = 20;
        inventory = "Map";
        inventoryLabelName.setText(inventory);
        ptLabelNumber.setText("" + getPlayerPT());

        //add sound to game play
        soundButton = new JButton("🔈 on/off");
        soundButton.setBounds(15,7,50,50);
        soundButton.addActionListener(e -> {
            if(isSoundOn()) {
                System.out.println("Sound Off");
                setSoundOn(false);
                themeSong.stop();
            } else {
                System.out.println("Sound on");
                setSoundOn(true);
                themeSong.start();
            }
        });

        menuPanel.add(soundButton);

        //start off with dock
        dock();
    }

    public ImageIcon setImage(String roomName, boolean isMap){
        ImageIcon anImage = new ImageIcon();
        //isMap then set map to image else set bg of room to image
        switch (roomName){
            case "dock":
                // show dock image
                anImage = isMap ? new ImageIcon("resources/images/map/VisitDock/All_Beach.png") : new ImageIcon("resources/images/dock.jpg") ;
                break;
            case "talkInstructor":
                // show talkInstructor image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/All_DFl.jpg") : new ImageIcon("resources/images/beach.jpeg");
                break;
            case "lobby":
                // show lobby image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/All_DLobby.jpg") : new ImageIcon("resources/images/lobby.jpg");
                break;
            case "hall":
                // show hall image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/All_Beach.png") : new ImageIcon("resources/images/hall.jpg");
                break;
            case "restaurant":
                // show restaurant image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/All_Beach.png") : new ImageIcon("resources/images/restaurant.jpg");
                break;
            case "gameFloor":
                // show theater image
                anImage = isMap ? new ImageIcon("resources/images/map/VisitDock/All_Beach.png") :  new ImageIcon("resources/images/casinofloor.jpg");
                break;
            case "theater":
                // show theater image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/All_Beach.png") : new ImageIcon("resources/images/theater.jpg");
                break;
        }
        return anImage;
    }

    public void setTexts(String pos, String mainText, String choiceOne, String choiceTwo, String choiceThree,
                         String choiceFour) {

        mainTextPanel.setVisible(true);
        choice1.setVisible(true);
        choice2.setVisible(true);
        choice3.setVisible(true);
        mapLabel.setVisible(false);
        imageBgLabel.setVisible(true);
        position = pos;

        //if room is null then set bgImage to dock else
        //get bg of image based on room
        if(getPreviousRoom() == null){
            gameBgImage = setImage("dock", false);
            setPreviousRoom("dock");

        } else {
            gameBgImage = setImage(pos, false);
            setPreviousRoom(getCurrentRoom());
        }
        imageBgLabel.setIcon(gameBgImage);
        mainTextPanel.add(imageBgLabel);
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
        setCurrentRoom(pos);

        System.out.println("Prev room: "+ getPreviousRoom() + "\nCurrent room: " + getCurrentRoom());
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

    public void showMap(String currentRoomName){
        // when clicked => shows map of current room then on choice, return to previous room.
        setCurrentRoom(currentRoomName);
        imageBgLabel.setVisible(false);
        mapLabel.setVisible(true);

        gameMapImage = setImage(getCurrentRoom(), true);

        choiceButtonPanel.setOpaque(true);

        mapLabel.setIcon(gameMapImage);
        mainTextPanel.add(mapLabel);
        position = "map";
        mainTextArea.setText("");

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
        setTexts("gameFloor", "This is the game floor", "go to theater", "go to restaurant", "Speak to Casino Jay", "see map");
    }
    public void theater() {
        setTexts("theater", "This is the theater", "go to game floor", "", "", "see map");
    }

    public void ending() {

    }

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
                            talkInstructor();
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
                        case "c4": showMap("theater");
                            break;
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
            }
        }
    }
}

