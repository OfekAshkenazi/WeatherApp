package com.example.android.myapplication;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Date;

import Adapters.DaysListAdapter;
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
    DaysListAdapter adapter;
    RecyclerView daysList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadDataTask task=new LoadDataTask(this);
        task.execute(new Double[]{lat,lng});
        setViews();
        daysList.setClipToPadding(true);
    }

    private void setViews() {
        daysList=findViewById(R.id.daysList);
        daysList.setLayoutManager(new LinearLayoutManager(this));
        SnapHelper helper=new LinearSnapHelper();
        helper.attachToRecyclerView(daysList);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDataLoaded(ArrayList<DayDetails> days) {
        adapter=new DaysListAdapter(days);
        daysList.setAdapter(adapter);
    }
}


class LoadDataTask extends AsyncTask<Double,Integer,ArrayList<DayDetails>>{

    private SkyDarkRetHelper helper;
    private OnWeatherDataLoadedListener listener;

    public LoadDataTask(OnWeatherDataLoadedListener listener) {
        this.listener = listener;
    }

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
        listener.onDataLoaded(days);
    }
    interface OnWeatherDataLoadedListener{
        void onDataLoaded(ArrayList<DayDetails> days);
    }
}
