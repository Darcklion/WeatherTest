package com.weather.weathertest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ofedzhora on 04.06.2017.
 */

class Coordinates {
    @Expose
    @SerializedName("lat")
    private long lat;

    @Expose
    @SerializedName("lon")
    private long lon;

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }
}
