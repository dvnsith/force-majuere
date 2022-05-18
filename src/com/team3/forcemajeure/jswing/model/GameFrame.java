package com.team3.forcemajeure.jswing.model;


import com.team3.forcemajeure.jswing.controller.ChoiceHandler;
import com.team3.forcemajeure.jswing.controller.TitleScreenHandler;
import com.team3.forcemajeure.util.SoundPlayer;
import java.awt.*;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameFrame {

    private JFrame window;
    private Container con;
    private JPanel menuPanel, userNamePanel, titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    public JLabel inventoryLabel, inventoryLabelName,ptLabelNumber,skipLabel;
    private JLabel userNameLabel, titleNameLabel, ptLabel;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    public JButton soundButton, startButton, choice1, choice2, choice3, choice4;
    private JTextArea mainTextArea;
    private int playerPT;
    public String position;
    private String player, previousRoom, currentRoom, mainText, firstChoice, secondChoice, thirdChoice, fourthChoice;
    public ArrayList<String> inventory = new ArrayList<>();
    private Boolean soundOn = true;
    private ImageIcon logo = new ImageIcon("resources/images/island.png");
    private ImageIcon gameMapImage,gameBgImage;
    private JLabel mapLabel = new JLabel();
    private JLabel imageBgLabel = new JLabel();
    private SoundPlayer sound = new SoundPlayer();
    private ChoiceHandler choiceHandler = new ChoiceHandler(this);
    private TitleScreenHandler tsHandler = new TitleScreenHandler(this);
    private SetUp setUp = new SetUp(this);
    private Boolean blackjackPlayed = false;
    Color bg = Color.black;
    Color skyBlue = new Color(177, 251, 244);
    Color darkTeal = new Color(15, 56, 67);
    Color mintGreen = new Color(46, 226, 109);
    Color seaGreen = new Color(12, 168, 153);
    Color goldenRod = new Color(195, 178, 70);


    // Ctor - creates the frame for the game
    public GameFrame() {
        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(seaGreen);
        window.setLayout(null);
        window.setIconImage(logo.getImage());
        con = window.getContentPane();

        Clip themeSong = sound.play("start",true,0, GameFrame.class);
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

    public int getPlayerPT() {
        return playerPT;
    }

    public void setPlayerPT(int playerPT) {
        this.playerPT = playerPT;
    }

    public Boolean getBlackjackPlayed() {
        return blackjackPlayed;
    }

    public void setBlackjackPlayed(Boolean blackjackPlayed) {
        this.blackjackPlayed = blackjackPlayed;
    }

    //Business Methods
    /* creates the components to be added onto the frame */
    public void createGameScreen() {
        userNamePanel.setVisible(false);
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(220, 75, 600, 425);
        mainTextPanel.setBackground(Color.black);
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
        setUp.playerSetup();


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
            gameBgImage = setUp.setImage("dock", false);
            setPreviousRoom("dock");

        } else {
            gameBgImage = setUp.setImage(pos, false);
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

        gameMapImage = setUp.setImage(getCurrentRoom(), true);

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

}

