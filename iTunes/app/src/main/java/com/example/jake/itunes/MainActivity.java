package com.example.jake.itunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private EditText searchText;
    private Toolbar toolbar;

    //url goes here to access API
    //public final static String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.landing_toolbar);
        this.setSupportActionBar(toolbar);

        //trySearch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        String opt1 = "Search";
        int groupId = Menu.NONE;
        int itemID = Menu.FIRST;
        int order = 10;
        menu.add(groupId, itemID, order, opt1);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            //do nothing because on search page
            return true;
        }
        if (id == R.id.access_favorites) {
            //goToFavList()
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToFavList() {
        //Intent intent = new Intent(MainActivity.this, FavoritesList.class);
        //startActivity(intent);
    }

    public void queryMusic(View something) {
        searchText = (EditText) findViewById(R.id.searchBar);
        if((searchText.getText().toString().trim().equals(""))) {
            Toast.makeText(getApplicationContext(), "Please enter a word", Toast.LENGTH_SHORT).show();
        }

        else {
            //Intent i = new Intent(getApplicationContext(), MusicList.class);
            String query = searchText.getText().toString();
            //i.putExtra(EXTRA_MESSAGE, query);
            //startActivity(i);
        }

    }


    public void trySearch() {
        iTunesSearch its = new iTunesSearch();
        its.searchiTunes("Taylor Swift", 0, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String rc = null;
                JSONArray results = null;
                try {
                    rc = response.get("resultCount").toString();
                    results = response.getJSONArray("results");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int resultCount = Integer.parseInt(rc);
                Log.d(TAG, "resultCount " + resultCount);

                Log.d(TAG, results.toString());
            }

            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "Calling onFailure");
            }
        });
    }

}
