package DarkSkyService;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import DarkSkyService.Entities.DayDetails;
import DarkSkyService.Entities.DetailedWeatherInfo;


/**
 * Created by Android on 12/7/2017.
 */

public class WeatherInfo {
    @SerializedName("latitude")
    double lat;
    @SerializedName("longitude")
    double lng;
    @SerializedName("timezone")
    String timeZone;
    @SerializedName("currently")
    DetailedWeatherInfo currently;
    @SerializedName("hourly")
    HourlyResult hourlyResult;
    @SerializedName("daily")
    DailyResult dailyResult;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        timeZone = timeZone;
    }

    public DetailedWeatherInfo getCurrently() {
        return currently;
    }

    public void setCurrently(DetailedWeatherInfo currently) {
        this.currently = currently;
    }

    public HourlyResult getHourlyResult() {
        return hourlyResult;
    }

    public void setHourlyResult(HourlyResult hourlyResult) {
        this.hourlyResult = hourlyResult;
    }

    public DailyResult getDailyResult() {
        return dailyResult;
    }

    public void setDailyResult(DailyResult dailyResult) {
        this.dailyResult = dailyResult;
    }

    class HourlyResult{
        @SerializedName("data")
        ArrayList<DetailedWeatherInfo> hours;

        public ArrayList<DetailedWeatherInfo> getHours() {
            return hours;
        }

        public void setHours(ArrayList<DetailedWeatherInfo> hours) {
            this.hours = hours;
        }
    }
    class DailyResult{
        @SerializedName("data")
        ArrayList<DayDetails> days;
        String timezone;

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public ArrayList<DayDetails> getDays() {
            return days;
        }

        public void setDays(ArrayList<DayDetails> days) {
            this.days = days;
        }
    }
}

