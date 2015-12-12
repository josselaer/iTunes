package com.example.jake.itunes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jake on 12/12/15.
 */
public class Artist implements Serializable {
    /*
    artistName
    primaryGenreName
     */
    private String artistName;
    private String genre;

    public static ArrayList<Artist> fromJson (JSONArray jsonArray) {
        ArrayList<Artist> artists = new ArrayList<Artist>(jsonArray.length());
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject artistJson = null;

            try {
                artistJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Artist artist = Artist.fromJson(artistJson);

            if(artist != null) {
                artists.add(artist);
            }
        }
        return artists;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getGenre() {
        return genre;
    }

    public static Artist fromJson(JSONObject jsonObject) {
        Artist artist = new Artist();
        String isExplicit = null;
        try {
            artist.artistName = jsonObject.getString("artistName");
            artist.genre = jsonObject.getString("primaryGenreName");


            //optString
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return artist;
    }
}
