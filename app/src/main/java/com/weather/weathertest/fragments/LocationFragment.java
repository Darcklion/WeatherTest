package com.weather.weathertest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weather.weathertest.R;
import com.weather.weathertest.WeatherManager;
import com.weather.weathertest.WeatherView;
import com.weather.weathertest.model.Coordinates;
import com.weather.weathertest.model.Place;
import com.weather.weathertest.model.WeatherMapResponse;

public class LocationFragment extends Fragment implements WeatherView {

    Place currentPlace;

    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Place place = new Place();
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(49.842529f);
        coordinates.setLon(24.026970f);
        place.setCoordinates(coordinates);
        WeatherManager.getInstance().getWeatherData(place, this);
    }

    @Override
    public void onWeatherLoaded(WeatherMapResponse weatherMapResponse) {
        Toast.makeText(getActivity(), weatherMapResponse.getCity().getName(), Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.put
//    }
}
