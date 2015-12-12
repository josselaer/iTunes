package com.example.jake.itunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by quincyschurr on 12/12/15.
 */
public class ArtistResultsList extends AppCompatActivity {
    private ListView lArtists;
    private ArtistsAdapter artistAdapter;
    public static final String artist_Detail = "event";
    private static final String TAG = "ArtistResultList";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<Artist> artists = (ArrayList<Artist>)intent.getSerializableExtra("SendIntent");
        setContentView(R.layout.artist_results_list);

        lArtists = (ListView) findViewById(R.id.artistResults);
        artistAdapter = new ArtistsAdapter(this, artists);
        lArtists.setAdapter(artistAdapter);


    }


}
