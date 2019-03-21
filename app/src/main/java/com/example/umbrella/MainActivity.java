package com.example.umbrella;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umbrella.Pojo.DetailWeather;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    String tomorrowDate;
    String myDate;
    String myTime;
    String myIcon;
    String current_temp;
    String dateToday;
    String myTimeF;
    List<String> today_details = new ArrayList<>();

    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.current_temp);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
       // recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

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

        String zipString = String.valueOf(userZipCode);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInterfaceApi weatherInterfaceApi = retrofit.create(WeatherInterfaceApi.class);
        weatherInterfaceApi.getWeather(zipString).enqueue(new Callback<DetailWeather>() {

           // @SuppressLint("ResourceAsColor")
            //http://openweathermap.org/img/w/10d.png
            @Override
            public void onResponse(Call<DetailWeather> call, Response<DetailWeather> response) {
                if (response.body() !=null){
                    Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_LONG).show();
                    getCity = (TextView) findViewById(R.id.location_name);
                    getDescreption = (TextView) findViewById(R.id.weather_details);
                    getTemp = (TextView) findViewById(R.id.temperature);

                    LinearLayout topLayout = (LinearLayout) findViewById(R.id.tittle_bar);


                            String date1;
                            date1 = response.body().getList().get(0).getDtTxt();
                            String array1[]= date1.split(" ");

                            myDate = array1[0];
                            myTime = String.valueOf(array1[1].charAt(0))+String.valueOf(array1[1].charAt(1))+ ":00";

                            String country = response.body().getCity().getCountry();
                            String city = (response.body().getCity().getName());
                            getDescreption.setText(response.body().getList().get(0).getWeather().get(0).getDescription());

                            getCity.setText(city + ", " + country );
                            if (userUnit.trim().equals("Celsius") ){
                                String current_temp1 = String.valueOf(Math.round(((response.body().
                                        getList().get(0).getMain().
                                        getTemp())-273.15))*100.00/100.00);
                                getTemp.setText((current_temp1)+" C");
                            }
                            if (userUnit.trim().equals("Fahrenheit")){
                                String current_temp1 = String.valueOf(Math.round((((response.body().
                                        getList().get(0).getMain().
                                        getTemp())-273.15))*9/5+32)*100.00/100.00);
                                getTemp.setText((current_temp1)+ " F");
                            }

                            if (response.body().getList().get(0).getMain().getTemp() < 288.0){
                                topLayout.setBackgroundColor(getResources().getColor(R.color.colorDBlue));

                            }
                            if (response.body().getList().get(0).getMain().getTemp() > 303.0){
                                topLayout.setBackgroundColor(getResources().getColor(R.color.colorOrange));

                            }

                            if (response.body().getList().get(0).getMain().getTemp() <= 303.0
                                    && response.body().getList().get(0).getMain().getTemp() >= 288.0){
                                topLayout.setBackgroundColor(getResources().getColor(R.color.colorMyGrey));
                            }




                        Calendar calendar = Calendar.getInstance();
                        Date today = calendar.getTime();
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                        Date tomorrow = calendar.getTime();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        tomorrowDate = dateFormat.format(tomorrow);
                        dateToday = dateFormat.format(today);
                        calendar.add(Calendar.DAY_OF_YEAR, 2);
                        Date tomorrow2 = calendar.getTime();
                        String tomorrowDate2 = dateFormat.format(tomorrow2);


                        int i;
                        for(i =0; i<17; i++){
                            String date2;
                            date2 = response.body().getList().get(i).getDtTxt();
                            String array2[]= date2.split(" ");
                            String myDate2;
                            myDate2 = array2[0];
                            myTimeF = String.valueOf(array2[1].charAt(0))+String.valueOf(array2[1].charAt(1))+ ":00";



                            if (myDate2.equals(dateToday)){
                                if (userUnit.trim().equals("Celsius") ){
                                    current_temp = String.valueOf(Math.round(((response.body().
                                            getList().get(i).getMain().
                                            getTemp())-273.15))*100.00/100.00) + " C";

                                }
                                if (userUnit.trim().equals("Fahrenheit")){
                                    current_temp = String.valueOf(Math.round((((response.body().
                                            getList().get(i).getMain().
                                            getTemp())-273.15))*9/5+32)*100.00/100.00) + " F";

                                }

                                myIcon = response.body().getList().get(i).getWeather().get(0).getIcon();
                                String[] listOfLists = new String[]{myTimeF, myIcon, current_temp};
                                today_details.add(myTimeF);
                                today_details.add(myIcon);
                                today_details.add(current_temp);


                            }else if (myDate2.equals(tomorrowDate)){
                                if (userUnit.trim().equals("Celsius") ){
                                     current_temp = String.valueOf(Math.round(((response.body().
                                            getList().get(i).getMain().
                                            getTemp())-273.15))*100.00/100.00) +" C";

                                }
                                if (userUnit.trim().equals("Fahrenheit")){
                                     current_temp = String.valueOf(Math.round((((response.body().
                                            getList().get(i).getMain().
                                            getTemp())-273.15))*9/5+32)*100.00/100.00)+ " F";

                                }

                                myIcon = response.body().getList().get(i).getWeather().get(0).getIcon();


                                today_details.add(myTimeF);
                                today_details.add(myIcon);
                                today_details.add(current_temp);

                                System.out.println("111111111111111111111" +
                                        "111111111111111111111111" +
                                        "11111111111111111111 " +today_details.toString() );
                            } else{

                                if (userUnit.trim().equals("Celsius") ){
                                    current_temp = String.valueOf(Math.round(((response.body().
                                            getList().get(i).getMain().
                                            getTemp())-273.15))*100.00/100.00) +" C";

                                }
                                if (userUnit.trim().equals("Fahrenheit")){
                                    current_temp = String.valueOf(Math.round((((response.body().
                                            getList().get(i).getMain().
                                            getTemp())-273.15))*9/5+32)*100.00/100.00)+ " F";

                                }

                                myIcon = response.body().getList().get(i).getWeather().get(0).getIcon();


                                today_details.add(myTimeF);
                                today_details.add(myIcon);
                                today_details.add(current_temp);



                            }
                         //   recyclerView.setAdapter(new CurrentAdapter(MainActivity.this, Collections.singletonList(list));

                        }

                }else {
                    getCity = (TextView) findViewById(R.id.location_name);
                    getDescreption = (TextView) findViewById(R.id.weather_details);
                    getTemp = (TextView) findViewById(R.id.temperature);

                    LinearLayout topLayout = (LinearLayout) findViewById(R.id.tittle_bar);
                    getCity.setText("No City Found");
                    getTemp.setText("");
                    getDescreption.setText("");
                    topLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                System.out.println("111111111111111111111" +
                        "1111222222222222222222222222221111111" +
                        "11111111111111111111 " +today_details);

               recyclerView.setAdapter(new CurrentAdapter(MainActivity.this, Collections.singletonList(today_details)));

            }

            @Override
            public void onFailure(Call<DetailWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });


    }
}
