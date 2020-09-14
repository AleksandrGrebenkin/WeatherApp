package com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap;

import com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities.OpenWeatherRequestRestModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeather {
    @GET("data/2.5/onecall")
    Call<OpenWeatherRequestRestModel> loadWeather(@Query("lat") String lat,
                                                  @Query("lon") String lon,
                                                  @Query("appid") String apiKey,
                                                  @Query("units") String units,
                                                  @Query("lang") String lang);
}
