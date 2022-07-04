package com.weatherapp.api;

public class WeatherData {

    private String the_temp , weather_state_name , applicable_date;


    public WeatherData() {
    }

    public WeatherData(String the_temp, String weather_state_name, String applicable_date) {
        this.the_temp = the_temp;
        this.weather_state_name = weather_state_name;
        this.applicable_date = applicable_date;
    }

    public String getThe_temp() {
        return the_temp;
    }

    public void setThe_temp(String the_temp) {
        this.the_temp = the_temp;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public void setApplicable_date(String applicable_date) {
        this.applicable_date = applicable_date;
    }
}
