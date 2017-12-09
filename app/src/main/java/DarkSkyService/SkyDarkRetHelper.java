package DarkSkyService;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.android.myapplication.MainActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import DarkSkyService.Entities.DayDetails;
import DarkSkyService.Entities.DetailedWeatherInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ofek on 09-Dec-17.
 */

public class SkyDarkRetHelper {
    private static final String BASE_URL = "https://api.darksky.net/forecast/";
    private static final String API_KEY = "371eb1171240e12f8b581216ca82c7c4/" ;
    private Retrofit retrofit;
    WeatherInfo info;
    public boolean isLoaded=false;
    public boolean isFailed=false;
    double lat,lng;
    public SkyDarkRetHelper(double lat,double lng) {
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL+API_KEY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.lat=lat;
        this.lng=lng;
    }
    public void loadData(){
        getWeatherInfo(lat,lng);
    }
    public WeatherInfo getInfo() {
        return info;
    }

    private void getWeatherInfo(double lat, double lng) {
        final WeatherInfo[] weatherInfo = new WeatherInfo[1];
        SkyDarkService client=retrofit.create(SkyDarkService.class);

        Call<WeatherInfo> call=client.getWeatherByLocation(String.valueOf(lat),String.valueOf(lng));
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                weatherInfo[0] =response.body();
                Log.v("response update:  ", "response received OK");
                info = weatherInfo[0];
                isLoaded=true;
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                isFailed=true;
                Log.v("response update: ","Failed");
                Log.v("Call Failed, URL:   ",call.request().url().toString());
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });


    }

    public DetailedWeatherInfo getCurrentInfo(){
        if (info==null){
            throw new NullPointerException("data didn't load yet");
        }
        else {
            return info.currently;
        }
    }

    public ArrayList<DayDetails> getAllDays() {
        if (info==null){
            throw new NullPointerException("data didn't load yet");
        }
        ArrayList<DayDetails> days = new ArrayList<>();
        for (int i = 0; i < info.dailyResult.days.size(); i++) {
            long startTime, endTime;
            DayDetails currentDay = info.dailyResult.days.get(i);
            startTime = currentDay.getTime();
            if (i >= info.dailyResult.days.size() - 1) {
                endTime = startTime + (24 * 36000);
            } else {
                DayDetails nextDay = info.dailyResult.days.get(i + 1);
                endTime = nextDay.getTime();
            }
            currentDay.setHoursInfo(getCurrentDayHourlyInfo(currentDay.getTime(), endTime));
            days.add(currentDay);
        }
        return days;

    }

    private ArrayList<DetailedWeatherInfo> getCurrentDayHourlyInfo(long dayStart,long dayEnd) {
        ArrayList<DetailedWeatherInfo> hours=info.hourlyResult.hours;
        ArrayList<DetailedWeatherInfo> currentDayHours=new ArrayList<>();
        Iterator<DetailedWeatherInfo> iterator=hours.iterator();
        while (iterator.hasNext()){
            DetailedWeatherInfo info=iterator.next();
            if (dayEnd<=info.getTime())
                break;
            if (dayStart<=info.getTime()){
                currentDayHours.add(info);
            }
        }
        return currentDayHours;
    }


}
