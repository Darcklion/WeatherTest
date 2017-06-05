package com.weather.weathertest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.weather.weathertest.fragments.HelpFragment;
import com.weather.weathertest.fragments.LocationFragment;
import com.weather.weathertest.fragments.LocationListFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar = null;
    public BottomNavigationView bottomNavigation = null;
    private GoogleApiClient mGoogleApiClient;
    public int lastIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MainActivity.this, "Google client fail connection", Toast.LENGTH_LONG).show();
                    }
                })
                .build();

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                changeFragment(item.getItemId());
                return true;
            }
        });

        if (null != savedInstanceState) {
            changeFragment(savedInstanceState.getInt("id"));
        } else{
            changeFragment(R.id.action_list);
        }
    }

    private void changeFragment(int id) {
        switch (id) {
            case R.id.action_list: {
                toolbar.setTitle(R.string.menu_list);
                getSupportFragmentManager().beginTransaction().replace(R.id.tabFragmentHolder, new LocationListFragment()).commit();
                break;
            }
            case R.id.action_location: {
                toolbar.setTitle(R.string.menu_location);
                getSupportFragmentManager().beginTransaction().replace(R.id.tabFragmentHolder, new LocationFragment()).commit();
                break;
            }
            case R.id.action_help: {
                toolbar.setTitle(R.string.menu_help);
                getSupportFragmentManager().beginTransaction().replace(R.id.tabFragmentHolder, new HelpFragment()).commit();
                break;
            }
        }
        lastIndex = id;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("id", lastIndex);
        super.onSaveInstanceState(outState);
    }
}
