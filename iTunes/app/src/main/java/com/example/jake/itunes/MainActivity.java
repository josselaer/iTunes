package com.example.jake.itunes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        /*Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Young & Beautiful.tff");
        TextView myTextView = (TextView) findViewById(R.id.welcomeFont);
        myTextView.setTypeface(myTypeface);*/


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/ //I  think I took out toolbar so there is no reference to it


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //not sure if we need this and "fab" is causing an error
        tryMusicPreview();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        String opt1 = "Search";
        int groupId = Menu.NONE;
        int itemID = Menu.FIRST;
        int order = 10;
        menu.add(groupId, itemID, order, opt1);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tryMusicPreview()  {
        iTunesSearch its = new iTunesSearch("Taylor Swift");
        its.execute();
        Log.d(TAG, "after execute");
        if(its.isThreadDone() == false) {
            Log.d(TAG, "is thread done " + its.isThreadDone());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JSONArray response = its.getResults();
        Log.d(TAG, response.toString());
        String preview = null;
        try {
            preview = response.getJSONObject(0).getString("previewUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
