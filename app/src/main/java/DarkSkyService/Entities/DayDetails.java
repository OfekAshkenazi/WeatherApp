package DarkSkyService.Entities;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Created by Ofek on 09-Dec-17.
 */

public class DayDetails {
    long time;
    String summary;
    String icon;
    long sunriseTime;
    long sunsetTime;
    double temperatureHigh;
    double temperatureLow;
    @SerializedName("humidity")
    double humidity;
    double windSpeed;
    @SerializedName("precipProbability")
    double rainPercentage;
    ArrayList<DetailedWeatherInfo> hoursInfo;

    public DayDetails() {
    }

    public Date getDate(){
        Date date=new Date(time*1000);
        SimpleDateFormat format=new SimpleDateFormat("EEE, d MMM yyyy");
        format.setTimeZone(TimeZone.getDefault());
        format.format(date);
        return date;
    }


    public double getRainPercentage() {
        return rainPercentage;
    }

    public void setRainPercentage(double rainPercentage) {
        this.rainPercentage = rainPercentage;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public ArrayList<DetailedWeatherInfo> getHoursInfo() {
        return hoursInfo;
    }

    public void setHoursInfo(ArrayList<DetailedWeatherInfo> hoursInfo) {
        this.hoursInfo = hoursInfo;
    }


}
