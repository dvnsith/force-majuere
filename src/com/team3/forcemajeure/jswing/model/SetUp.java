package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.util.*;
import org.json.simple.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;

public class SetUp {
    private GameFrame gameFrame;
    private final ReadFile readFile = new ReadFile();
    private MagicGame magicGame;
    private JavaScriptGame jsGame;
    private JSONObject jsonObject;
    private StackOverflowGame soGame;
    Player player = new Player();

    // Ctors
    public SetUp(){}

    public SetUp(GameFrame view){
        gameFrame = view;
        magicGame = new MagicGame(view);
        jsGame = new JavaScriptGame(view);
        soGame = new StackOverflowGame(view);
    }

    // accessor methods
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    // Business Methods
    /* create player's data */
    public void playerSetup() {
        gameFrame.inventory.add("Map");
        gameFrame.inventoryLabelName.setText(gameFrame.inventory.get(0));
        gameFrame.ptLabelNumber.setText("" + gameFrame.getPlayerPT());
        gameFrame.skipLabel.setText("Skips: " + player.getSkips());
        //start off with dock
        prelude();
    }

    /* create image for game background and map */
    public ImageIcon setImage(String roomName, boolean isMap){
        String imagePath;
        //isMap then set map to image else set bg of room to image
        // When time is available: refactor to hashmap
        switch (roomName){
            case "prelude":
                imagePath = "/images/vrset.jpg";
                break;
            case "dock":
                // show dock image
                imagePath = isMap ? "/images/map/VisitDock/DockMap.jpg" : "/images/dock.jpg";
                break;
            case "sign":
                imagePath = isMap ? "/images/map/VisitDock/DockMap.jpg" : "/images/docksign.jpg";
                break;
            case "beach":
                imagePath = isMap ? "/images/map/VisitDock/BeachMap.jpg" : "/images/beach.jpg";
                break;
            case "rennie":
                // show talkInstructor image
                imagePath = isMap ? "/images/map/VisitDock/BeachMap.jpg" : "/images/rennie.jpg";
                break;
            case "lobby":
                // show lobby image
                imagePath = isMap ? "/images/map/VisitDock/BeachMap.jpg" : "/images/lobby.jpg";
                break;
            case "nelly": case "jsStart": case "jsEnd2case": case "jsEnd2": case "jsQuestionThree": case "jsQuestionFour": case "jsQuestionTwo": case "jsEnd":
                // show talkInstructor image
                imagePath = isMap ? "/images/map/VisitDock/LobbyMap.jpg" : "/images/nelly.jpg";
                break;
            case "jsQuestionOne":
                imagePath = isMap ? "/images/map/VisitDock/LobbyMap.jpg" : "/images/js1.png";
                break;
            case "jsQuestionFive":
                imagePath = isMap ? "/images/map/VisitDock/LobbyMap.jpg" : "/images/js2.png";
                break;
            case "jsEnd1": case "jsNoStart":
                imagePath = isMap ? "/images/map/VisitDock/LobbyMap.jpg" : "/images/magicword.png";
                break;
            case "hall":
                // show hall image
                imagePath = isMap ? "/images/map/VisitDock/BeachMap.jpg" : "/images/hall.jpg";
                break;
            case "karl": case "soQuestionOne": case "soQuestionTwo": case "soQuestionThree":
                case "soQuestionFour": case "soQuestionFive": case "soStart": case "soEnd":
                imagePath = isMap ? "/images/map/VisitDock/RestaurantMap.jpg" : "/images/karl.jpg";
                break;
            case "ideSet":
                imagePath = isMap ? "/images/map/VisitDock/RestaurantMap.jpg" : "/images/ide.png";
                break;
            case "joke":
                imagePath = isMap ? "/images/map/VisitDock/RestaurantMap.jpg" : "/images/joke.png";
                break;
            case "restaurant": case "soNoStart":
                // show restaurant image
                imagePath = isMap ? "/images/map/VisitDock/RestaurantMap.jpg" : "/images/restaurant.jpg";
                break;
            case "jay":
                imagePath = isMap ? "/images/map/VisitDock/GameFloorMap.jpg" : "/images/blackjack.jpg";
                break;
            case "winBlackJack":
                imagePath = isMap ? "/images/map/VisitDock/GameFloor.jpg" : "images/win.jpeg";
                break;
            case "loseBlackJack":
                imagePath = isMap ? "/images/map/VisitDock/GameFloor.jpg" : "images/lose.jpeg";
                break;
            case "gameFloor":
            case "checkcards":
            case "blackjackfirsthand":
            case "blackjackstart":
                // show theater image
                imagePath = isMap ? "/images/map/VisitDock/GameFloorMap.jpg" : "/images/casinofloor.jpg";
                break;
            case "theater": case "preTheater":
                // show theater image
                imagePath = isMap ? "/images/map/VisitDock/TheaterMap.jpg" : "/images/theaterstage.jpg";
                break;
            case "chad":
            case "magicQuizAsk":
            case "magicQuestionOne":
            case "magicQuestionTwo":
            case "magicQuestionThree":
            case "magicQuestionFour":
            case "magicQuestionFive":
            case "magicQuestionEnd":
                // show theater image
                imagePath = isMap ? "/images/map/VisitDock/TheaterMap.jpg" : "/images/chad.jpg";
                break;
            case "ending":
                // show theater image
                imagePath = isMap ? "/images/map/VisitDock/TheaterMap.jpg" : "/images/rennie.jpg";
                break;
            default:
                imagePath = ("Unexpected value: " + roomName);
                break;
        }
        // load image to retrieve path in resources directory
        System.out.println(gameFrame.getMainText());
        URL imageUrl = getClass().getResource(imagePath);
        Image bgImage = Toolkit.getDefaultToolkit().getImage(imageUrl);
        return new ImageIcon(bgImage);
    }

