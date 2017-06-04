package com.weather.weathertest;

import com.weather.weathertest.model.WeatherMapResponse;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public interface WeatherView {
    public void onWeatherLoaded(WeatherMapResponse weatherMapResponse);
}
