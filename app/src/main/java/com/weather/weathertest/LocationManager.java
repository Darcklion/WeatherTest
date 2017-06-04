package com.weather.weathertest;

import com.weather.weathertest.model.Place;

import java.util.ArrayList;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class LocationManager {

    private static LocationManager instance;
    private ArrayList<Place> placesList = new ArrayList<Place>();

    private LocationManager() {
    }

    public static LocationManager getInstance() {
        if (instance == null) {
            instance = new LocationManager();
        }
        return instance;
    }

    public Place getPlace (int id) {
        return placesList.get(id);
    }

    public void addPlace (Place place) {
        placesList.add(place);
    }
}
