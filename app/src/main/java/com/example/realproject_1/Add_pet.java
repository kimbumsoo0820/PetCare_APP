package com.example.realproject_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class Add_pet extends Activity {

    Button add_pet_finish,load_result3,back_addpet_pethome;
    EditText pet_name,pet_birth,pet_kindof,pet_sex;
    TextView result3;
    String strmember;
    my_DBhelper dbhelper1;
    SQLiteDatabase sqldb1;
    private VideoView videoview;

    public class my_DBhelper extends SQLiteOpenHelper {
        public my_DBhelper(Context context) {
            super(context, "PET", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table PETTBL(Mname varchar(50) primary key, Mbirth varchar(30), Mkind varchar(50), Msex varchar(10))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("drop table if exists PETTBL");
            onCreate(db);
        }
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pet);

        videoview=(VideoView)findViewById(R.id.videoview) ;

        pet_name=(EditText)findViewById(R.id.pet_name);
        pet_birth=(EditText)findViewById(R.id.pet_birth);
        pet_kindof=(EditText)findViewById(R.id.pet_kindof);
        pet_sex=(EditText)findViewById(R.id.pet_sex);
        add_pet_finish=(Button)findViewById(R.id.add_pet_finish);
        result3=(TextView)findViewById(R.id.result3);
        back_addpet_pethome=(Button)findViewById(R.id.back_addpet_pethome) ;




        dbhelper1 = new my_DBhelper(this);


        add_pet_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check_edt = pet_name.getText().toString();
                sqldb1 = dbhelper1.getReadableDatabase();
                Cursor cursor;
                cursor=sqldb1.rawQuery("SELECT * FROM PETTBL;", null);
                String checkdb;
                int checkdb_db=0;
                while (cursor.moveToNext()){

                    checkdb=cursor.getString(0);
                    Log.v("db확인","입력된 값"+cursor);
                    if (checkdb.isEmpty()){
                        checkdb_db=1;
                    }

                }


                    if (check_edt.isEmpty()){
                        Toast.makeText(getApplicationContext(),"정보를 입력하여 주세요!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        sqldb1 = dbhelper1.getWritableDatabase();
                        sqldb1.execSQL("INSERT INTO PETTBL VALUES  ('" + pet_name.getText().toString() + "','"
                                + pet_birth.getText().toString() + "', '" + pet_kindof.getText().toString() + "','" + pet_sex.getText().toString() + "')");
                        sqldb1.close();
                        Toast.makeText(getApplicationContext(), "등록 완료", Toast.LENGTH_SHORT).show();
                        pet_flag();
                    }




            }
        });


        back_addpet_pethome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Test_pethome.class);
                startActivity(intent);
            }
        });

    }



    public void pet_flag() {
        Intent intent = new Intent(this, Test_pethome.class);
        intent.putExtra("pet_flag", 1);
        startActivity(intent);

    }
}
