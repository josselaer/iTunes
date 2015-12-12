package com.example.jake.itunes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jake on 12/12/15.
 */
public class ArtistsAdapter extends ArrayAdapter<Artist> {

    public ArtistsAdapter(Context context, ArrayList<Artist> aArtists) {
        super(context, 0, aArtists);
    }

    private static class ViewHolder {
        public TextView artist;
        public TextView genre;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Artist artist = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_artist, parent, false);
            viewHolder.artist = (TextView)convertView.findViewById(R.id.artistName);
            viewHolder.genre = (TextView)convertView.findViewById(R.id.primaryGenre);
            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.artist.setText(artist.getArtistName());
        viewHolder.genre.setText(artist.getGenre());

        return convertView;
    }


}
