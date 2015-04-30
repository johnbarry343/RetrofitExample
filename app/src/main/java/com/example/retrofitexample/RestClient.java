package com.example.retrofitexample;

import retrofit.RestAdapter;

public class RestClient
{
    private static OpenWeatherMapService service;
    private static String ROOT = "http://api.openweathermap.org/data/2.5";

    static
    {
        setupRestClient();
    }

    private RestClient()
    {
    }

    public static OpenWeatherMapService get()
    {
        return service;
    }

    private static void setupRestClient()
    {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ROOT)
                                                           .setLogLevel(RestAdapter.LogLevel.FULL)
                                                           .build();

        service = restAdapter.create(OpenWeatherMapService.class);
    }
}
