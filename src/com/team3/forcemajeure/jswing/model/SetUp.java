package com.team3.forcemajeure.jswing.model;

import com.team3.forcemajeure.util.ReadFile;
import org.json.simple.JSONObject;
import javax.swing.*;
import java.util.HashMap;

public class SetUp {
    private final GameFrame gameFrame;
    private final ReadFile readFile = new ReadFile();
    private final MagicGame magicGame;

    private JSONObject jsonObject;
    //Ctor
    public SetUp(GameFrame view){
        gameFrame = view;
        magicGame = new MagicGame(view);

    }

    //Accessor
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    //Business Methods
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


    /* pulls the JSON data and creates a panel based on room location */
    public void createPanelScene(String pos){

        while(true){
            setJsonObject(readFile.retrieveJson("data/mainTextArea.json"));
            HashMap<String, String> gameMap = (HashMap<String, String>) getJsonObject().get(pos);
            String choiceThree = gameMap.get("choiceThree");
            for(Object room : getJsonObject().keySet()){
                if(room.toString().equals(pos)){
                    String mainTxt = gameMap.get("mainText");
                    if(pos.equals("talkInstructor")){
                        mainTxt = gameFrame.getPlayer() + gameMap.get("mainText");
                        if(gameFrame.inventory.contains("Key")){
                            choiceThree = "leave this B";
                        }
                    }
                    gameFrame.setTexts(pos,mainTxt,gameMap.get("choiceOne"),gameMap.get("choiceTwo"),choiceThree,gameMap.get("choiceFour"));/* set valuue of room here*/
                }
            }
            break;
        }

    }

    public void talkInstructor() {
        createPanelScene("talkInstructor");
    }
    public void dock() {
        createPanelScene("dock");
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
        gameFrame.setTexts("ending", "YOOOOO do you have the key?", "leave the island", "", "","");
    }

    public void scoreBoard(){
        //show bgImage of closing scene and show scoreboard when leaving the island
        System.out.println("Here is the scoreboard");
    }




}
