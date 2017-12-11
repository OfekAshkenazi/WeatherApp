package com.example.android.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
    private static final int LOCATION_REQ_CODE = 111;
    Double lat=null;
    Double lng=null;
    private SkyDarkRetHelper helper;
    DaysListAdapter adapter;
    RecyclerView daysList;
    public static String timezone="";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQ_CODE);
            }
        }
        else {
            dialog = ProgressDialog.show(this, "Data Loading",
                    "Loading. Please wait...", true);
            getLocation();
            setViews();
        }

    }

    private void setViews() {
        daysList=findViewById(R.id.daysList);
        daysList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,((LinearLayoutManager)daysList.getLayoutManager()).getOrientation());
        daysList.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onDataLoaded(ArrayList<DayDetails> days) {
        adapter=new DaysListAdapter(days,this);
        daysList.setAdapter(adapter);
        daysList.setVisibility(View.VISIBLE);
        dialog.cancel();
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }else {
            LocationManager manager= (LocationManager) getSystemService(LOCATION_SERVICE);
            manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,10,listener);

        }
    }
    LocationListener listener=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (lat==null&&lng==null) {
                lat = location.getLatitude();
                lng = location.getLongitude();
                LoadDataTask task = new LoadDataTask(MainActivity.this);
                task.execute(new Double[]{lat, lng});
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==LOCATION_REQ_CODE&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            setViews();
            dialog = ProgressDialog.show(this, "Data Loading",
                    "Loading. Please wait...", true);
            LocationManager manager= (LocationManager) getSystemService(LOCATION_SERVICE);
            manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,10,listener);
        }

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
