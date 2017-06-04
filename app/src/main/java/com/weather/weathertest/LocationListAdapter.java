package com.weather.weathertest;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weather.weathertest.model.PlaceModel;

import java.util.ArrayList;

/**
 * Created by ofedzhora on 04.06.2017.
 */

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {

    ArrayList<PlaceModel> itemsList;
    Context context;
    PlacesListView listView;

    public LocationListAdapter (ArrayList<PlaceModel> items, Context context, PlacesListView listView){
        itemsList = new ArrayList(items);
        this.context = context;
        this.listView = listView;
    }

    public void setItemsList(ArrayList<PlaceModel> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_list_item, parent, false);

        return new LocationViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public void onBindViewHolder(final LocationViewHolder holder, final int position) {
        holder.title.setText(itemsList.get(position).getName());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.deleting_item);
                builder.setMessage(R.string.deleting_description);
                builder.setCancelable(false);
                builder.setPositiveButton(
                        R.string.delete,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LocationManager.getInstance().removePlace(itemsList.get(holder.getAdapterPosition()));
                                itemsList.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    }
                });
                alert.show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.onPlaceClick(itemsList.get(position));
            }
        });
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView deleteButton;

        public LocationViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.locationTitle);
            deleteButton = (ImageView) itemView.findViewById(R.id.delete);
        }
    }
}
