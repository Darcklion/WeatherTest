package com.weather.weathertest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class WeatherItem {
    @Expose
    @SerializedName("dt")
    private long date;

    @Expose
    @SerializedName("temp")
    private Temperature temperature;


    @Expose
    @SerializedName("pressure")
    private float pressure;

    @Expose
    @SerializedName("humidity")
    private int humidity;

    @Expose
    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @Expose
    @SerializedName("speed")
    private float windSpeed;

    @Expose
    @SerializedName("deg")
    private int windDegrees;

    @Expose
    @SerializedName("clouds")
    private int clouds;

    public long getDt() {
        return date;
    }

    public void setDt(long date) {
        this.date = date;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(int windDegrees) {
        this.windDegrees = windDegrees;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }
}
