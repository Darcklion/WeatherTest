package com.weather.weathertest;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.weather.weathertest.model.PlaceModel;
import com.weather.weathertest.model.PlacesList;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class LocationManager {

    private static String CONST = "LOCATION_LIST";
    public static final String PREFS_NAME = "MyPrefsFile";
    private static LocationManager instance;
    public PlacesList placesList;
    private static SharedPreferences preferences;

    private LocationManager() {
        placesList = new Gson().fromJson(preferences.getString(CONST, ""), PlacesList.class);
        if (null == placesList)
            placesList = new PlacesList();
    }

    public static LocationManager getInstance(Context context) {
        if (instance == null) {
            preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            instance = new LocationManager();
        }
        return instance;
    }

    public PlaceModel getPlace (int id) {
        return placesList.getPlacesList().get(id);
    }

    public void addPlace (PlaceModel placeModel) {
        placesList.getPlacesList().add(placeModel);
        cacheData();
    }

    public void removePlace (PlaceModel placeModel) {
        placesList.getPlacesList().remove(placeModel);
        cacheData();
    }

    private void cacheData(){
        preferences.edit().remove(CONST).commit();
        preferences.edit().putString(CONST, new Gson().toJson(placesList)).commit();
    }
}
