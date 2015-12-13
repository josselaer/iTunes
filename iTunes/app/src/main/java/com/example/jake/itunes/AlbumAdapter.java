package com.example.jake.itunes;

import android.content.Context;
import android.graphics.Color;
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
 * Created by Jake on 12/12/15.
 */
public class AlbumAdapter extends ArrayAdapter<Album> {


    private static final String TAG = "AlbumAdapter";

    public AlbumAdapter(Context context, ArrayList<Album> aAlbums) {
        super(context, 0, aAlbums);
    }

    private static class ViewHolder {
        public ImageView cover;
        public TextView artistName;
        public TextView collectionName;
        public TextView genre;
        public TextView explicit;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Album album = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_album, parent, false);
            viewHolder.cover = (ImageView)convertView.findViewById(R.id.aCover);
            viewHolder.artistName = (TextView)convertView.findViewById(R.id.albumArtistName);
            viewHolder.collectionName = (TextView)convertView.findViewById(R.id.albumName);
            viewHolder.genre = (TextView)convertView.findViewById(R.id.albumGenre);
            viewHolder.explicit = (TextView)convertView.findViewById(R.id.albumExplicit);

            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.artistName.setText(album.getArtistName());
        viewHolder.collectionName.setText(album.getCollectionName());
        viewHolder.genre.setText(album.getGenre());
        Picasso.with(getContext()).load(Uri.parse(album.getArtwork())).error(R.drawable.ic_action_favorite).into(viewHolder.cover);
        if(album.getExplicit() == true) {
            viewHolder.explicit.setTextColor(Color.RED);
        }
        else {
            viewHolder.explicit.setVisibility(View.INVISIBLE);
        }


        return convertView;
    }
}
