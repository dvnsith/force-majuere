package com.team3.forcemajeure.util;

import com.team3.forcemajeure.jswing.GameFrame;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReadFile {
    private JSONObject jsonObject;
    private Object obj;
    private JSONParser parser = new JSONParser();

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public JSONObject retrieveJson(String path) {
        InputStream inputTestJSON;

        inputTestJSON = getFileFromResourceAsStream(path, GameFrame.class);
        try{
            setObj(parser.parse(new InputStreamReader(inputTestJSON, "UTF-8")));
        } catch(IOException | ParseException e){
            e.printStackTrace();
        }

        setJsonObject((JSONObject) getObj());

        return getJsonObject();
    }

    public InputStream getFileFromResourceAsStream(String fileName, Class aClass) {
        ClassLoader classLoader = aClass.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }



}
