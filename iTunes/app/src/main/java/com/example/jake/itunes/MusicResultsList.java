package com.example.jake.itunes;

import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
=======
import android.os.Bundle;
>>>>>>> 8e5c40d59cbcfb9ef06e0e3d78768040dae0e648
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by quincyschurr on 12/11/15.
 */
public class MusicResultsList extends AppCompatActivity implements View.OnClickListener{
    private ImageView cover;
    private TextView songName;
    private TextView songArtist;
    private TextView albumName;
    private TextView explicit;
    private ImageButton heart;
    private ImageButton play;
    private Toolbar toolbar;
    private ListView lSongs;
    //private DBHandler dbHandler;
    private SongAdapter songAdapter;
    public static final String song_Detail = "event";
    private static final String TAG = "MusicResultsList";
    private boolean isPlaying = false;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<Song> songs = (ArrayList<Song>)intent.getSerializableExtra("SendIntent");
        setContentView(R.layout.song_results_list);

        lSongs = (ListView) findViewById(R.id.listResults);
        songAdapter = new SongAdapter(this, songs);
        lSongs.setAdapter(songAdapter);
        

    }

    @Override
    public void onClick(View view) {

    }

    public void favoriteSong(View view) {
        Drawable heartButton = getResources().getDrawable(R.drawable.ic_action_favorite);
        ColorFilter filter = new LightingColorFilter(Color.RED, Color. RED);
        heart = (ImageButton) findViewById(R.id.heartShape);
        if (view == heart) {
            heartButton.setColorFilter(filter);
        }
    }


}
