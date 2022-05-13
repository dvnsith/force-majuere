package com.team3.forcemajeure.jswing;

import com.team3.forcemajeure.util.Audio;

import javax.swing.*;
import java.awt.*;
import com.team3.forcemajeure.util.*;
import java.util.Map;


public class GameFrame {
    boolean soundOn = true;
    Audio audio = Audio.getInstance();
    boolean gameRunning = true;
    String inputOne;
    TextParser.WriteText write = new TextParser.WriteText();
//    private static String commandInput;

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public String getInputOne() {
        return inputOne;
    }

    public void setInputOne(String inputOne) {
        this.inputOne = inputOne;
    }

//    public static String getCommandInput() {
//        return commandInput;
//    }
//
//    public static void setCommandInput(String commandInput) {
//        GameFrame.commandInput = commandInput;
//    }

    public GameFrame(){

        // Images
        ImageIcon bgImage = new ImageIcon("resources/images/dock.jpg");
        Image image = new ImageIcon("resources/images/island.png").getImage();



        // Panels
        JPanel panelOne = new JPanel();
        panelOne.setBounds(50,10,400,50);

        JPanel panelTwo = new JPanel();
        panelTwo.setLayout(new GridLayout());
        panelTwo.setBounds(550,10,400,50);

        JPanel panelThree = new JPanel();
        panelThree.setBounds(100,70,800,410);

        JPanel panelFour = new JPanel();
        panelFour.setBounds(250,500,500,125);


        // Buttons
        JButton soundButton = new JButton("🔈 on/off");
        soundButton.setBounds(25,100,50,50);
        soundButton.addActionListener(e -> {
            if(isSoundOn()) {
                System.out.println("Sound Off");
                setSoundOn(false);
            } else {
                System.out.println("Sound on");
                setSoundOn(true);
            }
        });

        JButton restartButton = new JButton("🔁 restart");
        restartButton.setBounds(25,100,50,50);
        restartButton.addActionListener(e -> System.out.println("Restarting game"));

        JButton quitButton = new JButton("⛱ quit");
        quitButton.setBounds(50,100,50,50);
        quitButton.addActionListener(e -> System.out.println("Quitting game"));

        JButton btnNorth = new JButton();
        btnNorth.setText("⬆");
        btnNorth.setVerticalTextPosition(JButton.TOP);
        btnNorth.setBounds(805,500,50,50);
        btnNorth.addActionListener(e -> {
                    write.inputText("go north");

                }
               );


        JButton btnSouth = new JButton();
        btnSouth.setText("⬇");
        btnSouth.setBounds(805,550,50,50);
        btnSouth.addActionListener(e -> {
                    write.inputText("go south");
                }
        );

        JButton btnEast = new JButton();
        btnEast.setText("➡");
        btnEast.setBounds(855,530,50,50);
        btnEast.addActionListener(e -> {
                    write.inputText("go east");

                }
        );

        JButton btnWest = new JButton();
        btnWest.setText("⬅");
        btnWest.setBounds(755,530,50,50);
        btnWest.addActionListener(e -> {
                    write.inputText("go west");
                }
        );
        // input into text and output value in class

        // userInput
        JButton textButton = new JButton("submit");
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300,40));
        textButton.addActionListener(e -> {
            if(e.getSource() == textButton){
                System.out.println(textField.getText());
                write.inputText(textField.getText());
            }
        });


        // Labels
        JLabel labelOne = new JLabel();
        labelOne.setText("10/100 pts");
        labelOne.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel labelInventory = new JLabel();
        labelInventory.setText("Inventory");
        labelInventory.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel labelTwo = new JLabel();
        labelTwo.setLayout(new GridLayout());
        labelTwo.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        labelTwo.setIcon(bgImage);

        JLabel inputText = new JLabel();
        inputText.setText(write.getInputString());
        inputText.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel outputText = new JLabel();
        outputText.setText(write.getOutputString());
        outputText.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        // Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(image);
        frame.setLayout(null);
        frame.setSize(1000,1000);
        frame.setResizable(true);
        frame.setVisible(true);


        // Adding components together
        panelOne.add(soundButton);
        panelOne.add(restartButton);
        panelOne.add(quitButton);
        panelTwo.add(labelOne);
        panelTwo.add(labelInventory);
        panelThree.add(labelTwo);
        panelFour.add(textButton);
        panelFour.add(textField);
        panelFour.add(inputText);
        panelFour.add(outputText);
        frame.add(panelOne);
        frame.add(panelTwo);
        frame.add(panelThree);
        frame.add(btnWest);
        frame.add(btnNorth);
        frame.add(btnSouth);
        frame.add(btnEast);
        frame.add(panelFour);

//        frame.pack();

    }

    public void createGameScreen(){

    }

    public void runGame() {
//        Sets up all objects for the game
        Map<String, Room> roomMap = Data.getRoomMap();
        Map<String, NPC> npcMap = Data.getNpcMap();
        Map<String, Endings> endingsMap = Data.getEndingMap();
        // get starting room ("Water")
        Room startRoom = roomMap.get("WaterWreckage");
        // init com.team3.forcemajeure.util.Player
        Player player = new Player();
        // set player's current room to start room
        player.setCurRoom(startRoom);
//        TODO put enemy in an array that we loop through for encounters
//        TODO enemies are created from a JSON
//      Enemy Creation
        Enemy gambler = new Enemy("gambler", 10, roomMap.get("WaitingRoom"),
                "You thought you could just get the boatkey, Ha! lets roll for it with dice",
                "Come back when you are feeling lucky",
                "I am so embarassed I lost",
                "boatkey", player);

        while (gameRunning) {
//            check if ending game condition have been met
            if (player.getPlayerInventory().getInventory().contains("endgame")){
                Data.clearInventory();
                break;
            }
            if (player.getPlayerInventory().getInventory().contains("loopgame")){
                Data.clearInventory();
                runGame();
            }

            // get current room
            Room curRoom = player.getCurRoom();
            // play room audio
            audio.play(curRoom.getName().toLowerCase());
            // display room info
            curRoom.displayRoomInfo();
//            prints story element for doctors office
            if ((!player.getPlayerInventory().getInventory().contains("larson") &&
                    !player.getPlayerInventory().getInventory().contains("karma"))){
                System.out.println(player.getCurRoom().getStory());
            }
//            checks if enemy is around
//            TODO loop through enemy of array when that is implemented
//            if (player.getCurRoom().equals(gambler.getLocation())) {
//                gambler.talk();
//                if (gambler.getHealth() > 0) {
//                    Battle.battle(gambler, player, 3);
//                }
//            }
//            prompt User
            String response = TextParser.gameScannerInput();
            // response needs to be from actionlistenr
            String response2 = write.getInputString();
            clrScreen();
            if ("mute".equals(response2)) {
                audio.toggleMute();
            } else {
                TextParser.gameScannerOutput(response2, player, roomMap, npcMap, endingsMap);
            }
        }
    }

    public void clrScreen(){
        //TODO DS CLEAR SCREEN FUNCTION
        for (int i = 0; i < 60; ++i){
            System.out.println();
        }
    }


}
