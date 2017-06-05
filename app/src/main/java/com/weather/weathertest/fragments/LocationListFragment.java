package com.weather.weathertest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.weather.weathertest.MainActivity;
import com.weather.weathertest.R;
import com.weather.weathertest.adapters.LocationListAdapter;
import com.weather.weathertest.interfaces.PlacesListView;
import com.weather.weathertest.managers.LocationManager;
import com.weather.weathertest.model.Coordinates;
import com.weather.weathertest.model.PlaceModel;

import static android.app.Activity.RESULT_OK;

public class LocationListFragment extends Fragment implements PlacesListView {

    private int PLACE_PICKER_REQUEST = 1;
    private RecyclerView recyclerView;

    public LocationListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        LocationListAdapter adapter = new LocationListAdapter(LocationManager.getInstance(getActivity()).placesList.getPlacesList(), getActivity(), this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), manager.getOrientation()));
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_search) {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

            try {
                startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getActivity(), data);
                PlaceModel model = new PlaceModel();
                model.setName(place.getName().toString());
                model.setCoordinates(new Coordinates(place.getLatLng().latitude, place.getLatLng().longitude));
                LocationManager.getInstance(getActivity()).addPlace(model);
                ((LocationListAdapter)recyclerView.getAdapter()).setItemsList(LocationManager.getInstance(getActivity()).placesList.getPlacesList());
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onPlaceClick(PlaceModel placeModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", placeModel);
        LocationFragment locationFragment = new LocationFragment();
        locationFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.tabFragmentHolder, locationFragment).commit();
        ((MainActivity)getActivity()).lastIndex = R.id.action_location;
    }
}
