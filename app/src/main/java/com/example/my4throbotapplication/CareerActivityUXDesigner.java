package com.example.my4throbotapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class CareerActivityUXDesigner extends CareerActivityUIDesigner {

    ImageView career_ui, career_ux, career_develop, career_analyst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_uxdesigner);
        career_ui = findViewById(R.id.planet3);
        career_ux = findViewById(R.id.planet4);
        career_develop = findViewById(R.id.planet1);
        career_analyst = findViewById(R.id.planet2);


        career_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityUXDesigner.this,
                        CareerActivityUIDesigner.class);
                startActivity(i);
            }
        });

        career_develop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityUXDesigner.this,
                        CareerActivityDeveloper.class);
                startActivity(i);
            }
        });

        career_analyst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CareerActivityUXDesigner.this,
                        CareerActivitySystemAnalyst.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onPostResume() {
        career_vv1.resume();
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        career_vv1.resume();
        super.onResume();
    }


    @Override
    protected void onPause() {
        career_vv1.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        career_vv1.stopPlayback();
        super.onDestroy();
    }
}