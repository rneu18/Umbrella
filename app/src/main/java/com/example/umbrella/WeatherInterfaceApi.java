package com.example.umbrella;
import com.example.umbrella.Pojo.DetailWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterfaceApi {

    @GET("data/2.5/forecast?,us&appid=45ce497d7e79f72e88b6cda6ab866839")
//"data/2.5/forecast?zip=27513,us&appid=45ce497d7e79f72e88b6cda6ab866839"
    Call<DetailWeather> getWeather(@Query("zip")String myZip);
}
