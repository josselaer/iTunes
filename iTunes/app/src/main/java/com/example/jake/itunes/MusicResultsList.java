package com.example.jake.itunes;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;


import java.util.ArrayList;

/**
 * Created by quincyschurr on 12/11/15.
 */
public class MusicResultsList extends AppCompatActivity{
    private ImageView cover;
    private TextView songName;
    private TextView songArtist;
    private TextView albumName;
    private TextView explicit;
    private ImageButton heart;
    private ImageButton play;
    private Toolbar toolbar;
    private ListView lSongs;
    private SongAdapter songAdapter;
    public static final String song_Detail = "event";
    private static final String TAG = "MusicResultsList";
    private boolean isPlaying = false;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<Song> songs = (ArrayList<Song>) intent.getSerializableExtra("SendIntent");
        setContentView(R.layout.song_results_list);

        lSongs = (ListView) findViewById(R.id.listResults);
        songAdapter = new SongAdapter(this, songs);
        lSongs.setAdapter(songAdapter);

        toolbar = (Toolbar) findViewById(R.id.landing_toolbar);
        this.setSupportActionBar(toolbar);



    }


    public void removeSong(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                goToSearch();
                return true;
            case R.id.access_favorites:
                accessFavList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToSearch() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void accessFavList() {
        Intent i = new Intent(getApplicationContext(), FavoritesListView.class);
        startActivity(i);
    }

}
