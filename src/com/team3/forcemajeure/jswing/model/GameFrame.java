package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.jswing.controller.*;
import com.team3.forcemajeure.util.SoundPlayer;
import java.awt.*;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameFrame {
    private static final GameFrame INSTANCE = new GameFrame();
    private JFrame window;
    private Container con;
    private JPanel magicTextPanel, menuPanel, userNamePanel, titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    public JLabel  inventoryLabel, inventoryLabelName,ptLabelNumber,skipLabel;
    private JLabel userNameLabel, titleNameLabel, ptLabel;
    private Font titleFont = new Font("Impact", Font.PLAIN, 80);
    private Font menuBarFont = new Font("Impact", Font.PLAIN, 15);
    private Font choiceFont = new Font("Impact", Font.PLAIN, 14);
    private Font narrativeFont = new Font("Impact", Font.PLAIN, 20);
    private Font smallFont = new Font("Impact", Font.PLAIN, 12);
    public JButton  magicButton, soundButton, startButton, choice1, choice2, choice3, choice4;
    private  JTextField textField = new JTextField();
    private  JTextField magicTextField = new JTextField();
    private JTextArea mainTextArea, magicTextArea, jsTextArea;
    private int playerPT;
    public String position;
    private String magicWord, player, previousRoom, currentRoom, mainText, firstChoice, secondChoice, thirdChoice, fourthChoice;
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
    private Color bg = Color.black;
    private Color skyBlue = new Color(177, 251, 244);
    private Color darkTeal = new Color(15, 56, 67);
    private Color mintGreen = new Color(46, 226, 109);
    private Color seaGreen = new Color(12, 168, 153);
    private Color goldenRod = new Color(195, 178, 70);
    private int losses = 0;
    //JS - added condition for stack overflow game
    private Boolean soGameDone = false;
    private Boolean jsGameDone = false;
    private Boolean magicQuizDone = false;
    private Boolean magicWordCorrect = false;
    private Boolean gotMeal = false;

    // Ctor - creates the frame for the game
    GameFrame() {
        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(seaGreen);
        window.setLayout(null);
        window.setIconImage(logo.getImage());
        window.setResizable(false);
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
        startButton.setBackground(darkTeal);
        startButton.setForeground(skyBlue);
        startButton.setFont(narrativeFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        userNameLabel = new JLabel("Enter username");
        userNameLabel.setForeground(goldenRod);
        userNameLabel.setFont(menuBarFont);
        textField= new JTextField();
        textField.setPreferredSize(new Dimension(200,40));
        startButton.addActionListener(e -> {
            if(e.getSource() == startButton){
                String username = textField.getText().stripLeading().stripTrailing();
                if(username.length() > 0){
                    setPlayer(username);
                } else {
                    setPlayer("Unnamed player");
                }
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

    public static GameFrame getInstance() {
        return INSTANCE;
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

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public Boolean getSoGameDone() {
        return soGameDone;
    }

    public void setSoGameDone(Boolean soGameDone) {
        this.soGameDone = soGameDone;
    }

    public Boolean getJsGameDone() {
        return jsGameDone;
    }

    public void setJsGameDone(Boolean jsGameDone) {
        this.jsGameDone = jsGameDone;
    }

    public Boolean getMagicQuizDone() {
        return magicQuizDone;
    }

    public void setMagicQuizDone(Boolean magicQuizDone) {
        this.magicQuizDone = magicQuizDone;
    }

    public String getMagicWord() {
        return magicWord;
    }

    public void setMagicWord(String magicWord) {
        this.magicWord = magicWord;
    }

    public Boolean getMagicWordCorrect() {
        return magicWordCorrect;
    }

    public void setMagicWordCorrect(Boolean magicWordCorrect) {
        this.magicWordCorrect = magicWordCorrect;
    }

    public Font getMenuBarFont() {
        return menuBarFont;
    }

    public void setMenuBarFont(Font menuBarFont) {
        this.menuBarFont = menuBarFont;
    }

    public Font getChoiceFont() {
        return choiceFont;
    }

    public void setChoiceFont(Font choiceFont) {
        this.choiceFont = choiceFont;
    }

    public Font getNarrativeFont() {
        return narrativeFont;
    }

    public void setNarrativeFont(Font narrativeFont) {
        this.narrativeFont = narrativeFont;
    }

    public Font getSmallFont() {
        return smallFont;
    }

    public void setSmallFont(Font smallFont) {
        this.smallFont = smallFont;
    }

    public Boolean getGotMeal() {
        return gotMeal;
    }

    public void setGotMeal(Boolean gotMeal) {
        this.gotMeal = gotMeal;
    }

    //Business Methods
    /* creates the components to be added onto the frame */
    public void createGameScreen() {
        setMagicWord(magicWord);
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
        mainTextArea.setFont(narrativeFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        magicTextField = new JTextField();
        magicTextPanel = new JPanel();
        magicTextPanel.setBounds(220, 75, 600, 425);
        magicTextPanel.setBackground(seaGreen);
        magicTextArea = new JTextArea(
                "Oops...the text is not showing.");
        magicTextArea.setBounds(225, 500, 500, 300);
        magicTextArea.setBackground(seaGreen);
        magicTextArea.setForeground(darkTeal);
        magicTextArea.setFont(narrativeFont);
        magicTextArea.setLineWrap(true);
        magicTextArea.setWrapStyleWord(true);
        magicTextArea.setEditable(false);
        magicTextPanel.add(magicTextArea);
        con.add(magicTextPanel);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(350, 515, 300, 125);
        choiceButtonPanel.setBackground(seaGreen);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(choiceButtonPanel);
        choice1 = new JButton("Choice 1");
        choice1.setBackground(darkTeal);
        choice1.setForeground(skyBlue);
        choice1.setFont(choiceFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(darkTeal);
        choice2.setForeground(skyBlue);
        choice2.setFont(choiceFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(darkTeal);
        choice3.setForeground(skyBlue);
        choice3.setFont(choiceFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(darkTeal);
        choice4.setForeground(skyBlue);
        choice4.setFont(choiceFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);


        playerPanel = new JPanel();
        playerPanel.setBounds(250, 0, 680, 50);
        playerPanel.setBackground(seaGreen);
        playerPanel.setLayout(new GridLayout(1, 4));
        con.add(playerPanel);
        ptLabel = new JLabel("Points:");
        ptLabel.setFont(smallFont);
        ptLabel.setForeground(skyBlue);
        playerPanel.add(ptLabel);
        ptLabelNumber = new JLabel();
        ptLabelNumber.setFont(menuBarFont);
        ptLabelNumber.setForeground(goldenRod);
        playerPanel.add(ptLabelNumber);
        inventoryLabel = new JLabel("Inventory:");
        inventoryLabel.setFont(menuBarFont);
        inventoryLabel.setForeground(skyBlue);
        inventoryLabel.setBackground(darkTeal);
        playerPanel.add(inventoryLabel);
        inventoryLabelName = new JLabel();
        inventoryLabelName.setFont(menuBarFont);
        inventoryLabelName.setForeground(goldenRod);
        playerPanel.add(inventoryLabelName);
        skipLabel = new JLabel();
        skipLabel.setFont(menuBarFont);
        skipLabel.setForeground(skyBlue);
        playerPanel.add(skipLabel);
        setUp.playerSetup();

    }


    /* set up panel to display room's text and image */
    public void setTexts(String pos, String mainText, String choiceOne, String choiceTwo, String choiceThree,
                         String choiceFour) {

        if(pos.equals("preTheater")){
            magicTextField.setVisible(true);
            mainTextPanel.setVisible(false);
            magicTextPanel.setVisible(true);
            imageBgLabel.setVisible(true);
            choice3.setVisible(false);
            choice4.setVisible(false);
            gameBgImage = setUp.setImage("preTheater", false);
            magicTextField.setPreferredSize(new Dimension(200,40));
            imageBgLabel.setIcon(gameBgImage);
            magicTextPanel.add(magicTextField);
            magicTextPanel.add(imageBgLabel);
            magicTextArea.setText(mainText);
            setPreviousRoom(getCurrentRoom());

            choice2.addActionListener(e -> {
                String response = null;
                String magicTxtField =  magicTextField.getText().toLowerCase().stripLeading().stripTrailing();
                if (e.getSource() == choice2) {
                    if(magicTxtField.equals(getMagicWord())){
                        setMagicWordCorrect(true);
                        inventory.remove(magicTxtField);
                    } else {
                        response = ("Sorry, " + magicTxtField + " is not the magic word.");
                        if(magicTxtField.equals("I'M AN IDIOT") || magicTxtField.equals("IM AN IDIOT")){
                            response = ("You're an idiot!");
                        }
                        if(magicTxtField.equals("42")){
                            response = ("Yes, we know that is the meaning of life but it's not the magic word");
                        }
                        if(magicTxtField.equals("")){
                            response = ("No word was given");
                        }
                        magicTextArea.setText(response);

                    }
                }
            });
        } else {
            textField.setVisible(false);
            mainTextPanel.setVisible(true);
            magicTextPanel.setVisible(false);
            choice2.setVisible(true);
            choice3.setVisible(true);
            choice4.setVisible(true);

        //if room is null then set bgImage to dock else
        //get bg of image based on room
        if(getPreviousRoom() == null){
            gameBgImage = setUp.setImage("prelude", false);
            setPreviousRoom("prelude");
            choice2.setVisible(false);
            choice3.setVisible(false);
            choice4.setVisible(false);

            } else {
                gameBgImage = setUp.setImage(pos, false);
                setPreviousRoom(getCurrentRoom());
            choice2.setVisible(true);
            choice3.setVisible(true);
            choice4.setVisible(true);
            }
            mainTextPanel.add(imageBgLabel);
            mainTextArea.setText(mainText);
        }
        position = pos;
        choice1.setVisible(true);
        mapLabel.setVisible(false);
        imageBgLabel.setVisible(true);
        setCurrentRoom(pos);
        imageBgLabel.setIcon(gameBgImage);
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

