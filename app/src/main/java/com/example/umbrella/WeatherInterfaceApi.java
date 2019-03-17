package com.example.umbrella;
import com.example.umbrella.Pojo.DetailWeather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherInterfaceApi {
    @GET("data/2.5/forecast?zip=27513,us&appid=d32e9228eb6f80897b11799f3b586c0c")
    Call<DetailWeather> getWeather();
}
