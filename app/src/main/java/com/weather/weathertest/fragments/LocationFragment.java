package com.weather.weathertest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.weather.weathertest.R;
import com.weather.weathertest.adapters.WeatherListAdapter;
import com.weather.weathertest.managers.WeatherManager;
import com.weather.weathertest.interfaces.WeatherView;
import com.weather.weathertest.model.PlaceModel;
import com.weather.weathertest.model.WeatherMapResponse;

import java.util.ArrayList;

public class LocationFragment extends Fragment implements WeatherView {

    PlaceModel currentPlaceModel;
    ProgressBar progressBar;
    RecyclerView recyclerView;

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
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.weatherList);
        if (null != getArguments()){
            PlaceModel place = (PlaceModel) getArguments().getSerializable("place");
            WeatherManager.getInstance().getWeatherData(place, this);
        }

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), manager.getOrientation()));
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onWeatherLoaded(WeatherMapResponse weatherMapResponse) {
        WeatherListAdapter adapter = new WeatherListAdapter(new ArrayList(weatherMapResponse.getList()), getActivity());
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.put
//    }
}
