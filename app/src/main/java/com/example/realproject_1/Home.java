package com.example.realproject_1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

 //여기는 weather 레이아웃 코딩 이름 못바꿔서 이렇게 돼버림
public class Home extends AppCompatActivity {
      WebView mWebView;
      Button back_weather_home;
     private String myUrl = "http://";


     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
         back_weather_home=(Button)findViewById(R.id.back_weather_home);

         mWebView = (WebView) findViewById(R.id.webView);//xml 자바코드 연결
         mWebView.getSettings().setJavaScriptEnabled(true);// 자바 스크립트 허용

         mWebView.loadUrl("https://weather.naver.com/");//웹뷰 실행
         mWebView.setWebChromeClient(new WebChromeClient());//크롬 사용
         mWebView.setWebViewClient(new WebViewClientClass());

         back_weather_home.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(),RealHome.class);
                 startActivity(intent);
             }
         });



    }

     @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼 이벤트
         if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
             mWebView.goBack();
             return true;
         }
         return super.onKeyDown(keyCode, event);
     }



     private class WebViewClientClass extends WebViewClient {//페이지 이동
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
             Log.d("check URL",url);
             view.loadUrl(url);
             return true;
         }
     }




}


