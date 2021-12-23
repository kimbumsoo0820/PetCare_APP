package com.example.realproject_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Signup extends Activity {
    EditText signup_ID, signup_PW, signup_Name, signup_Birth, signup_checkPW, signup_email;
    Button signup_Finish, signup_checkID, btn_drop, Load_data, signup_Back;
    String sID, sPW, sPWcheck, sName, sBirth, sEmail;
    TextView result,signup_checkPW_Right,signup_checkPW_Wrong;
    int check = 1;
    int login = 0;


    myDBhelper dbhelper;
    SQLiteDatabase sqldb;
    Cursor cursor;
    String strmember=".";

    @Override
    public void onBackPressed(){
        Intent intent_information = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent_information);
    }




    public class myDBhelper extends SQLiteOpenHelper {
        public myDBhelper(Context context) {
            super(context, "MEMBER", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table MEMBERTBL(Mid varchar(50) primary key, Mpw varchar(30), mpwcheck varchar(50), Mname varchar(10), Mbirth INTEGER, Memail varchar(30))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("drop table if exists MEMBERTBL");
            onCreate(db);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        signup_PW = (EditText) findViewById(R.id.signup_PW);
        signup_Name = (EditText) findViewById(R.id.signup_Name);
        signup_Birth = (EditText) findViewById(R.id.signup_Birth);
        signup_checkPW = (EditText) findViewById(R.id.signup_checkPW);
        signup_email = (EditText) findViewById(R.id.signup_email);
        result = (TextView) findViewById(R.id.result);
        signup_checkID = (Button) findViewById(R.id.signup_checkID);


        dbhelper = new myDBhelper(this);


        signup_checkID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signup_ID = (EditText) findViewById(R.id.signup_ID);
                sqldb = dbhelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqldb.rawQuery("SELECT * FROM MEMBERTBL;", null);
                String checkID;
                String checkID2;


                while (cursor.moveToNext()) {
                    checkID = signup_ID.getText().toString();
                    checkID2 = cursor.getString(0);
                    Log.v("ID확인", "입력한 ID" + checkID + "   " + "데베에서 읽어온 ID" + checkID2);

                    try {


                        if (checkID.equals(checkID2)) {

                            check = 0;
                            login = 0;
                            Log.v("ID확인", "입력한 ID" + checkID + "   " + "데베에서 읽어온 ID" + checkID2);
                            Log.v("", "check값   " + check);
                            break;

                        }
                        if (checkID.isEmpty()) {
                            check = 0;
                            login = 1;

                            Log.v("ID확인", "입력한 ID" + checkID + "   " + "데베에서 읽어온 ID" + checkID2);
                            Log.v("", "check값   " + check);
                            break;
                        }
                        if (checkID != checkID2) {
                            check = 1;
                            login = 2;

                            Log.v("ID확인", "입력한 ID" + checkID + "   " + "데베에서 읽어온 ID" + checkID2);
                            Log.v("", "check값   " + check);
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "오류", Toast.LENGTH_SHORT).show();

                    }

                }
                if (login == 0 && check == 0) {
                    Toast.makeText(getApplicationContext(), "사용중인 ID입니다.", Toast.LENGTH_SHORT).show();
                }
                if (login == 1 && check == 0) {
                    Toast.makeText(getApplicationContext(), "ID를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                if (login == 2 && check == 1) {
                    Toast.makeText(getApplicationContext(), "사용가능한 ID입니다.", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                sqldb.close();


            }
        });


        findViewById(R.id.signup_Finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup_ID = (EditText) findViewById(R.id.signup_ID);
                String checkID = signup_ID.getText().toString();
                signup_checkPW_Right =(TextView)findViewById(R.id.signup_checkPW_Right);
                signup_checkPW_Wrong =(TextView)findViewById(R.id.signup_checkPW_Wrong);
                signup_PW=(EditText)findViewById(R.id.signup_PW);
                signup_checkPW=(EditText)findViewById(R.id.signup_checkPW);
                String pw = signup_PW.getText().toString();
                String pw_check = signup_checkPW.getText().toString();
                int check_pw=0;




                if (check == 0) {
                    Log.v("", "check값   " + check);

                    Toast.makeText(getApplicationContext(), "ID중복체크를 하세요", Toast.LENGTH_SHORT).show();

                }



                    if (checkID.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "ID 입력하세요", Toast.LENGTH_SHORT).show();
                        check = 2;
                    }


                if (pw.equals(pw_check)) {

                    if (check == 1) {
                        sqldb = dbhelper.getWritableDatabase();
                        sqldb.execSQL("INSERT INTO MEMBERTBL VALUES  ('" + signup_ID.getText().toString() + "','"
                                + signup_PW.getText().toString() + "', '" + signup_checkPW.getText().toString()
                                + "','" + signup_Name.getText().toString() + "','" + signup_Birth.getText().toString() + "','" + signup_email.getText().toString() + "')");
                        sqldb.close();
                    }
                    if (check == 1) {
                        Toast.makeText(getApplicationContext(), "가입 완료", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        signup_checkPW_Wrong.setVisibility(View.INVISIBLE);
                        startActivity(intent);
                    }
                }
                else{
                    signup_checkPW_Wrong.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"비밀번호를 맞게 입력하였는지 확인하여 주세요 :)",Toast.LENGTH_LONG).show();
                }





            }
        });


                findViewById(R.id.Load_data).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                sqldb = dbhelper.getReadableDatabase();
                cursor = sqldb.rawQuery("SELECT * FROM MEMBERTBL;", null);

                while (cursor.moveToNext()) {
                    strmember += cursor.getString(0) + "     " + cursor.getString(1) + "     " + cursor.getString(2) + "     " + cursor.getString(3) + "     " + cursor.getString(4) + "     " + cursor.getString(5) + "\r\n";

                }
                result.setText(strmember);
                cursor.close();
                sqldb.close();
            }
        });


        findViewById(R.id.signup_Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
