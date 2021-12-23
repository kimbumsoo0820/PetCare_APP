package com.example.realproject_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MyDiaryActivity extends AppCompatActivity {
    DatePicker datePicker;
    EditText EditDiary;
    Button ButtonWrite,back_diary_home;
    String fileName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        EditDiary = (EditText) findViewById(R.id.EditDiary);
        ButtonWrite = (Button) findViewById(R.id.ButtonWrite);
        back_diary_home=(Button)findViewById(R.id.back_diary_home);

        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfyear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfyear + 1) + "_" + Integer.toString(dayOfMonth) + ".txt";
                String string = readDiary(fileName);
                EditDiary.setText(string);
                ButtonWrite.setEnabled(true);
            }
        });

        ButtonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = EditDiary.getText().toString();

                    outFs.write(str.getBytes());

                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " 이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }
        });

        back_diary_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RealHome.class);
                startActivity(intent);

            }
        });
        {

        }

    }

    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            ButtonWrite.setText("수정하기");
        } catch (IOException e) {
            EditDiary.setHint("내용 없음");
            ButtonWrite.setText("새로 저장");
        }
        return diaryStr;
    }


}
