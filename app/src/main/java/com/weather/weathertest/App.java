package com.weather.weathertest;

import android.app.Application;

import com.weather.weathertest.managers.LocationManager;

/**
 * Created by ofedzhora on 05.06.2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LocationManager.getInstance(this);
    }
}
