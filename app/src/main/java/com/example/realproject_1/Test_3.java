package com.example.realproject_1;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Test_3 extends AppCompatActivity {
    int add3, t1_sum;

    Button test3_finish;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_3);
        add3 = 0;


        test3_finish = (Button) findViewById(R.id.test3_finish);

        test3_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add3 = 0;
                CheckBox test_1_top = (CheckBox) findViewById(R.id.test_3_top);
                CheckBox test_1_middle = (CheckBox) findViewById(R.id.test_3_middle);
                CheckBox test_1_bottom = (CheckBox) findViewById(R.id.test_3_bottom);


                if ((test_1_top).isChecked() == true && (test_1_middle).isChecked() == true) {
                    Toast.makeText(getApplicationContext(), "하나만 체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }
                if ((test_1_top).isChecked() == true && (test_1_bottom).isChecked() == true) {
                    Toast.makeText(getApplicationContext(), "하나만 체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }
                if ((test_1_bottom).isChecked() == true && (test_1_middle).isChecked() == true) {
                    Toast.makeText(getApplicationContext(), "하나만 체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }
                if ((test_1_bottom).isChecked() == true && (test_1_middle).isChecked() == true && (test_1_top).isChecked() == true) {
                    Toast.makeText(getApplicationContext(), "하나만 체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }
                if ((test_1_bottom).isChecked() == false && (test_1_middle).isChecked() == false && (test_1_top).isChecked() == false) {
                    Toast.makeText(getApplicationContext(), "체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }


                if ((test_1_top).isChecked() == true && (test_1_bottom).isChecked() == false && (test_1_middle).isChecked() == false) {
                    add3 = 10;
                    Intent getintent3 = getIntent();
                    int data2 = getintent3.getIntExtra("add2", 0);
                    add3 = data2 + add3;
                    Log.v("인텐트","add3값= "+add3);
                    Toss_add3();

                }// top 체크

                if ((test_1_middle).isChecked() == true && (test_1_top).isChecked() == false && (test_1_bottom).isChecked() == false) {
                    add3 = 5;
                    Intent getintent3 = getIntent();
                    int data2 = getintent3.getIntExtra("add2", 0);
                    add3 = data2 + add3;
                    Log.v("인텐트","add3값= "+add3);
                    Toss_add3();

                }//middle 체크

                if ((test_1_bottom).isChecked() == true && (test_1_middle).isChecked() == false && (test_1_top).isChecked() == false) {
                    add3 = 3;
                    Intent getintent3 = getIntent();
                    int data2 = getintent3.getIntExtra("add2", 0);
                    add3 = data2 + add3;
                    Log.v("인텐트","add3값= "+add3);
                    Toss_add3();

                }//바텀 체크





            }
        });


    }

    public void Toss_add3() {
        Intent intent = new Intent(this, Test_main.class);
        intent.putExtra("add3", add3);
        intent.putExtra("flag", 1);
        startActivity(intent);
    }

}


