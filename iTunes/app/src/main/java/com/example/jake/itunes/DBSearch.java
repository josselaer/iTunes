package com.example.jake.itunes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Jake on 12/13/15.
 */
public class DBSearch extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SearchDB.db";

    public static final String KEY_ID = "_id";
    public static final String KEY_QUERY = "queries";
    public static final String KEY_ENTITY = "entities";


    private static final String TABLE_SEARCH = "TableSearch";
    private static final String TAG = "DBSearch";

    public DBSearch(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SEARCH_TABLE =
                " CREATE TABLE " + TABLE_SEARCH + " ("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUERY + " TEXT, "
                        + KEY_ENTITY + " INTEGER);";

        db.execSQL(CREATE_SEARCH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH);
        this.onCreate(db);
    }

    public void addSearch(String query, int entity) {
        deleteFirst();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_QUERY, query);
        values.put(KEY_ENTITY, entity);

        db.insert(TABLE_SEARCH, null, values);

        db.close();

    }

    public void deleteFirst() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SEARCH, null, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            String rowId = cursor.getString(cursor.getColumnIndex(KEY_ID));

            db.delete(TABLE_SEARCH, KEY_ID + "=?",  new String[]{rowId});
        }
        db.close();
    }

    public ArrayList<String> getQueries() {
        ArrayList<String> queries = new ArrayList<String>();

        String query = "SELECT * FROM " + TABLE_SEARCH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String temp = cursor.getString(1);

                queries.add(temp);
            } while (cursor.moveToNext());
        }

        return queries;
    }

    public ArrayList<Integer> getEntities() {
        ArrayList<Integer> entities = new ArrayList<Integer>();

        String query = "SELECT * FROM " + TABLE_SEARCH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                int temp = cursor.getInt(2);

                entities.add(temp);
            } while (cursor.moveToNext());
        }

        return entities;
    }

}
