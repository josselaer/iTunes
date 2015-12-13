package com.example.jake.itunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by quincyschurr on 12/12/15.
 */
public class ArtistResultsList extends AppCompatActivity {
    private ListView lArtists;
    private ArtistsAdapter artistAdapter;
    private Toolbar toolbar;
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

        toolbar = (Toolbar) findViewById(R.id.landing_toolbar);
        this.setSupportActionBar(toolbar);


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
                //goToSearch();
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
