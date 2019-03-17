package com.example.umbrella;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView settingImage;
    int userZipCode = 0;
    String userUnit;

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
           initilizeRetrofit();
        }

    }

    private void initilizeRetrofit() {




    }
}
