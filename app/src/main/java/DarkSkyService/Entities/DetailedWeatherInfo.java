package DarkSkyService.Entities;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DetailedWeatherInfo {
    long time;
    String summary;
    String icon;
    @SerializedName("precipProbability")
    double rainPercentage;
    double temperature;
    double humidity;

    public DetailedWeatherInfo() {

    }
    public long getTime(){
        return time;
    }
    public Date getDate() {
        Date date=new Date(time*1000);
        SimpleDateFormat format=new SimpleDateFormat("EEE, d MMM yyyy");
        format.setTimeZone(TimeZone.getDefault());
        format.format(date);
        return date;
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

    public double getRainPercentage() {
        return rainPercentage;
    }

    public void setRainPercentage(double rainPercentage) {
        this.rainPercentage = rainPercentage;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
