package com.weather.weathertest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ofedzhora on 05.06.2017.
 */

public class PlacesList {
    @Expose
    @SerializedName("placesList")
    public ArrayList<PlaceModel> placesList = new ArrayList<PlaceModel>();

    public ArrayList<PlaceModel> getPlacesList() {
        return placesList;
    }

    public void setPlacesList(ArrayList<PlaceModel> placesList) {
        this.placesList = placesList;
    }
}
