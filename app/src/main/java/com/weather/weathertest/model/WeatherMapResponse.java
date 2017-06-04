package com.weather.weathertest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class WeatherMapResponse {
    @Expose
    @SerializedName("city")
    private City city;

    @Expose
    @SerializedName("cnt")
    private int listItemsCount;

    @Expose
    @SerializedName("list")
    private List<WeatherItem> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getListItemsCount() {
        return listItemsCount;
    }

    public void setListItemsCount(int listItemsCount) {
        this.listItemsCount = listItemsCount;
    }

    public List<WeatherItem> getList() {
        return list;
    }

    public void setList(List<WeatherItem> list) {
        this.list = list;
    }
}
