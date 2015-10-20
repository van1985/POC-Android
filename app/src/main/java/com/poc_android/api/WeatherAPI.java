package com.poc_android.api;

import com.poc_android.models.WeatherData;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by vanden on 10/19/15.
 */
public interface WeatherAPI {

    @Headers({"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
            "Host:api.openweathermap.org"})
    @GET("/data/2.5/weather")
    Call<WeatherData> getWeatherFromApiSync(
            @Query("q") String cityName,
            @Query("appId") String appId
    );
}
