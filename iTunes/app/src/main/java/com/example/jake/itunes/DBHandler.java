package com.example.jake.itunes;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by quincyschurr on 12/11/15.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FaveSongsDB.db";


    private static final String TABLE_SONGS = "favoritedSongs";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "songnames";
    public static final String KEY_ARTIST = "songartist";

    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_ARTIST};


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    /*, String name, SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVE_TABLE =
                " CREATE TABLE " + TABLE_SONGS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, " + KEY_ARTIST + " TEXT);";

        db.execSQL(CREATE_FAVE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        this.onCreate(db);

    }

    public void addSong (Song song) {
        //for logging now
        Log.d("addSong", song.toString());

        //reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, song.getTrackName()); //get song name
        values.put(KEY_ARTIST, song.getArtistName()); //get artist

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

        Log.d("getSong("+id+")", song.toString());

        return song;
    }

    //don't need an updateSong b/c not changing song attributes

    public void deleteSong(Song song) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONGS, KEY_ID+" = ?", new String[] {String.valueOf(song.getID()) });
        db.close();

        Log.d("deleteSong", song.toString());
        /*boolean result = false;

        String query = "Select * FROM " + TABLE_SONGS + " WHERE " + KEY_NAME + " =  \"" + songName + "\"";
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Song song = new Song();

        if(cursor.moveToFirst()) {
            song.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_SONGS, KEY_ID + " = ?", new String[] {String.valueOf(song.getID())});
            cursor.close();
            result = true;
        }*/

        //db.close();
        //return result;
    }

    public List<Song> getAllSongs() {
        List<Song> favSongs = new LinkedList<Song>();

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
                song.getTrackName();
                song.getArtistName();

                favSongs.add(song);
            } while (cursor.moveToNext());
        }
        Log.d("getAllSongs()", favSongs.toString());
        return favSongs;
    }
}
