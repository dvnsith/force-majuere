package com.team3.forcemajeure.jswing.model;

public class JavaScriptGame {
    private final GameFrame gameFrame;
    private Boolean jsGameDone = false;

    public JavaScriptGame(GameFrame view){
        gameFrame = view;
    }

    public Boolean getJsGameDone() {
        return jsGameDone;
    }

    public void setJsGameDone(Boolean jsGameDone) {
        this.jsGameDone = jsGameDone;
    }

    public void testStart(){
        gameFrame.setTexts("jsStart", "this is the start to js", "next","","","");
        System.out.println("from JS jsStart");
        gameFrame.choice2.setVisible(false);
        gameFrame.choice3.setVisible(false);
        gameFrame.choice4.setVisible(false);
    }
    public void testMid(){
        gameFrame.setTexts("jsMid", "this is the mid to js", "see end","","","");
        System.out.println("from JS jsMid");
        gameFrame.choice2.setVisible(false);
        gameFrame.choice3.setVisible(false);
        gameFrame.choice4.setVisible(false);
    }
    public void testEnd(){
        gameFrame.setTexts("jsEnd", "this is the end to js", "back to lobby","","","");
        System.out.println("from JS jsEnd");
        gameFrame.choice2.setVisible(false);
        gameFrame.choice3.setVisible(false);
        gameFrame.choice4.setVisible(false);
        gameFrame.setJsGameDone(true);
    }
}
