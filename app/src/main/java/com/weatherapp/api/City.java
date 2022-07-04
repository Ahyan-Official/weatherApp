package com.weatherapp.api;


public class City {
    private String name, woeid;
    private int id;

    public City(String name, String position, int id) {
        this.name = name;
        this.woeid = position;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
