package com.example.jake.itunes;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by Jake on 12/5/15.
 */
public class iTunesSearch {

    private static final String TAG = "iTunesSearch";
    private String term;
    private int limit;
    private String media;
    private String entity;
    private AsyncHttpClient client;

    public iTunesSearch() {
        client = new AsyncHttpClient();
        limit = 15;
        media = "music";

    }

    public void searchiTunes(String term, int entityType, JsonHttpResponseHandler handler) {
        this.term = replaceSpace(term);
        if(entityType == 1) {
            this.entity = "musicArtist";
        }
        else if(entityType == 2) {
            this.entity = "album";
        }
        else {
            this.entity = "musicTrack";
        }

        String tUrl = "https://itunes.apple.com/search?";
        tUrl += "term=" + this.term;
        tUrl += "&limit=" + limit;
        tUrl += "&media=" + media;
        tUrl += "&entity=" + entity;

        Log.d(TAG, tUrl);

        client.get(tUrl, handler);
    }

    public String replaceSpace(String term) {
        term = term.replace(' ', '+');
        Log.d(TAG, term);
        return term;
    }

    public int getLimit() {
        return limit;
    }

}