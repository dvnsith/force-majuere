package com.team3.forcemajeure.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;


public class SoundPlayer {

    private ReadFile readFile = new ReadFile();

    public Clip play(String name, Boolean playAll, int playLengthInMilSec, Class aClass){

        String fileNameWav = "audio/" + name + ".wav";
        String fileNameMP3 = "audio/" + name + ".mp3";
        boolean isMP3 = FileResourceUtils.resourceExists(fileNameMP3);
        String fileName = isMP3 ? fileNameMP3 : fileNameWav;


        InputStream sound = new BufferedInputStream(readFile.getFileFromResourceAsStream(fileName, aClass));
        Clip clip = null;

        try{
            // if playAll then start
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

            // if not playing all then stop after timer
            if(!playAll){
                Thread.sleep(playLengthInMilSec);
                clip.stop();
            }

        } catch (Exception e){
            System.out.println("Can't find file to play sound");
            e.printStackTrace();
        }
        return clip;
    }

}