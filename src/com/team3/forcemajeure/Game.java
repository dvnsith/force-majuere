package com.team3.forcemajeure;

import com.team3.forcemajeure.util.*;
import com.team3.forcemajeure.util.WriteText;
import java.io.IOException;
import java.util.Map;

public class Game {
//     Variables
    Audio audio = Audio.getInstance();
    boolean gameRunning = true;
     WriteText write = new WriteText();

//    Business methods
//    primarly runs the game
    void runGame() {
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
            if (player.getCurRoom().equals(gambler.getLocation())) {
//                gambler.miniGameConnect();
            }
//            prompt User
            String response = TextParser.gameScannerInput();
            write.inputText(response);
            String response2 = write.getInputString();
            clrScreen();
            if ("mute".equals(response2)) {
                audio.toggleMute();
            } else {
                TextParser.gameScannerOutput(response2, player, roomMap, npcMap, endingsMap);
            }
        }
    }
//     Clears screen
    public void clrScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
}
