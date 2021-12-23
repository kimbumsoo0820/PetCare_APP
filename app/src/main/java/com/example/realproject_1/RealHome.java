package com.example.realproject_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RealHome extends Activity {
    ImageView check_weather;
    Button infromation, my_diary,my_pet,my_gtest,logout,good_place;

    @Override
    public void onBackPressed(){
        Intent intent_information = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent_information);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        check_weather = (ImageView)findViewById(R.id.check_weather);
        my_diary = (Button)findViewById(R.id.my_diary);
        my_pet = (Button)findViewById(R.id.my_pet);
        my_gtest=(Button)findViewById(R.id.my_gtest);
        logout=(Button)findViewById(R.id.logout);
        good_place=(Button)findViewById(R.id.good_place);
        check_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "날씨정보 입니다 :)", Toast.LENGTH_SHORT).show();
            }
        });

        infromation =(Button)findViewById(R.id.information);
        infromation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_information = new Intent(getApplicationContext(), Expertise.class);
                startActivity(intent_information);
                Toast.makeText(getApplicationContext(), "전문지식 :)", Toast.LENGTH_SHORT).show();
            }
        });

        my_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_information = new Intent(getApplicationContext(), MyDiaryActivity.class);
                startActivity(intent_information);
                Toast.makeText(getApplicationContext(),"다이어리 입니다 :)",Toast.LENGTH_SHORT).show();

            }
        });

        my_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_information = new Intent(getApplicationContext(), Test_pethome.class);
                startActivity(intent_information);
                Toast.makeText(getApplicationContext(),"펫 정보입니다 :)",Toast.LENGTH_SHORT).show();
            }
        });

        my_gtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_information = new Intent(getApplicationContext(), Test_main.class);
                startActivity(intent_information);
                Toast.makeText(getApplicationContext(),"등급을 테스트합니다. :)",Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_information = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent_information);
                Toast.makeText(getApplicationContext(),"로그아웃 되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        good_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_information = new Intent(getApplicationContext(), Map.class);
                startActivity(intent_information);
                Toast.makeText(getApplicationContext(),"Good Place",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
