package com.example.jake.itunes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.Menu;

/**
 * Created by quincyschurr on 12/11/15.
 */
public class SongDetails extends AppCompatActivity {

    private ImageView albumCover;
    private TextView songTitle;
    private TextView artist;
    private TextView albumName;
    //we need an explicit thing too, didn't put it in xml yet
    //private TextView explicit;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_details);

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.favorite_toolbar);
        this.setSupportActionBar(toolbar);

        albumCover = (ImageView) findViewById(R.id.albumCover);
        songTitle = (TextView) findViewById(R.id.songTitle);
        artist = (TextView) findViewById(R.id.artist);
        albumName = (TextView) findViewById(R.id.albumName);

       // Song song = (Song) getIntent().getSerializableExtra(MusicResultsList.song_Detail);
        //loadSong(song);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                //selectFavorite();
                return true;
            case R.id.access_favorites:
                //goToFavList();
                return true;
            case R.id.action_search:
                //goToSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //add in new activity functions
    public void selectFavorite() {

    }


}
