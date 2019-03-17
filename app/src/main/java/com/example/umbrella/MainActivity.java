package com.example.umbrella;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umbrella.Pojo.DetailWeather;
import com.example.umbrella.Pojo.Sys;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView settingImage;
    int userZipCode = 0;
    String userUnit;
    String Base_Url = "https://api.openweathermap.org/";
    TextView getCity, getTemp, getDescreption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingImage =(ImageView) findViewById(R.id.setting_btn);
        settingImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivityForResult(intent, 1);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        userZipCode = sharedPreferences.getInt("zip", 0);
        userUnit = sharedPreferences.getString("unit", "");
        if(userZipCode == 0){
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivityForResult(intent, 1);

        }else{
           initilizedRetrofit();
        }

    }

    public void initilizedRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInterfaceApi weatherInterfaceApi = retrofit.create(WeatherInterfaceApi.class);
        weatherInterfaceApi.getWeather().enqueue(new Callback<DetailWeather>() {

            @Override
            public void onResponse(Call<DetailWeather> call, Response<DetailWeather> response) {
                System.out.print("11111111111111111111111111111111111111111");
                if (response.body() !=null){
                    System.out.print("11111111111111111111111111111111111111111");
                    Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_LONG).show();

                    getCity = (TextView) findViewById(R.id.location_name);
                    getDescreption = (TextView) findViewById(R.id.weather_details);
                    getTemp = (TextView) findViewById(R.id.temperature);

                    try {
                            String country = response.body().getCity().getCountry();
                            String city = (response.body().getCity().getName());
                            getDescreption.setText(response.body().getList().get(0).getWeather().get(0).getDescription());

                            getCity.setText(city + ", " + country );

                            if (userUnit.trim().equals("Celsius") ){
                                String current_temp = String.valueOf(Math.round(((response.body().
                                        getList().get(0).getMain().
                                        getTemp())-273.15))*100.00/100.00);
                                getTemp.setText((current_temp));
                            }
                            if (userUnit.trim().equals("Fahrenheit")){
                                String current_temp = String.valueOf(Math.round((((response.body().
                                        getList().get(0).getMain().
                                        getTemp())-273.15))*9/5+32)*100.00/100.00);
                                getTemp.setText((current_temp));
                            }

                    } catch(Exception e){

                            getCity.setText("No City Found");

                    }
                }




            }

            @Override
            public void onFailure(Call<DetailWeather> call, Throwable t) {
                System.out.print("11111111111111111111111111111111111111111");
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();



            }
        });


    }
}