    /* pulls the JSON data and creates a panel based on room location */
    public void createPanelScene(String pos) {

        while(true){
            setJsonObject(readFile.retrieveJson("data/location.json"));
            HashMap<String, String> gameMap = (HashMap<String, String>) getJsonObject().get(pos);
            String choiceOne, choiceTwo, choiceThree, choiceFour;

            for(Object room : getJsonObject().keySet()){
                if(room.toString().equals(pos)){
                    String mainTxt = gameMap.get("maintext");
                    choiceOne = gameMap.get("c1");
                    choiceTwo = gameMap.get("c2");
                    choiceThree = gameMap.get("c3");
                    choiceFour = gameMap.get("c4");

                    if(pos.matches("prelude")){
                        mainTxt = gameFrame.getPlayer() + mainTxt;
                    }

                    if(pos.matches("rennie")){
                        if(gameFrame.inventory.contains("Blueprint")){
                            choiceTwo = "Get key";
                            mainTxt = "In exchange for this blueprint, I'll hand you over the keys to begin your new career. Now go out there and remember to stay agile! 💪🏼";
                        }
                    }

                    // if pos = lobby && getBlackJack = true
                        // choice three = investigate noise (goes to nelly)
                    if(pos.matches("lobby") && gameFrame.getBlackjackPlayed().equals(true)) {
                        if(gameFrame.getJsGameDone().equals(true) && gameFrame.getMagicQuizDone().equals(true)){
                            mainTxt = "Looks like Nelly has gone to work on other projects for this island. You hear music playing on the beach, what could that be for?";
                        } else if (gameFrame.getJsGameDone().equals(true) && gameFrame.getMagicQuizDone().equals(false)) {
                            mainTxt = "With this newfound information, you need to hurry to the theater so you can make it in time for the magic show.";;
                        } else {
                            mainTxt = "As you make your way back into the lobby to turn in your ticket, you hear a familiar voice near the renovation project call out \"" + gameFrame.getPlayer() + ", come over here. I need some help please\"";
                            choiceThree = "Check out the voice";
                        }

                    }



                    if(pos.matches("beach") && gameFrame.getMagicQuizDone().equals(true)){
                        mainTxt = "The celebration to the start of your SDE journey has begun. There is one last thing you have to do. Go see Rennie";
                    }

                    if(pos.matches("gameFloor")){
                        if(gameFrame.getBlackjackPlayed().equals(true)){
                            mainTxt = "You've won a ticket for the magic show ticket drawing. You need to go to the lobby and check to see if you've won!";
                        }
                        if(gameFrame.getMagicQuizDone().equals(true)){
                            mainTxt = "There's nothing else to do but to keep exploring or play a few rounds of Blackjack";
                        }
                    }


                    if(pos.matches("theater") && gameFrame.getJsGameDone()){
                        if(gameFrame.getMagicQuizDone().equals(true)){
                            choiceTwo = "";
                            mainTxt = "The show has ended! Go find Nelly and give him the blueprint.";
                        } else {
                            choiceTwo = "Go to the magician";
                        }
                        System.out.println("JS Game played: " + jsGame.getJsGameDone());
                    }

                    if (pos.matches("restaurant")) {

                        if (gameFrame.getLosses() >= 5) {
                            mainTxt = "Hey, you're looking down. Some spaghetti and pepsi should get back in the right spirits. It's on the house! Come visit us after you've won a hand at blackjack and give us a review.";
                            gameFrame.setLosses(0);
                        }
                        if (gameFrame.getBlackjackPlayed().equals(true) && gameFrame.getSoGameDone().equals(false)) {
                            mainTxt = "'So glad you're here, can you come assist our chef in the back?', said the host. 'There's currently a culinary catastrophe going on! Hold off on that review too for now please.' \n You ponder to yourself if it's necessary to help here.";
                            choiceThree = "Investigate the issue";
                            gameFrame.choice3.setVisible(true);
                        }
                        if (gameFrame.getSoGameDone().equals(true)) {
                            mainTxt = "A sign hangs on the door that says:\n\n**CLOSED**\nSetting IDEs & Rebuilding Furniture\n\n";
                            choiceOne = "Return to Hall";
                            choiceTwo = "Return to Game Floor";
                            gameFrame.choice3.setVisible(false);
                            gameFrame.choice4.setVisible(false);
                        }
                    }


                    gameFrame.setTexts(pos,mainTxt,choiceOne,choiceTwo,choiceThree,choiceFour);/* set valuue of room here*/

                }
            }
            break;
        }
    }

