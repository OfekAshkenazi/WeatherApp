package DarkSkyService;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 12/7/2017.
 */

public class WeatherInfo {
    @SerializedName("latitude")
    double lat;
    @SerializedName("longtitude")
    double lng;
    @SerializedName("timezone")
    String timeZone;
    @SerializedName("currently")
    CurrentWeatherInfo currentInfo;


    public class CurrentWeatherInfo {
        String currently;
        String icon;
        @SerializedName("precipProbability")
        int rainPrecentage;
        double temperature;
        double humidity;
    }
}
