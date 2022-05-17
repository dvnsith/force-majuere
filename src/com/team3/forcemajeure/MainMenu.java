//package com.team3.forcemajeure;
//
//import com.team3.forcemajeure.jswing.model.GameFrame;
//import com.team3.forcemajeure.util.Audio;
//import com.team3.forcemajeure.util.Data;
//import com.team3.forcemajeure.util.TextParser;
//import com.team3.forcemajeure.util.WriteText;
//
//import java.io.*;
///*
// * com.team3.forcemajeure.MainMenu displays main menu for the user, first screen user sees when starting app.
// * When user finishes game it will bring him back to the main menu
// */
//public class MainMenu {
//        Audio audio = Audio.getInstance();
//        WriteText write = new WriteText();
//
//
//    void executes() throws IOException {
////        primarly runs game
//        boolean runGame = true;
//        GameFrame game = new GameFrame();
//        while (runGame) {
//            showMainMenu();
////            game.runGame();
//            replay();
//        }
//    }
//    void showMainMenu() throws IOException {
////        runs main menu
//        boolean runGame = true;
//        audio.play("start");
//
//        welcome();
////      keeps prompting user for valid response
//        write.setInputString(" test ");
//        while (runGame) {
//            String startGame = TextParser.textInputMainMenu();
//            String start = write.getInputString();
////            write.inputText(startGame);
//            if (start.equals("game")) {
//                runGame = false;
//                audio.stop();
//            } else if (start.equals("mute")) {
//                audio.toggleMute();
//            }
//        }
//    }
//    void replay (){
////        used to replay game
//        boolean validInput = false;
//        while (!validInput){
//            System.out.println("Please enter [Start] to play again or [Quit] to exit the game: ");
//
//            String playerInput = TextParser.textInputMainMenu();
//            if (playerInput.equals("game")){
//                validInput = true;
//            }
//        }
//    }
//
//    private void welcome() {
////        prints com.team3.forcemajeure.MainMenu Ascii
////        System.out.println(Data.getTextMap().get("mainMenu"));
//        write.outputText(Data.getTextMap().get("mainMenu"));
//    }
//}
