package com.team3.forcemajeure.util;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

public class Story {

    private String round;
    private String path;
    private String name;
    private String id;
    private String mainText;
    private String choiceOne;
    private String choiceTwo;
    private String choiceThree;
    private String choiceFour;
    ReadFile readFile = new ReadFile();

    Player player = new Player();
    JSONParser parser = new JSONParser();
    JSONObject jsonObject;
    JSONArray jsonArray;
    Object object;


    private void story(String round, String mainText, String choiceOne, String choiceTwo, String choiceThree, String choiceFour) {
        this.round = round;
        this.mainText = mainText;
        this.choiceOne = choiceOne;
        this.choiceTwo = choiceTwo;
        this.choiceThree = choiceThree;
        this.choiceFour = choiceFour;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getChoiceOne() {
        return choiceOne;
    }

    public void setChoiceOne(String choiceOne) {
        this.choiceOne = choiceOne;
    }

    public String getChoiceTwo() {
        return choiceTwo;
    }

    public void setChoiceTwo(String choiceTwo) {
        this.choiceTwo = choiceTwo;
    }

    public String getChoiceThree() {
        return choiceThree;
    }

    public void setChoiceThree(String choiceThree) {
        this.choiceThree = choiceThree;
    }

    public String getChoiceFour() {
        return choiceFour;
    }

    public void setChoiceFour(String choiceFour) {
        this.choiceFour = choiceFour;
    }

    public JSONParser getParser() {
        return parser;
    }

    public void setParser(JSONParser parser) {
        this.parser = parser;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void createPanelScene(String pos) {

        if(pos.matches("rennie|nelly|chad|karl|jay") && pos != null) {
            setPath("data/npc.json");
        }else{
            setPath("data/location.json");
        }
        while (true) {
            setJsonObject(readFile.retrieveJson(getPath()));
            System.out.println(getJsonObject());
            HashMap<String, String> sceneMap = (HashMap<String, String>) getJsonObject().get(pos);
            for (Object obj : getJsonObject().keySet()) {
                if (obj.toString().equals(pos)) {
                    setName(pos);
                    setId(sceneMap.get("id"));
                    setMainText(sceneMap.get("maintext"));
                    setChoiceOne(sceneMap.get("c1"));
                    setChoiceTwo(sceneMap.get("c2"));
                    setChoiceThree(sceneMap.get("c3"));
                    setChoiceFour(sceneMap.get("c4"));
                }
            }
            break;
        }
    }
//    public JSONObject retrieveJson(String path) {
//        InputStream inputTestJSON;
//        inputTestJSON = getFileFromResourceAsStream(getPath(), Story.class);
//        try{
//            setObject(parser.parse(new InputStreamReader(inputTestJSON, "UTF-8")));
//        } catch(IOException | ParseException e){
//            e.printStackTrace();
//        }
//        setJsonObject((JSONObject) getObject());
//        return getJsonObject();
//    }
//
//    public InputStream getFileFromResourceAsStream(String fileName, Class aClass) {
//        ClassLoader classLoader = aClass.getClassLoader();
//        InputStream inputStream = classLoader.getResourceAsStream(fileName);
//        if (inputStream == null) {
//            throw new IllegalArgumentException("file not found! " + fileName);
//        } else {
//            return inputStream;
//        }
//    }
}
