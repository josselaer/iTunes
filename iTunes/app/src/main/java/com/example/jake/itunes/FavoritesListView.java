package com.example.jake.itunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quincyschurr on 12/12/15.
 */
public class FavoritesListView extends AppCompatActivity {
    private ImageView cover;
    private TextView songName;
    private TextView songArtist;
    private TextView albumName;
    private TextView explicit;
    private ImageButton heart;
    private ImageButton play;
    private Toolbar toolbar;
    private ListView fSongs;
    private FavoriteAdapter favAdapter;

    public static final String song_Detail = "event";
    private static final String TAG = "MusicResultsList";
    private boolean isPlaying = false;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.favorites_list_view);

        ArrayList<Song> allFavSongs;
        DBHandler db = new DBHandler(this);
        allFavSongs = db.getAllSongs();

        fSongs = (ListView) findViewById(R.id.favoriteResults);
        favAdapter = new FavoriteAdapter(this, allFavSongs);
        fSongs.setAdapter(favAdapter);

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
