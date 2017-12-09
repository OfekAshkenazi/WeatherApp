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

        public ArrayList<DayDetails> getDays() {
            return days;
        }

        public void setDays(ArrayList<DayDetails> days) {
            this.days = days;
        }
    }
}

