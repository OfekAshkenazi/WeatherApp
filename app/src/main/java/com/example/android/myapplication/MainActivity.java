package com.example.android.myapplication;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Date;

import DarkSkyService.Entities.DayDetails;
import DarkSkyService.SkyDarkRetHelper;
import DarkSkyService.SkyDarkService;
import DarkSkyService.WeatherInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LoadDataTask.OnWeatherDataLoadedListener {
    double lat=32.5165219;
    double lng=35.4287508;
    private SkyDarkRetHelper helper;
    ArrayList<DayDetails> weekDetails;
    FoldingCell cell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LoadDataTask task=new LoadDataTask();
//        task.execute(new Double[]{lat,lng});
        cell=findViewById(R.id.cell);
        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cell.toggle(false);
                cell.initialize(30, 1000, Color.DKGRAY, 2);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDataLoaded(ArrayList<DayDetails> days) {

    }
}


class LoadDataTask extends AsyncTask<Double,Integer,ArrayList<DayDetails>>{

    private SkyDarkRetHelper helper;

    @Override
    protected ArrayList<DayDetails> doInBackground(Double... coordinates) {
        helper=new SkyDarkRetHelper(coordinates[0],coordinates[1]);
        helper.loadData();
        while (!helper.isFailed&&!helper.isLoaded){

        }
        return helper.getAllDays();
    }

    @Override
    protected void onPostExecute(ArrayList<DayDetails> days) {
        for (DayDetails day:days){
            Date date=new Date(day.getTime()*1000);
            Log.v("Days:   ",date.toString());
        }
    }
    interface OnWeatherDataLoadedListener{
        void onDataLoaded(ArrayList<DayDetails> days);
    }
}
