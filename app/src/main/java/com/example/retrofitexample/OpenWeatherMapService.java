package com.example.retrofitexample;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by john on 4/15/2015.
 */
public interface OpenWeatherMapService
{
    @GET("/weather")
    void currentWeather(@Query(
            "zip") String location, Callback<WeatherResponse> cb);
}
