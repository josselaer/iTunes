package com.example.jake.itunes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by quincyschurr on 12/12/15.
 */
public class SongAdapter extends ArrayAdapter<Song>{

    private static final String TAG = "SongAdapter";
    private MusicPlayer player;
    private DBHandler handler;
    private ArrayList<String> favSongNames;


    private static class ViewHolder {
        public ImageView cover;
        public TextView songName;
        public TextView songArtist;
        public TextView albumName;
        public ImageButton favoriteBtn;
        public ImageButton playButton;
        public TextView explicit;

    }

    public SongAdapter(Context context, ArrayList<Song> aSongs) {
        super(context, 0, aSongs);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Song song = getItem(position);
        player = new MusicPlayer();
        handler = new DBHandler(getContext());
        ArrayList<Song>favSongs = handler.getAllSongs();
        favSongNames = new ArrayList<String>();
        for(int i = 0; i<favSongs.size(); i++) {
            favSongNames.add(favSongs.get(i).getTrackName());
        }

        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_song, parent, false);
            viewHolder.cover = (ImageView)convertView.findViewById(R.id.albumCover);
            viewHolder.songName = (TextView)convertView.findViewById(R.id.songName);
            viewHolder.songArtist = (TextView)convertView.findViewById(R.id.songArtist);
            viewHolder.albumName = (TextView)convertView.findViewById(R.id.songAlbumName);
            viewHolder.favoriteBtn = (ImageButton)convertView.findViewById(R.id.heartShape);
            viewHolder.playButton = (ImageButton) convertView.findViewById(R.id.playback);
            viewHolder.explicit = (TextView)convertView.findViewById(R.id.explicit);


            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.songName.setText(song.getTrackName());

        viewHolder.songArtist.setText(song.getArtistName());
        viewHolder.albumName.setText(song.getCollectionName());

        final String sUrl = song.getArtwork();
        //Log.d("String url for cover", sUrl);
        final String sName = viewHolder.songName.getText().toString();
        final String sArtist = viewHolder.songArtist.getText().toString();
        final String sAlbum = viewHolder.albumName.getText().toString();
        final Drawable replaceImg = getContext().getResources().getDrawable(R.drawable.ic_action_red_heart);
        //final Drawable replaceImg = getResources().getDrawable(R.drawable.ic_action_red_heart);


        Log.d(TAG, sUrl);
        Picasso.with(getContext()).load(Uri.parse(song.getArtwork())).error(R.drawable.ic_action_favorite).into(viewHolder.cover);
        viewHolder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlayingSong() == false) {
                    player.setSong(song.getSongPreview());
                    player.playSong();
                }
                else {
                    player.pauseSong();
                }
            }
        });

        viewHolder.favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageButton) v).setEnabled(false);
                ((ImageButton) v).setBackgroundDrawable(replaceImg);
                handler.addSong(sUrl, sName, sArtist, sAlbum);
            }
        });

        boolean isFav = false;
        for(int i = 0; i<favSongNames.size(); i++) {
            if(song.getArtistName().equals(favSongNames.get(i))) {
                isFav = true;
                break;
            }
        }

        if(isFav == true) {
            ((ImageButton) convertView).setEnabled(false);
            ((ImageButton) convertView).setBackgroundDrawable(replaceImg);
        }

        if(song.getExplicit() == true) {
            viewHolder.explicit.setTextColor(Color.RED);
        }
        else {
            viewHolder.explicit.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
