package com.weather.weathertest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weather.weathertest.ImageViewLoader;
import com.weather.weathertest.R;
import com.weather.weathertest.managers.WeatherManager;
import com.weather.weathertest.model.WeatherItem;

import java.util.ArrayList;

/**
 * Created by ofedzhora on 05.06.2017.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {

    static String BASE_ICON_URL = "http://openweathermap.org/img/w/";
    ArrayList<WeatherItem> itemsList;
    Context context;

    public WeatherListAdapter (ArrayList<WeatherItem> items, Context context){
        itemsList = new ArrayList(items);
        this.context = context;
    }

    public void setItemsList(ArrayList<WeatherItem> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_line_item, parent, false);

        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position) {
        WeatherItem model = itemsList.get(position);
        holder.day.setText(DateUtils.getRelativeTimeSpanString(model.getDt() * 1000));
        holder.weather.setText(model.getWeather().get(0).getDescription());
        holder.temperature.setText(String.valueOf(model.getTemperature().getDay()) + getTemperatureSymbol());
        holder.humidity.setText(model.getHumidity() + "%");
        holder.windSpeed.setText(String.valueOf(model.getWindSpeed()) + getSpeedText());
        ImageViewLoader.loadFromUrl(BASE_ICON_URL + model.getWeather().get(0).getIcon() + ".png", holder.weatherImage);
        Log.d("ICON", model.getWeather().get(0).getIcon());
    }

    private String getTemperatureSymbol(){
        if (WeatherManager.getInstance().isMetricUsed())
            return "\u2103";
        else return "\u2109";
    }

    private String getSpeedText(){
        if (WeatherManager.getInstance().isMetricUsed())
            return "m/s";
        else return "miles/h";
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView day;
        TextView weather;
        TextView temperature;
        TextView humidity;
        TextView windSpeed;
        ImageView weatherImage;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            weather = (TextView) itemView.findViewById(R.id.weather);
            temperature = (TextView) itemView.findViewById(R.id.temperature);
            humidity = (TextView) itemView.findViewById(R.id.humidity);
            windSpeed = (TextView) itemView.findViewById(R.id.windSpeed);
            weatherImage = (ImageView) itemView.findViewById(R.id.weatherIcon);
        }
    }
}
