package DarkSkyService;




import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Android on 12/7/2017.
 */

public interface SkyDarkService {
    @GET("{lat},{lng}?extend=hourly&units=ca")
    Call<WeatherInfo> getWeatherByLocation(@Path("lat") String lat, @Path("lng") String lang);

}
