package DarkSkyService.Entities;

import com.google.gson.annotations.SerializedName;

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
