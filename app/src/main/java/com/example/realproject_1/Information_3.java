package com.example.realproject_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Information_3 extends YouTubeBaseActivity {
    YouTubePlayerView youtubeView;
    Button btn_play, back_info_list;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expertise_1);


        back_info_list = (Button)findViewById(R.id.back_info_list);
        btn_play = (Button)findViewById(R.id.btn_play);
        youtubeView =(YouTubePlayerView)findViewById(R.id.youtubeView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("ct0MsMlrLXU");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                youtubeView.initialize("AIzaSyCvCmFO889PYAs7Bllw_QSINWRF6QpTH_g", listener);
            }
        });

        back_info_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Expertise.class);
                startActivity(intent);
            }
        });

    }
}
