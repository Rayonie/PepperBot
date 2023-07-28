package com.example.my4throbotapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class LandingPageActivity extends AppCompatActivity {

    Timer timer;

    VideoView landing_vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        landing_vv = findViewById(R.id.landingvideoView);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LandingPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

        String path = "android.resource://com.example.my4throbotapplication/"+R.raw.main;
        Uri u = Uri.parse(path);
        landing_vv.setVideoURI(u);
        landing_vv.start();

        landing_vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(false);
            }
        });
    }


    @Override
    protected void onPostResume() {
        landing_vv.resume();
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        landing_vv.resume();
        super.onResume();
    }


    @Override
    protected void onPause() {
        landing_vv.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        landing_vv.stopPlayback();
        super.onDestroy();
    }
}