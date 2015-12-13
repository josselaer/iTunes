package com.example.jake.itunes;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by quincyschurr on 12/11/15.
 */
public class DBHandler extends SQLiteOpenHelper {

    private SQLiteDatabase database;
    private DBHandler handler;

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "FaveSongsDB.db";

    private static final String TABLE_SONGS = "favoritedSongs";

    public static final String KEY_ID = "_id";
    public static final String KEY_COVERURL = "coverurl";
    public static final String KEY_NAME = "songnames";
    public static final String KEY_ARTIST = "songartist";
    public static final String KEY_ALBUM = "songalbum";

    private static final String[] COLUMNS = {KEY_ID, KEY_COVERURL, KEY_NAME, KEY_ARTIST, KEY_ALBUM};


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVE_TABLE =
                " CREATE TABLE " + TABLE_SONGS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_COVERURL + " TEXT, "
                + KEY_NAME + " TEXT, " + KEY_ARTIST + " TEXT, " + KEY_ALBUM + " TEXT);";

        db.execSQL(CREATE_FAVE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        this.onCreate(db);

    }

    public void addSong (String url, String name, String artist, String album) {//(Song song) {
        //for logging now

        //Log.d("addSong", song.toString());

        //reference to writable DB, but not sure it's getting context
        SQLiteDatabase db = this.getWritableDatabase();

        //create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        values.put(KEY_COVERURL, url);
        values.put(KEY_NAME, name);
        values.put(KEY_ARTIST, artist);
        values.put(KEY_ALBUM, album);


        /*values.put(KEY_COVERURL, song.getArtwork());
        values.put(KEY_NAME, song.getTrackName()); //get song name
        values.put(KEY_ARTIST, song.getArtistName()); //get artist
        values.put(KEY_ALBUM, song.getCollectionName());*/
        Log.d("Song url", url);
        Log.d("Song Name", name);
        Log.d("Artist Name", artist);
        Log.d("Album Name", album);

        //insert into table
        db.insert(TABLE_SONGS, null, values);

        //close
        db.close();
        
    }

    public Song getSong(int id) {
        //get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        //build query to return results
        Cursor cursor = db.query(TABLE_SONGS, COLUMNS, " id = ?", new String[] {String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        //build Song object
        Song song = new Song();
        song.setID(Integer.parseInt(cursor.getString(0)));
        //online these were setting the titles and such...?
        song.getTrackName();
        song.getArtistName();




        return song;
    }

    //update, i don't think we need an update b/c not changing attributes

    public void deleteSong(Song song) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONGS, KEY_ID+" = ?", new String[] {String.valueOf(song.getID()) });
        db.close();

        Log.d("deleteSong", song.toString());

    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> favSongs = new ArrayList<Song>();

        //build query
        String query = "SELECT * FROM " + TABLE_SONGS;

        //get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //build all books
        Song song = null;
        if(cursor.moveToFirst()) {
            do {
                song = new Song();
                song.setID(Integer.parseInt(cursor.getString(0)));
                song.setArtwork(cursor.getString(1));

                song.setTrackName(cursor.getString(2));
                Log.d("Song - ", song.getTrackName());
                song.setArtistName(cursor.getString(3));
                song.setCollectionName(cursor.getString(4));

                favSongs.add(song);
            } while (cursor.moveToNext());
        }
        Log.d("getAllSongs()", song.getTrackName());
        return favSongs;
    }
}
