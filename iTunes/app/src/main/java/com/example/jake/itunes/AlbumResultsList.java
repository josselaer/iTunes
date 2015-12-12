package com.example.jake.itunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by quincyschurr on 12/12/15.
 */
public class AlbumResultsList extends AppCompatActivity{
    private ListView lAlbums;
    private AlbumAdapter albumAdapter;
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


    }
}
