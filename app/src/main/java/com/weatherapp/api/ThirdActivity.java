package com.weatherapp.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    String woeid;
    private List<WeatherData> weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        woeid = getIntent().getStringExtra("woeid");
        Log.e("ppoo", "onCreate: "+woeid );

        weatherList = new ArrayList<>();


        String url = "https://www.metaweather.com/api/location/"+woeid+"/";

        JsonObjectRequest j = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray consolidated_weather = response.getJSONArray("consolidated_weather");

                    for(int i = 0 ; i < response.length() ; i ++){

                        JSONObject item = consolidated_weather.getJSONObject(i);
                        String weather_state_name = item.getString("weather_state_name");
                        String the_temp = item.getString("the_temp");
                        String applicable_date = item.getString("applicable_date");
                        Log.e("ppp", "onResponse: "+applicable_date );

                        WeatherData w = new WeatherData(the_temp , weather_state_name , applicable_date);
                        weatherList.add(w);


                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }




                WeatherAdapter adapter = new WeatherAdapter(ThirdActivity.this , weatherList);
                recyclerView.setAdapter(adapter);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("aaa",error.toString());

            }
        });

        RequestQueue aa = Volley.newRequestQueue(ThirdActivity.this);
        aa.add(j);
    }
}