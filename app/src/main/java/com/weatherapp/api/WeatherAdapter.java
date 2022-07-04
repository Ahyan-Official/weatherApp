package com.weatherapp.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private Context context;
    private List<WeatherData> weatherList;

    public WeatherAdapter(Context context , List<WeatherData> weather){
        this.context = context;
        weatherList = weather;
    }
    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_item , parent , false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {

        WeatherData we = weatherList.get(position);

        holder.temp.setText(we.getThe_temp().toString());
        holder.weather_state.setText(we.getWeather_state_name());
        holder.date.setText(we.getApplicable_date());



    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public static class WeatherHolder extends RecyclerView.ViewHolder{

        TextView temp , date , weather_state;

        public WeatherHolder(@NonNull View itemView) {
            super(itemView);

            temp = itemView.findViewById(R.id.temp);
            date = itemView.findViewById(R.id.date);
            weather_state = itemView.findViewById(R.id.weather_state);

        }
    }
}
