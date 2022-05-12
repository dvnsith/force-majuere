package com.team3.forcemajeure.util;

import com.team3.forcemajeure.jswing.GameFrame;

import java.util.ArrayList;

public class WriteText {
    private static String inputString;
    private String outputString;
    private ArrayList<String> outputListString;


    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        WriteText.inputString = inputString;
    }

    public String getOutputString() {
        return outputString;
    }

    public void setOutputString(String outputString) {
        this.outputString = outputString;
    }

    public ArrayList<String> getOutputListString() {
        return outputListString;
    }

    public void setOutputListString(ArrayList<String> outputListString) {
        this.outputListString = outputListString;
    }

    public void inputText(String s){
        setInputString(s.stripLeading().stripTrailing());
        System.out.println(getInputString());

    }

    public void outputText(String s){
        setOutputString(s.stripLeading().stripTrailing());
        System.out.println(getOutputString());
    }

    public void outputTextList (ArrayList<String> s){
        setOutputListString(s);
        System.out.println(getOutputListString());
    }

}
