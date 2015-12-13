package com.example.jake.itunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static final String EXTRA_MESSAGE = "SendIntent";
    private EditText searchText;
    private Toolbar toolbar;
    private Spinner spinner;
    private int entity;


    //url goes here to access API
    //public final static String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.landing_toolbar);
        this.setSupportActionBar(toolbar);

        entity = 0;
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //trySearch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        String opt1 = "Search";
        int groupId = Menu.NONE;
        int itemID = Menu.FIRST;
        int order = 10;
        menu.add(groupId, itemID, order, opt1);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                //goToSearch(); already on search
                return true;
            case R.id.access_favorites:
                accessFavList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void accessFavList() {
        Intent i = new Intent(getApplicationContext(), FavoritesListView.class);
        startActivity(i);
    }


    public void queryMusic(View something) {
        searchText = (EditText) findViewById(R.id.searchBar);


        if((searchText.getText().toString().trim().equals(""))) {
            Toast.makeText(getApplicationContext(), "Please enter a word", Toast.LENGTH_SHORT).show();
        }

        else {
            //Intent i = new Intent(getApplicationContext(), MusicList.class);
            String query = searchText.getText().toString();
            trySearch(query);
            //i.putExtra(EXTRA_MESSAGE, query);
            //startActivity(i);
        }

    }


    public void trySearch(String query) {
        iTunesSearch its = new iTunesSearch();
        its.searchiTunes(query, entity, new JsonHttpResponseHandler() {
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

                if(entity == 1) {
                    final ArrayList<Artist> resultsList = Artist.fromJson(results);
                    Intent intent = new Intent(getApplicationContext(), ArtistResultsList.class);
                    intent.putExtra(EXTRA_MESSAGE, resultsList);
                    startActivity(intent);
                }
                else if(entity == 2) {
                    final ArrayList<Album> resultsList = Album.fromJson(results);
                    Intent intent = new Intent(getApplicationContext(), AlbumResultsList.class);
                    intent.putExtra(EXTRA_MESSAGE, resultsList);
                    startActivity(intent);
                }
                else {
                    final ArrayList<Song> resultsList = Song.fromJson(results);
                    Intent intent = new Intent(getApplicationContext(), MusicResultsList.class);
                    intent.putExtra(EXTRA_MESSAGE, resultsList);
                    startActivity(intent);

                }



            }

            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "Calling onFailure");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position) {
            case 0:
                entity = 0;
                break;
            case 1:
                entity=1;
                break;
            case 2:
                entity=2;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        entity = 0;
    }
}
