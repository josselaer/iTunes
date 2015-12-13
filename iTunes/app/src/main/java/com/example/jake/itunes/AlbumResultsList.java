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
public class AlbumResultsList extends AppCompatActivity{
    private ListView lAlbums;
    private AlbumAdapter albumAdapter;
    private Toolbar toolbar;
    public static final String album_Detail = "event";
    private static final String TAG = "AlbumEventList";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<Album> albums = (ArrayList<Album>)intent.getSerializableExtra("SendIntent");
        setContentView(R.layout.album_results_list);

        lAlbums = (ListView) findViewById(R.id.albumResults);
        albumAdapter = new AlbumAdapter(this, albums);
        lAlbums.setAdapter(albumAdapter);

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
