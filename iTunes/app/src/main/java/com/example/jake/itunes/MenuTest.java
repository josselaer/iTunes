package com.example.jake.itunes;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Menu;


/**
 * Created by quincyschurr on 12/11/15.
 */
public class MenuTest extends AppCompatActivity{

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favorite, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_favorite) {
            //mark currently visible content in the activity
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
