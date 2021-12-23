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


public class Test_2 extends AppCompatActivity {
    int add2,t1_sum;

    Button test2_go_test3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_2);

        add2=0;



        test2_go_test3=(Button)findViewById(R.id.test2_go_test3);

        test2_go_test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add2 =0;
                CheckBox test_1_top = (CheckBox)findViewById(R.id.test_2_top);
                CheckBox test_1_middle = (CheckBox)findViewById(R.id.test_2_middle);
                CheckBox test_1_bottom = (CheckBox)findViewById(R.id.test_2_bottom);


                if ((test_1_top).isChecked() == true && (test_1_middle).isChecked() == true) {
                    Toast.makeText(getApplicationContext(), "하나만 체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }
                if ((test_1_top).isChecked() == true && (test_1_bottom).isChecked() == true) {
                    Toast.makeText(getApplicationContext(), "하나만 체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }
                if ((test_1_bottom).isChecked() == true && (test_1_middle).isChecked() == true) {
                    Toast.makeText(getApplicationContext(), "하나만 체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }
                if ((test_1_bottom).isChecked() == true && (test_1_middle).isChecked() == true && (test_1_top).isChecked() == true ) {
                    Toast.makeText(getApplicationContext(), "하나만 체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }
                if ((test_1_bottom).isChecked() == false && (test_1_middle).isChecked() == false && (test_1_top).isChecked() == false ) {
                    Toast.makeText(getApplicationContext(), "체크하여 주세요 :)", Toast.LENGTH_SHORT).show();

                }



                if ((test_1_top).isChecked() == true && (test_1_bottom).isChecked() == false && (test_1_middle).isChecked() == false ) {
                    add2 = 10;
                    Intent getintent2 = getIntent();
                    int data1 = getintent2.getIntExtra("add1", 0);
                    add2 = data1 + add2;
                    Log.v("인텐트","add2값= "+add2);
                    Toss_add2();

                }// top 체크

                if ((test_1_middle).isChecked() == true && (test_1_top).isChecked() == false && (test_1_bottom).isChecked() == false) {
                    add2 = 5;
                    Intent getintent2 = getIntent();
                    int data1 = getintent2.getIntExtra("add1", 0);
                    add2 = data1 + add2;
                    Log.v("인텐트","add2값= "+add2);
                    Toss_add2();
                }//middle 체크

                if ((test_1_bottom).isChecked() == true && (test_1_middle).isChecked() == false && (test_1_top).isChecked() == false) {
                    add2 = 3;
                    Intent getintent2 = getIntent();
                    int data1 = getintent2.getIntExtra("add1", 0);
                    add2 = data1 + add2;
                    Log.v("인텐트","add2값= "+add2);
                    Toss_add2();
                }//바텀 체크





            }
        });



    }

    public void Toss_add2 (){
        Intent intent = new Intent(this, Test_3.class);
        intent.putExtra("add2",add2);
        startActivity(intent);
    }


}


