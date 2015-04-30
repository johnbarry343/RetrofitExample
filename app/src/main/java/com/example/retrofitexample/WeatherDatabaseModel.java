package com.example.retrofitexample;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by john on 4/19/2015.
 */
public class WeatherDatabaseModel extends RealmObject
{
    @PrimaryKey
    private String name;
    private double lat;
    private double lon;

    public WeatherDatabaseModel(String name, double lat, double lon)
    {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }
}
