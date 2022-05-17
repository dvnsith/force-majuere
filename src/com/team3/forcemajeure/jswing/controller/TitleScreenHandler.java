package com.team3.forcemajeure.jswing.controller;

import com.team3.forcemajeure.jswing.model.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreenHandler implements ActionListener {

    private final GameFrame gameFrame;

    public TitleScreenHandler(GameFrame pView){
        gameFrame = pView;
    }
    public void actionPerformed(ActionEvent event) {

        gameFrame.createGameScreen();
    }
}