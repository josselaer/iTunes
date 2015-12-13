package com.example.jake.itunes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jake on 12/12/15.
 */
public class Song implements Serializable{
    /*
    artworkUrl30
    trackName
    artistName
    collectionName
    trackExplicitness
    previewUrl
     */
    private int _id;
    private String artwork;
    private String trackName;
    private String artistName;
    private String collectionName;
    private boolean explicit;
    private String songPreview;

    public static ArrayList<Song> fromJson (JSONArray jsonArray) {
        ArrayList<Song> songs = new ArrayList<Song>(jsonArray.length());
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject songJson = null;

            try {
                songJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Song song = Song.fromJson(songJson);

            if(song != null) {
                songs.add(song);
            }
        }
        return songs;
    }

    public void setID(int id) { this._id = id;}

    public String getArtwork() {
        return artwork;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public boolean getExplicit() {
        return explicit;
    }

    public int getID() { return this._id;}

    public String getSongPreview() {
        return songPreview;
    }

    public static Song fromJson(JSONObject jsonObject) {
        Song song = new Song();
        String isExplicit = null;
        try {
            song.artwork = jsonObject.getString("artworkUrl30");
            song.artistName = jsonObject.getString("artistName");
            song.collectionName = jsonObject.getString("collectionName");
            isExplicit = jsonObject.getString("trackExplicitness");
            song.songPreview = jsonObject.getString("previewUrl");
            song.trackName = jsonObject.getString("trackName");

            if (isExplicit == "notExplicit") {
                song.explicit = false;
            }
            else {
                song.explicit = true;
            }

            //optString
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return song;
    }

}
