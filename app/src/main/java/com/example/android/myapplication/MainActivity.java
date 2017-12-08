package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import DarkSkyService.SkyDarkService;
import DarkSkyService.WeatherInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.darksky.net/forecast/";
    private static final String API_KEY = "371eb1171240e12f8b581216ca82c7c4/" ;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL+API_KEY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInfo weatherInfo=getWeatherInfo();
    }

    public WeatherInfo getWeatherInfo() {
        final WeatherInfo[] weatherInfo = new WeatherInfo[1];
        SkyDarkService client=retrofit.create(SkyDarkService.class);

        Call<WeatherInfo> call=client.getWeatherByLocation("32.7911269","35.0349122");
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                weatherInfo[0] =response.body();
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                Log.v("Call Failed, URL:   ",call.request().url().toString());
            }
        });

        return weatherInfo[0];
    }
}
