package com.example.jake.itunes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jake on 12/12/15.
 */
public class Album implements Serializable {
    private static final String TAG = "Album";
    /*
        artistName
        collectionName
        artworkUrl60
        collectionExplicitness
        primaryGenreName
         */
    private String artwork;
    private String artistName;
    private String collectionName;
    private boolean explicit;
    private String genre;

    public static ArrayList<Album> fromJson (JSONArray jsonArray) {
        ArrayList<Album> albums = new ArrayList<Album>(jsonArray.length());
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject albumJson = null;

            try {
                albumJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Album album = Album.fromJson(albumJson);

            if(album != null) {
                albums.add(album);
            }
        }
        return albums;
    }

    public String getArtwork() {
        return artwork;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getGenre() {
        return genre;
    }

    public boolean getExplicit() {
        return explicit;
    }

    public static Album fromJson(JSONObject jsonObject) {
        Album album = new Album();
        String isExplicit = null;
        try {
            album.artwork = jsonObject.getString("artworkUrl60");
            album.artistName = jsonObject.getString("artistName");
            album.collectionName = jsonObject.getString("collectionName");
            isExplicit = jsonObject.getString("collectionExplicitness");
            album.genre = jsonObject.getString("primaryGenreName");

            if (isExplicit.equals("notExplicit")) {
                album.explicit = false;
            }
            else {
                album.explicit = true;
            }

            //optString
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return album;
    }

}
