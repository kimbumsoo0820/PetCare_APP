package com.example.realproject_1;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class Test_main extends Activity {
    Button back_test_home, start_test;
    ImageView img_mygrade;
    TextView grade_date;
    int data = 0;
    @Override
    public void onBackPressed(){
        Intent intent_information = new Intent(getApplicationContext(), RealHome.class);
        startActivity(intent_information);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        //


        img_mygrade = (ImageView) findViewById(R.id.img_mygrade);
        back_test_home = (Button) findViewById(R.id.back_test_home);
        start_test = (Button) findViewById(R.id.start_test);
        grade_date = (TextView) findViewById(R.id.TextView_grade_data);



        try {
            Intent getintent1 = getIntent();
            int data1 = getintent1.getIntExtra("add1", 0);
            Intent getintent2 = getIntent();
            int data2 = getintent2.getIntExtra("add2", 0);
            Intent getintent3 = getIntent();
            int data3 = getintent3.getIntExtra("add3", 0);

            Intent getflag = getIntent();
            int flag = getflag.getIntExtra("flag", 0);

            data = data1 + data2 + data3;
            Log.v("데이터", "데이터값" + data + "데이터1값" + data1 + "데이터2값" + data2 + "데이터3값" + data3);
            Log.v("플래그", "플래그값" + flag);


            if (flag == 1) {


                if (data >= 25) {

                    Drawable drawable = getResources().getDrawable(R.drawable.gold);
                    ImageView imageView = (ImageView) findViewById(R.id.img_mygrade);
                    imageView.setImageDrawable(drawable);
                    Toast.makeText(getApplicationContext(), "나의점수" + data, Toast.LENGTH_SHORT).show();
                }

                if (data > 9 && data < 25) {

                    Drawable drawable = getResources().getDrawable(R.drawable.silver);
                    ImageView imageView = (ImageView) findViewById(R.id.img_mygrade);
                    imageView.setImageDrawable(drawable);
                    Toast.makeText(getApplicationContext(), "나의점수" + data, Toast.LENGTH_SHORT).show();
                }

                if (data < 10) {

                    Drawable drawable = getResources().getDrawable(R.drawable.bronzw);
                    ImageView imageView = (ImageView) findViewById(R.id.img_mygrade);
                    imageView.setImageDrawable(drawable);
                    Toast.makeText(getApplicationContext(), "나의점수" + data, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "값을 받지 못함", Toast.LENGTH_SHORT).show();
        }


        start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Test_1.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "테스트를 시작합니다 :)", Toast.LENGTH_SHORT).show();
            }
        });


        //에러검출 시도

        back_test_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RealHome.class);
                startActivity(intent);
            }
        });

    }
}


