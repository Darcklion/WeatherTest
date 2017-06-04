package com.weather.weathertest.model;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class Place {
    private Coordinates coordinates;
    private String name;

    public Place() {
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
