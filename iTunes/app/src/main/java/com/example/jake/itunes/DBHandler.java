package com.example.jake.itunes;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by quincyschurr on 12/11/15.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "allFaveSongs.db";
    private static final String TABLE_SONGS = "favoritedSongs";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "songnames";
    public static final String COLUMN_ARTIST = "songartist";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE_TABLE" + TABLE_SONGS + "(" + COLUMN_ID + "INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT," + COLUMN_ARTIST + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        onCreate(db);

    }

    public void addSong (Song song) {
        ContentValues values = new ContentValues();
        //maybe just supposed to store meta data??
        values.put(COLUMN_NAME, song.getTrackName());
        values.put(COLUMN_ARTIST, song.getArtistName());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SONGS, null, values);
        db.close();
        
    }

    public boolean deleteSong(String songName) {
        boolean result = false;

        String query = "Select * FROM " + TABLE_SONGS + " WHERE " + COLUMN_NAME + " =  \"" + songName + "\"";
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Song song = new Song();

        if(cursor.moveToFirst()) {
            song.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_SONGS, COLUMN_ID + " = ?", new String[] {String.valueOf(song.getID())});
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }
}
