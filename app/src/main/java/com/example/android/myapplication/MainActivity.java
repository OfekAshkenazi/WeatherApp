package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL+API_KEY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SkyDarkService client=retrofit.create(SkyDarkService.class);

        Call<WeatherInfo> call=client.getWeatherByLocation("32.7911269","35.0349122");
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {

            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {

            }
        });
    }
}
