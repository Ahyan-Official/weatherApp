package com.weatherapp.api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<City> cities;

    public MyAdapter(Context ctx,ArrayList<City> players)
    {
        //ASSIGN THEM LOCALLY
        this.c=ctx;
        this.cities =players;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJ FROM XML
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);

        //holder

        MyHolder holder=new MyHolder(v);

        return holder;
    }

    //BIND DATA TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
           holder.woeidTxt.setText(cities.get(position).getWoeid());
           holder.nameTxt.setText(cities.get(position).getName());

           holder.id.setText(String.valueOf(cities.get(position).getId()));

            Log.e("oioi", "onBindViewHolder: "+cities.get(position).getId() );

           holder.woeTxt2.setText(cities.get(position).getWoeid());

        String url = "https://www.metaweather.com/api/location/"+ cities.get(position).getWoeid()+"/";

        JsonObjectRequest j = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray consolidated_weather = response.getJSONArray("consolidated_weather");

                    JSONObject  item = consolidated_weather.getJSONObject(0);
                    String name = item.getString("weather_state_name");
                    holder.temp.setText(item.getString("the_temp"));
                    holder.max.setText(item.getString("max_temp"));
                    holder.min.setText(item.getString("min_temp"));
                    holder.woeidTxt.setText(item.getString("weather_state_name"));

                    Log.e("aaa",name);


                } catch (JSONException e) {
                    e.printStackTrace();
                }







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("aaa",error.toString());

            }
        });

        RequestQueue aa = Volley.newRequestQueue(c);
        aa.add(j);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void find_waether(){

        String url = "https://www.metaweather.com/api/location/44418/";

        JsonObjectRequest j = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray consolidated_weather = response.getJSONArray("consolidated_weather");

                    JSONObject  item = consolidated_weather.getJSONObject(0);
                    String name = item.getString("weather_state_name");
                    Log.e("aaa",name);


                } catch (JSONException e) {
                    e.printStackTrace();
                }







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("aaa",error.toString());

            }
        });

        RequestQueue aa = Volley.newRequestQueue(c);
        aa.add(j);


    }
}
