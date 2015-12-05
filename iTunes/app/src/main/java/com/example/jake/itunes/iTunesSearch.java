package com.example.jake.itunes;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jake on 12/5/15.
 */
public class iTunesSearch extends AsyncTask<String, Double, JSONObject> {

    private static final String TAG = "iTunesSearch";
    private boolean threadDone;
    private String term;
    private int limit;
    private String media;
    private int resultCount;
    private JSONArray results;

    public iTunesSearch(String term) {
        threadDone = false;
        this.term = replaceSpace(term);
        limit = 10;
        media = "music";
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        getSearchResults();
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        threadDone = true;
        Log.d(TAG, "on post execute" + threadDone);
    }

    @Override
    protected void onProgressUpdate(Double... progress) {

    }

    public void getSearchResults() {
        //https://itunes.apple.com/search?term=beyonce&limit=10&media=music&entity=album
        threadDone = false;
        String tUrl = "https://itunes.apple.com/search?";
        tUrl += "term=" + term;
        tUrl += "&limit=" + limit;
        tUrl += "&media=" + media;

        Log.d(TAG, tUrl);

        try {
            URL url = new URL(tUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            Log.d(TAG, "Response code " + responseCode);
            //put disconnect back here?
            if(responseCode == HttpURLConnection.HTTP_OK) {
                Log.d(TAG, "good");
                InputStream inputStream = connection.getInputStream();
                Log.d(TAG, "available " + inputStream.available());
                Log.d(TAG, inputStream.toString());
                Log.d(TAG, "read " + inputStream.read());


                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                inputStream.close();
                Log.d(TAG, sb.toString());
                String json = sb.toString();

                JSONObject search = new JSONObject(json);
                Log.d(TAG, search.toString());

                String rc = search.get("resultCount").toString();
                resultCount = Integer.parseInt(rc);
                Log.d(TAG, "resultCount " + resultCount);

                results = search.getJSONArray("results");
                Log.d(TAG, results.toString());

            }
            else {
                Log.d(TAG, "bad");
            }
            connection.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d(TAG, "bad url");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "ioexception");
        } catch (JSONException e) {
            Log.d(TAG, "Json didn't work");
            e.printStackTrace();
        }


        Log.d(TAG, "end of search");
    }

    public String replaceSpace(String term) {
        term = term.replace(' ', '+');
        Log.d(TAG, term);
        return term;
    }

    public JSONArray getResults() {
        return results;
    }

    public int getResultCount() {
        return resultCount;
    }

    public boolean isThreadDone() {
        return threadDone;
    }
}