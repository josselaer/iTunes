package com.example.jake.itunes;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by quincyschurr on 12/12/15.
 */
public class SongAdapter extends ArrayAdapter<Song> {

    private static class ViewHolder {
        public ImageView cover;
        public TextView songName;
        public TextView songArtist;
        public TextView albumName;

    }

    public SongAdapter(Context context, ArrayList<Song> aSongs) { super(context, 0, aSongs);}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Song song = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_song, parent, false);
            viewHolder.cover = (ImageView)convertView.findViewById(R.id.albumCover);
            viewHolder.songName = (TextView)convertView.findViewById(R.id.songName);
            viewHolder.songArtist = (TextView)convertView.findViewById(R.id.songArtist);
            viewHolder.albumName = (TextView)convertView.findViewById(R.id.songAlbumName);
            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.songName.setText(song.getTrackName());
        viewHolder.songArtist.setText(song.getArtistName());
        viewHolder.albumName.setText(song.getCollectionName());
        Picasso.with(getContext()).load(Uri.parse(song.getArtwork())).error(R.drawable.ic_action_favorite).into(viewHolder.cover);

        return convertView;
    }
}
