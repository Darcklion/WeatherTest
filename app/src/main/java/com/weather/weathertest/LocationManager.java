package com.weather.weathertest;

import com.weather.weathertest.model.PlaceModel;

import java.util.ArrayList;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class LocationManager {

    private static LocationManager instance;
    public ArrayList<PlaceModel> placesList = new ArrayList<PlaceModel>();

    private LocationManager() {
    }

    public static LocationManager getInstance() {
        if (instance == null) {
            instance = new LocationManager();
        }
        return instance;
    }

    public PlaceModel getPlace (int id) {
        return placesList.get(id);
    }

    public void addPlace (PlaceModel placeModel) {
        placesList.add(placeModel);
    }

    public void removePlace (PlaceModel placeModel) {
        placesList.remove(placeModel);
    }
}
