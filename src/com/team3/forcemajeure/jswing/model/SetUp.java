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

        while (true) {
            setJsonObject(readFile.retrieveJson("data/location.json"));
            HashMap<String, String> gameMap = (HashMap<String, String>) getJsonObject().get(pos);
            String choiceOne, choiceTwo, choiceThree, choiceFour;

            for (Object room : getJsonObject().keySet()) {
                if (room.toString().equals(pos)) {
                    String mainTxt = gameMap.get("maintext");
                    choiceOne = gameMap.get("c1");
                    choiceTwo = gameMap.get("c2");
                    choiceThree = gameMap.get("c3");
                    choiceFour = gameMap.get("c4");

                    if (pos.matches("prelude")) {
                        mainTxt = gameFrame.getPlayer() + mainTxt;
                    }

                    if (pos.matches("rennie")) {
                        mainTxt = gameFrame.getPlayer() + gameMap.get("maintext");
                        if (gameFrame.inventory.contains("Blueprint")) {
                            choiceThree = "leave this island";
                        }
                    }


                    if (pos.matches("lobby") && gameFrame.getJsGameDone().equals(true)) {
                        mainTxt = "Nelly has left the lobby.";
                        choiceOne = "Return to the Lobby.";
                    }
                    else if(pos.matches("lobby") && gameFrame.getBlackjackPlayed().equals(true)) {
                        mainTxt = "It seems like Nelly may be looking for you.";
                        choiceThree = "Check in with Nelly";
                        }

                    // if pos = dock && inventory contains key
                    // choice two = ending()
                    // mainText = "some message about key and boat"

                    if (pos.matches("theater") && gameFrame.getJsGameDone().equals(true)) {
                        if (gameFrame.getMagicQuizDone().equals(true)) {
                            choiceTwo = "";
                            mainTxt = "Looks like the show is over";

                        }

                    }

                    if (pos.matches("restaurant") && gameFrame.getLosses() >= 5) {
                        choiceThree = "Order Spaghetti & Pepsi";
                        mainTxt = "Long day? How about we give you an order of Spaghetti and Pepsi. It's on the house!";
                        gameFrame.setLosses(0);
                    }
                    if (pos.matches("restaurant") && gameFrame.getBlackjackPlayed().equals(true) && gameFrame.getSoGameDone().equals(false)) {
                        mainTxt = "There seems to be an issue in the restaurant, you can hear the chef shouting from the back, there appears to be an issue with the ordering system. You can investigate the issue, head to the game floor, or return to the hallway.";
                        choiceThree = "Investigate the issue";
                    }
                    if (pos.matches("restaurant") && gameFrame.getSoGameDone().equals(true)) {
                        mainTxt = "A sign hangs on the door that says:\n\n**CLOSED**\nSetting IDEs & Rebuilding Furniture\n\n";
                        choiceOne = "Return to Hall";
                        choiceTwo = "Return to Game Floor";
                    }


                    gameFrame.setTexts(pos, mainTxt, choiceOne, choiceTwo, choiceThree, choiceFour);/* set valuue of room here*/

                }
            }
            break;
        }
    }

    public void talkInstructor(String position) {
        switch (position) {
            case "rennie":
                createPanelScene("rennie");
                gameFrame.choice3.setVisible(false);
                break;
            case "nelly":
                createPanelScene("nelly");
                break;
            case "karl":
                createPanelScene("karl");
                break;
            case "jay":
                createPanelScene("jay");
                gameFrame.choice3.setVisible(false);
                gameFrame.choice4.setVisible(false);
                break;
            case "chad":
                createPanelScene("chad");
                break;
        }
    }

    public void miniGame(){
        createPanelScene("miniGame");}

    public void prelude(){
        createPanelScene("prelude");
    }
    public void dock() {
        createPanelScene("dock");
    }
    public void sign(){
        createPanelScene("sign");
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
        createPanelScene("gameFloor");
    }

    public void theater() {
        createPanelScene("theater");

    }

    public void preTheater() {
        createPanelScene("preTheater");

    }

    public void ending() {
        //if points greater than X amount then show ending
        gameFrame.setTexts("ending", "You unlock the boat with the key and the screen pixelates to black. As you take your VR goggles off, you're feeling exhausted from the challenges. Inside you feel a huge sense of accomplishment in your spirit. \n" +
                "You feel ready for whatever challenges may come across your journey as an SDE as you remember your training. ", "Leave the Island", "", "","");
    }

    public void scoreBoard(){
        //show bgImage of closing scene and show scoreboard when leaving the island
        System.out.println("Here is the scoreboard");
    }

}