    public void talkInstructor(String position) {
        switch (position) {
            case "rennie":
                createPanelScene("rennie");
                if(gameFrame.getMagicQuizDone().equals(true)){
                    gameFrame.choice2.setVisible(true);
                } else {
                    gameFrame.choice2.setVisible(false);
                    gameFrame.choice3.setVisible(false);
                }
                break;
            case "nelly":
                createPanelScene("nelly");
                gameFrame.choice3.setVisible(false);
                gameFrame.choice4.setVisible(false);
                break;
            case "karl":
                createPanelScene("karl");
                gameFrame.choice3.setVisible(false);
                break;
            case "jay":
                createPanelScene("jay");
                gameFrame.choice3.setVisible(false);
                gameFrame.choice4.setVisible(false);
                break;
            case "chad":
                createPanelScene("chad");
                gameFrame.choice3.setVisible(false);
                break;
        }
    }

    public void getKey(){
        // set main text with key
//        gameFrame.setTexts("rennie", "Now that you've go the key...", "Go back","Get ready to leave", "", "");
        gameFrame.setTexts("ending", "You unlock the boat with the key and the screen pixelates to black. As you take your VR goggles off, you're feeling exhausted from the challenges. Inside you feel a huge sense of accomplishment in your spirit. \n" +
                "You feel ready for whatever challenges may come across your journey as an SDE as you remember your training. ", "scoreboard", "", "","");
        gameFrame.choice1.setVisible(true);
        gameFrame.choice2.setVisible(false);
        gameFrame.choice3.setVisible(false);
        gameFrame.choice4.setVisible(false);

        gameFrame.inventory.remove("Blueprint");
        gameFrame.inventory.add("Key");
        gameFrame.inventoryLabelName.setText(gameFrame.inventory.get(0) + ", " + gameFrame.inventory.get(1) + " ");
    }


    public void prelude(){
        createPanelScene("prelude");
    }
    public void dock() {
        createPanelScene("dock");
        gameFrame.choice3.setVisible(false);
    }
    public void sign(){
        createPanelScene("sign");
        gameFrame.choice2.setVisible(false);
        gameFrame.choice3.setVisible(false);
        gameFrame.choice4.setVisible(false);
    }
    public void beach() {
        createPanelScene("beach");
    }
    public void lobby() {
        createPanelScene("lobby");
        if (gameFrame.getBlackjackPlayed().equals(true) && gameFrame.getJsGameDone().equals(false)){
            gameFrame.choice3.setVisible(true);
        } else {
            gameFrame.choice3.setVisible(false);
        }

    }
    public void hall() {
        createPanelScene("hall");
    }
    public void restaurant() {
        createPanelScene("restaurant");
    }
    public void gameFloor() {
        createPanelScene("gameFloor");
    }

    public void theater() {
        createPanelScene("theater");
        gameFrame.choice3.setVisible(false);
        if(gameFrame.getMagicQuizDone()){
            gameFrame.choice2.setVisible(false);
        }
    }

    public void preTheater() {
        createPanelScene("preTheater");

    }

    public void ending() {
        //if points greater than X amount then show ending
        gameFrame.setTexts("ending", "You unlock the boat with the key and the screen pixelates to black. As you take your VR goggles off, you're feeling exhausted from the challenges. Inside you feel a huge sense of accomplishment in your spirit. \n" +
                "You feel ready for whatever challenges may come across your journey as an SDE as you remember your training. ", "scoreboard", "", "","");
        gameFrame.choice1.setVisible(true);
        gameFrame.choice2.setVisible(false);
        gameFrame.choice3.setVisible(false);
        gameFrame.choice4.setVisible(false);
    }

    public void scoreBoard(){
        //show bgImage of closing scene and show scoreboard when leaving the island
        System.out.println("Here is the scoreboard");
    }

}
