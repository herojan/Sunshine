package com.herojan.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void showMap(Uri geoLocation){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoLocation);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_view_location:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                String location = preferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
                Uri geoLocation = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("?", location).build();
                showMap(geoLocation);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
