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


public class Test_1 extends AppCompatActivity {
    int add1, t1_sum;

    Button test1_go_test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_1);
        


        test1_go_test2 = (Button) findViewById(R.id.test1_go_test2);
        CheckBox test_1_top = (CheckBox) findViewById(R.id.test_1_top);
        CheckBox test_1_middle = (CheckBox) findViewById(R.id.test_1_middle);
        CheckBox test_1_bottom = (CheckBox) findViewById(R.id.test_1_bottom);
        test1_go_test2.setClickable(false);




        test1_go_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add1 = 0;


                CheckBox test_1_top = (CheckBox) findViewById(R.id.test_1_top);
                CheckBox test_1_middle = (CheckBox) findViewById(R.id.test_1_middle);
                CheckBox test_1_bottom = (CheckBox) findViewById(R.id.test_1_bottom);






                if ((test_1_top).isChecked() == true && (test_1_bottom).isChecked() == false && (test_1_middle).isChecked() == false ) {

                    add1 = 10;
                    Log.v("인텐트","add1값= "+add1);
                    Toss_add1();

                }// top 체크

                if ((test_1_middle).isChecked() == true && (test_1_top).isChecked() == false && (test_1_bottom).isChecked() == false) {

                    add1 = 5;
                    Log.v("인텐트","add1값= "+add1);
                    Toss_add1();

                }//middle 체크

                if ((test_1_bottom).isChecked() == true && (test_1_middle).isChecked() == false && (test_1_top).isChecked() == false) {

                    add1 = 3;
                    Log.v("인텐트","add1값= "+add1);
                    Toss_add1();

                }//바텀 체크
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





            }
        });


    }

    public void Toss_add1() {
        Intent intent = new Intent(this, Test_2.class);
        intent.putExtra("add1", add1);
        startActivity(intent);

    }


}

//todo 지금 상황이 체크박스로 체크하면 변수에 인트 형으로 값을 저장하고, 그 인트형 값을 인텐트를 통해 Test_main 으로 넘겨줘서 Test_main 에서 if 문으로 값에따라 이미지를 다르게 삽입하는거 구현하다가
//todo 값을 제대로 보내는 건지 알수 가 없음.. 지금 인텐트 구조를   public void Toss_top ()   이런식으로 함수호출 가능하도록 했는데 이 함수가 호출이 되는지 안되는지 알수가 없음
//todo 참고로 Toss_top 는 체크박스 첫번째 거, Toss_middle 은 가운데거 Toss_bottom 은 맨아래 체크박스

//todo respond 체크박스 중복 선택시 clickable-> false값으로 수정