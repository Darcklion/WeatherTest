package com.weather.weathertest;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.weather.weathertest.model.Coordinates;
import com.weather.weathertest.model.Place;
import com.weather.weathertest.model.WeatherMapResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class WeatherManager {
    private static String API_KEY = "c6e381d8c7ff98f0fee43775817cf6ad";
    private static String base_url = "http://api.openweathermap.org/data/2.5/forecast/daily?";

    private static WeatherManager instance;
    private WeatherMapResponse weatherData;
    private HashMap<Place, WeatherMapResponse> weatherDataStorage = new HashMap<>();
    private WeakReference<WeatherView> reference;

    private WeatherManager() {
    }

    public static WeatherManager getInstance() {
        if (instance == null) {
            instance = new WeatherManager();
        }
        return instance;
    }

    public void getWeatherData (Place location, WeatherView view) {
        reference = new WeakReference(view);
        WeatherMapResponse weatherMapResponse = weatherDataStorage.get(location);
        if (null == weatherMapResponse)
            loadWeather(location);
        else view.onWeatherLoaded(weatherMapResponse);
    }

    private void loadWeather (final Place location) {
        final Handler handler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                reference.get().onWeatherLoaded(weatherData);
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                BufferedReader reader;
                try {
                    Coordinates coordinates = location.getCoordinates();
                    url = new URL(generateUrl(coordinates.getLat(), coordinates.getLon(), getUnits()));
                    URLConnection connection = (URLConnection) url.openConnection();
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));

                    StringBuffer buffer = new StringBuffer();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }

                    line = buffer.toString();

                    Gson gson = new Gson();
                    weatherData = gson.fromJson(line, WeatherMapResponse.class);
                    weatherDataStorage.put(location, weatherData);
                    handler.sendEmptyMessage(0);
                    in.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String generateUrl (float lat, float lon, String units) {
        return base_url + "lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY + "&units=" + units;
    }

    private String getUnits () {
        return "metric";
    }
}
