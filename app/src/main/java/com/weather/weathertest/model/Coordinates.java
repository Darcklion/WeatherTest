package com.weather.weathertest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class Coordinates {
    @Expose
    @SerializedName("lat")
    private float lat;

    @Expose
    @SerializedName("lon")
    private float lon;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
