package com.example.jake.itunes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private Toolbar toolbar;
    private ListView lSongs;

    public static final String song_Detail = "event";
    private static final String TAG = "MusicResultsList";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.song_results_list);

        lSongs = (ListView) findViewById(R.id.listResults);

    }
}
