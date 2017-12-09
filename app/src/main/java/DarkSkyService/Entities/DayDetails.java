package DarkSkyService.Entities;

import java.util.ArrayList;

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
    double humidity;
    double windSpeed;
    ArrayList<DetailedWeatherInfo> hoursInfo;

    public DayDetails() {
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
