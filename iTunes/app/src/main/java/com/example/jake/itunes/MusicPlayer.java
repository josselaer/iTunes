package com.example.jake.itunes;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Jake on 12/11/15.
 */
public class MusicPlayer {
    private String song;
    private MediaPlayer player;

    //String preview = response.getJSONObject(0).getString("previewUrl");

    public MusicPlayer(String song) {
        this.song = song;
        player = new MediaPlayer();
        setupPlayer();
    }

    public void setSong(String song) {
        this.song = song;
        setupPlayer();
    }

    public void setupPlayer() {
        try {
            player.setDataSource(song);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playSong() {
        player.start();
    }

    public void pauseSong() {
        player.pause();
    }


}
