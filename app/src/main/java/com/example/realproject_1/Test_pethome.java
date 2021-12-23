package com.example.realproject_1;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test_pethome extends Add_pet {
    ImageView click_profile;
    Button change_profile,back_mypet_home;
    TextView result1,result2,result4;
    LinearLayout add_pet;
    Integer flag_btn =0;
    @Override
    public void onBackPressed(){
        Intent intent_information = new Intent(getApplicationContext(), RealHome.class);
        startActivity(intent_information);
    }

    private static final int IMAGE_PICK_CODE =1000;
    private static final int PERMISSION_CODE =1000;
    my_DBhelper dbhelper;
    SQLiteDatabase sqldb;
    Cursor cursor;
    String strmember="",strmember2="",strmember4="";
    Button get_petinfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_petinfo);


        click_profile = findViewById(R.id.click_profile);
        change_profile = findViewById(R.id.change_profile);
        result1 = (TextView)findViewById(R.id.result1) ;
        add_pet = (LinearLayout)findViewById(R.id.add_pet);
        get_petinfo =(Button)findViewById(R.id.get_petinfo);
        back_mypet_home=(Button)findViewById(R.id.back_mypet_home);
        result2=(TextView)findViewById(R.id.result2);
        result4=(TextView)findViewById(R.id.result4);


        Intent getflag = getIntent();
        int flag = getflag.getIntExtra("pet_flag", 0);
        Log.v("플래그", "플래그값" + flag);

        change_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //런타임 퍼미션
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for
                        requestPermissions(permissions,PERMISSION_CODE);

                    }
                    else {
                        //permission alreadty granted
                        pickImageFromGallery();
                    }
                }
                else {
                    // system os is less then marshmallow
                    pickImageFromGallery();
                    
                }
            }




        });// set onclick


        get_petinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag_btn == 1) {
                    Toast.makeText(getApplicationContext(), "이미 데이터값이 있습니다.", Toast.LENGTH_SHORT).show();
                }
                if (flag_btn == 0) {
                    sqldb1 = dbhelper1.getReadableDatabase();
                    Cursor cursor;
                    cursor = sqldb1.rawQuery("SELECT * FROM PETTBL;", null);

                    while (cursor.moveToNext()) {
                        strmember += cursor.getString(0)  + "\r\n";
                        strmember2 += cursor.getString(2) + "\r\n";
                        strmember4  += cursor.getString(3)+ "\r\n";
                    }

                    result1.setText(strmember);
                    result2.setText(strmember2);
                    result4.setText(strmember4);

                    cursor.close();
                    sqldb1.close();
                    flag_btn=1;
                }
            }
        });




        add_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Add_pet.class);
                startActivity(intent);
            }
        });

        back_mypet_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RealHome.class);
                startActivity(intent);
            }
        });
    }// on create





    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

//handle result of runtime permission


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }
                else{
                    //permission was denied
                    Toast.makeText(this,"거부하셨습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    //handle

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set IMAGE
            click_profile.setImageURI(data.getData());
        }
    }

}
