package com.example.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity
{
    @InjectView(R.id.cityName)
    TextView cityNameText;

    @InjectView(R.id.lat)
    TextView latText;

    @InjectView(R.id.lon)
    TextView lonText;

    @InjectView(R.id.refreshButton)
    Button refreshButton;

    @InjectView(R.id.editText)
    EditText editText;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        realm = Realm.getInstance(this);
    }

    @OnClick(R.id.refreshButton)
    protected void onRrefreshButtonClick()
    {
        refreshWeather();
    }

    private void refreshWeather()
    {
        RestClient.get().currentWeather(String.format("%s,us", editText.getText()),
                                        new Callback<WeatherResponse>()
                                        {
                                            @Override
                                            public void success(WeatherResponse weatherResponse, Response response)
                                            {
                                                realm.beginTransaction();
                                                WeatherDatabaseModel weatherDatabaseModel = realm
                                                        .createObject(WeatherDatabaseModel.class);
                                                weatherDatabaseModel.setName(weatherResponse.name);
                                                weatherDatabaseModel
                                                        .setLat(weatherResponse.coord.lat);
                                                weatherDatabaseModel
                                                        .setLon(weatherResponse.coord.lon);
                                                realm.commitTransaction();

                                                RealmResults<WeatherDatabaseModel> results = realm
                                                        .where(WeatherDatabaseModel.class)
                                                        .equalTo("name", "Birmingham").findAll();

                                                cityNameText
                                                        .setText("City: " + weatherResponse.name);
                                                latText.setText("Latitude: " + Double
                                                        .toString(weatherResponse.coord.lat));
                                                lonText.setText("Longitude: " + Double
                                                        .toString(weatherResponse.coord.lon));
                                            }

                                            @Override
                                            public void failure(RetrofitError error)
                                            {
                                                Log.e("FAILURE", "It failed!!");
                                            }
                                        });
    }
}
