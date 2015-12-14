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
 * Created by quincyschurr on 12/13/15.
 */
public class FavoriteAdapter extends ArrayAdapter<Song> {

    private static final String TAG = "FavoriteAdapter";
    private MusicPlayer player;
    private DBHandler handler;

    private static class ViewHolder {
        public ImageView cover;
        public TextView songName;
        public TextView songArtist;
        public TextView albumName;
        public ImageButton favoriteBtn;
        public ImageButton playButton;
        public TextView explicit;

    }

    public FavoriteAdapter(Context context, ArrayList<Song> aSongs) { super(context, 0, aSongs);}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Song song = getItem(position);
        player = new MusicPlayer();
        handler = new DBHandler(getContext());
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_fav, parent, false);
            //viewHolder.cover = (ImageView)convertView.findViewById(R.id.albumFavCover);
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

        //viewHolder.cover.setImageResource(song.getArtwork());
        viewHolder.songName.setText(song.getTrackName());
        viewHolder.songArtist.setText(song.getArtistName());
        viewHolder.albumName.setText(song.getCollectionName());

        final String sUrl = song.getArtwork();
        final String sName = viewHolder.songName.getText().toString();
        final String sArtist = viewHolder.songArtist.getText().toString();
        final String sAlbum = viewHolder.albumName.getText().toString();
        final Drawable replaceImg = getContext().getResources().getDrawable(R.drawable.ic_action_red_heart);



        //Picasso.with(getContext()).load(Uri.parse(song.getArtwork())).error(R.drawable.ic_action_favorite).into(viewHolder.cover);


        /*viewHolder.favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageButton) v).setEnabled(false);
                ((ImageButton) v).setBackgroundDrawable(replaceImg);
                handler.addSong(sUrl, sName, sArtist, sAlbum);
            }
        });*/

        if(song.getExplicit() == true) {
            viewHolder.explicit.setTextColor(Color.RED);
        }
        else {
            viewHolder.explicit.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
