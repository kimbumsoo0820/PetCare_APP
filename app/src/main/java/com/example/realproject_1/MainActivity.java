package com.example.realproject_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Signup {
    EditText TextinputEditText_ID, TextinputEditText_password;
    TextView text_signup;
    int IDflag = 0;
    int PWflag = 0;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    RelativeLayout Rlt_login;
    private String TAG = "VideoActivity";
    private VideoView videoView;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "나가려구? 어딜가려구 :)", Toast.LENGTH_SHORT).show();
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_signup = (TextView) findViewById(R.id.text_signup);
        TextinputEditText_ID = (EditText) findViewById(R.id.TextinputEditText_ID);
        TextinputEditText_password = (EditText) findViewById(R.id.TextinputEditText_password);
        Rlt_login = (RelativeLayout) findViewById(R.id.Rlt_login);

        videoView = (VideoView)findViewById(R.id.videoview);
        //play video
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName()+"/"+R.raw.addpet_background));

        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        text_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), Signup.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"회원가입 :)",Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.Rlt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqldb = dbhelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqldb.rawQuery("SELECT*FROM MEMBERTBL;", null);
                String edt1 = null; // 로그인화면에서 로그인 ID 입력
                String pass1 = null; // 로그인화면에서 pw
                String edt2 = null; // 데베 등록된 ID
                String pass2 = null;// 데베 등록된 pw

                while (cursor.moveToNext()) {
                    edt2 = cursor.getString(0);
                    pass2 = cursor.getString(1);

                    edt1 = TextinputEditText_ID.getText().toString();
                    pass1 = TextinputEditText_password.getText().toString();





                    try {
                        if (edt2.equals(edt1)) {
                            IDflag=1;
                            if (pass2.equals(pass1)) {
                                Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), RealHome.class);
                                startActivity(intent);
                                PWflag=1;
                                break;
                            } else {
                                Toast.makeText(getApplicationContext(), "회원이지만 비밀번호가 틀립니다", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } else {
                            /* Toast.makeText(getApplicationContext(), "아이디가 틀립니다", Toast.LENGTH_SHORT).show(); */
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                }//while문
                if(IDflag==0 && PWflag==0){
                    Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }

                cursor.close();
                sqldb.close();
            }
        });


    }
}
