package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.util.ReadFile;
import org.json.simple.JSONObject;
import javax.swing.*;
import java.util.HashMap;

public class SetUp {
    private final GameFrame gameFrame;
    private final ReadFile readFile = new ReadFile();
    private final MagicGame magicGame;
    private final BlackJackGame blackJackGame;
    private JSONObject jsonObject;

    // Ctor
    public SetUp(GameFrame view){
        gameFrame = view;
        magicGame = new MagicGame(view);
        blackJackGame = new BlackJackGame(view);
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
        gameFrame.skipLabel.setText("Skips: " + magicGame.getSkips());
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

    /* pulls the JSON data and creates a panel based on room location */
    public void createPanelScene(String pos){

        while(true){
            setJsonObject(readFile.retrieveJson("data/location.json"));
            HashMap<String, String> gameMap = (HashMap<String, String>) getJsonObject().get(pos);
            String choiceTwo, choiceThree;

            for(Object room : getJsonObject().keySet()){
                if(room.toString().equals(pos)){
                    String mainTxt = gameMap.get("maintext");
                    choiceTwo = gameMap.get("c2");
                    choiceThree = gameMap.get("c3");
                    if(pos.matches("rennie")){
                        mainTxt = gameFrame.getPlayer() + gameMap.get("maintext");
                        if(gameFrame.inventory.contains("Key")){
                            choiceThree = "leave this island";
                        }
                    }
                    if(pos.matches("theater") && gameFrame.getBlackjackPlayed().equals(true)){
                        choiceTwo = "Talk to Magician Chad";
                    }
                    gameFrame.setTexts(pos,mainTxt,gameMap.get("c1"),choiceTwo,choiceThree,gameMap.get("c4"));/* set valuue of room here*/
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

    public void miniGame(){
        createPanelScene("miniGame");}

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
        createPanelScene("gameFloor");
    }

    public void theater() {
        createPanelScene("theater");
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
