package com.team3.forcemajeure.jswing;

import com.team3.forcemajeure.util.Audio;
import com.team3.forcemajeure.util.ReadFile;
import com.team3.forcemajeure.util.SoundPlayer;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameFrame {

    private JFrame window;
    private Container con;
    private JPanel menuPanel, userNamePanel, titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    private JLabel userNameLabel, titleNameLabel, ptLabel, ptLabelNumber, inventoryLabel, inventoryLabelName, skipLabel;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private JButton soundButton, startButton, choice1, choice2, choice3, choice4;
    private JTextArea mainTextArea;
    private int playerPT;
    private TitleScreenHandler tsHandler = new TitleScreenHandler();
    private ChoiceHandler choiceHandler = new ChoiceHandler();
    private String position, player, previousRoom, currentRoom, mainText, firstChoice, secondChoice, thirdChoice, fourthChoice;
    private ArrayList<String> inventory = new ArrayList<>();
    private Boolean soundOn = true;
    private ImageIcon logo = new ImageIcon("resources/images/island.png");
    private ImageIcon gameMapImage,gameBgImage;
    private JLabel mapLabel = new JLabel();
    private JLabel imageBgLabel = new JLabel();
    private Audio audio = Audio.getInstance();
    private SoundPlayer sound = new SoundPlayer();
    private ReadFile readFile = new ReadFile();
    private JSONObject jsonObject;
    private Object obj;
    private JSONParser parser = new JSONParser();
    private int dealerHand = 0;
    private int playerHand = 0;
    private int card = 0;
    private int losses = 0;
    private int magicQuizCorrect = 0;
    private int skips = 3;
    private Boolean magicQuizDone = false;
    Color bg = Color.black;
    Color skyBlue = new Color(177, 251, 244);
    Color darkTeal = new Color(15, 56, 67);
    Color mintGreen = new Color(46, 226, 109);
    Color seaGreen = new Color(12, 168, 153);
    Color goldenRod = new Color(195, 178, 70);




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

    public Boolean getMagicQuizDone() {
        return magicQuizDone;
    }

    public void setMagicQuizDone(Boolean magicQuizDone) {
        this.magicQuizDone = magicQuizDone;
    }

    public int getPlayerPT() {
        return playerPT;
    }

    public void setPlayerPT(int playerPT) {
        this.playerPT = playerPT;
    }

    public int getSkips() {
        return skips;
    }

    public void setSkips(int skips) {
        this.skips = skips;
    }

    // Ctor
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    // Ctor - creates the frame for the game
    public GameFrame() {


        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(seaGreen);
        window.setLayout(null);
        window.setIconImage(logo.getImage());
        con = window.getContentPane();

        Clip themeSong = sound.play("start",true,0,GameFrame.class);
        //add sound to game play
        soundButton = new JButton("🔈 on/off");
        soundButton.setBackground(darkTeal);
        soundButton.setForeground(skyBlue);
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


        menuPanel = new JPanel();
        menuPanel.setBounds(15,7,200,50);
        menuPanel.setBackground(seaGreen);

        userNamePanel = new JPanel();
        userNamePanel.setBounds(350,250,250,125);
        userNamePanel.setBackground(seaGreen);
        userNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(175, 100, 600, 150);
        titleNamePanel.setBackground(seaGreen);
        titleNameLabel = new JLabel("Force Majeure");
        titleNameLabel.setForeground(skyBlue);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(370, 400, 200, 100);
        startButtonPanel.setBackground(seaGreen);

        startButton = new JButton("START");
        startButton.setBackground(goldenRod);
        startButton.setForeground(seaGreen);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        userNameLabel = new JLabel("Enter username");
        userNameLabel.setForeground(skyBlue);
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
    /* creates the components to be added onto the frame */
    public void createGameScreen() {
        userNamePanel.setVisible(false);
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(220, 75, 600, 425);
        mainTextPanel.setBackground(seaGreen);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea(
                "Oops...the text is not showing.");
        mainTextArea.setBounds(225, 500, 500, 300);
        mainTextArea.setBackground(seaGreen);
        mainTextArea.setForeground(darkTeal);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(350, 515, 300, 125);
        choiceButtonPanel.setBackground(seaGreen);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(choiceButtonPanel);
        choice1 = new JButton("Choice 1");
        choice1.setBackground(darkTeal);
        choice1.setForeground(skyBlue);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(darkTeal);
        choice2.setForeground(skyBlue);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(darkTeal);
        choice3.setForeground(skyBlue);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(darkTeal);
        choice4.setForeground(skyBlue);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        playerPanel = new JPanel();
        playerPanel.setBounds(250, 0, 600, 50);
        playerPanel.setBackground(seaGreen);
        playerPanel.setLayout(new GridLayout(1, 4));
        con.add(playerPanel);
        ptLabel = new JLabel("Points:");
        ptLabel.setFont(normalFont);
        ptLabel.setForeground(skyBlue);
        playerPanel.add(ptLabel);
        ptLabelNumber = new JLabel();
        ptLabelNumber.setFont(normalFont);
        ptLabelNumber.setForeground(skyBlue);
        playerPanel.add(ptLabelNumber);
        inventoryLabel = new JLabel("Inventory:");
        inventoryLabel.setFont(normalFont);
        inventoryLabel.setForeground(skyBlue);
        inventoryLabel.setBackground(darkTeal);
        playerPanel.add(inventoryLabel);
        inventoryLabelName = new JLabel();
        inventoryLabelName.setFont(normalFont);
        inventoryLabelName.setForeground(skyBlue);
        playerPanel.add(inventoryLabelName);
        skipLabel = new JLabel();
        skipLabel.setFont(normalFont);
        skipLabel.setForeground(skyBlue);
        playerPanel.add(skipLabel);
        playerSetup();


    }

    /* create player's data */
    public void playerSetup() {
        inventory.add("Map");
        inventoryLabelName.setText(inventory.get(0));
        ptLabelNumber.setText("" + getPlayerPT());
        skipLabel.setText("Skips: " + getSkips());
        //start off with dock
        dock();
    }

    /* create image for game background and map */
    public ImageIcon setImage(String roomName, boolean isMap){
        ImageIcon anImage = new ImageIcon();
        //isMap then set map to image else set bg of room to image
        // When time is available: refactor to hashmap
        switch (roomName){
            case "dock":
                // show dock image
                anImage = isMap ? new ImageIcon("resources/images/map/VisitDock/DockMap.jpg") : new ImageIcon("resources/images/dock.jpg") ;
                break;
            case "beach":
                anImage = isMap ? new ImageIcon("resources/images/map/VisitDock/BeachMap.jpg") : new ImageIcon("resources/images/beach.jpg");
                break;
            case "rennie":
                // show talkInstructor image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/BeachMap.jpg") : new ImageIcon("resources/images/rennie.jpg");
                break;
            case "lobby":
                // show lobby image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/LobbyMap.jpg") : new ImageIcon("resources/images/lobby.jpg");
                break;
            case "nelly":
                // show talkInstructor image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/LobbyMap.jpg") : new ImageIcon("resources/images/nelly.jpg");
                break;
            case "hall":
                // show hall image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/HallMap.png") : new ImageIcon("resources/images/hall.jpg");
                break;
            case "restaurant":
            case "karl":
                // show restaurant image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/RestaurantMap.png") : new ImageIcon("resources/images/restaurant.jpg");
                break;
            case "gameFloor":
            case "jay":
            case "checkcards":
            case "blackjackfirsthand":
            case "blackjackstart":
                // show theater image
                anImage = isMap ? new ImageIcon("resources/images/map/VisitDock/GameFloorMap.jpg") :  new ImageIcon("resources/images/casinofloor.jpg");
                break;
            case "theater":
                // show theater image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/TheaterMap.jpg") : new ImageIcon("resources/images/theaterstage.jpg");
                break;
            case "chad":
                // show theater image
                anImage =  isMap ? new ImageIcon("resources/images/map/VisitDock/TheaterMap.jpg") : new ImageIcon("resources/images/chad.jpg");
                break;
        }
        return anImage;
    }

    /* set up panel to display room's text and image */
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

        setCurrentRoom(pos);
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

        System.out.println("🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴\n" + "🌴 Previous room: "+ getPreviousRoom() + "\n🌴 Current room: " + getCurrentRoom() + "\n🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴🌴");
    }

    /* create map to show image of location after map button is clicked */
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

    /* pulls the JSON data and creates a panel based on room location */
    public void createPanelScene(String pos){

        while(true){
            setJsonObject(readFile.retrieveJson("data/location.json"));
            HashMap<String, String> gameMap = (HashMap<String, String>) getJsonObject().get(pos);
            String choiceThree = gameMap.get("choiceThree");
            for(Object room : getJsonObject().keySet()){
                if(room.toString().equals(pos)){
                    String mainTxt = gameMap.get("maintext");
                    choiceThree = gameMap.get("c3");
                    if(pos.matches("rennie||nelly||karl||jay||chad")){
                        mainTxt = getPlayer() + gameMap.get("maintext");
                        if(inventory.contains("Key")){
                            choiceThree = "leave this B";
                        }
                    }
                    setTexts(pos,mainTxt,gameMap.get("c1"),gameMap.get("c2"),choiceThree,gameMap.get("c4"));/* set valuue of room here*/
                }
            }
            break;
        }

    }


    public void talkInstructor(String position) {
        switch (position) {
            case "rennie":
                createPanelScene("rennie");
                break;
            case "nelly":
                createPanelScene("nelly");
                break;
            case "karl":
                createPanelScene("karl");
                break;
            case "jay":
                createPanelScene("jay");
                break;
            case "chad":
                createPanelScene("chad");
                break;
        }
    }

    public void miniGame(){createPanelScene("miniGame");}
    public void dock() {
        createPanelScene("dock");
    }
    public void beach() {
        createPanelScene("beach");
    }
    public void lobby() {
        createPanelScene("lobby");
    }
    public void hall() {
        createPanelScene("hall");
    }
    public void restaurant() {
        createPanelScene("restaurant");
    }
    public void gameFloor() {
        choice2.setVisible(true);
        choice3.setVisible(true);
        choice4.setVisible(true);
        createPanelScene("gameFloor");
    }

    public void blackJackStart() {
        setPlayerHand(0);
        setDealerHand(0);
        if (getLosses() < 5) {
            setTexts("blackjackstart", "Jay cracks his fingers and begins to shuffle the deck. Surprised? Ready for a game?", "Let's go!", "Not right now", "", "");
            choice3.setVisible(false);
            choice4.setVisible(false);
        } else if (getLosses() >= 5) {
            setTexts("blackjackstart", "I think it's best you lay off the tables for today. You have too many losses", "", "Return to Game Floor", "", "");
            choice1.setVisible(false);
            choice3.setVisible(false);
            choice4.setVisible(false);
        }
    }

    public void blackjackDeal() {
        setDealerHand(ThreadLocalRandom.current().nextInt(13, 21));
        for (int playerCount = 0; playerCount < 2; playerCount++) {
            card = ThreadLocalRandom.current().nextInt(1, 11);
            setPlayerHand(playerHand += card);
        }
    }

    public void blackJackRound() {
        if (getPlayerHand() < 22) {
            setTexts("blackjackfirsthand", "Here is your hand: " + getPlayerHand(), "Hit me", "Stay", "", "");
            choice3.setVisible(false);
            choice4.setVisible(false);
        } else if (getPlayerHand() > 21) {
            setPlayerPT(getPlayerPT() - 2);
            ptLabelNumber.setText("" + getPlayerPT());
            setLosses(getLosses() + 1);
            setTexts("checkcards", "Your hand: " + getPlayerHand() + "\n You busted! Better luck next time", "Return to Game Floor", "", "", "");
            choice2.setVisible(false);
            choice3.setVisible(false);
            choice4.setVisible(false);
        }
    }

    public void hitMe() {
        card = ThreadLocalRandom.current().nextInt(1, 11);
        setPlayerHand(playerHand += card);
    }

    public void checkCards() {
        if (getPlayerHand() > getDealerHand()) {
            setPlayerPT(getPlayerPT() + 3);
            ptLabelNumber.setText("" + getPlayerPT());
            setTexts("checkcards", "Dealers Hand : " + getDealerHand() +" \n It's your lucky day! You get 3 points", "Return to Game Floor", "", "", "");
            choice2.setVisible(false);
            choice3.setVisible(false);
            choice4.setVisible(false);

        } else if (getPlayerHand() < getDealerHand()) {
            setPlayerPT(getPlayerPT() - 2);
            ptLabelNumber.setText("" + getPlayerPT());
            setLosses(getLosses() + 1);
            setTexts("checkcards", "Dealers Hand : " + getDealerHand() + "\n Better luck next time.", "Return to Game Floor", "", "", "");
            choice2.setVisible(false);
            choice3.setVisible(false);
            choice4.setVisible(false);
        } else if (getPlayerHand() == getDealerHand()) {
            setTexts("checkcards", "Dealers Hand : " + getDealerHand() + "\n Didn't they tell you? The House always wins. We won't take any points off though.", "Return to Game Floor", "", "", "");
        }
    }

    public void theater() {
        if (!blackjackPlayed) {
            createPanelScene("theater");
            choice2.setVisible(false);
        }else if (blackjackPlayed){
            createPanelScene("theater");
            choice2.setText("Talk to Magician Chad");
        }
        choice3.setVisible(false);
        choice4.setVisible(true);
    }

    // When you're in the theater, this is where the Magician prompts you to play only if you haven't played this game already
    public void magicQuizAsk() {
        if (!getMagicQuizDone()) {
            setTexts("magicQuizAsk", "Hello " + getPlayer() + ", would you like to answer some questions? ", "Sure!", "No Thanks", "", "");
            choice3.setVisible(false);
            choice4.setVisible(false);
        }
        else if (getMagicQuizDone()) {
            setTexts("magicQuizAsk", "It seems like you've already answered my questions. Head to another person to chat", "", "Return to Theater", "", "");
            choice1.setVisible(false);
            choice3.setVisible(false);
            choice4.setVisible(false);
        }
    }
    // For each question we check to make sure that the player still has some skips left.
    public void magicQuestionOne() {
        choice4.setVisible(true);
        if (getSkips() > 0) {
            setTexts("magicQuestionOne", "Question 1: Which of the following is the correct extension of the Python file?", ".python", ".py", ".p", "Skip");
        }
        else if (getSkips() >= 3) {
            setTexts("magicQuestionOne", "Question 1: Which of the following is the correct extension of the Python file?", ".python", ".py", ".p", "");

        }
    }
    public void magicQuestionTwo() {
        if (getSkips() > 0) {
            setTexts("magicQuestionTwo", "Question 2: Which character is used in Python to make a single line comment?", "//", "#", "!", "Skip");
        }
        else if (getSkips() <= 0) {
            setTexts("magicQuestionTwo", "Question 2: Which character is used in Python to make a single line comment?", "//", "#", "!", "");

        }
    }
    public void magicQuestionThree() {
        if (getSkips() > 0) {
            setTexts("magicQuestionThree", "Question 3: Which of the following functions is a built-in function in python language?", "print()", "System.out.println", "val()", "Skip");
            choice4.setVisible(true);
        }
        else if (getSkips() <= 0) {
            setTexts("magicQuestionThree", "Question 3: Which of the following functions is a built-in function in python language?", "print()", "System.out.println", "Val()", "");
            choice4.setVisible(false);
        }
    }
    public void magicQuestionFour() {
        if (getSkips() > 0) {
            setTexts("magicQuestionFour", "Question 4: What would be the output of the following function? \nlen([\"hello\",2, 4, 6])", "Error", "4", "3", "Skip");
            choice4.setVisible(true);
        }
        else if (getSkips() <= 0) {
            setTexts("magicQuestionFour", "Question 4: What would be the output of the following function? \nlen([\"hello\",2, 4, 6])", "Error", "4", "3", "");
            choice4.setVisible(false);
        }
    }
    public void magicQuestionFive() {
        if (getSkips() > 0) {
            setTexts("magicQuestionFive", "Question 5: print(\"abc. DEF\".capitalize())", "Abc. Def", "ABC. DEF", "Abc. def", "Skip");
            choice4.setVisible(true);
        }
        else if (getSkips() <= 0) {
            setTexts("magicQuestionFive", "Question 5:  print(\"abc. DEF\".capitalize())", "Abc. Def", "ABC. DEF", "Abc. def", "");
            choice4.setVisible(false);
        }
    }
    // Evaluating the players score, they need to get past the threshold in order to consider this a win
    public void magicQuestionEnd() {
        if(getPlayerPT() >= 10 ) {
            inventory.add("Key");
            inventoryLabelName.setText(inventory.get(0) + ", " + inventory.get(1));
            setTexts("magicQuestionEnd","Your total points: " + getPlayerPT() + " , you did well enough to succeed here! You've received a key off the island.","Return to Theater","","","");
            setMagicQuizDone(true);
        }
        else if(getPlayerPT() < 10){
            setTexts("magicQuestionEnd","You got " + getPlayerPT() + " points. You probably should study up and give this another go, you need to get at least 10 points at the finish.","Return to Theater","","","");
        }
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }
    // Skips give the player the ability to skip the question without hurting the players score.
    public void skipQuestion() {
        setSkips(getSkips()-1);
        skipLabel.setText("Skips: " + getSkips());
    }
    // The first 3 questions will be easy and the last two will be hard. Easy questions give less points when correct.
    public void correctAnswerEasy() {
        setPlayerPT(getPlayerPT()+4);
        ptLabelNumber.setText("" + getPlayerPT());
    }
    public void correctAnswerHard() {
        setPlayerPT(getPlayerPT()+6);
        ptLabelNumber.setText("" + getPlayerPT());
    }
    // Wrong questions will subtract 2 points
    public void wrongAnswer() {
        setPlayerPT(getPlayerPT() - 2);
        ptLabelNumber.setText("" + getPlayerPT());
    }

    public void ending() {
    //if points greater than X amount then show ending
        setTexts("ending", "You unlock the boat with the key and the screen pixelates to black. As you take your VR goggles off, you're feeling exhausted from the challenges. Inside you feel a huge sense of accomplishment in your spirit. \n" +
                "You feel ready for whatever challenges may come across your journey as an SDE as you remember your training. ", "Leave the Island", "", "","");
    }

    public void scoreBoard(){
        //show bgImage of closing scene and show scoreboard when leaving the island
        System.out.println("Here is the scoreboard");
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
                            talkInstructor("rennie");
                            break;
                        case "c2":
                            beach();
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
                case "rennie":
                    switch (yourChoice) {
                        case "c1":
                            dock();
                            break;
                        case "c2":
                            miniGame();
                            break;
                        case "c3":
                            if(inventory.contains("Key")){
                                ending();
                            } else {
                                choice3.setContentAreaFilled(false);
                            }
                            break;
                        case "c4":
                            showMap("beach");
                            break;
                    }
                    break;
                case "beach":
                    switch (yourChoice) {
                        case "c1":
                            lobby();
                            break;
                        case "c2":
                            dock();
                            break;
                        case "c4":
                            showMap("lobby");
                            break;
                    }
                    break;
                case "lobby":
                    switch (yourChoice) {
                        case "c1":
                            hall();
                            break;
                        case "c2":
                            beach();
                        case "c3":
                            talkInstructor("nelly");
                            break;
                        case "c4":
                            showMap("lobby");
                            break;
                    }
                    break;
                case "nelly":
                    switch (yourChoice){
                        case "c1":
                            lobby();
                            break;
                        case "c2":
                            miniGame();
                            break;
                        case "c3":
                            if(inventory.contains("Key")){
                                ending();
                            } else {
                                choice3.setContentAreaFilled(false);
                            }
                            break;
                        case "c4":
                            showMap("lobby");
                            break;
                    }
                case "hall":
                    switch (yourChoice) {
                        case "c1":
                            restaurant();
                            break;
                        case "c2":
                            gameFloor();
                            break;
                        case "c3":
                            lobby();
                            break;
                        case "c4":
                            showMap("hall");
                            break;
                    }
                    break;
                case "restaurant":
                    switch (yourChoice) {
                        case "c1":
                            talkInstructor("karl");
                            break;
                        case "c2":
                            gameFloor();
                            break;
                        case "c3":
                            hall();
                            break;
                        case "c4":
                            showMap("restaurant");
                            break;
                    }
                    break;
                case "karl":
                    switch (yourChoice){
                        case "c1":
                            restaurant();
                            break;
                        case "c2":
                            miniGame();
                            break;
                        case "c4":
                            showMap("restaurant");
                            break;
                    }
                    break;
                case "gameFloor":
                    switch (yourChoice) {
                        case "c1":
                            talkInstructor("jay");
                            break;
                        case "c2":
                            restaurant();
                            break;
                        case "c3":
                            theater();
                            break;
                        case "c4":
                            showMap("gameFloor");
                            break;
                    }
                    break;
                case "jay":
                    switch (yourChoice){
                        case "c1":
                            gameFloor();
                            break;
                        case "c2":
                            blackJackStart();
                            break;
                        case "c4":
                            showMap("gameFloor");
                            break;
                    }
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
                case "theater":
                    switch (yourChoice) {
                        case "c1":
                            gameFloor();
                            break;
                        case "c2":
                            if (blackjackPlayed){
                                talkInstructor("chad");
                            }else {
                                choice2.setContentAreaFilled(false);
                            }
                        case "c4": showMap("theater");
                            break;
                    }
                    break;
                case "chad":
                    switch (yourChoice){
                        case "c1":
                            theater();
                            break;
                        case "c2":
                            //if beaten 21
                            magicQuizAsk();
                            break;
                        case "c4":
                            showMap("theater");
                            break;
                    }
                case "magicQuizAsk":
                    switch (yourChoice) {
                        case "c1":
                            magicQuestionOne();
                            break;
                        case "c2":
                            theater();
                            break;
                    }
                    break;
                case "magicQuestionOne":
                    switch (yourChoice) {
                        case "c1": case "c3":
                            wrongAnswer();
                            magicQuestionTwo();
                            break;
                        case "c2":
                            correctAnswerEasy();
                            magicQuestionTwo();
                            break;
                        case "c4":
                            skipQuestion();
                            magicQuestionTwo();
                            break;
                    }
                    break;
                case "magicQuestionTwo":
                    switch (yourChoice) {
                        case "c1": case "c3":
                            wrongAnswer();
                            magicQuestionThree();
                            break;
                        case "c2":
                            correctAnswerEasy();
                            magicQuestionThree();
                            break;
                        case "c4":
                            skipQuestion();
                            magicQuestionThree();
                            break;
                    }
                    break;
                case "magicQuestionThree":
                    switch (yourChoice) {
                        case "c1":
                            correctAnswerEasy();
                            magicQuestionFour();
                            break;
                        case "c2": case "c3":
                            wrongAnswer();
                            magicQuestionFour();
                            break;
                        case "c4":
                            skipQuestion();
                            magicQuestionFour();
                            break;
                    }
                    break;
                case "magicQuestionFour":
                    switch (yourChoice) {
                        case "c1": case "c3":
                            wrongAnswer();
                            magicQuestionFive();
                            break;
                        case "c2":
                            correctAnswerHard();
                            magicQuestionFive();
                            break;
                        case "c4":
                            skipQuestion();
                            magicQuestionFive();
                            break;
                    }
                    break;
                case "magicQuestionFive":
                    switch (yourChoice) {
                        case "c1": case "c2":
                            wrongAnswer();
                            magicQuestionEnd();
                            break;
                        case "c3":
                            correctAnswerHard();
                            magicQuestionEnd();
                            break;
                        case "c4":
                            skipQuestion();
                            magicQuestionEnd();
                            break;
                    }
                    break;
                case "magicQuestionEnd":
                    switch (yourChoice) {
                        case "c1":
                            theater();
                            break;
                    }
                    break;
                case "ending":
                    switch(yourChoice){
                        case "c1":
                            scoreBoard();
                            break;
                    }
            }
        }
    }
}

